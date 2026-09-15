# 🏷️ Cheat Sheet: All Major Validation Annotations

## 1. Nullability & Emptiness

| Annotation | Valid For | What it checks |
| :--- | :--- | :--- |
| `@NotNull` | Any Object | The value cannot be null, but it can be empty (e.g., `""` or `[]`). |
| `@NotEmpty` | Strings, Collections, Arrays | Cannot be null, and its size/length must be greater than 0. |
| `@NotBlank` | Strings only | Cannot be null, and the trimmed length must be greater than 0 (rejects pure whitespace). |

## 2. Size & Numeric Boundaries

| Annotation | Valid For | What it checks |
| :--- | :--- | :--- |
| `@Size(min=X, max=Y)` | Strings, Collections, Arrays | Evaluates if the element count or string length falls within the boundaries. |
| `@Min(value)` / `@Max(value)` | Numeric fields | Evaluates if a number is $\ge$ min or $\le$ max. |
| `@DecimalMin` / `@DecimalMax` | Numeric Strings / Numbers | Same as `@Min`/`@Max`, but accepts a string value for high-precision decimals (e.g., `"0.01"`). |
| `@Positive` / `@PositiveOrZero` | Numeric fields | Enforces that the number must be $> 0$ or $\ge 0$. |
| `@Negative` / `@NegativeOrZero` | Numeric fields | Enforces that the number must be $< 0$ or $\le 0$. |

## 3. Formats & Business Logic

| Annotation | Valid For | What it checks |
| :--- | :--- | :--- |
| `@Email` | Strings | Verifies the string matches a valid email format. |
| `@Pattern(regexp="...")` | Strings | Validates the string against a custom Regular Expression (Regex). |
| `@AssertTrue` / `@AssertFalse` | Booleans | Evaluates whether the property evaluates exactly to true or false. |
| `@Past` / `@PastOrPresent` | Dates / Times | Enforces that a temporal value is in the past. |
| `@Future` / `@FutureOrPresent` | Dates / Times | Enforces that a temporal value is in the future. |

---

## 🎮 Triggers: @Valid vs @Validated

### `@Valid` (Standard Jakarta Validation)
* **Usage:** Put this on the incoming request parameter in your `@RestController` to kickstart validation on a DTO.
* **Cascading:** Place it inside a DTO on nested objects or collections to trigger nested object validation.

### `@Validated` (Spring-Specific Validation)
* **Usage:** Place this at the class level of your `@RestController` to enable validation on flat `@RequestParam` or `@PathVariable` properties.
* **Groups:** It can also be applied to method arguments to support **Validation Groups** (e.g., executing certain validation rules for *Create* operations and others for *Update* operations).
