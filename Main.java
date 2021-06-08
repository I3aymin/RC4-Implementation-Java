import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        char[] key = { '8', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f'};
    	//char [] key = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'};
        char[] keystream = new char[1024];
        int len = 9; // Length of keystream

        long startTime = System.currentTimeMillis();
        System.out.println("Reading File...");
        String temp = readFile("theFile");
        char[] plainText = temp.toCharArray();

        RC4 cipher = new RC4(key);
        cipher.KSA();
        cipher.PRGA(keystream, len);

        startTime = System.currentTimeMillis();
        System.out.println("Starting RC4 encryption...");
        char[] cipherText = {' '};
        for(int i = 0; i < 100000; i++) {
        	 cipherText = encrypt(plainText, keystream, len);
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Encryption Complete: " + totalTime + "ms");

        System.out.println(new String(cipherText));

        startTime = System.currentTimeMillis();
        System.out.println("Starting RC4 decryption...");
        char[] decryptText = decrypt(cipherText, keystream, len);
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println("Decryption Complete: " + totalTime + "ms");
        for (int i = 0; i < 8; i++) {
            System.out.print(decryptText[i]);
        }
    }

    public static String readFile(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) {
            sb.append(sc.nextLine());
        }
        sc.close();
        return sb.toString();
    }

    // Encrypted the given plain text using the keystream.
    public static char[] encrypt(char[] plainText, char[] keystream, int len) {
        char[] cipherText = new char[plainText.length];
        for (int i = 0; i < plainText.length; i++) {
            cipherText[i] = (char) (plainText[i] ^ keystream[i % len]);
        }
        return cipherText;
    }

    public static char[] decrypt(char[] cipherText, char[] keystream, int len) {
        return encrypt(cipherText, keystream, len);
    }

}