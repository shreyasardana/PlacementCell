/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package placementcell;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author dell
 */
public class myconnection {
    Connection db;
    public myconnection()
    {
        try
        {
            db=DriverManager.getConnection("jdbc:mysql://localhost/placementcell","root","");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
            
    
}
