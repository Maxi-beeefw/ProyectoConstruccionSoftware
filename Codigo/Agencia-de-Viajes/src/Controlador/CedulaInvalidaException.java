package Controlador;

public class CedulaInvalidaException extends Exception {

    public CedulaInvalidaException() {
        super("La cédula ingresada no es válida. Por favor, revise los datos ingresados.");
    }
}

