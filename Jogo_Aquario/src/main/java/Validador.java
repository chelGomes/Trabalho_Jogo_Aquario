class Validador {
    public static boolean validarEntradas(int m, int n, int x, int y,
                                          int ra, int ma, int rb, int mb) {
        if (m <= 0 || n <= 0) {
            System.out.println("Erro: Dimensões da matriz devem ser positivas");
            return false;
        }
        if (x < 0 || y < 0) {
            System.out.println("Erro: Populações não podem ser negativas");
            return false;
        }
        if (x + y == 0) {
            System.out.println("Erro: Deve haver pelo menos um peixe");
            return false;
        }
        if (x + y > m * n) {
            System.out.println("Erro: População maior que capacidade da matriz");
            return false;
        }
        if (ra <= 0 || ma <= 0 || rb <= 0 || mb <= 0) {
            System.out.println("Erro: Parâmetros RA, MA, RB, MB devem ser positivos");
            return false;
        }
        return true;
    }
}