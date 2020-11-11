package teste.unidade.dominio.modelo;

import conta.sistema.dominio.modelo.Conta;
import conta.sistema.dominio.modelo.NegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


@DisplayName("Regra de Débito de Conta")
public class TesteDebitoConta {

    BigDecimal cem = BigDecimal.valueOf(100);
    Conta contaValida;

    @BeforeEach
    void preparar() {
        contaValida = new Conta(1, cem, "Rebeca");
    }

    @Test
    @DisplayName("Valor debito nulo como obrigatorio")
    void deveVerificarValorDebitoNulo() {
        try {
            contaValida.debitar(null);
            fail("valor débito obrigatório");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor débito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Valor debito negativo como obrigatorio")
    void deveVerificarValorDebitoNegativo() {
        try {
            contaValida.debitar(BigDecimal.valueOf(-10));
            fail("valor débito obrigatório");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor débito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Valor debito zerado como obrigatorio")
    void deveVerificarValorDebitoZerado() {
        try {
            contaValida.debitar(BigDecimal.ZERO);
            fail("valor débito obrigatório");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor débito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Valor debito acima do saldo")
    void deveVerificarValorDebitoAcimaDoSaldo() {
        try {
            contaValida.debitar(cem.add(BigDecimal.ONE));
            fail("valor débito acima do saldo");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Saldo insuficiente.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Valor debito com saldo igual")
    void deveVerificarValorDebitoComSaldoIgual() {
        try {
            contaValida.debitar(cem);
            assertEquals(contaValida.getSaldo(), BigDecimal.ZERO, "Saldo deve zerar");
        } catch (NegocioException e) {
            fail("Deve debitar com sucesso - " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Valor debito menor que o saldo")
    void deveVerificarValorDebitoMenorQueSaldo() {
        try {
            contaValida.debitar(BigDecimal.TEN);
            var saldoFinal = cem.subtract(BigDecimal.TEN);
            assertEquals(contaValida.getSaldo(), saldoFinal, "Saldo deve zerar");
        } catch (NegocioException e) {
            fail("Deve debitar com sucesso - " + e.getMessage());
        }
    }
}
