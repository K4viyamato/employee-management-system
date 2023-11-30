/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techcompany;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author kavindu
 */
public class database {
    
    public static Connection connectDb(){
    
        try{
  
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/employee","root","1234");
            return connect;
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return null;
    
    
    }
    
}
