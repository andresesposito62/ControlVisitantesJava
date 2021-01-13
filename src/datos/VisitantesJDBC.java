
package datos;
import domain.Visitante;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Andres-PC-Local
 */
public class VisitantesJDBC {
    //Nos apoyamos de la llave primaria autoincrementabe de MySql
    //por o que se omite el campo de persona_id
    //Se utiliza un preparestatement, por lo que podemos
    //utilizar parametros (signoss de ?)
    //los cuales posteriormente seran sustituidos por el parametro respectivo
    
    private java.sql.Connection userConn;
    
    
    private final String SQL_INSERT = "INSERT INTO visitantes"
            + "(idvisitante, nombres, apellidos, telefono, temperatura) VALUES(?,?,?,?,?)";
    
    private final String SQL_UPDATE = "UPDATE visitantes SET nombres=?, apellidos=?, telefono=?, temperatura=? WHERE idvisitante=?";
    
    private final String SQL_DELETE = "DELETE FROM visitantes WHERE idvisitante=?";
    
    private final String SQL_SELECT = "SELECT idvisitante, nombres, apellidos, telefono, temperatura "
            + "FROM visitantes ORDER BY temperatura";
    
    private final String SQL_SELECT_INDIVIDUAL = "SELECT idvisitante, nombres, apellidos, telefono, temperatura FROM visitantes "
            + "WHERE idvisitante=?";
    
    
    public VisitantesJDBC(){
        
    }
    
    public VisitantesJDBC(Connection conn){
        this.userConn = conn;
    }
    
    public int insert(int idvisitante, String nombres, String apellidos, String telefono, float temperatura) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;//no se utiliza en este ejercicio
        
        int rows = 0; //registros afectados
        try{
            conn = (this.userConn != null) ? this.userConn:
                    Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            int index = 1;//contador de columnas
            stmt.setInt(index++, idvisitante);//param 1=>?
            stmt.setString(index++, nombres);//param =>2
            stmt.setString(index++, apellidos);//param =>2
            stmt.setString(index++, telefono);//param =>2
            stmt.setFloat(index++, temperatura);//param =>2
            System.out.println("Ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();//no. resgtros afectados
            System.out.println("Registros afectados:" + rows);
        }finally{
            Conexion.close(stmt);
            if(this.userConn == null){
                Conexion.close(conn);
            }            
        }
        return rows;
    }
    
    public int update(String nombres, String apellidos, String telefono, float temperatura, int idvisitante) throws SQLException{
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try{
            conn = (this.userConn != null) ? this.userConn:
                    Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            int index = 1;
            //stmt.setInt(index++, idvisitante);//param 1=>?
            stmt.setString(index++, nombres);//param =>2
            stmt.setString(index++, apellidos);//param =>2
            stmt.setString(index++, telefono);//param =>2
            stmt.setFloat(index++, temperatura);
            stmt.setInt(index, idvisitante);// no tiene index ++ porque no hay mas parametros del regstro para consultar
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizados:" + rows);
        }finally{
            Conexion.close(stmt);
            if(this.userConn == null){
                Conexion.close(conn);
            }            
        }
        return rows;
    }
    
    public int delete(int idvisitante) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try{
            conn = (this.userConn != null) ? this.userConn:
                    Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt( 1, idvisitante);
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        }finally{
            Conexion.close(stmt);
            if(this.userConn == null){
                Conexion.close(conn);
            }            
        }
        return rows;
    }
    
    public List<Visitante> select() throws SQLException{  //Retorna una lista con objetos de tipo visitante
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Visitante visitante = null;
        List<Visitante> visitantes = new ArrayList<Visitante>();
        try{
            conn = (this.userConn != null) ? this.userConn:
                    Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){// consulta si hay mas registros
                int idvisitante = rs.getInt(1);
                String nombres = rs.getString(2);
                String apellidos = rs.getString(3);
                String telefono = rs.getString(4);
                Float temperatura = rs.getFloat(5);
                visitante = new Visitante();
                visitante.setIdVisitante(idvisitante);
                visitante.setNombres(nombres);
                visitante.setApellidos(apellidos);
                visitante.setTelefono(telefono);
                visitante.setTemperatura(temperatura);
                visitantes.add(visitante);
            }
        }finally{
            Conexion.close(stmt);
            if(this.userConn == null){
                Conexion.close(conn);
            }            
        }
        return visitantes;
    }
    
    
    public List<Visitante> select(int idVisitante)throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Visitante visitante = null;
        List<Visitante> visitantes = new ArrayList<Visitante>();
        try{
            conn = (this.userConn != null) ? this.userConn:
                    Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_INDIVIDUAL);
            stmt.setInt( 1, idVisitante);
            rs = stmt.executeQuery();
            while(rs.next()){// consulta si hay mas registros
                int idvisitante = rs.getInt(1);
                String nombres = rs.getString(2);
                String apellidos = rs.getString(3);
                String telefono = rs.getString(4);
                Float temperatura = rs.getFloat(5);
                visitante = new Visitante();
                visitante.setIdVisitante(idvisitante);
                visitante.setNombres(nombres);
                visitante.setApellidos(apellidos);
                visitante.setTelefono(telefono);
                visitante.setTemperatura(temperatura);
                visitantes.add(visitante);
            }
        }finally{
            Conexion.close(stmt);
            if(this.userConn == null){
                Conexion.close(conn);
            }            
        }
        return visitantes;    
    }
}
