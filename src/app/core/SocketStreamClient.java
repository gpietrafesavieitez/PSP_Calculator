package app.core;

import app.ui.Gui;
import app.data.Operation;
import static app.data.Connection.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

public class SocketStreamClient extends Thread {

    public SocketStreamClient(String str) {
        super(str);
    }

    private static double connect(Operation o) {
        double r = 0;
        try {
            Socket s = new Socket();
            InetSocketAddress isa = new InetSocketAddress(SERVER_HOST, SERVER_PORT);
            s.connect(isa);
            DataInputStream dis = new DataInputStream(s.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(o);
            oos.flush();
            r = dis.readDouble();
            oos.close();
            dis.close();
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(SocketStreamClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return r;
        }
    }

    @Override
    public void run() {
        Operation op = new Operation();
        Gui gui = new Gui();
        gui.setTitle(getName());
        gui.init();
        gui.repaint();
        for (JButton btn : gui.getListButtons()) {
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (btn.getText()) {
                        case "=":
                            op.setSecondNumber(Double.parseDouble(gui.getDisplay().getText()));
                            gui.getDisplay().setText(String.valueOf(connect(op)));
                            break;
                        case "+":
                            op.setFirstNumber(Double.parseDouble(gui.getDisplay().getText()));
                            gui.getDisplay().setText("");
                            op.setType('+');
                            break;
                        case "-":
                            op.setFirstNumber(Double.parseDouble(gui.getDisplay().getText()));
                            gui.getDisplay().setText("");
                            op.setType('-');
                            break;
                        case "x":
                            op.setFirstNumber(Double.parseDouble(gui.getDisplay().getText()));
                            gui.getDisplay().setText("");
                            op.setType('*');
                            break;
                        case "%":
                            op.setFirstNumber(Double.parseDouble(gui.getDisplay().getText()));
                            gui.getDisplay().setText("");
                            op.setType('/');
                            break;
                        case ".":
                            gui.getDisplay().setText(gui.getDisplay().getText() + ".");
                            break;
                        case "DEL":
                            gui.getDisplay().setText("");
                            break;
                        case "AC":
                            gui.dispose();
                            break;
                        default:
                            if (gui.getDisplay().getText().length() < 10) {
                                gui.getDisplay().setText(gui.getDisplay().getText() + btn.getText());
                            }
                            break;
                    }
                }
            });
        }
    }
}
