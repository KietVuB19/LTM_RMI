/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi_tcp.rmiServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import rmi_tcp.tcpClient.User;
public interface RMILoginInterface extends Remote{
public String checkLogin(User user) throws RemoteException;
public boolean register(String username, String password, String address, String birthday, String sex, String description) throws RemoteException;
}

