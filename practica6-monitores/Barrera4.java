public class Barrera4 {
    private int contador = 0;
    private final int N;
    private boolean barreraArriba = false;

    public Barrera4(int n) {
        this.N = n;
    }

    public synchronized void esperar() throws InterruptedException {

        if (barreraArriba) { // La barrera ya está arriba, no espera
            return;
        }

        contador++;

        if (contador == N) {
            barreraArriba = true;
            notifyAll(); // Despierta a todos los hilos esperando
        } else {
            while (!barreraArriba) {
                wait(); // Espera hasta que la barrera esté arriba
            }
        }
    }

}
