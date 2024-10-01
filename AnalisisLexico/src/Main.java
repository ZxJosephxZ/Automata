import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//q0 = 1 , q1 = 2
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<String> trazas = new ArrayList<>();
        trazas.add("2,1,0,0,0");
        trazas.add("0,0,3,0,0");
        trazas.add("0,1,0,0,0");
        trazas.add("0,0,0,4,0");
        trazas.add("0,0,0,0,5");
        trazas.add("0,0,0,4,0");
        Automata automata = new Automata(0,"1,2,3,4,5,6","a,b,c,d,e","3,5", trazas);
        //automata.comprobacion();
        System.out.print("inicial"+automata.getEstadoInicial()+" ");
        System.out.print("final"+automata.getEstadosFinales().get(0));
        System.out.println(automata.getEstadosFinales().get(1));
        System.out.print(" ");
        System.out.print(automata.getAlfabeto().get(0)+" " );
        System.out.print(automata.getAlfabeto().get(1)+" " );
        System.out.print(automata.getAlfabeto().get(2)+" " );
        System.out.print(automata.getAlfabeto().get(3)+" " );
        System.out.print(automata.getAlfabeto().get(4)+" " );
        System.out.println();
        System.out.println(automata.getEstados().get(0));
        System.out.println(automata.getEstados().get(1));
        System.out.println(automata.getEstados().get(2));
        System.out.println(automata.getEstados().get(3));
        System.out.println(automata.getEstados().get(4));
        System.out.println(automata.getEstados().get(5));
        System.out.println(automata.getMatriz().get(0));
        System.out.println(automata.getMatriz().get(1));
        System.out.println(automata.getMatriz().get(2));
        System.out.println(automata.getMatriz().get(3));
        System.out.println(automata.getMatriz().get(4));
        automata.Operacion("b,c,d,e,d,e");
    }
}