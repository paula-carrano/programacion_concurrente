// EJERCICIO 3

public class SecuenciadorTernacio3 {
    private int estado = 0;

    // primero() -> ejecuta 1 vez
    public synchronized void primero() throws InterruptedException {
        while (estado != 1) {
            wait();
        }
        estado = 2;
        notifyAll();
    }

    // segundo() -> ejecuta despues de primero()
    public synchronized void segundo() throws InterruptedException {
        while (estado != 2) {
            wait();
        }
        estado = 3;
        notifyAll();
    }

    // tercero() -> ejecuta despues de segundo()
    public synchronized void tercero() throws InterruptedException {
        while (estado != 3) {
            wait();
        }
        estado = 1;
        notifyAll();
    }
}
