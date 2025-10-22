/*
 * Modifique el ´ıtem anterior para considerar que se puede controlar la direcci´on en la que
 est´a apuntando el telescopio (por simplicidad, diremos que las posiciones posibles son 1, 2, 3
 y 4). Modifique el m´etodo iniciarObservacion(int posicion) para que tome por par´ametro
 la posici´on que se quiere observar. Si el telescopio no est´a siendo calibrado o utilizado
 para observar en otra direcci´on, se inicia la observaci´on y se interpreta que el telescopio en
 ese momento se mueve de manera instant´anea a la posici´on nueva. Si por el contrario el
 telescopio est´a en uso, se debe esperar a que se libere.
 */

public class TelescopioB {
    private int observadores = 0;
    private boolean calibrado = false;
    private int posicionActual = 1;

    public synchronized void iniciarObservacion(int posicion) throws InterruptedException {
        if (posicion < 1 || posicion > 4) {
            throw new IllegalArgumentException("Posicion invalida. Debe ser entre 1 y 4.");
        }

        while (calibrado || (observadores > 0 && posicionActual != posicion)) {
            wait();
        }

        if (observadores == 0) {
            posicionActual = posicion;
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
