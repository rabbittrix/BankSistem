package teste.unidade.dominio.modelo;

import conta.sistema.dominio.modelo.Conta;
import conta.sistema.dominio.modelo.NegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Regra de crédito de conta")
public class TesteCreditoConta {

    BigDecimal cem = new BigDecimal(100);
    Conta contaValida;

    @BeforeEach
    void preparar(){
        contaValida = new Conta(1, cem, "Roberto");
    }

    @Test
    @DisplayName("valor crédito nulo como obrigatório")
    void teste1(){
        try {
            contaValida.creditar(null);
            fail("valor crédito obrigatório.");
        }catch (NegocioException e){
            assertEquals(e.getMessage(), "Valor crédito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("valor crédito negativo")
    void teste2(){
        try {
            contaValida.creditar(new BigDecimal(-10));
            fail("valor crédito obrigatório.");
        }catch (NegocioException e){
            assertEquals(e.getMessage(), "Valor crédito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("valor crédito zero")
    void teste3(){
        try {
            contaValida.creditar(new BigDecimal(0));
            fail("valor crédito obrigatório.");
        }catch (NegocioException e){
            assertEquals(e.getMessage(), "Valor crédito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("valor crédito acima de zero")
    void teste4(){
        try {
            contaValida.setSaldo(cem.add(BigDecimal.ONE));
            var saldoFinal = cem.add(BigDecimal.ONE);
            assertEquals(contaValida.getSaldo(), saldoFinal, "Saldo deve bater");
        }catch (NegocioException e){
            fail("Deve creditar com sucesso - " + e.getMessage());
        }
    }
}
