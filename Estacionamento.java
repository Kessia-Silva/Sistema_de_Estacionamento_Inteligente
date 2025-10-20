import java.util.concurrent.atomic.AtomicInteger;

public class Estacionamento {
    // TODO: Declare os Semaphores necessários
    // - vagas regulares (5)
    // - vagas prioritárias (2)
    // - portão entrada (1)
    // - portão saída (1)
    
    private final AtomicInteger totalEntradas = new AtomicInteger(0);
    private final AtomicInteger totalDesistencias = new AtomicInteger(0);
    
    public Estacionamento() {
        // TODO: Inicialize os Semaphores
    }
    
    public boolean tentarEntrar(Veiculo veiculo) throws InterruptedException {
        // TODO: Implementar lógica de entrada
        // 1. Adquirir portão de entrada
        // 2. Log: veículo chegou ao portão
        // 3. Tentar adquirir vaga (com timeout de 2 segundos)
        //    - Se prioritário: tentar vaga prioritária primeiro, depois regular
        //    - Se normal: apenas vaga regular
        // 4. Se conseguiu vaga: incrementar totalEntradas e retornar true
        // 5. Se não conseguiu: incrementar totalDesistencias e retornar false
        // 6. Liberar portão de entrada (sempre!)
        
        return false; // placeholder - remover quando implementar
    }
    
    
    public void sair(Veiculo veiculo, boolean usouVagaPrioritaria) throws InterruptedException {
       // TODO: Implementar lógica de saída
        // 1. Adquirir portão de saída
        // 2. Log: veículo saindo
        // 3. Liberar a vaga apropriada
        // 4. Liberar portão de saída
    }
    
    public int getTotalEntradas() {
        return totalEntradas.get();
    }
    
    public int getTotalDesistencias() {
        return totalDesistencias.get();
    }
    
    /**
     * Exibe o status atual do estacionamento (vagas disponíveis).
     */
    public void exibirStatus() {
        // TODO: Exibir quantas vagas estão disponíveis de cada tipo
        System.out.println("\n=== STATUS DO ESTACIONAMENTO ===");
        // Use availablePermits() dos semáforos
    }
}