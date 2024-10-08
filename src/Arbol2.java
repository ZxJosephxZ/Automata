import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    // Método para imprimir el árbol (opcional para ver la estructura)
    public void imprimirArbol(Nodo nodo, String prefijo) {
        if (nodo != null) {
            System.out.println(prefijo + nodo.valor);
            imprimirArbol(nodo.derecho, prefijo + "--");
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
            vacio.izquierdo = actual;

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
        vacioFinal.izquierdo = actual;

        // Conectar el nodo vacío final con el nodo "fin"
        vacioFinal.derecho = fin;
        fin.izquierdo = vacioFinal;

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
