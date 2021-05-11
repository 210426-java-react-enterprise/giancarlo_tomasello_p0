package com.revature.p0.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrayListTest {

    private  ArrayList<String> sut;

    @Before
    public void setUp(){
        sut = new ArrayList<>();
    }

    @After
    public void tearDown(){
        sut = null;
    }

    @Test
    public void test_addWithNonNullValue(){
        //Arrange
        int exprectedSize = 1;

        //Act
        sut.add("Data");

        //Asset
        int actualSize = sut.size();
        Assert.assertEquals(exprectedSize, actualSize);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addWithNullValue(){

        //Act
        sut.add(null);

        //Asset
        int actualSize = sut.size();

    }

    @Test
    public void test_addAndResize(){
        int expectedSize = 15;

        for (int i = 0; i < expectedSize; i++) {
            sut.add("Data");
        }

        int actualSize = sut.size();
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void test_getInRange(){

        sut.add("Data");

        String temp = sut.get(0);

        Assert.assertEquals(temp, "Data");
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_getOutOfRange(){

        sut.add("Data");

        String temp = sut.get(5);

    }
}
