//              --- EJERCICIO 2 ---

public class Semaphore2 {
    private int permisos;

    public Semaphore2(int n) {
        this.permisos = n;
    }

    public synchronized void acquire() throws InterruptedException {
        while (permisos == 0) { // esperar si no hay permisos
            wait();
        }
        permisos--;
    }

    public synchronized void release() {
        permisos++;
        notify(); // despierta un hilo esperando como es debil no importa a cual
    }

    public synchronized int getValue() {
        return permisos;
    }
}

/*
 * El siguiente de la cola esta listo para ejecutar pero el scheduler puede
 * darle priorodad
 * a otro que entra y no esta en la cola => starvation
 * 
 * 
 * 
 * 
 * 
 */