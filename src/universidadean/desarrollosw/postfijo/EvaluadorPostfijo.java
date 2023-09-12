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
        for (String elemento : expresion) {
            if (esNumero(elemento)) {
                // Si el elemento es un número, lo convierte a entero y lo coloca en la pila
                pila.push(Integer.parseInt(elemento));
            } else if (esOperador(elemento)) {
                // Si el elemento es un operador, saca los dos operandos superiores de la pila,
                // aplica el operador y coloca el resultado nuevamente en la pila.
                int oper2 = pila.pop();
                int oper1 = pila.pop();
                int resultado = aplicarOperador(elemento, oper1, oper2);
                pila.push(resultado);
            }
        }

        // Al final, el resultado estará en la cima de la pila
        return pila.pop();
    }

    /**
     * Verifica si un elemento es un número.
     */
    static boolean esNumero(String elemento) {
        try {
            Integer.parseInt(elemento);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Verifica si un elemento es un operador.
     */
    static boolean esOperador(String elemento) {
        List<String> operadores = List.of("+", "-", "*", "/", "%");
        return operadores.contains(elemento);
    }

    /**
     * Aplica un operador a dos operandos y devuelve el resultado.
     */
    static int aplicarOperador(String operador, int oper1, int oper2) {
        switch (operador) {
            case "+":
                return oper1 + oper2;
            case "-":
                return oper1 - oper2;
            case "*":
                return oper1 * oper2;
            case "/":
                if (oper2 != 0) {
                    return oper1 / oper2;
                } else {
                    throw new ArithmeticException("División por cero");
                }
            case "%":
                if (oper2 != 0) {
                    return oper1 % oper2;
                } else {
                    throw new ArithmeticException("Módulo por cero");
                }
            default:
                throw new IllegalArgumentException("Operador no válido: " + operador);
        }
    }
}
