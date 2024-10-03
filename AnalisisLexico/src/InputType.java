import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InputType {

    Automata automata;
    public InputType(Automata automata) {
        this.automata = automata;
    }

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
            if (automata.OperacionRetorno(cadenaAleatoria.toString()))
            {
                p.add(new String(cadenaAleatoria.toString()));
                i++;
            }
            cadenaAleatoria.setLength(0);
        }
        return p;
    }

}
