import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class RC4 {
    private char[] state;
    private char[] key;

    public RC4(char[] key) {
        state = new char[256];
        this.key = key;
    }

    // Initialise states
    public void KSA() {
        int j = 0;
        for (int i = 0; i < 256; i++) {
            state[i] = (char) i;
        }
        for (int i = 0; i < 256; i++) {
            j = (j + state[i] + key[i % key.length]) % 256;
            swap(state[i], state[j]);
        }
    }

    // Swap elements in state
    public void swap(char i, char j) {
        char temp = state[j];
        state[j] = i;
        state[i] = temp;
    }

    // Generates key stream
    public void PRGA(char[] out, int len) {
        int i = 0;
        int j = 0;
        char t = 0;
        for (int x = 0; x < len; x++) {
            i = (i + 1) % 256;
            j = (j + state[i]) % 256;
            t = state[i];
            state[i] = state[j];
            state[j] = t;
            out[x] = state[(state[i] + state[j]) % 256];
        }
    }
}
