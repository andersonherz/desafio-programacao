package br.com.herz.desafio_luizalabs.exception;

public class InvalidFileException extends Exception {

    private static final long serialVersionUID = -7455971676339485193L;

    public InvalidFileException() {
        super("Arquivo inválido.");
    }

}
