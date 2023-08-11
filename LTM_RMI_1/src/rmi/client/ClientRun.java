/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi.client;

public class ClientRun {
    public enum SceneName {
        LOGIN,
        REGISTER,
    }
    // scenes
    public static ClientView loginView;
    public static Register registerView;

    public static ClientControl control;

    // controller 
    public ClientRun() {
        control = new ClientControl(loginView);
        initScene();
        openScene(SceneName.LOGIN);
    }

    public void initScene() {
        loginView = new ClientView();
        registerView = new Register();
    }
    

    public static void openScene(SceneName sceneName) {
        if (null != sceneName) {
            switch (sceneName) {
                case LOGIN:
                    loginView = new ClientView();
                    loginView.setVisible(true);
                    break;
                case REGISTER:
                    registerView = new Register();
                    registerView.setVisible(true);
                    break;
                default:
                    break;
            }
        }
    }

    public static void closeScene(SceneName sceneName) {
        if (null != sceneName) {
            switch (sceneName) {
                case LOGIN:
                    loginView.dispose();
                    break;
                case REGISTER:
                    registerView.dispose();
                    break;
                default:
                    break;
            }
        }
    }

    public static void closeAllScene() {
        loginView.dispose();
        registerView.dispose();
    }

    public static void main(String[] args) {
        new ClientRun();
    }
    
//    ClientView view = new ClientView();
//        Register regis = new Register();
//        ClientControl control = new ClientControl(view);
//        view.setVisible(true);
}
