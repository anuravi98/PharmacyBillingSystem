package com.anuravi98.pharmacybillingsystem.integration.user;

import com.anuravi98.pharmacybillingsystem.exception.IncorrectPasswordException;
import com.anuravi98.pharmacybillingsystem.exception.UserExistsException;
import com.anuravi98.pharmacybillingsystem.exception.UserNotFoundException;

import java.sql.*;

public class User {
    private String userName;
    private String name;
    private String password;
    private boolean admin;

    /**
     *
     * @param userName username of user
     * @param name name of user
     * @param password password
     * @param admin if adminor not
     */
 public User(String userName, String name, String password, boolean admin) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.admin = admin;
    }

    /**
     *
     * @param userName username of user
     * @param name name of user
     * @param password password
     */
    public User(String userName, String name, String password) {
        this.userName = userName;
        this.password = password;
        this.name = name;
    }

    /**
     *
     * @param userName username of user
     * @param password password
     */
   public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     *
     * @return username
     */
   public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName username of user
     */
   public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return name
     */
   public String getName() {
        return name;
    }

    /**
     *
     * @param name name
     */
   public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return password
     */
   public String getPassword() {
        return password;
    }

    /**
     *
     * @param password password
     */

   public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return admin status
     */
   public boolean getAdmin() {
        return admin;
    }

    /**
     *
     * @param admin if admin or not
     */

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     *
     * @param admin if admin or not
     */
    public void setAdmin(String admin) {
        this.admin = admin.equalsIgnoreCase("Yes");
    }

    /**
     *
     * @return yesor no
     */
    public static boolean createTable() {
        Connection conn = dbconnection.DBconnect.getConnection("Users");
        try {
            assert conn != null;
            Statement s = conn.createStatement();
            s.executeUpdate(
                    "create table users (username varchar(20) primary key, name varchar(50), password varchar(30), admin boolean)");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Function to login users
     *
     * @throws UserNotFoundException      User was not found in the database
     * @throws IncorrectPasswordException Incorrect password
     */
    public void login() throws UserNotFoundException, IncorrectPasswordException {
        try {
            Connection conn = dbconnection.DBconnect.getConnection("Users");
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select * from users where username = ? ");
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new UserNotFoundException(userName);
            }
            ps = conn.prepareStatement("select * from users where username = ? and password = ?");
            ps.setString(1, userName);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new IncorrectPasswordException(userName);
            }
            setName(rs.getString("name"));
            setAdmin(rs.getBoolean("admin"));
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(userName);
        } catch (IncorrectPasswordException e) {
            throw new IncorrectPasswordException(userName);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    /**
     *
     * @return inserted or not
     * @throws UserExistsException
     * ption whether the user exists
     */
    public boolean insertUser() throws UserExistsException {
        try {
            Connection conn = dbconnection.DBconnect.getConnection("Users");
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select * from users where username = ? ");
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                ps = conn.prepareStatement("insert into users values(?,?,?,?)");
                ps.setString(1, userName);
                ps.setString(2, name);
                ps.setString(3, password);
                ps.setBoolean(4, admin);
                ps.executeUpdate();
                return true;
            } else {
                throw new UserExistsException(userName);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (UserExistsException ue) {
            throw new UserExistsException(userName);
        }
        return false;
    }

    /**
     *
     * @return deleted or not
     * @throws UserNotFoundException when username is not found
     */
    public boolean deleteUser() throws UserNotFoundException {
        try {
            Connection conn = dbconnection.DBconnect.getConnection("Users");
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select * from users where username = ? ");
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ps = conn.prepareStatement("delete from users where username = ?");
                ps.setString(1, userName);
                ps.executeUpdate();
                return true;
            } else {
                throw new UserNotFoundException(userName);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (UserNotFoundException ue) {
            throw new UserNotFoundException(userName);
        }
        return false;
    }

    /**
     *
     * @param newPassword newupdated password
     * @return yes or no
     */

    public boolean updatePassword(String newPassword) {
        try {
            Connection conn = dbconnection.DBconnect.getConnection("Users");
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select * from users where username = ? ");
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ps = conn.prepareStatement("update users set password = ? where username = ?");
                ps.setString(1, newPassword);
                ps.setString(2, userName);
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @return string array
     */
    public String[] toStringArray() {
        String[] toreturn = { userName, name, password, Boolean.toString(admin) };
        return toreturn;
    }
}