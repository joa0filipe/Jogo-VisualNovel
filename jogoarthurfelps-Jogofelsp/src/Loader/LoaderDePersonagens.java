package Loader;

import Model.Personagens;
import Repository.RepositorioDePersonagens;

public class LoaderDePersonagens {

    private RepositorioDePersonagens repositorio;

    public LoaderDePersonagens(RepositorioDePersonagens repositorio) {
        this.repositorio = repositorio;
    }

    public void carregarPersonagens() {

        Personagens dante = new Personagens(
                "Dante",
                "Dante é o parceiro de longa data do protagonista e participa com ele do roubo de Blackglass. "
                        + "Carismático, impulsivo e habilidoso, costuma usar o humor mesmo nas situações mais perigosas. "
                        + "Apesar da amizade entre os dois, Dante possui seus próprios interesses e pode tomar decisões "
                        + "questionáveis quando grandes quantias de dinheiro estão envolvidas. A confiança entre ele e o "
                        + "protagonista pode aumentar ou diminuir ao longo da história, alterando diretamente sua lealdade."
        );

        Personagens julian = new Personagens(
                "Julian Ashcroft",
                "Julian Ashcroft é filho de Victor Ashcroft e um dos poucos membros da família que conhece parte dos "
                        + "segredos envolvendo Blackglass. Inteligente, observador e desconfiado, Julian foi enviado para "
                        + "Greyhaven depois de assumir as consequências de um crime ligado ao próprio pai. Ele conhece "
                        + "informações sobre construções, passagens secretas e negócios da família Ashcroft. Sua relação com "
                        + "o protagonista pode transformá-lo em um importante aliado durante a fuga e na busca pela verdade."
        );

        Personagens miller = new Personagens(
                "Miller",
                "Miller é uma detenta influente de Greyhaven que controla uma rede de favores, informações e contrabando "
                        + "dentro da prisão. Inteligente, prática e difícil de enganar, ela acredita que praticamente tudo "
                        + "possui um preço. Miller consegue encontrar ferramentas, contatos e informações que seriam "
                        + "impossíveis para outros prisioneiros conseguirem. Dependendo das escolhas do protagonista, ela "
                        + "pode se tornar uma aliada valiosa ou alguém pouco disposta a oferecer qualquer tipo de ajuda."
        );

        Personagens ward = new Personagens(
                "Inspector Elias Ward",
                "Elias Ward é o inspetor responsável pela investigação do roubo na Mansão Ashcroft. Calmo, persistente e "
                        + "extremamente observador, ele prefere pressionar suspeitos através de perguntas e contradições em "
                        + "vez de ameaças diretas. Ward desconfia que Blackglass possui importância muito maior do que "
                        + "Victor Ashcroft admite publicamente. Durante o interrogatório do protagonista, ele tenta descobrir "
                        + "quem é Dante, onde ele está e por que a família Ashcroft está tão desesperada para proteger a pedra."
        );

        Personagens victor = new Personagens(
                "Victor Ashcroft",
                "Victor Ashcroft é um poderoso empresário londrino, chefe da família Ashcroft e proprietário original de "
                        + "Blackglass. Frio, calculista e acostumado a controlar pessoas através de dinheiro e influência, "
                        + "Victor construiu uma extensa rede de empresas, contatos e negócios secretos. Para o público, "
                        + "Blackglass parece apenas uma joia extremamente valiosa, mas Victor sabe que seu verdadeiro valor "
                        + "está nas informações que ela pode desbloquear. Ele fará o necessário para impedir que esses "
                        + "segredos caiam nas mãos erradas."
        );

        repositorio.addPersonagem("VICTOR", victor);
        repositorio.addPersonagem("WARD", ward);
        repositorio.addPersonagem("DANTE", dante);
        repositorio.addPersonagem("JULIAN", julian);
        repositorio.addPersonagem("MILLER", miller);
    }
}