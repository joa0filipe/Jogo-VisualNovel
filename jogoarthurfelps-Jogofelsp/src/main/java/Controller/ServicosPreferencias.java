package Controller;
import Model.Preferencia;
import Model.Configuracoes;
import DAO.implementacao.PreferenciaJsonDao;
import DTOs.PreferenciaDTO;

import java.util.ArrayList;
import java.util.List;


public class ServicosPreferencias {

    private final PreferenciaJsonDao dao;

    public ServicosPreferencias(PreferenciaJsonDao dao) {
        this.dao = dao;
    }

    public void inicializarPreferencias() throws Exception {

        List<Preferencia> preferencias = dao.carregarPreferencias();

        if (preferencias.isEmpty()) {
            preferencias = Configuracoes.criarPreferenciasPadrao();
            dao.salvarPreferencia(preferencias);
        }

        boolean alterou = false;
        for (Preferencia padrao : Configuracoes.criarPreferenciasPadrao()) {
            int indice = encontrarIndice(preferencias, padrao.getId());
            if (indice < 0) {
                preferencias.add(padrao);
                alterou = true;
            } else {
                Preferencia existente = preferencias.get(indice);
                if (!existente.getTitulo().equals(padrao.getTitulo())
                        || !existente.getDescricao().equals(padrao.getDescricao())) {
                    preferencias.set(indice, new Preferencia(
                            padrao.getTitulo(),
                            padrao.getId(),
                            padrao.getDescricao(),
                            existente.isAtivo()
                    ));
                    alterou = true;
                }
            }
        }

        if (alterou) {
            dao.salvarPreferencia(preferencias);
        }


    }

    public void CarregarPreferencias() throws Exception {
        List<Preferencia> preferencias = dao.carregarPreferencias();

    }

    public List<PreferenciaDTO> getPreferenciasDTO() throws Exception {

        List<PreferenciaDTO> PreferenciaDTOS = new ArrayList<>();
        List<Preferencia> preferencias = dao.carregarPreferencias();
        for (Preferencia p : preferencias) {
            PreferenciaDTOS.add(new PreferenciaDTO(
                    p.getTitulo(),
                    p.getDescricao(),
                    p.isAtivo()
            ));
        }

        return PreferenciaDTOS;
    }

    public void inverterPreferencia(int id) throws Exception {
        List<Preferencia> preferencias = dao.carregarPreferencias();
        int indice = encontrarIndice(preferencias, Integer.toString(id));
        if (indice < 0) {
            throw new IllegalArgumentException("Preferência desconhecida: " + id);
        }
        Preferencia p = preferencias.get(indice);

        p.setAtivo(!p.isAtivo());

        dao.salvarPreferencia(preferencias);
    }

    public boolean isAutoSave() throws Exception {
        return dao.carregarPreferencias().get(0).isAtivo();
    }

    public boolean isMostrarEscolhas() throws Exception{
        return estaAtiva("1");
    }

    public boolean isOcultarHudAtributos() throws Exception {
        return estaAtiva("2");
    }

    private boolean estaAtiva(String id) throws Exception {
        List<Preferencia> preferencias = dao.carregarPreferencias();
        int indice = encontrarIndice(preferencias, id);
        if (indice < 0) {
            throw new IllegalStateException("Preferência não inicializada: " + id);
        }
        return preferencias.get(indice).isAtivo();
    }

    private int encontrarIndice(List<Preferencia> preferencias, String id) {
        for (int i = 0; i < preferencias.size(); i++) {
            if (id.equals(preferencias.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }



}
