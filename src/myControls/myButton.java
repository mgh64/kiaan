/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myControls;

import javax.swing.JButton;
import myClasses.InputKey;

/**
 *
 * @author mostafa
 */
public class myButton extends JButton{
    public myButton(String Text){
        super(Text);
        InputKey gik = new InputKey();
        addKeyListener(gik.getInputKey());
    }        
}
