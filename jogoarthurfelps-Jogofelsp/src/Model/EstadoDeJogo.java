package Model;

public class EstadoDeJogo {

    private String cena;
    private Protagonista protagonista;

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
}
