package Controlador;

//Excepcion personalizada

//se lanza si se produce un error al intentar convertir a tipo numérico alguno de los valores de los campos de texto
public class ValorNoNumericoException extends Exception {
    public ValorNoNumericoException() {
        super("Por favor ingrese un valor numérico en los campos correspondientes");
    }
}
