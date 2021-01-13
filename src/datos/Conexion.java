
package datos;
import java.sql.*;

/**
 *
 * @author Andres-PC-Local
 */
public class Conexion {
    
    private static String JDBC_STRING = "com.mysql.jdbc.Driver";//JDBC_DRIVER
    private static String JDBC_URL = "jdbc:mysql://127.0.0.1/basededatosvisitantes?useSSL=false";
    private static String JDBC_USER = "root";
    private static String JDBC_PASS = "admin";
    private static Driver driver = null;    
    
    public static synchronized Connection getConnection() throws SQLException{
        
        if(driver == null){
            try{
               Class jdbcDriverClass = Class.forName(JDBC_STRING);
               driver = (Driver) jdbcDriverClass.newInstance(); 
               DriverManager.registerDriver(driver);
            }catch(Exception e){
               System.out.println("Fallo en cargar el driver JDBC");
               e.printStackTrace();
            }
        }
        return DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASS);
    }    

    public static void close(ResultSet rs){
        try{
            if(rs != null){
                rs.close();
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }
    
    public static void close(PreparedStatement ps){//antes stmt
        try{
            if(ps != null){
                ps.close();
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }
    
    //cierre de conexion
    public static void close(Connection c){//antes conn
        try{
            if(c != null){
                c.close();
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }    
    
}
