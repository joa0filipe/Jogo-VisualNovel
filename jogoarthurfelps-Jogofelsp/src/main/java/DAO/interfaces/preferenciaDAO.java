package DAO.interfaces;

import Model.Preferencia;

import java.util.List;


public interface preferenciaDAO {


    Preferencia getPreferencia(int id) throws Exception;

    void setPreferencia(int id) throws Exception;

    void salvarPreferencia(List<Preferencia> preferencias)throws Exception;

    void clear(int id) throws Exception;

    void novaPreferencia(Preferencia preferencias)throws Exception;

    List<Preferencia> carregarPreferencias()throws Exception;
}
