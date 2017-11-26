package SQLiteBanco;

import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * @author Luana
 */
public class Connect {

    Connection conn = null;

    public static Connection ConnectDB(){

        try {

            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:"+new File(new File(".").getAbsolutePath()).getCanonicalPath()+"\\lib\\databaseMediaBox.sqlite");
            
            return conn;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return null;

    }

}
