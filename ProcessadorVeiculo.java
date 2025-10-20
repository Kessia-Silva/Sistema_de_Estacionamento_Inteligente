import java.util.Random;
import java.util.concurrent.Callable;

public class ProcessadorVeiculo implements Callable<Long> {
    private final Veiculo veiculo;
    private final Estacionamento estacionamento;
    private final Random random = new Random();
    
    public ProcessadorVeiculo(Veiculo veiculo, Estacionamento estacionamento) {
        this.veiculo = veiculo;
        this.estacionamento = estacionamento;
    }
    
    @Override
    public Long call() throws Exception {
        long inicio = System.currentTimeMillis();
        
        // TODO: Implementar ciclo completo do veículo
        // 1. Tentar entrar no estacionamento
        // 2. Se conseguiu entrar:
        //    - Simular tempo estacionado (1-5 segundos aleatório)
        //    - Sair do estacionamento
        // 3. Retornar tempo total (entrada até saída), ou -1 se desistiu
        
        return -1L; // placeholder
    }

}
