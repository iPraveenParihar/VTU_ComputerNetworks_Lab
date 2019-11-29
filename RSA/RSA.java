import java.util.*;

public class RSA{

    static String strData;
    static int p, q, n, e, d, phi, dataLength;

    public static int gcd(int m, int n){
        if(n == 0)
            return m;
        return gcd(n, m%n);
    }

    public static int[] encrypt() {

        int[] encryptData = new int[dataLength];
        int[] nummes = new int[dataLength];

        for(int i = 0; i < dataLength; i++){
            char c = strData.charAt(i);
            int a = (int)c;
            nummes[i] = a - 96;
        }

        for(int i = 0; i < dataLength; i++){
            encryptData[i] = 1;
            for(int j = 0; j < e; j++){
                encryptData[i] = (encryptData[i]*nummes[i])%n;
            }
        }

       return encryptData;
    }

    public static int[] decrypt(int[] encryptedData) {

        int[] decryptData = new int[dataLength];

        for(int i = 0; i < dataLength; i++){
            decryptData[i] = 1;
            for(int j = 0; j < d; j++){
                decryptData[i] = (decryptData[i] * encryptedData[i])%n;
            }
        }

        return decryptData;
    }

    public static void main(String[] args) {
        
        try{
            Scanner in = new Scanner(System.in);

            System.out.print("Enter the message to be encrypted: ");
            strData = in.nextLine();

            System.out.print("Enter two large prime numbers: ");
            p = in.nextInt(); 
            q = in.nextInt();

            dataLength = strData.length();
            n = p * q;
            phi = (p-1) * (q-1);

            e = 2;
            while(e < phi){
                if(gcd(e,phi) == 1)
                    break;
                e++;
            }

            d = 2;
            while(d < phi){
                if((e*d-1) % phi == 0)
                    break;
                d++;
            }

            int[] encryptedData = encrypt();
            System.out.print("\n\nEncrypted Message: ");
            for(int i = 0; i < dataLength; i++)
                System.out.print(encryptedData[i]+""+(char)(encryptedData[i] + 96));

            int[] decryptedData = decrypt(encryptedData);
            System.out.print("\n\nDecrypted Message: ");
            for(int i = 0; i < dataLength; i++)
                System.out.print((char)(decryptedData[i] + 96));

        }   
        catch(Exception e){
            System.out.println(e);
        }
    }
}