/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utile;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 *
 * @author mostafa
 */
public class mySetting {
    
    
    
    public static Font myFont(){
      return new Font("B Yekan", Font.BOLD, 14);  
    };
    
    public static class gradient_JPanel extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            if (!isOpaque()) {
                super.paintComponent(g);
                return;
            }

            Graphics2D g2d = (Graphics2D) g;
            int w = getWidth();
            int h = getHeight();
            GradientPaint gp = new GradientPaint(0, 0, Color.white, 0, h, Color.GRAY);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, w, h);

            setOpaque(false);
            super.paintComponent(g);
            setOpaque(true);

        }
    }
    
    public static int getScreenWidthSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return screenSize.width;
    }

    public static int getScreenHeightSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return screenSize.height;
    }
    
    
}
