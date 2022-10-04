package server;

public class Tarea {

    protected int numTarea;
    protected String estado;
    protected String descripcion;

    public Tarea (int numTarea, String estado, String descripcion) {

        this.numTarea = numTarea;
        this.descripcion = descripcion;
        this.estado = estado;

    }
}
