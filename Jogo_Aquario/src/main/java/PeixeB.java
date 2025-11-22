import java.util.List;
import java.util.Random;

class PeixeB extends Peixe { //predador
    private int peisComidos;
    private int ciclosSemComer;

    public PeixeB(int x, int y) {
        super(x, y);
        this.peisComidos = 0;
        this.ciclosSemComer = 0;
    }

    @Override
    void atualizar(Aquario aquario) {
        PeixeA peixeProximo = aquario.getPeixeAProximo(x, y);

        if (peixeProximo != null) {
            // Come peixe tipo A próximo
            x = peixeProximo.getX();
            y = peixeProximo.getY();
            peisComidos++;
            ciclosSemComer = 0;
            aquario.removerPeixeA(peixeProximo);

            // Verifica se deve reproduzir
            if (peisComidos >= aquario.getRB() &&
                    !aquario.temPeixeBVizinho(x, y)) {
                List<int[]> celulasLivres = aquario.getCelulasAdjacentesLivres(x, y);
                if (!celulasLivres.isEmpty()) {
                    int[] filhoPos = celulasLivres.get(new Random().nextInt(celulasLivres.size()));
                    aquario.adicionarPeixeB(new PeixeB(filhoPos[0], filhoPos[1]));
                    peisComidos = 0;
                }
            }
        } else {
            // Se não encontra peixe A, tenta se mover para célula livre
            List<int[]> celulasLivres = aquario.getCelulasAdjacentesLivres(x, y);
            if (!celulasLivres.isEmpty()) {
                int[] novaPos = celulasLivres.get(new Random().nextInt(celulasLivres.size()));
                x = novaPos[0];
                y = novaPos[1];
            }

            ciclosSemComer++;
            if (ciclosSemComer >= aquario.getMB()) {
                aquario.marcarPeixeBMorto(this);
            }
        }
    }

    @Override
    char getSimbolo() {
        return 'B';
    }
}