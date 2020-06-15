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
