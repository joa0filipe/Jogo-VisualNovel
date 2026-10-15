package DTOs;

import java.util.Map;

public record EscolhaDTO(
        String idProximoCap,
        String texto,
        String itemRequerido,
        Map<String, Integer> atributoRequerido,
        String transicaoTexto,
        int custoDinheiro,
        int danoOucura,
        int modificadorDinheiro,
        String personagemAfinidade,
        int difAfinidade,
        String itemConsumido,
        String itemGanho,
        Map<String, Integer> atributosAlterados
) {}