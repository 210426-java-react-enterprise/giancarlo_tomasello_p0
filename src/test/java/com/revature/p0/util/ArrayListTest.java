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
    //requires a data t
    @Test (expected = IllegalArgumentException.class)
    public void test_containsWithNull(){

        sut.add("Cat");
        sut.add("Dog");

        sut.contains(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_containsEmptyArraylist(){

        sut.contains("Dog");
    }

    @Test
    public void test_containsNonNull(){

        boolean expected = true;

        sut.add("Cat");
        sut.add("Dog");
        sut.add("Fish");

        boolean result = sut.contains("Dog");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void test_containsNonNullMissingElement(){

        boolean expected = false;

        sut.add("Cat");
        sut.add("Dog");
        sut.add("Fish");

        boolean result = sut.contains("Dragon");

        Assert.assertEquals(expected, result);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_removeNull(){

        sut.add("Cat");

        sut.remove(null);
    }

    @Test
    public void test_removeValue(){

        String expected = "Cat";

        sut.add("Cat");
        sut.add("Dog");
        sut.add("Fish");

        String actual = sut.remove("Cat");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_removeMissingValue(){


        sut.add("Cat");
        sut.add("Dog");
        sut.add("Fish");

        String actual = sut.remove("Dragon");

        Assert.assertNull(actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_RemoveIndexOutOfBounds(){
        sut.add("Cat");

        sut.remove(10);
    }

    @Test
    public void test_removeWithIndex(){
        boolean expected = true;

        sut.add("Cat");
        sut.add("Dog");
        sut.add("Fish");

        boolean actual = sut.remove(0);

        Assert.assertEquals(expected, actual);
    }
    
}
