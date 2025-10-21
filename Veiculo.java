import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

enum TipoVeiculo {
    NORMAL, PRIORITARIO
}

public class Veiculo {
    private static final AtomicInteger contador = new AtomicInteger(0);
    private final int id;
    private final TipoVeiculo tipo;
    private final long chegada;

    private final AtomicBoolean conseguiuVagaPrioritaria = new AtomicBoolean(false);
    
    public Veiculo(TipoVeiculo tipo) {
        this.id = contador.incrementAndGet();
        this.tipo = tipo;
        this.chegada = System.currentTimeMillis();
    }
    
    public int getId() { 
        return id; 
    }
    public TipoVeiculo getTipo() { 
        return tipo; 
    }
    public long getChegada() { 
        return chegada; 
    }

    public boolean getConseguiuVagaPrioritaria(){
        return conseguiuVagaPrioritaria.get();
    }

    public void setConseguiuVagaPrioritaria(boolean valor){
        conseguiuVagaPrioritaria.set(valor);
    }

    
    @Override
    public String toString() {
        return String.format("Veículo #%d (%s)", id, tipo);
    }

}
