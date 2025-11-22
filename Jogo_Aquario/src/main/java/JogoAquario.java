import java.util.InputMismatchException;
import java.util.Scanner;

public class JogoAquario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("====== JOGO DO AQUÁRIO ======");
            System.out.print("Dimensão M (linhas): ");
            int m = scanner.nextInt();

            System.out.print("Dimensão N (colunas): ");
            int n = scanner.nextInt();

            System.out.print("Peixes tipo A inicial: ");
            int x = scanner.nextInt();

            System.out.print("Peixes tipo B inicial: ");
            int y = scanner.nextInt();

            System.out.print("RA (ciclos para reprodução A): ");
            int ra = scanner.nextInt();

            System.out.print("MA (ciclos para morte A): ");
            int ma = scanner.nextInt();

            System.out.print("RB (refeições para reprodução B): ");
            int rb = scanner.nextInt();

            System.out.print("MB (ciclos para morte B): ");
            int mb = scanner.nextInt();

            Aquario aquario = new Aquario(m, n, x, y, ra, ma, rb, mb);

            System.out.println("\n===== JOGO INICIADO =====\n");
            aquario.exibir();

            while (aquario.continuarJogo()) {
                System.out.print("\nContinuar? (s/n): ");
                String resposta = scanner.next();

                if (resposta.equalsIgnoreCase("n")) {
                    break;
                }

                aquario.executarIteracao();
                aquario.exibir();
            }

            System.out.println("\n===== JOGO FINALIZADO =====");
            System.out.println("Iterações: " + aquario.getIteracoes());
            System.out.println("Peixes A restantes: " + aquario.getQtdPeixesA());
            System.out.println("Peixes B restantes: " + aquario.getQtdPeixesB());
            System.out.println("PONTUAÇÃO: " + aquario.getIteracoes());

        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida. Digite valores inteiros.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}