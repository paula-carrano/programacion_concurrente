package grid6;

public class gridC {
    private final int N;
    private int consumidores = 0;
    private int productores = 0;
    private int productoresEsperando = 0;

    public gridC(int n) {
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
        productoresEsperando++;
        while ((productores - 1) < consumidores) {
            wait(); // espera si al terminar va a dejar sin suministros a los consumidores
        }
        productores--;
        productoresEsperando--;
        notifyAll();
    }

    // inicioConsumo
    public synchronized void inicioConsumo() throws InterruptedException {
        while (productores < 1 || productoresEsperando > 0) {
            wait(); // espera si no hay productores o si hay productores esperando a terminar
        }
        consumidores++;
    }

    // finConsumo
    public synchronized void finConsumo() {
        consumidores--;
        notifyAll();
    }
}
