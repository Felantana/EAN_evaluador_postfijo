/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogotá - Colombia)
 * Departamento de Tecnologías de la Información y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Evaluador de Expresiones Postfijas
 * Fecha: Febrero 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package universidadean.desarrollosw.postfijo;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.*;

/**
 * Esta clase representa una clase que evalúa expresiones en notación polaca o
 * postfija. Por ejemplo: 4 5 +
 */
public class EvaluadorPostfijo {

    /**
     * Permite saber si la expresión en la lista está balanceada
     * o no. Cada elemento de la lista es un elemento. DEBE OBlIGATORIAMENTE
     * USARSE EL ALGORITMO QUE ESTÁ EN EL ENUNCIADO.
     */
    static boolean estaBalanceada(List<String> expresion) {
        Stack<String> delimitadores = new Stack<>();

        // TODO: Escriba el algoritmo del enunciado aquí
        List<String> caracteresApertura = List.of("{", "[", "(");
        List<String> caracteresCierre = List.of(")", "]", "}");
        String caracterApertura = null;
        String caracterCierre = null;

        for (String caracter: expresion){
            if (caracteresApertura.contains(caracter)){
                delimitadores.push(caracter);
            }
            else if (caracteresCierre.contains(caracter)) {
                if(delimitadores.isEmpty()){
                    return false;
                }
                switch (caracter){
                    case ")":
                        caracterApertura="(";
                        break;
                    case "]":
                        caracterApertura="[";
                        break;
                    case "}":
                        caracterApertura="{";
                        break;
                }
                caracterCierre = delimitadores.pop();
                if(!caracterCierre.equals(caracterApertura)){
                    return false;
                }
            }
        }
        return delimitadores.isEmpty();
    }

    /**
     * Transforma la expresión, cambiando los símbolos de agrupación
     * de corchetes ([]) y llaves ({}) por paréntesis ()
     */
    static void reemplazarDelimitadores(List<String> expresion) {
        // TODO: Escriba el algoritmo aquí
        List<String> caracteresReemplazar = List.of("{", "[", "]", "}");
        for (int i=0; i<expresion.size();i++){
            if(caracteresReemplazar.contains(expresion.get(i))){
                switch (expresion.get(i)){
                    case "{":
                        expresion.set(i, "(");
                        break;
                    case "[":
                        expresion.set(i, "(");
                        break;
                    case "]":
                        expresion.set(i, ")");
                        break;
                    case "}":
                        expresion.set(i, ")");
                        break;
                }
            }
        }
    }

    /**
     * Realiza la conversión de la notación infija a postfija
     * @return la expresión convertida a postfija
     * OJO: Debe usarse el algoritmo que está en el enunciado OBLIGATORIAMENTE
     */
    static List<String> convertirAPostfijo(List<String> expresion) {
        Stack<String> pila = new Stack<>();
        List<String> salida = new ArrayList<>();

        // TODO: Escriba el algoritmo aquí
        List<String> operadores = List.of("+", "-", "*", "/", "%");
        for(String caracter: expresion){
            if(operadores.contains(caracter)){
                pila.push(caracter);
            } else if (caracter.equals("(")) {
                continue;
            } else if (caracter.equals(")")) {
                salida.add(pila.pop());
            } else salida.add(caracter);
        }
        return salida;
    }

    /**
     * Realiza la evaluación de la expresión postfijo utilizando una pila
     * @param expresion una lista de elementos con números u operadores
     * @return el resultado de la evaluación de la expresión.
     */
    static int evaluarPostFija(List<String> expresion) {
        Stack<Integer> pila = new Stack<>();

        // TODO: Realiza la evaluación de la expresión en formato postfijo

        return pila.pop();
    }

}