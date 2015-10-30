/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myControls;

import org.kiaan.Main;
/**
 *
 * @author mostafa
 */
import javax.swing.*;

public class myToggleButton extends JToggleButton {    
    
    public myToggleButton(String str) {
        super(str);
    }

    public myToggleButton(String str, Boolean sel) {
        super(str, sel);
    }
}
