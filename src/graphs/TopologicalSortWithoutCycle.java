package graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//The question is - https://leetcode.com/problems/course-schedule-ii/submissions/841795630/
class TopologicalSortWithoutCycle {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        GraphV2 graph = new GraphV2(numCourses);
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

class GraphV2 {
    private int V;
    private ArrayList<ArrayList<Integer>> adj;

    public GraphV2(int V) {
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

        boolean[] visited = new boolean[V];
        Stack<Integer> topologicalOrder = new Stack<Integer>();
        int i;
        for(i=0; i<V; i++) {
            visited[i] = false;
        }

        for(i=0; i<V; i++) {
            if(!visited[i]) {
                topologicalSortUtil(i, topologicalOrder, visited);
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

    private void topologicalSortUtil(int node, Stack<Integer> stack, boolean[] visited) {
        visited[node] = true;
        List<Integer> neighbours = adj.get(node);
        int i;
        int neighbour;
        for(i=0; i<neighbours.size(); i++) {
            neighbour = neighbours.get(i);
            if(!visited[neighbour]) {
                topologicalSortUtil(neighbour, stack, visited);
            }
        }
        stack.push(node);
    }
}
