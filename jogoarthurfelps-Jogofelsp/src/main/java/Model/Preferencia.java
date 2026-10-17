package Model;

import java.util.ArrayList;
import java.util.List;

public class Preferencia {
    private String titulo;
    private String id;
    private String descricao;
    private boolean ativo;

    public Preferencia(String titulo,String Id, String descricao, boolean ativo) {
        this.titulo = titulo;
        this.id = Id;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getId() {
        return id;
    }
}
