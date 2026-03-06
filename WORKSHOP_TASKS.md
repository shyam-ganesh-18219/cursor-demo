# Workshop Tasks

Welcome to the AI Coding Workshop! Below are hands-on exercises designed to help you explore, debug, and extend this Java project using AI tools like **Cursor**.

---

## Exercise 1 – Codebase Exploration

Use AI to understand how the project is structured and how authentication works.

**Prompt example:**
```
@Codebase explain how authentication works in this project
```

**Goal:** Understand the flow from `AuthController` → `AuthService` → `UserRepository` → `FakeDatabase`.

---

## Exercise 2 – Feature Planning

Ask AI to help you plan a new feature before writing any code.

**Prompt example:**
```
Plan how to add pagination to the users API
```

**Goal:** Get a step-by-step implementation plan for adding `page` and `pageSize` parameters to `UserService.getUsers()`.

---

## Exercise 3 – Feature Implementation

Implement the pagination feature identified in Exercise 2.

**Task:** Add pagination support to the `GET /users` endpoint.

- Modify `UserService.getUsers()` to accept `page` and `pageSize` parameters.
- Update `UserController.getUsers()` to pass those parameters through.
- Look for the `// TODO: pagination support for workshop exercise` comment in `UserService.java` as your starting point.

---

## Exercise 4 – Debugging Logic Bug

There is a deliberate bug in `AuthService.login()` that causes login to fail for all valid users.

**Task:** Find and fix the bug.

**Hint:** Look at how the password comparison is done.

**Prompt example:**
```
Debug why login fails for valid users in AuthService
```

---

## Exercise 5 – Runtime Bug Detection

There is a bug in `UserService.getUsernameById()` that can cause a `NullPointerException` at runtime.

**Task:** Identify the bug and fix it safely.

**Prompt example:**
```
Analyze UserService for possible runtime errors
```

---

Happy coding! 🚀
