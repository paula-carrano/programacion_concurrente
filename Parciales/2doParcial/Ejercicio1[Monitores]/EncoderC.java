import java.util.ArrayList;
import java.util.List;

public class EncoderC {
    private final int M;
    private final int K;
    private final ArrayList<Byte> listaFrames = new ArrayList<>();
    private int encodingCount = 0;

    public EncoderC(int M, int K) {
        this.M = M;
        this.K = K;
    }

    public synchronized void putRawFrame(Byte frame) throws InterruptedException {
        while (listaFrames.size() >= M) {
            wait();
        }

        listaFrames.addLast(frame);
        notifyAll();
    }

    public synchronized List<Byte> getPack(int p) throws InterruptedException {
        // espero si hay k threads codificando
        while (encodingCount >= K) {
            wait();
        }

        // espero cuando haya al menos p frames en el listaFrames
        while (listaFrames.size() < p) {
            wait();
        }

        encodingCount++;

        List<Byte> pack = new ArrayList<>();
        for (int i = 0; i < p; i++) {
            pack.add(listaFrames.removeFirst());
        }
        notifyAll();
        return pack;
    }

    public synchronized void putEncodedPack(List<Byte> encodedPack) {
        encodingCount--;
        notifyAll();
    }
}
