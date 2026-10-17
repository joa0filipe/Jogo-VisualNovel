import Controller.ServicosJogo;
import Controller.ServicosPreferencias;
import Controller.ServicosSave;
import Loader.LoaderDeItens;
import Loader.LoaderDePersonagens;
import Repository.RepositorioDeCenas;
import Repository.RepositorioDeItens;
import Repository.RepositorioDePersonagens;
import View.ViewTerminal;
import DAO.implementacao.*;
public class
Main {
  public static void main(String[] args) throws Exception {


    RepositorioDeCenas repositorioCenas = new RepositorioDeCenas();
    RepositorioDePersonagens repositorioPersonagens = new RepositorioDePersonagens();
    RepositorioDeItens repositorioItens = new RepositorioDeItens();
    LoaderDePersonagens loaderPersonagens = new LoaderDePersonagens(repositorioPersonagens);

    //dao
    EstadoJsonDao EstadoDao = new EstadoJsonDao();
    PreferenciaJsonDao PreferenciaDao = new PreferenciaJsonDao();

    loaderPersonagens.carregarPersonagens();

    LoaderDeItens loaderItens = new LoaderDeItens(repositorioItens);
    loaderItens.carregarItens();

    ServicosPreferencias servicosPreferencias = new ServicosPreferencias(PreferenciaDao);
    servicosPreferencias.inicializarPreferencias();
    ServicosSave servicosSave = new ServicosSave(EstadoDao);

    ServicosJogo servicosJogo = new ServicosJogo(
            repositorioCenas,
            repositorioItens,
            repositorioPersonagens,
            EstadoDao,
            servicosSave,
            servicosPreferencias
    );

    ViewTerminal view = new ViewTerminal(servicosJogo);
    view.inicio();
  }
}
