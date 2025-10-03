package salaConferencia7;

public class salaConferenciaA {
    private int asistentes = 0;
    private int capacidad;
    private boolean conferenciaEnCurso = false;

    public salaConferenciaA(int capacidad) {
        this.capacidad = capacidad;
    }

    public synchronized void entrarSala() throws InterruptedException {
        while (asistentes == capacidad) {
            wait();
        }
        asistentes++;
        notifyAll(); // aviso de que hay un nuevo asistente
    }

    public synchronized void salirSala() throws InterruptedException {
        while (conferenciaEnCurso) {
            wait();
        }
        asistentes--;
        notifyAll();
    }

    public synchronized void iniciarConferencia() throws InterruptedException {
        while (asistentes == 0) {
            wait(); // espero a que haya al menos un asistente
        }
        conferenciaEnCurso = true;
    }

    public synchronized void finalizarConferencia() {
        conferenciaEnCurso = false;
        notifyAll(); // aviso para que puedan salir los asistentes
    }
}
