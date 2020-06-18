package gsondes;

import java.util.GregorianCalendar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import gsondes.deserializers.GregorianCalendarDeserializer;
import gsondes.deserializers.MyCustomDeserializer;
import gsondes.serializers.GregorianCalendarSerializer;
import gsondes.serializers.MyCustomSerializer;

/**
 *
 * @author goa
 */
public class GsonFactory {

	private static Gson mthis = null;

	private GsonFactory() {

	}

	public static Gson getGson() {
		if (mthis == null) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(MyCustomClass.class, new MyCustomDeserializer());
			gsonBuilder.registerTypeAdapter(MyCustomClass.class, new MyCustomSerializer());
			gsonBuilder.registerTypeAdapter(GregorianCalendar.class, new GregorianCalendarDeserializer());
			gsonBuilder.registerTypeAdapter(GregorianCalendar.class, new GregorianCalendarSerializer());
			mthis = gsonBuilder.create();
		}
		return mthis;
	}
}
