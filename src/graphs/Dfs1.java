package graphs;

//{ Driver Code Starts
// Initial Template for Java
import java.util.*;
import java.lang.*;
import java.io.*;
class Dfs1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            String[] s = br.readLine().trim().split(" ");
            int V = Integer.parseInt(s[0]);
            int E = Integer.parseInt(s[1]);
            ArrayList<ArrayList<Integer>> adj =
                    new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i < V; i++) adj.add(new ArrayList<Integer>());
            for (int i = 0; i < E; i++) {
                String[] S = br.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            Dfs1Solution obj = new Dfs1Solution();
            ArrayList<Integer> ans = obj.dfsOfGraph(V, adj);
            for (int i = 0; i < ans.size(); i++)
                System.out.print(ans.get(i) + " ");
            System.out.println();
        }
    }
}

// } Driver Code Ends


class Dfs1Solution {
    // Function to return a list containing the DFS traversal of the graph.
    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        //Basically, we are given a list of lists. At 0th index, we may have 2,3,1. At 2th index, we'll have 4. At
        //4th index, we'll have null. At 3rd index, we'll have null. At 1st index, we'll have null.
        ArrayList<Integer> result = new ArrayList<Integer>();

        //We'll visit a node first. Then, we'll visit that node's first connection. Then, that first connection's first connection.
        //And so on. In a tree, for every node, we used to call for left and right child. Here, for every node, we'll call all
        //its neighbours. So, we'll have to iterate. In a tree, that node itself had the information about left and right child.
        //But, here, that information is there in adj. So, we'll have to pass node, adj both.

        //Since the graph is connected, we don't need to iterate for all vertices.
        //However, since it is an undirected graph. 0 is connected 2 means, 2 is connected to 0. So, we may go into loop.
        //We need to keep track of visited vertices.
        List<Boolean> visited = new ArrayList<Boolean>();
        for(int i=0; i<V; i++) {
            visited.add(false);
        }
        dfsOfGraphUtil(0, adj, result, visited);
        return result;
    }

    private void dfsOfGraphUtil(int node, ArrayList<ArrayList<Integer>> adj, List<Integer> result, List<Boolean> visited) {
        //Visit the current node
        result.add(node);
        visited.set(node, true);
        ArrayList<Integer> neighbours = adj.get(node);
        //Iterate over all neighbours from left to right.
        int neighbourNode;
        for(int i=0; i<neighbours.size(); i++) {
            neighbourNode = neighbours.get(i);
            if(!visited.get(neighbourNode)) {
                dfsOfGraphUtil(neighbourNode, adj, result, visited);
            }
        }
    }
}
