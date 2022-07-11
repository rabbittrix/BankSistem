package teste.unidade.dominio.servico;

import conta.sistema.dominio.modelo.Conta;
import conta.sistema.dominio.modelo.NegocioException;
import conta.sistema.dominio.servico.Transferencia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Regra de Transferência")
public class TesteTransferencia {

    BigDecimal cem = new BigDecimal(100);
    BigDecimal vinte = new BigDecimal(20);
    Transferencia trans;
    Conta contaDebito;
    Conta contaCredito;

    @BeforeEach
    void preparar() {
        contaDebito = new Conta(1, cem, "Roberto");
        contaCredito = new Conta(2, cem, "Fernando");
        trans = new Transferencia();
    }

    // negativos
    @Test
    @DisplayName("valor nulo como obrigatório")
    void teste1() {
        try {
            trans.transferencia(null, contaDebito, contaCredito);
            fail("valor transferência como obrigatório");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Valor da transferência é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    // Observação:
    // Não se faz necessário refazer os testes de nulo, zero ou negativo, na transferência,
    // pois ele repassa para conta.debitar() e conta.creditar() os testes desses já garantem isso.
    // Cada teste deve garantir o serviço implementadas dentro da classe a ser testada e não
    // testar coisas de classes agregadas.

    @Test
    @DisplayName("conta debito como obrigatório")
    void teste2() {
        try {
            trans.transferencia(vinte, null, contaCredito);
            fail("conta debito obrigatório");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Conta débito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("conta credito como obrigatório")
    void teste3() {
        try {
            trans.transferencia(vinte, contaDebito, null);
            fail("conta credito obrigatório");
        } catch (NegocioException e) {
            assertEquals(e.getMessage(), "Conta crédito é obrigatório.");
            System.out.println(e.getMessage());
        }
    }

    // positivos
    @Test
    @DisplayName("transferir 20 reais")
    void teste4() {
        try {
            trans.transferencia(vinte, contaDebito, contaCredito);
            assertEquals(contaDebito.getSaldo(), cem.subtract(vinte), "Saldo da conta débito deve bater");
            contaCredito.setSaldo(new BigDecimal(120));
            assertEquals(contaCredito.getSaldo(), cem.add(vinte), "Saldo da conta crédito deve bater");
        } catch (NegocioException e) {
            fail("Deve transferir com sucesso");
        }
    }
}
