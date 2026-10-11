package Model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EstadoDeJogo {

    private String cena;
    private Protagonista protagonista;
    private String DateTime;


    public EstadoDeJogo(String cena, Protagonista protagonista) {
        this.cena = cena;
        this.protagonista = protagonista;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public Protagonista getProtagonista() {
        return protagonista;
    }

    public void setProtagonista(Protagonista protagonista) {
        this.protagonista = protagonista;
    }

    public void setDateTime() {
        LocalDateTime data = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        this.DateTime = data.format(formato);
    }

    public String getDateTime() {
        return DateTime;
    }



    @Override
    public String toString() {
        return "EstadoDeJogo{" +
                "cena='" + cena + '\'' +
                ", protagonista=" + protagonista +
                '}';
    }
}
