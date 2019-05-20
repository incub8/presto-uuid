package de.incub8.presto.uuid;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.airlift.slice.Slices;

public class TestUuidGenerator {
	@Test
	public void type1() {
		String uuid = UuidFunctions.uuid_type1().toStringUtf8();
		Assert.assertNotNull(uuid);
		Assert.assertNotEquals(uuid.length(), 0);
		Assert.assertEquals(UUID.fromString(uuid).version(), 1);
	}

	@Test
	public void type3() {
		String uuid = UuidFunctions.uuid_type3(UuidFunctions.uuid_type1(), Slices.utf8Slice("foo")).toStringUtf8();
		Assert.assertNotNull(uuid);
		Assert.assertNotEquals(uuid.length(), 0);
		Assert.assertEquals(UUID.fromString(uuid).version(), 3);
	}

	@Test
	public void type4() {
		String uuid = UuidFunctions.uuid_type4().toStringUtf8();
		Assert.assertNotNull(uuid);
		Assert.assertNotEquals(uuid.length(), 0);
		Assert.assertEquals(UUID.fromString(uuid).version(), 4);
	}

	@Test
	public void type5() {
		String uuid = UuidFunctions.uuid_type5(UuidFunctions.uuid_type1(), Slices.utf8Slice("bar")).toStringUtf8();
		Assert.assertNotNull(uuid);
		Assert.assertNotEquals(uuid.length(), 0);
		Assert.assertEquals(UUID.fromString(uuid).version(), 5);
	}
}
