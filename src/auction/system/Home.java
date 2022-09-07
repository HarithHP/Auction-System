/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.system;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;


public class Home extends javax.swing.JFrame {

    Connection con = SqlConnection.getCon();
    PreparedStatement pst = null;
    ResultSet rs = null;
    public int min=0;
    public int sec=0;
    Timer time;
    public Home() {
        initComponents();
        MaxHeap obj1 = new MaxHeap(30);
        flechitems();
        showValues();
        
        setLocationRelativeTo(this);
        time = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               
                lbl_sec.setText(String.valueOf(sec));
                lbl_min.setText(String.valueOf(min));
                
                if(sec==60){
                    sec=0;
                    min++;
                    if(min==30){
                        time.stop();
                        btn_bit1.setVisible(false);
                         btn_bit2.setVisible(false);
                          btn_bid3.setVisible(false);
                    }
                }
                sec++;
            }
        });
        time.start();
    } 
    
    
     MaxHeap obj1 = new MaxHeap(20);
     MaxHeap obj2 = new MaxHeap(20);
     MaxHeap obj3 = new MaxHeap(20);
     
     double item1Max,item2Max,item3Max;
     String id1,id2,id3;
    public void showValues(){
        item1Max = obj1.getMax();
        item2Max = obj2.getMax();
        item3Max = obj3.getMax();
        itemBidvalue1.setText(Double.toString(item1Max));
        itemBidvalue2.setText(Double.toString(item2Max));
        itemBidvalue3.setText(Double.toString(item3Max));
        
    }
     public void flechitems(){
        try{
           String query = "SELECT * FROM `item` WHERE itemID =1"; 
           //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction?autoReconnec=true&useSSl=false","root","");
           pst = con.prepareStatement(query);
           rs =pst.executeQuery();
           if(rs.next()){
                int iitemID1=rs.getInt("itemID");
                String iitemType1=rs.getString("itemType");
                String iitemName1=rs.getString("itemName");
                
                
                itemID1.setText(""+iitemID1);
                itemType1.setText(iitemType1);
                itemName1.setText(iitemName1);
           }           
       }catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);
       }
        try{
           String query = "SELECT * FROM `item` WHERE itemID =2"; 
           //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction?autoReconnec=true&useSSl=false","root","");
           pst = con.prepareStatement(query);
           rs =pst.executeQuery();
           if(rs.next()){
                int iitemID2=rs.getInt("itemID");
                String iitemType2=rs.getString("itemType");
                String iitemName2=rs.getString("itemName");
                
                
                itemID2.setText(""+iitemID2);
                itemType2.setText(iitemType2);
                itemName2.setText(iitemName2);
           }           
       }catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);
       }
        try{
           String query = "SELECT * FROM `item` WHERE itemID =3"; 
           //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auction?autoReconnec=true&useSSl=false","root","");
           pst = con.prepareStatement(query);
           rs =pst.executeQuery();
           if(rs.next()){
                int iitemID3=rs.getInt("itemID");
                String iitemType3=rs.getString("itemType");
                String iitemName3=rs.getString("itemName");
                
                
                itemID3.setText(""+iitemID3);
                itemType3.setText(iitemType3);
                itemName3.setText(iitemName3);
           }           
       }catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);
       }
    }
     
    private void checkData1() {
        try {
            String query = "select * from biditem1 where cus_natiID=? and bidvalue=?";
            pst = con.prepareStatement(query);
            pst.setString(1, txt_cusID.getText());
            pst.setString(2, txt_bidvalue.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                obj1.removeMax();
                delete1();
            } else {

                JOptionPane.showMessageDialog(null, "Can't find");
            }
        } catch (SQLException ex) {
            //Logger.getLogger(ManageLoging.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    private void checkData2() {
        try {
            String query = "select * from biditem2 where cus_natiID=? and bidvalue=?";
            pst = con.prepareStatement(query);
            pst.setString(1, txt_cusID.getText());
            pst.setString(2, txt_bidvalue.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                obj2.removeMax();
                delete2();
            } else {

                JOptionPane.showMessageDialog(null, "Can't find");
            }
        } catch (SQLException ex) {
            //Logger.getLogger(ManageLoging.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    private void checkData3() {
        try {
            String query = "select * from biditem3 where cus_natiID=? and bidvalue=?";
            pst = con.prepareStatement(query);
            pst.setString(1, txt_cusID.getText());
            pst.setString(2, txt_bidvalue.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                obj3.removeMax();
                delete3();
            } else {

                JOptionPane.showMessageDialog(null, "Can't find");
            }
        } catch (SQLException ex) {
            //Logger.getLogger(ManageLoging.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
   
     
    public void validateInputitem1(){
                if(txt_cusID.equals("")){
            JOptionPane.showMessageDialog(null, "Please Enter Customer ID");
        }else if(txt_cusName.equals("Select")){
            JOptionPane.showMessageDialog(null, "Please Select Customer Name");
        }else if(txt_cusEmail.equals("")){
            JOptionPane.showMessageDialog(null, "Please Enter Email");
        }
        else if(txt_bidvalue.equals("")){
            JOptionPane.showMessageDialog(null, "Please Enter Bid Value");
        }
        else{
            
  
            input1();
        }
    }
    public void input1(){
        try{ String query = "INSERT INTO `biditem1`(`cus_natiID`, `name`, `email`, `bidvalue`) VALUES (?,?,?,?)"; 
           pst = con.prepareStatement(query);
           pst.setString(1,txt_cusID.getText());
           pst.setString(2,txt_cusName.getText());
           pst.setString(3,txt_cusEmail.getText());
           pst.setString(4,txt_bidvalue.getText());
           pst.executeUpdate();
             JOptionPane.showMessageDialog(null, "Bidded For Item 1 Successfully");
         }catch(Exception ex){
             JOptionPane.showMessageDialog(null, ex);
         }
    }
    public void validateInputitem2(){
                if(txt_cusID.equals("")){
            JOptionPane.showMessageDialog(null, "Please Enter Customer ID");
        }else if(txt_cusName.equals("Select")){
            JOptionPane.showMessageDialog(null, "Please Select Customer Name");
        }else if(txt_cusEmail.equals("")){
            JOptionPane.showMessageDialog(null, "Please Enter Email");
        }
        else if(txt_bidvalue.equals("")){
            JOptionPane.showMessageDialog(null, "Please Enter Bid Value");
        }
        else{
            input2();
        }
    }
    public void input2(){
        try{ String query = "INSERT INTO `biditem2`(`cus_natiID`, `name`, `email`, `bidvalue`) VALUES (?,?,?,?)"; 
           pst = con.prepareStatement(query);
           pst.setString(1,txt_cusID.getText());
           pst.setString(2,txt_cusName.getText());
           pst.setString(3,txt_cusEmail.getText());
           pst.setString(4,txt_bidvalue.getText());
           pst.executeUpdate();
             JOptionPane.showMessageDialog(null, "Bidded For Item 2 Successfully");
         }catch(Exception ex){
             JOptionPane.showMessageDialog(null, ex);
         }
    }
    public void validateInputitem3(){
                if(txt_cusID.equals("")){
            JOptionPane.showMessageDialog(null, "Please Enter Customer ID");
        }else if(txt_cusName.equals("Select")){
            JOptionPane.showMessageDialog(null, "Please Select Customer Name");
        }else if(txt_cusEmail.equals("")){
            JOptionPane.showMessageDialog(null, "Please Enter Email");
        }
        else if(txt_bidvalue.equals("")){
            JOptionPane.showMessageDialog(null, "Please Enter Bid Value");
        }
        else{
            input3();
        }
    }
    public void input3(){
        try{ String query = "INSERT INTO `biditem3`(`cus_natiID`, `name`, `email`, `bidvalue`) VALUES (?,?,?,?)"; 
           pst = con.prepareStatement(query);
           pst.setString(1,txt_cusID.getText());
           pst.setString(2,txt_cusName.getText());
           pst.setString(3,txt_cusEmail.getText());
           pst.setString(4,txt_bidvalue.getText());
           pst.executeUpdate();
             JOptionPane.showMessageDialog(null, "Bidded For Item 3 Successfully");
         }catch(Exception ex){
             JOptionPane.showMessageDialog(null, ex);
         }
    }
    public void delete1(){
        try{ String query = "DELETE FROM `biditem1`WHERE bidvalue =?"; 
           pst = con.prepareStatement(query);
           pst.setString(1,txt_bidvalue.getText());
           pst.executeUpdate();
             
         }catch(SQLException | HeadlessException ex){
             JOptionPane.showMessageDialog(null, ex);
         }
    }
     public void delete2(){
        try{ String query = "DELETE FROM `biditem2`WHERE bidvalue =?"; 
           pst = con.prepareStatement(query);
           pst.setString(1,txt_bidvalue.getText());
           pst.executeUpdate();
             
         }catch(SQLException | HeadlessException ex){
             JOptionPane.showMessageDialog(null, ex);
         }
    }
     public void delete3(){
        try{ String query = "DELETE FROM `biditem3`WHERE bidvalue =?"; 
           pst = con.prepareStatement(query);
           pst.setString(1,txt_bidvalue.getText());
           pst.executeUpdate();
             
         }catch(SQLException | HeadlessException ex){
             JOptionPane.showMessageDialog(null, ex);
         }
    }
    public void clear(){
        txt_bidvalue.setText("");
        txt_cusEmail.setText("");
        txt_cusID.setText("");
        txt_cusName.setText("");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_admin = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        item_one_name = new javax.swing.JLabel();
        item1_time = new javax.swing.JLabel();
        item1_id = new javax.swing.JLabel();
        item_one_type = new javax.swing.JLabel();
        itemID1 = new javax.swing.JLabel();
        itemType1 = new javax.swing.JLabel();
        itemName1 = new javax.swing.JLabel();
        item_one_big_value = new javax.swing.JLabel();
        itemBidvalue1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        item2_time = new javax.swing.JLabel();
        item2_id = new javax.swing.JLabel();
        item_two_type = new javax.swing.JLabel();
        item_two_name = new javax.swing.JLabel();
        item_two_big_value = new javax.swing.JLabel();
        itemBidvalue2 = new javax.swing.JLabel();
        itemName2 = new javax.swing.JLabel();
        itemType2 = new javax.swing.JLabel();
        itemID2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        item3_time = new javax.swing.JLabel();
        item3_id = new javax.swing.JLabel();
        item_three_type = new javax.swing.JLabel();
        item_three_name = new javax.swing.JLabel();
        item_three_big_value = new javax.swing.JLabel();
        itemBidvalue3 = new javax.swing.JLabel();
        itemName3 = new javax.swing.JLabel();
        itemType3 = new javax.swing.JLabel();
        itemID3 = new javax.swing.JLabel();
        lbl_bidvalue = new javax.swing.JLabel();
        lbl_cusID = new javax.swing.JLabel();
        lbl_cusName = new javax.swing.JLabel();
        lbl_cusEmail = new javax.swing.JLabel();
        btn_bid3 = new javax.swing.JButton();
        txt_bidvalue = new javax.swing.JTextField();
        txt_cusID = new javax.swing.JTextField();
        txt_cusName = new javax.swing.JTextField();
        txt_cusEmail = new javax.swing.JTextField();
        btn_bit1 = new javax.swing.JButton();
        btn_bit2 = new javax.swing.JButton();
        lbl_auctionsytem = new javax.swing.JLabel();
        btn_bitOneDelete = new javax.swing.JButton();
        btn_bitTwoDelete = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        btn_bidThreeDelete1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_min = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbl_sec = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1366, 748));

        btn_admin.setText("Admin");
        btn_admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_adminActionPerformed(evt);
            }
        });

        item_one_name.setText("Item Name");

        item1_time.setText("Item1 ");

        item1_id.setText("Item One ID");

        item_one_type.setText("Item Type");

        itemID1.setText("1");

        itemType1.setText("xxxxx");

        itemName1.setText("xxxxx");

        item_one_big_value.setText("Current Bid Value($)");

        itemBidvalue1.setText("xxxxx");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(item_one_type, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(item1_id, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(item_one_name, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(item_one_big_value, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(itemType1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemID1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemName1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemBidvalue1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(item1_time, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(item1_time, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(item1_id, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(item_one_type, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(item_one_name, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(itemID1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(itemType1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(itemName1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(item_one_big_value, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemBidvalue1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        item2_time.setText("Item 2 ");

        item2_id.setText("Item One ID");

        item_two_type.setText("Item Type");

        item_two_name.setText("Item Name");

        item_two_big_value.setText("Current Bid Value($)");

        itemBidvalue2.setText("xxxxx");

        itemName2.setText("xxxxx");

        itemType2.setText("xxxxx");

        itemID2.setText("2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(item_two_type, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(item2_id, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(item_two_name, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(item_two_big_value, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(itemType2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemID2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemName2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemBidvalue2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(item2_time, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(item2_time, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(item2_id, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(item_two_type, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(item_two_name, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(itemID2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(itemType2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(itemName2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(item_two_big_value, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemBidvalue2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        item3_time.setText("Item 3 ");

        item3_id.setText("Item One ID");

        item_three_type.setText("Item Type");

        item_three_name.setText("Item Name");

        item_three_big_value.setText("Current Bid Value($)");

        itemBidvalue3.setText("xxxxx");

        itemName3.setText("xxxxx");

        itemType3.setText("xxxxx");

        itemID3.setText("3");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(item_three_type, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(item3_id, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(item_three_name, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(item_three_big_value, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(itemType3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemID3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemName3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemBidvalue3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(item3_time, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(item3_time, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(item3_id, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(item_three_type, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(item_three_name, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(itemID3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(itemType3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(itemName3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(item_three_big_value, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemBidvalue3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53))
        );

        lbl_bidvalue.setText("Bid Value");

        lbl_cusID.setText("Customer National ID");

        lbl_cusName.setText("Customer Name");

        lbl_cusEmail.setText("Customer E mail");

        btn_bid3.setText("BID to Item Three");
        btn_bid3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bid3ActionPerformed(evt);
            }
        });

        btn_bit1.setText("BID to Item One");
        btn_bit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bit1ActionPerformed(evt);
            }
        });

        btn_bit2.setText("BID to Item Two");
        btn_bit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bit2ActionPerformed(evt);
            }
        });

        lbl_auctionsytem.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lbl_auctionsytem.setText("Auction System");

        btn_bitOneDelete.setText("Delete Bid");
        btn_bitOneDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bitOneDeleteActionPerformed(evt);
            }
        });

        btn_bitTwoDelete.setText("Delete Bid");
        btn_bitTwoDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bitTwoDeleteActionPerformed(evt);
            }
        });

        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        btn_bidThreeDelete1.setText("Delete Bid");
        btn_bidThreeDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bidThreeDelete1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Time : 30 Min");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Timer:");

        lbl_min.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_min.setText("00");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Min");

        lbl_sec.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_sec.setText("00");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setText("Sec");

        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("!!WARNING!!");

        jLabel5.setForeground(new java.awt.Color(204, 0, 0));
        jLabel5.setText("ONLY IF YOU ADD MAX BID VALUE AT THE TIME");

        jLabel7.setForeground(new java.awt.Color(204, 0, 0));
        jLabel7.setText("YOU CAN DELETE BID VALUE,");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_admin, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_min)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_sec)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(148, 148, 148)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel5)))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_cusName, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_cusID, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_bidvalue, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_cusEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_bidvalue, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_cusID, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_cusEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_cusName, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(62, 88, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_bitOneDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_bit1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(364, 364, 364)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_bitTwoDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_bit2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_bid3, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_bidThreeDelete1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(89, 89, 89))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(539, Short.MAX_VALUE)
                    .addComponent(lbl_auctionsytem)
                    .addContainerGap(539, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_admin, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lbl_min)
                            .addComponent(jLabel4)
                            .addComponent(lbl_sec)
                            .addComponent(jLabel6))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lbl_cusID, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_cusID, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lbl_cusName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_cusName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbl_cusEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_cusEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_bidvalue, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_bidvalue, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_bitOneDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_bitTwoDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_bit1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_bit2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(53, 53, 53))))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_bid3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_bidThreeDelete1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(25, Short.MAX_VALUE)
                    .addComponent(lbl_auctionsytem)
                    .addContainerGap(661, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_adminActionPerformed
        AdminSignIn cusm = new AdminSignIn(this,true);
        cusm.setVisible(true);
        
    }//GEN-LAST:event_btn_adminActionPerformed

    private void btn_bit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bit1ActionPerformed
       
        
       
        double bidValue = Double.parseDouble(txt_bidvalue.getText());
        
        if(bidValue>item1Max){
            id1 = txt_cusID.getText();
            
        }
        
        if(obj1.checkValue(bidValue)== false){
            obj1.insert(bidValue);
            validateInputitem1();
        }
        else{
            JOptionPane.showMessageDialog(null,"Insert not equal value to current bid");
        }
        showValues();
        System.out.println("----Item 01----");
        System.out.println("");
        obj1.print();
        
   
    }//GEN-LAST:event_btn_bit1ActionPerformed

    private void btn_bit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bit2ActionPerformed
        
        double bidValue = Double.parseDouble(txt_bidvalue.getText());
        
        if(bidValue>item1Max){
            id1 = txt_cusID.getText();
            
        }
        
        if(obj2.checkValue(bidValue)== false){
            obj2.insert(bidValue);
            validateInputitem2();
        }
        else{
            JOptionPane.showMessageDialog(null,"Insert not equal value to current bid");
        }
        showValues();
        System.out.println("----Item 02----");
        System.out.println("");
        obj2.print();
        
        
    }//GEN-LAST:event_btn_bit2ActionPerformed

    private void btn_bid3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bid3ActionPerformed
        
         double bidValue = Double.parseDouble(txt_bidvalue.getText());
        
        if(bidValue>item1Max){
            id1 = txt_cusID.getText();
            
        }
        
        if(obj3.checkValue(bidValue)== false){
            obj3.insert(bidValue);
            validateInputitem3();
        }
        else{
            JOptionPane.showMessageDialog(null,"Insert not equal value to current bid");
        }
        showValues();
        System.out.println("----Item 03----");
        System.out.println("");
        obj3.print();
       
    }//GEN-LAST:event_btn_bid3ActionPerformed

    private void btn_bitOneDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bitOneDeleteActionPerformed
        
        
        if((Integer.parseInt(txt_bidvalue.getText())== item1Max)){
           
            checkData1();
            JOptionPane.showMessageDialog(null,"Succesfully removed");
            showValues();
            System.out.println("----Item 01----");
            obj1.print();
            
            clear();
        }
        else{
            JOptionPane.showMessageDialog(null,"Can't delete");
            
        }
        
    }//GEN-LAST:event_btn_bitOneDeleteActionPerformed

    private void btn_bitTwoDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bitTwoDeleteActionPerformed
        // TODO add your handling code here:
        if(Integer.parseInt(txt_bidvalue.getText())== item2Max){
            checkData2();
            JOptionPane.showMessageDialog(null,"Succesfully removed");
            showValues();
            System.out.println("----Item 02----");
            obj2.print();
           
            clear();
        }
        else{
            JOptionPane.showMessageDialog(null,"Can't delete");
        }
    }//GEN-LAST:event_btn_bitTwoDeleteActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_bidThreeDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bidThreeDelete1ActionPerformed
        // TODO add your handling code here:
        if(Integer.parseInt(txt_bidvalue.getText())== item3Max){
            checkData3();
            JOptionPane.showMessageDialog(null,"Succesfully removed");
            showValues();
            System.out.println("----Item 03----");
            obj3.print();
            
            clear();
        }
        else{
            JOptionPane.showMessageDialog(null,"Can't delete");
        }
        
    }//GEN-LAST:event_btn_bidThreeDelete1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_admin;
    private javax.swing.JButton btn_bid3;
    private javax.swing.JButton btn_bidThreeDelete1;
    private javax.swing.JButton btn_bit1;
    private javax.swing.JButton btn_bit2;
    private javax.swing.JButton btn_bitOneDelete;
    private javax.swing.JButton btn_bitTwoDelete;
    private javax.swing.JButton btn_clear;
    private javax.swing.JLabel item1_id;
    private javax.swing.JLabel item1_time;
    private javax.swing.JLabel item2_id;
    private javax.swing.JLabel item2_time;
    private javax.swing.JLabel item3_id;
    private javax.swing.JLabel item3_time;
    private javax.swing.JLabel itemBidvalue1;
    private javax.swing.JLabel itemBidvalue2;
    private javax.swing.JLabel itemBidvalue3;
    private javax.swing.JLabel itemID1;
    private javax.swing.JLabel itemID2;
    private javax.swing.JLabel itemID3;
    private javax.swing.JLabel itemName1;
    private javax.swing.JLabel itemName2;
    private javax.swing.JLabel itemName3;
    private javax.swing.JLabel itemType1;
    private javax.swing.JLabel itemType2;
    private javax.swing.JLabel itemType3;
    private javax.swing.JLabel item_one_big_value;
    private javax.swing.JLabel item_one_name;
    private javax.swing.JLabel item_one_type;
    private javax.swing.JLabel item_three_big_value;
    private javax.swing.JLabel item_three_name;
    private javax.swing.JLabel item_three_type;
    private javax.swing.JLabel item_two_big_value;
    private javax.swing.JLabel item_two_name;
    private javax.swing.JLabel item_two_type;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbl_auctionsytem;
    private javax.swing.JLabel lbl_bidvalue;
    private javax.swing.JLabel lbl_cusEmail;
    private javax.swing.JLabel lbl_cusID;
    private javax.swing.JLabel lbl_cusName;
    private javax.swing.JLabel lbl_min;
    private javax.swing.JLabel lbl_sec;
    private javax.swing.JTextField txt_bidvalue;
    private javax.swing.JTextField txt_cusEmail;
    private javax.swing.JTextField txt_cusID;
    private javax.swing.JTextField txt_cusName;
    // End of variables declaration//GEN-END:variables
}
