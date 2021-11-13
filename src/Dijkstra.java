import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Dijkstra 
{
	public static void main(String[] args) 
	{
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();

		Vertex arad  	 = new Vertex("Arad");
		Vertex zerind    = new Vertex("Zerind");
		Vertex sibiu  	 = new Vertex("Sibiu");
		Vertex timisoara = new Vertex("Timisoara");
		arad.connect(zerind, 75);
		arad.connect(sibiu, 140);
		arad.connect(timisoara, 118);
		
		vertices.add(arad);
		vertices.add(zerind);
		vertices.add(sibiu);
		vertices.add(timisoara);
		
		Graph romeniaMap = new Graph(vertices);
		
		BestPaths result = dijkstra(romeniaMap, arad);
	}
	
	private static BestPaths dijkstra(Graph graph, Vertex source)
	{
		HashSet<Vertex> Q = new HashSet<Vertex>();
		int[] dist = new int[256];
		Vertex[] prev = new Vertex[256];
		
		for(int i = 0; i < 256; i++)
		{
			dist[i] = Integer.MAX_VALUE; //2,147,483,647
			prev[i] = null;			
		}
		
		for(Vertex v : graph.getVertices())
		{		
			Q.add(v);
		}

		int sourceIndex = source.getName().charAt(0);
		dist[sourceIndex] = 0;
		
		while(!Q.isEmpty())
		{			
			Vertex u = minDistVertex(Q, dist);
			
			Q.remove(u);
			
			ArrayList<Vertex> neighbors = u.getNeighbors();
			for(Vertex v : neighbors)
			{
				if(Q.contains(v))
				{
					int uIndex = u.getName().charAt(0);
					int vIndex = v.getName().charAt(0);
					
					int alt = dist[uIndex] + u.length(v);
					if(alt < dist[vIndex])
					{
						dist[vIndex] = alt;
						prev[vIndex] = u;
					}
				}
			}
		}
		
		BestPaths result = new BestPaths();
		result.dist = dist;
		result.prev = prev;
		
		return result;
	}

	private static Vertex minDistVertex(HashSet<Vertex> Q, int[] dist) {
		Vertex result = null;
		int minimumDistance = Integer.MAX_VALUE;
		
		for(Vertex v : Q)
		{
			int vIndex = v.getName().charAt(0);
			if(dist[vIndex] < minimumDistance)
			{
				result = v;
				minimumDistance = dist[vIndex];
			}
		}
		return result;
	}
}
