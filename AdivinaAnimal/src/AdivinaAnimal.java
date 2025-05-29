
import java.io.*;
import java.util.Scanner;

public class AdivinaAnimal {
    private Nodo raiz;
    private Scanner scanner;
    private boolean jugando;
    private static final String SALIR = "x";

    public AdivinaAnimal() {
        scanner = new Scanner(System.in);
        raiz = cargarArbol();
        if (raiz == null) {
            raiz = new Nodo("perro", true); // Arbol inicial si no hay archivo
        }
        jugando = true;
    }

    public void iniciarJuego() {
        System.out.println("----- ADIVINA EL ANIMAL -----");
        System.out.println("-INSTRUCCIONES DEL JUEGO");
        System.out.println("-El juego es muy sencillo, debes de contestar las preguntas");
        System.out.println("-responde 's' (si) o 'n' (no). Escribe 'x' para terminar");
        System.out.println("-con forme respondas y agregues animales el juego irá aprendiendo.\n");

        while (jugando) {
            jugarRonda();
            System.out.println("Jugar otra vez? (s/n/x)");
            String continuar = scanner.nextLine().trim().toLowerCase();

            if (continuar.equals("n") || continuar.equals("x")) {
                jugando = false;
                System.out.println("\nGracias por jugar. Hasta pronto.");
            }
        }
    }

    private void jugarRonda() {
        System.out.println("\nPiensa en un animal...");
        hacerPregunta(raiz);
    }

    private void hacerPregunta(Nodo actual) {
        if (!jugando) return;

        if (actual.isEsAnimal()) {
            System.out.println("Es un " + actual.getTexto() + "? (s/n/x)");
            String respuesta = scanner.nextLine().trim().toLowerCase();

            if (respuesta.equals(SALIR)) {
                jugando = false;
                System.out.println("Juego terminado.");
                return;
            }

            if (respuesta.equals("s")) {
                System.out.println("Adivine! :)");
            } else if (respuesta.equals("n")) {
                aprender(actual);
            } else {
                System.out.println("Respuesta no valida. Usa 's', 'n' o 'x'.");
                hacerPregunta(actual);
            }
        } else {
            System.out.println(actual.getTexto() + " (s/n/x)");
            String respuesta = scanner.nextLine().trim().toLowerCase();

            if (respuesta.equals(SALIR)) {
                jugando = false;
                System.out.println("Juego terminado.");
                return;
            }

            if (respuesta.equals("s")) {
                hacerPregunta(actual.getNodoSi());
            } else if (respuesta.equals("n")) {
                hacerPregunta(actual.getNodoNo());
            } else {
                System.out.println("Respuesta no valida. Usa 's', 'n' o 'x'.");
                hacerPregunta(actual);
            }
        }
    }

    private void aprender(Nodo nodoActual) {
        System.out.println("Vaya! Que animal era? (o escribe 'x')");
        String nuevoAnimal = scanner.nextLine().trim();

        if (nuevoAnimal.equalsIgnoreCase(SALIR)) {
            jugando = false;
            System.out.println("Juego terminado.");
            return;
        }

        System.out.println("Escribe una pregunta que sea verdadera para un " + nuevoAnimal + " y falsa para un " + nodoActual.getTexto());
        String nuevaPregunta = scanner.nextLine();

        Nodo nuevoNodo = new Nodo(nuevaPregunta, false);
        Nodo nodoAnimalNuevo = new Nodo(nuevoAnimal, true);
        Nodo nodoAnimalViejo = new Nodo(nodoActual.getTexto(), true);

        nuevoNodo.setNodoSi(nodoAnimalNuevo);
        nuevoNodo.setNodoNo(nodoAnimalViejo);

        nodoActual.setTexto(nuevoNodo.getTexto());
        nodoActual.setEsAnimal(false);
        nodoActual.setNodoSi(nuevoNodo.getNodoSi());
        nodoActual.setNodoNo(nuevoNodo.getNodoNo());

        System.out.println("Aprendido! Ahora conozco mas animales.");
        guardarArbol();
    }

    private void guardarArbol() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("db.txt"))) {
            guardarNodo(raiz, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar el arbol: " + e.getMessage());
        }
    }

    private void guardarNodo(Nodo nodo, PrintWriter writer) {
        if (nodo == null) return;

        if (nodo.isEsAnimal()) {
            writer.println("A|" + nodo.getTexto());
        } else {
            writer.println("P|" + nodo.getTexto());
            guardarNodo(nodo.getNodoSi(), writer);
            guardarNodo(nodo.getNodoNo(), writer);
        }
    }

    private Nodo cargarArbol() {
        File archivo = new File("db.txt");
        if (!archivo.exists()) return null;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            return cargarNodo(reader);
        } catch (IOException e) {
            System.out.println("Error al cargar el arbol: " + e.getMessage());
            return null;
        }
    }

    private Nodo cargarNodo(BufferedReader reader) throws IOException {
        String linea = reader.readLine();
        if (linea == null) return null;

        String[] partes = linea.split("\\|", 2);

        if (partes.length < 2) return null;

        String tipo = partes[0];
        String texto = partes[1];

        Nodo nodo = new Nodo(texto, tipo.equals("A"));
        if (!nodo.isEsAnimal()) {
            nodo.setNodoSi(cargarNodo(reader));
            nodo.setNodoNo(cargarNodo(reader));
        }
        return nodo;
    }
}
