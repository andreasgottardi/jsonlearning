package gsondes;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 *
 * @author goa
 */
public class GsonFactory {

	private GsonFactory() {

	}

	public static Gson getGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(MyCustomClass.class, new MyDeserializer());
		gsonBuilder.registerTypeAdapter(MyCustomClass.class, new MySerializer());
		return gsonBuilder.create();
	}

	private static class MySerializer implements JsonSerializer<MyCustomClass> {

		@Override
		public JsonElement serialize(MyCustomClass src, Type typeOfSrc, JsonSerializationContext context) {
			return new JsonPrimitive(String.format("%s;%s;%s", src.getMem1(), src.getMem2(), src.getMem3()));
		}
	}

	private static class MyDeserializer implements JsonDeserializer<MyCustomClass> {

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
}
