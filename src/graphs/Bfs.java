package graphs;

//{ Driver Code Starts
// Initial Template for Java
import java.util.*;
import java.lang.*;
import java.io.*;
class Bfs {
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
                // adj.get(v).add(u);
            }
            BfsSolution obj = new BfsSolution();
            ArrayList<Integer> ans = obj.bfsOfGraph(V, adj);
            for (int i = 0; i < ans.size(); i++)
                System.out.print(ans.get(i) + " ");
            System.out.println();
        }
    }
}

// } Driver Code Ends


class BfsSolution {
    // Function to return Breadth First Traversal of given graph.
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        //In tree, how did we use to do BFS? We used to have a queue. We'd put root in queue. Then, we'd pull out a node.
        //As we pull out a node, we know that we have visited this one. So, we add it to result. We put all its children/neighbours
        //in the queue. We do this till the queue is empty.

        //However, since it is a graph, it may contain cycle. We'll need to take care of nodes that we have already visited.
        List<Boolean> visited = new ArrayList<Boolean>();
        for(int i=0; i<V; i++) {
            visited.add(false);
        }
        ArrayList<Integer> result = new ArrayList<Integer>();
        Queue<Integer> queue = new LinkedList<Integer>();
        visited.set(0, true);
        queue.add(0);
        Integer currentNode;
        while(!queue.isEmpty()) {
            currentNode = queue.poll();
            result.add(currentNode);
            //We should not set visited here. Because, it means till the time, we have not polled this number, we can put in
            //the queue again and again. Which will not be correct. Queue should only contain nodes that are yet to be visited.
            //Every node must be put in the queue only once.
            //visited.set(currentNode, true);
            // System.out.println("Setting " + currentNode + "to true");

            List<Integer> neighbours = adj.get(currentNode);
            for(Integer neighbour: neighbours) {
                // System.out.println("Neighbour " + neighbour + "visited value is: " + visited.get(neighbour));
                if(!visited.get(neighbour)) {
                    visited.set(neighbour, true);
                    queue.add(neighbour);
                }
            }
        }

        return result;
    }
}
