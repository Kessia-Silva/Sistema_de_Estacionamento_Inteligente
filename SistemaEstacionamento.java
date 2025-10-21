import java.util.*;
import java.util.concurrent.*;

public class SistemaEstacionamento {
    
    public static void main(String[] args) {
        System.out.println("\n=== Sistema de Estacionamento Inteligente ===\n");
        
        Estacionamento estacionamento = new Estacionamento();
        List<Future<Long>> futures = new ArrayList<>();
        List<Long> tempos = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Veiculo> veiculos = new ArrayList<>();

        int numeroDeVeiculos = 20;

         System.out.println("=== GERANDO VEÍCULOS ===");
         for(int i = 0; i < numeroDeVeiculos; i ++){
            Veiculo v = gerarVeiculoAleatorio();
            veiculos.add(v);
            System.out.println("Gerado: " + v);
         }

         System.out.println("\n=== INICIANDO SIMULAÇÃO ===\n");

         for (Veiculo v: veiculos) {
            Future <Long> future = executor.submit(new ProcessadorVeiculo(v, estacionamento));
            futures.add(future);
         }

         for(Future <Long> future: futures){
            try {
                Long tempo = future.get();
                if(tempo > 0){ // apenas se veiculo nao desistiu
                tempos.add(tempo);
            }
            } catch (Exception e) {
                 System.err.println("Erro ao processar veículo: " + e.getMessage());
                 e.printStackTrace();
            }
         }

         executor.shutdown();
         try {
            // espera até 60 segundos pelas tarefas terminarem
            if(!executor.awaitTermination(60, TimeUnit.SECONDS)){
                // se não terminar no tempo, força shutdown
                executor.shutdownNow();
            } 
         } catch (InterruptedException e) {
            // se a thread principal for interrompida, força shutdown
            executor.shutdownNow();
            Thread.currentThread().interrupt();
         }


         estacionamento.exibirStatus();

        System.out.println("\n=== ESTATÍSTICAS FINAIS ===");

        System.out.println("Total de veículos: " + numeroDeVeiculos);
        System.out.println("Conseguiram entrar: " + estacionamento.getTotalEntradas());
        System.out.println("Desistiram: " + estacionamento.getTotalDesistencias());
        System.out.println("Taxa de sucesso: " + estacionamento.getTotalEntradas()*100 / numeroDeVeiculos +"%");
        exibirEstatisticas(tempos, estacionamento);
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
        if (!tempos.isEmpty()) {

            Long tempoMinimo = Long.MAX_VALUE;
            Long tempoMaximo = Long.MIN_VALUE;
            double soma = 0;

            for(Long tempo: tempos){
                soma = soma + tempo;
                if(tempoMinimo > tempo){
                    tempoMinimo = tempo;
                }
                if(tempoMaximo < tempo){
                    tempoMaximo = tempo;
                }
            }

            double tempoMedio = soma/ tempos.size();
            double taxaOcupacao = (estacionamento.getTotalEntradas() * 100.0 ) / 7; // 7 = total de vagas    

            System.out.printf("Tempo médio de permanência: %.2f ms (%.2f segundos) %n" , tempoMedio, tempoMedio/1000);
            System.out.printf("Tempo mínimo: %d ms (%.2f segundos) %n" , tempoMinimo, tempoMinimo/1000.0);
            System.out.printf("Tempo máximo: %d ms (%.2f segundos) %n" , tempoMaximo, tempoMaximo/1000.0);
            System.out.println("Taxa de ocupação: " + Math.min(100, taxaOcupacao) +"%");
            System.out.println("===========================\n");
            System.out.println("=== Simulação Concluída ===\n");

        }
        else{
            System.out.println("Nenhum veículo completou o ciclo.");
        }
    }
}