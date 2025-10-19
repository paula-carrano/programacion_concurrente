
// Ejercicio 1 [Monitores]

import java.util.ArrayList;
import java.util.List;

public class EncoderA {

    private final int M; // capacidad maxima
    private final int P; // cantidad de frames por paquete
    private ArrayList<Byte> listaFrames = new ArrayList<>();

    public EncoderA(int M, int P) {
        this.M = M;
        this.P = P;
    }

    public synchronized void putRawFrame(Byte frame) throws InterruptedException {
        while (listaFrames.size() >= M) {
            wait();
        }

        listaFrames.add(frame);

        if (listaFrames.size() >= P) {
            notify();
        }
    }

    public synchronized List<Byte> getPack() throws InterruptedException {
        while (listaFrames.size() < P) {
            wait(); // espera hasta que haya suficientes frames
        }

        List<Byte> pack = new ArrayList<>();

        for (int i = 0; i < P; i++) {
            pack.add(listaFrames.removeFirst());
        }

        notifyAll();

        return pack;
    }
}