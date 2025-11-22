import java.util.*;

class Aquario {
    private int m, n;
    private int ra, ma, rb, mb;
    private List<PeixeA> peixesA;
    private List<PeixeB> peixesB;
    private Set<PeixeA> peixesAMortos;
    private Set<PeixeB> peisesBMortos;
    private int iteracoes;

    public Aquario(int m, int n, int x, int y, int ra, int ma, int rb, int mb) {
        if (!Validador.validarEntradas(m, n, x, y, ra, ma, rb, mb)) {
            throw new IllegalArgumentException("Entradas inválidas");
        }

        this.m = m;
        this.n = n;
        this.ra = ra;
        this.ma = ma;
        this.rb = rb;
        this.mb = mb;
        this.peixesA = new ArrayList<>();
        this.peixesB = new ArrayList<>();
        this.peixesAMortos = new HashSet<>();
        this.peisesBMortos = new HashSet<>();
        this.iteracoes = 0;

        inicializarPeixes(x, y);
    }

    private void inicializarPeixes(int x, int y) {
        Random rand = new Random();
        Set<String> posicoes = new HashSet<>();

        // Adiciona peixes tipo A
        for (int i = 0; i < x; i++) {
            int px, py;
            do {
                px = rand.nextInt(m);
                py = rand.nextInt(n);
            } while (posicoes.contains(px + "," + py));
            posicoes.add(px + "," + py);
            peixesA.add(new PeixeA(px, py));
        }

        // Adiciona peixes tipo B
        for (int i = 0; i < y; i++) {
            int px, py;
            do {
                px = rand.nextInt(m);
                py = rand.nextInt(n);
            } while (posicoes.contains(px + "," + py));
            posicoes.add(px + "," + py);
            peixesB.add(new PeixeB(px, py));
        }
    }

    public void executarIteracao() {
        peixesAMortos.clear();
        peisesBMortos.clear();

        // Atualiza peixes tipo A
        for (PeixeA p : peixesA) {
            p.atualizar(this);
        }

        // Atualiza peixes tipo B
        for (PeixeB p : peixesB) {
            p.atualizar(this);
        }

        // Remove peixes mortos
        peixesA.removeAll(peixesAMortos);
        peixesB.removeAll(peisesBMortos);

        iteracoes++;
    }

    public boolean continuarJogo() {
        return !peixesB.isEmpty();
    }

    public void exibir() {
        char[][] matriz = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = '.';
            }
        }

        for (PeixeA p : peixesA) {
            if (p.getX() >= 0 && p.getX() < m && p.getY() >= 0 && p.getY() < n) {
                matriz[p.getX()][p.getY()] = 'A';
            }
        }

        for (PeixeB p : peixesB) {
            if (p.getX() >= 0 && p.getX() < m && p.getY() >= 0 && p.getY() < n) {
                matriz[p.getX()][p.getY()] = 'B';
            }
        }

        System.out.println("\n===== ITERAÇÃO " + iteracoes + " =====");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Peixes A: " + peixesA.size() + " | Peixes B: " + peixesB.size());
        System.out.println("================================");
    }

    public List<int[]> getCelulasAdjacentesLivres(int x, int y) {
        List<int[]> livres = new ArrayList<>();
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n &&
                    !temPeixe(nx, ny)) {
                livres.add(new int[]{nx, ny});
            }
        }
        return livres;
    }

    private boolean temPeixe(int x, int y) {
        for (PeixeA p : peixesA) {
            if (p.getX() == x && p.getY() == y) return true;
        }
        for (PeixeB p : peixesB) {
            if (p.getX() == x && p.getY() == y) return true;
        }
        return false;
    }

    public PeixeA getPeixeAProximo(int x, int y) {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            for (PeixeA p : peixesA) {
                if (p.getX() == nx && p.getY() == ny) {
                    return p;
                }
            }
        }
        return null;
    }

    public boolean temPeixeBVizinho(int x, int y) {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            for (PeixeB p : peixesB) {
                if (p.getX() == nx && p.getY() == ny) {
                    return true;
                }
            }
        }
        return false;
    }

    public void adicionarPeixeA(PeixeA p) {
        peixesA.add(p);
    }
    public void adicionarPeixeB(PeixeB p) {
        peixesB.add(p);
    }
    public void removerPeixeA(PeixeA p) {
        peixesA.remove(p);
    }
    public void marcarPeixeAMorto(PeixeA p) {
        peixesAMortos.add(p);
    }
    public void marcarPeixeBMorto(PeixeB p) {
        peisesBMortos.add(p);
    }

    public int getIteracoes() {
        return iteracoes;
    }
    public int getRA() {
        return ra;
    }
    public int getMA() {
        return ma;
    }
    public int getRB() {
        return rb;
    }
    public int getMB() {
        return mb;
    }
    public int getQtdPeixesA() {
        return peixesA.size();
    }
    public int getQtdPeixesB() {
        return peixesB.size();
    }
}