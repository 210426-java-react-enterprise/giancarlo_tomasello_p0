package com.revature.p0.util;

import org.junit.*;

public class LinkedListTest {

    private LinkedList<String> sut;


    @Before
    public void setUp(){
        sut = new LinkedList<>();
        System.out.println("Running before test");
    }

    @After
    public void tearDownTest(){
        sut = null;
        System.out.println("Running after test");
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
        System.out.println("Now running test");
        //Act
        sut.add(null);
        //Assert
        //Sometimes blank especially if exception will be thrown

    }

    @Test
    public void test_fakeTest(){
        System.out.println("test is now running.");
    }
}
