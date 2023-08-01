package super_la_familia;
// metodos vistos en clase01 02 03 explicar la parte inicial al grupo
import javax.swing.JOptionPane;

public class Supermercado {
    private static final int MAX_PRODUCTOS = 100;
    private static final int MAX_COMPRAS = 50;
    private static final int MAX_PRODUCTOS_PASILLO = 10;

    private static final Producto[] productos = new Producto[MAX_PRODUCTOS];
    private static final Pasillo[] pasillos = new Pasillo[10];
    private static final Compra[] compras = new Compra[MAX_COMPRAS];
    private static int totalProductos = 0;
    private static int totalCompras = 0;

    public static void main(String[] args) { // ruben se encarga del bucle
        int opcion;

        do {
            opcion = mostrarMenu();
            ejecutarOpcion(opcion);
        } while (opcion != 0);
    }
// ejecuto el meno y le realizo un parse visto en clase 03
    private static int mostrarMenu() {
        String menu = "¡Bienvenido al Supermercado La Prueba!\n\n" +
                "1. Registrar productos disponibles\n" +
                "2. Organizar producto en pasillo\n" +
                "3. Realizar compra de productos\n" +
                "4. Retirar un producto\n" +
                "5. Módulo de reportes\n" +
                "0. Salir\n\n" +
                "Elige una opción:";
        return Integer.parseInt(JOptionPane.showInputDialog(null, menu));
    }
// breaks visto en clase 04 pruebo funciones del break y aplican 
    private static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                registrarProductos();
                break;
            case 2:
                organizarProducto();
                break;
            case 3:
                realizarCompra();
                break;
            case 4:
                retirarProducto();
                break;
            case 5:
                mostrarReportes();
                break;
            case 0:
                JOptionPane.showMessageDialog(null, "Gracias por visitar Supermercado La Familia. ¡Regresa pronto!");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción inválida. Intenta de nuevo.");
                break;
        }
    }
// metodo privado visto en clase 02 el profe explica con detalle sobre el private
    private static void registrarProductos() {
        if (totalProductos >= MAX_PRODUCTOS) {
            JOptionPane.showMessageDialog(null, "Supermercado La Familia está lleno de productos. No puedes registrar más.");
            return;
        }

        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del producto:");
        String categoria = JOptionPane.showInputDialog(null, "Ingrese la categoría del producto:");
        double precio = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el precio del producto:"));
        int existencias = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese las existencias del producto:"));
        String fechaCaducidad = JOptionPane.showInputDialog(null, "Ingrese la fecha de caducidad del producto:");

        int id = generarIdUnico();

        Producto producto = new Producto(id, nombre, categoria, precio, existencias, fechaCaducidad);
        productos[totalProductos] = producto;
        totalProductos++;

        JOptionPane.showMessageDialog(null, "Producto registrado con éxito. ID del producto: " + id);
    }

    private static int generarIdUnico() {
        return totalProductos + 1;
    }

    private static void organizarProducto() {
        if (totalProductos == 0) {
            JOptionPane.showMessageDialog(null, "Supermercado La Familia está vacío. No hay productos para organizar.");
            return;
        }

        int idProducto = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del producto que desea organizar:"));
        int pasillo = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número del pasillo (1-10) donde desea organizar el producto:"));
        int posicion = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la posición (1-10) en el pasillo donde desea organizar el producto:"));

        if (pasillo < 1 || pasillo > 10 || posicion < 1 || posicion > 10) {
            JOptionPane.showMessageDialog(null, "La posición del pasillo y la posición deben estar entre 1 y 10.");
            return;
        }

        Producto producto = buscarProducto(idProducto);

        if (producto == null) {
            JOptionPane.showMessageDialog(null, "No se encontró ningún producto con el ID especificado.");
            return;
        }

        if (producto.getPasillo() != 0 || producto.getPosicion() != 0) {
            JOptionPane.showMessageDialog(null, "El producto ya está organizado en el pasillo " + producto.getPasillo() +
                    ", posición " + producto.getPosicion() + ".");
            return;
        }

        if (pasillos[pasillo - 1] == null) {
            pasillos[pasillo - 1] = new Pasillo();
        }

        if (pasillos[pasillo - 1].getProductosRegistrados() >= MAX_PRODUCTOS_PASILLO) {
            JOptionPane.showMessageDialog(null, "El pasillo " + pasillo + " está lleno. No se puede organizar más productos en él.");
            return;
        }

        pasillos[pasillo - 1].registrarProducto(producto, posicion);
        producto.setPasillo(pasillo);
        producto.setPosicion(posicion);

        JOptionPane.showMessageDialog(null, "Producto organizado en el pasillo " + pasillo + ", posición " + posicion + ".");
    }

    private static Producto buscarProducto(int idProducto) {
        for (int i = 0; i < totalProductos; i++) {
            if (productos[i].getId() == idProducto) {
                return productos[i];
            }
        }
        return null;
    }

    private static void realizarCompra() {//showinput dialog visto en clase 03 aprox para mostrar la forma del menu
        if (totalProductos == 0) {
            JOptionPane.showMessageDialog(null, "Supermercado La Familia está vacío. No hay productos para comprar.");
            return;
        }

        String nombreCliente = JOptionPane.showInputDialog(null, "Ingrese el nombre del cliente:");
        String cedulaCliente = JOptionPane.showInputDialog(null, "Ingrese la cédula del cliente:");
        int cantidadProductos = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de productos que desea comprar:"));

        if (cantidadProductos <= 0 || cantidadProductos > totalProductos) {
            JOptionPane.showMessageDialog(null, "La cantidad de productos debe ser mayor a cero y no puede ser mayor a la cantidad de productos disponibles.");
            return;
        }

        Producto[] productosCompra = new Producto[cantidadProductos];
        double montoTotal = 0;

        for (int i = 0; i < cantidadProductos; i++) {
            int idProducto = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del producto " + (i + 1) + ":"));

            Producto producto = buscarProducto(idProducto);

            if (producto == null) {
                JOptionPane.showMessageDialog(null, "No se encontró ningún producto con el ID especificado.");
                return;
            }

            if (producto.getExistencias() == 0) {
                JOptionPane.showMessageDialog(null, "El producto " + producto.getNombre() + " no tiene existencias. No se puede comprar.");
                return;
            }

            productosCompra[i] = producto;
            montoTotal += producto.getPrecio();
        }

        Compra compra = new Compra(nombreCliente, cedulaCliente, cantidadProductos, montoTotal, productosCompra);
        compras[totalCompras] = compra;
        totalCompras++;

        for (Producto producto : productosCompra) {
            producto.setExistencias(producto.getExistencias() - 1);
        }

        JOptionPane.showMessageDialog(null, "Compra realizada con éxito. Total a pagar: " + montoTotal);
    }

    private static void retirarProducto() {
        if (totalProductos == 0) {
            JOptionPane.showMessageDialog(null, "Supermercado La Familia está vacío. No hay productos para retirar.");
            return;
        }

        int idProducto = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del producto que desea retirar:"));

        Producto producto = buscarProducto(idProducto);

        if (producto == null) {
            JOptionPane.showMessageDialog(null, "No se encontró ningún producto con el ID especificado.");
            return;
        }

        if (producto.getPasillo() == 0 || producto.getPosicion() == 0) {
            JOptionPane.showMessageDialog(null, "El producto no está organizado en ningún pasillo.");
            return;
        }

        pasillos[producto.getPasillo() - 1].retirarProducto(producto.getPosicion());
        producto.setPasillo(0);
        producto.setPosicion(0);

        JOptionPane.showMessageDialog(null, "Producto retirado con éxito.");
    }

    private static void mostrarReportes() {
        int opcion;

        do {
            opcion = mostrarMenuReportes();
            ejecutarOpcionReportes(opcion);
        } while (opcion != 0);
    }

    private static int mostrarMenuReportes() {
        String menu = "Módulo de Reportes\n\n" +
                "1. Reporte de productos disponibles\n" +
                "2. Reporte de organización de pasillos\n" +
                "3. Reporte de caducidad\n" +
                "4. Reporte de resumen de compras\n" +
                "0. Volver al menú principal\n\n" +
                "Elige una opción:";
        return Integer.parseInt(JOptionPane.showInputDialog(null, menu));
    }

    private static void ejecutarOpcionReportes(int opcion) {
        switch (opcion) {
            case 1:
                reporteProductosDisponibles();
                break;
            case 2:
                reporteOrganizacionPasillos();
                break;
            case 3:
                reporteCaducidad();
                break;
            case 4:
                reporteResumenCompras();
                break;
            case 0:
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción inválida. Intenta de nuevo.");
                break;
        }
    }

    private static void reporteProductosDisponibles() {
        if (totalProductos == 0) {
            JOptionPane.showMessageDialog(null, "Supermercado La Familia está vacío. No hay productos disponibles.");
            return;
        }

        StringBuilder reporte = new StringBuilder(); // por terminos de rendimiento usamos StringBuilder es mejor que usar +
        reporte.append("Reporte de productos disponibles:\n\n");//con StringBuilder el yo pude agregar el append sin crerar c

        for (int i = 0; i < totalProductos; i++) {
            Producto producto = productos[i];
            if (producto.getPasillo() == 0) {
                reporte.append("ID: ").append(producto.getId()).append("\n");
                reporte.append("Nombre: ").append(producto.getNombre()).append("\n");
                reporte.append("Categoría: ").append(producto.getCategoria()).append("\n");
                reporte.append("Precio: ").append(producto.getPrecio()).append("\n");
                reporte.append("Existencias: ").append(producto.getExistencias()).append("\n");
                reporte.append("Fecha de Caducidad: ").append(producto.getFechaCaducidad()).append("\n\n");
            }
        }

        JOptionPane.showMessageDialog(null, reporte.toString());
    }

    private static void reporteOrganizacionPasillos() {
        StringBuilder reporteVisual = new StringBuilder();
        StringBuilder reporteDetallado = new StringBuilder();

        reporteVisual.append("Reporte de organización de pasillos:\n\n");
        reporteDetallado.append("Detalles de organización de pasillos:\n\n");

        for (int i = 0; i < 10; i++) {
            reporteVisual.append("Pasillo ").append(i + 1).append(": ");
            reporteDetallado.append("Productos en el pasillo ").append(i + 1).append(":\n");

            Pasillo pasillo = pasillos[i];
            if (pasillo == null) {
                reporteVisual.append("[Vacío]\n");
                reporteDetallado.append("[Vacío]\n\n");
            } else {
                for (int j = 0; j < MAX_PRODUCTOS_PASILLO; j++) {
                    Producto producto = pasillo.getProductos()[j];
                    if (producto != null) {
                        reporteVisual.append(producto.getExistencias() > 0 ? "X" : "R");
                        reporteDetallado.append("ID: ").append(producto.getId()).append(", Nombre: ").append(producto.getNombre())
                                .append(", Categoría: ").append(producto.getCategoria()).append(", Precio: ").append(producto.getPrecio())
                                .append(", Existencias: ").append(producto.getExistencias()).append("\n");
                    } else {
                        reporteVisual.append("-");
                    }
                }
                reporteVisual.append("\n");
                reporteDetallado.append("\n");
            }
        }

        JOptionPane.showMessageDialog(null, reporteVisual.toString());
        JOptionPane.showMessageDialog(null, reporteDetallado.toString());
    }

    private static void reporteCaducidad() {
        if (totalProductos == 0) {
            JOptionPane.showMessageDialog(null, "Supermercado La Familia está vacío. No hay productos para reportar.");
            return;
        }

        StringBuilder reporte = new StringBuilder();
        reporte.append("Reporte de productos próximos a caducar:\n\n");

        for (int i = 0; i < totalProductos; i++) {
            Producto producto = productos[i];
            if (diasHastaCaducidad(producto.getFechaCaducidad()) <= 30) {
                reporte.append("ID: ").append(producto.getId()).append("\n");
                reporte.append("Nombre: ").append(producto.getNombre()).append("\n");
                reporte.append("Categoría: ").append(producto.getCategoria()).append("\n");
                reporte.append("Fecha de Caducidad: ").append(producto.getFechaCaducidad()).append("\n\n");
            }
        }

        JOptionPane.showMessageDialog(null, reporte.toString());
    }

    private static int diasHastaCaducidad(String fechaCaducidad) {
      
    
        return 30; 
    }

    private static void reporteResumenCompras() {
        if (totalCompras == 0) {
            JOptionPane.showMessageDialog(null, "No se han realizado compras en Supermercado La Familia.");
            return;
        }

        StringBuilder reporte = new StringBuilder();
        reporte.append("Reporte de resumen de compras:\n\n");

        for (int i = 0; i < totalCompras; i++) {
            Compra compra = compras[i];
            reporte.append("Nombre: ").append(compra.getNombreCliente()).append("\n");
            reporte.append("Cédula: ").append(compra.getCedulaCliente()).append("\n");
            reporte.append("Cantidad de compras: ").append(compra.getCantidadCompras()).append("\n");
            reporte.append("Cantidad de productos: ").append(compra.getCantidadProductos()).append("\n");
            reporte.append("Monto gastado total: ").append(compra.getMontoTotal()).append("\n\n");
        }

        JOptionPane.showMessageDialog(null, reporte.toString());
    }
}
//atributos que se sobreescribiran por medio de override en cada clase no son clases repetidas 
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

class Pasillo {
    public Producto[] productosRegistrados;
    public int totalProductosRegistrados;

    public Pasillo() {
        this.productosRegistrados = new Producto[10];
        this.totalProductosRegistrados = 0;
    }

    public Producto[] getProductos() {
        return productosRegistrados;
    }

    public int getProductosRegistrados() {
        return totalProductosRegistrados;
    }

    public void registrarProducto(Producto producto, int posicion) {
        productosRegistrados[posicion - 1] = producto;
        totalProductosRegistrados++;
    }

    public void retirarProducto(int posicion) {
        productosRegistrados[posicion - 1] = null;
        totalProductosRegistrados--;
    }
}

class Compra {
    public String nombreCliente;
    public String cedulaCliente;
    public int cantidadCompras;
    public int cantidadProductos;
    public double montoTotal;
    public Producto[] productosComprados;

    public Compra(String nombreCliente, String cedulaCliente, int cantidadProductos, double montoTotal, Producto[] productosComprados) {
        this.nombreCliente = nombreCliente;
        this.cedulaCliente = cedulaCliente;
        this.cantidadCompras = 1;
        this.cantidadProductos = cantidadProductos;
        this.montoTotal = montoTotal;
        this.productosComprados = productosComprados;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public int getCantidadCompras() {
        return cantidadCompras;
    }

    public int getCantidadProductos() {
        return cantidadProductos;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public Producto[] getProductosComprados() {
        return productosComprados;
    }
}
