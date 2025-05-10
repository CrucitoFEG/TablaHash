public class TablaHash<K, V> {
	
    private static final int CAPACIDAD_INICIAL = 16;
    private Nodo<K, V>[] buckets;
    private int tamaño;

    // Clase interna para los nodos (encadenamiento)
    private static class Nodo<K, V> {
        K clave;
        V valor;
        Nodo<K, V> siguiente;

        Nodo(K clave, V valor) {
            this.clave = clave;
            this.valor = valor;
        }
    }

    // Constructor
    public TablaHash() {
        this(CAPACIDAD_INICIAL);
    }

    @SuppressWarnings("unchecked")
    public TablaHash(int capacidad) {
        this.buckets = new Nodo[capacidad];
        this.tamaño = 0;
    }
    
    public boolean containsKey(K clave) {
        int indice = getIndice(clave);
        Nodo<K, V> nodoActual = buckets[indice];

        while (nodoActual != null) {
            if (nodoActual.clave.equals(clave)) {
                return true;
            }
            nodoActual = nodoActual.siguiente;
        }
        return false;
    }

    
    private int getIndice(K clave) {
        if (clave == null) {
            return 0; // Manejo de clave nula (como en HashMap)
        }
        // Usa hashCode() de la clave y aplica módulo para evitar índices negativos
        return Math.abs(clave.hashCode()) % buckets.length;
    }
    
    
    public void put(K clave, V valor) {
        int indice = getIndice(clave);
        Nodo<K, V> nodoActual = buckets[indice];

        // Busca si la clave ya existe en la lista
        while (nodoActual != null) {
            if (nodoActual.clave.equals(clave)) {
                nodoActual.valor = valor; // Actualiza valor si existe
                return;
            }
            nodoActual = nodoActual.siguiente;
        }

        // Si no existe, crea un nuevo nodo y lo añade al frente
        Nodo<K, V> nuevoNodo = new Nodo<>(clave, valor);
        nuevoNodo.siguiente = buckets[indice];
        buckets[indice] = nuevoNodo;
        tamaño++;

        // Opcional: Redimensionar si la carga es alta (no implementado aquí)
    }
    
    
    public V get(K clave) {
        int indice = getIndice(clave);
        Nodo<K, V> nodoActual = buckets[indice];

        while (nodoActual != null) {
            if (nodoActual.clave.equals(clave)) {
                return nodoActual.valor;
            }
            nodoActual = nodoActual.siguiente;
        }
        return null; // Clave no encontrada
    }
    
    
    public V remove(K clave) {
        int indice = getIndice(clave);
        Nodo<K, V> nodoActual = buckets[indice];
        Nodo<K, V> nodoAnterior = null;

        while (nodoActual != null) {
            if (nodoActual.clave.equals(clave)) {
                if (nodoAnterior == null) {
                    buckets[indice] = nodoActual.siguiente; // Elimina el primer nodo
                } else {
                    nodoAnterior.siguiente = nodoActual.siguiente; // Elimina un nodo intermedio
                }
                tamaño--;
                return nodoActual.valor;
            }
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.siguiente;
        }
        return null; // Clave no encontrada
    }

    public void imprimirEntradas() {
        for (int i = 0; i < buckets.length; i++) {
            Nodo<K, V> nodo = buckets[i];
            while (nodo != null) {
                System.out.println(nodo.clave + ": " + nodo.valor);
                nodo = nodo.siguiente;
            }
        }
    }
}
