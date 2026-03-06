import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebServer {

    private static final int PORT = 8080;
    private static final Path WEB_DIR = Paths.get("web");

    private final AuthController authController = new AuthController();
    private final UserController userController = new UserController();

    public static void main(String[] args) throws IOException {
        new WebServer().start();
    }

    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);

        server.createContext("/api/login", this::handleLogin);
        server.createContext("/api/users", this::handleUsers);
        server.createContext("/", this::handleStatic);

        server.setExecutor(null);
        server.start();
        System.out.println("Web UI at http://localhost:" + PORT);
    }

    private void handleLogin(HttpExchange exchange) throws IOException {
        if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            sendJson(exchange, 405, "{\"error\":\"Method not allowed\"}");
            return;
        }

        String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        String username = extractJsonString(body, "username");
        String password = extractJsonString(body, "password");

        String result = authController.login(username != null ? username : "", password != null ? password : "");
        String json;
        if (result.equals("Invalid credentials")) {
            json = "{\"error\":\"Invalid credentials\"}";
            sendJson(exchange, 401, json);
        } else {
            json = "{\"token\":\"" + escapeJson(result) + "\"}";
            sendJson(exchange, 200, json);
        }
    }

    private void handleUsers(HttpExchange exchange) throws IOException {
        if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
            sendJson(exchange, 405, "{\"error\":\"Method not allowed\"}");
            return;
        }

        String query = exchange.getRequestURI().getQuery();
        int page = 1;
        int pageSize = 10;
        if (query != null) {
            for (String pair : query.split("&")) {
                String[] kv = pair.split("=", 2);
                if (kv.length == 2) {
                    try {
                        if ("page".equals(kv[0])) page = Integer.parseInt(kv[1]);
                        else if ("pageSize".equals(kv[0])) pageSize = Integer.parseInt(kv[1]);
                    } catch (NumberFormatException ignored) {}
                }
            }
        }

        List<User> users = userController.getUsers(page, pageSize);
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < users.size(); i++) {
            if (i > 0) sb.append(",");
            User u = users.get(i);
            sb.append("{\"id\":").append(u.getId()).append(",\"username\":\"").append(escapeJson(u.getUsername())).append("\"}");
        }
        sb.append("]");
        sendJson(exchange, 200, sb.toString());
    }

    private void handleStatic(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        if (path == null || path.equals("/")) path = "/index.html";
        if (path.startsWith("/")) path = path.substring(1);

        Path file = WEB_DIR.resolve(path).normalize();
        if (!file.startsWith(WEB_DIR) || !Files.exists(file) || !Files.isRegularFile(file)) {
            file = WEB_DIR.resolve("index.html");
        }

        byte[] bytes = Files.readAllBytes(file);
        String contentType = "text/html";
        if (path.endsWith(".css")) contentType = "text/css";
        else if (path.endsWith(".js")) contentType = "application/javascript";

        exchange.getResponseHeaders().set("Content-Type", contentType);
        exchange.sendResponseHeaders(200, bytes.length);
        exchange.getResponseBody().write(bytes);
        exchange.close();
    }

    private static String extractJsonString(String json, String key) {
        Pattern p = Pattern.compile("\"" + key + "\"\\s*:\\s*\"([^\"]*)\"");
        Matcher m = p.matcher(json);
        return m.find() ? m.group(1) : null;
    }

    private static String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private static void sendJson(HttpExchange exchange, int code, String json) throws IOException {
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(code, bytes.length);
        exchange.getResponseBody().write(bytes);
        exchange.close();
    }
}
