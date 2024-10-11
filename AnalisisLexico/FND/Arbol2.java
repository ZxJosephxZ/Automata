import java.util.*;

public class Arbol2 {
    Nodo raiz;
    ArrayList<String>cadena;
    int inicio, fin;
    public Arbol2(String expresion)
    {
        cadena = dividirEnSegmentos(expresion);
        raiz = construirArbol(cadena);
        inicio = 0;
        fin = 0;
    }

    public void imprimirArbolDetalles(Nodo nodo, Set<Nodo> visitados) {
        if (nodo != null && !visitados.contains(nodo)) {
            visitados.add(nodo);
            // Imprimir el nodo actual con sus conexiones izquierda y derecha
            System.out.println("Nodo: " + nodo.valor +
                    " | Izquierdo: " + (nodo.izquierdo != null ? nodo.izquierdo.valor : "null") +
                    " | Derecho: " + (nodo.derecho != null ? nodo.derecho.valor : "null"));

            // Imprimir conexiones izquierda y derecha
            //imprimirArbolDetalles(nodo.izquierdo, visitados);
            imprimirArbolDetalles(nodo.derecho, visitados);
        }
    }

    // Método para iniciar la impresión detallada del árbol
    public void imprimirArbolDetalles() {
        Set<Nodo> visitados = new HashSet<>();
        imprimirArbolDetalles(raiz, visitados);
    }

    private Nodo aplicarOperadorEstrella(Nodo actual, String simbolo) {
        // Nodo anterior y posterior al nodo actual
        Nodo anterior = actual.izquierdo;
        Nodo posterior = actual.derecho;
        actual.valor = simbolo;

        // Crear un nodo vacío ε y actualizar conexiones
        Nodo nodoVacio = new Nodo("ε");

        // El nodo vacío apunta al anterior y al posterior
        nodoVacio.izquierdo = anterior;
        nodoVacio.derecho = posterior;

        // Ajustar el nodo anterior para que apunte al nodo vacío
        if (anterior != null) {
            anterior.izquierdo = nodoVacio;
        }

        // Ajustar el nodo actual (simbolo) para que apunte al nodo vacío
        //actual.izquierdo = nodoVacio;

        // Ajustar el nodo posterior para que apunte al nodo vacío
        if (posterior != null) {
            posterior.izquierdo = nodoVacio;
        }
        return posterior != null ? posterior : actual.derecho;
    }

    private Nodo aplicarOperadorInterrogacion(Nodo actual, String simbolo) {
        Nodo anterior = actual.izquierdo;
        Nodo posterior = actual.derecho;
        actual.valor = simbolo;

        // Crear 3 nodos vacíos
        Nodo vacio1 = new Nodo("ε");
        Nodo vacio2 = new Nodo("ε");
        Nodo vacio3 = new Nodo("ε");

        // Conexiones para el nodo anterior
        if (anterior != null) {
            anterior.derecho = vacio1;
            anterior.izquierdo = vacio2;
        }

        // Conectar el primer nodo vacío con el nodo actual
        vacio1.derecho = actual;
        actual.izquierdo = vacio1;

        // Conectar el primer nodo vacío con el segundo nodo vacío
        vacio2.izquierdo = null;
        // El segundo nodo vacío no apunta de vuelta

        // Conectar el segundo nodo vacío con el tercer nodo vacío
        vacio2.derecho = vacio3;
        vacio3.izquierdo = null;

        // Conectar el tercer nodo vacío con el nodo posterior
        vacio3.derecho = posterior;
        if (posterior != null) {
            posterior.izquierdo = vacio3;
        }
        return posterior != null ? posterior : vacio3;
    }

    public void añadirNodosEspeciales(Nodo nodo) {
        Nodo actual = nodo;

        while (actual != null) {
            String valor = actual.valor;

            // Si el nodo tiene un operador especial al final
            if (valor.length() > 1) {
                char operador = valor.charAt(valor.length() - 1);
                String simbolo = valor.substring(0, valor.length() - 1);
                System.out.println("simbolo:"+simbolo+" operador:"+operador);

                switch (operador) {
                    case '*':
                        actual = aplicarOperadorEstrella(actual, simbolo);
                        break;
                    case '?':
                        actual = aplicarOperadorInterrogacion(actual, simbolo);
                        break;
                    default:
                        actual = actual.derecho;
                    // Aquí puedes agregar más casos para otros operadores (+, |, etc.)
                }
            }
            else
            {
                System.out.println(actual.valor);
                actual = actual.derecho;
            }

        }
    }

    private Nodo construirArbol(ArrayList<String> expresion) {
        // Nodo inicial del árbol
        Nodo principio = new Nodo("inicio");

        // Nodo que usaremos para iterar y construir la estructura
        Nodo actual = principio;

        // Iterar a través de cada segmento de la lista de la expresión
        for (String elemento : expresion) {
            // Crear un nodo vacío ("ε") y un nodo para el elemento actual
            Nodo vacio = new Nodo("ε");
            Nodo siguiente = new Nodo(elemento);

            // Conectar el nodo actual con el nodo vacío
            actual.derecho = vacio;
            vacio.izquierdo = null;

            // Conectar el nodo vacío con el nodo siguiente
            vacio.derecho = siguiente;
            siguiente.izquierdo = vacio;

            // Actualizar el nodo actual para que apunte al último nodo creado (siguiente)
            actual = siguiente;
        }

        // Después de iterar a través de la lista, conectar el último nodo con el nodo final
        Nodo vacioFinal = new Nodo("ε");
        Nodo fin = new Nodo("fin");

        // Conectar el último nodo de la expresión con el nodo vacío final
        actual.derecho = vacioFinal;
        vacioFinal.izquierdo = null;

        // Conectar el nodo vacío final con el nodo "fin"
        vacioFinal.derecho = fin;
        fin.izquierdo = null;
        fin.derecho = null;

        return principio;
    }

    public ArrayList<String> dividirEnSegmentos(String expresion) {
        ArrayList<String> segmentos = new ArrayList<>();
        StringBuilder segmentoActual = new StringBuilder(); // Acumula el segmento actual
        int parentesisNiveles = 0; // Contar los niveles de paréntesis

        for (int i = 0; i < expresion.length(); i++) {
            char actual = expresion.charAt(i);

            // Manejo de grupos entre paréntesis
            if (actual == '(') {
                // Si estamos construyendo un segmento previo, guardarlo
                if (segmentoActual.length() > 0 && parentesisNiveles == 0) {
                    segmentos.add(segmentoActual.toString());
                    segmentoActual.setLength(0); // Limpiar el acumulador
                }
                parentesisNiveles++; // Aumentar el nivel de paréntesis
                segmentoActual.append(actual);
            } else if (actual == ')') {
                parentesisNiveles--; // Reducir el nivel de paréntesis
                segmentoActual.append(actual);

                // Si hemos cerrado todos los paréntesis, verificar si hay un operador unario
                if (parentesisNiveles == 0) {
                    if (i + 1 < expresion.length() && esOperadorUnario(expresion.charAt(i + 1))) {
                        segmentoActual.append(expresion.charAt(i + 1)); // Añadir el operador al grupo
                        i++; // Saltar el operador
                    }
                    segmentos.add(segmentoActual.toString());
                    segmentoActual.setLength(0); // Limpiar el acumulador
                }
            }
            // Manejo de caracteres individuales fuera de paréntesis
            else if (esCaracter(actual) && parentesisNiveles == 0) {
                // Si estamos construyendo un segmento previo, guardarlo
                if (segmentoActual.length() > 0) {
                    segmentos.add(segmentoActual.toString());
                    segmentoActual.setLength(0); // Limpiar el acumulador
                }
                segmentoActual.append(actual);

                // Verificar si el siguiente carácter es un operador unario
                if (i + 1 < expresion.length() && esOperadorUnario(expresion.charAt(i + 1))) {
                    segmentoActual.append(expresion.charAt(i + 1)); // Añadir el operador unario
                    i++; // Saltar el operador
                }
                segmentos.add(segmentoActual.toString());
                segmentoActual.setLength(0); // Limpiar el acumulador
            }
            // Acumular contenido dentro de un grupo de paréntesis
            else {
                segmentoActual.append(actual);
            }
        }

        // Si queda algún segmento residual, agregarlo
        if (segmentoActual.length() > 0) {
            segmentos.add(segmentoActual.toString());
        }
        for (String c : segmentos)
        {
            System.out.println(c);
        }
        return segmentos;
    }

    // Método auxiliar para identificar operadores unarios (*, +, ?, |)
    private boolean esOperadorUnario(char c) {
        return c == '*' || c == '+' || c == '?' || c == '|';
    }

    // Método auxiliar para identificar caracteres simples (letras o números)
    private boolean esCaracter(char c) {
        return Character.isLetterOrDigit(c);
    }
}
