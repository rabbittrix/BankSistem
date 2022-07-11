package teste.unidade.dominio.modelo;

import conta.sistema.dominio.modelo.Conta;
import conta.sistema.dominio.modelo.NegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Regra de débito de conta")
public class TesteDebitoConta {

    BigDecimal cem = new BigDecimal(100);
    Conta contaValida;

    @BeforeEach
    void preparar(){
        contaValida = new Conta(1, cem, "Roberto");
    }

    @Test
    @DisplayName("valor débito nulo como obrigatório")
    void teste1(){
        try {
            contaValida.debitar(null);
            fail("valor débito obrigatório.");
        }catch (NegocioException e){
            assertEquals(e.getMessage(), "Valor débito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("valor débito negativo")
    void teste2(){
        try {
            contaValida.debitar(new BigDecimal(-10));
            fail("valor débito obrigatório.");
        }catch (NegocioException e){
            assertEquals(e.getMessage(), "Valor débito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("valor débito zero")
    void teste3(){
        try {
            contaValida.debitar(BigDecimal.ZERO);
            fail("valor débito obrigatório.");
        }catch (NegocioException e){
            assertEquals(e.getMessage(), "Valor débito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("valor débito acima de zero")
    void teste4(){
        try {
            contaValida.debitar(cem.add(BigDecimal.ONE));
            fail("Valor débito acima do saldo");
        }catch (NegocioException e){
            assertEquals(e.getMessage(), "Saldo insuficiente.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("valor débito igual ao saldo")
    void teste5(){
        try {
            contaValida.debitar(cem);
            assertEquals(contaValida.getSaldo(), BigDecimal.ZERO, "Saldo deve zerar");
        }catch (NegocioException e){
            fail("Deve debitar com sucesso - " + e.getMessage());
        }
    }

    @Test
    @DisplayName("valor débito menor que saldo")
    void teste6(){
        try {
            contaValida.debitar(BigDecimal.TEN);
            var saldoFinal = cem.subtract(BigDecimal.TEN);
            assertEquals(contaValida.getSaldo(), saldoFinal, "Saldo deve bater");
        }catch (NegocioException e){
            fail("Deve debitar com sucesso - " + e.getMessage());
        }
    }
}
