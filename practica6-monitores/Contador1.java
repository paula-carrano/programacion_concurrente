// --- EJERCICIO 1 ---

public class Contador1 {
    private int valor = 0;

    public synchronized void incrementa() {
        valor++;
        notifyAll();
    }

    public synchronized void decrementa() throws InterruptedException {
        while (valor == 0) {
            wait();
        }
        valor--;
        notifyAll();
    }

    public synchronized int getValor() {
        return valor;
    }

}
