package app.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gui extends JFrame {

    private JPanel panel;
    private JTextField display;
    private JButton btn1, btn2;
    private ArrayList<JButton> listButtons;

    public Gui() {
    }

    public JTextField getDisplay() {
        return display;
    }

    public void setDisplay(JTextField display) {
        this.display = display;
    }

    public ArrayList<JButton> getListButtons() {
        return listButtons;
    }
    
    public JButton getBtn2() {
        return btn2;
    }

    public void init() {
        createFrame();
        createPanel();
        createDisplay();
        createButtons();
    }

    private void createFrame() {
        this.setMinimumSize(new Dimension(372, 490));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.pack();
    }

    private void createPanel() {
        panel = new JPanel();
        panel.setSize(372, 490);
        panel.setBackground(Color.BLACK);
        panel.setVisible(true);
        panel.setLayout(null);
        this.add(panel);
    }

    private void createDisplay() {
        display = new JTextField();
        display.setBounds(10, 30, 335, 100);
        Font displayFont = new Font("Courier", Font.BOLD, 60);
        display.setFont(displayFont);
        display.setBackground(Color.WHITE);
        display.setEditable(false);
        panel.add(display);
    }

    private void createButtons() {
        btn1 = new JButton("DEL");
        btn1.setBounds(100, 155, 75, 25);
        btn1.setBackground(Color.CYAN);
        btn2 = new JButton("AC");
        btn2.setBounds(180, 155, 75, 25);
        btn2.setBackground(Color.RED);
        panel.add(btn1);
        panel.add(btn2);
        String[] arSymbols = new String[]{"7", "4", "1", ".", "8", "5", "2", "0", "9", "6", "3", "=", "%", "x", "-", "+"};
        listButtons = new ArrayList<>();
        int i = -1;
        for (int x = 0; x < 4; x++) {
            for (int y = 4; y < 8; y++) {
                i++;
                JButton btn3 = new JButton();
                btn3.setBounds((x * 85) + 10, y * 55, 80, 50);
                Font btnFont = new Font("Courier", Font.BOLD, 30);
                btn3.setFont(btnFont);
                btn3.setBackground(Color.LIGHT_GRAY);
                btn3.setText(arSymbols[i]);
                listButtons.add(btn3);
                panel.add(btn3);
            }
        }
        listButtons.add(btn1);
        listButtons.add(btn2);
    }
}
