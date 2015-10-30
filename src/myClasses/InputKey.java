/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myClasses;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;

/**
 *
 * @author mostafa
 */
public class InputKey extends JComponent{
    
    KeyAdapter ka = new KeyAdapter(){		
        @Override
        public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    System.out.println("Escape key pressed");                    
                    System.exit(0);
                }                        
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                        System.out.println("Enter key pressed");
                if (e.getKeyCode() == KeyEvent.VK_CONTROL)
                            System.out.println("Ctrl key pressed");
            }
    };
    
    public KeyAdapter getInputKey(){
         return ka;
     }
}
