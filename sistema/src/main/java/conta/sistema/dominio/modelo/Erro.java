package conta.sistema.dominio.modelo;

public class Erro {

    public static void obrigatorio(String name){
        throw new NegocioException(name + " é obrigatório.");
    }

    public static void inexistente(String name){
        throw new NegocioException(name + " é inexistente.");
    }

    public static void saldoInsuficiente(){
        throw new NegocioException("Saldo insuficiente.");
    }

    public static void mesmaConta(){
        throw new NegocioException(" Conta débito e crédito devem ser diferentes.");
    }
}
