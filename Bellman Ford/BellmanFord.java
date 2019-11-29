import java.util.*;

public class BellmanFord {

	int MAX_VALUE = 99;
	int noOfVertices;
	int[] dist;

	public BellmanFord(int noOfVertices) {
		this.noOfVertices = noOfVertices;
		dist = new int[noOfVertices+1];
	}

	public void FindShortestPath(int[][] graph, int src) {

		for(int i = 1; i <= noOfVertices; i++)
			dist[i] = MAX_VALUE;

		dist[src] = 0;

		for(int k = 1; k <= noOfVertices; k++) {
			for(int i = 1; i <= noOfVertices; i++) {
				for(int j = 1; j <= noOfVertices; j++){
					if(graph[i][j] != MAX_VALUE) {
						if(dist[j] > dist[i] + graph[i][j])
							dist[j] = dist[i] + graph[i][j];
					}
				}
			}
		}


		for(int i = 1; i <= noOfVertices; i++) {
			for(int j = 1; j <= noOfVertices; j++) {
				if(graph[i][j] != MAX_VALUE){
					if(dist[j] > dist[i] + graph[i][j]){
						System.out.println("\n The Graph contains negative edge cycle.");
						return;
					}
				}
			}
		}

		for(int i = 1; i <= noOfVertices; i++) {
			System.out.printf("\n Distance from source %d to %d is %d",src,i,dist[i]);
		}
	}

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		try {
			System.out.print("\n Enter the number of vertices: ");
			int noOfVertices = in.nextInt();

			BellmanFord bellmanford = new BellmanFord(noOfVertices);
			int[][] graph = new int[noOfVertices+1][noOfVertices+1];

			System.out.println("\n Enter graph weight matrix: ");
			for(int i = 1; i <= noOfVertices; i++){
				for(int j = 1; j <= noOfVertices; j++){
					graph[i][j] = in.nextInt();
					if(i == j){
						graph[i][j] = 0;
						 continue;
					}
					if(graph[i][j] == 0)
						graph[i][j] = bellmanford.MAX_VALUE;
				}
			}

			System.out.print("\n Enter the source vertex: ");
			int src = in.nextInt();

			bellmanford.FindShortestPath(graph,src);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
