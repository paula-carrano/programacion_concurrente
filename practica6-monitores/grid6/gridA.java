package grid6;

public class gridA {
    private int consumidores = 0;
    private int productores = 0;

    // inicioProduccion
    public synchronized void inicioProduccion() {
        productores++;
        notify();
    }

    // finProduccion
    public synchronized void finProduccion() throws InterruptedException {
        while (productores ==consumidores) {
            wait();
        }
        productores--;
      
    }

    // inicioConsumo
    public synchronized void inicioConsumo() throws InterruptedException {
        // al menos hay un productor
        while (consumidor >= productores) {
            wait();
        }
        consumidores++;
    }

    // finConsumo
    public synchronized void finConsumo() {

        consumidores--;
        notify();
    }

}
