/*
 * Modifique el ´ıtem anterior para considerar que el telescopio no debe moverse de posici´on
 si hay alg´un pedido de calibraci´on pendiente (efectivamente priorizando la calibraci´on por
 sobre las observaciones en posiciones distintas a la actual)
 */

public class TelescopioC {
    private int observadores = 0;
    private boolean calibrado = false;
    private int posicionActual = 1;
    private int pedidosCalibracion = 0;

    public synchronized void iniciarObservacion(int posicion) throws InterruptedException {
        if (posicion < 1 || posicion > 4) {
            throw new IllegalArgumentException("Posicion invalida. Debe ser entre 1 y 4.");
        }

        while (calibrado || (observadores > 0 && posicionActual != posicion)
                || (observadores == 0 && pedidosCalibracion > 0)) {
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
        pedidosCalibracion++;
        try {
            while (calibrado || observadores > 0) {
                wait();
            }
            calibrado = true;
        } finally {
            pedidosCalibracion--;
        }
    }

    public synchronized void finalizarCalibracion() {
        calibrado = false;
        notifyAll();
    }
}
