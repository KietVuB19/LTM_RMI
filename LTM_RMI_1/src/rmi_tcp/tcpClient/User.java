/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi_tcp.tcpClient;

/**
 *
 * @author Admin
 */
import java.io.Serializable;

public class User implements Serializable {

    private String userName;
    private String password;
    private String Address;
    private String Birthday;
    private String Sex;
    private String Description;
    

    public User() {
    }

    public User(String userName, String password, String Address, String Birthday, String Sex, String Description) {
        this.userName = userName;
        this.password = password;
        this.Address = Address;
        this.Birthday = Birthday;
        this.Sex = Sex;
        this.Description = Description;
    }

    
    public User(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String Birthday) {
        this.Birthday = Birthday;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    
}
