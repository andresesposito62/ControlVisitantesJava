
package controlvisitantes;
import datos.VisitantesJDBC;
import domain.Visitante;
import java.util.List;
import datos.Conexion;
import java.sql.*;

public class ControlVisitantes {


    public static void main(String[] args) {
        VisitantesJDBC visitantesJDBC = new VisitantesJDBC();
        
        //Creamos objeto de tipo conexion, se va a compartir para todos los querys que ejecutemos
        Connection conn = null;
        
        try{
            conn = Conexion.getConnection();
            // Revisamos si la conexion esta en modo autocommit
            //por defecto es autoommit==true
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            
            //Creamos el objeto VisitantesJDBC
            //proporcionamos la conexion creada
            VisitantesJDBC visitantes = new VisitantesJDBC(conn);
            
            //empezamos a ejecutar sentencias
            //recordar que una transaccion agrupa varias sentencias sql
            //Si algo falla no se realizan cambios en la BD
            //int identificacion =Integer.parseDouble("1121");
            //personas.insert(1121901786,"Andres Steven", "Esposito Ramirez", "3106002979", (float) 37.5);

            //visitantes.update("Andres Steven", "Esposito Ramirez", "3106002979", (float) 40.5, 1121901785);
    
            //int row = visitantes.select(12345678);
            
            //visitantes.update("Andres Steven", "Esposito Ramirez", "3106002979", (float) 40.5, 1121901785);
            //visitantes.insert(1121901785,"Karen stefany", "Esposito Ramirez", "315873737", (float) 36.5);
            //visitantes.delete(1121901785);
            
            /*
            List<Visitante> visitantes2 = visitantesJDBC.select(12345678);
            for(Visitante visitante : visitantes2){
                System.out.print(visitante);
                System.out.println("");
            } */           
            
            //Guardamos los cambios
            conn.commit();
        }catch(SQLException e){
            //hacemos rollback en caso de error
            try{
                System.out.println("Entramos al rollback");
                //imprimimos la excepcion a la consola
                e.printStackTrace();
                //Hacemos rollback
                conn.rollback();
            }catch(SQLException e1){
                e1.printStackTrace();
            }
        }
    }
}
