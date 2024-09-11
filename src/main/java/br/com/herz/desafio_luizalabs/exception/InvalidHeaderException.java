package br.com.herz.desafio_luizalabs.exception;

public class InvalidHeaderException extends Exception {

    private static final long serialVersionUID = -9066831907097986102L;

    public InvalidHeaderException(String expected, String received) {
        super("Cabeçalho do arquivo diferente do esperado.<br>Esperado: " + expected + "<br>Recebido: " + received);
    }
}
