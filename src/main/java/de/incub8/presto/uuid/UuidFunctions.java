package de.incub8.presto.uuid;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import com.facebook.presto.spi.function.Description;
import com.facebook.presto.spi.function.ScalarFunction;
import com.facebook.presto.spi.function.SqlNullable;
import com.facebook.presto.spi.function.SqlType;
import com.facebook.presto.spi.type.StandardTypes;
import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;

import io.airlift.slice.Slice;
import io.airlift.slice.Slices;

public class UuidFunctions {
	private UuidFunctions() {
	}

	@Description("Returns a type 1 UUID")
	@ScalarFunction("uuid_type1")
	@SqlType(StandardTypes.VARCHAR)
	public static Slice uuid_type1() {
		return Slices.utf8Slice(Generators.timeBasedGenerator(EthernetAddress.fromInterface()).generate().toString());
	}

	@Description("Returns a type 3 UUID")
	@ScalarFunction("uuid_type3")
	@SqlType(StandardTypes.VARCHAR)
	public static Slice uuid_type3(@SqlNullable @SqlType(StandardTypes.VARCHAR) Slice namespace,
			@SqlNullable @SqlType(StandardTypes.VARCHAR) Slice name) {
		return hashBasedUuid(namespace, namespace, "MD5");
	}

	@Description("Returns a type 4 UUID")
	@ScalarFunction("uuid_type4")
	@SqlType(StandardTypes.VARCHAR)
	public static Slice uuid_type4() {
		return Slices.utf8Slice(Generators.randomBasedGenerator().generate().toString());
	}

	@Description("Returns a type 5 UUID")
	@ScalarFunction("uuid_type5")
	@SqlType(StandardTypes.VARCHAR)
	public static Slice uuid_type5(@SqlNullable @SqlType(StandardTypes.VARCHAR) Slice namespace,
			@SqlNullable @SqlType(StandardTypes.VARCHAR) Slice name) {
		return hashBasedUuid(namespace, namespace, "SHA-1");
	}

	private static Slice hashBasedUuid(Slice namespace, Slice name, String algorithm) {
		try {
			return Slices.utf8Slice(Generators
					.nameBasedGenerator(UUID.fromString(namespace.toStringUtf8()), MessageDigest.getInstance(algorithm))
					.generate(name.toStringUtf8()).toString());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
