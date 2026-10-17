package DAO.implementacao;

import DAO.interfaces.preferenciaDAO;
import Model.Configuracoes;
import Model.Preferencia;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class PreferenciaJsonDao implements preferenciaDAO {
    private final Path arquivo;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public PreferenciaJsonDao() {
        this(Path.of("jogoarthurfelps-Jogofelsp", "saves", "preferencia.json"));
    }

    public PreferenciaJsonDao(Path arquivo) {
        this.arquivo = arquivo;
    }

    @Override
    public Preferencia getPreferencia(int id) throws Exception {
        return null;
    }


    @Override
    public void setPreferencia(int id) throws Exception {
        List<Preferencia> preferencias = carregarPreferencias();
        Preferencia config = preferencias.get(id);

    }

    @Override
    public void clear(int id) throws Exception {

    }


    @Override
    public void novaPreferencia(Preferencia novaPreferencia) throws Exception {
        List<Preferencia> preferencias = carregarPreferencias();

        preferencias.add(novaPreferencia);

        salvarPreferencia(preferencias);
    }

    @Override
    public List<Preferencia> carregarPreferencias() throws Exception {
        if (Files.notExists(arquivo) || Files.size(arquivo) == 0) {
            List<Preferencia> preferencias = Configuracoes.criarPreferenciasPadrao();
            salvarPreferencia(preferencias);
            return preferencias;
        }

        List<Preferencia> preferencias;

        try (Reader reader = Files.newBufferedReader(arquivo)) {

            Type tipo = new TypeToken<List<Preferencia>>() {}.getType();

            preferencias = gson.fromJson(reader, tipo);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar preferências", e);
        }

        if (preferencias == null) {
            preferencias = new ArrayList<>();
            salvarPreferencia(preferencias);
        }

        return preferencias;
    }

    @Override
    public void salvarPreferencia(List<Preferencia> preferencias) throws Exception {
        try {
            Files.createDirectories(arquivo.getParent());

            try (Writer writer = Files.newBufferedWriter(arquivo)) {
                gson.toJson(preferencias, writer);
            }

        } catch (IOException e) {
            throw new IOException("Erro ao salvar preferencias");
        }
    }

}
