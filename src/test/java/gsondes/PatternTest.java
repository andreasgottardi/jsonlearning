package gsondes;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

class PatternTest {

	private static final Logger logger = LoggerFactory.getLogger(PatternTest.class);

	@Test
	void test1() {
		String[] dates = { "24.12.2020 14:25:22", "2020-06-11T16:40:47" };

		Pattern[] patterns = { Pattern.compile("[\\d]{2,2}\\.[\\d]{2,2}\\.[\\d]{4,4} [\\d]{2,2}:[\\d]{2,2}:[\\d]{2,2}"),
				Pattern.compile("[\\d]{4,4}-[\\d]{2,2}-[\\d]{2,2}T[\\d]{2,2}:[\\d]{2,2}:[\\d]{2,2}") };

		for (int i = 0; i < dates.length; i++) {
			Pattern p = patterns[i];
			String d = dates[i];
			assertTrue(p.matcher(d).matches());
		}
	}

	@Test
	void test2() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		DateHolder dh = new DateHolder();
		dh.setGc(new GregorianCalendar());
		Gson gson = GsonFactory.getGson();
		String json = gson.toJson(dh);
		logger.debug("Object serialized: {}", json);
		assertNotNull(json);
		DateHolder target = gson.fromJson(json, DateHolder.class);
		logger.debug("Date1: {}", sdf.format(dh.getGc().getTime()));
		logger.debug("Date2: {}", sdf.format(target.getGc().getTime()));
		assertEquals(dh.getGc(), target.getGc());
	}
}
