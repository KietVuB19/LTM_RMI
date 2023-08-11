/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi.server;

public class RMIServerRun {

    public static void main(String[] args) {
        RMILoginServerView view = new RMILoginServerView();
        try {
            RMILoginServerControl control = new RMILoginServerControl(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
