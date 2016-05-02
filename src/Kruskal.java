import java.util.*;

public class Kruskal {

	public static void main(String[] args) {

	}

	public static int mst(Edge2[] list, int n) {
		Arrays.sort(list);
		
		dset trees = new dset(n);
		int numEdges = 0;
		int res = 0;
		
		// Consider edges in other
		for (int i = 0; i < list.length; i++) {
			// Try to put together these two trees.
			boolean merged = trees.union(list[i].v1, list[i].v2);
			if (!merged) continue;
			
			// Bookkeeping
			numEdges++;
			res += list[i].weight;
			if (numEdges == n - 1) break;
		}
		
		// -1 indicates no MST, so not connected
		return numEdges == n - 1 ? res : -1;
	}
}

class dset {
	public int[] parent;
	public int[] height;
	public int n;
	
	public dset(int size) {
		this.parent = new int[size];
		this.height = new int[size];
		for (int i = 0; i < size; i++) {
			parent[i] = i;
		}
	}
	
	public int find(int v) {
		if (parent[v] == v) return v;
		parent[v] = find(parent[v]);
		height[v] = 1;
		return parent[v];
	}
	
	public boolean union(int v1, int v2) {
		int p1 = find(v1);
		int p2 = find(v2);
		if (p1 == p2) return false;
		
		if (height[p2] < height[p2]) parent[p2] = p1;
		else if (height[p1] < height[p2]) parent[p1] = p2;
		else {
			parent[p2] = p1;
			height[p1]++;
		}
		return true;
	}
}

class Edge2 implements Comparable<Edge2> {
	int v1;
	int v2;
	int weight;
	
	public Edge2(int v1, int v2, int weight) {
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}
	
	public int compareTo(Edge2 other) {
		return this.weight - other.weight;
	}
}