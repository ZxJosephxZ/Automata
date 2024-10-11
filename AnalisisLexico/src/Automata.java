import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Automata {
    private List<Integer> Estados;
    private List<Character> Alfabeto;
    private Map<Integer, Map<Character, Integer>> matriz;
    private Integer estadoInicial;
    private List<Integer> estadosFinales;

    public Automata(Integer estadoInicial, String estados, String alfabeto, String estadoFinales, List<String> traza) {
        this.estadoInicial = estadoInicial;
        this.Estados = iniciarEstados(estados);
        this.Alfabeto = iniciarAlfabeto(alfabeto);
        this.estadosFinales = iniciarEstadosFinales(estadoFinales);
        this.matriz = crearMatriz(Alfabeto, traza);
    }

    /*
    *Metodo que crea un hashmap que simula la traza de cada estado, el cual es implementado en la matriz de estados
    * */
    private Map<Character, Integer> creadorTraza(List<Character> caracter, String numero)
    {
        int i = 0;
        Map<Character, Integer> mapeo = new HashMap<>();
        String[] partes = numero.split(",");
        while (i < caracter.size())
        {
            mapeo.put(caracter.get(i), Integer.parseInt(partes[i]));
            i++;
        }

        return mapeo;
    }

    /*
    *Metodo que crea la matriz de estados, implementando un hashmap principal y en cada posicion se concatena
    *otro hashmap que sera la traza del estado correspondiente
    * */
    private Map<Integer, Map<Character, Integer>>  crearMatriz(List<Character> alfabeto, List<String>traza)
    {
        //la traza es por indice y cada -> [1,1,2,3,4,5,6] -> que son los parametros para cada letra
        int i = 0;
        matriz = new HashMap<>();
        while (i < getEstados().size())
        {
            matriz.put(i, creadorTraza(alfabeto, traza.get(i)));
            i++;
        }
        return matriz;
    }

    /*
    *Metodo que inicializa una lista de estados, para la implementacion del automata
    * */
    private List<Integer> iniciarEstados(String prompt)
    {
        int i = 0;
        List<Integer> estado = new ArrayList<>();
        String[] partes = prompt.split(",");
        while (i < partes.length)
        {
            estado.add(Integer.parseInt(partes[i]));
            i++;
        }
        return (estado);
    }

    /*
    *Metodo que crea una lista de los caracteres que implementa el automata
    * */
    private List<Character> iniciarAlfabeto(String prompt)
    {
        int i = 0;
        List<Character> alfabeto = new ArrayList<>();
        String[] partes = prompt.split(",");
        while (i < partes.length)
        {
            alfabeto.add(partes[i].charAt(0));
            i++;
        }
        return alfabeto;
    }

    /*
    *Metodo que crea una lista con los estados finales, que indican si una cadena sera correcto
    * */
    private List<Integer> iniciarEstadosFinales(String prompt)
    {
        int i = 0;
        List<Integer> finales = new ArrayList<>();
        String[] partes = prompt.split(",");
        while (i < partes.length)
        {
            finales.add(Integer.parseInt(partes[i]));
            i++;
        }
        return finales;
    }

    public List<Integer> getEstados() {
        return Estados;
    }

    public void setEstados(List<Integer> estados) {
        Estados = estados;
    }

    public List<Character> getAlfabeto() {
        return Alfabeto;
    }

    public void setAlfabeto(List<Character> alfabeto) {
        Alfabeto = alfabeto;
    }

    public Integer getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(Integer estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public List<Integer> getEstadosFinales() {
        return estadosFinales;
    }

    public void setEstadosFinales(List<Integer> estadosFinales) {
        this.estadosFinales = estadosFinales;
    }

    public Map<Integer, Map<Character, Integer>> getMatriz() {
        return matriz;
    }

    public void setMatriz(Map<Integer, Map<Character, Integer>> matriz) {
        this.matriz = matriz;
    }

    /*
    * Metodo que se encarga de comprobar si la cadena a evaluar contiene los caracteres indicados en el automata
    * */
    public boolean parseo(String test)
    {
        int i = 0;
        String[]partes = test.split("");
        while (i < partes.length)
        {
            char currentChar = partes[i].charAt(0);
            if (!Alfabeto.contains(currentChar))
            {
                return false;
            }
            i++;
        }
        return true;
    }

    /*
    *Metodo que comprueba que las cadenas son validas segun el automata
    */
    public boolean operacionRetorno(String test)
    {
        int i = 0;
        int control;
        if (parseo(test) && comprobacion(test))
        {
            //System.out.println("es correcto");
            return true;
        }
        else
        {
            //System.out.println("incorrecto");
            return false;
        }
    }

    /*
    *Metodo para la comprobacion de una sola cadena de caracteres
    */
    public void Operacion(String test)
    {
        int i = 0;
        int control;
        if (parseo(test) && comprobacion(test))
        {
            System.out.println("es correcto");
        }
        else
        {
            System.out.println("incorrecto");
        }
    }

    public boolean contiene(int result)
    {
        int i = 0;
        while (i < estadosFinales.size())
        {
            if (estadosFinales.get(i) == result)
                return true;
            i++;
        }
        return false;
    }

    /*
    *Comprueba la funcionalidad del automata
     */
    public boolean comprobacion(String test)
    {
        int i = 0;
        int control = 0;
        String[] partes = test.split("");

        while (i < partes.length)
        {
            if (control == 0 && matriz.get(estadoInicial).get(partes[i].charAt(0)) != 0)
            {
                   control = matriz.get(estadoInicial).get(partes[i].charAt(0));
            }
            else if (control != 0 && matriz.get(control).get(partes[i].charAt(0)) != 0)
            {
                control = matriz.get(control).get(partes[i].charAt(0));
            }
            else
            {
                return (contiene(control) && (i == partes.length));
            }
            i++;
        }
        return contiene(control);
    }
}
