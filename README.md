# JSON learning project

If one wants to wrap complex objects into JSON using own de-/serialization code this is a template and learning example.

## Example
The following JSON string can be deserialized into a Java object
```
{"mcc":"val1;val2;val3"}
```
by using the code:
```
ParentClass deserialized = gson.fromJson(serialized, ParentClass.class);
```
## Import the project
The project is developed with Eclipse and uses the Java development tools and BuildShip integraton. Import it as Gradle project.
## Run the project
You can run the project on the command line with the gradle command. Execute in the project folder.
```
.\gradlew.bat test
```
In an IDE like Eclipse you can test the code by running the test in the file
```
src/test/java/gsondes/GsonFactoryTest.java
```
## Changelog
| Version | Description |
|-|-|
| 0.0.1 | Basic implementation of de-/serializer for custom class added. |