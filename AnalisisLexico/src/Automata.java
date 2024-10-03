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

    public boolean OperacionRetorno(String test)
    {
        int i = 0;
        int control;
        if (parseo(test) && comprobacion(test))
        {
            System.out.println("es correcto");
            return true;
        }
        else
        {
            return false;
            //System.out.println("incorrecto");
        }
    }

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
        /**
        int i;

        i = 0;
        while (i < Test.size() && !Test.isEmpty())
        {
            if (comprobacion(Test.get(i)))
            {
                System.out.print("primer test completado: ");
                System.out.println(Test.get(i));
            }
            else
            {
                System.out.println("no paso");
            }
            i++;
        }*/
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

//comprobar funcionalidad
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
