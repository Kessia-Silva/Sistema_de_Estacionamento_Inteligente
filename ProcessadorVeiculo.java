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

        boolean conseguiuEntrar = estacionamento.tentarEntrar(veiculo);

        if(conseguiuEntrar == true){
            boolean conseguiuPrioritaria = veiculo.getConseguiuVagaPrioritaria();

            int tempo = 1000 + random.nextInt(4000);
            System.out.println(veiculo + " está estacionado por: " + tempo + "ms");
            Thread.sleep(tempo);
            estacionamento.sair(veiculo, conseguiuPrioritaria);

            return System.currentTimeMillis() - inicio;

        }
        else{
            return -1L; 
        }
    }

}
