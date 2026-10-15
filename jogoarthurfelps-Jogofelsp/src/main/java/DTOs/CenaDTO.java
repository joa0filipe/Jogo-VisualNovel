package DTOs;

import java.util.List;

public record CenaDTO(
        String id,
        List<String> personagens,
        int capitulo,
        String nome_cena,
        String texto,
        List<EscolhaDTO> opcoes,
        boolean isFinal
) {}
