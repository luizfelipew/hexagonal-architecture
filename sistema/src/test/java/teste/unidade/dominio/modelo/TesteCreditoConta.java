package teste.unidade.dominio.modelo;

import conta.sistema.dominio.modelo.Conta;
import conta.sistema.dominio.modelo.NegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Regra de Crédito de Conta")
public class TesteCreditoConta {

    BigDecimal cem = BigDecimal.valueOf(100);
    Conta contaValida;

    @BeforeEach
    void preparar() {
        contaValida = new Conta(1, cem, "Rebeca");
    }

    @Test
    @DisplayName("Valor credito nulo como obrigatorio")
    void deveVerificarValorCreditoNulo() {
        try {
            contaValida.creditar(null);
            fail("valor crédito obrigatório");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor crédito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Valor credito negativo como obrigatorio")
    void deveVerificarValorCreditoNegativo() {
        try {
            contaValida.creditar(BigDecimal.valueOf(-10));
            fail("valor crédito obrigatório");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor crédito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Valor credito zerado como obrigatorio")
    void deveVerificarValorCreditoZerado() {
        try {
            contaValida.creditar(BigDecimal.ZERO);
            fail("valor crédito obrigatório");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor crédito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Valor credito acima de zero como obrigatorio")
    void deveVerificarValorCreditoValido() {
        try {
            contaValida.creditar(BigDecimal.ONE);
            var saldoFinal = cem.add(BigDecimal.ONE);
            assertEquals(contaValida.getSaldo(), saldoFinal, "Saldo deve bater");
        } catch (NegocioException e) {
            fail("Deve creditar com sucesso - " + e.getMessage());
        }
    }
}
