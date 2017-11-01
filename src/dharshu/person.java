package dharshu;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author priya dharshini
 */
public class person {
    String name;
    int phoneno;
    String email;
    Connection con;
    ResultSet rs;
 Statement stt;
    person() throws SQLException
    {
    
    con=DriverManager.getConnection("jdbc:mysql://localhost/addressbook","root","root");
       
        stt=con.createStatement();
       // rs=stt.executeQuery("select * from addressbook");
       // JOptionPane.showMessageDialog(null,rs.getString(1));
    
    
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(int phoneno) {
        this.phoneno = phoneno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
