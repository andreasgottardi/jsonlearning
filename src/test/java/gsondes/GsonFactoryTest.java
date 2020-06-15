package gsondes;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

class GsonFactoryTest {

	private static final Logger logger = LoggerFactory.getLogger(GsonFactoryTest.class);

	@Test
	void test1() {
		Gson gson = GsonFactory.getGson();
		assertNotNull(gson != null);
		ParentClass pc = new ParentClass();
		pc.setMcc(new MyCustomClass("val1", "val2", "val3"));
		String serialized = gson.toJson(pc);
		logger.debug("Serialized JSON: {}", serialized);
		assertNotNull(serialized);
		assertNotEquals("", serialized);
		ParentClass deserialized = gson.fromJson(serialized, ParentClass.class);
		assertEquals("val1", deserialized.getMcc().getMem1());
		assertEquals("val2", deserialized.getMcc().getMem2());
		assertEquals("val3", deserialized.getMcc().getMem3());
	}
}
