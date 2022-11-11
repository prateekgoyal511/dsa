package graphs;

//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;
class DetectCycleInUndirectedGraphDFS {
    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            String[] s = br.readLine().trim().split(" ");
            int V = Integer.parseInt(s[0]);
            int E = Integer.parseInt(s[1]);
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < V; i++) adj.add(i, new ArrayList<Integer>());
            for (int i = 0; i < E; i++) {
                String[] S = br.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            DetectCycleInUndirectedGraphDFSSolution obj = new DetectCycleInUndirectedGraphDFSSolution();
            boolean ans = obj.isCycle(V, adj);
            if (ans)
                System.out.println("1");
            else
                System.out.println("0");
        }
    }
}
// } Driver Code Ends


class DetectCycleInUndirectedGraphDFSSolution {
    // Function to detect cycle in an undirected graph.
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        //Detecting cycle using DFS
        //What is meant by Depth first search. If in DFS, I reach a node and I go for node
        //in next depth. That means, that next depth node has never been visited earlier,
        //This is what Depth first means. But, if it was visited => there has been a cycle.

        //How do I know next depth nodes. They are in adjacency list of the current node.
        //But, adjacency list also contains our parent node of current, which is obviously visited
        //But, that does not indicate cycle. So, we'll need to track of parent node as well.

        //We'll iterate through all components of the graph - beginning with every node.
        //If any component has a cycle and it returns true => we'll return true
        int i;
        List<Boolean> visited = new ArrayList<Boolean>();
        for(i=0; i<V; i++) {
            visited.add(false);
        }

        for(i=0; i<V; i++) {
            if(!visited.get(i)) {
                //If we do DFS using this vertex and we find that this component had a cycle
                //we return true.
                //We are passing -1 as parent as this vertex is the top of dfs traversal
                if(isCycleUtil(i, -1, adj, visited)) {
                    return true;
                }
            }
        }
        //If none of the components returned true => no cycle was present.
        return false;
    }

    //This function takes in a node. And runs DFS on it. It returns whether this component had a cycle
    private boolean isCycleUtil(int node, int parent, ArrayList<ArrayList<Integer>> adj, List<Boolean> visited) {
        visited.set(node, true);
        List<Integer> neighbours = adj.get(node);
        for(Integer neighbour: neighbours) {
            //If the neighbour is not yet visited, we'll run dfs.
            if(!visited.get(neighbour)) {
                //If this neighbour returns true => we return true
                if(isCycleUtil(neighbour, node, adj, visited)) return true;
            } else {
                //If this neighbour was already visited and it is not parent => cycle
                if(neighbour != parent) return true;
            }
        }

        //If no neighbour reported cycle => no cycle
        return false;
    }
}
