import java.util.*;

//q0 = 1 , q1 = 2
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pedir el estado inicial al usuario
        System.out.print("Ingrese el estado inicial (ejemplo: q0 -> 0): ");
        int estadoInicial = scanner.nextInt();
        scanner.nextLine();  // Consumir la línea restante

        // Pedir los estados al usuario
        System.out.print("Ingrese los estados separados por comas,(ejemplo: q0,q1 -> 1,2): ");
        String estados = scanner.nextLine();

        // Pedir el alfabeto
        System.out.print("Ingrese el alfabeto separado por comas (ejemplo: x,y,z): ");
        String alfabeto = scanner.nextLine();

        // Pedir los estados finales
        System.out.print("Ingrese los estados finales separados por comas (ejemplo: q2,q4,q5->2,4,5): ");
        String estadosFinales = scanner.nextLine();

        // Pedir el número de trazas
        System.out.print("Ingrese el total de estados contando el q0 (ejemplo: q0,q1-> 2): ");
        int numTrazas = scanner.nextInt();
        scanner.nextLine();  // Consumir la línea restante

        // Pedir las trazas y agregarlas a la lista
        System.out.println("Como ingresar la traza: qo: x y z -> 0,0,1");
        System.out.println("Las posiciones respetan el orden del alfabeto, en caso de que el estado no tenga una conexion con otro se colocara un 0");
        System.out.println("Si tiene un salto a otro estado se colocara el numero del estado q1 -> 1");
        System.out.println("ejemplo: q0 salta a q1 por z y no tiene saltos por x ni y -> 0,0,1");
        List<String> trazas = new ArrayList<>();
        for (int i = 0; i < numTrazas; i++) {
            System.out.print("Ingrese la traza " + "q" + i + " separada por comas (ejemplo: 0,0,1): ");
            String traza = scanner.nextLine();
            trazas.add(traza);
        }

        // Crear el automata con los datos del usuario
        Automata automata = new Automata(estadoInicial, estados, alfabeto, estadosFinales, trazas);

        // Pedir una cadena de entrada para realizar la operación
        System.out.print("Ingrese la cadena a evaluar (ejemplo: zyxzzzzzz): ");
        String cadena = scanner.nextLine();

        // Ejecutar la operación
        automata.Operacion(cadena);

/* En caso de querer hacerlo manual
        List<String> trazas = new ArrayList<>();
        trazas.add("0,0,1");//traza0
        trazas.add("4,3,2");//traza1
        trazas.add("4,3,5");//traza2
        trazas.add("4,5,6");//traza3
        trazas.add("0,0,7");//traza4
        trazas.add("4,3,5");//traza5
        trazas.add("0,0,7");//traza6
        trazas.add("0,0,7");//traza7
        Automata automata = new Automata(0,"1,2,3,4,5,6,7,8","x,y,z","2,4,5,6,7", trazas);

        automata.Operacion("zyxzzzzzz");*/
        /*
        //Descomentar en caso de probar 100 string aleatorias
        InputType input = new InputType(automata);
        List<String>ppp;
        //Introduce como cadena los caracteres que se implementaran para formar los string
        ppp = input.crearCadenas("xyz");
        int x = 0;
        while (x < ppp.size())
        {
            System.out.println(ppp.get(x));
            x++;
        }
        */
    }
}