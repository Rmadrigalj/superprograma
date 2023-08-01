package super_la_familia;

import java.util.ArrayList;
import java.util.List;
// metodos vistos en clase01 02 03 explicar la parte inicial al grupo
public class Compra {
    
    private String nombreCliente;
    private int cedulaCliente;
    private List<Producto> productos;
    private double totalPagar;

    public Compra(String nombreCliente, int cedulaCliente) {
        this.nombreCliente = nombreCliente;
        this.cedulaCliente = cedulaCliente;
        this.productos = new ArrayList<>();
        this.totalPagar = 0.0;
    }


    public void agregarProducto(Producto producto) {
        productos.add(producto);
        totalPagar += producto.getPrecio();
    }

    public void removerProducto(Producto producto) {
        productos.remove(producto);
        totalPagar -= producto.getPrecio();
    }

    //explicar al profe que estos son los getters y setters de la clase 06
    public String getNombreCliente() {
        return nombreCliente;
    }
    
    public void setNombreCliente(String nombreCliente) {// equipo todas estas son funciones para que las terminen 
        this.nombreCliente = nombreCliente;
    }

    public int getCedulaCliente() {//creen una con get para la cedula 
        return cedulaCliente;
    }
    
    public void setCedulaCliente(int cedulaCliente) {//set cedula
        this.cedulaCliente = cedulaCliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public double getTotalPagar() {
        return totalPagar;
    }
    
    @Override // usamos override mencion del profe primeras clases , usamos esta clase pra sobreescribir el metodo que ya existe 
    public String toString() {//con esto nos aseguramos de no usar un metodo nuevo sino el mismo de compra
        return "Compra{" +
                "nombreCliente='" + nombreCliente + '\'' +
                ", cedulaCliente=" + cedulaCliente +
                ", productos=" + productos +
                ", totalPagar=" + totalPagar +
                '}';
    }
}


//MUCHAS GRACIAS EQUIPO