import java.util.*;

public class LeakyBucket {


	public static void main(String[] args) {
		
		try{	

			int pSize = 0, drop = 0, minSize = 0, i;

			Scanner in = new Scanner(System.in);
			System.out.print("Enter Bucket Size: ");
			int capacity = in.nextInt();

			System.out.print("Enter Operation Rate: ");
			int rate = in.nextInt();

			System.out.print("Enter the no.of seconds to Stimulate: ");
			int sec = in.nextInt();

			int[] packet = new int[sec];

			for(i = 0; i < sec; i++){
				System.out.printf("Enter the size of packet entering at %d sec: ",(i+1));
				packet[i] = in.nextInt();
			}

			System.out.println("\nSecond | Packet Recieved | Packet Sent | Packet Left | Packet Dropped\n");

			for(i = 0; i < sec; i++){
				pSize += packet[i];
				if(pSize > capacity){
					drop = pSize - capacity - rate;
					//pSize = capacity;
				}

				System.out.print(i+1+"\t\t"+packet[i]);
				minSize = Math.min(pSize,rate);
				System.out.print("\t\t"+minSize);
				pSize = Math.min(pSize - minSize - drop, capacity);
				System.out.print("\t\t"+pSize);
				System.out.print("\t\t"+drop+"\n");
				drop = 0;
			}
			while(pSize != 0){
				if(pSize > capacity){
					drop = pSize - capacity - rate;
					//pSize = capacity;
				}
				System.out.print(i+1+"\t\t0");
				minSize = Math.min(pSize,rate);
				System.out.print("\t\t"+minSize);
				pSize = Math.min(pSize - minSize - drop, capacity);
				System.out.print("\t\t"+pSize);
				System.out.print("\t\t"+drop+"\n");
				i++;
			}
		
		}	
		catch(Exception e){
			System.out.println(e);
		}
	}
}