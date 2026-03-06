# cursor-workshop-demo

A small Java project built for **AI coding workshops**.  
Participants use AI tools like [Cursor](https://www.cursor.so/) to explore, debug, and extend the codebase.

---

## About This Repository

This project simulates a minimal user-authentication service backed by an **in-memory data store** (no real database required).  
It is intentionally kept simple so that the focus stays on practising AI-assisted development workflows.

### What's inside

| Thing | Details |
|---|---|
| **Intentional bugs** | `AuthService` compares passwords with `==` instead of `.equals()`. `UserService.getUsernameById()` can throw a `NullPointerException`. |
| **Missing feature** | `UserService.getUsers()` is missing pagination support (marked with a `TODO` comment). |
| **Workshop tasks** | Step-by-step exercises are in [`WORKSHOP_TASKS.md`](WORKSHOP_TASKS.md). |
| **Cursor command** | `.cursor/commands/generate-tests.md` – prompts AI to generate JUnit 5 tests for any selected file. |

---

## Project Structure

```
cursor-workshop-demo
├── controller
│   ├── UserController.java
│   └── AuthController.java
├── service
│   ├── UserService.java      ← TODO + NPE bug
│   └── AuthService.java      ← == password bug
├── repository
│   └── UserRepository.java
├── model
│   └── User.java
├── util
│   └── TokenUtil.java
├── data
│   └── FakeDatabase.java
├── .cursor
│   └── commands
│       └── generate-tests.md
├── WORKSHOP_TASKS.md
└── README.md
```

---

## Getting Started

No build tool is required for exploration.  
To compile all sources from the project root:

```bash
find . -name "*.java" | xargs javac -cp .
```

---

## Workshop Exercises

See **[WORKSHOP_TASKS.md](WORKSHOP_TASKS.md)** for the full list of exercises:

1. Codebase Exploration
2. Feature Planning (pagination)
3. Feature Implementation (pagination)
4. Debugging a Logic Bug (password comparison)
5. Runtime Bug Detection (NullPointerException)
