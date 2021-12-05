# Abound Java SDK

The Abound Java SDK provides convenient access to the Abound API from applications written in Java.

### Documentation
### Requirements
### Installation
### Usage

### Development

It is recommended to configure your IDE with the `google-java-format` plugin: https://github.com/google/google-java-format#intellij-android-studio-and-other-jetbrains-ides

Run all tests:

```sh
$ ./gradlew test
```

Run `spotless` and apply auto-fixes:

```sh
$ ./gradlew spotlessApply
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