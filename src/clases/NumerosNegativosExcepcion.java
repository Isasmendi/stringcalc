package clases;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Alejandrux
 */
public class NumerosNegativosExcepcion extends ArithmeticException {

    String nros;

    public NumerosNegativosExcepcion(String nros, String comentario) {
        super(comentario);
        this.nros = nros;
    }

    public String devolverNegativos() {
        String valor = "";
        String frac = nros;
        int primer_index = 0;
        int segund_index = 0;
        for (int index = 0; index <= frac.length(); index++) {
            try {
                char c = frac.charAt(index);
                if (c == ',') {
                    segund_index = index;
                    int valor1 = Integer.parseInt(frac.substring(primer_index, segund_index));
                    primer_index = index + 1;
                    if (valor1 < 0) {
                        valor = valor + valor1 + " ";
                    }
                }
            } catch (StringIndexOutOfBoundsException f) {
                segund_index = index;
                int valor1 = Integer.parseInt(frac.substring(primer_index, segund_index));
                if (valor1 < 0) {
                    valor = valor + valor1 + " ";
                }
            }
        }
        return valor;
    }

}
