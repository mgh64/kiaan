/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myControls;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.AbstractBorder;

/**
 *
 * @author mostafa
 */
public class myMainIcon extends JLabel{
    
    private myIconType myiconType = myIconType.none;       

    public myMainIcon(myIconType iconType) {
        myiconType = iconType;
        setOpaque(true);
        addMouseListener(ma);
        //
        
       
        if (myiconType == myIconType.none){
            
        } else if (myiconType == myIconType.person){
            setText("لیست طرف حساب ها");
            setIcon(new ImageIcon("myImage/person.png"));             
        } else if (myiconType == myIconType.shareHolder) {
            setText("لیست سهامداران");
            setIcon(new ImageIcon("myImage/shareHolder.png")); 
        } else if (myiconType == myIconType.product){
            setText("فهرست کالاها");
            setIcon(new ImageIcon("myImage/product.png")); 
        } else if (myiconType == myIconType.bankAccount) {
            setText("لیست حساب های بانکی");
            setIcon(new ImageIcon("myImage/bankAccount.png")); 
        } else if (myiconType == myIconType.g_Cheque) {
            setText("دسته چک");
            setIcon(new ImageIcon("myImage/g_Cheque.png")); 
        } else if (myiconType == myIconType.cash) {
            setText("موجودی صندوق");
            setIcon(new ImageIcon("myImage/cash.png")); 
        } else if (myiconType == myIconType.service) {
            setText("لیست خدمات");
            setIcon(new ImageIcon("myImage/service.png")); 
        } else if (myiconType == myIconType.cost) {
            setText("لیست هزینه ها");
            setIcon(new ImageIcon("myImage/cost.png")); 
        } else if (myiconType == myIconType.income) {
            setText("لیست درامدهای متفرقه");
            setIcon(new ImageIcon("myImage/income.png")); 
        } else if (myiconType == myIconType.production) {
            setText("لیست فرمول های تولید");
            setIcon(new ImageIcon("myImage/formula.png")); 
        }
        //
//        setBackground(Color.white);
        setHorizontalAlignment(RIGHT);
        setVerticalAlignment(TOP);
        setHorizontalTextPosition(JLabel.CENTER);
        setVerticalTextPosition(JLabel.BOTTOM);
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    }
    
    class TextBubbleBorder extends AbstractBorder {

    private Color color;
    private int thickness = 4;
    private int radii = 8;
    private int pointerSize = 7;
    private Insets insets = null;
    private BasicStroke stroke = null;
    private int strokePad;
    private int pointerPad = 4;
    private boolean left = true;
    RenderingHints hints;

    TextBubbleBorder(Color color) {
        new TextBubbleBorder(color, 4, 8, 7);
    }

    TextBubbleBorder(
            Color color, int thickness, int radii, int pointerSize) {
        this.thickness = thickness;
        this.radii = radii;
        this.pointerSize = pointerSize;
        this.color = color;

        stroke = new BasicStroke(thickness);
        strokePad = thickness / 2;

        hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int pad = radii + strokePad;
        int bottomPad = pad + pointerSize + strokePad;
        insets = new Insets(pad, pad, bottomPad, pad);
    }

    TextBubbleBorder(
            Color color, int thickness, int radii, int pointerSize, boolean left) {
        this(color, thickness, radii, pointerSize);
        this.left = left;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return insets;
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        return getBorderInsets(c);
    }

    @Override
    public void paintBorder(
            Component c,
            Graphics g,
            int x, int y,
            int width, int height) {

        Graphics2D g2 = (Graphics2D) g;

        int bottomLineY = height - thickness - pointerSize;

        RoundRectangle2D.Double bubble = new RoundRectangle2D.Double(
                0 + strokePad,
                0 + strokePad,
                width - thickness,
                bottomLineY,
                radii,
                radii);

        Polygon pointer = new Polygon();

        if (left) {
            // left point
            pointer.addPoint(
                    strokePad + radii + pointerPad,
                    bottomLineY);
            // right point
            pointer.addPoint(
                    strokePad + radii + pointerPad + pointerSize,
                    bottomLineY);
            // bottom point
            pointer.addPoint(
                    strokePad + radii + pointerPad + (pointerSize / 2),
                    height - strokePad);
        } else {
            // left point
            pointer.addPoint(
                    width - (strokePad + radii + pointerPad),
                    bottomLineY);
            // right point
            pointer.addPoint(
                    width - (strokePad + radii + pointerPad + pointerSize),
                    bottomLineY);
            // bottom point
            pointer.addPoint(
                    width - (strokePad + radii + pointerPad + (pointerSize / 2)),
                    height - strokePad);
        }

        Area area = new Area(bubble);
        area.add(new Area(pointer));

        g2.setRenderingHints(hints);

        // Paint the BG color of the parent, everywhere outside the clip
        // of the text bubble.
        Component parent  = c.getParent();
        if (parent!=null) {
            Color bg = parent.getBackground();
            Rectangle rect = new Rectangle(0,0,width, height);
            Area borderRegion = new Area(rect);
            borderRegion.subtract(area);
            g2.setClip(borderRegion);
            g2.setColor(bg);
            g2.fillRect(0, 0, width, height);
            g2.setClip(null);
        }

        g2.setColor(color);
        g2.setStroke(stroke);
        g2.draw(area);
    }
}
    
    MouseAdapter ma = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            if (myiconType != myIconType.none){
                setOpaque(true);
                setBackground(Color.red);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }            
        }

        public void mouseExited(MouseEvent e) {
            if (myiconType != myIconType.none){
                setOpaque(false);
            setBackground(Color.white);
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }            
        }
    };
    
    public enum myIconType {
        none,
        person, 
        shareHolder,
        product,
        bankAccount,
        g_Cheque,
        cash,
        service,
        cost,
        income,
        production
    }
}
