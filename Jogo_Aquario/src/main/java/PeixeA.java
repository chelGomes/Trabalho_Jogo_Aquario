import java.util.List;
import java.util.Random;

class PeixeA extends Peixe {  //herbívoro
    private int ciclosMovimento;

    public PeixeA(int x, int y) {
        super(x, y);
        this.ciclosMovimento = 0;
    }

    @Override
    void atualizar(Aquario aquario) {
        List<int[]> celulasLivres = aquario.getCelulasAdjacentesLivres(x, y);

        if (!celulasLivres.isEmpty()) {
            // Se pode se mover
            ciclosMovimento++;
            int[] novaPos = celulasLivres.get(new Random().nextInt(celulasLivres.size()));
            x = novaPos[0];
            y = novaPos[1];

            // Verifica se deve reproduzir
            if (ciclosMovimento >= aquario.getRA() && !celulasLivres.isEmpty()) {
                int[] filhoPos = celulasLivres.get(new Random().nextInt(celulasLivres.size()));
                aquario.adicionarPeixeA(new PeixeA(filhoPos[0], filhoPos[1]));
                ciclosMovimento = 0;
            }
        } else {
            // Se não pode se mover, incrementa ciclos sem movimento
            ciclosVida++;
            if (ciclosVida >= aquario.getMA()) {
                aquario.marcarPeixeAMorto(this);
            }
        }
    }

    @Override
    char getSimbolo() {
        return 'A';
    }
}