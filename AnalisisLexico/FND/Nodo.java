public class Nodo {

    String valor;
    Nodo izquierdo, derecho;

    public Nodo(String valor)
    {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
    }
    @Override
    public String toString() {
        return valor;
    }


}
