package gsondes.serializers;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import gsondes.MyCustomClass;

public class MyCustomSerializer implements JsonSerializer<MyCustomClass> {

	@Override
	public JsonElement serialize(MyCustomClass src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(String.format("%s;%s;%s", src.getMem1(), src.getMem2(), src.getMem3()));
	}
}