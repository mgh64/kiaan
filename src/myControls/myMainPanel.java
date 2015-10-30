/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myControls;

import java.awt.ComponentOrientation;
import java.awt.GridLayout;

/**
 *
 * @author mostafa
 */
public class myMainPanel extends myPanel {

    public myMainPanel() {
        setLayout(new GridLayout(3, 5, 10, 10));
        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        myMainIcon mi_person = new myMainIcon(myMainIcon.myIconType.person);
        myMainIcon mi_shareHolder = new myMainIcon(myMainIcon.myIconType.shareHolder);
        myMainIcon mi_product = new myMainIcon(myMainIcon.myIconType.product);
        myMainIcon mi_bankAccount = new myMainIcon(myMainIcon.myIconType.bankAccount);
        myMainIcon mi_g_Cheque = new myMainIcon(myMainIcon.myIconType.g_Cheque);
        myMainIcon mi_cash = new myMainIcon(myMainIcon.myIconType.cash);
        myMainIcon mi_service = new myMainIcon(myMainIcon.myIconType.service);
        myMainIcon mi_cost = new myMainIcon(myMainIcon.myIconType.cost);
        myMainIcon mi_income = new myMainIcon(myMainIcon.myIconType.income);
        myMainIcon mi_formula = new myMainIcon(myMainIcon.myIconType.production);
        myMainIcon mi_none1 = new myMainIcon(myMainIcon.myIconType.none);
        myMainIcon mi_none2 = new myMainIcon(myMainIcon.myIconType.none);
        myMainIcon mi_none3 = new myMainIcon(myMainIcon.myIconType.none);
        myMainIcon mi_none4 = new myMainIcon(myMainIcon.myIconType.none);
        myMainIcon mi_none5 = new myMainIcon(myMainIcon.myIconType.none);        
        
        add(mi_person);
        add(mi_bankAccount);                
        add(mi_cost);
                        
        add(mi_formula);
        
        add(mi_none2);
        add(mi_shareHolder);
        
        add(mi_g_Cheque);        
        add(mi_income);
        add(mi_none3);                
        
        add(mi_none1);        
        add(mi_product);
        add(mi_cash);        
        
        add(mi_service);                        
        add(mi_none4);
        add(mi_none5);
    }

}
