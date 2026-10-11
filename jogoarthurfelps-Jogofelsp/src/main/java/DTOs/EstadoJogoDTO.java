package DTOs;

import java.time.LocalDateTime;

public record EstadoJogoDTO (
        String nomeJogador,
        String capitulo,
        String nomeCena,
        String dataSalvamento )  {
}
