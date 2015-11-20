package de.incub8.presto.uuid;

import org.testng.TestException;
import org.testng.annotations.Test;

public class TestUuidGenerator
{
    @Test
    public void type1()
    {
        String uuid = UuidFunctions.uuid_type1();
        if (uuid == null)
        {
            throw new TestException("must not be null");
        }
        if (uuid.length() == 0)
        {
            throw new TestException("must not be empty");
        }
        if (!uuid.matches("[A-Fa-f0-9]{8}-[A-Fa-f0-9]{4}-1[A-Fa-f0-9]{3}-[89aAbB][A-Fa-f0-9]{3}-[A-Fa-f0-9]{12}"))
        {
            throw new TestException("not a type 1 uuid");
        }
    }

    @Test
    public void type3()
    {
        String uuid = UuidFunctions.uuid_type3("foo", "bar");
        if (uuid == null)
        {
            throw new TestException("must not be null");
        }
        if (uuid.length() == 0)
        {
            throw new TestException("must not be empty");
        }
        if (!uuid.matches("[A-Fa-f0-9]{8}-[A-Fa-f0-9]{4}-3[A-Fa-f0-9]{3}-[89aAbB][A-Fa-f0-9]{3}-[A-Fa-f0-9]{12}"))
        {
            throw new TestException("not a type 3 uuid");
        }
    }

    @Test
    public void type4()
    {
        String uuid = UuidFunctions.uuid_type4();
        if (uuid == null)
        {
            throw new TestException("must not be null");
        }
        if (uuid.length() == 0)
        {
            throw new TestException("must not be empty");
        }
        if (!uuid.matches("[A-Fa-f0-9]{8}-[A-Fa-f0-9]{4}-4[A-Fa-f0-9]{3}-[89aAbB][A-Fa-f0-9]{3}-[A-Fa-f0-9]{12}"))
        {
            throw new TestException("not a type 4 uuid");
        }
    }

    @Test
    public void type5()
    {
        String uuid = UuidFunctions.uuid_type5("foo", "bar");
        if (uuid == null)
        {
            throw new TestException("must not be null");
        }
        if (uuid.length() == 0)
        {
            throw new TestException("must not be empty");
        }
        if (!uuid.matches("[A-Fa-f0-9]{8}-[A-Fa-f0-9]{4}-5[A-Fa-f0-9]{3}-[89aAbB][A-Fa-f0-9]{3}-[A-Fa-f0-9]{12}"))
        {
            throw new TestException("not a type 5 uuid");
        }
    }
}
