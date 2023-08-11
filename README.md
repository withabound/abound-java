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
  AboundApiVersion.V3
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
    .country("US")
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

#### Mailings

List `Mailing`s:

```java
String userId = "userId_506...";
String documentId = "documentId_efb...";

AboundBulkResponse<Mailing> response = abound.mailings().list(userId, documentId);

System.out.println(response.getData()); // list of Mailings
```

Create a `Mailing`:

```java
String userId = "userId_506...";
String documentId = "documentId_efb...";

AboundResponse<Mailing> response =
  abound.mailings().create(userId, documentId);

System.out.println(response.getData()); // created Mailing
```

Retrieve a `Mailing`:

```java
String userId = "userId_506...";
String documentId = "documentId_efb...";
String mailingId = "mailingId_d01...";

AboundResponse<Mailing> response = abound.mailings().retrieve(userId, documentId, mailingId);

System.out.println(response.getData());
```

Delete a `Mailing`:

```java
String userId = "userId_506...";
String documentId = "documentId_efb...";
String mailingId = "mailingId_d01...";

AboundResponse<EmptyJsonObject> response = abound.mailings().delete(userId, documentId, mailingId);

System.out.println(response.getData()); // {}
```

### Development

It is recommended to configure your IDE with the `google-java-format` plugin: https://github.com/google/google-java-format#intellij-android-studio-and-other-jetbrains-ides

Run all tests:

```console
./gradlew test
```

Run `spotless` and apply auto-fixes:

```console
./gradlew spotlessApply
```

Publish the SDK to the local Maven repository (`~/.m2/repository`):

```console
./gradlew publishToMavenLocal
```

#### VS Code development

The recommended extensions and settings for Java development with VS Code have been configured in this repository (see [.vscode][vscode-configuration]). Please ensure that Java 8 is installed on your system with `JAVA_HOME` and `GRADLE_USER_HOME` environment variables exported.

Install Java 8 on Mac:

```console
brew tap homebrew/cask-versions
brew install temurin8
echo "export JAVA_HOME=$(/usr/libexec/java_home -v1.8)" >> ~/.zprofile
echo "export GRADLE_USER_HOME=\"$HOME/.gradle\"" >> ~/.zprofile
```

#### Project Layout

```console
.
├── .github
│  └── workflows — CI/release configuration
└── src
    ├── main
    │  ├── java
    │  │   └── com
    │  │       └── withabound
    │  │           ├── exceptions — HTTP 4xx/5xx responses map to these Java exceptions
    │  │           ├── http — OkHttp request helpers
    │  │           ├── models — Java objects that represent request/response bodies
    │  │           └── resources — Defines the public APIs, e.g. abound.users(), and available verbs, e.g. abound.users().list()
    │  │                └── base — Abstract classes that make it easier to define new resources
    │  └── resources
    │      └── VERSION — Defines the versions of this SDK
    └── test
        └── java
            └── com
                └── withabound
                    ├── resources — JUnit tests
                    │  └── asserts — AssertJ AbstractAssert definitions
                    └── util — Test utils
```

Abbreviated from `git ls-tree -r --name-only HEAD | tree -d --fromfile` (just the important parts).

#### Instance variable and method return types

In order to provide a consistent, predictable experience for consumers of the SDK, these
established patterns should be followed:

- Avoid using primitives as instance variables and return types for `public` methods on
  resources, since they delegate and (de)serialize to
  [default values](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html) if
  they are `null`.
- `Optional<T>` can be leveraged to provide callers with hints about potential nullability.
- `Optional` variants for boxed primitives (e.g. `OptionalLong`, `OptionalInt`, `OptionalDouble`,
  etc.) have been avoided since their propensity for throwing `NoSuchElementException` makes them
  more difficult to use in Optional chains.

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
[vscode-configuration]: https://github.com/withabound/abound-java/tree/develop/src
