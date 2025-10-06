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
        while ((productores - consumidores) >= N) {
            wait();
        }
        productores++;
        notifyAll();
    }

    // finProduccion
    public synchronized void finProduccion() throws InterruptedException {
        // no puedo dejar sin suministros a los consumidores
        while (productores == consumidores) {
            wait();
        }
        productores--;
        notifyAll();
    }

    // inicioConsumo
    public synchronized void inicioConsumo() throws InterruptedException {
        // al menos hay un productor
        while (consumidores >= productores) {
            wait();
        }
        consumidores++;
        notifyAll();
    }

    // finConsumo
    public synchronized void finConsumo() {
        consumidores--;
        notifyAll();
    }

}
