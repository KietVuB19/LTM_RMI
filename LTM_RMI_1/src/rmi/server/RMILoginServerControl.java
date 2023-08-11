/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi.server;

import rmi_tcp.rmiServer.RMILoginInterface;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import rmi_tcp.tcpClient.User;

public class RMILoginServerControl extends UnicastRemoteObject
        implements RMILoginInterface {

    private int serverPort = 3535;
    private Registry registry;
    private Connection con;
    private RMILoginServerView view;
    private String rmiService = "rmitcpLoginServer";

    public RMILoginServerControl(RMILoginServerView view) throws
            RemoteException {
        this.view = view;
        view.showMessage("RMI server is running...");
// dang ki RMI server
        try {
            registry = LocateRegistry.createRegistry(serverPort);
            registry.rebind(rmiService, this);
        } catch (RemoteException e) {
            throw e;
        }
    }

    public RMILoginServerControl() throws RemoteException{
    
    }

    public String checkLogin(User user) throws RemoteException {
        String result = "";
        getDBConnection("ltm_login_re", "kiet123", "kietvb21");
        if (checkUser(user)) {
            result = "ok";
        }
        return result;
    }

    private void getDBConnection(String dbName, String username,
            String password) {
        String dbUrl = "jdbc:mysql://localhost:3306/" + dbName;
        String dbClass = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(dbClass);
            con = DriverManager.getConnection(dbUrl, username, password);
        } catch (Exception e) {
            view.showMessage(e.getStackTrace().toString());
        }
    }

    private boolean checkUser(User user) {
        String query = "Select * FROM users WHERE username ='"
                + user.getUserName()
                + "' AND password ='" + user.getPassword() + "'";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            view.showMessage(e.getStackTrace().toString());
        }
        return false;
    }

    public boolean register(String username, String password, String address, String birthday, String sex, String description) throws RemoteException {
        try {
            getDBConnection("ltm_login_re", "kiet123", "kietvb21");
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM users WHERE username = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return false; // username already exists
            } else {
                stmt = con.prepareStatement("INSERT INTO users (username, password, address, birthday, sex, description) VALUES (?, ?, ?, ?, ?, ?)");
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.setString(3, address);
                stmt.setString(4, birthday);
                stmt.setString(5, sex);
                stmt.setString(6, description);
                
                stmt.executeUpdate();
                return true; // registration successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
