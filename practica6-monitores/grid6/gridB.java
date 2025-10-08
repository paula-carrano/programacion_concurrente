package grid6;

public class gridB {
    private int consumidores = 0;
    private int productores = 0;
    private int maxOciosos;

    public gridB(int n) {
        this.maxOciosos = n;
    }

    // inicioProduccion
    public synchronized void inicioProduccion() throws InterruptedException {
        while ((productores - consumidores) >= maxOciosos) { // espera hasta que haya menos productores ociosos
            wait();
        }
        productores++;
        notifyAll();
    }

    // finProduccion
    public synchronized void finProduccion() throws InterruptedException {
        while (productores == consumidores) {
            wait();
        }
        productores--;
        notifyAll();
    }

    // inicioConsumo
    public synchronized void inicioConsumo() throws InterruptedException {
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
