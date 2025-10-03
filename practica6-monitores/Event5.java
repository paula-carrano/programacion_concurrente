public class Event5 {
    private int version = 0; // version del evento
    private int suscriptoresEsperando = 0; // numero de hilos esperando al evento

    public synchronized void suscribe() throws InterruptedException {
        int miVersion = version;
        suscriptoresEsperando++;
        while (version == miVersion) {
            wait();
        }
        suscriptoresEsperando--;
    }

    public synchronized void publish() {
        if (suscriptoresEsperando > 0) {
            version++;
            notifyAll();
        }
    }

    public synchronized int getVersion() {
        return version;
    }
};