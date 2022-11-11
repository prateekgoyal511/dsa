package graphs;

//{ Driver Code Starts
import java.util.*;
import java.io.*;
import java.lang.*;

class DetectCycleInDirectedGraphDFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            int V = sc.nextInt();
            int E = sc.nextInt();
            for (int i = 0; i < V; i++)
                list.add(i, new ArrayList<Integer>());
            for (int i = 0; i < E; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                list.get(u).add(v);
            }
            if (new DetectCycleInDirectedGraphDFSSolution().isCyclic(V, list) == true)
                System.out.println("1");
            else
                System.out.println("0");
        }
    }
}
// } Driver Code Ends


/*Complete the function below*/

class DetectCycleInDirectedGraphDFSSolution {
    // Function to detect cycle in a directed graph.
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        // code here
        //In undirected graph, we could do DFS. Now, if we encountered any visited vertex as neighbour except parent
        //=> we thought it was a cycle. But, we can't apply the same to directed graph. Why?
        //Because, in directed graph, it is possible that we reach to a vertex in 2 different paths. That would not necessarily
        //make it a cycle. See -
        //https://stackoverflow.com/questions/10972028/finding-a-cycle-in-an-undirected-graph-vs-finding-one-in-a-directed-graph
        //So, what do we do. In directed graph, if we have started DFS from a vertex and we go 1 depth deeper in every recursive
        //call => if we encounter any neighbour which is already visited and which was visited in this very recursion stack =>
        //this is a cycle.
        //Note that it is possible that the neighbour is marked visited but it was not visited during this recursive call.
        //Then, this would not indicate a cycle.
        //That means, we have to track what all is part of current recursion stack. So, what do we do. As soon as recursive
        //call begins, we mark this vertex as part of recursive stack. And as it ends, we remove it from recursion stack.
        boolean[] visited = new boolean[V];
        boolean[] currentRecursionStack = new boolean[V];
        int i;
        for(i=0; i<V; i++) {
            visited[i] = false;
            currentRecursionStack[i] = false;
        }

        //Do DFS from all vertices. If any of these components say that they have a cycle, return true.
        for(i=0; i<V; i++) {
            if(!visited[i]) {
                if(isCyclicUtil(i, adj, visited, currentRecursionStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isCyclicUtil(int sourceNode, ArrayList<ArrayList<Integer>> adj, boolean[] visited, boolean[] currentRecursionStack) {
        visited[sourceNode] = true;
        //Recursive call for this node has begun => we can add it to recursion stack.
        currentRecursionStack[sourceNode] = true;

        ArrayList<Integer> neighbours = adj.get(sourceNode);
        int i;
        int neighbour;
        for(i=0; i<neighbours.size(); i++) {
            neighbour = neighbours.get(i);
            //If neighbour is not visited => good. Do DFS from neighbour and if it returns a cycle => return true
            if(!visited[neighbour]) {
                if(isCyclicUtil(neighbour, adj, visited, currentRecursionStack)) return true;
            } else {
                //If neighbour is already visited => check if it is part of currentRecursionStack
                if(currentRecursionStack[neighbour]) return true;
            }
        }
        //Now, recursive call for this node has ended. So, recursion stack for this call will go away.
        currentRecursionStack[sourceNode] = false;
        return false;
    }
}
