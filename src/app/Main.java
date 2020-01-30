package app;

import app.core.SocketStreamClient;
import app.core.SocketStreamServer;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        SocketStreamServer server = new SocketStreamServer("Server");
        SocketStreamClient[] clients = new SocketStreamClient[3];
        for (int i = 0; i < clients.length; i++) {
            clients[i] = new SocketStreamClient("Calculator (client " + (i + 1) + ")");
            clients[i].start();
            clients[i].join();
        }
        server.start();
        server.join();
    }
}
