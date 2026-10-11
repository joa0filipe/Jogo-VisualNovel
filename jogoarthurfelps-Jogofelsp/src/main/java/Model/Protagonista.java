package Model;

public class Protagonista {
    private String nome;
    private int danteLealdade;
    private int julianLealdade;
    private int millerLealdade;
    private int charme;
    private int astucia;
    private int furtividade;
    private int saude;
    private int dinheiro;
    private Item[] inventario;

    public Protagonista(String nome, int astucia, int charme, int furtividade) {
        this.nome = nome;
        this.astucia = astucia;
        this.charme = charme;
        this.furtividade = furtividade;
        this.dinheiro = 0;
        this.danteLealdade = 0;
        this.julianLealdade = 0;
        this.millerLealdade = 0;
        this.saude = 100;
        this.inventario = new Item[5];
    }
    public Protagonista(String nome) {
        this.nome = nome;
        this.astucia = 2;
        this.charme = 2;
        this.furtividade = 2;
        this.saude = 100;
        this.dinheiro = 150;
        this.inventario = new Item[5];
    }

    public void alterarLealdade(int valor, String nomePersonagem) {
        nomePersonagem = nomePersonagem.toLowerCase().trim();
        switch (nomePersonagem) {
            case "dante":
                danteLealdade += valor;
                break;
            case "julian":
                julianLealdade += valor;
                break;
            case "miller":
                millerLealdade += valor;
                break;
            default:
                System.out.println("Nome de personagem inválido.");
                break;
        }
    }


    public void alterarAtributo(String tipo, int valor) {
        if (tipo == null) return;
        tipo = tipo.toLowerCase().trim();
        switch (tipo) {
            case "astucia":
                this.astucia += valor;
                if(this.astucia < 0){
                    this.astucia = 0;
                }
                break;
            case "charme":
                this.charme += valor;
                if(this.charme < 0){
                    this.charme = 0;
                }
                break;
            case "furtividade":
                this.furtividade += valor;
                if(this.furtividade < 0){
                    this.furtividade = 0;
                }
                break;
            default:
                break;
        }
    }

    public int getAtributo(String nomeAtributo) {
        if (nomeAtributo == null) return 0;
        switch (nomeAtributo.toLowerCase().trim()) {
            case "astucia":
                return astucia;
            case "charme":
                return charme;
            case "furtividade":
                return furtividade;
            case "saude":
                return saude;
            default:
                return 0;
        }
    }

    public void modificarSaude(int valor) {
        this.saude += valor;
        if (this.saude > 100) {
            this.saude = 100;
        } else if (this.saude < 0) {
            this.saude = 0;
            // personagem morreu, implementar lógica de morte
        }
    }

    public void modificarDinheiro(int valor) {
        this.dinheiro += valor;
        if (this.dinheiro < 0) {
            this.dinheiro = 0;
        }
    }

    public void modificarAfinidade(String nomePersonagem, int valor) {
        alterarLealdade(valor, nomePersonagem);
    }

    public int getDinheiro() {
        return dinheiro;
    }

    public int adicionarDinheiro(int valor) {
        if (this.dinheiro + valor >= 0) {
            this.dinheiro += valor;
        } else {
            System.out.println("Valor inválido para dinheiro.");
        }
        return this.dinheiro;
        }

    public int retirarDinheiro(int valor) {
        this.dinheiro -= valor;
        if (this.dinheiro < 0) {
            this.dinheiro = 0;
        }
        return this.dinheiro;
    }

    public void adicionarItem(Item item) {
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] == null) {
                inventario[i] = item;
                return;
            }
        }
    }

    public void retirarItem(Item nomeItem) {
        if (nomeItem == null || inventario == null) {
            return;
        }

        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] != null && inventario[i].equals(nomeItem)) {
                inventario[i] = null;
                return;
            }
        }
    }

    public void usarItem(String nomeItem) {
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] != null && inventario[i].getNome().equalsIgnoreCase(nomeItem)) {
                inventario[i].usar(this);
                inventario[i] = null;
                return;
            }
        }
    }

    public boolean possuiItem(String nomeItem) {
        for (Item item : inventario) {
            if (item != null && item.getNome().equalsIgnoreCase(nomeItem)) {
                return true;
            }
        }
        return false;
    }



    public String getNome() {
        return nome;
    }

    public int getDanteLealdade() {
        return danteLealdade;
    }

    public int getJulianLealdade() {
        return julianLealdade;
    }

    public int getMillerLealdade() {
        return millerLealdade;
    }

    public int getCharme() {
        return charme;
    }

    public int getAstucia() {
        return astucia;
    }

    public int getFurtividade() {
        return furtividade;
    }

    public int getSaude() {
        return saude;
    }

    public Item[] getInventario() {
        return inventario;
    }


    @Override
    public String toString() {
        return "Protagonista{" +
                "nome='" + nome + '\'' +
                ", danteLealdade=" + danteLealdade +
                ", julianLealdade=" + julianLealdade +
                ", millerLealdade=" + millerLealdade +
                ", charme=" + charme +
                ", astucia=" + astucia +
                ", furtividade=" + furtividade +
                ", saude=" + saude +
                ", dinheiro=" + dinheiro +
                '}';
    }
}
