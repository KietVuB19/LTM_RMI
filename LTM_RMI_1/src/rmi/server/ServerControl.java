/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import rmi_tcp.rmiServer.RMILoginInterface;
import rmi_tcp.tcpClient.User;

public class ServerControl {

    private ServerView view;
    private ServerSocket myServer;
    private Socket clientSocket;
    private String serverRMIHost = "localhost";
    private int serverRMIPort = 3535;
    private int serverTCPPort = 8000;
    private RMILoginInterface rmiServer;
    private Registry registry;
    private String rmiService = "rmitcpLoginServer";

    public ServerControl(ServerView view) {
        this.view = view;
        openServer(serverTCPPort);
        bindingRMI();
        view.showMessage("TCP server is running...");
        while (true) {
            listenning();
        }
    }

    private void openServer(int portNumber) {
        try {
            myServer = new ServerSocket(portNumber);
        } catch (IOException e) {
            view.showMessage(e.toString());
            e.printStackTrace();
        }
    }

    private void bindingRMI() {
        try {
// lay the dang ki
            registry
                    = LocateRegistry.getRegistry(serverRMIHost,
                            serverRMIPort);
// tim kiem RMI server
            rmiServer = (RMILoginInterface) (registry.lookup(rmiService));
        } catch (RemoteException e) {
            view.showMessage(e.getStackTrace().toString());
        } catch (NotBoundException e) {
            view.showMessage(e.getStackTrace().toString());
        }
    }

    private void listenning() {
        try {
            clientSocket = myServer.accept();
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            Object o = ois.readObject();
            if (o instanceof User) {
                User user = (User) o;
                String result = rmiServer.checkLogin(user);
                ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
                oos.writeObject(result);
            }
        } catch (Exception e) {
            view.showMessage(e.toString());
        }
    }
}
