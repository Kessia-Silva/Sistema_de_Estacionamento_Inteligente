import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Estacionamento {

    private final Semaphore vagaRegular;
    private final Semaphore vagaPrioritaria;
    private final Semaphore portaoEntrada;
    private final Semaphore portaoSaida;
    
    private final AtomicInteger totalEntradas = new AtomicInteger(0);
    private final AtomicInteger totalDesistencias = new AtomicInteger(0);

    
    
    public Estacionamento() {
        vagaRegular = new Semaphore(5);
        vagaPrioritaria = new Semaphore(2);
        portaoEntrada = new Semaphore(1);
        portaoSaida = new Semaphore(1);
    }
    
    public boolean tentarEntrar(Veiculo veiculo) throws InterruptedException {

        portaoEntrada.acquire();
        System.out.println(veiculo + " chegou ao portão de entrada");

        boolean conseguiuEntrar = false;

        try {
            if(veiculo.getTipo() == TipoVeiculo.PRIORITARIO){
                if(vagaPrioritaria.tryAcquire(2, TimeUnit.SECONDS)){
                    System.out.println(veiculo + " conseguiu vaga PRIORITARIA");
                    conseguiuEntrar = true;
                    veiculo.setConseguiuVagaPrioritaria(true);
                }
                else if (vagaRegular.tryAcquire(2, TimeUnit.SECONDS)) {
                    System.out.println(veiculo + " conseguiu vaga REGULAR");  
                    conseguiuEntrar = true; 
                    veiculo.setConseguiuVagaPrioritaria(false);
                }                 
            }

            else if(veiculo.getTipo() == TipoVeiculo.NORMAL){
                if(vagaRegular.tryAcquire(2, TimeUnit.SECONDS)){
                    System.out.println(veiculo + " conseguiu vaga REGULAR");   
                    conseguiuEntrar = true;
                    veiculo.setConseguiuVagaPrioritaria(false);
                }
            }

            if (conseguiuEntrar == true) {
                totalEntradas.incrementAndGet(); 
            }
            if(conseguiuEntrar == false){
                totalDesistencias.incrementAndGet();
                System.out.println(veiculo + " desistiu da vaga");  

            }
            
        } finally {
            portaoEntrada.release();
        }

        return conseguiuEntrar;
    }
    
    
    public void sair(Veiculo veiculo, boolean usouVagaPrioritaria) throws InterruptedException {

        portaoSaida.acquire();

        System.out.println(veiculo + " está saindo do estacionamento... ");

        try {
            if(usouVagaPrioritaria == true){
                vagaPrioritaria.release();
            }
            if(usouVagaPrioritaria == false){
                vagaRegular.release();
            }
            System.out.println(veiculo + " saiu com sucesso!");
            
        } finally {
            portaoSaida.release();
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

        System.out.println("Vagas regulares disponíveis: " + vagaRegular.availablePermits() + "/5"); 
        System.out.println("Vagas prioritárias disponíveis: " + vagaPrioritaria.availablePermits() + "/2"); 
        System.out.println("Total de entradas: " +totalEntradas.get()); 
        System.out.println("Total de desistências: " +totalDesistencias.get()); 
        System.out.println("=================================="); 
    }
}