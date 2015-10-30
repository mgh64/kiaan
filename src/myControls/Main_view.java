/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myControls;

import Utile.mySetting;
import Utile.mySetting.gradient_JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import myClasses.InputKey;
import Utile.persianCalendar;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.table.DefaultTableModel;
import org.bson.types.ObjectId;

/**
 *
 * @author mostafa
 */
public class Main_view extends JFrame {

    public Main_view() throws IOException {

        InputKey gik = new InputKey();
        addKeyListener(gik.getInputKey());

        getContentPane();
        setTitle("نرم افزار حسابداری کیان");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setScreenSize();
        //This will center the JFrame in the middle of the screen
//        setLocationRelativeTo(null);
//        add BorderLayout
        BorderLayout Layout = new BorderLayout();
        Layout.setHgap(3);
        Layout.setVgap(3);
        setLayout(Layout);
//        add Top
        myPanel pnlMainTop = new myPanel();
        pnlMainTop.setBackground(Color.LIGHT_GRAY);
        add(pnlMainTop, BorderLayout.PAGE_START);
        // set BorderLayout for top
        BorderLayout bdrTop = new BorderLayout(7,7);
        pnlMainTop.setLayout(bdrTop);

        myPanel pnlTopCenter = new myPanel();
        pnlMainTop.add(pnlTopCenter, BorderLayout.CENTER);
        
        myPanel pnlTopLeft = new myPanel();
        pnlMainTop.add(pnlTopLeft, BorderLayout.LINE_START);
        
        myPanel pnlTopRight = new myPanel();
        pnlMainTop.add(pnlTopRight, BorderLayout.LINE_END);
                
        //add Fiscal Year
        myComboBox cboFiscalYear = new myComboBox();
        pnlTopLeft.add(cboFiscalYear);

        myLabel lblFiscalYear = new myLabel("سال مالی :");
        lblFiscalYear.setOpaque(true);
        lblFiscalYear.setBackground(Color.orange);
        pnlTopLeft.add(lblFiscalYear);

        //create img for log`s company
        BoxLayout bxbdrTopCenter = new BoxLayout(pnlTopCenter, BoxLayout.Y_AXIS);
        pnlTopCenter.setLayout(bxbdrTopCenter);
//        BufferedImage imgCompany = new BufferedImage(90, 80, BufferedImage.TYPE_INT_ARGB); //ImageIO.read(getClass().getResource("1.gpg"));
        BufferedImage imgCompany = ImageIO.read(new File("1.jpg"));
        BufferedImage imgResize = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = imgResize.createGraphics();
        g.drawImage(imgCompany, 0, 0, 100, 100, null);
        g.dispose();
        JLabel lblCompanyImage = new JLabel(new ImageIcon(imgResize));//new ImageIcon(imgCompany)
        lblCompanyImage.setBorder(BorderFactory.createLineBorder(Color.GRAY ));
        lblCompanyImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblCompanyName = new JLabel("<html>صنایع دستی <font color='red'>قاسمی</font></html>", SwingConstants.CENTER);
        lblCompanyName.setOpaque(true);
        lblCompanyName.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        pnlTopCenter.add(lblCompanyImage);
        pnlTopCenter.add(lblCompanyName);

        //create img for log`s Kiaan
        BoxLayout bxbdrTopRight = new BoxLayout(pnlTopRight, BoxLayout.Y_AXIS);
        pnlTopRight.setLayout(bxbdrTopRight);
        
        BufferedImage imgKiaan = new BufferedImage(90, 80, BufferedImage.TYPE_INT_ARGB); //ImageIO.read(getClass().getResource("1.gpg"));
        JLabel lblKiaanImage = new JLabel(new ImageIcon(imgKiaan));//new ImageIcon(imgKiaan)
        lblKiaanImage.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        lblKiaanImage.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JLabel lblKiaanName = new JLabel("نرم افزار حسابداری کیان",SwingConstants.RIGHT);
        lblKiaanName.setAlignmentX(Component.RIGHT_ALIGNMENT);
        JLabel lblKiaanVersion = new JLabel("نسخه الفا",SwingConstants.RIGHT);
        lblKiaanVersion.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        pnlTopRight.add(lblKiaanImage);
        pnlTopRight.add(lblKiaanName);
        pnlTopRight.add(lblKiaanVersion);
                
        //
        gradient_JPanel pnlShowClock = new gradient_JPanel();
        pnlShowClock.setOpaque(true);
        pnlShowClock.setPreferredSize(new Dimension(0, (int) mySetting.getScreenHeightSize() / 27));
        pnlMainTop.add(pnlShowClock, BorderLayout.PAGE_END);

        pnlShowClock.setLayout(new FlowLayout(FlowLayout.RIGHT,30,5));        
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        persianCalendar persianCalendar = new persianCalendar(calendar);                                    
        pnlShowClock.add(new JLabel(persianCalendar.getDescribedDateFormat(), JLabel.RIGHT));

//        add left
        myPanel pnlMainLeft = new myPanel();
        pnlMainLeft.setBackground(Color.LIGHT_GRAY);
        add(pnlMainLeft, BorderLayout.LINE_START);
//        add right
        myPanel pnlMainRight = new myPanel();
        pnlMainRight.setBackground(Color.LIGHT_GRAY);
        add(pnlMainRight, BorderLayout.LINE_END);
//        add Center
        myPanel pnlMainCenter = new myPanel();
        pnlMainCenter.setBackground(Color.GRAY);
        add(pnlMainCenter, BorderLayout.CENTER);

        BorderLayout bdrMain = new BorderLayout();
        pnlMainCenter.setLayout(bdrMain);
        
        myPanel pnlCenterTop = new myPanel();        
        CardLayout cardLayout_ct = new CardLayout();
        pnlCenterTop.setLayout(cardLayout_ct);
        myPanel pnl_subTop1 = new myPanel(new FlowLayout(FlowLayout.RIGHT,50,5));
        pnl_subTop1.setBackground(Color.red);
        pnl_subTop1.add(getMainMenu());
        myPanel pnl_subTop2 = new myPanel();
        pnl_subTop2.setBackground(Color.blue);
        pnlCenterTop.add(pnl_subTop1, "1");
        pnlCenterTop.add(pnl_subTop2, "2");
        
//        pnlCenterTop.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);        
//        myMainMenu mnuBasicDefinitions = new myMainMenu("تعاریف پایه");
//        pnlCenterTop.add(mnuBasicDefinitions);        
//        myMainMenu mnuBasicInfo = new myMainMenu("اطلاعات پایه");
//        pnlCenterTop.add(mnuBasicInfo);        
        
        myPanel pnlCenterCenter = new myPanel();
        BoxLayout boxLayout_CC = new BoxLayout(pnlCenterCenter, BoxLayout.Y_AXIS);
        pnlCenterCenter.setLayout(boxLayout_CC);
        pnlCenterCenter.setBackground(Color.gray);
        
        
        pnlMainCenter.add(pnlCenterTop, BorderLayout.PAGE_START);
        
        pnlMainCenter.add(pnlCenterCenter, BorderLayout.CENTER);
        myButton btn = new myButton("change");
        pnlCenterCenter.add(btn);
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                cardLayout_ct.show(pnlMainCenter, "2");
                cardLayout_ct.next(pnlCenterTop);
            }
        });
        
//        myMainPanel mp = new myMainPanel();
//        pnlCenterCenter.add(mp);
        
        pnlCenterCenter.add(getGrid());
         
        
//        CardLayout cardLayoutMain = new CardLayout();
//        pnlMainCenter.setLayout(cardLayoutMain);
//        JPanel subPanelMain = new JPanel(); 
//        subPanelMain.setBackground(Color.white);
//
//        myMainMenu mnu_desktop = new myMainMenu("میزکار");
//        subPanelMain.add(mnu_desktop);
//        
//        myToggleButton tb_main = new myToggleButton("کیان",false);            
//        subPanelMain.add(tb_main);
//
////            JPanel subPanel2 = new JPanel(); subPanel2.setBackground(Color.blue);
//        pnlMainCenter.add(subPanelMain, "1");
////            pnlMainCenter.add(subPanel2, "2");
////        btn.addKeyListener(gik.getInputKey()); //todo create class for buttons
//        btn.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
////                cardLayoutMain.show(pnlMainCenter, "2");
////                cardLayoutMain.next(pnlMainCenter);
//            }
//            
//        });  
//        add Bottom
        myPanel pnlMainBottom = new myPanel();
        pnlMainBottom.setBackground(Color.LIGHT_GRAY);
        add(pnlMainBottom, BorderLayout.PAGE_END);
    }

    
    private myPanel getMainMenu(){
        myPanel pnl = new myPanel(new FlowLayout(FlowLayout.RIGHT,10,0));
        pnl.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        
        myMainMenu mnuBasicDefinitions = new myMainMenu("تعاریف پایه");
        myMainMenu mnuBasicInfo = new myMainMenu("اطلاعات پایه");
        myMainMenu mnuFiscalOperations = new myMainMenu("عملیات مالی");
        myMainMenu mnuFiscalReports = new myMainMenu("گزارشات مالی");
        myMainMenu mnuUsers = new myMainMenu("کاربران");
        myMainMenu mnuTools = new myMainMenu("امکانات");
        
        pnl.add(mnuBasicDefinitions);
        pnl.add(mnuBasicInfo);
        pnl.add(mnuFiscalOperations);
        pnl.add(mnuFiscalReports);
        pnl.add(mnuUsers);
        pnl.add(mnuTools);
        
        return pnl;
    }
    
    private myPanel getGrid(){
        myPanel pnl = new myPanel();
        myTable table = new myTable();
        pnl.add(table);
        //
        MongoClient mongoClient = null;
        DBCursor cursor = null;
        try {
            mongoClient = new MongoClient( "localhost" , 27017 );
            DB db = mongoClient.getDB( "kiaan" );
            DBCollection coll = db.getCollection("banks");
            cursor = coll.find();

            String[] columnNames = {"id", "name"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            while(cursor.hasNext()) {
                    DBObject obj = cursor.next();
                    String first = (String)obj.get("name");
                    ObjectId id = (ObjectId)obj.get("_id");
                    model.addRow(new Object[] { id, first });
            }
            table.setModel(model);

            cursor.close(); 
            mongoClient.close();
        } catch (UnknownHostException ex) {
                System.out.println(ex);
            } finally {
                if (cursor!= null) {
                    cursor.close();
                }
                if (mongoClient != null) {
                     mongoClient.close();
                }   
            }
        //
        return pnl;
    }
    
    
    
    private void setScreenSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
    }

}
