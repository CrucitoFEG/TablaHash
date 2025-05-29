
public class Nodo {
    private String texto; // Pregunta o animal
    private boolean esAnimal;
    private Nodo nodoSi;  // Rama SI
    private Nodo nodoNo;  // Rama NO

    public Nodo(String texto, boolean esAnimal) {
        this.setTexto(texto);
        this.setEsAnimal(esAnimal);
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isEsAnimal() {
        return esAnimal;
    }

    public void setEsAnimal(boolean esAnimal) {
        this.esAnimal = esAnimal;
    }

    public Nodo getNodoSi() {
        return nodoSi;
    }

    public void setNodoSi(Nodo nodoSi) {
        this.nodoSi = nodoSi;
    }

    public Nodo getNodoNo() {
        return nodoNo;
    }

    public void setNodoNo(Nodo nodoNo) {
        this.nodoNo = nodoNo;
    }
}
