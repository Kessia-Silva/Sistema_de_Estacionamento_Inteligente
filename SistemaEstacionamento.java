import java.util.*;
import java.util.concurrent.*;

public class SistemaEstacionamento {
    
    public static void main(String[] args) {
        System.out.println("=== Sistema de Estacionamento Inteligente ===\n");
        
        // TODO: Implementar o sistema completo
        // 1. Criar instância do Estacionamento
        // 2. Criar ExecutorService (FixedThreadPool com 4 threads)
        // 3. Gerar 20 veículos (70% normais, 30% prioritários)
        // 4. Submeter todos os veículos ao executor
        // 5. Aguardar conclusão de todos (usar futures)
        // 6. Calcular estatísticas
        // 7. Shutdown do executor
        
        Estacionamento estacionamento = new Estacionamento();
        List<Future<Long>> futures = new ArrayList<>();
        List<Long> tempos = new ArrayList<>();
        
        // Seu código aqui...
        
        System.out.println("\n=== ESTATÍSTICAS FINAIS ===");
        // TODO: Exibir:
        // - Total de veículos que entraram
        // - Total de veículos que desistiram
        // - Tempo médio de permanência
        // - Taxa de ocupação do estacionamento
    }
    
    /**
     * Gera um veículo aleatório (70% normal, 30% prioritário).
     */
    private static Veiculo gerarVeiculoAleatorio() {
        Random rand = new Random();
        TipoVeiculo tipo = rand.nextDouble() < 0.3 ? 
            TipoVeiculo.PRIORITARIO : TipoVeiculo.NORMAL;
        return new Veiculo(tipo);
    }
    
    /**
     * Calcula e exibe estatísticas da simulação.
     */
    private static void exibirEstatisticas(List<Long> tempos, Estacionamento estacionamento) {
        // TODO: Calcular e exibir estatísticas
        if (tempos.isEmpty()) {
            System.out.println("Nenhum veículo completou o ciclo.");
            return;
        }
        // Calcular média, máximo, mínimo dos tempos
        // Calcular taxa de ocupação
    }
}