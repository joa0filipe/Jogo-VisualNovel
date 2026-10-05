import Controller.ServicosJogo;
import Loader.LoaderDeItens;
import Loader.LoaderDePersonagens;
import Repository.JogoRepository;
import Repository.RepositorioDeCenas;
import Repository.RepositorioDeItens;
import Repository.RepositorioDePersonagens;
import View.ViewTerminal;
import com.google.gson.Gson;
public class
Main {
  public static void main(String[] args) {
    JogoRepository stateRepository = new JogoRepository();
    RepositorioDeCenas repositorioCenas = new RepositorioDeCenas();
    RepositorioDePersonagens repositorioPersonagens = new RepositorioDePersonagens();
    RepositorioDeItens repositorioItens = new RepositorioDeItens();

    LoaderDePersonagens loaderPersonagens = new LoaderDePersonagens(repositorioPersonagens);
    loaderPersonagens.carregarPersonagens();

    LoaderDeItens loaderItens = new LoaderDeItens(repositorioItens);
    loaderItens.carregarItens();

    ServicosJogo servicosJogo = new ServicosJogo(
            repositorioCenas,
            repositorioItens,
            repositorioPersonagens,
            stateRepository
    );

    ViewTerminal view = new ViewTerminal(servicosJogo);
    view.inicio();
  }}
