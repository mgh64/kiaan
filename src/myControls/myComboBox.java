/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myControls;

import java.awt.ComponentOrientation;
import javax.swing.JComboBox;

/**
 *
 * @author mostafa
 */
public class myComboBox extends JComboBox{
    public myComboBox(){
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    }
}
