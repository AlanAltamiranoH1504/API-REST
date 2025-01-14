package altamirano.hernandez.api_restful.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Size(min = 3, max = 60)
    private String nombre;
    @Positive
    private double precio;
    @NotBlank
    @Size(min = 5, max = 90)
    private String descripcion;

    //Constructores
    public Producto(){}
    public Producto(String nombre, double precio, String descripcion){
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    //Metodos GET y SET
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
