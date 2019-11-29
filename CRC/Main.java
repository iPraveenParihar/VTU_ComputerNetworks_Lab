import java.io.* ;
import java.util.*;

class CRC {

   String genPoly = "10001000000100001";
   String data;

   StringBuilder remainder;
   StringBuilder dataword;

   public CRC() {
      System.out.println("\nError Detecting Code Using CRC-CCITT (16-bit)");
      System.out.println("Generator Polynominal : "+genPoly+"\n\n");
   }

   public CRC(String data) {

      this.data = data;
   }

   public void generateDataword() {

      dataword = new StringBuilder(data);

      int dataLen = data.length();
      int genPolyLen = genPoly.length();

      for(int i = 0; i < (genPolyLen-1); i++)
         dataword.append('0');

      System.out.println("\nDataWord : "+dataword);      
   }


   public static String xor(String a, String b) {

        StringBuilder stringBuilder = new StringBuilder();
        int len = Math.min(a.length(), b.length());
        for (int i = 0; i < len; i++) {
            if (a.charAt(i) == b.charAt(i)) {
                stringBuilder.append('0');
            } else {
                stringBuilder.append('1');
            }
        }
        return stringBuilder.toString();
   }

   public void divide(String dividend, String divisor) {

      int divisorLength = divisor.length();
        int dividendLength = dividend.length();

        while (dividendLength >= divisorLength) {
            String temp;
            if (dividend.charAt(0) == '1')
                temp = xor(divisor, dividend.substring(0, divisorLength));
            else
                temp = dividend.substring(0, divisorLength);
            dividend = temp.substring(1) + dividend.substring(divisorLength);
            dividendLength -= 1;
        }

      remainder = new StringBuilder(dividend);
   }
}

public class Main {

   public static void main(String[] args) throws IOException {
         
         CRC intro = new CRC();
      try {

         BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
         String data;

         System.out.print("Enter Sent Data :");
         data = reader.readLine();

         CRC checker = new CRC(data);

         checker.generateDataword();
         
         checker.divide(checker.dataword.toString(), checker.genPoly);
         System.out.println("\nCodeWord : "+(data+checker.remainder));
         
         System.out.print("\n\nEnter Received Data :");
         data = reader.readLine();

         checker.divide(data, checker.genPoly);

         System.out.println("\nRemainder = "+checker.remainder);

         if(Long.parseLong(checker.remainder.toString()) == 0) {
            System.out.println("\nNo Error.!");
         }
         else{
            System.out.println("\nError.!");
         }
      
      }

      catch (Exception e) {
         System.out.println(e);
      }
      
   }
}