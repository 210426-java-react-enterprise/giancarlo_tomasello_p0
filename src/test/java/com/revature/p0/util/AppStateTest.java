package com.revature.p0.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppStateTest {

    AppState sut;

    @Before
    public void SetUp(){
        sut = new AppState();
    }

    @After
    public void TearDown(){
        sut = null;
    }

    @Test
    public void test_IsAppRunning(){
        boolean expected = true;
        boolean actual = sut.isAppRunning();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_SetAppRunning(){
        boolean expected = false;

        sut.setAppRunning(false);
        boolean actual = sut.isAppRunning();

        Assert.assertEquals(expected, actual);
    }

}
