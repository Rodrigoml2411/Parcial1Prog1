//Rodrigo Martinez PU 

//Encapsulamiento

class Publicacion {

    private String codigo;
    private String titulo;
    private int anioPublicacion;

    public Publicacion(String codigo, String titulo, int anioPublicacion) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }


    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getAnioPublicacion() { return anioPublicacion; }
    public void setAnioPublicacion(int anioPublicacion) { this.anioPublicacion = anioPublicacion; }

    public String mostrarDatos() {
        return "Publicación " + codigo + " – " + titulo + " – Año: " + anioPublicacion;
    }
}

//Interfaz 

interface Prestamo {

    public String tipoPrestamo();
}

// Herencia y Polimorfismo 

class Libro extends Publicacion implements Prestamo {
   
    private String autor;

    public Libro(String codigo, String titulo, int anioPublicacion, String autor) {
        super(codigo, titulo, anioPublicacion);
        this.autor = autor;
    }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }



    @Override
    public String mostrarDatos() {
        return "Libro: " + getTitulo() + " – Autor: " + autor + " – Año: " + getAnioPublicacion();
    }


    @Override
    public String tipoPrestamo() {
        return "Préstamo por 15 días";
    }
}

class Revista extends Publicacion implements Prestamo {
    private int numeroEdicion;

    public Revista(String codigo, String titulo, int anioPublicacion, int numeroEdicion) {
        super(codigo, titulo, anioPublicacion);
        this.numeroEdicion = numeroEdicion;
    }

    public int getNumeroEdicion() { return numeroEdicion; }
    public void setNumeroEdicion(int numeroEdicion) { this.numeroEdicion = numeroEdicion; }

    @Override
    public String mostrarDatos() {
        return "Revista: " + getTitulo() + " – Edición N° " + numeroEdicion + " – Año: " + getAnioPublicacion();
    }

    @Override
    public String tipoPrestamo() {
        return "Préstamo por 7 días";
    
    }
}

//Ordenamiento y Búsqueda

class PublicacionNoEncontradaException extends Exception {
    public PublicacionNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}

class Biblioteca {

    // Ordena de más reciente a más antigua

    public void ordenarPorAnio(Publicacion[] lista) {
        java.util.Arrays.sort(lista, (a, b) -> b.getAnioPublicacion() - a.getAnioPublicacion());
    }

    // Busca por título 

    public Publicacion buscarPorTitulo(Publicacion[] lista, String titulo) throws PublicacionNoEncontradaException {
        for (Publicacion p : lista) {
            if (p.getTitulo().equalsIgnoreCase(titulo)) {
                return p;
            }
        }
        throw new PublicacionNoEncontradaException("Publicación con título '" + titulo + "' no encontrada.");
    }
}

//Uso en Main

class Usuario {

    private String nombre;
    private Publicacion[] prestamos;

    public Usuario(String nombre, Publicacion[] prestamos) {
        this.nombre = nombre;
        this.prestamos = prestamos;
    }

    public void mostrarPrestamos() {
        System.out.println("Préstamos del usuario " + nombre + ":");
        for (Publicacion p : prestamos) {
            System.out.println(" - " + p.getTitulo());
        }
    }
}

//demostración completa en Main

public class Main {
    public static void main(String[] args) {
       
        // Crear publicaciones

        Publicacion l1 = new Libro("L001", "Java Básico", 2020, "Juan Pérez");
        Publicacion l2 = new Libro("L002", "Patrones de Diseño", 2023, "Ana Gómez");
        Publicacion r1 = new Revista("R001", "Tech World", 2022, 15);

        Publicacion[] lista = { l1, l2, r1 };

        // Biblioteca

        Biblioteca biblio = new Biblioteca();
        System.out.println(" Lista Original ");
        for (Publicacion p : lista) {
            System.out.println(p.mostrarDatos());
        }

        // Ordenar por año

        biblio.ordenarPorAnio(lista);
        System.out.println("\n Ordenadas (más recientes primero) ");
        for (Publicacion p : lista) {
            System.out.println(p.mostrarDatos());
        }

        // Buscar por título

        try {
            Publicacion buscada = biblio.buscarPorTitulo(lista, "Tech World");
            System.out.println("\nBúsqueda exitosa: " + buscada.mostrarDatos());
        } catch (PublicacionNoEncontradaException e) {
            System.out.println(e.getMessage());
        }

        // Crear usuario con composición

        Usuario user = new Usuario("Carlos", new Publicacion[] { l2, r1 });
        user.mostrarPrestamos();

        // Mostrar uso de interfaz y polimorfismo

        System.out.println("\n Tipo de préstamo ");
        for (Publicacion p : lista) {
            if (p instanceof Prestamo) {
                Prestamo pr = (Prestamo) p;
                System.out.println(p.getTitulo() + ": " + pr.tipoPrestamo());
            }
         }
     }
}








