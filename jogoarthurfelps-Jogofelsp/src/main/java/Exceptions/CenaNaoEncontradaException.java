package Exceptions;

public class CenaNaoEncontradaException extends IllegalArgumentException {
    public CenaNaoEncontradaException(String cenaId) {
        super("Cena não encontrada: " + cenaId);
    }

    public CenaNaoEncontradaException(String cenaId, String escolha) {
        super("A escolha \"" + escolha + "\" aponta para uma cena inexistente: " + cenaId);
    }
}
