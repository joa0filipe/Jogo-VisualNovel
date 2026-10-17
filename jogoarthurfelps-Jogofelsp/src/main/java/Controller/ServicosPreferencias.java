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
        int i = 0;
        for(Preferencia config : Configuracoes.criarPreferenciasPadrao()){
            boolean existe = false;
            for (Preferencia p : preferencias) {
                if (p.getId().equals(config.getId())) {
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                preferencias.add(config);
                alterou = true;
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

        Preferencia p = preferencias.get(id);

        p.setAtivo(!p.isAtivo());

        dao.salvarPreferencia(preferencias);
    }

    public boolean isAutoSave() throws Exception {
        return dao.carregarPreferencias().get(0).isAtivo();
    }

    public boolean isMostrarEscolhas() throws Exception{
        return dao.carregarPreferencias().get(1).isAtivo();
    }



}
