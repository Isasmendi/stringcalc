package test;

import clases.NumerosNegativosExcepcion;
import clases.StringCalculadora;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author hk
 */
public class teststringcalculator {

    @Test
    public void cadena_vacia_test() {
        StringCalculadora calcula = new StringCalculadora();
        int result = calcula.add("");
        assertEquals(result, 0);
    }

    @Test
    public void verificar_retorno_de_un_valor() {
        StringCalculadora calcula = new StringCalculadora();
        int result = calcula.add("1515");
        assertEquals(result, 1515);
    }

    @Test
    public void comprobar_dos_valores_con_coma() {
        StringCalculadora calcula = new StringCalculadora();
        int result = calcula.add("10,12");
        assertEquals(result, 22);
    }

    @Test
    public void comprobar_n_valores_con_coma() {
        StringCalculadora calcula = new StringCalculadora();
        int result = calcula.add("10,1,2,4,1");
        assertEquals(result, 18);

    }

    @Test
    public void comprobar_n_valores_separados_por_espacios() {
        StringCalculadora calcula = new StringCalculadora();
        int result = calcula.add("10 1 2 4 1");
        assertEquals(result, 18);
    }

    @Test
    public void comprobar_suma_con_saltos_de_linea_entre_numeros() {
        StringCalculadora calculadora = new StringCalculadora();
        int result = calculadora.add("10\n1\n1");
        assertEquals(result, 12);
    }

    @Test
    public void comprobar_con_caracteres_especiales() {
        StringCalculadora calcula = new StringCalculadora();
        int result = calcula.add("10#1:2,4 1");
        assertEquals(result, 18);
    }


    @Test
    public void comprobar_con_delimitador() {
        StringCalculadora calculadora = new StringCalculadora();
        int result = calculadora.add("//;\n1;2;3");
   //     System.out.println(result);
        assertEquals(result, 6);
    }
    
        @Test
    public void comprobar_con_delimitadores() {
        StringCalculadora calculadora = new StringCalculadora();
        int result = calculadora.add("//[*][%]\n1*2%3");

        assertEquals(result, 6);
    }
    
            @Test
    public void comprobar_con_delimitadores_largos() {
        StringCalculadora calculadora = new StringCalculadora();
        int result = calculadora.add("//[rrrrrrrrrrrr][tttttttttttttttttttt]\n1rrrrrrrrrrrr2tttttttttttttttttttt3");

        assertEquals(result, 6);
    }
    
       @Test
    public void comprobar_con_delimitador_largos() {
        StringCalculadora calculadora = new StringCalculadora();
        int result = calculadora.add("//qwwwwwwwwwwwwwwwwwwwq\n1qwwwwwwwwwwwwwwwwwwwq2qwwwwwwwwwwwwwwwwwwwq3");

        assertEquals(result, 6);
    }

    @Test
    public void comprobar_numeros_negativos() {
        StringCalculadora calculadora = new StringCalculadora();
        try{
        int result = calculadora.add("1,-2,-3,4");
        }catch(NumerosNegativosExcepcion e){
        assertEquals (e.devolverNegativos(),"-2 -3 ");
        }
        
    }

    @Test
    public void comprobar_numeros_menores_de1000() {
        StringCalculadora calculadora = new StringCalculadora();
        int result = calculadora.add("2,2000,3,2");
        assertEquals(result, 7);
    }

}
