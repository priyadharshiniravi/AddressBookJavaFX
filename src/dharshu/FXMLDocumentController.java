/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dharshu;


import com.mysql.jdbc.Field;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 *
 * @author priya dharshini
 */
public class FXMLDocumentController implements Initializable 
{
 @FXML
    private Label label;
    @FXML
    private TextField pername,perphone,permail,searchname;
    @FXML
    private Button add1,edit1,delete1;
    @FXML
    private ListView perlist;
    person p;
    //private TextField searchname;
    
    @FXML
    void addrec(ActionEvent ae) throws SQLException
    {
     p=new person();
     ResultSet rs;
     String n1,m1;
     String phone;
     
     int status=0;
     n1=pername.getText();
     
     phone=perphone.getText();
     BigDecimal p1 = new BigDecimal(phone);
     m1=permail.getText();
     
     String qry;
     qry="select * from addressbook where name='"+n1+"'";
     System.out.println(qry);
    
     rs=p.stt.executeQuery(qry);
     while(rs.next())
     {
       if(n1.equals(rs.getString("name")))
       {
           JOptionPane.showMessageDialog(null,"name alredy exits");
           status=1;
  //         clear();
       }
     }
     if(status==0)
     {
        qry="insert into addressbook values('"+n1+"',"+p1+",'"+m1+"')";
        System.out.println(qry);
        p.stt.execute(qry);
        
        perlist.getItems().add(n1);
        JOptionPane.showMessageDialog(null,"Record added sucessfully");
     }
     }
    @FXML
    void editrec()
    {
        try {
            String n1,m1;
            
            n1=pername.getText();
            String phone =perphone.getText();
            BigDecimal p1;
            p1 = new BigDecimal(phone);
            m1=permail.getText();
            String qry;
            String oldname;
            oldname=(String)perlist.getSelectionModel().getSelectedItem();
            qry = "update addressbook set name = '"+n1+"',phone_no="+p1+",mail_id='"+m1+"' where name ='"+ oldname+"'";
            System.out.println(qry);
            p.stt.executeUpdate(qry);
         perlist.getItems().clear();
            listitem();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(FXMLDocumentController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    @FXML
   void deleterec()
    {
      try {
         String n1,m1;
         int p1;
         n1 = pername.getText();
         String qry;
         //qry = "insert into ab values('"+n1+"',"+p1+",'"+m1+"')";
         qry="delete from addressbook where name= '"+n1+"'";
         //System.out.println(qry);
         p.stt.execute(qry);
         perlist.getItems().remove(n1);
         JOptionPane.showMessageDialog(null, "Record deleted successfully");
      } catch (SQLException ex) {
         java.util.logging.Logger.getLogger(FXMLDocumentController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
     }
    }
   @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
        System.out.println("You clicked me!");
         JOptionPane.showMessageDialog(null,event.getSource());
    }
   void listitem() //throws SQLException
{  
     try //throws SQLException
     {
         p = new person();
         p.rs = p.stt.executeQuery("Select * from addressbook");
         perlist.getItems().clear();
         while(p.rs.next())
         {
             perlist.getItems().add(p.rs.getString(1));
             JOptionPane.showMessageDialog(null, p.rs.getString(1));
         }
     } catch (SQLException ex) {
         Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
     }

}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
     
         //        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         listitem();
      //   p=new person();
         perlist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
             @Override
             public void changed(ObservableValue observable, Object oldValue, Object newValue){
                 try {
                     String n1;
                     pername.setText("");
                     perphone.setText("");
                     permail.setText("");
                     n1 = (String) perlist.getSelectionModel().getSelectedItem();
                     String qry;
                     qry="select * from addressbook where name='"+n1+"'";
                     p.rs = p.stt.executeQuery(qry);
                     p.rs.next();
                     pername.setText(p.rs.getString(1));
                     perphone.setText(String.valueOf(p.rs.getBigDecimal(2)));
                     permail.setText(p.rs.getString(3));
                     
                     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 } catch (SQLException ex) {
                   java.util.logging.Logger.getLogger(FXMLDocumentController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
             }
             }      
              
         }); 
    
  //  } //catch (SQLException ex) {
     //java.util.logging.Logger.getLogger(FXMLDocumentController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
   //}
     
  /*  searchname.textProperty().addListener(new ChangeListener<String>() {
         @Override
         public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
             throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         }
     });*/
    

searchname.textProperty().addListener(new ChangeListener<String>() {
ResultSet rs;
         @Override
         public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
             try {
                 //String n1;
                 //n1=null;
                String  n1 = searchname.getText();
                 String qry = "select * from addressbook where name like '%"+n1+"%'";
                 rs=p.stt.executeQuery(qry);
                 perlist.getItems().removeAll();
                 perlist.getItems().clear();
                 while(rs.next())
                 {
                     perlist.getItems().add(rs.getString(1));
                 }
             } catch (SQLException ex) {
                 Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         });

    }
void clear()
{
    pername.setText("");
    permail.setText("");
    perphone.setText("");
}
    }



    
