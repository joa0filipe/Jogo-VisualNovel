package DAO.implementacao;

import DAO.interfaces.conquistasDAO;
import Exceptions.CatalogoConquistasException;
import Exceptions.ConquistaNaoEncontradaException;
import Model.Conquista;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConquistasJsonDao implements conquistasDAO {
    private static final Path CAMINHO_PROJETO = Path.of("saves", "conquistas.json");
    private static final Path CAMINHO_REPOSITORIO =
            Path.of("jogoarthurfelps-Jogofelsp", "saves", "conquistas.json");
    private static final Type TIPO_LISTA = new TypeToken<List<Conquista>>() { }.getType();

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Path caminhoArquivo;

    public ConquistasJsonDao() {
        this(caminhoPadrao());
    }

    public ConquistasJsonDao(Path caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    private static Path caminhoPadrao() {
        if (Files.exists(CAMINHO_PROJETO)) {
            return CAMINHO_PROJETO;
        }
        if (Files.exists(CAMINHO_REPOSITORIO)) {
            return CAMINHO_REPOSITORIO;
        }
        return Files.isDirectory(Path.of("jogoarthurfelps-Jogofelsp"))
                ? CAMINHO_REPOSITORIO
                : CAMINHO_PROJETO;
    }

    @Override
    public List<Conquista> listarTodas() throws IOException {
        try {
            if (Files.notExists(caminhoArquivo) || Files.size(caminhoArquivo) == 0) {
                List<Conquista> iniciais = criarConquistasIniciais();
                gravar(iniciais);
                return List.copyOf(iniciais);
            }

            try (var reader = Files.newBufferedReader(caminhoArquivo)) {
                List<Conquista> conquistas = gson.fromJson(reader, TIPO_LISTA);
                validarCatalogo(conquistas);
                return List.copyOf(conquistas);
            }
        } catch (CatalogoConquistasException e) {
            throw e;
        } catch (JsonParseException e) {
            throw new CatalogoConquistasException(
                    "O arquivo de conquistas contém JSON inválido: " + caminhoArquivo, e);
        } catch (IOException e) {
            throw new CatalogoConquistasException(
                    "Não foi possível ler o catálogo de conquistas: " + caminhoArquivo, e);
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
        if (conquista.isDesbloqueada()) {
            return;
        }
        conquista.desbloquear();
        gravar(conquistas);
    }

    private void gravar(List<Conquista> conquistas) throws IOException {
        Path diretorio = caminhoArquivo.toAbsolutePath().getParent();
        Files.createDirectories(diretorio);
        try (var writer = Files.newBufferedWriter(caminhoArquivo)) {
            gson.toJson(conquistas, TIPO_LISTA, writer);
        }
    }

    private void validarCatalogo(List<Conquista> conquistas) throws IOException {
        if (conquistas == null || conquistas.isEmpty()) {
            throw new CatalogoConquistasException("O catálogo de conquistas está vazio: " + caminhoArquivo);
        }
        Set<String> ids = new HashSet<>();
        for (Conquista conquista : conquistas) {
            if (conquista == null
                    || vazio(conquista.getId())
                    || vazio(conquista.getNome())
                    || vazio(conquista.getDescricao())
                    || vazio(conquista.getCriterio())
                    || !ids.add(conquista.getId())) {
                throw new CatalogoConquistasException(
                        "O catálogo de conquistas possui dados inválidos: " + caminhoArquivo);
            }
        }
    }

    private Conquista encontrarPorId(List<Conquista> conquistas, String id) {
        return conquistas.stream()
                .filter(conquista -> conquista.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ConquistaNaoEncontradaException(id));
    }

    private boolean vazio(String valor) {
        return valor == null || valor.isBlank();
    }

    private List<Conquista> criarConquistasIniciais() {
        return List.of(
                new Conquista("primeira_escolha", "Primeiros passos",
                        "Faça sua primeira escolha na história.", "ESCOLHA_REALIZADA", false),
                new Conquista("coracao_da_mansao", "O coração da mansão",
                        "Chegue à sala de Blackglass.", "CENA:C02_001", false),
                new Conquista("final_preco_certo", "O preço certo",
                        "Conclua a história escolhendo o final O Preço Certo.",
                        "FINAL:C10_DINHEIRO", false),
                new Conquista("final_parceiros", "Parceiros até o fim",
                        "Conclua a história no final Parceiros.",
                        "FINAL:C10_PARCEIROS", false),
                new Conquista("final_blackglass", "Tudo para si",
                        "Conclua a história no final Tudo Para Si.",
                        "FINAL:C10_BLACKGLASS", false),
                new Conquista("final_quebrando_vidro", "Quebrando o vidro",
                        "Descubra e conclua o final secreto.",
                        "FINAL:C10_SEGREDO", false)
        );
    }
}
