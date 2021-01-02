package il.ac.hit.costManager.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleGUI {

    private JFrame frame;
    private JPanel panelOne, panelTwo;
    private JButton btPlus;
    private JTextField tf1,tf2,tf3;
    private JButton btWest, btCenter, btEast;

    public SimpleGUI() {
        frame = new JFrame("SimpleGUI Demo");
        panelOne = new JPanel();
        panelTwo = new JPanel();
        btPlus = new JButton("+");
        tf1 = new JTextField(10);
        tf2 = new JTextField(10);
        tf3 = new JTextField(10);
        btWest = new JButton("west");
        btCenter = new JButton("center");
        btEast = new JButton("east");
    }

    public void start() {
        //setting the panels' background
        panelOne.setBackground(Color.GREEN);
        panelTwo.setBackground(Color.YELLOW);
        //frame.setLayout(new FlowLayout());
        //frame.setLayout(new GridLayout(2,1));

        //setting the frame layout manager
        frame.setLayout(new BorderLayout());
        //frame.setLayout(new FlowLayout());

        //setting layout managers for the two panels
        panelOne.setLayout(new GridLayout(1,3));
        panelTwo.setLayout(new FlowLayout());

        //adding components to panel one
        panelOne.add(tf1);
        panelOne.add(btPlus);
        panelOne.add(tf2);

        //adding components to panel two
        panelTwo.add(tf3);

        //adding the components to the jframe
        frame.add(panelOne,BorderLayout.NORTH);
        frame.add(panelTwo,BorderLayout.SOUTH);
        frame.add(btWest, BorderLayout.WEST);
        frame.add(btCenter, BorderLayout.CENTER);
        frame.add(btEast, BorderLayout.EAST);
        //frame.add(panelOne);
        //frame.add(panelTwo);

        //adding an event listener to the button (btPlus)
        btPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double num1 = Double.parseDouble(tf1.getText());
                double num2 = Double.parseDouble(tf2.getText());
                double result = num1 + num2;
                tf3.setText(String.valueOf(result));
            }
        });

        //handling the jframe closing
        frame.addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             *
             * @param e
             */
            @Override
            public void windowClosing(WindowEvent e) {
                //super.windowClosing(e);
                frame.setVisible(false);
                frame.dispose();
                System.exit(0);
            }
        });

        //setting the jframe size
        frame.setSize(800,400);

        //turning on the jframe visibility
        frame.setVisible(true);
    }


}
