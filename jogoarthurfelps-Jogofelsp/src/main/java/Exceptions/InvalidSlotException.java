package Exceptions;

public class InvalidSlotException extends IllegalArgumentException {
    public InvalidSlotException(int slot) {
        super("Slot inválido: " + slot + ". Os slots válidos são de 1 a 3.");
    }
}
