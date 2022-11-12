package graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Basic philosophy of detecting a cycle

/*
Use the following approach: consider we have three colors, and each vertex should be painted with one of these colors. "White color" means that the vertex hasn't been visited yet. "Gray" means that we've visited the vertex but haven't visited all vertices in its subtree. "Black" means we've visited all vertices in subtree and left the vertex. So, initially all vertices are white. When we visit the vertex, we should paint it gray. When we leave the vertex (that is we are at the end of the dfs() function, after going throung all edges from the vertex), we should paint it black. If you use that approach, you just need to change dfs() a little bit. Assume we are going to walk through the edge v->u. If u is white, go there. If u is black, don't do anything. If u is gray, you've found the cycle because you haven't left u yet (it's gray, not black), but you come there one more time after walking throung some path.
To keep vertices' colors replace boolean array with char or integer array and just use values in range [0..2].
 */
class TopologicalSortWithDetectingCycle {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        GraphV3 graph = new GraphV3(numCourses);
        int[] singlePrerequisite;
        for(int i=0; i<prerequisites.length; i++) {
            singlePrerequisite = prerequisites[i];
            //Note that it says, to take first course, you need to take second course prior. That means, first
            //course is dependent on second course. That means, edge will be from second course to first course.
            graph.addEdge(singlePrerequisite[1], singlePrerequisite[0]);
        }

        return graph.topologicalSort();
    }
}

class GraphV3 {
    private int V;
    private ArrayList<ArrayList<Integer>> adj;

    public GraphV3(int V) {
        this.V = V;
        adj = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<V; i++) {
            adj.add(new ArrayList<Integer>());
        }
    }

    public void addEdge(int source, int dest) {
        adj.get(source).add(dest);
    }

    public int[] topologicalSort() {
        //Topological sort means => if there is an edge from A to B. That means, B is dependent on A. Hence, A
        //should come prior to it.
        //So, if we consider one single Connected component of a graph which does not have a cycle => if we do
        //DFS on this graph. Then, DFS of a dependent will always be completed first. So, as recursion of a node
        //is completed, we should put that in a list somewhere. At the end, root node will be completed.
        //So, it'll be added in the last. That means, we should have this as stack.


        //visited will have only 3 possible values - 0/1/2. 0 => we haven't visited it yet. 1 => we have visited
        //but haven't completed visiting all its subtree. 2 => we have visited this node and its subtree too.
        int[] visited = new int[V];
        Stack<Integer> topologicalOrder = new Stack<Integer>();
        int i;
        for(i=0; i<V; i++) {
            visited[i] = 0;
        }

        boolean checkForCycle;

        for(i=0; i<V; i++) {
            if(visited[i] == 0) {
                checkForCycle = topologicalSortUtil(i, topologicalOrder, visited);
                if(checkForCycle) return new int[0];
            }
        }

        int[] result = new int[V];
        i=0;
        while(!topologicalOrder.isEmpty()) {
            result[i] = topologicalOrder.pop();
            i++;
        }
        return result;
    }

    private boolean topologicalSortUtil(int node, Stack<Integer> stack, int[] visited) {
        visited[node] = 1;
        List<Integer> neighbours = adj.get(node);
        int i;
        int neighbour;
        for(i=0; i<neighbours.size(); i++) {
            neighbour = neighbours.get(i);
            if(visited[neighbour] == 0) {
                if(topologicalSortUtil(neighbour, stack, visited))return true;
            } else {
                if(visited[neighbour] == 1) return true;
            }
        }
        visited[node] = 2;
        stack.push(node);
        return false;
    }
}
