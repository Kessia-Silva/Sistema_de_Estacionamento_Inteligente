import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Estacionamento {
    // TODO: Declare os Semaphores necessários
    // - vagas regulares (5)
    // - vagas prioritárias (2)
    // - portão entrada (1)
    // - portão saída (1)
    private static Semaphore entrada;
    private static Semaphore saida;
    private static Semaphore vagaRegular;
    private static Semaphore vagaPrioritaria;
    
    
    private final AtomicInteger totalEntradas = new AtomicInteger(0);
    private final AtomicInteger totalDesistencias = new AtomicInteger(0);
    
    public Estacionamento() {
        // TODO: Inicialize os Semaphores
        entrada = new Semaphore(1);
        saida = new Semaphore(1);
        vagaRegular =  new Semaphore(5);
        vagaPrioritaria =  new Semaphore(2);
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

        entrada.acquire();
        System.out.println(veiculo + " chegou ao portão de entrada");
        boolean conseguiuVaga = false;
        boolean conseguiuVagaPrioritaria = false;

        try {
            if(veiculo.getTipo() == TipoVeiculo.PRIORITARIO){
                // Veiculo PRIORITARIO
                if(vagaPrioritaria.tryAcquire(2, TimeUnit.SECONDS)){
                    // conseguiu vaga prioritaria
                    System.out.println(veiculo + " conseguiu vaga PRIORITARIA");
                    conseguiuVaga = true;
                    conseguiuVagaPrioritaria = true;
                }
                else if(vagaRegular.tryAcquire(2, TimeUnit.SECONDS)){
                    // nao conseguiu vaga prioritaria foi para regular
                    System.out.println(veiculo + " conseguiu vaga Regular");
                    conseguiuVaga = true;
                }
            }
            else{
                // Veiculo REGULAR
                if(vagaRegular.tryAcquire(2, TimeUnit.SECONDS)){
                    System.out.println(veiculo + " conseguiu vaga REGULAR");
                    conseguiuVaga = true;
                }
            }

            if(conseguiuVaga == true){
                totalEntradas.incrementAndGet();
            }
            else {
               totalDesistencias.incrementAndGet(); 
               System.out.println("Desistiu, não havia vagas disponíveis");
            }
            
        } catch (Exception e) {
            Thread.currentThread().interrupt();

        } finally {
            entrada.release();
        }
        
       return conseguiuVaga;
    }
    
    
    public void sair(Veiculo veiculo, boolean usouVagaPrioritaria) throws InterruptedException {
       // TODO: Implementar lógica de saída
        // 1. Adquirir portão de saída
        // 2. Log: veículo saindo
        // 3. Liberar a vaga apropriada
        // 4. Liberar portão de saída

        saida.acquire();
        try {
            System.out.println(veiculo + " saiu com sucesso!");
            if(usouVagaPrioritaria == true){
                vagaPrioritaria.release();
            }
            else{
                vagaRegular.release();
            }
            
        } catch (Exception e) {
        } finally {
            saida.release();
        }  
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
        System.out.println("Vagas regulares disponíveis: "+ vagaRegular.availablePermits() + "/5");
        System.out.println("Vagas prioritárias disponíveis: " +vagaPrioritaria.availablePermits() + "/2");
        System.out.println("Total de entradas: " +totalEntradas);
        System.out.println("Total de desistências: "+totalDesistencias);
        System.out.println("=============================\n");

    }
}