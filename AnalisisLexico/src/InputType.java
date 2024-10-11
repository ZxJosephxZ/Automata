import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InputType {

    Automata automata;
    public InputType(Automata automata) {
        this.automata = automata;
    }
    /*
    *Metodo que crea 100 cadenas de caracteres aleatorias de longitud maxima 10,
    *mediante los caracteres del string pasado por parametros
    */
    public  List<String> crearCadenas(String cadena)
    {
        int i = 0;
        List<String> p = new ArrayList<>();
        Random random = new Random();
        StringBuilder cadenaAleatoria = new StringBuilder();
        while (i < 100)
        {
            for (int j = 0; j < random.nextInt(1,10); j++)
            {
                int indiceAleatorio = random.nextInt(cadena.length());
                cadenaAleatoria.append(cadena.charAt(indiceAleatorio));
            }
            //En caso de querer ver las cadenas de caracteres descomentar
            //System.out.println(cadenaAleatoria.toString());
            if (automata.operacionRetorno(cadenaAleatoria.toString()))
            {
                p.add(new String(cadenaAleatoria.toString()));
            }
            cadenaAleatoria.setLength(0);
            i++;
        }
        return p;
    }

}
