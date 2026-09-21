import Controller.ServicosJogo;
import Loader.LoaderDeItens;
import Loader.LoaderDePersonagens;
import Repository.RepositorioDeCenas;
import Repository.RepositorioDeItens;
import Repository.RepositorioDePersonagens;
import View.ViewTerminal;

public class
Main {
  public static void main(String[] args) {
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
            repositorioPersonagens
    );

    ViewTerminal view = new ViewTerminal(servicosJogo);
    view.inicio();
  }}
