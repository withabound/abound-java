# Abound Java SDK

The Abound Java SDK provides convenient access to the Abound API from applications written in Java.

### Documentation

The Abound Java SDK supports all Abound API endpoints. See the [API Documentation][docs] and the [API Reference][api-reference] for complete information about the APIs.

### Requirements

You can start to integrate the Abound Java SDK into your solution as soon as you [create an account with Abound][developer-dashboard-signup] and [obtain your API keys][developer-dashboard-keys].

### Installation

Install the SDK as a Maven dependency:

```xml
<dependency>
    <groupId>com.withabound</groupId>
    <artifactId>withabound-java</artifactId>
    <version>[latest version]</version>
</dependency>
```

or as a Gradle dependency:

```groovy
dependencies {
    implementation group: 'com.withabound', name: 'withabound-java', version: '[latest version]'
    // or shorthand,
    implementation 'com.withabound:withabound-java:[latest version]'
}
```

### Usage

The Abound client must be configured with your account's `appId` and `appSecret`, which are available on the [Keys page][developer-dashboard-keys] of the [Abound Dashboard][developer-dashboard].

HTTP 4xx and 5xx responses from Abound's APIs can be handled by catching an `AboundApiException` on every SDK method.

```java
AboundConfig aboundConfig = new AboundConfig(
  "appId_f2d...",
  "appSecret_bf3...",
  AboundEnvironment.SANDBOX,
  AboundApiVersion.V2
);

Abound abound = new Abound(aboundConfig);

UserRequest toCreate = UserRequest.builder()
  .email("user@example.com")
  .build();

try {
  abound.users().create(toCreate);
} catch (AboundApiException ex) {
  logger.warn(ex.getStatusCode());
  logger.warn(ex.getMessage());
} catch (IOException ex) {
  logger.error(...);
}
```

### Examples

#### Users

Create a `User`:

```java
AboundResponse<User> response = abound.users().create(UserRequest.builder()
  .email("jane.doe@example.com")
  .profile(UserProfile.builder()
    .firstName("Jane")
    .lastName("Doe")
    .address("123 Maple St")
    .address2("Apt 101")
    .city("Anytown")
    .state("PA")
    .zipcode("17101")
    .phoneNumber("5555555555")
    .dateOfBirth("1989-05-01")
    .socialSecurityNumber("123456789")
    .build())
  .build());

System.out.println(response.getData().getUserId());
```

List `User`s:

```java
AboundBulkResponse<User> response = abound.users().list();

System.out.println(response.getData()); // list of Users
```

Retrieve a `User`:

```java
String userId = "userId_506...";

AboundResponse<User> response = abound.users().retrieve(userId);

System.out.println(response.getData().getUserId());
```

Update a `User`:

```java
String userId = "userId_506...";

UserRequest userUpdates = UserRequest.builder()
  .email("janedoe123@example.com")
  .profile(UserProfile.builder()
    .phoneNumber("4444444444")
    .build())
  .build();

AboundResponse<User> response = abound.users().update(userId, userUpdates);

System.out.println(response.getData().getEmail());
```

#### Expenses

Create `Expense`s:

```java
String userId = "userId_506...";
ExpenseRequest expenseRequest = ExpenseRequest.builder()
  .amount(139.99)
  .description("Tax Filing Fee")
  .date("2021-01-16")
  .expenseType(ExpenseType.BUSINESS)
  .build();

AboundBulkResponse<Expense> response = abound.expenses()
  .create(userId, Collections.singletonList(expenseRequest));

System.out.println(response.getData()); // list of created Expenses
```

List `Expense`s for a `User`:

```java
String userId = "userId_506...";

AboundBulkResponse<Expense> response = abound.expenses().list(userId);
// filter Expenses by year:
AboundBulkResponse<Expense> response =
  abound.expenses().list(userId, ExpenseParams.builder().year("2020").build());

System.out.println(response.getData()); // list of Expenses
```

Retrieve an `Expense`:

```java
String userId = "userId_506...";
String expenseId = "expenseId_65c...";

AboundResponse<Expense> response = abound.expenses().retrieve(userId, expenseId);

System.out.println(response.getData().getDeductionAmount());
```

Update an `Expense`:

```java
String userId = "userId_506...";
String expenseId = "expenseId_65c...";
ExpenseRequest expenseUpdates = ExpenseRequest.builder()
  .amount(249.99)
  .description("Premium Tax Filing Service Fee")
  .build();

AboundResponse<Expense> response =
  abound.expenses().update(userId, expenseId, expenseUpdates);

System.out.println(response.getData().getDescription());
```

Delete an `Expense`:

```java
String userId = "userId_506...";
String expenseId = "expenseId_65c...";

AboundResponse<EmptyJsonObject> response = abound.expenses().delete(userId, expenseId);

System.out.println(response.getData()); // {}
```

#### Mileage

Create `Mileage`s:

```java
String userId = "userId_506...";
MileageRequest mileageRequest = MileageRequest.builder()
  .distance(21.1)
  .date("2021-03-04")
  .description("Client visit")
  .build();

AboundBulkResponse<Mileage> response =
  abound.mileages().create(userId, Collections.singletonList(mileageRequest));

System.out.println(response.getData()); // list of created Mileages
```

Retrieve a `Mileage`:

```java
String userId = "userId_506...";
String mileageId = "mileageId_4af...";

AboundResponse<Mileage> response = abound.mileages().retrieve(userId, mileageId);

System.out.println(response.getData().getDistance());
```

#### Payment Methods

Create a `PaymentMethod`:

```java
String userId = "userId_506...";
PaymentMethodRequest paymentMethodRequest = PaymentMethodRequest.builder()
  .accountNumber("123456789")
  .routingNumber("44449944")
  .accountType(AccountType.BUSINESS)
  .accountClass(AccountClass.CHECKING)
  .build();

AboundResponse<PaymentMethod> response = abound.paymentMethods().create(userId, paymentMethodRequest);

System.out.println(response.getData()); // created PaymentMethod
```

List `PaymentMethod`s for a `User`:

```java
String userId = "userId_506...";

AboundBulkResponse<PaymentMethod> response = abound.paymentMethods().list(userId);

System.out.println(response.getData()); // list of PaymentMethods
```

Retrieve a `PaymentMethod`:

```java
String userId = "userId_506...";
String paymentMethodId = "paymentMethodId_329...";

AboundResponse<PaymentMethod> response = abound.paymentMethods().retrieve(userId, paymentMethodId);

System.out.println(response.getData().getDisplayName());
```

#### Tax Payments

Create a `TaxPayment`:

```java
String userId = "userId_506...";
String paymentMethodId = "paymentMethodId_329...";
TaxPaymentRequest taxPaymentRequest = TaxPaymentRequest.builder()
  .paymentMethodId(paymentMethodId)
  .year("2021")
  .period(TaxPeriod.Q1)
  .amount(840.81)
  .entity(TaxPaymentEntity.IRS)
  .build();

AboundResponse<TaxPayment> response = abound.taxPayments().create(userId, taxPaymentRequest);

System.out.println(response.getData().getTaxPaymentId());
```

List `TaxPayment`s for a `User`:

```java
String userId = "userId_506...";

AboundBulkResponse<TaxPayment> response = abound.taxPayments().list(userId);

System.out.println(response.getData()); // list of TaxPayments
```

Retrieve a `TaxPayment`:

```java
String userId = "userId_506...";
String taxPaymentId = "taxPaymentId_614...";

AboundResponse<TaxPayment> response = abound.taxPayments().retrieve(userId, taxPaymentId);

System.out.println(response.getData().getStatus());
```

#### Incomes

Create `Income`s:

```java
String userId = "userId_506...";
IncomeRequest w2IncomeRequest = IncomeRequest.builder()
  .incomeType(IncomeType.W2)
  .amount(55000.00)
  .date("2020-12-30")
  .build();
IncomeRequest ten99intIncomeRequest = IncomeRequest.builder()
  .incomeType(IncomeType.TEN99INT)
  .amount(10.85)
  .date("2020-12-15")
  .description("Savings Account interest accrued")
  .build();

AboundBulkResponse<Income> response = abound.incomes().create(userId, Arrays.asList(
  w2IncomeRequest,
  ten99intIncomeRequest
));

System.out.println(response.getData()); // list of created Incomes
```

List `Income`s for a `User`:

```java
String userId = "userId_506...";

AboundBulkResponse<Income> response = abound.incomes().list(userId);
// filter Incomes by IncomeType
AboundBulkResponse<Income> response =
  abound.incomes().list(userId, IncomeParams.builder().incomeType(IncomeType.W2).build());

System.out.println(response.getData()); // list of Incomes
```

Retrieve an `Income`:

```java
String userId = "userId_506...";
String incomeId = "incomeId_8cb...";

AboundResponse<Income> response = abound.incomes().retrieve(userId, incomeId);

System.out.println(response.getData().getAmount());
```

Update an `Income`:

```java
String userId = "userId_506...";
String incomeId = "incomeId_8cb...";
IncomeRequest incomeUpdates = IncomeRequest.builder()
  .amount(57500.00)
  .build();

AboundResponse<Income> response = abound.incomes().update(userId, incomeId, incomeUpdates);

System.out.println(response.getData().getAmount());
```

Delete an `Income`:

```java
String userId = "userId_506...";
String incomeId = "incomeId_8cb...";

AboundResponse<EmptyJsonObject> response = abound.incomes().delete(userId, incomeId);

System.out.println(response.getData()); // {}
```

#### Taxes

List `Taxes` for a `User`:

```java
String userId = "userId_506...";

AboundBulkResponse<Tax> response = abound.taxes().list(userId);

System.out.println(response.getData()); // list of tax calculations for all years
```

Retrieve `Taxes` for a `User` for a specific year:

```java
String userId = "userId_506...";
String year = "2020";

AboundResponse<Tax> response = abound.taxes().retrieve(userId, year);

System.out.println(response.getData().getTotalTax());
```

Calculate `Taxes` based on the specified adjustments:

```java
String userId = "userId_506...";
String year = "2020";
TaxRequest taxUpdates = TaxRequest.builder()
  .w2Income(75000.00)
  .mileage(16500.0)
  .build();

AboundResponse<Tax> response = abound.taxes().calculate(userId, year, taxUpdates);

System.out.println(response.getData().getEffectiveTaxRate());
```

#### Tax Categories

Retrieve `TaxCategories` by tax year:

```java
String year = "2021";

AboundResponse<String[]> response = abound.taxCategories().retrieve(year);

System.out.println(response.getData()); // ["Advertising and Marketing", "Car and Truck", etc.]
```

### Development

It is recommended to configure your IDE with the `google-java-format` plugin: https://github.com/google/google-java-format#intellij-android-studio-and-other-jetbrains-ides

Run all tests:

```console
$ ./gradlew test
```

Run `spotless` and apply auto-fixes:

```console
$ ./gradlew spotlessApply
```

Publish the SDK to the local Maven repository (`~/.m2/repository`):

```console
$ ./gradlew publishToMavenLocal
```

#### A note about Notes

A number of different Abound domain resources have an optional `notes` attribute. If provided, this can either be a String, or a JSON object. As a result, `notes` are more difficult to work with in languages such as Java that lack first-class support for JSON. We have chosen to represent these fields using `com.google.gson.JsonElement` — an abstract class that is subclassed by both `com.google.gson.JsonObject` and `com.google.gson.JsonPrimitive`. This is a convenient way of representing this union type, and has various advantages over using just a regular `java.lang.Object`.

As a result, for the resources that have `notes`, there are a few interesting pieces worth highlighting:

- If a class with `notes` is decorated with a Lombok `@Setter`, Lombok would normally generate code like,
    ```java
    public void setNotes(final JsonElement notes) {
      this.notes = notes;
    }
    ```

    We don't necessarily want Lombok to generate this setter, because callers could still provide invalid subclasses as arguments (e.g. passing a `com.google.gson.JsonArray` would compile, but would be invalid `notes`).

    Instead, we can define two `notes` setters that more accurately capture the possible (valid) types:

    ```java
    public void setNotes(final String notes) {
      this.notes = new JsonPrimitive(notes);
    }

    public void setNotes(final JsonObject notes) {
      this.notes = notes;
    }
    ```

    Because we have defined `setNotes` methods, Lombok will not auto-generate its own.


- We need to modify the Lombok `@Builder` in a very similar fashion. If a resource with `notes` is decorated with Lombok `@Builder`, it would typically auto-generate code as follows:

    ```java
    @Builder
    public class Resource {
      private JsonElement notes;

      public static ResourceBuilder {
        ...

        public ResourceBuilder notes(final JsonElement notes) {
          this.notes = notes;
          return this;
        }
      }
    }
    ```

  However, we can alter the signature of the auto-generated `.notes()` builder method by defining the static inner builder class (which must be named `<OuterClass>Builder`), an instance variable, and our own builder methods:

    ```java
    @Builder
    public class Resource {
      private JsonElement notes;

      public static ResourceBuilder {
        private JsonElement notes;

        public ResourceBuilder notes(final String notes) {
          this.notes = new JsonPrimitive(notes);
          return this;
        }

        public ResourceBuilder notes(final JsonObject notes) {
          this.notes = notes;
          return this;
        }
      }
    }
    ```

  This is enough; we do not need to define the entire `Builder` class; Lombok will inject the other required fields, methods, and constructors for us (assuming we named the static inner class correctly). [Lombok's @Builder docs](https://projectlombok.org/features/Builder) goes into further detail regarding this topic.


[docs]: https://docs.withabound.com
[api-reference]: https://docs.withabound.com/reference
[developer-dashboard]: https://dashboard.withabound.com
[developer-dashboard-keys]: https://dashboard.withabound.com/keys
[developer-dashboard-signup]: https://dashboard.withabound.com/signup
