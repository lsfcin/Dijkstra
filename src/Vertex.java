import java.util.ArrayList;

public class Vertex {
	private String name;
	private ArrayList<Edge> edges;
	
	public Vertex(String name)
	{
		this.name = name;
		this.edges = new ArrayList<Edge>();
	}
	
	public String getName()
	{
		return name;
	}

	public ArrayList<Vertex> getNeighbors() {
		ArrayList<Vertex> neighbors = new ArrayList<Vertex>();
		
		for(Edge edge : edges)
		{
			if(edge.a.equals(this))
			{
				neighbors.add(edge.b);
			}
			else
			{
				neighbors.add(edge.a);
			}
		}
		
		return neighbors;
	}

	public int length(Vertex v) {
		
		int result = 0;
		for(Edge edge : edges)
		{
			if(edge.a.equals(v) || edge.b.equals(v)) 
			{
				result = edge.length;
			}
		}
		return result;
	}

	public void connect(Vertex that, int length) {
		Edge edge = new Edge(this, that, length);
		this.edges.add(edge);
		that.edges.add(edge);		
	}
}
