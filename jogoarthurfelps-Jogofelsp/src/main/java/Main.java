import Controller.ServicosJogo;
import Controller.ServicosConquistas;
import Controller.ServicosSave;
import Loader.LoaderDeItens;
import Loader.LoaderDePersonagens;
import Repository.JogoRepository;
import Repository.RepositorioDeCenas;
import Repository.RepositorioDeItens;
import Repository.RepositorioDePersonagens;
import View.ViewTerminal;
import DAO.implementacao.*;
public class Main {
  public static void main(String[] args) throws Exception {

    JogoRepository stateRepository = new JogoRepository(); //tirar
    RepositorioDeCenas repositorioCenas = new RepositorioDeCenas();
    RepositorioDePersonagens repositorioPersonagens = new RepositorioDePersonagens();
    RepositorioDeItens repositorioItens = new RepositorioDeItens();
    LoaderDePersonagens loaderPersonagens = new LoaderDePersonagens(repositorioPersonagens);

    //dao
    EstadoJsonDao EstadoDao = new EstadoJsonDao();
    PreferenciaJsonDao PreferenciaDao = new PreferenciaJsonDao();
    ConquistasJsonDao conquistasDao = new ConquistasJsonDao();
    ServicosConquistas servicosConquistas = new ServicosConquistas(conquistasDao);

    loaderPersonagens.carregarPersonagens();

    LoaderDeItens loaderItens = new LoaderDeItens(repositorioItens);
    loaderItens.carregarItens();


    ServicosSave servicosSave = new ServicosSave(EstadoDao,PreferenciaDao);

    ServicosJogo servicosJogo = new ServicosJogo(
            repositorioCenas,
            repositorioItens,
            repositorioPersonagens,
            stateRepository,
            EstadoDao,
            servicosSave,
            servicosConquistas
    );

    ViewTerminal view = new ViewTerminal(servicosJogo);
    view.inicio();
  }
}
