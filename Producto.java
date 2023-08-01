package super_la_familia;

import java.util.Objects;  // usamos este importe para poder traslados los objetos 

class Producto {
    public int id;
    public String nombre;
    public String categoria;
    public double precio;
    public int existencias;
    public String fechaCaducidad;
    public int pasillo;
    public int posicion;

    public Producto(int id, String nombre, String categoria, double precio, int existencias, String fechaCaducidad) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.existencias = existencias;
        this.fechaCaducidad = fechaCaducidad;
        this.pasillo = 0;
        this.posicion = 0;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public int getExistencias() {
        return existencias;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public int getPasillo() {
        return pasillo;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public void setPasillo(int pasillo) {
        this.pasillo = pasillo;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}

//MUCHAS GRACIAS EQUIPO 