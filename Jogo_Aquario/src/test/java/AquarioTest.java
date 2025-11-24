
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AquarioTest {

    // ========== TESTES DE VALIDAÇÃO ==========

    @Test
    public void testValidacaoDimensoesValidas() {
        // Testa dimensões válidas
        boolean resultado = Validador.validarEntradas(5, 5, 3, 2, 2, 3, 2, 3);
        assertTrue(resultado, "Dimensões válidas devem ser aceitas");
    }

    @Test
    public void testValidacaoDimensaoZero() {
        // Testa dimensão zero (inválida)
        boolean resultado = Validador.validarEntradas(0, 5, 3, 2, 2, 3, 2, 3);
        assertFalse(resultado, "Dimensão zero deve ser rejeitada");
    }

    @Test
    public void testValidacaoDimensaoNegativa() {
        // Testa dimensão negativa (inválida)
        boolean resultado = Validador.validarEntradas(-3, 5, 3, 2, 2, 3, 2, 3);
        assertFalse(resultado, "Dimensão negativa deve ser rejeitada");
    }

    @Test
    public void testValidacaoPopulacaoNegativa() {
        // Testa população negativa (inválida)
        boolean resultado = Validador.validarEntradas(5, 5, -2, 2, 2, 3, 2, 3);
        assertFalse(resultado, "População negativa deve ser rejeitada");
    }

    @Test
    public void testValidacaoSemPeixes() {
        // Testa sem nenhum peixe (inválido)
        boolean resultado = Validador.validarEntradas(5, 5, 0, 0, 2, 3, 2, 3);
        assertFalse(resultado, "Aquário sem peixes deve ser rejeitado");
    }

    @Test
    public void testValidacaoPopulacaoMaiorQueCapacidade() {
        // Testa população maior que capacidade (inválido)
        boolean resultado = Validador.validarEntradas(3, 3, 6, 5, 2, 3, 2, 3);
        assertFalse(resultado, "População maior que capacidade deve ser rejeitada");
    }

    @Test
    public void testValidacaoParametroRAZero() {
        // Testa RA = 0 (inválido)
        boolean resultado = Validador.validarEntradas(5, 5, 3, 2, 0, 3, 2, 3);
        assertFalse(resultado, "RA zero deve ser rejeitado");
    }

    @Test
    public void testValidacaoParametroMANegativo() {
        // Testa MA negativo (inválido)
        boolean resultado = Validador.validarEntradas(5, 5, 3, 2, 2, -3, 2, 3);
        assertFalse(resultado, "MA negativo deve ser rejeitado");
    }

// ========== TESTES DE PEIXES ==========

    @Test
    public void testCriacaoPeixeA() {
        PeixeA peixe = new PeixeA(2, 3);
        assertEquals(2, peixe.getX());
        assertEquals(3, peixe.getY());
    }

    @Test
    public void testSimboloPeixeA() {
        PeixeA peixe = new PeixeA(0, 0);
        assertEquals('A', peixe.getSimbolo());
    }

    @Test
    public void testCriacaoPeixeB() {
        PeixeB peixe = new PeixeB(1, 4);
        assertEquals(1, peixe.getX());
        assertEquals(4, peixe.getY());
    }

    @Test
    public void testSimboloPeixeB() {
        PeixeB peixe = new PeixeB(0, 0);
        assertEquals('B', peixe.getSimbolo());
    }

}