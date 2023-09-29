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
		BFSalgorithm(myG, "A");
	}
	static void BFSalgorithm(Graph g, String vInicial){
		String inicialP = vInicial + "/";
		File vFile  = new File(inicialP);
		System.out.println("Vector inicial: " + vInicial);
     	ArrayList<String> cola = new ArrayList<String>();
     	/* nos aseguramos que la cola está vacía */
		g.setV.get(vInicial).place = inicialP;
		g.setV.get(vInicial).visit();
		if(!vFile.mkdir()) System.out.println("creando " + inicialP);
		System.out.println("Añadiendo a la cola a " + vInicial);
     	cola.add(vInicial);
     	while (cola.size() > 0)
     	{
	        // extraemos el nodo u de la cola Q y exploramos todos sus nodos adyacentes
        	String cv = cola.get(0);
			cola.remove(0);
        	for(String v: g.setV.get(cv).adjacentWith())
        	{
				//String parent = cv;
           		if(g.setV.get(v).notVisited())
           		{
					String cPlace = g.setV.get(cv).place +v + "/";
                	g.setV.get(v).visit();
					g.setV.get(v).place = cPlace;
                	//now create the carpet
					File f = new File(cPlace);
					if(f.mkdir()) System.out.println("creando " + cPlace);
					System.out.println("Añadiendo a la cola a " + v);
                	cola.add(v);
           		}
        	}
     	}
	}
}
class Graph{
	public static char[] characters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
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
			System.out.println("visitando al nodo " + label);
			notVisited = false;
		}
		public void isAdjacentWith(String v){
			adjacentWith.add(v);
		}
		public boolean notVisited(){return notVisited;};//paz im sorry
		public String toString(){
			return label;
		}
		public ArrayList<String> adjacentWith(){return adjacentWith;};
	}

	public static String num2label(int id){
		String label = "";
		while(id / 26 > 0){
			label = characters[id] + label;
			id /= 26;
		}
		label = characters[id] + label;
		return label;
	}
	
	HashMap<String, Vertice> setV = new HashMap<String, Vertice>();
	//2DO: Learn TreeMaps!!
	private Graph(boolean[][] matrix){
		int l = matrix.length;
		for(int i = 0; i< l; i++){
			String cv = num2label(i);
			setV.put(cv, new Vertice(cv));
			for(int e = 0; e < l; e++){
				if(matrix[i][e]) setV.get(cv).isAdjacentWith(num2label(e));
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
	public static int[][] readMatriz(int x, int y){
		int[][] subject = new int[x][y];
		
		return subject;
	}
	public static boolean[][] toAdjacencyMatr(int[][] matris){
		int length = matris.length;
		boolean[][] subject = new boolean[length][length];
		for(int i = 0; i < length; i++){
			for(int e = 0; e < length; e++){
				subject[i][e] = (matris[i][e] > 0)? true : false;
			}
		}
		return subject;
	}
}

