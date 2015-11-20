package de.incub8.presto.uuid;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Nullable;

import com.facebook.presto.operator.Description;
import com.facebook.presto.operator.scalar.ScalarFunction;
import com.facebook.presto.spi.type.StandardTypes;
import com.facebook.presto.type.SqlType;
import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;

public class UuidFunctions
{
    private UuidFunctions()
    {
    }

    @Description("Returns a type 1 UUID")
    @ScalarFunction("uuid_type1")
    @SqlType(StandardTypes.VARCHAR)
    public static String uuid_type1()
    {
        return Generators.timeBasedGenerator(EthernetAddress.fromInterface()).generate().toString();
    }

    @Description("Returns a type 3 UUID")
    @ScalarFunction("uuid_type3")
    @SqlType(StandardTypes.VARCHAR)
    public static String uuid_type3(@Nullable @SqlType(StandardTypes.VARCHAR) String namespace,
        @Nullable @SqlType(StandardTypes.VARCHAR) String name)
    {
        return hashBasedUuid(namespace, namespace, "MD5");
    }

    @Description("Returns a type 4 UUID")
    @ScalarFunction("uuid_type4")
    @SqlType(StandardTypes.VARCHAR)
    public static String uuid_type4()
    {
        return Generators.randomBasedGenerator().generate().toString();
    }

    @Description("Returns a type 5 UUID")
    @ScalarFunction("uuid_type5")
    @SqlType(StandardTypes.VARCHAR)
    public static String uuid_type5(@Nullable @SqlType(StandardTypes.VARCHAR) String namespace,
        @Nullable @SqlType(StandardTypes.VARCHAR) String name)
    {
        return hashBasedUuid(namespace, namespace, "SHA-1");
    }

    private static String hashBasedUuid(String namespace, String name, String algorithm)
    {
        try
        {
            return Generators.nameBasedGenerator(null, MessageDigest.getInstance(algorithm)).generate(name).toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }
}
