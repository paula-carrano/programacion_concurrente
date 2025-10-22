
/*
El telescopio no puede ser utilizado para observaciones mientras est´a sien
do calibrado, ni puede calibrarse mientras haya otra calibraci´on en curso o alguien realizando
 una observación.
 Implemente el monitor propuesto, tomando en cuenta que el telescopio no tiene un m´aximo
 de observadores concurrentes. 
*/

class TelescopioA {
    private int observadores = 0;
    private boolean calibrado = false;

    public synchronized void iniciarObservacion() throws InterruptedException {
        while (calibrado) {
            wait();
        }
        observadores++;
    }

    public synchronized void finalizarObservacion() {
        observadores--;
        if (observadores == 0) {
            notifyAll();
        }
    }

    public synchronized void iniciarCalibracion() throws InterruptedException {
        while (calibrado || observadores > 0) {
            wait();
        }
        calibrado = true;
    }

    public synchronized void finalizarCalibracion() {
        calibrado = false;
        notifyAll();
    }
}
