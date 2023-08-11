/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi.server;

public class ServerRun {

    public static void main(String[] args) {
        ServerView view = new ServerView();
        ServerControl control = new ServerControl(view);
    }
}
