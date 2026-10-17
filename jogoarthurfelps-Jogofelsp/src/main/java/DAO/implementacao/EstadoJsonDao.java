package DAO.implementacao;

import DAO.interfaces.estadoDAO;
import Exceptions.InvalidSaveDataException;
import Exceptions.InvalidSlotException;
import Exceptions.LoadException;
import Exceptions.SaveException;
import Model.EstadoDeJogo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EstadoJsonDao implements estadoDAO {
    private static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final Path diretorioSaves;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public EstadoJsonDao() {
        this(Path.of("jogoarthurfelps-Jogofelsp", "saves"));
    }

    public EstadoJsonDao(Path diretorioSaves) {
        this.diretorioSaves = diretorioSaves;
    }

    private Path getCaminho(int slot) {
        if (slot < 1 || slot > 3) {
            throw new InvalidSlotException(slot);
        }
        return diretorioSaves.resolve("save" + slot + ".json");
    }

    @Override
    public EstadoDeJogo getEstado(int slot) throws IOException {
        Path arquivo = getCaminho(slot);
        if (Files.notExists(arquivo)) {
            throw new LoadException("Não há uma partida salva no slot " + slot + ".");
        }

        try (Reader reader = Files.newBufferedReader(arquivo)) {
            EstadoDeJogo estado = gson.fromJson(reader, EstadoDeJogo.class);
            if (estado == null) {
                throw new LoadException("Não há uma partida salva no slot " + slot + ".");
            }
            validarEstado(estado, arquivo);
            return estado;
        } catch (InvalidSaveDataException | LoadException e) {
            throw e;
        } catch (JsonParseException e) {
            throw new InvalidSaveDataException("JSON inválido no save " + arquivo + ".", e);
        } catch (IOException e) {
            throw new LoadException("Não foi possível ler o save do slot " + slot + ".", e);
        }
    }

    @Override
    public void salvar(int slot, EstadoDeJogo state) throws IOException {
        Path arquivo = getCaminho(slot);
        validarEstado(state, arquivo);
        try {
            Files.createDirectories(arquivo.getParent());
            try (Writer writer = Files.newBufferedWriter(arquivo)) {
                gson.toJson(state, writer);
            }
        } catch (IOException e) {
            throw new SaveException("Erro ao salvar a partida no slot " + slot + ".", e);
        }
    }

    @Override
    public void limpar(int slot) throws IOException {
        Path arquivo = getCaminho(slot);
        try {
            Files.createDirectories(arquivo.getParent());
            try (Writer writer = Files.newBufferedWriter(arquivo)) {
                gson.toJson(null, writer);
            }
        } catch (IOException e) {
            throw new SaveException("Erro ao excluir a partida do slot " + slot + ".", e);
        }
    }

    @Override
    public boolean temSave(int slot) throws IOException {
        Path arquivo = getCaminho(slot);
        if (Files.notExists(arquivo)) {
            return false;
        }
        return lerEstadoSeExiste(arquivo) != null;
    }

    private EstadoDeJogo lerEstadoSeExiste(Path arquivo) throws IOException {
        try (Reader reader = Files.newBufferedReader(arquivo)) {
            EstadoDeJogo estado = gson.fromJson(reader, EstadoDeJogo.class);
            if (estado != null) {
                validarEstado(estado, arquivo);
            }
            return estado;
        } catch (InvalidSaveDataException e) {
            throw e;
        } catch (JsonParseException e) {
            throw new InvalidSaveDataException("JSON inválido no save " + arquivo + ".", e);
        } catch (IOException e) {
            throw new LoadException("Não foi possível verificar o save " + arquivo + ".", e);
        }
    }

    private void validarEstado(EstadoDeJogo estado, Path arquivo) throws InvalidSaveDataException {
        if (estado == null
                || estado.getCena() == null
                || estado.getCena().isBlank()
                || estado.getProtagonista() == null
                || estado.getProtagonista().getNome() == null
                || estado.getProtagonista().getNome().isBlank()) {
            throw new InvalidSaveDataException(
                    "Dados obrigatórios ausentes no save " + arquivo + ".");
        }
        try {
            LocalDateTime.parse(estado.getDateTime(), FORMATO_DATA);
        } catch (DateTimeParseException | NullPointerException e) {
            throw new InvalidSaveDataException("Data inválida no save " + arquivo + ".", e);
        }
    }
}


