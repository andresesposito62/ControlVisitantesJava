
package domain;

/**
 *
 * @author Andres-PC-Local
 */
public class Visitante {
    private int idVisitante;
    private String nombres, apellidos, telefono;
    private Float temperatura;

    /**
     * @return the idVisitante
     */
    public int getIdVisitante() {
        return idVisitante;
    }

    /**
     * @param idVisitante the idVisitante to set
     */
    public void setIdVisitante(int idVisitante) {
        this.idVisitante = idVisitante;
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the temperatura
     */
    public Float getTemperatura() {
        return temperatura;
    }

    /**
     * @param temperatura the temperatura to set
     */
    public void setTemperatura(Float temperatura) {
        this.temperatura = temperatura;
    }

    @Override
    public String toString() {
        return "Identificacion=" + idVisitante + ", nombres=" + nombres + ", apellidos=" + apellidos + ", telefono=" + telefono + ", temperatura=" + temperatura;
    }
    
    
}
