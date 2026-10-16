package Controller;
import Model.*;
import DAO.implementacao.*;
import DTOs.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ServicosSave {

    private final EstadoJsonDao dao;
    private final PreferenciaJsonDao preferenciaDAO;
    private boolean autosave;


    public ServicosSave(EstadoJsonDao dao, PreferenciaJsonDao preferenciaDAO) throws Exception {
        this.dao = dao;
        this.preferenciaDAO = preferenciaDAO;
        this.autosave = preferenciaDAO.carregarPreferencias().get(0).isAtivo();
    }

    public boolean isAutosave() {
        return autosave;
    }

    public EstadoJogoDTO getInfoSave(int slot) throws Exception{
        if (!dao.temSave(slot)) {
            return null;
        }
        if (dao.getEstado(slot) == null){
            return null;
        }

        EstadoDeJogo estado = dao.getEstado(slot);
        return new EstadoJogoDTO(
                estado.getProtagonista().getNome(),
                estado.getCena(),
                estado.getCena(),
                estado.getDateTime()
        );


    }

    public void LimparSlot(int slot) throws IOException {
        dao.limpar(slot);
    }

    public EstadoDeJogo getEstadoSave(int slot) throws Exception {
        return dao.getEstado(slot);
    }

    public int getPartidaMaisRecente() throws Exception {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        LocalDateTime maior = LocalDateTime.MIN;
        int slotRecente = -1;
        for (int slot = 1; slot <= 3; slot++) {
            if(dao.temSave(slot)) {
                String data = dao.getEstado(slot).getDateTime();
                LocalDateTime ds = LocalDateTime.parse(data, formato);

                if (ds.isAfter(maior)) {
                    maior = ds;
                    slotRecente = slot;
                }
            }
        }
        return slotRecente;
    }

    public boolean temSave(int slot) throws IOException {
        return dao.temSave(slot);
    }

    public void salvarJogo(int slot,EstadoDeJogo state) throws Exception {
        dao.salvar(slot,state);
    }

    public void CarregarPreferencias() throws Exception {
        List<Preferencia> preferencias = preferenciaDAO.carregarPreferencias();

    }

    public List<PreferenciaDTO> getPreferenciasDTO() throws Exception {

        List<PreferenciaDTO> PreferenciaDTOS = new ArrayList<>();
        List<Preferencia> preferencias = preferenciaDAO.carregarPreferencias();
        for (Preferencia p : preferencias) {
            PreferenciaDTOS.add(new PreferenciaDTO(
                    p.getTitulo(),
                    p.getDescricao(),
                    p.isAtivo()
            ));
        }

        return PreferenciaDTOS;
    }

}
