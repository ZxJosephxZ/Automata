public class Transicion {

    Estado origen;
    String simbolos;
    Estado destino;

    public Transicion(Estado origen, String simbolos, Estado destino)
    {
        this.origen = origen;
        this.simbolos = simbolos;
        this.destino = destino;
    }
}
