/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MbTrreTable.DAO;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author PJGT
 */
public class DAO {

    DataSource ds;

    public DAO() {
        try {
            Context cI = new InitialContext();
            ds = (DataSource) cI.lookup("java:comp/env/jdbc/__webSystem");
        } catch (NamingException ex) {
        }
    }
    
    
   
    
    
    
    
}
