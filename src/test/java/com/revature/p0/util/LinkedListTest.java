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
        System.out.println("Fake test is now running.");
    }

    @Test
    public void test_PeekNonEmpty(){
        sut.add("test");

        String temp = sut.peek();

        Assert.assertEquals(temp, "test");
    }

    @Test
    public void test_PeekEmpty(){

        String temp = sut.peek();

        Assert.assertNull(temp);
    }

    @Test
    public void test_PollNonEmpty(){
        sut.add("test");
        sut.add("test2");

        String actual = sut.poll();

        Assert.assertSame("test", actual);
        Assert.assertEquals(1, sut.size());
    }

    @Test
    public void test_PollEmpty(){

        String actual = sut.poll();

        Assert.assertNull(actual);
    }

    @Test
    public void test_getNonEmpty(){
        sut.add("test");
        sut.add("test2");

        String actual = sut.get(1);

        Assert.assertSame("test2", actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_getEmpty(){
        sut.get(10);
    }



}
