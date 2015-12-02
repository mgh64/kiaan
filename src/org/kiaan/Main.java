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
import org.bson.types.ObjectId;
import Utile.persianCalendar;
import com.mongodb.CommandResult;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import myControls.Main_view;
import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author mostafa
 */
public class Main {

        
     
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        db();
        date();
//        getBuy();
        
//        Main_view Main = new Main_view();
//        Main.setFocusable(true);
//        Main.setVisible(true);        
//        
//        getBranchesWithProject();
        
//        mergeJson();
        
        
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
            MongoIterable<String> databaseNames =  MongoClient.listDatabaseNames();
//            MongoDatabase kiaanDB = MongoClient.getDatabase("kiaan");            
            //check exists kiaan db
            Boolean blnDBExists = false;
            for(String DBName : databaseNames){
                if (DBName.equals("kiaan")) {
                    blnDBExists = true;                    
                    System.out.println("kiaan database is exists.");
                    break;
                }
            }           
            if (blnDBExists == false) System.out.println("create kiaan database...");            
            MongoDatabase mydb = MongoClient.getDatabase("kiaan");
            System.out.println("Connect to database successfully");
            //Check exist user collection
            Boolean blnCollectionName = false;
            for(String collectionName : mydb.listCollectionNames()){
                if (collectionName.equals("user")) {
                    blnCollectionName = true;
                    break;
                }               
            }
            if (!blnCollectionName){
                //create users collection
                ObjectId user_id = new ObjectId();
                Document doc = new Document()
                        .append("name", "مدیران")
                        //.append("credit_limit", 0)
//                        .append("status", true)
                        .append("comment", "پیش فرض سیستم(غیرقابل حذف)")
                        .append("creator_id", user_id)
                        .append("users", new Document("_id", user_id)                                
                                .append("id", 1)
                                .append("first_name", "مدیر عامل")
                                .append("last_name", "(پیش فرض سیستم)")                                
                                .append("user_name", "admin")
                                .append("password", "1602")                                
                                //.append("status", false)
                                .append("creator_id", user_id)
                        );
                mydb.getCollection("user").insertOne(doc);
                System.out.println("user collection created...");
                doc.clear();
                //
                BasicDBList dbl = new BasicDBList();
                ObjectId branch_id = new ObjectId();
                dbl.add(new Document("_id", branch_id)
                        .append("id",8561)
                        .append("name","بازار رضا")
                        .append("city","مشهد")
                        .append("creator_id", user_id)
                );
                dbl.add(new Document("_id", new ObjectId())
                        .append("id",8576)
                        .append("name","غدیر")
                        .append("city","مشهد")
                        .append("creator_id", user_id)
                );
                ObjectId bank_id = new ObjectId();
                doc = new Document("_id", bank_id)
                        .append("name", "ملی")
//                        .append("logo", "")
                        .append("creator_id", user_id)
                        .append("branches", dbl);                
                mydb.getCollection("bank").insertOne(doc);
                
                dbl = new BasicDBList();
                dbl.add(new Document("_id", new ObjectId())
                        .append("id",3238)
                        .append("name","بازار رضا")
                        .append("city","مشهد")
                        .append("creator_id", user_id)
                );
                doc = new Document()
                        .append("name","صادرات")
//                        .append("logo", "")
                        .append("creator_id", user_id)
                        .append("branches", dbl);
                mydb.getCollection("bank").insertOne(doc);
                //
                doc = new Document()
                        .append("name", "ملت")
                        .append("creator_id", user_id)
                        .append("branches", new BasicDBList());
                mydb.getCollection("bank").insertOne(doc);
                //add doc to array
//                DBObject listItem = new BasicDBObject("branches", new BasicDBObject("_id", new ObjectId())
//                        .append("branch_id",8576)
//                        .append("name","دقیقی")
//                        .append("city","مشهد")
//                );
//                DBObject findQuery = new BasicDBObject("name", "ملی");
//                DBObject updateQuery = new BasicDBObject("$push", listItem);
//                mydb.getCollection("banks").update(findQuery, updateQuery);                                
                //
                System.out.println("bank collection created...");
                
                //add person
                doc.clear(); dbl.clear();
                ObjectId person_id1 = new ObjectId();
                ObjectId person_id2 = new ObjectId();
                dbl.add(new Document("_id",person_id1)
                        .append("id", 1)
                        .append("type", "سایر")
                        .append("first_name", "نقدی")
                        .append("last_name", "حساب")                        
                        .append("comment", "جهت معاملات نقدی مشتریان بدون حساب")                        
                        .append("creator_id", user_id)
                );
                
                BasicDBList dbl_tel = new BasicDBList();
                BasicDBList dbl_bankAcc = new BasicDBList();
                dbl_tel.add(new Document("_id", new ObjectId())
                        .append("type", "mobile")
                        .append("number", "09151213139")
                        .append("default", true)
                );
                dbl_tel.add(new Document("_id", new ObjectId())
                        .append("type", "mobile")
                        .append("number", "09151112233")
                );
                dbl_tel.add(new Document("_id", new ObjectId())
                        .append("type", "work")
                        .append("number", "05133661313")
                        .append("default", true)
                );                
                dbl_bankAcc.add(new Document("_id", new ObjectId())
                        .append("type", "bank_acc")
                        .append("bank_id", bank_id)
                        .append("number", "4218504285")
                        .append("comment", "تجارت بازار رضا")
                        .append("default", true)
                );
                dbl_bankAcc.add(new Document("_id", new ObjectId())
                        .append("type", "sheba")
                        .append("bank_id", bank_id)
                        .append("number", "600120020000004218504285")
                );
                dbl.add(new Document("_id",person_id2)
                        .append("id", 2)
                        .append("type", "حقیقی")
                        .append("first_name", "مرتضی")
                        .append("last_name", "الیاسی")
                        .append("gender", true)
                        .append("credit_limit", 10000000)
                        .append("address", "بازار رضا - بازار سوم از طرف قلکه آب - پلاک 2/716")                        
                        .append("creator_id", user_id)
                        .append("tel", dbl_tel)
                        .append("bank_account", dbl_bankAcc)
                );
                doc = new Document("name","مشتری")
                        .append("creator_id", user_id)
                        .append("persons", dbl);
                mydb.getCollection("person").insertOne(doc);
                
                mydb.getCollection("person").insertOne(
                        new Document("name","سهامدار")                                
                            .append("creator_id", user_id)
                            .append("persons", new BasicDBList())
                );
                System.out.println("person collection created...");
                //
                doc = new Document("id", 1)
                        .append("account_no", "0205575259006")
                        .append("account_holder", "مصطفی قاسمی")
                        .append("bank_id", bank_id)
                        .append("branch_id", branch_id)
                        .append("type", 0)
                        .append("comment", "حساب پوز")
                        .append("creator_id", user_id)
                ;                
                mydb.getCollection("bank_account").insertOne(doc);
                doc = new Document("id", 2)
                        .append("account_no", "0207723518008")
                        .append("account_holder", "مصطفی قاسمی")
                        .append("bank_id", bank_id)
                        .append("branch_id", branch_id)
                        .append("type", 1)                        
                        .append("creator_id", user_id)
                ;           
                mydb.getCollection("bank_account").insertOne(doc);
                
                System.out.println("bank_account collection created...");
                //add units
                ObjectId unit_id = new ObjectId();
                doc = new Document("_id", unit_id)
                        .append("name", "ریال")
                        .append("creator_id", user_id)
                ;
                mydb.getCollection("unit").insertOne(doc);
                System.out.println("Unit collection created...");
                //add products
                dbl = new BasicDBList();
                ObjectId product_id = new ObjectId();                
                dbl.add(new Document("_id",product_id)
                        .append("id", 1)
                        .append("name", "ساچمه")
                        .append("unit_id", unit_id)
                        .append("min_stock", 1000)
                        .append("default_price", 8300)
                        .append("comment", "توضیحات کالا")
                        .append("creator_id", user_id)
                );
                dbl.add(new Document("_id",new ObjectId())
                        .append("id", 2)
                        .append("name", "سکه")
                        .append("unit_id", unit_id)
                        .append("min_stock", 10)
                        .append("default_price", 80000)
                        .append("comment", "توضیحات کالا سکه")
                        .append("creator_id", user_id)
                );
                doc = new Document("name","نقره")
                        .append("comment", "توضیحات گروه کالا")
                        .append("creator_id", user_id)                        
                        .append("products", dbl);
                mydb.getCollection("product").insertOne(doc);
                //
                dbl.clear();
                dbl.add(new Document("_id",new ObjectId())
                        .append("id", 3)
                        .append("name", "نقره")
                        .append("unit_id", unit_id)
                        .append("min_stock", 100)
                        .append("default_price", 2500)
                        .append("comment", "توضیحات کالا گل نقره")
                        .append("creator_id", user_id)
                );
                doc = new Document("name","گل")
                        .append("comment", "توضیحات گروه کالای گل")
                        .append("creator_id", user_id)                        
                        .append("products", dbl);
                mydb.getCollection("product").insertOne(doc);
                //
                System.out.println("product Document created...");
                
                //add buy
                doc.clear(); dbl.clear();
                dbl.add(new Document("_id",new ObjectId())
                        .append("product_id", product_id)
                        .append("value", 100)
                        .append("price", 9250)
                        .append("comment", "تست توضیحات سطر اول")                                                                                                
                );
                dbl.add(new Document("_id",new ObjectId())
                        .append("product_id", product_id)
                        .append("value", 350)
                        .append("price", 9350)
                        .append("comment", "تست توضیحات سطر دوم")
                );
                doc = new Document("_id", new ObjectId())                        
                        .append("type", "buy")
                        .append("id", 1)
                        .append("person_id", person_id1)
                        .append("by", "جعفری علی")
                        .append("discount", -1500)
//                        .append("increase", 0)
                        .append("tax", 4000)
//                        .append("fare", 0)
                        .append("comment", "تست توضیحات فاکتور")                        
                        .append("creator_id", user_id)
                        .append("items", dbl);
                mydb.getCollection("buy").insertOne(doc);
//                doc.clear(); dbl.clear();
//                dbl.add(new BasicDBObject("_id",new ObjectId())
//                        .append("product_id", 1)
//                        .append("value", 1000)
//                        .append("price", 9300)                        
//                );
//                doc = new BasicDBObject("num", 2)
//                        .append("date", new Date())                        
//                        .append("person_id", person_id2)
//                        .append("by", "یوسفی غلام")
//                        .append("discount", 0)
//                        .append("increase", 0)
//                        .append("user_id", user_id)
//                        .append("items", dbl);
//                mydb.getCollection("buy").insert(doc);         
                System.out.println("buy Document created...");
                
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
//                DBCursor cursor = (DBCursor) coll.findOne(null, doc);
                DBCursor cursor = coll.find();
    //            db.banks.findOne({},{"_id" :0, "branches": 0});


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
            
            BasicDBObject dbo = new BasicDBObject();            
            BasicDBList dbl = new BasicDBList();
            Cursor cursor = coll.aggregate(pipeline, aggregationOptions);
            while (cursor.hasNext()) { 
                dbo = (BasicDBObject) cursor.next();
                System.out.println(dbo.toString());
//                dbl.add(cursor.next());                
            }                                             
            System.out.println(dbl.toString());
            
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
    
    private static void getBuy(){
        try{
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB( "kiaan" );
            DBCollection coll = db.getCollection("buy");
            //aggregate
            DBObject unwind = new BasicDBObject("$unwind","$items");
            //$group            
            DBObject group_id = new BasicDBObject("_id", "$_id");
            group_id.put("num", "$num");
            group_id.put("person_id", "$person_id");
            group_id.put("discount", "$discount");
            group_id.put("increase", "$increase");
            //$group -> $multiply
            BasicDBList args  = new BasicDBList();
            args.add("$items.value");
            args.add("$items.price");
            DBObject multiply = new BasicDBObject("$multiply", args);
            //$group -> $sum
//            DBObject group_sum = new BasicDBObject("$sum", multiply);
            DBObject group_field = new BasicDBObject();
            group_field.put("_id", group_id);
            group_field.put("total", new BasicDBObject("$sum", multiply));
            DBObject group = new BasicDBObject("$group",group_field);
            //$project
            DBObject project_field = new BasicDBObject("_id","$_id._id");
            project_field.put("person_id", "$_id.person_id");
            project_field.put("num", "$_id.num");
            BasicDBList arr = new BasicDBList();
            arr.add("$total");arr.add("$_id.discount");arr.add("$_id.increase");
            DBObject field_add = new BasicDBObject("$add",arr);
            project_field.put("sum", field_add);
            DBObject project = new BasicDBObject("$project",project_field);
            DBObject sort = new BasicDBObject("$sort", new BasicDBObject("_id",1));
            List<DBObject> pipeline = Arrays.asList(unwind, group, project, sort);
            
//            AggregationOutput output = coll.aggregate(pipeline);
//            for (DBObject result : output.results()) {
//                System.out.println(result);
//            }
            
            AggregationOptions aggregationOptions = AggregationOptions.builder()
                .batchSize(100)
                .outputMode(AggregationOptions.OutputMode.CURSOR)
                .allowDiskUse(true)
                .build();
            
            BasicDBObject dbo = new BasicDBObject();            
            BasicDBList dbl = new BasicDBList();
            Cursor cursor = coll.aggregate(pipeline, aggregationOptions);
            //
            DBCollection person_col = db.getCollection("persons");
            
//            BasicDBObject query = new BasicDBObject("items.personId",1);             
            BasicDBObject fields = new BasicDBObject("items.$",1)                    
                    .append("_id",false);
            
//            BasicDBList l_per = (BasicDBList) person_col.findOne(query, fields).get("items");
//            BasicDBObject[] lightArr = l_per.toArray(new BasicDBObject[0]);            
//            System.out.println(lightArr[0].get("_id"));
//            System.out.println(lightArr[0].get("first_name"));  
            
            
        
            
            
            BasicDBList result = new BasicDBList();
            while (cursor.hasNext()) {                 
                dbo = (BasicDBObject) cursor.next();
//                System.out.println(dbo.toString());  
                DBObject query = new BasicDBObject("items._id", (ObjectId) dbo.get("person_id"));
                BasicDBList lst_person = (BasicDBList) person_col.findOne(query, fields).get("items");
                BasicDBObject[] lightArr = lst_person.toArray(new BasicDBObject[0]);                            
//                System.out.println(lightArr[0].get("first_name"));
                
                Date date = ((ObjectId) lightArr[0].get("_id")).getDate();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                persianCalendar persianCalendar = new persianCalendar(calendar);            
                
                dbo.put("date", persianCalendar.getNumericDateFormatWithTime());
                dbo.put("personId", lightArr[0].get("personId").toString());
                dbo.put("first_name", lightArr[0].get("first_name").toString());
                dbo.put("last_name", lightArr[0].get("last_name").toString());
                
                dbo.removeField("person_id");
                result.add(dbo);
//                System.out.println(dbo.get("num"));                  
            }                                             
            System.out.println(result.toString());
                        
        } catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    
    }

