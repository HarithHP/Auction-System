/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class ViewAuction extends javax.swing.JFrame {
    Connection con = SqlConnection.getCon();
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form ViewAuction
     */
    public ViewAuction() {
        
        initComponents();
    }
    private void fetchItem(){
       
     
       String tc = combo_itemid.getSelectedItem().toString();
       
       try{
           String query = "SELECT * FROM `item` WHERE itemID ="+tc+""; 
           //con = DriverManager.getConnection("jjdbc:mysql://localhost:3306/auction?autoReconnec=true&useSSl=false","root","");
           pst = con.prepareStatement(query);
           rs =pst.executeQuery();
           if(rs.next()){
                
                String itemType=rs.getString("itemType");
                String itemName=rs.getString("itemName");
               
                txt_ItemName.setText(itemType); 
                txt_itemType.setText(itemName);
                
           }           
       }catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);  
       }
    }
    private void fetchbid1(){
          
       
       try{
           //SELECT MAX(bidvalue) AS LargestPrice FROM biditem1
           String query = "SELECT * FROM biditem1 WHERE bidvalue = (select max( bidvalue) from biditem1)"; 
           //con = DriverManager.getConnection("jdbc:mysql://localhost/harith","root","");
           pst = con.prepareStatement(query);
           rs =pst.executeQuery();
           if(rs.next()){
                
                String bidvalue=rs.getString("bidvalue");                    
                txt_bidvalue.setText(bidvalue);               
           }           
       }catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);  
       }
    }
    private void fetchbid2(){
          
       
       try{
           //SELECT MAX(bidvalue) AS LargestPrice FROM biditem1
           String query = "SELECT * FROM biditem2 WHERE bidvalue = (select max( bidvalue) from biditem2)"; 
           //con = DriverManager.getConnection("jdbc:mysql://localhost/harith","root","");
           pst = con.prepareStatement(query);
           rs =pst.executeQuery();
           if(rs.next()){
                
                String bidvalue=rs.getString("bidvalue");                    
                txt_bidvalue.setText(bidvalue);               
           }           
       }catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);  
       }
    }
    private void fetchbid3(){
          
       
       try{
           //SELECT MAX(bidvalue) AS LargestPrice FROM biditem1
           String query = "SELECT * FROM biditem3 WHERE bidvalue = (select max( bidvalue) from biditem3)"; 
           //con = DriverManager.getConnection("jdbc:mysql://localhost/harith","root","");
           pst = con.prepareStatement(query);
           rs =pst.executeQuery();
           if(rs.next()){
                
                String bidvalue=rs.getString("bidvalue");                    
                txt_bidvalue.setText(bidvalue);               
           }           
       }catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);  
       }
    }
    private void item1Winner1(){
       
     double d = Double.valueOf(txt_bidvalue.getText());
       //String tc = txt_bidvalue.getSelectedText();
       
       try{
           String query = "SELECT * FROM `biditem1` WHERE bidvalue ="+d+""; 
           //con = DriverManager.getConnection("jdbc:mysql://localhost/harith","root","");
           pst = con.prepareStatement(query);
           rs =pst.executeQuery();
           if(rs.next()){
                
                String cus_natiID=rs.getString("cus_natiID");
                String name=rs.getString("name");
                String email=rs.getString("email");
               
                cusID.setText(cus_natiID); 
                cusName.setText(name);
                cusEmail.setText(email);
                
                
           }           
       }catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);  
       }
    }
    private void item1Winner2(){
       
     double d = Double.valueOf(txt_bidvalue.getText());
       //String tc = txt_bidvalue.getSelectedText();
       
       try{
           String query = "SELECT * FROM `biditem2` WHERE bidvalue ="+d+""; 
           //con = DriverManager.getConnection("jdbc:mysql://localhost/harith","root","");
           pst = con.prepareStatement(query);
           rs =pst.executeQuery();
           if(rs.next()){
                
                String cus_natiID=rs.getString("cus_natiID");
                String name=rs.getString("name");
                String email=rs.getString("email");
               
                cusID.setText(cus_natiID); 
                cusName.setText(name);
                cusEmail.setText(email);
                
                
           }           
       }catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);  
       }
    }
    private void item1Winner3(){
    double d = Double.valueOf(txt_bidvalue.getText());
       //String tc = txt_bidvalue.getSelectedText();
       
       try{
           String query = "SELECT * FROM `biditem3` WHERE bidvalue ="+d+""; 
           //con = DriverManager.getConnection("jdbc:mysql://localhost/harith","root","");
           pst = con.prepareStatement(query);
           rs =pst.executeQuery();
           if(rs.next()){
                
                String cus_natiID=rs.getString("cus_natiID");
                String name=rs.getString("name");
                String email=rs.getString("email");
               
                cusID.setText(cus_natiID); 
                cusName.setText(name);
                cusEmail.setText(email);
                
                
           }           
       }catch(Exception ex){
         JOptionPane.showMessageDialog(null, ex);  
       }
    }
    public void inputwinner(){
        try{ String query = "INSERT INTO `winner`(`item_id`, `item_name`, `cus_natiID`, `cus_name`, `email`, `bidvalue`) VALUES(?,?,?,?,?,?)"; 
           pst = con.prepareStatement(query);
           pst.setString(1,combo_itemid.getSelectedItem().toString());
           pst.setString(2,txt_ItemName.getText());
           pst.setString(3,cusID.getText());
           pst.setString(4,cusName.getText());
           pst.setString(5,cusEmail.getText());
           pst.setString(6,txt_bidvalue.getText());
           pst.executeUpdate();
             JOptionPane.showMessageDialog(null, "Item Added Successfully");
         }catch(Exception ex){
             JOptionPane.showMessageDialog(null, ex);
         }
    }
    public void Clear(){
       txt_ItemName.setText("");
       txt_itemType.setText("");
       //combo_itemid.setSelectedIndex(0);
       txt_bidvalue.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_auctionTitle = new javax.swing.JLabel();
        item_id = new javax.swing.JLabel();
        item_type = new javax.swing.JLabel();
        txt_itemType = new javax.swing.JTextField();
        item_name = new javax.swing.JLabel();
        txt_ItemName = new javax.swing.JTextField();
        lbl_bidValue = new javax.swing.JLabel();
        txt_bidvalue = new javax.swing.JTextField();
        btn_clear = new javax.swing.JButton();
        btn_selectwinner = new javax.swing.JButton();
        lbl_cusID = new javax.swing.JLabel();
        cusName = new javax.swing.JLabel();
        lbl_cusEmail = new javax.swing.JLabel();
        cusID = new javax.swing.JLabel();
        lbl_cusName = new javax.swing.JLabel();
        cusEmail = new javax.swing.JLabel();
        lbl_Winner = new javax.swing.JLabel();
        combo_itemid = new javax.swing.JComboBox<>();
        btn_savewinner = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();
        btn_email = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1024, 540));

        lbl_auctionTitle.setText("VIEW AUCTION");

        item_id.setText("Item ID");

        item_type.setText("Item Type");

        item_name.setText("Item Name");

        lbl_bidValue.setText("Max Bid");

        btn_clear.setText("CLEAR");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        btn_selectwinner.setText("Select Winner");
        btn_selectwinner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_selectwinnerActionPerformed(evt);
            }
        });

        lbl_cusID.setText("Customer National ID");

        cusName.setText("Customer Name");

        lbl_cusEmail.setText("Customer E mail");

        cusID.setText("Customer National ID");

        lbl_cusName.setText("Customer Name");

        cusEmail.setText("Customer E mail");

        lbl_Winner.setText("Winner Details");

        combo_itemid.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Item ID", "1", "2", "3", " " }));
        combo_itemid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_itemidActionPerformed(evt);
            }
        });

        btn_savewinner.setText("Save Winner");
        btn_savewinner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_savewinnerActionPerformed(evt);
            }
        });

        btn_back.setText("Back");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        btn_email.setText("Send Email");
        btn_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_emailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(405, 405, 405)
                        .addComponent(lbl_Winner, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_savewinner, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbl_cusID, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cusID, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(79, 79, 79)
                                        .addComponent(lbl_cusName, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cusName, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(131, 131, 131)
                                .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_selectwinner, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(item_id, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(combo_itemid, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(item_type, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txt_itemType, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(item_name, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txt_ItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lbl_bidValue, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txt_bidvalue, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(74, 74, 74)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_cusEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cusEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_email, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(493, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(412, 412, 412)
                .addComponent(lbl_auctionTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_auctionTitle)
                    .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(item_id, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_itemid, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(item_type, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_itemType, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(item_name, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_bidValue, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_bidvalue, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_savewinner, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_selectwinner, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_email, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbl_Winner, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cusID, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_cusName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cusEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_cusID, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_cusEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cusName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        Clear();
    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_selectwinnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_selectwinnerActionPerformed
        int tc = combo_itemid.getSelectedIndex();
       if(tc == 1){
           item1Winner1();
       }else if(tc==2){
          item1Winner2();
       }
       else if(tc==3){
           item1Winner3();
       }
       else{
           System.out.println("fff");
       }
    }//GEN-LAST:event_btn_selectwinnerActionPerformed

    private void combo_itemidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_itemidActionPerformed
       fetchItem();
       int tc = combo_itemid.getSelectedIndex();
       if(tc == 1){
           fetchbid1();
       }else if(tc==2){
           fetchbid2();
       }
       else if(tc==3){
           fetchbid3();
       }
       
       
    }//GEN-LAST:event_combo_itemidActionPerformed

    private void btn_savewinnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_savewinnerActionPerformed
        inputwinner();
        Clear();
        cusEmail.setText("");
        cusID.setText("");
        cusName.setText("");
    }//GEN-LAST:event_btn_savewinnerActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Admin newAdmin = new Admin();
        newAdmin.setVisible(true);
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_emailActionPerformed
        // TODO add your handling code here:
        String from="quizmanagementsystem43@gmail.com";
        String to = cusEmail.getText();
        String host = "localhost";
        String subject = "Auction System mail client";
        String Content = "!!Congratulations!! You have won" +txt_ItemName.getText()+" at the Auction.Please contact the seller for more info";
        
        Properties p = new Properties();
        p.put("mail.smtp.auth", "true");
         p.put("mail.smtp.starttls.enable", "true");
          p.put("mail.smtp.host", "smtp.gmail.com");
           p.put("mail.smtp.port", "587");
           
           Session s = Session.getDefaultInstance(p, new javax.mail.Authenticator(){
               
               protected PasswordAuthentication getPasswordAuthentication(){
                   return new PasswordAuthentication("quizmanagementsystem43@gmail.com","1a23456!");
               }
           });
           try{
               MimeMessage m = new MimeMessage(s);
               m.setFrom(from);
               m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
               m.setSubject(subject);
               m.setText(Content);
               Transport.send(m);
               System.out.println("Email sent");
               
           }catch(Exception e){
               e.printStackTrace();
           }
    }//GEN-LAST:event_btn_emailActionPerformed

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
            java.util.logging.Logger.getLogger(ViewAuction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewAuction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewAuction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewAuction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewAuction().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_email;
    private javax.swing.JButton btn_savewinner;
    private javax.swing.JButton btn_selectwinner;
    private javax.swing.JComboBox<String> combo_itemid;
    private javax.swing.JLabel cusEmail;
    private javax.swing.JLabel cusID;
    private javax.swing.JLabel cusName;
    private javax.swing.JLabel item_id;
    private javax.swing.JLabel item_name;
    private javax.swing.JLabel item_type;
    private javax.swing.JLabel lbl_Winner;
    private javax.swing.JLabel lbl_auctionTitle;
    private javax.swing.JLabel lbl_bidValue;
    private javax.swing.JLabel lbl_cusEmail;
    private javax.swing.JLabel lbl_cusID;
    private javax.swing.JLabel lbl_cusName;
    private javax.swing.JTextField txt_ItemName;
    private javax.swing.JTextField txt_bidvalue;
    private javax.swing.JTextField txt_itemType;
    // End of variables declaration//GEN-END:variables
}
