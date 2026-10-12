
package main;

import ui.Login;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
       
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (Exception ex) {
            ex.printStackTrace();
        }

        
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}

    
    
