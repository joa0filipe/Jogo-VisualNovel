package Loader;

import Model.Item;
import Repository.RepositorioDeItens;

public class LoaderDeItens{

    private RepositorioDeItens repositorioItens;


    public LoaderDeItens(RepositorioDeItens repositorioitens) {
        this.repositorioItens = repositorioitens;
    }

    public void carregarItens() {

        Item fragmentoBlackglass = new Item(
                "Fragmento de Blackglass",
                "Um pequeno fragmento negro encontrado durante o primeiro roubo. "
                        + "Seu formato parece combinar com um encaixe existente em Blackglass."
        );

        Item mapaManutencao = new Item(
                "Mapa de Manutenção",
                "Um mapa parcial de corredores, túneis e áreas de manutenção de Greyhaven, "
                        + "entregue por Julian."
        );

        Item chaveImprovisada = new Item(
                "Chave Improvisada",
                "Uma pequena chave feita de metal dobrado. "
                        + "Pode abrir algumas portas de serviço de Greyhaven."
        );

        Item cartaoAcesso = new Item(
                "Cartão de Acesso",
                "Um cartão de funcionário roubado dentro de Greyhaven. "
                        + "Permite acessar algumas áreas restritas da prisão."
        );

        Item sinalDante = new Item(
                "Sinal de Dante",
                "Representa a lealdade demonstrada ao proteger Dante durante o interrogatório. "
                        + "Essa decisão pode fazer Dante confiar mais no protagonista posteriormente."
        );

        Item blackglass = new Item(
                "Blackglass",
                "Uma pequena pedra negra pertencente a Victor Ashcroft. "
                        + "Apesar de parecer uma joia extremamente valiosa, na verdade funciona como "
                        + "uma chave para informações secretas."
        );

        Item provasAshcroft = new Item(
                "Provas contra Ashcroft",
                "Uma cópia dos arquivos secretos desbloqueados através de Blackglass. "
                        + "Contém registros financeiros, contratos, pagamentos e outras informações "
                        + "comprometedoras relacionadas a Victor Ashcroft."
        );


        repositorioItens.addItem(
                "FRAGMENTO BLACKGLASS",
                fragmentoBlackglass
        );

        repositorioItens.addItem(
                "MAPA_MANUTENCAO",
                mapaManutencao
        );

        repositorioItens.addItem(
                "CHAVE_IMPROVISADA",
                chaveImprovisada
        );

        repositorioItens.addItem(
                "CARTAO_ACESSO",
                cartaoAcesso
        );

        repositorioItens.addItem(
                "SINAL_DANTE",
                sinalDante
        );

        repositorioItens.addItem(
                "BLACKGLASS",
                blackglass
        );

        repositorioItens.addItem(
                "PROVAS_ASHCROFT",
                provasAshcroft
        );
    }
}