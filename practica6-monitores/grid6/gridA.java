package grid6;

public class gridA {
    private int consumidores = 0;
    private int productores = 0;

    // inicioProduccion
    public synchronized void inicioProduccion() {
        productores++;
    }

    // finProduccion
    public synchronized void finProduccion() throws InterruptedException {
        // no puedo dejar sin suministros a los consumidores
        while ((productores - 1) < consumidores) {
            wait();
        }
        productores--;
        notifyAll();
    }

    // inicioConsumo
    public synchronized void inicioConsumo() throws InterruptedException {
        // al menos hay un productor
        while (productores < 1) {
            wait();
        }
        consumidores++;
    }

    // finConsumo
    public synchronized void finConsumo() {
        consumidores--;
        notifyAll();
    }

}
