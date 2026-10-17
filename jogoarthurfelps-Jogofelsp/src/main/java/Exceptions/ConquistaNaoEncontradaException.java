package Exceptions;

public class ConquistaNaoEncontradaException extends IllegalArgumentException {
    public ConquistaNaoEncontradaException(String id) {
        super("Conquista desconhecida: " + id);
    }
}
