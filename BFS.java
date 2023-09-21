import java.util.*;
import java.nio.*;
import java.io.*;

public class BFS{
	public static void main(String[]args){
		boolean[][] matris = {  {false, true, true, false, true, false},
								{true, false, false, false, false, true},
								{true, false, false, true, true, false},
								{false, false, true, false,false,true},
								{true, false, true, false, false, true},
								{false, true, false, true, true, false}
							 };
		Graph myG = Graph.fromAdjacencyMatrix(matris);
		BFSalgorithm(myG, "1");
	}
	static void BFSalgorithm(Graph g, String vInicial){
		String inicialP = vInicial + "/";
		File vFile  = new File(inicialP);
		if(!vFile.mkdir()) System.out.println("error al crear directorio vInicial");
     	ArrayList<String> cola = new ArrayList<String>();
     	/* nos aseguramos que la cola está vacía */
		g.setV.get(vInicial).place = inicialP;
		g.setV.get(vInicial).visit();
     	cola.add(vInicial);
     	while (cola.size() > 0)
     	{
	        // extraemos el nodo u de la cola Q y exploramos todos sus nodos adyacentes
        	String cv = cola.get(0);
			cola.remove(0);
        	for(String v: g.setV.get(cv).adjacentWith())
        	{
				String parent = cv;
           		if(g.setV.get(v).notVisited())
           		{
					String cPlace = g.setV.get(cv).place +v + "/";
                	g.setV.get(v).visit();
					g.setV.get(v).place = cPlace;
                	//now create the carpet
					File f = new File(cPlace);
					if(!f.mkdir()) System.out.println("error al crear directorio " + cPlace);
                	cola.add(v);
           		}
        	}
     	}
	}
}
class Graph{
	class Vertice{
		private String label;
		private ArrayList<String> adjacentWith = new ArrayList<>();
		private boolean notVisited;
		String place;
		Vertice(String label){
			this.label = label;
			notVisited = true;
		}
		public void visit(){
			notVisited = false;
		}
		public void isAdjacentWith(String v){
			adjacentWith.add(v);
		}
		public boolean notVisited(){return notVisited;};//paz im sorry
		public ArrayList<String> adjacentWith(){return adjacentWith;};
	}

	HashMap<String, Vertice> setV = new HashMap<String, Vertice>();
	//2DO: Learn TreeMaps!!
	private Graph(boolean[][] matrix){
		int l = matrix.length;
		for(int i = 1; i<= l; i++){
			String cv = ""+i;
			setV.put(cv, new Vertice("" +i));
			for(int e = 1; e <= l; e++){
				if(matrix[i-1][e-1]) setV.get(cv).isAdjacentWith(""+e);
			}
		}
	}
	public static Graph fromAdjacencyMatrix(boolean[][] matrix){
		return(isSquare(matrix))? new Graph(matrix) : null;
	}
	private static boolean isSquare(boolean[][] m){
		int size = m.length;
		for(boolean[] a : m){
			if(a.length != size) return false;
		}
		return true;
	}
}

