package DAO.implementacao;

import DAO.interfaces.conquistasDAO;
import Model.Conquista;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ConquistasJsonDao implements conquistasDAO {
    private static final Path CAMINHO_REPOSITORIO =
            Path.of("jogoarthurfelps-Jogofelsp", "saves", "conquistas.json");
    private static final Path CAMINHO_PROJETO = Path.of("saves", "conquistas.json");
    private static final Type TIPO_LISTA = new TypeToken<List<Conquista>>() { }.getType();

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Path caminhoArquivo;

    public ConquistasJsonDao() {
        this(Files.exists(CAMINHO_PROJETO) || !Files.exists(CAMINHO_REPOSITORIO)
                ? CAMINHO_PROJETO
                : CAMINHO_REPOSITORIO);
    }

    public ConquistasJsonDao(Path caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    @Override
    public List<Conquista> listarTodas() throws IOException {
        if (!Files.exists(caminhoArquivo)) {
            throw new IOException("Arquivo de conquistas não encontrado: " + caminhoArquivo);
        }

        try (var reader = Files.newBufferedReader(caminhoArquivo)) {
            List<Conquista> conquistas = gson.fromJson(reader, TIPO_LISTA);
            if (conquistas == null) {
                throw new IOException("Arquivo de conquistas vazio ou inválido: " + caminhoArquivo);
            }
            return conquistas;
        }
    }

    @Override
    public boolean estaDesbloqueada(String id) throws IOException {
        return encontrarPorId(listarTodas(), id).isDesbloqueada();
    }

    @Override
    public void desbloquear(String id) throws IOException {
        List<Conquista> conquistas = new ArrayList<>(listarTodas());
        Conquista conquista = encontrarPorId(conquistas, id);
        conquista.desbloquear();

        Files.createDirectories(caminhoArquivo.toAbsolutePath().getParent());
        try (var writer = Files.newBufferedWriter(caminhoArquivo)) {
            gson.toJson(conquistas, TIPO_LISTA, writer);
        }
    }

    private Conquista encontrarPorId(List<Conquista> conquistas, String id) {
        return conquistas.stream()
                .filter(conquista -> conquista.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Conquista desconhecida: " + id));
    }
}
