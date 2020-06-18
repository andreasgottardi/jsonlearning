package gsondes.deserializers;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

/**
 * Deserializes JSON date properties in the formats "dd.MM.yyyy HH:mm:ss.SSS"
 * and "yyyy-MM-dd'T'HH:mm:ss.SSS".
 * 
 * @author andreasgottardi
 *
 */
public class GregorianCalendarDeserializer implements JsonDeserializer<GregorianCalendar> {

	private static final Logger logger = LoggerFactory.getLogger(GregorianCalendarDeserializer.class);

	@Override
	public GregorianCalendar deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) {

		String date = element.getAsString();
		PatternFormat[] pfs = {
				new PatternFormat("[\\d]{2,2}\\.[\\d]{2,2}\\.[\\d]{4,4} [\\d]{2,2}:[\\d]{2,2}:[\\d]{2,2}\\.[\\d]{3,3}",
						"dd.MM.yyyy HH:mm:ss.SSS"),
				new PatternFormat("[\\d]{4,4}-[\\d]{2,2}-[\\d]{2,2}T[\\d]{2,2}:[\\d]{2,2}:[\\d]{2,2}\\.[\\d]{3,3}",
						"yyyy-MM-dd'T'HH:mm:ss.SSS") };
		int i = 0;
		PatternFormat pf = null;
		while (pf == null && i < pfs.length) {
			if (Pattern.compile(pfs[i].getPattern()).matcher(date).matches()) {
				pf = pfs[i];
			}
			i++;
		}
		GregorianCalendar gc = null;
		try {
			if (pf != null) {
				SimpleDateFormat formatter = new SimpleDateFormat(pf.getFormat());
				gc = new GregorianCalendar();
				gc.setTime(formatter.parse(date));
			}
		} catch (ParseException e) {
			logger.error("Error parsing date.", e);
		}
		return gc;
	}

	private class PatternFormat {

		private String pattern;
		private String format;

		public PatternFormat(String pattern, String format) {

			this.pattern = pattern;
			this.format = format;
		}

		public String getPattern() {
			return pattern;
		}

		public String getFormat() {
			return format;
		}
	}
}