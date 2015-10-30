/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myControls;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import Utile.mySetting;
//import com.oracle.jrockit.jfr.ValueDefinition;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

/**
 *
 * @author mostafa
 */
public class myMainMenu extends JLabel {

    MouseAdapter ma = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            setOpaque(true);
            setBackground(Color.gray);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setOpaque(false);
            setBackground(Color.white);
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }

        @Override
        public void mousePressed(MouseEvent e) {
            setBackground(Color.lightGray);
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            setBackground(Color.gray);
        }

        

    };

    public myMainMenu(String text) {
        super(text);
        setFont(mySetting.myFont());
        addMouseListener(ma);
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
        setAlignmentX(Component.RIGHT_ALIGNMENT);
        setPreferredSize(new Dimension(100, 30));
        //        
    }

    /*
     @Override
     public void mouseClicked(MouseEvent e) {
     System.out.println("Mouse Clicked!");
     }

     @Override
     public void mousePressed(MouseEvent e) {
     System.out.println("Mouse Pressed!");
     }

     @Override
     public void mouseReleased(MouseEvent e) {
     System.out.println("Mouse Released!");
     }

     @Override
     public void mouseEntered(MouseEvent e) {
     System.out.println("Mouse Enterd!");
     }

     @Override
     public void mouseExited(MouseEvent e) {
     System.out.println("Mouse Exited!");
     }
     */
}
