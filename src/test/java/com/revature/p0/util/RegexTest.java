package com.revature.p0.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegexTest {



    @Before
    public void SetUp(){}

    @After
    public void tearDown(){}

    @Test
    public void test_getPattern(){
        String expected = "^([a-zA-z])+([\\w@]{2,})+$";

        String actual = Regex.USERNAME_PATTERN.getPattern();

        Assert.assertSame(expected, actual);
    }

}
