package il.ac.hit.costManager.view;


import javax.swing.*;

public class CostManagerApp {

    public static void main(String args[]) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    //Open the application window
                    CostManagerMenu frame = new CostManagerMenu();
                    frame.ManagerMenu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        SwingUtilities.invokeLater(runnable);

    }
}