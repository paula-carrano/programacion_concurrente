public class Event5 {
    private int version = 0; // version del evento

    public synchronized void suscribe() throws InterruptedException {
        int miVersion = version;
        while (version == miVersion) {
        }
    }

    public synchronized void publish() {
            version++;
            notifyAll();
    }

    public synchronized int getVersion() {
        return version;
    }
};