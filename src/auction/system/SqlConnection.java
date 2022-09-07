
package auction.system;

import java.sql.Connection;
import java.sql.DriverManager;


public class SqlConnection {
 
    public static Connection getCon(){
        
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/auction","root","");
            return con;
            
        } catch (Exception e) {
            
            return null;
        }
    

}  
}
