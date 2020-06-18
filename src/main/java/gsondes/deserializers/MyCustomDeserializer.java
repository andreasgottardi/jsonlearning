package gsondes.deserializers;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import gsondes.MyCustomClass;

public class MyCustomDeserializer implements JsonDeserializer<MyCustomClass> {

	@Override
	public MyCustomClass deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) {
		String elementdata = element.getAsString();
		String[] e = elementdata.split(";");
		if (e.length != 3) {
			return null;
		} else {
			return new MyCustomClass(e[0], e[1], e[2]);
		}
	}
}