package app.core;

import app.data.Operation;
import static app.data.Connection.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketStreamServer extends Thread {

    public SocketStreamServer(String str) {
        super(str);
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("***** [ " + SERVER_NAME + " ] ******");
                System.out.println("\t-> Creating new server socket...");
                ServerSocket ss = new ServerSocket();
                System.out.println("\t-> Binding...");
                InetSocketAddress isa = new InetSocketAddress(SERVER_HOST, SERVER_PORT);
                ss.bind(isa);
                System.out.println("\t-> Listening on port: " + SERVER_PORT);
                Socket s = ss.accept();
                System.out.println("\t-> Connection accepted from: " + s.getRemoteSocketAddress());
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                Operation o = (Operation) ois.readObject();
                double n1 = o.getFirstNumber();
                double n2 = o.getSecondNumber();
                double r = 0;
                switch (o.getType()) {
                    case '+':
                        r = n1 + n2;
                        break;
                    case '-':
                        r = n1 - n2;
                        break;
                    case '*':
                        r = n1 * n2;
                        break;
                    case '/':
                        r = n1 / n2;
                        break;
                }
                dos.writeDouble(r);
                dos.flush();
                dos.close();
                ois.close();
                System.out.println("\t-> Closing socket...");
                s.close();
                System.out.println("\t-> Closing server socket...");
                ss.close();
                System.out.println("\t-> Done!");
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(SocketStreamServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
