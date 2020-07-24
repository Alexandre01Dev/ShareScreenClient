package be.alexandre01.sharescreenmc.connection;

import java.io.IOException;
import java.net.ServerSocket;

public class Socket {
    public static ServerSocket server= null;
    public static int port = 0;
    public Socket(){

    }

    public static void startServer(){
        try {
            ServerSocket server = new ServerSocket(getPort());
            setServer(server);

            System.out.println("La communication à démarré avec succès / PORT :" + getPort());
            Runnable target;
            Thread waitForConnection = new Thread(new WaitForConnection());
            waitForConnection.start();

        } catch (IOException e) {
            System.out.println("La communication n'a pas pu démarré");
        }
    }
    public static void stopServer(){
        try {
            if(getServer() != null){
                getServer().close();
                System.out.println("La communication à bien été stoppé avec succès / PORT :" + getPort());
            }
        } catch (IOException e) {
            System.out.println("La communication n'a pas pu se stopper");
        }
    }
    public static ServerSocket getServer(){
        return server;
    }
    public static void setServer(ServerSocket server){
        Socket.server = server;
    }
    public static int getPort(){
        return port;
    }
    public static void setPort(int port){
        Socket.port = port;
    }
}

