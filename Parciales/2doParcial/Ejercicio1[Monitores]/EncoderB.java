import java.util.ArrayList;
import java.util.List;

public class EncoderB {
    private final int M;
    private final ArrayList<Byte> listaFrames = new ArrayList<>();

    public EncoderB(int M) {
        this.M = M;
    }

    public synchronized void putRawFrame(Byte frame) throws InterruptedException {
        while (listaFrames.size() > M) {
            wait();
        }

        listaFrames.add(frame);

        notifyAll();
    }

    public synchronized List<Byte> getPack(int p) throws InterruptedException {
        while (listaFrames.size() < p) {
            wait();
        }

        List<Byte> pack = new ArrayList<>();

        for (int i = 0; i < p; i++) {
            pack.add(listaFrames.removeFirst());
        }

        notifyAll();
        return pack;
    }
};
