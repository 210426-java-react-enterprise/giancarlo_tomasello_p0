package com.revature.p0.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

    private LinkedList<String> sut;

    @Before
    public void SetUpTest(){
        sut = new LinkedList<>();
    }

    @After
    public void tearDownTest(){
        sut = null;
    }

    @Test
    public void test_addWithNonNullValue(){
        //Arrange (Prepare the test)
        int expectedSize = 1;
        //Act (Do the test)
        sut.add("data");

        //Assert (Insure the results)
        int actualSize = sut.size();
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addWithNullValue(){
        //Arrange

        //Act
        sut.add(null);
        //Assert
        //Sometimes blank especially if exception will be thrown
    }


}
