package com.revature.p0.services;

import com.revature.p0.daos.UserDAO;
import com.revature.p0.exceptions.ResourcePersistenceException;
import com.revature.p0.models.AppUser;
import com.revature.p0.util.ConnectionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService sut;
    private UserDAO mockUserDao;
    private Connection mockConn;
    private ConnectionFactory mockConnectionFactory;
    private AppUser mockAppUser;

    @Before
    public void SetUp(){
        mockUserDao = mock(UserDAO.class);
        mockConn = mock(Connection.class);
        mockConnectionFactory = mock(ConnectionFactory.class);
        mockAppUser = mock(AppUser.class);
        sut = new UserService(mockUserDao);
    }

    @After
    public void TearDown(){
        sut = null;
        mockUserDao = null;
        mockConn = null;
        mockConnectionFactory = null;
    }

    @Test
    public void test_Register_ValidUsername_ValidPassword() throws SQLException {
        //Arrange
        when(mockUserDao.isUsernameAvailable(any(), anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailable(any(), anyString())).thenReturn(true);
        //Act
        sut.register(new AppUser(0, "username", "password", "email", "fn", "ln", 11.25, 1));
        //Assert
       // verify(mockConnectionFactory, times(1)).getConnection();
        verify(mockUserDao, times(1)).isUsernameAvailable(any(),anyString());
        verify(mockUserDao, times(1)).isEmailAvailable(any(), anyString());
        verify(mockUserDao, times(1)).save(any(), any());
    }

    @Test
    public void test_Register_InvalidUsername_ValidPassword() throws SQLException {
        //Arrange
        when(mockUserDao.isUsernameAvailable(any(), anyString())).thenReturn(false);
        when(mockUserDao.isEmailAvailable(any(), anyString())).thenReturn(true);
        //Act
        try{
            sut.register(new AppUser(0, "username", "password", "email", "fn", "ln", 11.25, 1));
        } catch (Exception e){
            Assert.assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            verify(mockUserDao, times(1)).isUsernameAvailable(any(),anyString());
            verify(mockUserDao, times(0)).isEmailAvailable(any(), anyString());
            verify(mockUserDao, times(0)).save(any(), any());
        }


    }

    @Test
    public void test_Register_ValidUsername_InvalidPassword() throws SQLException {
        //Arrange
        when(mockUserDao.isUsernameAvailable(any(), anyString())).thenReturn(true);
        when(mockUserDao.isEmailAvailable(any(), anyString())).thenReturn(false);
        //Act
        try{
            sut.register(new AppUser(0, "username", "password", "email", "fn", "ln", 11.25, 1));
        } catch (Exception e){
            Assert.assertTrue(e instanceof ResourcePersistenceException);
        } finally {
            verify(mockUserDao, times(1)).isUsernameAvailable(any(),anyString());
            verify(mockUserDao, times(1)).isEmailAvailable(any(), anyString());
            verify(mockUserDao, times(0)).save(any(), any());

        }


    }

    @Test
    public void test_foundUserInDatabase(){

     when(mockUserDao.findUserByUsernameAndPassword(any(), anyString(), anyString())).thenReturn(any());


     sut.findUserInDatabase(anyString(), anyString());

     verify(mockUserDao, times(1)).findUserByUsernameAndPassword(any(), anyString(), anyString());


    }
}
