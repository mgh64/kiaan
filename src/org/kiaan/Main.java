/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kiaan;

import com.mongodb.AggregationOptions;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.bson.types.ObjectId;
import Utile.persianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import myControls.Main_view;
import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author mostafa
 */
public class Main {

        
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        db();
        date();
        
        Main_view Main = new Main_view();
        Main.setFocusable(true);
        Main.setVisible(true);        
        
//        getBranchesWithProject();
        mergeJson();
        
        
    }   
    
    /**
    public static class view_main extends JFrame {            
        
        public view_main() throws HeadlessException, IOException {            
            //
            
            addKeyListener(getInputKey());
            //            
            getContentPane();
            setTitle("نرم افزار حسابداری کیان");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setScreenSize();
//            add BorderLayout
            BorderLayout Layout = new BorderLayout();
            Layout.setHgap(3);Layout.setVgap(3);
            setLayout(Layout);
//        add Top
            JPanel pnlMainTop = new JPanel();
            pnlMainTop.setBackground(Color.LIGHT_GRAY);
            add(pnlMainTop, BorderLayout.PAGE_START);
            JButton btn = new JButton("change");
            pnlMainTop.add(btn);
//        add left
            JPanel pnlMainLeft = new JPanel();
            pnlMainLeft.setBackground(Color.LIGHT_GRAY);
            add(pnlMainLeft, BorderLayout.LINE_START);
//        add right
            JPanel pnlMainRight = new JPanel();
            pnlMainRight.setBackground(Color.LIGHT_GRAY);
            add(pnlMainRight, BorderLayout.LINE_END);
//        add Center
            JPanel pnlMainCenter = new JPanel();
            pnlMainCenter.setBackground(Color.GRAY);
            add(pnlMainCenter, BorderLayout.CENTER);
            
            CardLayout cardLayoutMain = new CardLayout();
            pnlMainCenter.setLayout(cardLayoutMain);
            JPanel subPanelMain = new JPanel(); 
            subPanelMain.setBackground(Color.white);
            
            myToggleButton tb_main = new myToggleButton("کیان",false);            
            subPanelMain.add(tb_main);
            
//            JPanel subPanel2 = new JPanel(); subPanel2.setBackground(Color.blue);
            pnlMainCenter.add(subPanelMain, "1");
//            pnlMainCenter.add(subPanel2, "2");
            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
//                    cardLayoutMain.show(pnlMainCenter, "2");
                    cardLayoutMain.next(pnlMainCenter);
                }
            });

            
//        add Bottom
            JPanel pnlMainBottom = new JPanel();
            pnlMainBottom.setBackground(Color.LIGHT_GRAY);
            add(pnlMainBottom, BorderLayout.PAGE_END);
            
            
            
//            JTable tbl = new JTable();
//            add(tbl, BorderLayout.CENTER);
            
            
            

        }                            
        
        private void setScreenSize() {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            setSize(screenSize.width, screenSize.height);
        }
    }
**/  
    
    public static void db(){
        try{
            //connect to db
            MongoClient MongoClient = new MongoClient("localhost", 27017);
            List<String> databaseNames = MongoClient.getDatabaseNames();            
            //check exists kiaan db
            Boolean blnDBExists = false;
            for(String str : databaseNames){
                if (str.equals("kiaan")) {
                    blnDBExists = true;
                    System.out.println("kiaan database is exists.");
                }
            }           
            if (blnDBExists == false) System.out.println("create kiaan database...");            
            DB mydb = MongoClient.getDB("kiaan");
            System.out.println("Connect to database successfully");
            //Check exist mycol
            if(mydb.collectionExists("g_users") == false){
                DBCollection mycol = mydb.getCollection("g_users");
                //create g_users collection
                ObjectId usr_id = new ObjectId();
                BasicDBObject doc = new BasicDBObject("create_date",new Date())
                        .append("name", "مدیران")
                        .append("credit_limit", 0)
                        .append("status", true)                        
                        .append("users", new BasicDBObject("_id", usr_id)                                
                                .append("create_date", new Date())
                                .append("username", "admin")
                                .append("password", "123")
                                .append("f_name", "مدیر")
                                .append("l_name", "اصلی(پیش فرض سیستم)")
                                .append("status", true)
                        )
                        ;
                mycol.insert(doc);
                System.out.println("g_users Document created...");
                //create banks collection
                doc.clear();
//                db.banks.findOne({"name":"ملی"},{"_id" :0, "name": 0}).branches;
//                db.banks.findOne({},{"_id" :0, "branches": 0});
                
                
                BasicDBList dbl = new BasicDBList();
                dbl.add(new BasicDBObject("_id", new ObjectId())
                        .append("branch_id",8561)
                        .append("name","بازار رضا")
                        .append("city","مشهد")
                );
                doc = new BasicDBObject("name","ملی")
                        .append("branches", dbl);
                mydb.getCollection("banks").insert(doc);
                dbl = new BasicDBList();
                dbl.add(new BasicDBObject("_id", new ObjectId())
                        .append("branch_id",3238)
                        .append("name","بازار رضا")
                        .append("city","مشهد")
                );
                doc = new BasicDBObject("_id", new ObjectId())
                        .append("name","صادرات")
                        .append("branches", dbl);
                mydb.getCollection("banks").insert(doc);
                //add doc to array
                DBObject listItem = new BasicDBObject("branches", new BasicDBObject("_id", new ObjectId())
                        .append("branch_id",8576)
                        .append("name","دقیقی")
                        .append("city","مشهد")
                );
                DBObject findQuery = new BasicDBObject("name", "ملی");
                DBObject updateQuery = new BasicDBObject("$push", listItem);
                mydb.getCollection("banks").update(findQuery, updateQuery);
                System.out.println("banks Document created...");
                
                //add g_person
                doc.clear(); dbl.clear();
                dbl.add(new BasicDBObject("prsn_id",1)
                        .append("type", "سایر")
                        .append("f_name", "نقدی")
                        .append("l_name", "حساب")
                        .append("comment", "جهت معاملات نقدی مشتریان بدون حساب")
                        .append("usr_id", usr_id)
                );
                doc = new BasicDBObject("name","مشتری")
                        .append("usr_id", usr_id)
                        .append("persons", dbl);
                mydb.getCollection("g_persons").insert(doc);
                mydb.getCollection("g_persons").insert(
                        new BasicDBObject("name","سهامدار")                                
                                .append("usr_id", usr_id)
                                .append("persons", null));
                System.out.println("g_persons Document created...");
                //add g_costs
                doc.clear(); dbl.clear();
                dbl.add(new BasicDBObject("_id",new ObjectId())
                        .append("name","تخفیف")
                        .append("default_amount", null)                        
                        .append("usr_id", usr_id)
                );
                dbl.add(new BasicDBObject("_id",new ObjectId())
                        .append("name","مالیات")
                        .append("default_amount", null)                        
                        .append("usr_id", usr_id)
                );
                dbl.add(new BasicDBObject("_id",new ObjectId())
                        .append("name","کرایه")
                        .append("default_amount", null)                        
                        .append("usr_id", usr_id)
                );
                dbl.add(new BasicDBObject("_id",new ObjectId())
                        .append("name","کارمزد بانکی")
                        .append("default_amount", null)                        
                        .append("usr_id", usr_id)
                );
                doc = new BasicDBObject("name","پیش فرض سیستم")                        
                        .append("usr_id", usr_id)
                        .append("costs", dbl);
                mydb.getCollection("g_costs").insert(doc);
                System.out.println("g_costs Document created...");
                
                //add g_costs
                doc.clear(); dbl.clear();
                dbl.add(new BasicDBObject("_id",new ObjectId())
                        .append("name","تخفیف")
                        .append("default_amount", null)                        
                        .append("usr_id", usr_id)
                );
                dbl.add(new BasicDBObject("_id",new ObjectId())
                        .append("name","مالیات")
                        .append("default_amount", null)                        
                        .append("usr_id", usr_id)
                );
                dbl.add(new BasicDBObject("_id",new ObjectId())
                        .append("name","کرایه")
                        .append("default_amount", null)                        
                        .append("usr_id", usr_id)
                );
                dbl.add(new BasicDBObject("_id",new ObjectId())
                        .append("name","سود سپرده بانکی")
                        .append("default_amount", null)                        
                        .append("usr_id", usr_id)
                );
                doc = new BasicDBObject("name","پیش فرض سیستم")                        
                        .append("usr_id", usr_id)
                        .append("incomes", dbl);
                mydb.getCollection("g_incomes").insert(doc);
                System.out.println("g_incomes Document created...");
                                
                //add g_products
                doc.clear(); dbl.clear();
                dbl.add(new BasicDBObject("prdt_id",1)
                        .append("name", "ساچمه")
                        .append("unit", "مثقال")
                        .append("usr_id", usr_id)
                );
                doc = new BasicDBObject("name","نقره")                        
                        .append("usr_id", usr_id)
                        .append("products", dbl);
                mydb.getCollection("g_products").insert(doc);                
                System.out.println("g_products Document created...");
                
                //add buy_invoices
                doc.clear(); dbl.clear();
                dbl.add(new BasicDBObject("_id",new ObjectId())
                        .append("prdt_id", 1)
                        .append("value", 100)
                        .append("price", 9250)
                        .append("comment", "تست توضیحات سطر اول")                                                                                                
                );
                dbl.add(new BasicDBObject("_id",new ObjectId())
                        .append("prdt_id", 1)
                        .append("value", 350)
                        .append("price", 9350)
                        .append("comment", "تست توضیحات سطر دوم")
                );
                doc = new BasicDBObject("number", 1)
                        .append("date", new Date())
                        .append("prsn_id", 1)
                        .append("comment", "تست توضیحات فاکتور")                        
                        .append("usr_id", usr_id)
                        .append("items", dbl);
                mydb.getCollection("buy_invoices").insert(doc);
                doc.clear(); dbl.clear();
                dbl.add(new BasicDBObject("_id",new ObjectId())
                        .append("prdt_id", 1)
                        .append("value", 1000)
                        .append("price", 930)                        
                );
                doc = new BasicDBObject("number", 2)
                        .append("date", new Date())
                        .append("prsn_id", 1)
                        .append("usr_id", usr_id)
                        .append("items", dbl);
                mydb.getCollection("buy_invoices").insert(doc);         
                System.out.println("buy_invoices Document created...");
                
            } else {
                DBCollection mycol = mydb.getCollection("user_groups");
                BasicDBObject doc = new BasicDBObject("users.password" , "123");
                DBObject myDoc = mycol.findOne(doc);
//                System.out.println(myDoc);
                
            }

            

//            System.out.println("Collection created successfully");
//            BasicDBObject doc = new BasicDBObject("name","mongoDB");
//            coll.insert(doc);
        } catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
    public static void date(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss");
//            String dateInString = "2015/02/18 15:36:01";
//            Date date = sdf.parse(dateInString);
 
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
        
//            Calendar calendar = new GregorianCalendar(2015,02,18,15,36,1);
            persianCalendar persianCalendar = new persianCalendar(calendar);            
            System.out.println(persianCalendar.getDescribedDateFormat());
//            System.out.println(persianCalendar.getNumericDateFormat());
//            System.out.println(persianCalendar.getTime());
//            System.out.println(persianCalendar.getMonth());
//            System.out.println(persianCalendar.getWeekDay());
//            System.out.println(persianCalendar.getTimeInMillis()  );
    }
    
    public static void getFind(){
        try{
            MongoClient mongoClient = new MongoClient("localhost", 27017);
                DB db = mongoClient.getDB( "kiaan" );
                DBCollection coll = db.getCollection("test");
    //            BasicDBObject doc = new BasicDBObject("_id" , false);
    //            DBCursor cursor = (DBCursor) coll.findOne(null, doc);
                DBCursor cursor = coll.find();
    //            db.banks.findOne({},{"_id" :0, "branches": 0});

                String[] columnNames = {"_id", "id", "name"};
                DefaultTableModel model = new DefaultTableModel(columnNames, 0);

                while(cursor.hasNext()) {                  
                    DBObject obj = cursor.next();
                    System.out.println(obj.get("name"));
    //                Double first = (Double)obj.get("id");
    //                String last = (String)obj.get("name");
    //                ObjectId id = (ObjectId)obj.get("_id");
    //                model.addRow(new Object[] { id, first, last });
                }
    //            tbl.setModel(model);
        } catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        
    }
    
    public static void mergeJson() {
        JSONObject json1 = new JSONObject();
        JSONObject json2 = new JSONObject();

        try {
            json1.put("Crunchify", "Java Company");
            json1.put("Google", "Search Company");
        json1.put("Yahoo", "Web Company");

        json2.put("Facebook", "Social Network Company");
        json2.put("Twitter", "Another Social Company");
        json2.put("Linkedin", "Professional Network Company");

        } catch (JSONException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("json1: " + json1);
        System.out.println("json2: " + json2);

        JSONObject mergedJSON = mergeJSONObjects(json1, json2);
        System.out.println("\nmergedJSON: " + mergedJSON);
    } 
    
    public static JSONObject mergeJSONObjects(JSONObject json1, JSONObject json2) {
        JSONObject mergedJSON = new JSONObject();
        try {
                mergedJSON = new JSONObject(json1, JSONObject.getNames(json1));
                for (String crunchifyKey : JSONObject.getNames(json2)) {
                        mergedJSON.put(crunchifyKey, json2.get(crunchifyKey));
                }

        } catch (JSONException e) {
                throw new RuntimeException("JSON Exception" + e);
        }
        return mergedJSON;
    }
    
    public static void getBranchesWithProject(){
        try{
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB( "kiaan" );
            DBCollection coll = db.getCollection("banks");
//            BasicDBObject doc = new BasicDBObject("_id" , false).append("name", true);
//            BasicDBObject doc1 = new BasicDBObject("name" , "مشتری");
            
//            BasicDBObject doc = new BasicDBObject("_id" , false).append("branches.name", true);
//            DBCursor cursor = coll.find(null, doc);
            
            DBObject unwind = new BasicDBObject("$unwind","$branches");
            DBObject field = new BasicDBObject("_id",false);
            field.put("name", "$name");
            field.put("branch_id", "$branches.branch_id");
            field.put("branch_name", "$branches.name");
            DBObject project = new BasicDBObject("$project",field);
            DBObject sort = new BasicDBObject("$sort", new BasicDBObject("name",1));
            List<DBObject> pipeline = Arrays.asList(unwind, project, sort);
            
//            AggregationOutput output = coll.aggregate(pipeline);
//            for (DBObject result : output.results()) {
//                System.out.println(result);
//            }
            
            AggregationOptions aggregationOptions = AggregationOptions.builder()
                .batchSize(100)
                .outputMode(AggregationOptions.OutputMode.CURSOR)
                .allowDiskUse(true)
                .build();
            
            Cursor cursor = coll.aggregate(pipeline, aggregationOptions);
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }

//            while(cursor.hasNext()) {                  
//                DBObject obj = cursor.next();               
//                System.out.println(obj.get("branches"));
////                Double first = (Double)obj.get("id");
////                String last = (String)obj.get("name");
////                ObjectId id = (ObjectId)obj.get("_id");
////                model.addRow(new Object[] { id, first, last });
//            }
//            tbl.setModel(model);
        } catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        
    }
    
    
    
    }

