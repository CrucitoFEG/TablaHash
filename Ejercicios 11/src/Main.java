public class Main {
    public static void main(String[] args) {
         // Ejercicio 1 
        String texto = "Hola mundo, hola universo. Mundo mundo mundo! Hola!";

        // Paso 1: Normalizar (minúsculas, sin signos de puntuación)
        texto = texto.toLowerCase().replaceAll("[.,;!]", "");

        // Paso 2: Separar palabras
        String[] palabras = texto.split("\\s+");

        // Paso 3: Crear tabla hash para contar frecuencias
        TablaHash<String, Integer> frecuencias = new TablaHash<>();

        for (String palabra : palabras) {
            if (frecuencias.containsKey(palabra)) {
                int actual = frecuencias.get(palabra);
                frecuencias.put(palabra, actual + 1);
            } else {
                frecuencias.put(palabra, 1);
            }
        }

        // Paso 4: Mostrar resultados
        System.out.println("------------------------------Ejercicio 1----------------------------------");
        System.out.println("\nFrecuencia de palabras:");
        frecuencias.imprimirEntradas();       
        
        //este seria el ejercicio 2
        TablaHash<String, Simbolo> tablaSimbolos = new TablaHash<>();
        
        // Insertar algunos símbolos
        tablaSimbolos.put("edad", new Simbolo("edad", "int", 25));
        tablaSimbolos.put("nombre", new Simbolo("nombre", "String", "Ana"));
        tablaSimbolos.put("activo", new Simbolo("activo", "boolean", true));
        
        // Obtener e imprimir símbolos específicos
        // System.out.println("Símbolo 'edad': " + tablaSimbolos.get("edad"));
        // System.out.println("Símbolo 'nombre': " + tablaSimbolos.get("nombre"));
        
        // Mostrar todos los símbolos
        System.out.println("------------------------------Ejercicio 2----------------------------------");
        System.out.println("\nTabla de símbolos completa:");
        tablaSimbolos.imprimirEntradas();
    }
}
