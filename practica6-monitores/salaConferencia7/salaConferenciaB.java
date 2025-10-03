package salaConferencia7;

public class salaConferenciaB {
    private int asistentes = 0;
    private final int capacidad;
    private boolean charlaEnCurso = false;
    private int numeroCharlaActual = 0; // 0,1,2

    public salaConferenciaB(int capacidad) {
        this.capacidad = capacidad;
    }

    public synchronized void entrar(int numeroCharla) throws InterruptedException {
        while (asistentes == capacidad || (charlaEnCurso && numeroCharla != numeroCharlaActual)) {
            wait();
        }
        asistentes++;
        notifyAll();
    }

    public synchronized void salir() throws InterruptedException {
        while (charlaEnCurso) {
            wait();
        }
        asistentes--;
        notifyAll();
    }

    public synchronized void inicioCharla(int numeroCharla) throws InterruptedException {
        while (numeroCharla != numeroCharlaActual || asistentes == 0) {
            wait();
        }
        charlaEnCurso = true;
    }

    public synchronized void finCharla() {
        charlaEnCurso = false;
        numeroCharlaActual = (numeroCharlaActual + 1) % 3; // rotar charla
        notifyAll();
    }
}
