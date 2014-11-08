package clases;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




/**
 *
 * @author hk
 */
public class StringCalculadora {

    public int add(String string) throws NumerosNegativosExcepcion {
        if (string.equals("")) {
            return 0;
        } else {
            try {
                int valor = Integer.parseInt(string);
                if (valor < 0) {
                    throw new NumerosNegativosExcepcion(string, "no se puede ingresar negativos");
                } else {
                    return valor;
                }
            } catch (NumberFormatException e) {
                String sin_patron = stringSinPatron(string);

                String frac = this.sacarCaracteresEspeciales(sin_patron);
                return this.sumarString(frac);
            }
        }
    }

    private String sacarCaracteresEspeciales(String string) {
        Pattern patron = Pattern.compile(" |#|%|&|:|[*]|\n|;|/");
        Matcher encaja = patron.matcher(string);
        return encaja.replaceAll(",");
    }

    private int sumarString(String frac) {
        int valor_acumulado = 0;
        int primer_index = 0;
        int segund_index = 0;
        for (int index = 0; index <= frac.length(); index++) {
            try {
                char c = frac.charAt(index);
                if (c == ',') {
                    segund_index = index;
                    int valor1 = Integer.parseInt(frac.substring(primer_index, segund_index));
                    if (valor1 < 0) {
                        throw new NumerosNegativosExcepcion(frac, "no se puede ingresar negativos");
                    } else {
                        if (valor1 > 1000) {
                            valor1 = 0;
                            valor_acumulado = valor_acumulado + valor1;
                            primer_index = index + 1;
                        } else {
                            valor_acumulado = valor_acumulado + valor1;
                            primer_index = index + 1;
                        }
                    }
                }
            } catch (StringIndexOutOfBoundsException f) {
                segund_index = index;
                int valor1 = Integer.parseInt(frac.substring(primer_index, segund_index));
                if (valor1 < 0) {
                    throw new NumerosNegativosExcepcion(frac, "no se puede ingresar negativos");
                } else {
                    valor_acumulado = valor_acumulado + valor1;
                }
            }
        }
        return valor_acumulado;
    }

    private String stringSinPatron(String string) {
        String frac = string;
        String patron = "", patron1;
        LinkedList lista_patrones;
        int index = 0;
        if (frac.charAt(0) == '/') {
            if (frac.charAt(1) == '/') {
                if (frac.charAt(2) == '[') {
                    lista_patrones = new LinkedList();
                    patron1 = new String();
                    for (index = 3; index < frac.length(); index++) {
                        char c = frac.charAt(index);
                        if (c != '\n') {
                            if (c != ']') {
                                if (c != '*') {
                                    patron1 = patron1 + c;
                                } else {
                                    patron1 = patron1 + ',';
                                }
                            } else {
                                lista_patrones.add(patron1);
                                if (frac.charAt(index + 1) == '[') {
                                    patron1 = new String();
                                    index++;
                                }
                            }
                        } else {
                            break;
                        }
                    }

                    String sin_patron = "";
                    for (index = index + 1; index < frac.length(); index++) {
                        char c = frac.charAt(index);
                        if (c != '*') {
                            sin_patron = sin_patron + c;
                        } else {
                            sin_patron = sin_patron + ',';
                        }
                    }
                    for (Iterator it = lista_patrones.iterator(); it.hasNext();) {
                        String patronA = (String) it.next();
                        Pattern patron2 = Pattern.compile(patronA);
                        Matcher encaja2 = patron2.matcher(sin_patron);
                        sin_patron = encaja2.replaceAll(",");
                    }
                    return sin_patron;

                } else {
                    for (index = 2; index < frac.length(); index++) {
                        char c = frac.charAt(index);
                        if (c != '\n') {
                            patron = patron + c;
                            if (c == ']' && frac.charAt(index + 1) == '[') {
                                patron = patron + '|';
                            }

                        } else {
                            break;
                        }
                    }
                    String sin_patron = "";
                    for (index = index + 1; index < frac.length(); index++) {
                        char c = frac.charAt(index);
                        sin_patron = sin_patron + c;
                    }

                    Pattern patron2 = Pattern.compile(patron);
                    Matcher encaja2 = patron2.matcher(sin_patron);
                    return encaja2.replaceAll(",");
                }

            }
        }
        return string;
    }
}
