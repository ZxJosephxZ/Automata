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
        trazas.add("2,1,0,0,0");//traza0
        trazas.add("0,0,3,0,0");//traza1
        trazas.add("0,1,0,0,0");//traza2
        trazas.add("0,0,0,4,0");//traza3
        trazas.add("0,0,0,0,5");//traza4
        trazas.add("0,0,0,4,0");//traza5
        Automata automata = new Automata(0,"1,2,3,4,5,6","a,b,c,d,e","3,5", trazas);
        //automata.comprobacion();

        automata.Operacion("abcdede");
        InputType input = new InputType(automata);
        List<String>ppp;
        ppp = input.crearCadenas("abcde");
        int x = 0;
        while (x < ppp.size())
        {
            System.out.println(ppp.get(x));
            x++;
        }
        System.out.println(ppp.size());
    }
}