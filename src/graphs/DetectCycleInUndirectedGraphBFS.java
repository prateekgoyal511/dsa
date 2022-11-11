package graphs;

//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;
class DetectCycleInUndirectedGraphBFS {
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
            DetectCycleInUndirectedGraphBFSSolution obj = new DetectCycleInUndirectedGraphBFSSolution();
            boolean ans = obj.isCycle(V, adj);
            if (ans)
                System.out.println("1");
            else
                System.out.println("0");
        }
    }
}
// } Driver Code Ends


class DetectCycleInUndirectedGraphBFSSolution {
    // Function to detect cycle in an undirected graph.
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        //Detecting cycle using BFS.
        //In Breadth First Search, we first add children. Then, all children's children in the queue. Now, if while adding
        //an element in the queue, we find that this has already been visited => cycle. Because, ideally, node at this breadth should
        //be visited for the first time. But, while adding all children => we'll also get the parent. We shouldn't consider that
        //So, when we are finding all children of a node, we should also know who is its parent. When we are adding elements
        //in queue, we know who is the parent. So, we can either add this information in the queueNode or we can put this info
        //in a map.

        Map<Integer, Integer> parentMap = new HashMap<Integer, Integer>();
        int i;
        List<Boolean> visited = new ArrayList<Boolean>();
        for(i=0; i<V; i++) {
            visited.add(false);
        }
        for(i=0; i<V; i++) {
            //If we do BFS using this vertex and this component had a cycle => we return true.
            if(!visited.get(i)) {
                if(isCycleUtil(i, adj, visited, parentMap)) return true;
            }
        }
        //If none of the components had a cycle => no cycle was there.
        return false;
    }


    //This function takes in a source node. Does BFS on it. If there is a cycle => it returns.
    private boolean isCycleUtil(int node, ArrayList<ArrayList<Integer>> adj, List<Boolean> visited, Map<Integer, Integer> parentMap) {

        Queue<Integer> queue = new LinkedList<Integer>();
        //While putting in queue => we need to mark this as visited and also set its parent.
        parentMap.put(node, -1);
        visited.set(node, true);
        queue.add(node);


        while(!queue.isEmpty()) {
            Integer currentNode = queue.poll();
            List<Integer> neighbours = adj.get(currentNode);
            for(int i=0; i<neighbours.size(); i++) {
                int neighbour = neighbours.get(i);
                if(!visited.get(neighbour)) {
                    visited.set(neighbour, true);
                    queue.add(neighbour);
                    parentMap.put(neighbour, currentNode);
                } else if (parentMap.get(currentNode) != neighbour) return true;
            }
        }
        return false;
    }
}
