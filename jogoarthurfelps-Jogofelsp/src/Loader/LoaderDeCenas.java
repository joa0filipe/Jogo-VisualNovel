package Loader;
import Model.Cena;
import Model.Escolhas;
import Model.Personagens;
import Model.Protagonista;
import Repository.RepositorioDeCenas;
import Repository.RepositorioDePersonagens;
import Model.Item;
import Repository.RepositorioDeItens;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class LoaderDeCenas {
    private RepositorioDeCenas repositorioCenas;
    private RepositorioDePersonagens repositorioPersonagens;
    private Protagonista protagonista;
    private RepositorioDeItens repositorioItens;
    public LoaderDeCenas(RepositorioDeCenas repositorioCenas, RepositorioDePersonagens repositorioPersonagens, Protagonista protagonista, RepositorioDeItens repositorioItens) {
        this.repositorioCenas = repositorioCenas;
        this.repositorioPersonagens = repositorioPersonagens;
        this.protagonista = protagonista;
        this.repositorioItens = repositorioItens;
    }
    private String formatTexto(String texto, Object... argumentos) {
        if (texto == null) {
            return "";
        }
        StringBuilder resultado = new StringBuilder();
        int indiceArg = 0;
        for (int i = 0; i < texto.length(); i++) {
            char atual = texto.charAt(i);
            if (atual == '%') {
                if (i + 1 < texto.length() && texto.charAt(i + 1) == 's') {
                    Object valor;
                    if (argumentos != null && indiceArg < argumentos.length) {
                        valor = argumentos[indiceArg++];
                    } else if (protagonista != null) {
                        valor = protagonista.getNome();
                    } else {
                        valor = "%s";
                    }
                    resultado.append(valor);
                    i++;
                    continue;
                }
                resultado.append('%');
                continue;
            }
            resultado.append(atual);
        }
        return resultado.toString();
    }
    public void createCena() {
        Item sinalDante = repositorioItens.getItem("SINAL_DANTE");
        Item mapaManutencao = repositorioItens.getItem("MAPA_MANUTENCAO");
        Item chaveImprovisada = repositorioItens.getItem("CHAVE_IMPROVISADA");
        Item cartaoAcesso = repositorioItens.getItem("CARTAO_ACESSO");
        Item fragmentoBlackglass = repositorioItens.getItem("FRAGMENTO_BLACKGLASS");
        Item blackglass = repositorioItens.getItem("BLACKGLASS");
        Item provasAshcroft = repositorioItens.getItem("PROVAS_ASHCROFT");
        Personagens victor = repositorioPersonagens.getPersonagem("VICTOR");
        Personagens dante = repositorioPersonagens.getPersonagem("DANTE");
        Personagens julian = repositorioPersonagens.getPersonagem("JULIAN");
        Personagens miller = repositorioPersonagens.getPersonagem("MILLER");
        Personagens ward = repositorioPersonagens.getPersonagem("WARD");
        Map<String, Integer> charme3 = Map.of("charme", 3);
        Map<String, Integer> furtividade3 = Map.of("furtividade", 3);
        Map<String, Integer> saude50 = Map.of("saude", 50);
        Map<String, Integer> astucia2 = Map.of("astucia", 2);
        Map<String, Integer> astucia3 = Map.of("astucia", 3);
        Map<String, Integer> charme2 = Map.of("charme", 2);
        Map<String, Integer> furtividade2 = Map.of("furtividade", 2);
        Map<String, Integer> astucia4 = Map.of("astucia", 4);
        Map<String, Integer> furtividade4 = Map.of("furtividade", 4);
        Map<String, Integer> ganhaAstucia1 = Map.of("astucia", 1);
        Map<String, Integer> perdeAstucia1 = Map.of("astucia", -1);
        Map<String, Integer> ganhaCharme1 = Map.of("charme", 1);
        Map<String, Integer> perdeCharme1 = Map.of("charme", -1);
        Map<String, Integer> ganhaFurtividade1 = Map.of("furtividade", 1);
        Map<String, Integer> perdeFurtividade1 = Map.of("furtividade", -1);
        Map<String, Integer> semAlteracaoAtributos = Map.of();
        Cena cena01001 = new Cena("C01_001", new ArrayList<>(List.of(dante)), formatTexto("""
                Presente. Mansão Ashcroft, Londres.

                — %s?

                A voz de Dante estala no comunicador.

                %s pisca.

                Por alguns segundos, sua mente ainda estava presa em uma
                lembrança da infância.

                — %s!

                — Eu ouvi.

                — Você ficou parado por quase vinte segundos.

                — Estava pensando.

                — Estamos roubando uma das famílias mais perigosas de Londres.
                Pode ter crise existencial depois?

                %s ajusta as luvas e observa o corredor à sua frente.

                A Mansão Ashcroft é ainda maior por dentro do que parecia nas
                plantas.

                Quadros antigos ocupam as paredes, esculturas estão posicionadas
                entre grandes colunas de mármore e câmeras acompanham cada
                movimento.

                Do outro lado daquele corredor fica a Galeria Ashcroft.

                E dentro dela está o motivo de toda aquela noite.

                Blackglass.

                Uma pequena pedra negra, lapidada no formato de um olho,
                avaliada em milhões de libras.

                Dante volta a falar pelo comunicador.

                — Temos três maneiras de atravessar esse corredor.

                %s observa o movimento das câmeras e dos seguranças.

                É hora de decidir.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "De Volta ao Presente", 1);
        cena01001.addEscolha(new Escolhas("C01_002", "Seguir pelas sombras e evitar as câmeras.", formatTexto("""
                %s espera a câmera virar para o lado oposto.

                Um passo.

                Depois outro.

                Ele se move entre as esculturas, usando cada ponto cego do
                corredor.

                Dante acompanha tudo pelo comunicador.

                — Às vezes eu esqueço o quanto você é irritantemente silencioso.

                %s alcança a porta da galeria sem chamar atenção.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, null, 0, null, null, ganhaFurtividade1));
        cena01001.addEscolha(new Escolhas("C01_002", "Invadir o painel de segurança e congelar as câmeras.", formatTexto("""
                %s se aproxima discretamente do painel de manutenção.

                Ele remove a tampa e conecta o pequeno dispositivo preparado
                por Dante.

                — Você tem trinta segundos antes do sistema perceber alguma
                coisa — Dante avisa.

                %s analisa os códigos.

                Uma tentativa.

                Falha.

                Segunda tentativa.

                As câmeras param.

                — Consegui.

                — Vinte e oito segundos agora — Dante responde.

                — Mais do que preciso.

                %s atravessa o corredor rapidamente e chega à galeria.

                — Nada mal — Dante comenta.

                — Tente parecer mais impressionado.

                — Não exagera.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, astucia2, 0, 0, 0, "Dante", 1, null, null, ganhaAstucia1));
        cena01001.addEscolha(new Escolhas("C01_002", "Se passar por um funcionário da manutenção.", formatTexto("""
                %s pega uma prancheta deixada sobre uma mesa próxima.

                Ele ajeita a postura e começa a caminhar como se trabalhasse
                naquela mansão há anos.

                Um segurança bloqueia o caminho.

                — Identificação.

                %s suspira, demonstrando irritação.

                — Vazamento no circuito térmico da galeria. De novo.

                O segurança franze a testa.

                — Ninguém me avisou.

                — Então pode explicar para Ashcroft por que metade do sistema
                de segurança desligou quando o circuito superaquecer.

                O homem hesita.

                Depois abre passagem.

                — Vai logo.

                %s apenas balança a cabeça e continua andando.

                Assim que passa pela porta, Dante fala pelo comunicador:

                — Eu quase acreditei nisso.

                — Esse é o objetivo.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, charme2, 0, 0, 0, null, 0, null, null, ganhaCharme1));
        repositorioCenas.addCena(cena01001);
        Cena cena01002 = new Cena("C01_002", new ArrayList<>(List.of(dante)), formatTexto("""
                %s entra na Galeria Ashcroft.

                O ambiente é silencioso.

                Silencioso demais.

                Relógios antigos, pinturas e objetos históricos ocupam toda a
                sala, mas nenhum deles chama sua atenção.

                No centro da galeria está uma estrutura de vidro reforçado.

                Dentro dela:

                Blackglass.

                Mesmo pequena, a pedra parece absorver a pouca luz ao redor.

                — Finalmente — Dante diz.

                %s se aproxima.

                — Tem certeza de que esse é o sistema certo?

                — Absoluta.

                — Sua "certeza absoluta" quase nos explodiu em Manchester.

                — Aquilo foi diferente.

                — Você disse exatamente isso em Manchester.

                Dante ignora o comentário.

                — Quando eu desligar os sensores, você terá quarenta segundos.

                %s observa a vitrine.

                Há sensores nas laterais.

                Uma trava eletrônica na base.

                E algo mais.

                Um pequeno encaixe metálico que não aparecia nas plantas.

                — Preparado? — Dante pergunta.

                %s respira fundo.

                — Vai.

                Dante começa a contagem.

                — Três...

                Dois...

                Um.

                Um clique ecoa pela galeria.

                Os sensores são desativados.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "A Galeria Ashcroft", 1);
        cena01002.addEscolha(new Escolhas("C02_001", "Examinar o mecanismo antes de tocar em Blackglass.", formatTexto("""
                %s não toca imediatamente na vitrine.

                Em vez disso, observa as dobradiças, os sensores e a base
                metálica.

                — O que você está fazendo? — Dante pergunta.

                — Pensando.

                — Temos menos de quarenta segundos.

                %s percebe que existe um segundo mecanismo escondido sob o
                suporte de Blackglass.

                Ele não aparece nas plantas.

                — Dante.

                — O quê?

                — Tem alguma coisa errada aqui.

                — Define "errada".

                — Depois.

                %s memoriza a posição do mecanismo e começa a trabalhar na
                trava.

                Talvez aquela informação seja útil mais tarde.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, astucia3, 0, 0, 0, null, 0, null, null, ganhaAstucia1));
        cena01002.addEscolha(new Escolhas("C02_001", "Ir direto para Blackglass.", formatTexto("""
                %s ignora os detalhes do mecanismo.

                — Objetivo primeiro. Curiosidade depois.

                Dante ri pelo comunicador.

                — Essa é provavelmente a coisa mais responsável que você já
                disse.

                %s posiciona as ferramentas sobre a trava.

                — Aproveita. Talvez nunca aconteça de novo.

                Ele começa a abrir a proteção.

                Dante acompanha cada segundo.

                — Vinte e cinco.

                — Eu sei.

                — Vinte.

                — Dante.

                — Certo. Silêncio.

                A trava começa a ceder.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Dante", 1, null, null, semAlteracaoAtributos));
        cena01002.addEscolha(new Escolhas("C02_001", "Perguntar a Dante se ele está escondendo alguma coisa.", formatTexto("""
                %s mantém as mãos sobre a trava.

                — Dante.

                — O quê?

                — Você está quieto demais.

                — Estamos no meio de um roubo.

                — Você fala mais quando está nervoso.

                Silêncio.

                Alguns segundos passam.

                — Abre a vitrine, %s.

                %s para por um instante.

                A resposta demorou mais do que deveria.

                — Você não respondeu.

                — Porque não temos tempo para isso.

                Dante muda de assunto imediatamente.

                — Quinze segundos.

                %s continua trabalhando.

                Mas agora uma dúvida permanece.

                Talvez Dante saiba mais sobre Blackglass do que contou.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Dante", -1, null, null, ganhaAstucia1));
        repositorioCenas.addCena(cena01002);
        Cena cena02001 = new Cena("C02_001", new ArrayList<>(List.of(dante)), formatTexto("""
                A trava finalmente cede.
        
                %s aproxima a mão de Blackglass.
        
                Por um instante, tudo parece ter funcionado.
        
                Então uma luz vermelha se acende na base da vitrine.
        
                — Dante...
        
                — Eu vi.
        
                Um som grave percorre as paredes da galeria.
        
                Depois outro.
        
                E então o alarme explode pela mansão inteira.
        
                As luzes do teto mudam para vermelho.
        
                — Isso não estava nas plantas! — Dante grita pelo comunicador.
        
                Uma proteção de aço começa a descer ao redor de Blackglass.
        
                %s tenta puxar a trava novamente.
        
                Não responde.
        
                — Quanto tempo? — pergunta %s.
        
                Dante verifica o sistema.
        
                — Antes dos guardas chegarem?
        
                — Sim.
        
                — Trinta segundos.
        
                Passos começam a ecoar no andar inferior.
        
                — Talvez vinte e cinco.
        
                %s olha para Blackglass.
        
                Depois para a saída.
        
                Dante surge na entrada lateral da galeria.
        
                Ele havia entrado por outra rota para dar apoio na retirada.
        
                — Temos que sair! — Dante diz.
        
                A proteção continua descendo.
        
                Ainda existe espaço suficiente para alguém alcançar a pedra.
        
                Mas não por muito tempo.
        
                Dante olha para %s.
        
                — Decide agora!
        
                Os passos dos guardas estão cada vez mais próximos.
        
                Há tempo para apenas uma tentativa.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "Trinta Segundos", 2);
        cena02001.addEscolha(new Escolhas("C03_001A", "Ignorar Dante e tentar pegar Blackglass pessoalmente.", formatTexto("""
                — Eu consigo.
        
                Dante olha para a proteção descendo.
        
                — %s, deixa isso!
        
                %s ignora o aviso.
        
                Ele se joga contra a vitrine e enfia o braço pela abertura.
        
                Seus dedos passam a poucos centímetros de Blackglass.
        
                Mais um pouco.
        
                A proteção continua descendo.
        
                — Sai daí! — Dante grita.
        
                %s força o braço ainda mais.
        
                Seus dedos finalmente encostam na pedra.
        
                Mas não conseguem segurá-la.
        
                Um barulho de portas sendo abertas ecoa pelo corredor.
        
                Dante olha para a entrada da galeria.
        
                Guardas.
        
                Depois olha para %s.
        
                — Droga...
        
                — Dante!
        
                A proteção fecha completamente.
        
                Blackglass permanece dentro.
        
                Dante recua em direção à saída.
        
                — Eu volto por você!
        
                — Dante!
        
                Ele desaparece pelo corredor segundos antes dos guardas entrarem.
        
                %s tenta correr.
        
                Tarde demais.
        
                Um segurança o acerta por trás.
        
                %s cai de joelhos.
        
                Quando ergue o rosto, três armas estão apontadas para ele.
        
                Blackglass continua intocada dentro da proteção.
        
                Dante conseguiu fugir.
        
                Mas eles não roubaram nada.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 1, 0, "Dante", -1, null, null, perdeAstucia1));
        cena02001.addEscolha(new Escolhas("C03_001B", "Mandar Dante pegar Blackglass enquanto você segura os guardas.", formatTexto("""
                %s olha para a porta.
        
                Depois para Dante.
        
                — Pega a pedra.
        
                Dante demora um instante para entender.
        
                — O quê?
        
                — Eu seguro eles. Pega Blackglass!
        
                — Você vai ficar preso!
        
                — DANTE!
        
                Os primeiros guardas aparecem no corredor.
        
                Dante não discute mais.
        
                Ele corre até a vitrine.
        
                %s vira uma mesa no corredor no momento em que o primeiro segurança entra.
        
                — Ei!
        
                O guarda tenta avançar.
        
                %s o empurra contra a parede.
        
                Atrás dele, Dante se joga no chão e estende o braço pela última abertura
                da proteção.
        
                Os dedos dele alcançam Blackglass.
        
                — Peguei!
        
                Outro guarda entra na galeria.
        
                %s recebe um golpe no ombro.
        
                — Vai! — grita %s.
        
                Dante permanece parado por uma fração de segundo.
        
                Blackglass está em sua mão.
        
                — Eu volto por você!
        
                — Só vai!
        
                Dante desaparece pela passagem lateral.
        
                %s tenta seguir.
        
                Um segurança segura seu braço.
        
                Outro bloqueia a saída.
        
                Em poucos segundos, ele está no chão.
        
                Algema no pulso.
        
                Sangue no canto da boca.
        
                Mas Blackglass não está mais na mansão.
        
                E Dante está com ela.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 1, 0, "Dante", 2, null, null, semAlteracaoAtributos));
        cena02001.addEscolha(new Escolhas("C03_001B", "Improvisar uma forma de travar a proteção.", formatTexto("""
                %s observa o mecanismo.
        
                A proteção desce.
        
                Os guardas se aproximam.
        
                Dante continua gritando alguma coisa.
        
                Mas %s quase não escuta.
        
                Há um pequeno espaço entre a estrutura metálica e o trilho.
        
                O mesmo mecanismo estranho que ele havia visto antes.
        
                Talvez...
        
                — Me dá sua ferramenta! — grita %s.
        
                — Qual delas?!
        
                — A maior!
        
                — Isso não ajuda!
        
                Dante joga uma ferramenta metálica.
        
                %s a pega no ar e enfia entre a proteção e o trilho.
        
                CRACK.
        
                A proteção para.
        
                Dante arregala os olhos.
        
                — Você conseguiu?
        
                — Ainda não!
        
                O metal começa a entortar.
        
                — Pega a pedra!
        
                Dante se joga no chão.
        
                Ele alcança Blackglass.
        
                No mesmo instante, a ferramenta quebra.
        
                A proteção desaba novamente.
        
                Um pequeno fragmento negro se solta da base do mecanismo e cai próximo
                aos pés de %s.
        
                Ele pega o fragmento rapidamente e o coloca no bolso.
        
                — Consegui! — Dante grita.
        
                Blackglass está na mão dele.
        
                Os guardas entram na galeria.
        
                — Corre! — diz %s.
        
                — E você?
        
                %s olha para os homens bloqueando a saída.
        
                — Parece que vou ter que improvisar de novo.
        
                Dante hesita.
        
                — Eu volto.
        
                — É melhor voltar.
        
                Dante desaparece pela passagem lateral.
        
                Segundos depois, %s é derrubado no chão.
        
                As algemas fecham em seus pulsos.
        
                Blackglass desapareceu com Dante.
        
                Mas no bolso de %s existe algo que ninguém percebeu.
        
                Um pequeno fragmento negro.
        
                Talvez parte da vitrine.
        
                Talvez algo mais importante.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, astucia4, 0, 1, 0, "Dante", 1, null, fragmentoBlackglass, ganhaAstucia1));
        repositorioCenas.addCena(cena02001);
        Cena cena03001A = new Cena("C03_001A", new ArrayList<>(List.of(ward)), formatTexto("""
                Algumas horas depois.
        
                %s está sentado em uma pequena sala de interrogatório.
        
                As mãos estão algemadas à mesa.
        
                Uma lâmpada branca ilumina o ambiente.
        
                Nenhuma janela.
        
                Nenhum relógio.
        
                Apenas uma porta de metal e uma câmera no canto da sala.
        
                A porta se abre.
        
                Um homem de terno escuro entra carregando uma pasta.
        
                Ele coloca a pasta sobre a mesa e se senta.
        
                — Inspector Elias Ward.
        
                %s não responde.
        
                Ward abre a pasta.
        
                A primeira fotografia mostra Dante entrando na propriedade Ashcroft.
        
                A segunda mostra %s sendo retirado da mansão algemado.
        
                Ward coloca uma terceira fotografia sobre a mesa.
        
                Blackglass.
        
                Ainda dentro da vitrine.
        
                — Seu amigo conseguiu fugir.
        
                Ward empurra a fotografia para frente.
        
                — Mas vocês não levaram nada.
        
                %s observa a imagem.
        
                — Então imagino que essa conversa acabou.
        
                Ward sorri.
        
                — Na verdade, é justamente por isso que ela está começando.
        
                Ele se inclina sobre a mesa.
        
                — Victor Ashcroft tem dezenas de objetos que valem mais dinheiro do que
                aquela pedra.
        
                — E?
        
                — E mesmo assim, quando você foi preso, ele perguntou três vezes se
                Blackglass estava segura antes de perguntar qualquer coisa sobre você.
        
                Ward fecha a pasta.
        
                — Então eu quero saber duas coisas.
        
                Ele aponta para a fotografia de Dante.
        
                — Quem é seu parceiro?
        
                Depois aponta para Blackglass.
        
                — E por que vocês queriam tanto aquela pedra?
        
                %s permanece em silêncio.
        
                Ward espera.
        
                — Pense bem antes de responder.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "Mãos Vazias", 3);
        cena03001A.addEscolha(new Escolhas("C04_001A", "Entregar informações sobre Dante.", formatTexto("""
                %s olha para a fotografia.
        
                Depois para Ward.
        
                — Dante.
        
                Ward pega uma caneta.
        
                — Sobrenome?
        
                %s começa a falar.
        
                Endereços antigos.
        
                Lugares onde costumavam se encontrar.
        
                Contatos.
        
                Possíveis esconderijos.
        
                Ward anota tudo.
        
                Quando termina, fecha o caderno.
        
                — Foi mais fácil do que eu esperava.
        
                — Não confunda cooperação com amizade.
        
                Ward se levanta.
        
                — Imagino que Dante diria a mesma coisa.
        
                A porta se abre.
        
                Dois guardas entram.
        
                Enquanto é levado para fora, %s percebe uma coisa.
        
                Se Dante descobrir que ele falou...
        
                provavelmente nunca esquecerá.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Dante", -3, null, null, semAlteracaoAtributos));
        cena03001A.addEscolha(new Escolhas("C04_001A", "Inventar uma história sobre como conheceu Dante.", formatTexto("""
                %s olha novamente para a fotografia.
        
                — Nunca soube o nome verdadeiro dele.
        
                Ward permanece imóvel.
        
                — Continue.
        
                — Conheci o sujeito alguns dias atrás.
        
                — Onde?
        
                — Num bar em Camden.
        
                — Qual?
        
                %s responde sem hesitar.
        
                — The Red Crown.
        
                Ward observa seu rosto.
        
                — E ele simplesmente ofereceu um roubo milionário para um desconhecido?
        
                — Pessoas tomam decisões ruins quando bebem.
        
                Ward permanece em silêncio.
        
                %s sustenta o olhar.
        
                Depois de alguns segundos, Ward fecha a pasta.
        
                — Interessante.
        
                — Acreditou?
        
                — Eu não disse isso.
        
                Ele se levanta.
        
                — Mas você mente melhor do que a maioria.
        
                %s não sabe se aquilo foi um elogio.
        
                Provavelmente não.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, astucia3, 0, 0, 0, null, 0, null, null, ganhaAstucia1));
        cena03001A.addEscolha(new Escolhas("C04_001A", "Não dizer nada sobre Dante.", formatTexto("""
                %s permanece em silêncio.
        
                Ward espera.
        
                Dez segundos.
        
                Vinte.
        
                — Nome do seu parceiro?
        
                Nenhuma resposta.
        
                — Onde ele está?
        
                Silêncio.
        
                — Quem planejou o roubo?
        
                %s continua olhando para a mesa.
        
                Ward fecha a pasta lentamente.
        
                — Lealdade entre ladrões.
        
                Ele se levanta.
        
                — Sempre parece bonita até alguém colocar dinheiro suficiente na frente.
        
                Ward caminha até a porta.
        
                Antes de sair, olha novamente para %s.
        
                — Espero que Dante tenha a mesma consideração por você.
        
                A porta fecha.
        
                %s fica sozinho.
        
                Pelo menos por enquanto, Dante está protegido.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Dante", 2, null, sinalDante, semAlteracaoAtributos));
        repositorioCenas.addCena(cena03001A);
        Cena cena03001B = new Cena("C03_001B", new ArrayList<>(List.of(ward)), formatTexto("""
                Algumas horas depois.
        
                %s está algemado a uma mesa.
        
                O ombro ainda dói por causa da captura.
        
                A porta se abre.
        
                Um homem de terno escuro entra carregando uma pasta.
        
                — Inspector Elias Ward.
        
                Ele se senta.
        
                %s permanece em silêncio.
        
                Ward coloca uma fotografia sobre a mesa.
        
                Dante.
        
                Depois uma segunda.
        
                A vitrine de Blackglass.
        
                Vazia.
        
                %s observa a imagem.
        
                Ward percebe.
        
                — Seu parceiro saiu da mansão com alguma coisa.
        
                Nenhuma resposta.
        
                — O interessante é que Victor Ashcroft afirma que nada foi roubado.
        
                Ward encosta as costas na cadeira.
        
                — Uma vitrine vazia.
        
                — Um ladrão desaparecido.
        
                — E um milionário insistindo que não perdeu nada.
        
                Ward sorri levemente.
        
                — Pessoas inocentes raramente mentem tão rápido.
        
                %s pensa em Dante.
        
                Na última vez em que o viu, Blackglass estava nas mãos dele.
        
                "Eu volto por você."
        
                Foi o que Dante disse.
        
                Ward empurra a fotografia de Dante para frente.
        
                — Seu amigo está lá fora com algo que Ashcroft quer desesperadamente.
        
                Ele aponta para %s.
        
                — E você está aqui.
        
                Ward cruza os braços.
        
                — Vamos descobrir quanto vale amizade entre ladrões.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "A Promessa de Dante", 3);
        cena03001B.addEscolha(new Escolhas("C04_001B", "Contar a Ward que Dante está com Blackglass.", formatTexto("""
                %s observa a fotografia por alguns segundos.
        
                — Foi ele.
        
                Ward pega a caneta.
        
                — Explique.
        
                — Dante saiu com Blackglass.
        
                Ward não demonstra surpresa.
        
                — Para onde ele iria?
        
                %s hesita.
        
                Depois começa a listar os lugares que conhece.
        
                Um apartamento em Camden.
        
                Uma oficina abandonada.
        
                Dois contatos no sul de Londres.
        
                Ward anota tudo.
        
                — Mais alguma coisa?
        
                — Não.
        
                Ward fecha o caderno.
        
                — Espero por você que seja verdade.
        
                Quando os guardas entram para levar %s embora, uma pergunta permanece.
        
                Se Dante conseguir escapar de Ward...
        
                quanto tempo levará até descobrir quem o entregou?
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Dante", -3, null, null, semAlteracaoAtributos));
        cena03001B.addEscolha(new Escolhas("C04_001B", "Dizer que não viu Dante sair com Blackglass.", formatTexto("""
                %s dá de ombros.
        
                — Eu estava no chão.
        
                — Então?
        
                — Então não vi o que Dante levou.
        
                Ward encara %s.
        
                — Você espera que eu acredite nisso?
        
                — Não particularmente.
        
                — Ele estava a poucos metros de você.
        
                — Eu estava ocupado sendo atingido por três seguranças.
        
                Ward permanece em silêncio.
        
                %s mantém a expressão neutra.
        
                — Talvez ele tenha pego Blackglass.
        
                — Talvez não.
        
                — Pergunta para ele quando encontrar.
        
                Ward fecha a pasta.
        
                — Pretendo.
        
                Não parece convencido.
        
                Mas também não conseguiu uma confissão.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, astucia3, 0, 0, 0, null, 0, null, null, ganhaAstucia1));
        cena03001B.addEscolha(new Escolhas("C04_001B", "Permanecer em silêncio sobre Dante.", formatTexto("""
                Ward faz a primeira pergunta.
        
                %s não responde.
        
                Faz a segunda.
        
                Silêncio.
        
                Depois a terceira.
        
                Nada.
        
                Ward muda de estratégia.
        
                Pergunta sobre a mansão.
        
                Sobre Victor Ashcroft.
        
                Sobre Blackglass.
        
                Sobre Dante.
        
                %s não entrega nada.
        
                Quase quarenta minutos depois, Ward finalmente fecha a pasta.
        
                — Impressionante.
        
                %s levanta os olhos.
        
                — O quê?
        
                — Você está disposto a ir para a prisão por alguém que agora está livre,
                carregando uma pedra que vale milhões.
        
                Ward se aproxima da porta.
        
                — Só espero que ele esteja fazendo o mesmo por você.
        
                %s observa a porta fechar.
        
                Dante prometeu voltar.
        
                Agora resta descobrir se aquela promessa significava alguma coisa.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Dante", 2, null, sinalDante, semAlteracaoAtributos));
        repositorioCenas.addCena(cena03001B);
        Cena cena04001A = new Cena("C04_001A", new ArrayList<>(List.of(julian)), formatTexto("""
                Dois dias depois.
        
                O portão de Greyhaven fecha atrás de %s com um estrondo metálico.
        
                A prisão fica nos arredores de Londres, longe o bastante da cidade
                para que ninguém precise pensar muito sobre quem vive ali dentro.
        
                Concreto.
        
                Grades.
        
                Câmeras.
        
                Portas que só abrem de um lado.
        
                Um guarda entrega um uniforme para %s.
        
                — Bloco C. Cela 214.
        
                — Vista bonita?
        
                O guarda não acha graça.
        
                — Continue fazendo piada. Greyhaven adora gente engraçada.
        
                Minutos depois, %s atravessa o corredor principal acompanhado por
                dois agentes.
        
                Detentos observam das celas.
        
                Alguns reconhecem seu rosto das notícias.
        
                Outros simplesmente reconhecem alguém novo.
        
                Quando chega ao Bloco C, uma voz surge atrás das grades.
        
                — %s.
        
                %s para.
        
                Um homem está encostado na parede de uma das celas.
        
                Cabelo escuro.
        
                Postura tranquila demais para alguém preso.
        
                Ele observa %s como se já o conhecesse.
        
                — A gente se conhece? — pergunta %s.
        
                O homem sorri.
        
                — Não.
        
                Ele se aproxima das grades.
        
                — Mas você tentou roubar meu pai.
        
                %s fica imóvel por um instante.
        
                O homem estende a mão através das barras.
        
                — Julian Ashcroft.
        
                O sobrenome pesa mais do que deveria.
        
                Filho de Victor Ashcroft.
        
                Filho do dono de Blackglass.
        
                Julian mantém a mão estendida.
        
                — Acho que nós dois temos coisas para conversar.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "Greyhaven", 4);
        cena04001A.addEscolha(new Escolhas("C04_002A", "Sentar com Julian e ouvir o que ele tem a dizer.", formatTexto("""
                %s observa a mão de Julian.
        
                Depois aceita.
        
                — Cinco minutos — diz %s.
        
                Julian sorri.
        
                — É mais do que eu esperava.
        
                Pouco depois, os dois estão sentados no refeitório.
        
                Julian coloca a bandeja de lado.
        
                — Meu pai não costuma perdoar gente que entra na casa dele sem convite.
        
                — Percebi.
        
                — E você escolheu exatamente o objeto que ele mais protege.
        
                %s encara Julian.
        
                — Você sabe alguma coisa sobre Blackglass.
        
                Julian apenas sorri.
        
                — Agora estamos começando a conversar.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Julian", 1, null, null, ganhaCharme1));
        cena04001A.addEscolha(new Escolhas("C04_002A", "Observar Julian antes de decidir se pode confiar nele.", formatTexto("""
                %s não aceita imediatamente a mão.
        
                Em vez disso, observa Julian.
        
                As mãos não têm os calos de alguém acostumado àquele lugar.
        
                O uniforme está ajustado demais.
        
                E dois guardas passaram pelo corredor sem sequer olhar diretamente
                para ele.
        
                Julian percebe.
        
                — Está me analisando?
        
                — Estou decidindo se você é útil.
        
                Julian começa a rir.
        
                — Meu pai tinha razão sobre você.
        
                %s estreita os olhos.
        
                — Seu pai falou de mim?
        
                — Mais do que você imagina.
        
                Julian aponta para uma mesa no refeitório.
        
                — Agora ficou curioso?
        
                %s segue com ele.
        
                Talvez ouvir Julian seja perigoso.
        
                Não ouvir pode ser ainda pior.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, astucia3, 0, 0, 0, "Julian", 1, null, null, ganhaAstucia1));
        cena04001A.addEscolha(new Escolhas("C04_002A", "Dizer que já teve problemas demais com a família Ashcroft.", formatTexto("""
                %s olha para a mão de Julian.
        
                Não aceita.
        
                — Seu sobrenome já me causou problemas suficientes.
        
                Julian recolhe a mão.
        
                — Engraçado.
        
                — O quê?
        
                — Eu diria exatamente a mesma coisa.
        
                %s começa a andar.
        
                Julian acompanha pelo outro lado das grades.
        
                — Você pode me ignorar agora, mas vai acabar querendo falar comigo.
        
                — Não conte com isso.
        
                — Todo mundo aqui dentro precisa de alguma coisa.
        
                %s continua andando.
        
                Julian sorri.
        
                — Principalmente quem está pensando em fugir.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Julian", -1, null, null, perdeCharme1));
        repositorioCenas.addCena(cena04001A);
        Cena cena04002A = new Cena("C04_002A", new ArrayList<>(List.of(julian)), formatTexto("""
                Mais tarde, Julian encontra %s no refeitório.
        
                Ele escolhe uma mesa afastada das câmeras.
        
                — Blackglass continua na mansão — diz Julian.
        
                — Eu sei.
        
                — Não. Você sabe que não conseguiu roubar.
        
                Julian se inclina.
        
                — Você não sabe por que meu pai protege aquela pedra daquele jeito.
        
                %s coloca os braços sobre a mesa.
        
                — Então me explique.
        
                Julian olha ao redor antes de continuar.
        
                — Blackglass não é apenas uma joia.
        
                %s espera.
        
                Julian sorri.
        
                — Tire-me daqui e talvez eu conte o resto.
        
                — Talvez?
        
                — Confiança é um investimento.
        
                — E eu pareço banco?
        
                — Você parece alguém que precisa de uma rota de fuga.
        
                Julian se recosta na cadeira.
        
                — Meu pai financiou parte da reforma de Greyhaven.
        
                Agora Julian finalmente possui toda a atenção de %s.
        
                — Existem corredores aqui que não aparecem nas plantas oficiais.
        
                — E você conhece esses corredores?
        
                — Conheço algumas coisas.
        
                Julian estende a mão.
        
                — A questão é: quanto você pretende me contar?
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "O Filho do Colecionador", 4);
        cena04002A.addEscolha(new Escolhas("C05_001A", "Contar a verdade sobre o roubo.", formatTexto("""
                %s respira fundo.
        
                — Não fui contratado.
        
                Julian permanece em silêncio.
        
                — Dante e eu descobrimos Blackglass e decidimos tentar.
        
                — Só pelo dinheiro?
        
                — Existe outro motivo?
        
                Julian solta uma pequena risada.
        
                %s conta sobre a invasão.
        
                O alarme.
        
                A proteção.
        
                Dante fugindo.
        
                A prisão.
        
                Julian escuta tudo sem interromper.
        
                Quando %s termina, Julian pega um pequeno pedaço de papel dobrado
                de dentro do uniforme.
        
                Ele desliza o papel pela mesa.
        
                %s abre discretamente.
        
                É um mapa.
        
                Não da prisão inteira.
        
                Apenas corredores de manutenção.
        
                — Meu pai financiou a reforma de Greyhaven anos atrás — Julian explica.
        
                — Por quê?
        
                — Essa é uma pergunta muito melhor.
        
                %s guarda o mapa.
        
                — Isso é confiança?
        
                Julian se levanta.
        
                — É um adiantamento.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Julian", 2, null, mapaManutencao, ganhaCharme1));
        cena04002A.addEscolha(new Escolhas("C05_001A", "Inventar que alguém contratou você para roubar Blackglass.", formatTexto("""
                %s cruza os braços.
        
                — Foi um trabalho.
        
                Julian ergue uma sobrancelha.
        
                — De quem?
        
                — Não posso dizer.
        
                — Claro.
        
                %s mantém a expressão séria.
        
                — Recebi o alvo, parte das plantas e metade do pagamento.
        
                Julian observa %s durante alguns segundos.
        
                Então começa a guardar o papel que estava sobre a mesa.
        
                — O que é isso?
        
                — Nada importante.
        
                — Você ia me mostrar.
        
                Julian sorri.
        
                — Ia.
        
                %s percebe que a história talvez tenha sido convincente.
        
                Mas não o bastante.
        
                Julian se levanta.
        
                — Quando decidir contar a verdade, procure por mim.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, astucia3, 0, 0, 0, "Julian", -1, null, null, ganhaAstucia1));
        cena04002A.addEscolha(new Escolhas("C05_001A", "Ameaçar Julian para conseguir as informações.", formatTexto("""
                %s se inclina sobre a mesa.
        
                — Vamos simplificar.
        
                Julian não se move.
        
                — Você vai me contar o que Blackglass faz.
        
                — E se eu não contar?
        
                — Você não vai gostar.
        
                Julian observa %s.
        
                Não parece assustado.
        
                — Se você realmente pretendesse me machucar, não teria avisado.
        
                %s permanece em silêncio.
        
                Julian se levanta.
        
                — Meu pai gosta desse tipo de negociação.
        
                — Você não é seu pai.
        
                Pela primeira vez o sorriso de Julian desaparece.
        
                — Não.
        
                Ele olha diretamente para %s.
        
                — E sugiro que nunca me confunda com ele novamente.
        
                Julian vai embora.
        
                A conversa terminou.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Julian", -2, null, null, perdeCharme1));
        repositorioCenas.addCena(cena04002A);
        Cena cena04001B = new Cena("C04_001B", new ArrayList<>(List.of(julian)), formatTexto("""
                Greyhaven recebe %s com chuva.
        
                Muita chuva.
        
                O portão fecha atrás do transporte.
        
                — Bloco C — diz um guarda.
        
                %s recebe uniforme, número de cela e uma lista de regras que não
                pretende memorizar.
        
                Na primeira noite, pensa em Dante.
        
                Na segunda, pensa em Blackglass.
        
                Na terceira, começa a pensar que Dante talvez nunca volte.
        
                Durante a caminhada para o refeitório, uma voz surge atrás das grades.
        
                — %s.
        
                %s para.
        
                Um homem encostado à parede observa com um pequeno sorriso.
        
                — A gente se conhece?
        
                — Ainda não.
        
                Ele se aproxima.
        
                — Julian Ashcroft.
        
                %s sente o corpo ficar rígido.
        
                Ashcroft.
        
                O filho de Victor.
        
                Julian continua:
        
                — Antes que pergunte...
        
                Ele olha para os guardas.
        
                Depois volta os olhos para %s.
        
                — Sim.
        
                — Eu sei o que Dante levou.
        
                %s dá um passo em direção às grades.
        
                Julian sorri.
        
                — Agora temos algo para conversar.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "O Herdeiro de Greyhaven", 4);
        cena04001B.addEscolha(new Escolhas("C04_002B", "Perguntar imediatamente o que Blackglass realmente é.", formatTexto("""
                %s se aproxima das grades.
        
                — O que aquela pedra realmente é?
        
                Julian começa a rir.
        
                — Nem um "prazer em conhecer"?
        
                — Posso dizer depois que você responder.
        
                — Você é exatamente como disseram.
        
                %s estreita os olhos.
        
                — Quem disse?
        
                Julian apenas aponta para o refeitório.
        
                — Venha comigo.
        
                — Isso não respondeu minha pergunta.
        
                — Tenho várias respostas.
        
                Julian começa a andar.
        
                — Você só precisa descobrir quais delas vale a pena ouvir.
        
                %s segue atrás dele.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Julian", 1, null, null, semAlteracaoAtributos));
        cena04001B.addEscolha(new Escolhas("C04_002B", "Fingir que Dante não conseguiu levar Blackglass.", formatTexto("""
                %s dá de ombros.
        
                — Você está mal informado.
        
                Julian não reage.
        
                — Dante saiu de mãos vazias.
        
                — Sério?
        
                — Sério.
        
                Julian se aproxima das grades.
        
                — Meu pai fechou três contas bancárias na mesma noite.
        
                %s permanece em silêncio.
        
                — Mandou homens para Camden.
        
                Outro silêncio.
        
                — E colocou uma recompensa pela localização do seu amigo.
        
                Julian sorri.
        
                — Quer tentar de novo?
        
                %s percebe que aquela mentira não durou muito.
        
                Julian aponta para o refeitório.
        
                — Vamos conversar.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, astucia3, 0, 0, 0, "Julian", -1, null, null, ganhaAstucia1));
        cena04001B.addEscolha(new Escolhas("C04_002B", "Perguntar por que o filho de Victor Ashcroft está preso.", formatTexto("""
                %s não pergunta sobre Blackglass.
        
                — Por que você está aqui?
        
                O sorriso de Julian desaparece.
        
                — Perdão?
        
                — Seu pai é Victor Ashcroft.
        
                %s olha ao redor.
        
                — Imagino que ele consiga pagar um advogado.
        
                Julian fica em silêncio por alguns segundos.
        
                — Meu pai precisava que alguém assumisse um crime.
        
                — E escolheu você?
        
                Julian olha para o chão.
        
                — Família primeiro.
        
                %s percebe a ironia.
        
                Julian volta a encará-lo.
        
                — Agora talvez você entenda por que não estou exatamente preocupado
                em proteger os interesses dele.
        
                %s começa a entender.
        
                Julian pode ser um Ashcroft.
        
                Mas isso não significa que esteja do lado de Victor.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Julian", 2, null, null, ganhaCharme1));
        repositorioCenas.addCena(cena04001B);
        Cena cena04002B = new Cena("C04_002B", new ArrayList<>(List.of(julian)), formatTexto("""
                Julian leva %s até uma mesa afastada no refeitório.
        
                — Dante não consegue vender Blackglass como uma joia comum.
        
                — Por quê?
        
                — Porque qualquer comprador sério sabe quem é meu pai.
        
                %s dá de ombros.
        
                — Então ele encontra um comprador menos sério.
        
                Julian começa a rir.
        
                — Seu amigo parece mesmo o tipo.
        
                %s não acha graça.
        
                — Se sabe tanto, então me diga onde ele está.
        
                — Não sei.
        
                — Muito útil.
        
                Julian se aproxima.
        
                — Mas sei o que acontecerá quando ele tentar vender.
        
                %s espera.
        
                — Meu pai ficará sabendo.
        
                Julian aponta discretamente para uma câmera.
        
                — Se quer encontrar Dante antes de Victor...
        
                Ele olha ao redor da prisão.
        
                — Primeiro precisa sair daqui.
        
                — Imagino que tenha uma sugestão.
        
                Julian sorri.
        
                — Por coincidência, eu também gostaria de sair.
        
                Uma parceria entre um ladrão e o filho do homem que ele roubou.
        
                Péssima ideia.
        
                Exatamente o tipo de ideia que costuma funcionar.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "Uma Aliança Inconveniente", 4);
        cena04002B.addEscolha(new Escolhas("C05_001B", "Admitir que pretende encontrar Dante e tomar Blackglass dele.", formatTexto("""
                %s olha diretamente para Julian.
        
                — Eu não quero salvar Dante.
        
                Julian fica interessado.
        
                — Não?
        
                — Quero Blackglass.
        
                — Mesmo depois de ele ter tirado a pedra da mansão?
        
                — Principalmente depois disso.
        
                Julian começa a rir.
        
                — Finalmente uma resposta honesta.
        
                Ele retira um pedaço de papel dobrado de dentro do uniforme.
        
                %s abre.
        
                Um mapa parcial de Greyhaven.
        
                Corredores técnicos.
        
                Túneis.
        
                Áreas de manutenção.
        
                — Meu pai financiou parte da reforma daqui — Julian explica.
        
                — Por que está me dando isso?
        
                — Porque pessoas gananciosas são previsíveis.
        
                %s guarda o mapa.
        
                — Isso foi um elogio?
        
                — Não.
        
                — Ótimo.
        
                Julian sorri.
        
                — Acho que vamos nos entender.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Julian", 2, null, mapaManutencao, ganhaCharme1));
        cena04002B.addEscolha(new Escolhas("C05_001B", "Dizer que só quer encontrar Dante para ajudá-lo.", formatTexto("""
                %s se recosta na cadeira.
        
                — Dante é meu parceiro.
        
                — Isso não respondeu.
        
                — Quero encontrá-lo antes de Ashcroft.
        
                Julian observa %s.
        
                — Para ajudar?
        
                — Sim.
        
                Alguns segundos passam.
        
                Julian sorri.
        
                — Isso foi quase comovente.
        
                %s franze a testa.
        
                — Quase?
        
                — Se eu não soubesse que vocês invadiram minha casa para roubar uma
                pedra de milhões.
        
                Julian se levanta.
        
                — Talvez eu acredite em você depois.
        
                Ele não entrega nenhuma informação adicional.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, null, 0, null, null, semAlteracaoAtributos));
        cena04002B.addEscolha(new Escolhas("C05_001B", "Recusar qualquer tipo de acordo com Julian.", formatTexto("""
                %s se levanta.
        
                — Não preciso de você.
        
                Julian continua sentado.
        
                — Todo mundo precisa de alguém aqui dentro.
        
                — Eu trabalho melhor sozinho.
        
                Julian inclina a cabeça.
        
                — Seu último parceiro também parece pensar assim.
        
                %s para.
        
                Julian percebe que acertou.
        
                — Quando mudar de ideia, talvez eu ainda esteja disposto a conversar.
        
                %s começa a andar.
        
                — Não conte com isso.
        
                — Greyhaven costuma mudar opiniões.
        
                %s não responde.
        
                Mas a frase permanece na cabeça.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Julian", -2, null, null, perdeCharme1));
        repositorioCenas.addCena(cena04002B);
        Cena cena05001A = new Cena("C05_001A", new ArrayList<>(List.of(miller)), formatTexto("""
                No quarto dia em Greyhaven, %s volta para a cela depois do almoço.
        
                Existe um envelope sobre a cama.
        
                Nenhum nome.
        
                Nenhuma marca.
        
                Apenas uma frase escrita à mão:
        
                "Se quer sair daqui, pare de perguntar aos guardas."
        
                Embaixo:
        
                "Pátio. Quinze minutos. — M."
        
                %s vira o papel.
        
                Nada.
        
                Quinze minutos depois, ele está no pátio.
        
                Uma mulher está sentada em uma mesa de concreto jogando cartas com
                três detentos.
        
                Ninguém se aproxima dela sem ser convidado.
        
                Ela coloca uma carta sobre a mesa.
        
                — %s.
        
                %s para.
        
                — Todo mundo aqui sabe meu nome?
        
                A mulher sorri.
        
                — Só quem vale a pena conhecer.
        
                Ela aponta para o banco à frente.
        
                — Miller.
        
                %s se senta.
        
                — Foi você que mandou o bilhete?
        
                — Não. Foi o rei da Inglaterra.
        
                %s olha para ela.
        
                Miller ri.
        
                — Sim. Fui eu.
        
                Ela recolhe as cartas.
        
                — Ouvi dizer que você quer sair daqui.
        
                — Ouviu errado.
        
                — Claro.
        
                Miller coloca discretamente uma pequena peça metálica sobre a mesa.
        
                Uma chave improvisada.
        
                — Trezentas libras.
        
                %s observa a chave.
        
                — E se eu não tiver trezentas libras?
        
                Miller guarda a peça.
        
                — Então trabalha.
        
                — Fazendo o quê?
        
                Miller olha para o outro lado do pátio.
        
                Um homem alto está encostado perto da lavanderia.
        
                — Recuperando algo que me pertence.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "A Rainha de Greyhaven", 5);
        cena05001A.addEscolha(new Escolhas("C06_001A", "Pagar £300 pela chave improvisada.", formatTexto("""
                %s encara Miller.
        
                — Trezentas.
        
                — Trezentas.
        
                — Isso é roubo.
        
                Miller sorri.
        
                — Você está preso por quê mesmo?
        
                %s não responde.
        
                Algumas horas depois, uma transferência é feita através de um contato
                do lado de fora.
        
                Naquela noite, %s encontra uma pequena chave feita de metal dobrado
                escondida dentro do próprio sapato.
        
                Miller passa pela cela pouco depois.
        
                — Não pergunte de onde veio.
        
                — Eu não ia.
        
                — Por isso talvez você sobreviva.
        
                %s guarda a chave.
        
                Não parece muito.
        
                Mas dentro de Greyhaven, qualquer coisa que abra uma porta vale mais
                do que dinheiro.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 300, 0, 0, "Miller", 1, null, chaveImprovisada, semAlteracaoAtributos));
        cena05001A.addEscolha(new Escolhas("C05_002A", "Aceitar recuperar o pacote de Miller.", formatTexto("""
                %s acompanha o olhar de Miller.
        
                — O que ele pegou?
        
                — Algo meu.
        
                — Isso não respondeu.
        
                — Não precisava responder.
        
                Miller coloca as cartas novamente sobre a mesa.
        
                — O nome dele é Lewis.
        
                — Quer que eu bata nele?
        
                Miller dá de ombros.
        
                — Quero meu pacote.
        
                — O método é problema seu.
        
                %s olha para Lewis.
        
                — E depois?
        
                Miller mostra novamente a pequena chave.
        
                — Depois nós dois saímos satisfeitos.
        
                %s se levanta.
        
                — Tenho a impressão de que só um de nós vai sair satisfeito.
        
                Miller sorri.
        
                — Está aprendendo rápido.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Miller", 1, null, null, semAlteracaoAtributos));
        cena05001A.addEscolha(new Escolhas("C06_001A", "Recusar qualquer acordo com Miller.", formatTexto("""
                %s se levanta.
        
                — Eu encontro outro jeito.
        
                Miller recolhe as cartas.
        
                — Todo mundo encontra.
        
                %s começa a andar.
        
                — Até não encontrar — completa Miller.
        
                %s olha por cima do ombro.
        
                Miller já voltou ao jogo de cartas.
        
                A conversa terminou.
        
                E a chave continua com ela.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Miller", -1, null, null, semAlteracaoAtributos));
        repositorioCenas.addCena(cena05001A);
        Cena cena05002A = new Cena("C05_002A", new ArrayList<>(List.of(miller)), formatTexto("""
                Lewis está sozinho na lavanderia.
        
                O barulho das máquinas cobre boa parte dos sons do corredor.
        
                %s entra.
        
                Lewis olha imediatamente.
        
                — Miller mandou você?
        
                — Não.
        
                %s fecha a porta.
        
                — Eu gosto muito de lavanderias.
        
                Lewis não acha graça.
        
                Um pequeno pacote cria um volume evidente sob a camisa dele.
        
                %s percebe outra coisa.
        
                Preso ao cinto de Lewis existe um cartão de acesso.
        
                Provavelmente roubado de algum funcionário.
        
                Uma chave improvisada abre uma porta.
        
                Aquele cartão talvez abra várias.
        
                Lewis cruza os braços.
        
                — Diz pra Miller que ela perdeu.
        
                %s olha para a câmera no canto.
        
                Ela está quebrada.
        
                Isso deixa várias possibilidades.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "O Favor", 5);
        cena05002A.addEscolha(new Escolhas("C06_001A", "Tomar o pacote de Lewis à força.", formatTexto("""
                %s dá um passo à frente.
        
                — Última chance.
        
                Lewis responde com um soco.
        
                A luta não dura muito.
        
                Mas dura o suficiente.
        
                Lewis acerta %s no rosto.
        
                %s responde com um golpe no estômago.
        
                Alguns segundos depois, Lewis está sentado contra uma máquina,
                tentando recuperar o ar.
        
                %s pega o pacote.
        
                — Isso tudo podia ter sido mais fácil.
        
                — Vai pro inferno.
        
                Mais tarde, Miller recebe o pacote.
        
                Ela observa o corte no rosto de %s.
        
                — Método sutil.
        
                — Funcionou.
        
                Miller joga uma pequena chave sobre a mesa.
        
                — Negócio é negócio.
        
                %s guarda a chave.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 1, 0, "Miller", 2, null, chaveImprovisada, perdeCharme1));
        cena05002A.addEscolha(new Escolhas("C06_001A", "Convencer Lewis de que Miller já descobriu tudo.", formatTexto("""
                %s não avança.
        
                Apenas encosta em uma das máquinas.
        
                — Miller já sabe.
        
                Lewis tenta rir.
        
                — Sabe o quê?
        
                — Que foi você.
        
                O sorriso desaparece um pouco.
        
                %s continua.
        
                — Ela só está esperando para descobrir se você vai devolver o pacote
                sozinho...
        
                %s aponta para a porta.
        
                — ...ou se ela precisa fazer todo este bloco escolher um lado.
        
                Lewis permanece em silêncio.
        
                — Está blefando.
        
                — Talvez.
        
                %s olha para o pacote escondido.
        
                — Quer descobrir?
        
                Lewis encara %s por alguns segundos.
        
                Depois tira o pacote debaixo da camisa.
        
                — Pega.
        
                %s aceita.
        
                — Escolha inteligente.
        
                Mais tarde, Miller recebe o pacote.
        
                — Nenhum dente quebrado?
        
                — Hoje não.
        
                Miller entrega a chave.
        
                — Estou começando a gostar de você.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, charme3, 0, 0, 0, "Miller", 2, null, chaveImprovisada, ganhaCharme1));
        cena05002A.addEscolha(new Escolhas("C06_001A", "Roubar o pacote e o cartão de acesso sem Lewis perceber.", formatTexto("""
                %s olha para o cartão preso ao cinto de Lewis.
        
                Uma ideia começa a se formar.
        
                — Então Miller perdeu?
        
                — Perdeu.
        
                %s se aproxima.
        
                — Ótimo.
        
                — Ótimo?
        
                — Significa que não preciso perder tempo.
        
                Lewis franze a testa.
        
                Nesse momento, alguém passa pelo corredor.
        
                Lewis olha para a porta.
        
                É tudo que %s precisava.
        
                Um movimento rápido.
        
                O pacote desaparece debaixo da camisa de Lewis.
        
                O cartão de acesso também.
        
                Quando Lewis olha novamente, %s já está saindo.
        
                — Ei!
        
                — Até mais, Lewis.
        
                Mais tarde, Miller recebe o pacote.
        
                %s não menciona o cartão.
        
                Miller observa seu rosto por alguns segundos.
        
                — Você parece satisfeito demais.
        
                — Gosto de serviço bem feito.
        
                Miller estreita os olhos.
        
                Talvez tenha percebido alguma coisa.
        
                Talvez não.
        
                %s sai com o cartão escondido no uniforme.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, furtividade3, 0, 0, 0, "Miller", -1, null, cartaoAcesso, ganhaFurtividade1));
        repositorioCenas.addCena(cena05002A);
        Cena cena05001B = new Cena("C05_001B", new ArrayList<>(List.of(miller)), formatTexto("""
                No quarto dia em Greyhaven, uma mulher chama %s durante o horário
                do pátio.
        
                — O ladrão de Ashcroft.
        
                %s olha para ela.
        
                — Isso está virando apelido?
        
                — Podia ser pior.
        
                Ela embaralha algumas cartas.
        
                — Miller.
        
                %s se aproxima.
        
                — O que você quer?
        
                — Nada.
        
                Miller coloca uma carta na mesa.
        
                — Mas talvez você queira alguma coisa de mim.
        
                %s cruza os braços.
        
                Miller continua:
        
                — Seu amigo está ficando famoso lá fora.
        
                %s perde o sorriso.
        
                — Dante?
        
                — Homens de Ashcroft passaram por Camden ontem.
        
                Outra carta.
        
                — Southwark hoje de manhã.
        
                Outra.
        
                — E alguém ofereceu bastante dinheiro por qualquer informação sobre
                um homem carregando uma certa pedra negra.
        
                %s se aproxima da mesa.
        
                — Sabe onde Dante está?
        
                Miller sorri.
        
                — Informação custa mais caro do que ferramenta.
        
                Ela mostra discretamente uma pequena chave improvisada.
        
                — Mas antes de procurar seu amigo...
        
                Miller olha para as muralhas de Greyhaven.
        
                — Talvez você queira sair daqui.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "Notícias de Fora", 5);
        cena05001B.addEscolha(new Escolhas("C06_001B", "Pagar £300 pela chave improvisada.", formatTexto("""
                — Trezentas libras — diz Miller.
        
                %s observa a pequena chave.
        
                — Por isso?
        
                — Aqui dentro?
        
                Miller sorri.
        
                — Isso vale mais que ouro.
        
                %s aceita.
        
                Horas depois, o pagamento é confirmado através de um contato externo.
        
                Na fila do jantar, Miller esbarra em %s.
        
                A chave passa de uma mão para outra.
        
                — Se morrer usando isso, nunca comprou de mim.
        
                — Ótimo atendimento.
        
                — Volte sempre.
        
                %s guarda a chave.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 300, 0, 0, "Miller", 1, null, chaveImprovisada, semAlteracaoAtributos));
        cena05001B.addEscolha(new Escolhas("C05_002B", "Aceitar recuperar o pacote de Miller.", formatTexto("""
                %s olha para a chave.
        
                — O que tenho que fazer?
        
                Miller aponta discretamente para um homem próximo da lavanderia.
        
                — Lewis.
        
                — O que ele fez?
        
                — Pegou algo que não era dele.
        
                %s quase sorri.
        
                — Isso incomoda você?
        
                — Quando é meu, sim.
        
                Miller recolhe as cartas.
        
                — Traga meu pacote.
        
                — E a chave?
        
                — É sua.
        
                %s se levanta.
        
                — Parece simples demais.
        
                Miller sorri.
        
                — Você ainda é novo aqui.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Miller", 1, null, null, semAlteracaoAtributos));
        cena05001B.addEscolha(new Escolhas("C06_001B", "Exigir que Miller diga onde Dante está primeiro.", formatTexto("""
                %s coloca as mãos sobre a mesa.
        
                — Primeiro você me diz onde Dante está.
        
                Miller olha para as mãos.
        
                Depois para %s.
        
                — Acho que você não entendeu como isso funciona.
        
                — Então explica.
        
                Miller pega a chave e guarda no bolso.
        
                — Eu tenho a informação.
        
                — Eu tenho as ferramentas.
        
                — E você...
        
                Ela aponta para o uniforme de %s.
        
                — Está preso.
        
                %s fecha a expressão.
        
                Miller volta às cartas.
        
                — Volte quando entender quem está negociando com quem.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Miller", -2, null, null, perdeCharme1));
        repositorioCenas.addCena(cena05001B);
        Cena cena05002B = new Cena("C05_002B", new ArrayList<>(List.of(miller)), formatTexto("""
                %s encontra Lewis sozinho na lavanderia.
        
                O pacote está claramente escondido sob a camisa.
        
                Lewis olha para %s.
        
                — Miller mandou você?
        
                — Talvez.
        
                — Diz pra ela que acabou.
        
                %s observa Lewis.
        
                Depois observa o cartão de acesso preso ao cinto dele.
        
                Provavelmente pertence a algum funcionário.
        
                Aquilo pode ser ainda mais útil do que a chave de Miller.
        
                %s fecha a porta atrás de si.
        
                Agora precisa decidir como resolver o problema.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "O Preço do Favor", 5);
        cena05002B.addEscolha(new Escolhas("C06_001B", "Tomar o pacote à força.", formatTexto("""
                Lewis ataca primeiro.
        
                %s bloqueia como pode.
        
                O segundo golpe acerta seu ombro.
        
                %s responde.
        
                Alguns segundos depois, Lewis está no chão.
        
                O pacote muda de mãos.
        
                Mais tarde, Miller o recebe.
        
                Ela entrega a chave prometida.
        
                — Negócio é negócio.
        
                %s guarda a ferramenta.
        
                O ombro ainda dói.
        
                Mas agora existe uma possível saída.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 1, 0, "Miller", 2, null, chaveImprovisada, perdeCharme1));
        cena05002B.addEscolha(new Escolhas("C06_001B", "Fazer Lewis devolver o pacote sem lutar.", formatTexto("""
                %s não demonstra pressa.
        
                — Miller sabe.
        
                Lewis ri.
        
                — Ela não sabe nada.
        
                — Então por que você está escondendo o pacote debaixo da camisa?
        
                O sorriso diminui.
        
                %s continua:
        
                — Você pode devolver agora.
        
                — Ou pode esperar até Miller decidir transformar isso num problema
                para todo o bloco.
        
                Lewis olha para a porta.
        
                — Está blefando.
        
                — Descobre.
        
                Alguns segundos depois, Lewis entrega o pacote.
        
                Mais tarde, Miller recebe o objeto.
        
                — Sem sangue?
        
                — Decepcionada?
        
                Miller joga a chave para %s.
        
                — Um pouco.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, charme3, 0, 0, 0, "Miller", 2, null, chaveImprovisada, ganhaCharme1));
        cena05002B.addEscolha(new Escolhas("C06_001B", "Roubar o pacote e o cartão de acesso.", formatTexto("""
                %s percebe que não precisa escolher entre o pacote e o cartão.
        
                Pode levar os dois.
        
                Ele espera.
        
                Lewis olha para o corredor quando dois guardas passam.
        
                %s se move.
        
                Quando Lewis percebe, o pacote já desapareceu.
        
                O cartão também.
        
                — Ei!
        
                %s já está saindo pela porta.
        
                Mais tarde, entrega o pacote para Miller.
        
                Ela examina %s.
        
                — Você pegou mais alguma coisa?
        
                — Não.
        
                Miller continua olhando.
        
                %s sustenta a expressão.
        
                Ela finalmente guarda o pacote.
        
                — Claro.
        
                %s sai com o cartão escondido.
        
                A chave de Miller seria útil.
        
                Mas um cartão oficial pode ser ainda melhor.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, furtividade3, 0, 0, 0, "Miller", -1, null, cartaoAcesso, ganhaFurtividade1));
        repositorioCenas.addCena(cena05002B);
        Cena cena06001A = new Cena("C06_001A", new ArrayList<>(List.of(julian)), formatTexto("""
                Segunda-feira.
        
                23:47.
        
                Greyhaven está quase silenciosa.
        
                %s está sentado na cama quando alguma coisa passa por baixo da porta
                da cela.
        
                Um livro.
        
                %s olha para o corredor.
        
                Ninguém.
        
                Ele pega o livro.
        
                Entre as páginas existe um pequeno pedaço de papel dobrado.
        
                Apenas uma frase:
        
                "TERÇA. 02:10.
                ALA OESTE.
                CONFIA EM MIM.
        
                — D."
        
                %s lê novamente.
        
                Dante.
        
                Depois de dias sem nenhuma notícia, finalmente alguma coisa.
        
                Julian está deitado na cama acima.
        
                — Pelo seu rosto, imagino que não seja uma carta de amor.
        
                %s mostra o papel.
        
                Julian lê.
        
                — Péssima ideia.
        
                — Você nem sabe o plano.
        
                — Exatamente.
        
                Julian devolve o bilhete.
        
                — Seu amigo manda você aparecer numa ala da prisão no meio da
                madrugada e escreve "confia em mim".
        
                Ele aponta para a mensagem.
        
                — Normalmente é assim que histórias ruins começam.
        
                %s guarda o papel.
        
                Sobre a pequena mesa da cela estão as poucas coisas que conseguiu
                reunir nos últimos dias.
        
                Talvez um mapa.
        
                Talvez uma chave.
        
                Talvez um cartão.
        
                Talvez apenas uma mensagem de Dante.
        
                Greyhaven possui dezenas de portas.
        
                %s precisa decidir qual delas será sua saída.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "02:10", 6);
        cena06001A.addEscolha(new Escolhas("C07_DANTE_A", "Confiar na mensagem de Dante e ir para a Ala Oeste.", formatTexto("""
                %s dobra o bilhete.
        
                — Vou encontrar Dante.
        
                Julian olha para ele.
        
                — Você realmente confia nele?
        
                %s demora alguns segundos para responder.
        
                — Confiei quando Ward perguntou onde ele estava.
        
                Julian entende.
        
                — Então espera que ele saiba disso.
        
                02:06.
        
                %s sai da cela durante a troca de turno.
        
                02:08.
        
                Chega ao corredor de manutenção.
        
                02:09.
        
                A Ala Oeste está logo à frente.
        
                %s olha para o relógio preso na parede.
        
                02:10.
        
                Agora resta descobrir se Dante realmente vai aparecer.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                sinalDante, null, 0, 0, 0, "Dante", 1, null, null, semAlteracaoAtributos));
        cena06001A.addEscolha(new Escolhas("C07_JULIAN_A", "Ignorar a mensagem de Dante e seguir o mapa de Julian.", formatTexto("""
                %s abre discretamente o mapa sobre a cama.
        
                Julian desce.
        
                — Mudou de ideia?
        
                — Não gosto de depender de alguém que está do lado de fora.
        
                Julian aponta para uma linha quase apagada.
        
                — Antiga ventilação de serviço.
        
                — Para onde leva?
        
                — Debaixo da lavanderia.
        
                Julian segue a linha com o dedo.
        
                — Daqui existe uma passagem até o sistema antigo de drenagem.
        
                %s observa o desenho.
        
                — E a saída?
        
                — Fora do muro.
        
                — Parece simples.
        
                Julian começa a rir.
        
                — Então você não entendeu o mapa.
        
                %s dobra o papel.
        
                — Você vem?
        
                Julian olha para ele.
        
                — Essa é a primeira pergunta inteligente que você fez hoje.
        
                Os dois esperam a troca de turno.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                mapaManutencao, null, 0, 0, 0, "Julian", 1, null, null, ganhaAstucia1));
        cena06001A.addEscolha(new Escolhas("C07_SOLO_A", "Fugir sozinho usando a chave improvisada.", formatTexto("""
                %s segura a pequena chave feita de metal dobrado.
        
                Não parece confiável.
        
                Mas Miller não vende coisas inúteis.
        
                Pelo menos não pelo preço que cobrou.
        
                %s espera o corredor ficar vazio.
        
                Aproxima-se da porta de serviço.
        
                Insere a chave.
        
                Nada.
        
                Tenta novamente.
        
                Um clique.
        
                %s sorri.
        
                — Obrigado, Miller.
        
                A porta abre.
        
                Do outro lado existe um corredor que não deveria estar acessível
                aos detentos.
        
                A partir dali, %s está sozinho.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                chaveImprovisada, null, 0, 0, 0, null, 0, "CHAVE_IMPROVISADA", null, ganhaFurtividade1));
        cena06001A.addEscolha(new Escolhas("C07_SOLO_A", "Usar o cartão de acesso roubado.", formatTexto("""
                %s observa o cartão de acesso.
        
                Um pedaço de plástico roubado de Lewis.
        
                Talvez pertença a um funcionário.
        
                Talvez esteja bloqueado.
        
                Só existe uma maneira de descobrir.
        
                %s espera o último guarda desaparecer no corredor.
        
                Aproxima o cartão do leitor.
        
                Luz vermelha.
        
                %s congela.
        
                Um segundo depois...
        
                Verde.
        
                A fechadura destrava.
        
                — Às vezes crime realmente compensa.
        
                %s passa pela porta antes que alguém apareça.
        
                Agora precisa encontrar sozinho o caminho para fora.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                cartaoAcesso, null, 0, 0, 0, null, 0, "CARTAO_ACESSO", null, ganhaFurtividade1));
        cena06001A.addEscolha(new Escolhas("C07_IMPROVISO_A", "Criar um plano de fuga sem depender de ninguém.", formatTexto("""
                %s olha para o bilhete.
        
                Depois para a cela.
        
                Dante pode falhar.
        
                Julian pode estar errado.
        
                Miller pode ter vendido uma ferramenta inútil.
        
                %s rasga o bilhete em pequenos pedaços.
        
                Julian observa.
        
                — Isso significa que você tem outro plano?
        
                — Significa que vou ter.
        
                — Isso não é a mesma coisa.
        
                %s olha para a tubulação que passa atrás da parede.
        
                Depois para o sistema elétrico próximo à lavanderia.
        
                Uma ideia começa a surgir.
        
                Não é uma boa ideia.
        
                Mas ideias boas raramente terminam com alguém escapando de uma prisão.
        
                — Vou criar uma distração.
        
                Julian fecha os olhos.
        
                — Claro que vai.
        
                %s espera a madrugada começar.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, null, 0, null, null, ganhaAstucia1));
        repositorioCenas.addCena(cena06001A);
        Cena cena06001B = new Cena("C06_001B", new ArrayList<>(List.of(julian)), formatTexto("""
                Segunda-feira.
        
                Pouco antes da meia-noite.
        
                Um livro aparece dentro da cela de %s.
        
                Ninguém assume ter colocado ali.
        
                Entre duas páginas existe um bilhete.
        
                "TERÇA. 02:10.
                ALA OESTE.
                CONFIA EM MIM.
        
                — D."
        
                %s reconhece imediatamente a letra.
        
                Dante.
        
                O mesmo Dante que está livre.
        
                O mesmo Dante que está com Blackglass.
        
                O mesmo Dante que prometeu voltar.
        
                Julian lê por cima do ombro.
        
                — Interessante.
        
                %s dobra o papel.
        
                — O quê?
        
                — Se eu tivesse uma pedra que vale milhões no bolso, sabe o que eu
                não faria?
        
                — O quê?
        
                — Invadir uma prisão para buscar meu parceiro.
        
                %s olha para Julian.
        
                — Ainda bem que você e Dante não são amigos.
        
                — Exatamente.
        
                Julian aponta para o bilhete.
        
                — A pergunta não é se ele escreveu isso.
        
                — É por quê.
        
                %s olha para as poucas ferramentas disponíveis.
        
                Uma mensagem de Dante.
        
                Talvez o mapa de Julian.
        
                Talvez uma chave.
        
                Talvez um cartão.
        
                Existe mais de uma forma de tentar sair de Greyhaven.
        
                E uma delas pode ser exatamente o que Dante espera que %s faça.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "A Mensagem", 6);
        cena06001B.addEscolha(new Escolhas("C07_DANTE_B", "Confiar em Dante e ir para a Ala Oeste.", formatTexto("""
                %s guarda o bilhete.
        
                Julian percebe.
        
                — Vai mesmo?
        
                — Ele disse que voltaria.
        
                — E agora está com alguns milhões nas mãos.
        
                %s olha para Julian.
        
                — Quando Ward perguntou sobre ele, eu não falei.
        
                — Isso significa que você é leal.
        
                Julian aponta para o bilhete.
        
                — Não significa que ele seja.
        
                %s não responde.
        
                02:07.
        
                Sai da cela.
        
                02:09.
        
                Chega à Ala Oeste.
        
                %s olha para o relógio.
        
                02:10.
        
                Se Dante pretende cumprir a promessa, é agora.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                sinalDante, null, 0, 0, 0, "Dante", 1, null, null, semAlteracaoAtributos));
        cena06001B.addEscolha(new Escolhas("C07_JULIAN_B", "Ignorar Dante e usar o mapa de Julian.", formatTexto("""
                %s coloca o bilhete de lado.
        
                — Se Dante realmente quisesse me tirar daqui, teria dado mais do que
                um horário.
        
                Julian sorri.
        
                — Finalmente.
        
                %s abre o mapa.
        
                — Mostra a rota.
        
                Julian aponta para a lavanderia.
        
                — Antiga ventilação.
        
                Depois desliza o dedo até uma linha abaixo do muro.
        
                — Drenagem.
        
                — Câmeras?
        
                — Algumas.
        
                — Guardas?
        
                — Também.
        
                %s dobra o mapa.
        
                — Ótimo.
        
                Julian franze a testa.
        
                — Sua definição de ótimo é preocupante.
        
                Os dois aguardam a troca de turno.
        
                Enquanto isso, o bilhete de Dante permanece sobre a cama.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                mapaManutencao, null, 0, 0, 0, "Julian", 1, null, null, ganhaAstucia1));
        cena06001B.addEscolha(new Escolhas("C07_SOLO_B", "Usar a chave improvisada e fugir sozinho.", formatTexto("""
                %s decide que já confiou em gente demais.
        
                Dante tem Blackglass.
        
                Julian tem seus próprios interesses.
        
                Miller cobra por cada favor.
        
                A chave pelo menos não pode mentir.
        
                %s espera o corredor ficar vazio.
        
                Encaixa a pequena ferramenta na fechadura de serviço.
        
                Um movimento.
        
                Depois outro.
        
                Clique.
        
                A porta abre.
        
                %s olha uma última vez para a direção da própria cela.
        
                Depois desaparece pelo corredor técnico.
        
                Dessa vez, não existe parceiro.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                chaveImprovisada, null, 0, 0, 0, null, 0, "CHAVE_IMPROVISADA", null, ganhaFurtividade1));
        cena06001B.addEscolha(new Escolhas("C07_SOLO_B", "Usar o cartão de acesso e fugir sozinho.", formatTexto("""
                %s passa o dedo pela borda do cartão.
        
                Lewis provavelmente nem sabe que aquilo está com ele.
        
                Dante também não sabe.
        
                Melhor assim.
        
                %s se aproxima da porta de serviço.
        
                Encosta o cartão no leitor.
        
                Vermelho.
        
                O coração acelera.
        
                Então:
        
                Verde.
        
                A porta abre.
        
                %s guarda o cartão e atravessa.
        
                Atrás dele, Greyhaven continua silenciosa.
        
                Por enquanto.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                cartaoAcesso, null, 0, 0, 0, null, 0, "CARTAO_ACESSO", null, ganhaFurtividade1));
        cena06001B.addEscolha(new Escolhas("C07_IMPROVISO_B", "Ignorar todos os planos e criar sua própria fuga.", formatTexto("""
                %s olha para o bilhete de Dante.
        
                Depois rasga ao meio.
        
                Julian ergue uma sobrancelha.
        
                — Dramático.
        
                — Ele quer que eu esteja na Ala Oeste às 02:10.
        
                — E?
        
                — Então às 02:10 eu vou estar em qualquer lugar menos na Ala Oeste.
        
                Julian começa a sorrir.
        
                %s observa a estrutura da cela.
        
                Sistema elétrico.
        
                Tubulação.
        
                Lavanderia.
        
                Guardas.
        
                Se Dante pretende criar alguma coisa naquela noite, talvez %s possa
                usar a confusão de outra maneira.
        
                — Tenho um plano.
        
                Julian olha para ele.
        
                — Isso me preocupa mais do que deveria.
        
                %s espera a madrugada.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, null, 0, null, null, ganhaAstucia1));
        repositorioCenas.addCena(cena06001B);
        Cena cena07DanteA = new Cena("C07_DANTE_A", new ArrayList<>(List.of(dante)), formatTexto("""
                02:10.
        
                A Ala Oeste está vazia.
        
                %s espera.
        
                Dez segundos.
        
                Vinte.
        
                Trinta.
        
                Nada.
        
                %s começa a pensar que Julian estava certo.
        
                Então as luzes apagam.
        
                Greyhaven inteira mergulha na escuridão.
        
                Um segundo depois, as luzes de emergência acendem.
        
                Vermelhas.
        
                O comunicador de um guarda abandonado sobre uma mesa começa a chiar.
        
                — Falha no gerador da Ala Oeste.
        
                — Todas as unidades para o setor principal.
        
                %s escuta três batidas na porta de serviço.
        
                Uma pausa.
        
                Mais duas.
        
                O mesmo código que Dante usava nos trabalhos antigos.
        
                %s abre.
        
                Dante está do outro lado.
        
                Uniforme de manutenção.
        
                Boné.
        
                Um enorme sorriso no rosto.
        
                — Sentiu minha falta?
        
                %s olha para ele.
        
                — Você demorou.
        
                — Eu tive que invadir uma prisão.
        
                — Eu também estou tentando fazer isso.
        
                Dante entrega um uniforme.
        
                — Vista.
        
                — Qual é o plano?
        
                Dante aponta para o corredor.
        
                — Sair pela garagem antes que percebam que o apagão foi provocado.
        
                Passos começam a ecoar ao longe.
        
                Dante olha para %s.
        
                — Agora seria uma excelente hora para confiar em mim.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "Uma Promessa Cumprida", 7);
        cena07DanteA.addEscolha(new Escolhas("C08_001A", "Seguir silenciosamente o plano de Dante.", formatTexto("""
                %s veste o uniforme.
        
                — Vai na frente.
        
                Dante abre a porta.
        
                Os dois avançam pelos corredores de serviço.
        
                Quando um guarda aparece, %s puxa Dante para trás de uma coluna.
        
                O homem passa sem perceber.
        
                Dante olha para %s.
        
                — Ainda sabe fazer isso.
        
                — Eu estive preso, não aposentado.
        
                Minutos depois, os dois chegam à garagem.
        
                Uma van de manutenção os espera.
        
                Dante abre a porta.
        
                — Bem-vindo de volta ao mundo exterior.
        
                %s entra.
        
                Greyhaven fica para trás.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, furtividade3, 0, 0, 0, "Dante", 1, null, null, ganhaFurtividade1));
        cena07DanteA.addEscolha(new Escolhas("C08_001A", "Usar o sistema da prisão para criar uma segunda distração.", formatTexto("""
                %s olha para um painel elétrico aberto.
        
                — Seu apagão vai durar quanto?
        
                — Quatro minutos.
        
                — Pouco.
        
                %s começa a mexer nos controles.
        
                Dante observa.
        
                — O que está fazendo?
        
                — Melhorando seu plano.
        
                Um alarme dispara no Bloco B.
        
                No rádio, guardas começam a ser redirecionados.
        
                Dante sorri.
        
                — Eu senti falta disso.
        
                — De alarmes?
        
                — De trabalhar com alguém que sabe o que está fazendo.
        
                Os dois atravessam a garagem praticamente vazia.
        
                Pouco depois, uma van deixa Greyhaven.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, astucia3, 0, 0, 0, "Dante", 1, null, null, ganhaAstucia1));
        cena07DanteA.addEscolha(new Escolhas("C08_001A", "Abrir caminho quando um guarda reconhecer Dante.", formatTexto("""
                Os dois estão quase na garagem.
        
                Então um guarda para.
        
                — Ei.
        
                Dante continua andando.
        
                — Você. Para.
        
                O guarda reconhece o rosto dele.
        
                — Você não trabalha aqui.
        
                %s age primeiro.
        
                Ele empurra o guarda contra a parede.
        
                Dante corre para ajudar.
        
                Outro agente aparece.
        
                %s recebe um golpe no ombro, mas consegue fechar a porta de segurança
                entre eles.
        
                — Corre!
        
                Os dois entram na van.
        
                Dante liga o motor.
        
                — Você acabou de apanhar por mim.
        
                — Não se acostuma.
        
                Dante sorri.
        
                A van atravessa os portões antes que o bloqueio seja ativado.
        
                Greyhaven desaparece no retrovisor.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 1, 0, "Dante", 2, null, null, perdeFurtividade1));
        repositorioCenas.addCena(cena07DanteA);
        Cena cena07DanteB = new Cena("C07_DANTE_B", new ArrayList<>(List.of(dante)), formatTexto("""
                02:10.
        
                As luzes de Greyhaven apagam.
        
                %s fica imóvel.
        
                Alguns segundos depois, as luzes vermelhas de emergência acendem.
        
                Três batidas na porta.
        
                Pausa.
        
                Duas batidas.
        
                %s reconhece o código.
        
                A porta abre.
        
                Dante aparece usando uniforme de manutenção.
        
                — Você veio — diz %s.
        
                — Eu disse que voltaria.
        
                Os dois se encaram.
        
                Existe uma pergunta que nenhum deles fez.
        
                Blackglass.
        
                %s olha para os bolsos de Dante.
        
                Nada.
        
                — Onde está?
        
                Dante sabe exatamente do que ele está falando.
        
                — Seguro.
        
                — Isso não é um lugar.
        
                — E esse não é o momento.
        
                Dante entrega um uniforme.
        
                — Meu carro está do lado de fora. Temos cinco minutos antes que
                percebam que o apagão foi provocado.
        
                %s segura o uniforme.
        
                Dante estende a mão.
        
                — Primeiro saímos daqui.
        
                — Depois falamos sobre Blackglass.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "O Homem com Blackglass", 7);
        cena07DanteB.addEscolha(new Escolhas("C08_001B", "Confiar em Dante e discutir Blackglass depois da fuga.", formatTexto("""
                %s pega o uniforme.
        
                — Primeiro saímos.
        
                Dante assente.
        
                — Depois você me conta tudo.
        
                — Tudo.
        
                Os dois avançam pelos corredores durante o apagão.
        
                Alguns minutos depois, chegam à garagem.
        
                Uma van de manutenção está esperando.
        
                %s entra no banco do passageiro.
        
                Dante assume o volante.
        
                Os portões de Greyhaven ficam para trás.
        
                Pela primeira vez em dias, %s está livre.
        
                Mas Blackglass continua em algum lugar.
        
                E apenas Dante sabe onde.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Dante", 2, null, null, ganhaCharme1));
        cena07DanteB.addEscolha(new Escolhas("C08_001B", "Exigir saber onde Blackglass está antes de seguir Dante.", formatTexto("""
                %s não pega o uniforme.
        
                — Onde está Blackglass?
        
                Dante olha para o corredor.
        
                — Agora não.
        
                — Agora.
        
                — Estamos dentro de uma prisão!
        
                — E você está com uma coisa que vale milhões.
        
                Dante fica em silêncio.
        
                %s dá um passo à frente.
        
                — Eu fiquei aqui enquanto você saiu com a pedra.
        
                Dante perde o sorriso.
        
                — E eu voltei.
        
                — Isso não responde.
        
                Um alarme começa a tocar ao longe.
        
                Dante joga o uniforme para %s.
        
                — Quer Blackglass?
        
                Ele aponta para a saída.
        
                — Então primeiro precisa sobreviver aos próximos quatro minutos.
        
                %s veste o uniforme.
        
                A conversa acabou.
        
                Por enquanto.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Dante", -2, null, null, perdeCharme1));
        cena07DanteB.addEscolha(new Escolhas("C08_001B", "Fingir confiança e observar Dante com atenção.", formatTexto("""
                %s pega o uniforme.
        
                — Certo.
        
                Dante parece surpreso.
        
                — Certo?
        
                — Primeiro saímos.
        
                Os dois começam a andar.
        
                Mas %s observa cada detalhe.
        
                Dante evita tocar no bolso interno da jaqueta.
        
                Verifica o telefone várias vezes.
        
                E quando acredita que %s não está olhando, confere uma mensagem.
        
                Apenas duas palavras aparecem na tela:
        
                "LOCAL SEGURO."
        
                %s não comenta.
        
                Ainda.
        
                Os dois alcançam a van.
        
                Greyhaven fica para trás.
        
                Mas agora %s sabe que Dante está escondendo alguma coisa.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, astucia3, 0, 0, 0, null, 0, null, null, ganhaAstucia1));
        repositorioCenas.addCena(cena07DanteB);
        Cena cena07JulianA = new Cena("C07_JULIAN_A", new ArrayList<>(List.of(julian)), formatTexto("""
                02:03.
        
                %s e Julian entram pela passagem indicada no mapa.
        
                O túnel é estreito.
        
                Úmido.
        
                E provavelmente não recebe manutenção há décadas.
        
                Julian ilumina o caminho com uma pequena lanterna.
        
                — Meu pai realmente pagou por isso?
        
                — Meu pai pagava por muitas coisas que não deveriam existir.
        
                Os dois continuam.
        
                Depois de quase dez minutos, chegam a uma grade metálica.
        
                Julian consulta o mapa.
        
                — Isso não estava aqui.
        
                %s segura a grade.
        
                Não se move.
        
                Atrás deles, um som ecoa pelo túnel.
        
                Guardas.
        
                Julian olha para %s.
        
                — Se vamos resolver isso, precisa ser agora.
                """, protagonista.getNome(), protagonista.getNome() ),
                "Sob Greyhaven", 7);
        cena07JulianA.addEscolha(new Escolhas("C08_001A", "Examinar o mecanismo da grade.", formatTexto("""
                %s ignora a força bruta.
        
                Ele examina as dobradiças.
        
                Uma delas está enferrujada.
        
                Mas a trava está ligada a um sistema antigo de emergência.
        
                %s remove a tampa lateral.
        
                — Me dá a lanterna.
        
                Julian entrega.
        
                Alguns fios.
        
                Um pequeno encaixe.
        
                Um clique.
        
                A grade abre.
        
                Julian sorri.
        
                — Eu sabia que trazer você seria útil.
        
                — Você trouxe o mapa.
        
                — Exatamente. Trabalho em equipe.
        
                Os dois desaparecem pelo túnel antes dos guardas chegarem.
        
                Minutos depois, saem além dos muros de Greyhaven.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, astucia3, 0, 0, 0, "Julian", 1, null, null, ganhaAstucia1));
        cena07JulianA.addEscolha(new Escolhas("C08_001A", "Forçar a grade junto com Julian.", formatTexto("""
                %s segura uma das barras.
        
                — No três.
        
                Julian segura a outra.
        
                — Um.
        
                — Dois.
        
                — Três!
        
                Os dois puxam.
        
                Nada.
        
                Novamente.
        
                A estrutura range.
        
                Os passos dos guardas ficam mais próximos.
        
                — Outra vez!
        
                Os dois puxam com toda a força.
        
                A grade finalmente cede.
        
                %s bate o braço contra a parede, mas continua.
        
                Julian passa pela abertura.
        
                Depois ajuda %s.
        
                Os dois correm pelo túnel.
        
                Alguns minutos depois, o ar frio da madrugada atinge seus rostos.
        
                Estão fora.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, saude50, 0, 1, 0, "Julian", 2, null, null, perdeFurtividade1));
        cena07JulianA.addEscolha(new Escolhas("C08_001A", "Usar uma passagem lateral e deixar Julian para trás.", formatTexto("""
                %s percebe uma abertura estreita na parede.
        
                Pequena demais para os dois passarem rapidamente.
        
                Julian vê também.
        
                — Não.
        
                %s começa a entrar.
        
                — Você não está pensando seriamente...
        
                — Os guardas estão chegando.
        
                Julian encara %s.
        
                — Nós tínhamos um acordo.
        
                %s passa pela abertura.
        
                Julian fica do outro lado.
        
                — %s!
        
                Os passos estão próximos demais.
        
                %s continua.
        
                Minutos depois, encontra a saída do sistema de drenagem.
        
                Ele está livre.
        
                Julian não.
        
                Pelo menos não por enquanto.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Julian", -3, null, null, ganhaFurtividade1));
        repositorioCenas.addCena(cena07JulianA);
        Cena cena07JulianB = new Cena("C07_JULIAN_B", new ArrayList<>(List.of(julian)), formatTexto("""
                %s e Julian descem pelos túneis antigos de Greyhaven.
        
                A mensagem de Dante ficou para trás.
        
                Assim como a possibilidade de descobrir imediatamente onde está
                Blackglass.
        
                Julian parece perceber.
        
                — Ainda pensando nele?
        
                — Em Dante?
        
                — Na pedra.
        
                %s não responde.
        
                Julian sorri.
        
                — Imaginei.
        
                Eles chegam a uma grade bloqueando o túnel.
        
                Julian olha o mapa.
        
                — Isso é novo.
        
                Um barulho surge atrás deles.
        
                Guardas entrando no sistema de manutenção.
        
                %s se aproxima da grade.
        
                Se não abrirem aquilo rapidamente, a fuga termina ali.
                """, protagonista.getNome(), protagonista.getNome() ),
                "Não Confie em Ninguém", 7);
        cena07JulianB.addEscolha(new Escolhas("C08_001B", "Descobrir como destravar a grade.", formatTexto("""
                %s se ajoelha diante da trava.
        
                — É elétrica.
        
                Julian segura a lanterna.
        
                — Isso é bom?
        
                — Melhor do que concreto.
        
                %s abre o pequeno painel.
        
                Dois fios.
        
                Um circuito antigo.
        
                Ele improvisa uma ligação.
        
                Clique.
        
                A grade destrava.
        
                Julian abre um sorriso.
        
                — Depois que sairmos, precisamos conversar sobre Blackglass.
        
                %s olha para ele.
        
                — Finalmente algo em que concordamos.
        
                Os dois correm.
        
                Pouco depois, emergem fora dos muros de Greyhaven.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, astucia3, 0, 0, 0, "Julian", 1, null, null, ganhaAstucia1));
        cena07JulianB.addEscolha(new Escolhas("C08_001B", "Forçar a grade com Julian.", formatTexto("""
                %s segura a grade.
        
                Julian faz o mesmo.
        
                Os dois puxam.
        
                O metal resiste.
        
                — De novo!
        
                Os guardas estão cada vez mais próximos.
        
                Os dois puxam com toda a força.
        
                A dobradiça quebra.
        
                %s machuca o braço, mas a passagem está aberta.
        
                Julian atravessa.
        
                %s vai logo atrás.
        
                Minutos depois, os dois emergem do sistema de drenagem.
        
                Greyhaven está atrás deles.
        
                Agora Blackglass volta a ser o problema principal.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, saude50, 0, 1, 0, "Julian", 2, null, null, perdeFurtividade1));
        cena07JulianB.addEscolha(new Escolhas("C08_001B", "Abandonar Julian e usar a passagem lateral sozinho.", formatTexto("""
                %s encontra uma abertura estreita ao lado do túnel.
        
                Julian percebe.
        
                — Não vamos passar os dois.
        
                — Eu sei.
        
                Julian olha para %s.
        
                — Não faça isso.
        
                Os passos dos guardas ecoam atrás deles.
        
                %s entra na passagem.
        
                — Nós tínhamos um acordo!
        
                %s continua.
        
                Julian grita seu nome uma última vez.
        
                Depois a voz desaparece.
        
                Minutos depois, %s emerge do lado de fora de Greyhaven.
        
                Livre.
        
                Sozinho.
        
                E ainda sem Blackglass.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Julian", -3, null, null, ganhaFurtividade1));
        repositorioCenas.addCena(cena07JulianB);
        Cena cena07SoloA = new Cena("C07_SOLO_A", new ArrayList<>(), formatTexto("""
                %s fecha a porta de serviço atrás de si.
        
                Nenhum Dante.
        
                Nenhum Julian.
        
                Nenhuma ajuda.
        
                Apenas corredores técnicos e luzes fluorescentes.
        
                Pela sinalização na parede, a garagem fica dois níveis abaixo.
        
                A liberdade está próxima.
        
                Então %s vê dois guardas no corredor principal.
        
                Não existe caminho completamente seguro.
        
                Ele terá que escolher como passar.
                """, protagonista.getNome(), protagonista.getNome() ),
                "Sem Parceiros", 7);
        cena07SoloA.addEscolha(new Escolhas("C08_001A", "Passar pelos pontos cegos das câmeras.", formatTexto("""
                %s espera.
        
                Uma câmera vira.
        
                Depois a outra.
        
                Ele atravessa.
        
                Para atrás de uma coluna.
        
                Espera novamente.
        
                Quando os guardas percebem alguma coisa, %s já está na garagem.
        
                Minutos depois, desaparece além dos muros de Greyhaven.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, furtividade3, 0, 0, 0, null, 0, null, null, ganhaFurtividade1));
        cena07SoloA.addEscolha(new Escolhas("C08_001A", "Usar o painel de manutenção para abrir uma saída alternativa.", formatTexto("""
                %s encontra um terminal antigo.
        
                A saída principal está bloqueada.
        
                Mas o sistema ainda controla uma porta de carga.
        
                Alguns comandos depois...
        
                ACESSO LIBERADO.
        
                %s atravessa antes que o sistema volte a bloquear.
        
                Do outro lado existe apenas estrada e noite.
        
                Greyhaven ficou para trás.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, astucia3, 0, 0, 0, null, 0, null, null, ganhaAstucia1));
        cena07SoloA.addEscolha(new Escolhas("C08_001A", "Forçar a saída de emergência.", formatTexto("""
                Não existe tempo.
        
                %s corre contra a porta.
        
                Uma vez.
        
                Nada.
        
                Outra.
        
                A trava começa a ceder.
        
                Na terceira tentativa, a porta abre.
        
                Um alarme dispara imediatamente.
        
                %s cai do outro lado, dolorido.
        
                Mas do lado de fora.
        
                Ele se levanta e começa a correr.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, saude50, 0, 2, 0, null, 0, null, null, perdeFurtividade1));
        cena07SoloA.addEscolha(new Escolhas("C08_001A", "Esperar a troca de turno e correr quando surgir uma oportunidade.", formatTexto("""
                %s se esconde.
        
                Um minuto.
        
                Dois.
        
                Cinco.
        
                Finalmente os guardas mudam de posição.
        
                %s corre.
        
                Um deles percebe.
        
                — PARADO!
        
                %s não para.
        
                Ele atravessa a porta de carga segundos antes de ela fechar.
        
                Um golpe contra a grade machuca seu ombro.
        
                Mas Greyhaven fica para trás.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 1, 0, null, 0, null, null, perdeAstucia1));
        repositorioCenas.addCena(cena07SoloA);
        Cena cena07SoloB = new Cena("C07_SOLO_B", new ArrayList<>(), formatTexto("""
                %s atravessa a porta de serviço.
        
                Dante está em algum lugar de Londres.
        
                Blackglass também.
        
                Mas primeiro %s precisa sair de Greyhaven.
        
                Dois guardas bloqueiam o caminho para a garagem.
        
                Câmeras cobrem o corredor.
        
                Ninguém virá ajudar.
        
                Dessa vez, depende apenas dele.
                """, protagonista.getNome(), protagonista.getNome() ),
                "Livre, Mas Sem Blackglass", 7);
        cena07SoloB.addEscolha(new Escolhas("C08_001B", "Evitar câmeras e guardas.", formatTexto("""
                %s memoriza o movimento das câmeras.
        
                Espera o momento certo.
        
                Então atravessa.
        
                Um corredor.
        
                Depois outro.
        
                Quando chega à garagem, ninguém ainda percebeu.
        
                %s encontra uma saída de serviço e desaparece na madrugada.
        
                O próximo objetivo é simples.
        
                Encontrar Dante.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, furtividade3, 0, 0, 0, null, 0, null, null, ganhaFurtividade1));
        cena07SoloB.addEscolha(new Escolhas("C08_001B", "Hackear uma porta de manutenção.", formatTexto("""
                %s acessa o terminal.
        
                Porta de carga.
        
                Controle manual.
        
                Substituir bloqueio.
        
                Alguns segundos depois:
        
                ACESSO LIBERADO.
        
                %s atravessa.
        
                A noite de Londres está diante dele.
        
                Agora só falta descobrir onde Dante escondeu Blackglass.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, astucia3, 0, 0, 0, null, 0, null, null, ganhaAstucia1));
        cena07SoloB.addEscolha(new Escolhas("C08_001B", "Forçar a saída.", formatTexto("""
                %s corre contra a porta de emergência.
        
                O primeiro impacto dói.
        
                O segundo também.
        
                No terceiro, a trava quebra.
        
                O alarme dispara.
        
                Guardas começam a gritar.
        
                Mas %s já está do lado de fora.
        
                Livre.
        
                Ferido.
        
                E com uma única pergunta na cabeça:
        
                onde está Dante?
                """, protagonista.getNome(), protagonista.getNome() ),
                null, saude50, 0, 2, 0, null, 0, null, null, perdeFurtividade1));
        cena07SoloB.addEscolha(new Escolhas("C08_001B", "Esperar uma oportunidade e correr.", formatTexto("""
                %s espera escondido.
        
                Quando dois guardas deixam o posto, ele corre.
        
                Um terceiro percebe tarde demais.
        
                — PARADO!
        
                %s atravessa a saída.
        
                Um golpe contra a grade machuca seu braço.
        
                Mas ele continua.
        
                Greyhaven ficou para trás.
        
                Dante e Blackglass estão em algum lugar à frente.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 1, 0, null, 0, null, null, perdeAstucia1));
        repositorioCenas.addCena(cena07SoloB);
        Cena cena07ImprovisoA = new Cena("C07_IMPROVISO_A", new ArrayList<>(), formatTexto("""
                02:08.
        
                %s está escondido próximo à lavanderia.
        
                O plano é simples.
        
                O que normalmente significa que alguma coisa vai dar errado.
        
                Às 02:10, ele danifica uma válvula da tubulação.
        
                Água começa a atingir o sistema elétrico.
        
                As luzes piscam.
        
                Um alarme dispara.
        
                Portas de segurança começam a abrir e fechar automaticamente.
        
                Guardas correm pelo corredor.
        
                Detentos começam a gritar.
        
                Em poucos segundos, Greyhaven vira caos.
        
                %s sorri.
        
                Agora precisa apenas usar esse caos para sair.
                """, protagonista.getNome(), protagonista.getNome() ),
                "Caos Controlado", 7);
        cena07ImprovisoA.addEscolha(new Escolhas("C08_001A", "Manipular o sistema para abrir a saída de carga.", formatTexto("""
                %s alcança o painel de emergência.
        
                Enquanto alarmes piscam, ele acessa o controle das portas.
        
                BLOQUEIO.
        
                BLOQUEIO.
        
                SAÍDA DE CARGA.
        
                ABRIR.
        
                A porta começa a subir.
        
                %s corre.
        
                Quando o sistema reinicia, ele já está fora de Greyhaven.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, astucia3, 0, 0, 0, null, 0, null, null, ganhaAstucia1));
        cena07ImprovisoA.addEscolha(new Escolhas("C08_001A", "Usar a confusão para passar despercebido.", formatTexto("""
                Guardas correm em todas as direções.
        
                %s mantém a cabeça baixa e acompanha o fluxo.
        
                Um corredor.
        
                Uma escada.
        
                Outra porta.
        
                Ninguém presta atenção em um único detento durante uma emergência.
        
                Quando percebem o desaparecimento, %s já está fora.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, furtividade3, 0, 0, 0, null, 0, null, null, ganhaFurtividade1));
        cena07ImprovisoA.addEscolha(new Escolhas("C08_001A", "Correr pela saída durante o caos.", formatTexto("""
                %s decide parar de pensar.
        
                E começa a correr.
        
                Alarmes.
        
                Guardas.
        
                Água.
        
                Gritos.
        
                Ele atravessa tudo.
        
                Um guarda quase consegue segurá-lo.
        
                %s escapa, mas bate violentamente contra uma grade.
        
                Dói.
        
                Não importa.
        
                Alguns segundos depois, ele está fora.
        
                Livre.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 1, 0, null, 0, null, null, perdeAstucia1));
        repositorioCenas.addCena(cena07ImprovisoA);
        Cena cena07ImprovisoB = new Cena("C07_IMPROVISO_B", new ArrayList<>(), formatTexto("""
                02:10.
        
                Dante espera %s na Ala Oeste.
        
                Pelo menos é o que o bilhete dizia.
        
                %s está do outro lado da prisão.
        
                Ele abre uma válvula próxima ao sistema elétrico.
        
                Água invade o corredor técnico.
        
                As luzes piscam.
        
                Então apagam.
        
                Alarmes disparam.
        
                Guardas começam a correr.
        
                %s olha para a saída de carga.
        
                Dante pode ficar esperando.
        
                Blackglass não vai.
        
                %s começa a fuga.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "O Caos é uma Saída", 7);
        cena07ImprovisoB.addEscolha(new Escolhas("C08_001B", "Usar o painel para abrir a saída.", formatTexto("""
                %s acessa o sistema durante a falha elétrica.
        
                A segurança está reiniciando.
        
                É a oportunidade perfeita.
        
                Alguns comandos depois, a saída de carga abre.
        
                %s atravessa.
        
                Greyhaven desaparece atrás dele.
        
                Agora existe apenas um objetivo:
        
                Dante.
        
                E Blackglass.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, astucia3, 0, 0, 0, null, 0, null, null, ganhaAstucia1));
        cena07ImprovisoB.addEscolha(new Escolhas("C08_001B", "Se esconder entre funcionários durante a emergência.", formatTexto("""
                %s acompanha um grupo de funcionários.
        
                Cabeça baixa.
        
                Passos tranquilos.
        
                Ninguém tem tempo para observar rostos durante o caos.
        
                Uma porta se abre.
        
                Depois outra.
        
                Quando um guarda finalmente percebe algo estranho, %s já atravessou
                o último portão.
        
                Livre.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, furtividade3, 0, 0, 0, null, 0, null, null, ganhaFurtividade1));
        cena07ImprovisoB.addEscolha(new Escolhas("C08_001B", "Aproveitar a confusão e simplesmente correr.", formatTexto("""
                Não existe mais plano.
        
                %s corre.
        
                Um guarda tenta segurá-lo.
        
                %s escapa.
        
                Outro bloqueia o corredor.
        
                %s muda de direção.
        
                Uma grade começa a fechar.
        
                Ele passa por baixo no último segundo.
        
                O impacto machuca seu ombro.
        
                Mas alguns metros depois...
        
                Não existem mais muros.
        
                %s está livre.
        
                E Dante tem algo que pertence a ele.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 1, 0, null, 0, null, null, perdeAstucia1));
        repositorioCenas.addCena(cena07ImprovisoB);
        Cena cena08001A = new Cena("C08_001A", new ArrayList<>(List.of(dante)), formatTexto("""
                Londres.
        
                05:42.
        
                A chuva transforma as ruas em reflexos de neon e faróis.
        
                Depois de Greyhaven, qualquer lugar deveria parecer confortável.
        
                Não parece.
        
                %s entra em uma antiga oficina abandonada no sul de Londres.
        
                O lugar já serviu como esconderijo anos atrás.
        
                Agora existe apenas poeira, ferramentas enferrujadas e um sofá que
                provavelmente deveria ter sido jogado fora antes de %s ser preso.
        
                Uma voz surge no escuro.
        
                — Você está com uma aparência horrível.
        
                %s se vira imediatamente.
        
                Dante está encostado em uma bancada.
        
                — Você poderia ter avisado que estava aqui.
        
                — E perder sua reação?
        
                Dante sorri.
        
                %s não.
        
                — Blackglass.
        
                O sorriso desaparece.
        
                — Ainda está com Ashcroft.
        
                — Eu sei.
        
                Dante coloca algumas fotografias sobre a bancada.
        
                A primeira mostra a Mansão Ashcroft.
        
                A segunda mostra um comboio deixando a propriedade.
        
                A terceira mostra um edifício no centro de Londres.
        
                ASHCROFT TOWER.
        
                — Victor transferiu Blackglass ontem à noite — diz Dante.
        
                %s pega a fotografia.
        
                — Para onde?
        
                — Cofre privado no último andar.
        
                Dante coloca outra imagem sobre a mesa.
        
                Um caminhão blindado.
        
                — E amanhã de manhã ela sai de Londres.
        
                %s olha para ele.
        
                — Como sabe?
        
                — Porque passei os últimos dias tentando consertar nosso desastre.
        
                Silêncio.
        
                Dante cruza os braços.
        
                — Temos uma noite.
        
                — Uma noite para quê?
        
                Dante encara %s.
        
                — Para terminar o roubo.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "De Volta a Londres", 8);
        cena08001A.addEscolha(new Escolhas("C08_002A", "Aceitar trabalhar com Dante novamente.", formatTexto("""
                %s observa as fotografias.
        
                — Uma noite.
        
                Dante sorri.
        
                — Sabia que você ia aceitar.
        
                — Não terminei.
        
                O sorriso desaparece.
        
                — Dessa vez, se alguma coisa der errado...
        
                %s aponta para Dante.
        
                — Você não desaparece.
        
                Dante sustenta o olhar.
        
                — Justo.
        
                %s estende a mão.
        
                Dante aperta.
        
                Pela primeira vez desde a Mansão Ashcroft, os dois estão novamente
                do mesmo lado.
        
                Pelo menos por enquanto.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Dante", 2, null, null, ganhaCharme1));
        cena08001A.addEscolha(new Escolhas("C08_002A", "Aceitar o plano, mas deixar claro que Dante não está no comando.", formatTexto("""
                %s pega as fotografias.
        
                — Eu vou.
        
                Dante sorri.
        
                — Ótimo.
        
                — Mas você não decide nada.
        
                O sorriso desaparece.
        
                — Como é?
        
                — A última vez que seguimos seu plano eu terminei em Greyhaven.
        
                Dante cruza os braços.
        
                — Eu voltei por você.
        
                — Depois de alguns dias.
        
                Silêncio.
        
                %s coloca a fotografia da torre sobre a mesa.
        
                — Se vamos fazer isso, fazemos do meu jeito.
        
                Dante parece irritado.
        
                Mas assente.
        
                — Certo.
        
                %s guarda as fotografias.
        
                A parceria voltou.
        
                A confiança, nem tanto.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Dante", -1, null, null, perdeCharme1));
        cena08001A.addEscolha(new Escolhas("C08_002A", "Dizer que não confia em Dante, mas aceitar as informações.", formatTexto("""
                %s recolhe as fotografias.
        
                — Obrigado.
        
                Dante franze a testa.
        
                — Obrigado?
        
                — Pela informação.
        
                — Eu vou com você.
        
                %s olha diretamente para ele.
        
                — Não decidi isso.
        
                Dante fica em silêncio.
        
                — Ainda está bravo?
        
                — Eu fui preso.
        
                — Eu voltei.
        
                — E eu ainda não sei se você voltou por mim ou por Blackglass.
        
                Dante não responde imediatamente.
        
                %s percebe.
        
                — Exatamente.
        
                Ele pega a fotografia da torre.
        
                — Primeiro fazemos um plano.
        
                — Depois decido quem participa.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, null, 0, null, null, ganhaAstucia1));
        repositorioCenas.addCena(cena08001A);
        Cena cena08002A = new Cena("C08_002A", new ArrayList<>(List.of(dante)), formatTexto("""
                21:16.
        
                A planta da Ashcroft Tower está aberta sobre a bancada.
        
                Dante marca o último andar.
        
                — Cofre particular.
        
                %s observa o desenho.
        
                — Quantos seguranças?
        
                — Muitos.
        
                — Câmeras?
        
                — Muitas.
        
                — Alarmes?
        
                Dante aponta para %s.
        
                — Também muitos.
        
                %s suspira.
        
                — Excelente plano.
        
                — Eu ainda não apresentei o plano.
        
                Dante coloca quatro fotografias sobre a mesa.
        
                A primeira mostra a garagem subterrânea.
        
                — Entrada de serviço.
        
                A segunda mostra plantas antigas do edifício.
        
                — Julian pode conhecer partes da estrutura que não aparecem aqui.
        
                A terceira mostra o símbolo usado pela rede de contatos de Miller.
        
                — Sua amiga de Greyhaven conhece gente que falsifica credenciais.
        
                %s ergue uma sobrancelha.
        
                — "Minha amiga"?
        
                — Ela ameaçou quebrar meus dedos pelo telefone.
        
                — Então ela gostou de você.
        
                Dante ignora.
        
                Por último, coloca a planta principal sobre a mesa.
        
                — Ou você entra sozinho.
        
                %s observa todas as possibilidades.
        
                Blackglass está no último andar.
        
                Amanhã ela desaparece.
        
                Esta é a última oportunidade.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "Quatro Caminhos", 8);
        cena08002A.addEscolha(new Escolhas("C09_DANTE_A", "Entrar pela garagem subterrânea com Dante.", formatTexto("""
                %s aponta para a garagem.
        
                — Aqui.
        
                Dante sorri.
        
                — Sabia que escolheria a opção divertida.
        
                — Você dirige.
        
                — Retiro o que disse.
        
                Dante começa a separar equipamentos.
        
                Uniformes.
        
                Comunicadores.
        
                Ferramentas.
        
                %s observa Ashcroft Tower pela janela da oficina.
        
                Uma última invasão.
        
                Uma última tentativa.
        
                Desta vez Blackglass não ficará para trás.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Dante", 1, null, null, ganhaFurtividade1));
        cena08002A.addEscolha(new Escolhas("C09_JULIAN_A", "Usar as informações de Julian para encontrar uma entrada escondida.", formatTexto("""
                %s coloca o antigo mapa de Greyhaven sobre a mesa.
        
                Dante olha.
        
                — Isso é uma prisão.
        
                — Olha o símbolo no canto.
        
                Dante aproxima a fotografia das plantas da Ashcroft Tower.
        
                O mesmo símbolo aparece em ambas.
        
                Ashcroft Engineering.
        
                %s pega o telefone.
        
                — Julian disse que o pai dele financiava construções que não apareciam
                nas plantas oficiais.
        
                — E você acredita nele?
        
                %s começa a discar.
        
                — Mais do que acredito em algumas pessoas.
        
                Dante entende a indireta.
        
                Minutos depois, Julian atende.
        
                %s tem apenas uma pergunta.
        
                — Como eu entro na torre do seu pai?
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                mapaManutencao, null, 0, 0, 0, "Julian", 1, null, null, ganhaAstucia1));
        cena08002A.addEscolha(new Escolhas("C09_MILLER_A", "Pagar Miller por credenciais falsas de segurança.", formatTexto("""
                %s liga para o número que Miller havia deixado.
        
                Ela atende no quarto toque.
        
                — Se está ligando para pedir outra chave, o preço aumentou.
        
                — Preciso entrar na Ashcroft Tower.
        
                Silêncio.
        
                — Isso é consideravelmente pior.
        
                — Consegue?
        
                Miller suspira.
        
                — Segurança privada?
        
                — Sim.
        
                — Duzentas libras.
        
                %s olha para Dante.
        
                Dante balança a cabeça.
        
                — Cento e cinquenta.
        
                — Duzentas e cinquenta agora.
        
                — Certo. Duzentas.
        
                Miller ri.
        
                — Sabia que aprenderia.
        
                A ligação termina.
        
                Dante olha para %s.
        
                — Ela é sempre assim?
        
                — Quando está de bom humor.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, null, 200, 0, 0, "Miller", 1, null, null, semAlteracaoAtributos));
        cena08002A.addEscolha(new Escolhas("C09_SOLO_A", "Criar uma rota própria e invadir Ashcroft Tower sozinho.", formatTexto("""
                %s afasta as fotografias.
        
                — Não.
        
                Dante olha para ele.
        
                — Não o quê?
        
                — Não vou entrar pela garagem.
        
                — Não vou depender de Julian.
        
                — E não vou comprar uma identidade falsa.
        
                Dante cruza os braços.
        
                — Então qual é o plano?
        
                %s gira a planta da torre.
        
                Entrada principal.
        
                Garagem.
        
                Elevadores.
        
                Sistema contra incêndio.
        
                Manutenção externa.
        
                Ele começa a marcar pontos no papel.
        
                — Eu entro aqui.
        
                Dante observa.
        
                — Isso é ridículo.
        
                %s continua desenhando.
        
                — Depois subo por aqui.
        
                Dante se aproxima.
        
                Alguns segundos depois:
        
                — Isso pode funcionar.
        
                %s sorri.
        
                — Eu sei.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, astucia4, 0, 0, 0, null, 0, null, null, ganhaAstucia1));
        repositorioCenas.addCena(cena08002A);
        Cena cena08001B = new Cena("C08_001B", new ArrayList<>(List.of(dante)), formatTexto("""
                Londres.
        
                04:51.
        
                %s recebe uma mensagem de um número desconhecido.
        
                Apenas um endereço.
        
                E uma palavra:
        
                "SOZINHO."
        
                Uma hora depois, %s chega a uma antiga gráfica abandonada.
        
                A porta está aberta.
        
                Dante espera no segundo andar.
        
                Há uma mochila sobre a mesa.
        
                %s entra.
        
                Dante não sorri.
        
                — Fechou a porta?
        
                — Sim.
        
                — Foi seguido?
        
                — Não.
        
                %s olha para a mochila.
        
                — Está aí?
        
                Dante permanece imóvel por alguns segundos.
        
                Depois abre.
        
                Blackglass.
        
                A pedra negra está envolvida em tecido.
        
                Mesmo depois de tudo, ela parece pequena demais para ter causado
                tantos problemas.
        
                %s estende a mão.
        
                Dante fecha a mochila.
        
                — Ainda não.
        
                %s encara o antigo parceiro.
        
                — Eu fui preso por causa disso.
        
                — Eu sei.
        
                — Então abre.
        
                Dante respira fundo.
        
                — Tentei vender.
        
                Silêncio.
        
                %s não esperava aquela resposta.
        
                — E?
        
                — O comprador olhou para Blackglass por aproximadamente três segundos.
        
                Dante coloca a mochila sobre a mesa.
        
                — Depois disse que não era uma joia.
        
                %s se aproxima.
        
                — Então o que é?
        
                Dante olha para Blackglass.
        
                — É exatamente isso que precisamos descobrir.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "A Pedra Entre Nós", 8);
        cena08001B.addEscolha(new Escolhas("C08_002B", "Guardar a raiva e ouvir Dante até o fim.", formatTexto("""
                %s afasta a mão da mochila.
        
                — Fala.
        
                Dante parece surpreso.
        
                — Só isso?
        
                — Não significa que não vou te bater depois.
        
                — Justo.
        
                Dante abre a mochila novamente.
        
                — O comprador reconheceu o símbolo na base.
        
                %s observa Blackglass.
        
                Pela primeira vez percebe pequenas linhas gravadas na parte inferior.
        
                — Ele disse que aquilo parece um mecanismo de autenticação.
        
                — Para quê?
        
                Dante balança a cabeça.
        
                — Antes de responder, recebeu uma ligação.
        
                — E?
        
                — Levantou.
        
                — Foi embora.
        
                — E vinte minutos depois três homens de Ashcroft apareceram no local.
        
                %s olha para Dante.
        
                — Então Victor está procurando a pedra.
        
                — Não.
        
                Dante encara Blackglass.
        
                — Victor está desesperado por ela.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Dante", 2, null, null, ganhaCharme1));
        cena08001B.addEscolha(new Escolhas("C08_002B", "Exigir que Dante entregue Blackglass imediatamente.", formatTexto("""
                %s estende a mão.
        
                — Me dá.
        
                Dante não se move.
        
                — Primeiro precisamos descobrir...
        
                — Não.
        
                %s aponta para Blackglass.
        
                — Eu fiquei em Greyhaven enquanto você estava com ela.
        
                — E eu voltei para buscar você.
        
                — Depois de tentar vender.
        
                Dante fecha a expressão.
        
                — Eu precisava saber quanto valia.
        
                — Nós dois sabemos quanto vale.
        
                — Dinheiro não é o problema!
        
                Silêncio.
        
                Dante percebe que levantou a voz.
        
                %s mantém a mão estendida.
        
                — Então me entrega.
        
                Dante fecha novamente a mochila.
        
                — Quando entendermos o que isso é.
        
                A confiança entre os dois está por um fio.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Dante", -2, null, null, perdeCharme1));
        cena08001B.addEscolha(new Escolhas("C08_002B", "Fingir calma e observar o que Dante não está contando.", formatTexto("""
                %s se afasta da mochila.
        
                — Certo.
        
                Dante franze a testa.
        
                — Certo?
        
                — Mostra o que descobriu.
        
                Dante começa a explicar o encontro com o comprador.
        
                %s escuta.
        
                Mas também observa.
        
                Sempre que fala do comprador, Dante parece tranquilo.
        
                Quando fala de Victor, fica tenso.
        
                Quando fala de Blackglass...
        
                olha para a mochila.
        
                Como se estivesse com medo dela.
        
                %s percebe algo.
        
                — Você descobriu mais alguma coisa.
        
                Dante fica em silêncio.
        
                — Não.
        
                A resposta foi rápida demais.
        
                %s não insiste.
        
                Ainda.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, astucia3, 0, 0, 0, null, 0, null, null, ganhaAstucia1));
        repositorioCenas.addCena(cena08001B);
        Cena cena08002B = new Cena("C08_002B", new ArrayList<>(List.of(dante)), formatTexto("""
                Dante coloca Blackglass sob uma luminária.
        
                %s observa.
        
                A pedra não é completamente sólida.
        
                Sob determinado ângulo, pequenas linhas metálicas aparecem dentro
                do material negro.
        
                — Isso não é natural — diz %s.
        
                — Exatamente.
        
                Dante vira Blackglass.
        
                Na base existe uma pequena abertura.
        
                Quase invisível.
        
                %s aproxima o rosto.
        
                Parece um encaixe.
        
                Não uma rachadura.
        
                Um encaixe feito deliberadamente.
        
                Dante coloca ao lado da pedra um aparelho eletrônico.
        
                — Tentei escanear.
        
                A tela mostra apenas:
        
                ACESSO NEGADO.
        
                — O comprador disse uma coisa antes de ir embora.
        
                %s olha para Dante.
        
                — O quê?
        
                Dante respira fundo.
        
                — "Blackglass não guarda dinheiro."
        
                — Então o que guarda?
        
                Dante olha para a pedra.
        
                — Segredos.
        
                %s encara o pequeno encaixe.
        
                Victor Ashcroft não estava protegendo uma joia.
        
                Estava protegendo alguma coisa que aquela joia podia abrir.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "O Segredo no Vidro", 8);
        cena08002B.addEscolha(new Escolhas("C09_SEGREDO_B", "Comparar o fragmento encontrado na mansão com o encaixe de Blackglass.", formatTexto("""
                %s fica imóvel.
        
                O encaixe.
        
                Ele já viu aquele formato.
        
                Dante percebe.
        
                — O quê?
        
                %s coloca a mão no bolso.
        
                De dentro, retira o pequeno fragmento negro encontrado na noite
                do roubo.
        
                Dante arregala os olhos.
        
                — Você estava com isso o tempo inteiro?
        
                — Eu nem sabia o que era.
        
                %s aproxima o fragmento da pedra.
        
                O formato é quase perfeito.
        
                — Não faz isso — diz Dante.
        
                %s encaixa.
        
                CLIQUE.
        
                Uma linha azul percorre Blackglass.
        
                Dante dá um passo para trás.
        
                O aparelho eletrônico sobre a mesa liga sozinho.
        
                A tela muda.
        
                ACESSO AUTORIZADO.
        
                Depois:
        
                ASHCROFT PRIVATE ARCHIVE
                2.418 ARQUIVOS ENCONTRADOS
        
                Dante olha para %s.
        
                — Que diabos seu amigo Victor estava escondendo?
        
                %s observa os nomes surgindo na tela.
        
                Empresas.
        
                Contas.
        
                Contratos.
        
                Pagamentos.
        
                Políticos.
        
                Policiais.
        
                Juízes.
        
                Empresas de segurança.
        
                Greyhaven.
        
                %s sente o estômago apertar.
        
                Blackglass vale muito mais do que milhões.
        
                Ela pode destruir Victor Ashcroft.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                fragmentoBlackglass, null, 0, 0, 0, "Dante", 1, null, null, ganhaAstucia1));
        cena08002B.addEscolha(new Escolhas("C09_DECIFRAR_B", "Tentar quebrar a autenticação de Blackglass.", formatTexto("""
                %s conecta o aparelho ao notebook.
        
                Dante observa.
        
                — Você sabe o que está fazendo?
        
                — Não.
        
                — Excelente.
        
                %s começa a analisar o sistema.
        
                A primeira camada cai.
        
                Depois a segunda.
        
                A terceira bloqueia completamente o acesso.
        
                %s tenta novamente.
        
                Alguns arquivos aparecem na tela antes de o sistema fechar.
        
                ASHCROFT HOLDINGS.
        
                GREYHAVEN.
        
                PROJECT BLACKGLASS.
        
                TRANSFERÊNCIAS INTERNACIONAIS.
        
                A tela fica preta.
        
                Dante encara %s.
        
                — Você viu?
        
                — Vi.
        
                Não conseguiram abrir tudo.
        
                Mas conseguiram o suficiente para entender uma coisa.
        
                Blackglass não é uma joia.
        
                É uma chave.
        
                E Victor Ashcroft tem muito mais a perder do que dinheiro.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, astucia4, 0, 0, 0, null, 0, null, null, ganhaAstucia1));
        cena08002B.addEscolha(new Escolhas("C09_DANTE_B", "Guardar Blackglass e descobrir o segredo depois.", formatTexto("""
                %s fecha o notebook.
        
                — Não vamos abrir isso aqui.
        
                Dante olha para ele.
        
                — Finalmente uma ideia sensata.
        
                %s envolve Blackglass novamente no tecido.
        
                — Precisamos de um lugar seguro.
        
                — Conheço um.
        
                — Melhor do que o lugar onde tentou vender?
        
                Dante faz uma careta.
        
                — Consideravelmente.
        
                %s entrega a mochila para ele.
        
                Dante parece surpreso.
        
                — Está confiando em mim?
        
                — Não exagera.
        
                Dante sorri.
        
                Os dois deixam a gráfica juntos.
        
                Blackglass continua sem revelar todos os seus segredos.
        
                Mas, pela primeira vez, os dois carregam o problema juntos.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Dante", 2, null, null, ganhaAstucia1));
        cena08002B.addEscolha(new Escolhas("C09_SOLO_B", "Tomar Blackglass de Dante e seguir sozinho.", formatTexto("""
                %s pega Blackglass.
        
                Dante segura seu pulso.
        
                — Não.
        
                Os dois ficam imóveis.
        
                — Solta.
        
                — Não até você me dizer o que pretende fazer.
        
                %s olha para a mão de Dante.
        
                — Você tentou vender pelas minhas costas.
        
                — Eu estava tentando resolver isso!
        
                — Para você.
        
                Dante aperta a mandíbula.
        
                %s puxa o braço.
        
                Desta vez Dante solta.
        
                Blackglass fica nas mãos de %s.
        
                — Se sair por essa porta com ela...
        
                Dante não termina.
        
                %s coloca a pedra dentro da jaqueta.
        
                — O quê?
        
                Silêncio.
        
                Dante balança a cabeça.
        
                — Nada.
        
                %s caminha até a saída.
        
                A parceria que começou anos atrás pode ter acabado naquela sala.
        
                Mas Blackglass finalmente está em suas mãos.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Dante", -3, null, null, perdeCharme1));
        repositorioCenas.addCena(cena08002B);
        Cena cena09DanteA = new Cena("C09_DANTE_A", new ArrayList<>(List.of(dante)), formatTexto("""
                23:41.
        
                Uma van de manutenção entra na garagem subterrânea da Ashcroft Tower.
        
                Dante está no volante.
        
                %s ocupa o banco ao lado.
        
                Os dois usam uniformes falsos.
        
                — Sabe qual é a parte mais preocupante? — pergunta Dante.
        
                — Qual?
        
                — Essa nem é a pior ideia que já tivemos.
        
                A cancela abre.
        
                Dante dirige até o nível B3.
        
                Os dois descem.
        
                O elevador principal exige identificação.
        
                A escada está sendo vigiada.
        
                E uma câmera acompanha o corredor.
        
                Dante olha para %s.
        
                — Você escolhe.
        
                — Como nos velhos tempos?
        
                — Espero que melhor do que nos velhos tempos.
        
                Blackglass está quarenta e dois andares acima.
                """, protagonista.getNome(), protagonista.getNome() ),
                "Uma Última Vez", 9);
        cena09DanteA.addEscolha(new Escolhas("C09_VICTOR_A", "Passar pelo ponto cego das câmeras.", formatTexto("""
                %s observa o movimento da câmera.
        
                Três segundos para a esquerda.
        
                Cinco para a direita.
        
                — Agora.
        
                Os dois atravessam.
        
                Dante quase esbarra em um carrinho de ferramentas.
        
                %s o segura antes que caia.
        
                — Silencioso — sussurra %s.
        
                — Eu estava sendo silencioso.
        
                — Você quase derrubou cinquenta quilos de metal.
        
                Os dois desaparecem pela escada de serviço.
        
                Minutos depois, alcançam o último andar.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, furtividade3, 0, 0, 0, "Dante", 1, null, null, ganhaFurtividade1));
        cena09DanteA.addEscolha(new Escolhas("C09_VICTOR_A", "Burlar o controle do elevador.", formatTexto("""
                %s abre o painel ao lado do elevador.
        
                Dante observa o corredor.
        
                — Quanto tempo?
        
                — Menos se você parar de perguntar.
        
                Alguns fios.
        
                Um circuito.
        
                Um código de manutenção.
        
                A luz do elevador muda para verde.
        
                Dante sorri.
        
                — Sentiu minha falta.
        
                — Do silêncio, principalmente.
        
                As portas fecham.
        
                Último andar.
        
                O elevador começa a subir.
                """, protagonista.getNome() ),
                null, astucia3, 0, 0, 0, "Dante", 1, null, null, ganhaAstucia1));
        cena09DanteA.addEscolha(new Escolhas("C09_VICTOR_A", "Usar a ordem de manutenção preparada por Dante.", formatTexto("""
                Dante entrega uma prancheta para %s.
        
                — Confia em mim.
        
                — Normalmente essa frase termina mal.
        
                Um segurança se aproxima.
        
                — Identificação.
        
                %s entrega os documentos.
        
                O homem analisa.
        
                Alguns segundos passam.
        
                Então devolve.
        
                — Elevador de serviço. Último andar.
        
                Dante espera o guarda se afastar.
        
                — Viu?
        
                — Ainda pode terminar mal.
        
                — Otimismo é importante.
        
                Os dois entram no elevador.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Dante", 1, null, null, ganhaCharme1));
        repositorioCenas.addCena(cena09DanteA);
        Cena cena09JulianA = new Cena("C09_JULIAN_A", new ArrayList<>(List.of(julian)), formatTexto("""
                %s encontra Julian em um estacionamento a dois quarteirões da
                Ashcroft Tower.
        
                Julian observa o prédio.
        
                — Passei metade da infância lá.
        
                — Então imagino que conheça uma entrada.
        
                — Conheço uma que meu pai acha que ninguém conhece.
        
                Julian conduz %s até um edifício vizinho.
        
                No subsolo existe um antigo corredor técnico.
        
                Na parede:
        
                ASHCROFT ENGINEERING.
        
                Julian remove uma placa metálica.
        
                Atrás dela existe uma passagem estreita.
        
                — Meu pai construiu isso caso precisasse sair sem ser visto.
        
                %s olha para Julian.
        
                — Sua família tem hábitos estranhos.
        
                — Você ainda não viu nada.
        
                Os dois entram.
        
                O túnel termina diretamente abaixo do cofre particular de Victor.
                """, protagonista.getNome(), protagonista.getNome() ),
                "A Porta do Pai", 9);
        cena09JulianA.addEscolha(new Escolhas("C09_VICTOR_A", "Seguir exatamente a rota indicada por Julian.", formatTexto("""
                Julian segue na frente.
        
                %s observa cada curva.
        
                — Direita.
        
                Depois uma escada.
        
                — Agora esquerda.
        
                Os dois chegam diante de uma porta sem identificação.
        
                Julian aproxima a mão do leitor.
        
                Verde.
        
                %s ergue uma sobrancelha.
        
                — Ainda tem acesso?
        
                — Meu pai esquece de apagar algumas coisas.
        
                Julian abre a porta.
        
                O corredor do último andar aparece do outro lado.
        
                — Blackglass está atrás daquela parede.
        
                %s respira fundo.
        
                Chegaram.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Julian", 2, null, null, semAlteracaoAtributos));
        cena09JulianA.addEscolha(new Escolhas("C09_VICTOR_A", "Verificar se Victor deixou alguma armadilha na rota.", formatTexto("""
                %s para.
        
                — Espera.
        
                Julian olha para trás.
        
                — O quê?
        
                %s percebe um sensor quase invisível junto à parede.
        
                — Seu pai mudou o corredor.
        
                Julian se aproxima.
        
                — Isso não estava aqui.
        
                %s desativa cuidadosamente o sensor.
        
                Alguns segundos depois, a passagem está livre.
        
                Julian sorri.
        
                — Talvez seja bom ter um ladrão por perto.
        
                — Finalmente percebeu.
        
                Os dois avançam até o último andar.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, astucia3, 0, 0, 0, "Julian", 1, null, null, ganhaAstucia1));
        cena09JulianA.addEscolha(new Escolhas("C09_VICTOR_A", "Memorizar a rota e seguir sozinho.", formatTexto("""
                %s observa a passagem.
        
                — Daqui eu continuo sozinho.
        
                Julian fecha a expressão.
        
                — Nós tínhamos um acordo.
        
                — Tínhamos uma rota.
        
                — Não é a mesma coisa.
        
                %s entra no corredor.
        
                Julian permanece parado.
        
                — Você está cometendo um erro.
        
                %s não responde.
        
                Alguns minutos depois, chega ao último andar.
        
                Sozinho.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Julian", -2, null, null, ganhaAstucia1));
        repositorioCenas.addCena(cena09JulianA);
        Cena cena09MillerA = new Cena("C09_MILLER_A", new ArrayList<>(List.of(miller)), formatTexto("""
                22:58.
        
                %s entra pela porta principal da Ashcroft Tower.
        
                Terno.
        
                Credencial.
        
                Pasta executiva.
        
                Tudo fornecido por Miller.
        
                O telefone vibra.
        
                Mensagem dela:
        
                "SE PERGUNTAREM, VOCÊ É DA AUDITORIA."
        
                Outra mensagem.
        
                "E ME DEVE."
        
                %s guarda o telefone.
        
                Um segurança se aproxima.
        
                — Senhor?
        
                %s mostra a credencial.
        
                O homem verifica o sistema.
        
                O nome aparece.
        
                A fotografia aparece.
        
                Miller realmente fez um bom trabalho.
        
                O segurança devolve o cartão.
        
                — Sua autorização permite até o quadragésimo andar.
        
                O cofre está no quadragésimo segundo.
        
                Ainda faltam dois.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "Funcionário do Mês", 9);
        cena09MillerA.addEscolha(new Escolhas("C09_VICTOR_A", "Convencer o segurança de que a auditoria inclui o último andar.", formatTexto("""
                %s olha para o cartão.
        
                Depois para o segurança.
        
                — Isso está errado.
        
                — Senhor?
        
                — Minha equipe foi enviada pelo conselho para revisar os registros
                particulares do Sr. Ashcroft.
        
                — Eu não fui informado.
        
                %s suspira.
        
                — É exatamente por isso que estou aqui.
        
                O segurança hesita.
        
                — Posso ligar para meu superior.
        
                — Claro.
        
                %s olha para o relógio.
        
                — E explicar por que uma auditoria confidencial foi atrasada.
        
                O segurança para.
        
                Alguns segundos depois, libera o elevador.
        
                — Quadragésimo segundo andar.
        
                %s sorri.
        
                — Obrigado.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Miller", 1, null, null, ganhaCharme1));
        cena09MillerA.addEscolha(new Escolhas("C09_VICTOR_A", "Alterar a autorização da credencial.", formatTexto("""
                %s entra no banheiro executivo.
        
                Retira um pequeno dispositivo.
        
                Conecta a credencial.
        
                NÍVEL 40.
        
                Ele modifica dois valores.
        
                NÍVEL 42.
        
                %s recoloca o cartão no bolso.
        
                Dez minutos depois, o elevador aceita a nova autorização.
        
                As portas se fecham.
        
                Destino:
        
                42.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, astucia3, 0, 0, 0, "Miller", 1, null, null, ganhaAstucia1));
        cena09MillerA.addEscolha(new Escolhas("C09_VICTOR_A", "Pagar £150 para Miller liberar remotamente outro acesso.", formatTexto("""
                %s manda uma mensagem.
        
                "PRECISO DO 42."
        
                Miller responde quase imediatamente.
        
                "150."
        
                %s digita:
        
                "VOCÊ JÁ RECEBEU."
        
                Resposta:
        
                "ISSO ERA PELA IDENTIDADE."
        
                %s fecha os olhos.
        
                Transfere o dinheiro.
        
                Trinta segundos depois:
        
                "TENTA AGORA."
        
                O cartão passa no leitor.
        
                Verde.
        
                %s entra no elevador.
        
                Miller realmente cobra por tudo.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 150, 0, 0, "Miller", 1, null, null, semAlteracaoAtributos));
        repositorioCenas.addCena(cena09MillerA);
        Cena cena09SoloA = new Cena("C09_SOLO_A", new ArrayList<>(), formatTexto("""
                %s observa Ashcroft Tower do edifício vizinho.
        
                Quarenta e dois andares.
        
                Segurança privada.
        
                Câmeras.
        
                Sensores.
        
                Nenhum parceiro.
        
                Exatamente como decidiu.
        
                Um cabo de manutenção passa entre os dois prédios.
        
                A entrada de serviço fica oito andares abaixo.
        
                O sistema contra incêndio possui acesso remoto.
        
                Existem várias opções.
        
                Nenhuma delas parece particularmente segura.
        
                %s sorri.
        
                Perfeito.
                """, protagonista.getNome(), protagonista.getNome() ),
                "Quarenta e Dois Andares", 9);
        cena09SoloA.addEscolha(new Escolhas("C09_VICTOR_A", "Usar a estrutura externa de manutenção.", formatTexto("""
                %s prende o equipamento ao cabo.
        
                Olha para baixo.
        
                Quarenta andares de queda.
        
                — Excelente ideia.
        
                Ele começa a atravessar.
        
                Vento.
        
                Chuva.
        
                Metal escorregadio.
        
                Minutos depois, alcança a lateral da Ashcroft Tower.
        
                Uma janela de manutenção.
        
                Uma trava.
        
                Um movimento preciso.
        
                %s entra.
        
                O último andar está logo acima.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, furtividade4, 0, 0, 0, null, 0, null, null, ganhaFurtividade1));
        cena09SoloA.addEscolha(new Escolhas("C09_VICTOR_A", "Criar um falso alerta de incêndio.", formatTexto("""
                %s acessa remotamente o sistema de segurança.
        
                ALARME DE INCÊNDIO.
        
                ANDARES 38-42.
        
                Confirmar?
        
                Sim.
        
                A torre inteira reage.
        
                Funcionários descem.
        
                Guardas abandonam posições.
        
                Elevadores são bloqueados.
        
                Portas de emergência são liberadas.
        
                No meio da evacuação, %s entra pelo acesso lateral.
        
                Enquanto todos descem...
        
                ele sobe.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, astucia4, 0, 0, 0, null, 0, null, null, ganhaAstucia1));
        cena09SoloA.addEscolha(new Escolhas("C09_VICTOR_A", "Esperar a troca de segurança e entrar pelo estacionamento.", formatTexto("""
                %s espera.
        
                23:58.
        
                Uma equipe termina o turno.
        
                00:03.
        
                Outra assume.
        
                Durante cinco minutos, ninguém parece saber exatamente quem deveria
                estar em qual posição.
        
                %s aproveita.
        
                Entra pela garagem.
        
                Pega a escada de emergência.
        
                Trinta e sete.
        
                Trinta e oito.
        
                Trinta e nove.
        
                Quando chega ao quadragésimo segundo andar, suas pernas estão
                reclamando.
        
                Mas chegou.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, null, 0, null, null, ganhaFurtividade1));
        repositorioCenas.addCena(cena09SoloA);
        Cena cena09VictorA = new Cena("C09_VICTOR_A", new ArrayList<>(List.of(victor)), formatTexto("""
                O cofre está aberto.
        
                %s para na entrada.
        
                No centro da sala existe uma pequena caixa de vidro.
        
                Dentro dela:
        
                Blackglass.
        
                Finalmente.
        
                %s dá um passo.
        
                — Eu estava começando a imaginar quando você voltaria.
        
                A voz surge atrás dele.
        
                Victor Ashcroft.
        
                Terno escuro.
        
                Nenhum sinal de surpresa.
        
                Como se estivesse esperando por aquilo.
        
                — Entrar na minha casa foi ousado — diz Victor.
        
                — Fugir de Greyhaven foi inconveniente.
        
                — Entrar aqui...
        
                Victor sorri.
        
                — Isso é quase pessoal.
        
                %s olha para Blackglass.
        
                — Você parece muito preocupado com uma pedra.
        
                — Porque você ainda acredita que é uma pedra.
        
                Victor se aproxima da caixa.
        
                — Blackglass é uma chave.
        
                — Para quê?
        
                Victor olha para %s.
        
                — Para coisas que podem comprar governos.
        
                — Derrubar empresas.
        
                — Fazer desaparecer pessoas.
        
                Silêncio.
        
                Victor coloca uma pequena maleta sobre uma mesa.
        
                Abre.
        
                Dinheiro.
        
                — Cinco mil libras agora.
        
                — Sua liberdade garantida.
        
                — E tudo isso termina.
        
                %s olha para o dinheiro.
        
                Depois para Blackglass.
        
                Victor espera.
        
                — Então?
        
                — O que vale mais para você?
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "O Cofre", 9);
        cena09VictorA.addEscolha(new Escolhas("C10_DINHEIRO", "Aceitar a oferta de Victor e abandonar Blackglass.", formatTexto("""
                %s olha para Blackglass.
        
                Depois para a maleta.
        
                Cinco mil libras.
        
                Uma saída.
        
                Um fim para aquilo.
        
                %s fecha a maleta.
        
                Victor sorri.
        
                — Uma decisão racional.
        
                — Não se acostume.
        
                Victor entrega a maleta.
        
                Blackglass permanece no cofre.
        
                %s sai da Ashcroft Tower com dinheiro suficiente para desaparecer.
        
                Mas alguns segredos permanecerão enterrados.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 5000, null, 0, null, null, semAlteracaoAtributos));
        cena09VictorA.addEscolha(new Escolhas("C10_PARCEIROS", "Pegar Blackglass e fugir com quem ajudou você a chegar até aqui.", formatTexto("""
                %s olha para Victor.
        
                — Acho que você esqueceu uma coisa.
        
                Victor estreita os olhos.
        
                — Eu não vim aqui pelo dinheiro.
        
                %s quebra o vidro de proteção.
        
                O alarme dispara.
        
                Blackglass finalmente está em sua mão.
        
                Victor dá um passo à frente.
        
                — Você não entende o que está fazendo.
        
                — Talvez.
        
                %s guarda a pedra.
        
                — Mas vou descobrir.
        
                Vozes surgem no corredor.
        
                Segurança.
        
                Não há mais tempo.
        
                %s corre.
        
                Blackglass vai com ele.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, null, 0, null, blackglass, semAlteracaoAtributos));
        cena09VictorA.addEscolha(new Escolhas("C10_BLACKGLASS", "Enganar Victor, pegar Blackglass e desaparecer sozinho.", formatTexto("""
                %s observa a maleta.
        
                — Cinco mil?
        
                — É uma oferta generosa.
        
                %s aproxima-se.
        
                Pega a maleta.
        
                Victor relaxa.
        
                Exatamente o que %s queria.
        
                Em um único movimento, ele empurra a caixa de vidro contra Victor.
        
                O homem perde o equilíbrio.
        
                %s pega Blackglass.
        
                O alarme dispara.
        
                — PARE!
        
                %s já está correndo.
        
                Dinheiro fica para trás.
        
                Victor fica para trás.
        
                Parceiros também.
        
                Dessa vez Blackglass pertence somente a %s.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, astucia4, 0, 0, 0, null, 0, null, fragmentoBlackglass, ganhaAstucia1));
        repositorioCenas.addCena(cena09VictorA);
        Cena cena09SegredoB = new Cena("C09_SEGREDO_B", new ArrayList<>(List.of(dante)), formatTexto("""
                2.418 arquivos.
        
                %s e Dante permanecem imóveis diante da tela.
        
                Nomes.
        
                Contas bancárias.
        
                Contratos.
        
                Subornos.
        
                Operações ilegais.
        
                Greyhaven.
        
                Empresas de Victor Ashcroft.
        
                Pessoas que deveriam estar em lados completamente diferentes da lei.
        
                Tudo conectado.
        
                Dante olha para %s.
        
                — Se metade disso for real...
        
                — Victor acabou.
        
                O computador emite um alerta.
        
                CONEXÃO EXTERNA DETECTADA.
        
                Dante empalidece.
        
                — Acho que ele sabe que abrimos.
        
                Outra mensagem aparece.
        
                RASTREAMENTO INICIADO.
        
                %s olha para a barra de download.
        
                12%.
        
                — Quanto tempo?
        
                — Para copiar tudo?
        
                Dante verifica.
        
                — Quatro minutos.
        
                Sirenes surgem do lado de fora.
        
                Eles provavelmente têm menos.
                """, protagonista.getNome(), protagonista.getNome() ),
                "2.418 Arquivos", 9);
        cena09SegredoB.addEscolha(new Escolhas("C10_SEGREDO", "Copiar todo o arquivo de Victor e tornar as provas públicas.", formatTexto("""
                %s conecta uma unidade externa.
        
                21%.
        
                Dante olha pela janela.
        
                Dois carros param na rua.
        
                — Temos companhia.
        
                43%.
        
                Passos sobem as escadas.
        
                67%.
        
                Dante pega uma barra de metal.
        
                — Você realmente quer fazer isso?
        
                %s olha para os arquivos.
        
                Greyhaven.
        
                Ward.
        
                Empresas.
        
                Contas.
        
                Nomes.
        
                89%.
        
                — Sim.
        
                100%.
        
                DOWNLOAD CONCLUÍDO.
        
                %s remove a unidade.
        
                Dante abre um sorriso.
        
                — Agora corremos?
        
                — Agora corremos.
        
                Antes de sair, %s programa o envio.
        
                Vários destinatários.
        
                Imprensa.
        
                Autoridades.
        
                Servidores públicos.
        
                Um clique.
        
                ENVIADO.
        
                Blackglass não é mais apenas uma pedra valiosa.
        
                É a prova de tudo.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Dante", 2, null, provasAshcroft, ganhaAstucia1));
        cena09SegredoB.addEscolha(new Escolhas("C10_DINHEIRO", "Usar os arquivos para obrigar Victor a pagar pelo silêncio.", formatTexto("""
                %s cancela o download.
        
                Dante olha para ele.
        
                — O que está fazendo?
        
                — Transformando informação em dinheiro.
        
                %s pega o telefone.
        
                Uma ligação.
        
                Um número que estava entre os arquivos.
        
                Victor atende.
        
                — Você abriu Blackglass.
        
                Não é uma pergunta.
        
                %s sorri.
        
                — Agora podemos conversar.
        
                Alguns segredos valem mais guardados do que publicados.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 5000, "Dante", -1, null, null, ganhaAstucia1));
        cena09SegredoB.addEscolha(new Escolhas("C10_BLACKGLASS", "Apagar o acesso aos arquivos e ficar apenas com Blackglass.", formatTexto("""
                %s olha para os milhares de arquivos.
        
                Poder demais.
        
                Gente demais.
        
                Problemas demais.
        
                Ele remove o fragmento.
        
                A tela apaga.
        
                Dante olha para %s.
        
                — O que fez?
        
                — Escolhi a pedra.
        
                %s guarda Blackglass.
        
                — Depois de tudo isso?
        
                — Principalmente depois de tudo isso.
        
                Os homens de Victor estão chegando.
        
                %s pega a mochila.
        
                É hora de desaparecer.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Dante", -2, null, blackglass, semAlteracaoAtributos));
        repositorioCenas.addCena(cena09SegredoB);
        Cena cena09DecifrarB = new Cena("C09_DECIFRAR_B", new ArrayList<>(List.of(dante, victor)), formatTexto("""
                A tela apagou.
        
                Mas %s viu o suficiente.
        
                GREYHAVEN.
        
                TRANSFERÊNCIAS.
        
                PROJECT BLACKGLASS.
        
                Dante fotografa os poucos dados que conseguiram recuperar.
        
                — Isso vale alguma coisa?
        
                — Para Victor?
        
                %s olha para Blackglass.
        
                — Provavelmente vale nossa vida.
        
                O telefone de Dante toca.
        
                Número desconhecido.
        
                Ele atende no viva-voz.
        
                — Vocês têm algo que me pertence — diz Victor.
        
                Dante olha para %s.
        
                — Tecnicamente roubamos primeiro.
        
                Victor ignora.
        
                — Eu ofereço uma oportunidade.
        
                — Entreguem Blackglass.
        
                — Apaguem o que viram.
        
                — E saiam de Londres vivos.
        
                A ligação termina.
        
                Dante encara %s.
        
                — Ele realmente precisa trabalhar nas habilidades sociais.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "Parte da Verdade", 9);
        cena09DecifrarB.addEscolha(new Escolhas("C10_DINHEIRO", "Usar as informações incompletas para negociar com Victor.", formatTexto("""
                %s liga de volta.
        
                — Cinco mil.
        
                Dante arregala os olhos.
        
                Victor fica em silêncio.
        
                — Você não está em posição de negociar.
        
                %s olha para os arquivos fotografados.
        
                — Então imagino que não se importe se eu enviar isso.
        
                Outro silêncio.
        
                Victor responde:
        
                — Onde?
        
                Dante começa a sorrir.
        
                %s acabou de descobrir o preço do silêncio.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 5000, null, 0, null, null, ganhaCharme1));
        cena09DecifrarB.addEscolha(new Escolhas("C10_PARCEIROS", "Levar Blackglass e desaparecer com Dante.", formatTexto("""
                %s fecha o notebook.
        
                — Vamos embora.
        
                Dante pega a mochila.
        
                — Sem chantagem?
        
                — Sem Victor.
        
                — Sem Londres.
        
                — Sem plano?
        
                %s olha para Dante.
        
                — Alguma tradição precisa continuar.
        
                Dante sorri.
        
                Os dois saem antes que os homens de Victor cheguem.
        
                Blackglass vai com eles.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Dante", 2, null, blackglass, ganhaFurtividade1));
        cena09DecifrarB.addEscolha(new Escolhas("C10_BLACKGLASS", "Pegar Blackglass e desaparecer sozinho.", formatTexto("""
                %s pega Blackglass.
        
                Dante percebe.
        
                — Não.
        
                — Chegamos até aqui.
        
                — Nós chegamos.
        
                %s guarda a pedra.
        
                Dante entende imediatamente.
        
                — Então é isso?
        
                %s não responde.
        
                Sai pela porta dos fundos.
        
                Sirenes aparecem ao longe.
        
                Dante fica para trás.
        
                Blackglass não.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Dante", -3, null, blackglass, ganhaFurtividade1));
        repositorioCenas.addCena(cena09DecifrarB);
        Cena cena09DanteB = new Cena("C09_DANTE_B", new ArrayList<>(List.of(dante, victor)), formatTexto("""
                Dante dirige para o leste de Londres.
        
                Blackglass está dentro da mochila entre os bancos.
        
                %s observa a estrada.
        
                — Esse lugar seguro fica onde?
        
                — Quase lá.
        
                O carro entra em um galpão abandonado.
        
                A porta fecha atrás deles.
        
                Então as luzes se acendem.
        
                Homens armados cercam o carro.
        
                Dante para.
        
                — Isso não fazia parte do plano.
        
                Victor Ashcroft surge entre eles.
        
                — Não.
        
                — Fazia parte do meu.
        
                %s olha para Dante.
        
                Dante parece tão surpreso quanto ele.
        
                Victor aponta para a mochila.
        
                — Blackglass.
        
                — Agora.
        
                Dante segura a mochila.
        
                Victor coloca outra proposta sobre a mesa:
        
                — Cinco mil libras.
        
                — Entreguem a pedra.
        
                — E vão embora.
        
                Dante olha para %s.
        
                A escolha pertence a ele.
                """, protagonista.getNome(), protagonista.getNome() ),
                "O Preço da Lealdade", 9);
        cena09DanteB.addEscolha(new Escolhas("C10_PARCEIROS", "Ficar ao lado de Dante e escapar com Blackglass.", formatTexto("""
                %s olha para Dante.
        
                — No três.
        
                Dante entende imediatamente.
        
                — Um.
        
                Victor franze a testa.
        
                — Dois.
        
                Dante joga a mochila para %s.
        
                — Três!
        
                Os dois correm.
        
                Um disparo atinge a parede.
        
                Outro quebra uma janela.
        
                %s atravessa primeiro.
        
                Dante vem logo atrás.
        
                Minutos depois, estão correndo pelas ruas de Londres.
        
                Blackglass ainda está com eles.
        
                E, contra todas as probabilidades, também estão juntos.
                """, protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Dante", 2, null, blackglass, ganhaFurtividade1));
        cena09DanteB.addEscolha(new Escolhas("C10_BLACKGLASS", "Usar a confusão para tomar Blackglass de Dante.", formatTexto("""
                Dante olha para Victor.
        
                %s olha para a mochila.
        
                Quando um dos homens se move, Dante reage.
        
                É o momento.
        
                %s pega a mochila.
        
                — O que está fazendo?!
        
                %s corre pela saída lateral.
        
                — %s!
        
                Dante fica para trás.
        
                Victor também.
        
                A mochila pesa pouco.
        
                Mas vale milhões.
        
                Talvez muito mais.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 0, "Dante", -4, null, blackglass, ganhaFurtividade1));
        cena09DanteB.addEscolha(new Escolhas("C10_DINHEIRO", "Aceitar a oferta de Victor e devolver Blackglass.", formatTexto("""
                %s levanta as mãos.
        
                — Cinco mil.
        
                Dante olha para ele.
        
                — Você está falando sério?
        
                %s estende a mão para a mochila.
        
                Dante segura.
        
                — Depois de tudo?
        
                — Principalmente depois de tudo.
        
                Dante solta lentamente.
        
                %s entrega Blackglass a Victor.
        
                Victor entrega a maleta.
        
                — Uma decisão inteligente.
        
                Dante não diz nada.
        
                O olhar dele já diz o suficiente.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 5000, "Dante", -3, null, null, ganhaCharme1));
        repositorioCenas.addCena(cena09DanteB);
        Cena cena09SoloB = new Cena("C09_SOLO_B", new ArrayList<>(List.of(victor)), formatTexto("""
                %s troca de carro duas vezes.
        
                Depois troca de telefone.
        
                Depois abandona o telefone.
        
                Blackglass permanece no bolso interno da jaqueta.
        
                Às 03:17, %s entra em um apartamento vazio no norte de Londres.
        
                Às 03:21, alguém bate na porta.
        
                Três vezes.
        
                %s congela.
        
                — Não precisa tornar isso difícil — diz uma voz do outro lado.
        
                Victor.
        
                %s olha para a janela.
        
                Quarto andar.
        
                Escada de incêndio.
        
                Victor continua:
        
                — Você tem algo meu.
        
                — Posso pagar por isso.
        
                — Ou posso simplesmente esperar você cometer um erro.
        
                %s toca em Blackglass.
        
                É hora de decidir quanto aquela pedra realmente vale.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "Sem Testemunhas", 9);
        cena09SoloB.addEscolha(new Escolhas("C10_DINHEIRO", "Vender Blackglass de volta para Victor.", formatTexto("""
                %s abre a porta.
        
                Victor está sozinho.
        
                Pelo menos aparentemente.
        
                — Cinco mil — diz %s.
        
                Victor sorri.
        
                — Três.
        
                — Cinco.
        
                Alguns segundos.
        
                — Certo.
        
                Uma transferência é feita.
        
                %s entrega Blackglass.
        
                Depois de tudo...
        
                a pedra volta para onde começou.
        
                Mas %s não sai de mãos vazias.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, null, 0, 0, 5000, null, 0, null, null, ganhaCharme1));
        cena09SoloB.addEscolha(new Escolhas("C10_BLACKGLASS", "Enganar Victor e fugir com Blackglass.", formatTexto("""
                %s abre a janela do quarto.
        
                — Certo, Victor.
        
                — Vou abrir.
        
                Enquanto fala, %s liga o chuveiro.
        
                Abre outra torneira.
        
                Depois aciona o alarme de incêndio.
        
                Sirenes explodem pelo prédio.
        
                Portas começam a abrir.
        
                Moradores entram no corredor.
        
                No meio do caos, %s sai pela escada de serviço.
        
                Quando Victor entra no apartamento...
        
                encontra apenas um quarto vazio.
        
                Blackglass continua com %s.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, astucia4, 0, 0, 0, null, 0, null, blackglass, ganhaAstucia1));
        cena09SoloB.addEscolha(new Escolhas("C10_BLACKGLASS", "Descer pela escada de incêndio e desaparecer.", formatTexto("""
                %s abre a janela.
        
                Quatro andares.
        
                Já fez coisas piores.
        
                Provavelmente.
        
                Ele passa para o lado de fora.
        
                Victor bate novamente na porta.
        
                %s começa a descer.
        
                Um andar.
        
                Dois.
        
                A porta do apartamento é arrombada.
        
                Três.
        
                %s pula os últimos metros.
        
                O impacto dói.
        
                Mas ele continua correndo.
        
                Quando os homens de Victor chegam à rua...
        
                %s já desapareceu.
        
                Blackglass também.
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                null, saude50, 0, 1, 0, null, 0, null, blackglass, ganhaFurtividade1));
        repositorioCenas.addCena(cena09SoloB);
        Cena cena10Dinheiro = new Cena("C10_DINHEIRO", new ArrayList<>(), formatTexto("""
                Três semanas depois.
        
                Londres continua exatamente como antes.
        
                Chuva.
        
                Trânsito.
        
                Pessoas correndo para lugares onde não querem estar.
        
                %s observa tudo pela janela de um pequeno apartamento alugado
                com um nome falso.
        
                Sobre a mesa existe uma passagem de avião.
        
                Só de ida.
        
                Ao lado dela, uma conta bancária que não existia algumas semanas atrás.
        
                Dinheiro suficiente para desaparecer.
        
                Victor Ashcroft cumpriu sua parte do acordo.
        
                Pelo menos até agora.
        
                Blackglass voltou para as mãos dele.
        
                Os arquivos continuam escondidos.
        
                Os segredos continuam enterrados.
        
                E Greyhaven já procura outro culpado para ocupar a cela vazia deixada
                por %s.
        
                O telefone vibra.
        
                Uma notícia aparece na tela:
        
                "ASHCROFT INDUSTRIES ANUNCIA NOVA EXPANSÃO INTERNACIONAL."
        
                A fotografia de Victor ocupa metade da matéria.
        
                Sorrindo.
        
                Como se nada tivesse acontecido.
        
                %s observa por alguns segundos.
        
                Depois bloqueia o telefone.
        
                Talvez pudesse ter acabado com Victor.
        
                Talvez pudesse ter ficado com Blackglass.
        
                Talvez pudesse ter feito alguma coisa diferente.
        
                Mas escolhas têm preço.
        
                E dessa vez...
        
                %s recebeu o pagamento.
        
                Ele pega a passagem.
        
                Fecha a mala.
        
                E deixa Londres antes do amanhecer.
        
                Blackglass continua sendo problema de outra pessoa.
        
                Pelo menos por enquanto.
        
        
                ============================================================
                                 FINAL — O PREÇO CERTO
                ============================================================
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "O Preço Certo", 10);
        cena10Dinheiro.setFinal(true);
        repositorioCenas.addCena(cena10Dinheiro);
        Cena cena10Parceiros = new Cena("C10_PARCEIROS", new ArrayList<>(List.of(dante)), formatTexto("""
                Dois meses depois.
        
                Porto de Dover.
        
                04:18.
        
                %s está encostado em um carro alugado quando Dante aparece carregando
                duas malas.
        
                — Você trouxe coisa demais.
        
                Dante coloca as malas no chão.
        
                — Nós não sabemos quando vamos voltar.
        
                — Essa é a ideia.
        
                Dante abre o porta-malas.
        
                Uma pequena caixa metálica está escondida sob uma manta.
        
                %s olha para ela.
        
                Blackglass.
        
                Depois de Ashcroft.
        
                Depois de Greyhaven.
        
                Depois de tudo.
        
                A pedra finalmente está fora do alcance de Victor.
        
                Pelo menos temporariamente.
        
                Dante fecha o porta-malas.
        
                — Recebi uma oferta.
        
                %s olha para ele.
        
                — Não.
        
                — Você nem ouviu.
        
                — A última oferta terminou comigo na prisão.
        
                — Tecnicamente, aquela nem foi uma oferta.
        
                %s encara Dante.
        
                — Isso melhora muito a situação.
        
                Dante sorri.
        
                Os dois entram no carro.
        
                No rádio, uma notícia menciona Victor Ashcroft.
        
                Investigações.
        
                Empresas fechando contratos às pressas.
        
                Rumores sobre uma sequência de movimentações financeiras.
        
                Nada conclusivo.
        
                Ainda.
        
                Dante liga o motor.
        
                — Para onde?
        
                %s observa a estrada.
        
                Pela primeira vez em muito tempo, não existe uma prisão, um cofre ou
                uma equipe de segurança à frente.
        
                Apenas estrada.
        
                — Longe.
        
                Dante sorri.
        
                — Finalmente um plano bom.
        
                O carro começa a se mover.
        
                Blackglass vai com eles.
        
                Talvez algum dia descubram tudo que ela pode abrir.
        
                Talvez algum dia consigam vendê-la.
        
                Talvez sejam perseguidos pelo resto da vida.
        
                Mas dessa vez...
        
                nenhum deles ficou para trás.
        
        
                ============================================================
                                   FINAL — PARCEIROS
                ============================================================
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "Parceiros", 10);
        cena10Parceiros.setFinal(true);
        repositorioCenas.addCena(cena10Parceiros);
        Cena cena10Blackglass = new Cena("C10_BLACKGLASS", new ArrayList<>(), formatTexto("""
                Seis meses depois.
        
                Um quarto de hotel.
        
                Algum lugar da Europa.
        
                %s nunca permanece na mesma cidade por muito tempo.
        
                Paris.
        
                Bruxelas.
        
                Praga.
        
                Berlim.
        
                Nomes diferentes.
        
                Documentos diferentes.
        
                Quartos diferentes.
        
                Mas uma coisa nunca muda.
        
                A pequena caixa preta dentro da mala.
        
                %s abre.
        
                Blackglass está ali.
        
                Silenciosa.
        
                Pequena.
        
                Quase decepcionante para algo que custou tanto.
        
                Dante não voltou a ligar.
        
                Julian também não.
        
                Miller tentou uma vez.
        
                %s não respondeu.
        
                Relações deixam rastros.
        
                Rastros levam até pessoas.
        
                E pessoas são exatamente o que Victor Ashcroft procura.
        
                O telefone descartável vibra.
        
                Uma mensagem.
        
                Número desconhecido.
        
                "AINDA QUERO O QUE É MEU."
        
                %s lê.
        
                Depois remove o chip.
        
                Quebra ao meio.
        
                Joga pela janela.
        
                Victor ainda está procurando.
        
                Talvez continue procurando pelo resto da vida.
        
                %s olha novamente para Blackglass.
        
                Existem compradores.
        
                Colecionadores.
        
                Governos.
        
                Criminosos.
        
                Pessoas dispostas a pagar quantias que ele nunca imaginou possuir.
        
                Mas ainda não.
        
                Quanto mais aprende sobre a pedra, menos vontade tem de entregá-la.
        
                Talvez seja ganância.
        
                Talvez curiosidade.
        
                Talvez as duas coisas.
        
                %s fecha a caixa.
        
                Coloca dentro da mala.
        
                Há um voo naquela noite.
        
                Outro país.
        
                Outro nome.
        
                Outra oportunidade de desaparecer.
        
                Blackglass finalmente pertence a ele.
        
                Mas possuir algo tão valioso tem um preço.
        
                Agora...
        
                %s precisa passar o resto da vida garantindo que ninguém consiga
                roubá-la dele.
        
        
                ============================================================
                                FINAL — TUDO PARA SI
                ============================================================
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "Tudo Para Si", 10);
        cena10Blackglass.setFinal(true);
        repositorioCenas.addCena(cena10Blackglass);
        Cena cena10Segredo = new Cena("C10_SEGREDO", new ArrayList<>(List.of(dante, julian, miller, victor)), formatTexto("""
                07:32.
        
                Londres acorda.
        
                E Victor Ashcroft perde tudo.
        
                Os primeiros arquivos aparecem em um jornal.
        
                Depois em outro.
        
                Depois em todos.
        
                Transferências bancárias.
        
                Empresas de fachada.
        
                Contratos falsificados.
        
                Pagamentos clandestinos.
        
                Registros de Greyhaven.
        
                Nomes.
        
                Datas.
        
                Contas.
        
                Assinaturas.
        
                Tudo.
        
                %s observa as notícias de um quarto alugado enquanto Dante permanece
                junto à janela.
        
                — Você acha que isso vai funcionar?
        
                %s continua olhando para a tela.
        
                — Já funcionou.
        
                O telefone começa a vibrar.
        
                Uma mensagem de Julian.
        
                "Meu pai acabou de descobrir que metade do conselho desapareceu."
        
                Outra mensagem.
        
                Miller.
        
                "SE ALGUÉM PERGUNTAR, EU NUNCA CONHECI VOCÊ."
        
                Alguns segundos depois:
        
                "BOM TRABALHO."
        
                Dante ri.
        
                — Acho que isso é um elogio.
        
                A televisão muda para uma transmissão ao vivo.
        
                Ashcroft Tower.
        
                Viaturas cercam o edifício.
        
                Jornalistas ocupam a entrada.
        
                Funcionários saem carregando caixas.
        
                A imagem muda.
        
                Victor Ashcroft aparece cercado por advogados.
        
                Pela primeira vez desde que %s o conheceu...
        
                Victor não parece estar no controle.
        
                A repórter continua:
        
                "As autoridades confirmaram a abertura de múltiplas investigações após
                o vazamento de milhares de documentos relacionados ao grupo Ashcroft."
        
                Dante olha para a pequena pedra sobre a mesa.
        
                Blackglass.
        
                O fragmento ainda está encaixado em sua base.
        
                — Engraçado.
        
                %s olha para ele.
        
                — O quê?
        
                — A gente começou tudo isso tentando ficar rico.
        
                %s observa Blackglass.
        
                — Ainda podemos vender.
        
                Dante começa a rir.
        
                — Claro.
        
                %s remove o fragmento.
        
                A luz interna da pedra desaparece.
        
                Pela primeira vez, Blackglass parece apenas vidro negro.
        
                Um objeto pequeno.
        
                Sem importância.
        
                Mas os arquivos já estão fora.
        
                Não podem ser recolocados dentro da pedra.
        
                Não podem ser comprados de volta.
        
                Não podem ser apagados de todos os lugares ao mesmo tempo.
        
                Victor passou anos protegendo Blackglass porque acreditava que controlar
                a chave significava controlar seus segredos.
        
                Ele estava errado.
        
                %s pega a pedra.
        
                Dante observa.
        
                — O que vai fazer com ela?
        
                %s olha pela janela.
        
                Sirenes continuam ecoando pela cidade.
        
                — Nada.
        
                Ele coloca Blackglass dentro de uma pequena caixa.
        
                Fecha.
        
                Pela primeira vez desde a noite na Mansão Ashcroft...
        
                não precisa roubá-la.
        
                Não precisa vendê-la.
        
                Não precisa fugir por causa dela.
        
                Blackglass deixou de ser o objetivo.
        
                A verdade que existia dentro dela já está livre.
        
                Dante pega o casaco.
        
                — Então acabou?
        
                %s pensa por alguns segundos.
        
                Ashcroft.
        
                Greyhaven.
        
                Ward.
        
                Julian.
        
                Miller.
        
                Dante.
        
                A mansão.
        
                A prisão.
        
                Tudo começou com uma pequena pedra negra.
        
                %s sorri.
        
                — Agora acabou.
        
                Os dois deixam o quarto.
        
                Blackglass fica para trás.
        
                Sobre a mesa.
        
                Dentro da caixa.
        
                Pela primeira vez...
        
                completamente sem valor.
        
        
                ============================================================
                             FINAL SECRETO — QUEBRANDO O VIDRO
                ============================================================
                """, protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome(), protagonista.getNome() ),
                "Quebrando o Vidro", 10);
        cena10Segredo.setFinal(true);
        repositorioCenas.addCena(cena10Segredo);
    }
}
