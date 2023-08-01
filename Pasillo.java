package super_la_familia;

public class Pasillo {
 
    private static final int MAX_PRODUCTOS_PASILLO = 100; //equipo meti 100 productos maximo pero se puede cambiar
    public Producto[] productos;
    private int numProductos;

    public Pasillo() {
        productos = new Producto[MAX_PRODUCTOS_PASILLO];// gente recuerden inicamos en 0 para poder darle valor inicial
        numProductos = 0;
    }

    public void agregarProducto(Producto producto) {
        if (numProductos < MAX_PRODUCTOS_PASILLO) {
            productos[numProductos] = producto;
            numProductos++;
            System.out.println("Producto agregado correctamente.");
        } else {
            System.out.println("No se pueden agregar más productos, el pasillo está lleno.");
        }
    }

    public void retirarProducto(Producto producto) {
        boolean encontrado = false;
        int indice = -1;
        for (int i = 0; i < numProductos; i++) {
            if (productos[i].equals(producto)) {
                encontrado = true;
                indice = i;
                break;// pongan un break para que cancele cuando se cumpla el requerimento ****
            }
        }

        if (encontrado) {
     
            for (int i = indice; i < numProductos - 1; i++) {
                productos[i] = productos[i + 1];
            }
            productos[numProductos - 1] = null; // Eliminar referencia al último producto
            numProductos--;
            System.out.println("Producto retirado correctamente.");
        } else { 
            System.out.println("El producto no existe en el pasillo.");
        }
    }

  
    public Producto[] getProductos() {
        return productos;
    }

    public int getNumProductos() {
        return numProductos;
    }
}
//MUCHAS GRACIAS EQUIPO