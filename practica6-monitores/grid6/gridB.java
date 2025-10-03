package grid6;

public class gridB {
    private final int N;
    private int consumidores = 0;
    private int productores = 0;

    public gridB(int n) {
        this.N = n;
    }

    // inicioProduccion
    public synchronized void inicioProduccion() throws InterruptedException {
        while ((productores - consumidores) >= N) { // espera hasta que haya menos productores ociosos
            wait();
        }
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
