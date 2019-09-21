import java.util.*;

/**
 * 图算法
 * 
 * @author zzy
 *
 */
public class Algorithm_Graph {

	public static int vertices = 6;
	public static int[][] edges = { { 0, 1 }, { 0, 3 }, { 0, 4 }, { 1, 4 }, { 2, 3 }, { 2, 5 },
			{ 3, 4 } };
	public static int[][] weightedEdges = { { 0, 1, 4 }, { 0, 3, 5 }, { 0, 4, 2 }, { 1, 4, 10 },
			{ 2, 3, 3 }, { 2, 5, 1 }, { 3, 4, 7 } };

	public static void main(String[] args) {
		int[][] adjMatrix = edgeList2adjMatrix(edges, vertices);
		printIntMatrix(adjMatrix);
		double[][] distMatrix = weightedEdgeList2distMatrix(weightedEdges, vertices);
		// 最短路径问题
		double[][] shortestPath = new double[vertices][vertices];
		for (int i = 0; i < vertices; i++) {
			shortestPath[i] = Dijkstra(i, distMatrix);
		}
		printDoubleMatrix(shortestPath);
		for (int i = 0; i < vertices; i++) {
			shortestPath[i] = BellmanFord(i, distMatrix);
		}
		printDoubleMatrix(shortestPath);
		shortestPath = FloydWarshall(distMatrix);
		printDoubleMatrix(shortestPath);
		// 最小生成树
		double[][] miniSpanTree = new double[vertices][vertices];
		miniSpanTree = Prim(0, distMatrix);
		printDoubleMatrix(miniSpanTree);
		miniSpanTree = Kruskal(0, distMatrix);
		printDoubleMatrix(miniSpanTree);
	}

	/******************** 旅行商问题（遍历完所有的点而可以重复） ********************/

	/******************** 邮递员问题（遍历完所有的边而可以重复） ********************/

	/******************** 网络流 ********************/

	/******************** 最小生成树 ********************/

	/**
	 * 普里姆算法
	 */
	public static double[][] Prim(int start, double[][] distMatrix) {
		ArrayList<Integer> closed = new ArrayList<>(); // 已访问的节点
		closed.add(start);
		double[][] miniSpanTree = new double[vertices][vertices]; // 最小生成树
		while (closed.size() < vertices) { // 遍历完所有节点后退出循环
			double minDistance = Double.POSITIVE_INFINITY;
			int from = -1;
			int to = -1;
			// 在所有已达节点的边中找到最短边
			for (int i : closed) {
				for (int j = 0; j < vertices; j++) {
					if (!closed.contains(j) && 0 < distMatrix[i][j]
							&& distMatrix[i][j] < minDistance) {
						from = i;
						to = j;
						minDistance = distMatrix[i][j];
					}
				}
			}
			miniSpanTree[from][to] = minDistance;
			miniSpanTree[to][from] = minDistance;
			closed.add(to);
		}
		return miniSpanTree;
	}

	/**
	 * 克鲁斯克尔算法
	 */
	public static double[][] Kruskal(int start, double[][] distMatrix) {
		double[][] miniSpanTree = new double[vertices][vertices]; // 最小生成树
		for (int[] edge : getSortedEdge(weightedEdges)) { // 把边按权重排序取出
			int u = edge[0];
			int v = edge[1];
			if (!inSameTree(u, v, miniSpanTree)) { // 如果两个节点在同一棵树中则构成环
				miniSpanTree[u][v] = edge[2];
				miniSpanTree[v][u] = edge[2];
			}
		}
		return miniSpanTree;
	}

	/******************** 图的遍历 ********************/

	/**
	 * 广度优先搜索
	 */
	public static void BFS(int n, int[][] adjMatrix) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(n);
		boolean[] closed = new boolean[vertices];
		closed[n] = true;
		while (!queue.isEmpty()) {
			n = queue.poll();
			System.out.print(n + " ");
			for (int k = 0; k < vertices; k++) {
				if (adjMatrix[n][k] == 1 && !closed[k]) {
					queue.add(k);
					closed[k] = true;
				}
			}
		}
	}

	/**
	 * 深度优先搜索
	 */
	public static void DFS1(int n, int[][] adjMatrix) {
		Stack<Integer> stack = new Stack<>();
		stack.add(n);
		boolean[] closed = new boolean[vertices];
		closed[n] = true;
		while (!stack.isEmpty()) {
			n = stack.pop();
			System.out.print(n + " ");
			for (int k = 0; k < vertices; k++) {
				if (adjMatrix[n][k] == 1 && !closed[k]) {
					stack.push(k);
					closed[k] = true;
				}
			}
		}
	}

	/**
	 * 深度优先搜索（递归）
	 */
	public static void DFS2(int n, int[][] adjMatrix) {
		boolean[] visited = new boolean[vertices];
		DFSRecusion(n, adjMatrix, visited);
	}

	/**
	 * 深度优先搜索（递归）
	 */
	public static void DFSRecusion(int n, int[][] adjMatrix, boolean[] visited) {
		visited[n] = true;
		System.out.print(n + " ");
		for (int k = 0; k < vertices; k++) {
			if (adjMatrix[n][k] == 1 && !visited[k]) {
				DFSRecusion(k, adjMatrix, visited);
			}
		}
	}

	/******************** 最短路径 ********************/

	/**
	 * 迪杰斯特拉算法（从起点到任意一点的最短路径）
	 */
	public static double[] Dijkstra(int start, double[][] distMatrix) {
		double[] distance = new double[vertices]; // 从起点到各个节点的距离
		for (int k = 0; k < vertices; k++) {
			if (k == start) distance[k] = 0;
			else distance[k] = Double.POSITIVE_INFINITY;
		}
		boolean[] closed = new boolean[vertices]; // 已访问的节点
		for (int i = 0; i < vertices; i++) {
			// 找出距离当前节点最近的节点 u
			double min = Double.MAX_VALUE;
			int u = -1; // 目标节点 u 的索引
			for (int j = 0; j < distance.length; j++) {
				if (!closed[j] && distance[j] < min) {
					min = distance[j];
					u = j;
				}
			}
			closed[u] = true;
			// 对 u 的出边进行松弛操作
			for (int v = 0; v < vertices; v++) {
				if (!closed[v] && distMatrix[u][v] != Double.POSITIVE_INFINITY) {
					if (distance[u] + distMatrix[u][v] < distance[v]) {
						distance[v] = distance[u] + distMatrix[u][v];
					}
				}
			}
		}
		return distance;
	}

	/**
	 * 贝尔曼-福特算法（从起点到任意一点的最短路径）
	 */
	public static double[] BellmanFord(int start, double[][] distMatrix) {
		double[] distance = new double[vertices]; // 从起点到各个节点的距离
		for (int k = 0; k < vertices; k++) {
			if (k == start) distance[k] = 0;
			else distance[k] = Double.POSITIVE_INFINITY;
		}
		for (int i = 0; i < vertices; i++) {
			// 对所有边进行松弛操作
			for (int u = 0; u < vertices; u++) {
				for (int v = 0; v < vertices; v++) {
					if (distMatrix[u][v] != Double.POSITIVE_INFINITY) {
						if (distance[u] + distMatrix[u][v] < distance[v]) {
							distance[v] = distance[u] + distMatrix[u][v];
						}
					}
				}
			}
		}
		// 检验负权值
		for (int u = 0; u < vertices; u++) {
			for (int v = 0; v < vertices; v++) {
				if (distMatrix[u][v] > 0 && distMatrix[u][v] != Double.POSITIVE_INFINITY) {
					if (distance[u] + distMatrix[u][v] < distance[v]) {
						System.out.println("图中包含负权回路");
					}
				}
			}
		}
		return distance;
	}

	/**
	 * 弗洛伊德算法（任意两点最短路径）
	 */
	public static double[][] FloydWarshall(double[][] distMatrix) {
		for (int k = 0; k < vertices; k++) { // 中转点
			for (int u = 0; u < vertices; u++) { // 起点
				for (int v = 0; v < vertices; v++) { // 终点
					if (distMatrix[u][k] + distMatrix[k][v] < distMatrix[u][v]) {
						distMatrix[u][v] = distMatrix[u][k] + distMatrix[k][v];
					}
				}
			}
		}
		return distMatrix;
	}

	/******************** 图的转换 ********************/

	/**
	 * 边缘列表转邻接矩阵（无向无权图）
	 */
	public static int[][] edgeList2adjMatrix(int[][] edges, int vertices) {
		int[][] adjMatrix = new int[vertices][vertices];
		for (int[] edge : edges) {
			// 无权无向图
			adjMatrix[edge[0]][edge[1]] = 1;
			adjMatrix[edge[1]][edge[0]] = 1;
		}
		return adjMatrix;
	}

	/**
	 * 带权边缘列表转距离矩阵（无向带权图）
	 */
	public static double[][] weightedEdgeList2distMatrix(int[][] edges, int vertices) {
		double[][] distMatrix = new double[vertices][vertices];
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				if (i != j) distMatrix[i][j] = Double.POSITIVE_INFINITY;
			}
		}
		for (int[] edge : edges) {
			distMatrix[edge[0]][edge[1]] = edge[2];
			distMatrix[edge[1]][edge[0]] = edge[2];
		}
		return distMatrix;
	}

	/******************** 辅助方法 ********************/

	/**
	 * 打印整型矩阵
	 */
	public static void printIntMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.printf("%0$-3s", matrix[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * 打印浮点矩阵
	 */
	public static void printDoubleMatrix(double[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.printf("%0$-10s", matrix[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * 根据权重排序边界
	 */
	public static int[][] getSortedEdge(int[][] weightedEdges) {
		Arrays.sort(weightedEdges, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		return weightedEdges;
	}

	/**
	 * BFS检验两个节点是否在同一棵树上
	 */
	public static boolean inSameTree(int u, int v, double[][] matrix) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(u);
		boolean[] closed = new boolean[vertices];
		closed[u] = true;
		while (!queue.isEmpty()) {
			u = queue.poll();
			if (u == v) return true;
			for (int k = 0; k < vertices; k++) {
				if (matrix[u][k] > 0 && !closed[k]) {
					queue.add(k);
					closed[k] = true;
				}
			}
		}
		return false;
	}
}