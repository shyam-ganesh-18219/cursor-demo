# Generate Unit Tests

Generate JUnit 5 unit tests for the selected Java file.

- Cover all public methods.
- Include edge cases (e.g., null inputs, empty collections, boundary values).
- Follow standard naming conventions: `methodName_stateUnderTest_expectedBehavior`.
- Use `@Test`, `@BeforeEach`, and appropriate assertions from `org.junit.jupiter.api`.
- Mock dependencies with Mockito where necessary.
