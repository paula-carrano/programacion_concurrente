package grid6;

public class gridC {
    private int consumidores = 0;
    private int productores = 0;
    private int productoresEsperando = 0;
    private int maxOciosos;

    public gridC(int n) {
        this.maxOciosos = n;
    }

    // inicioProduccion
    public synchronized void inicioProduccion() throws InterruptedException {
        while (productores >= (consumidores + maxOciosos)) { // espera hasta que haya menos productores ociosos
            wait();
        }
        productores++;
        notifyAll();
    }

    // finProduccion
    public synchronized void finProduccion() throws InterruptedException {
        productoresEsperando++;
        while (productores == consumidores) {
            wait();
        }
        productores--;
        productoresEsperando--;
        notifyAll();
    }

    // inicioConsumo
    public synchronized void inicioConsumo() throws InterruptedException {
        while (productores <= consumidores || productoresEsperando > 0) {
            wait(); // espera si no hay productores o si hay productores esperando a terminar
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
