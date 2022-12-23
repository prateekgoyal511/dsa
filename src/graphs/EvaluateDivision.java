package graphs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

class EvaluateDivision {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        //Each entry holds a string node. It can have multiple edges. An edge from a->b represents, we know
        //the value of a/b. The value can be inferred from double value in Edge class.
        Map<String, Map<String, Double>> adjMap = new HashMap<>();
        populateMap(equations, values, adjMap);
        System.out.println(adjMap);

        int n = queries.size();
        double[] queryAnswers = new double[n];
        //Iterate over queries.
        int i=0;
        for(List<String> query: queries) {
            queryAnswers[i] = evaluateQuery(query, adjMap);
            i++;
        }
        return queryAnswers;
    }

    private void populateMap(List<List<String>> equations, double[] values, Map<String, Map<String, Double>> adjMap) {
        int i=0;
        for(List<String> equation: equations) {
            addEdgesForEquation(equation, values[i], adjMap);
            i++;
        }
    }

    private void addEdgesForEquation(List<String> equation, double value, Map<String, Map<String, Double>> adjMap) {
        String first = equation.get(0);
        String second = equation.get(1);
        addCrossEdge(first, second, value, adjMap);
        addCrossEdge(second, first, 1/value, adjMap);
    }

    private void addCrossEdge(String node1, String node2, double value, Map<String, Map<String, Double>> adjMap){
        Map<String, Double> edgeMap;
        if(adjMap.containsKey(node1)) {
            edgeMap = adjMap.get(node1);
        } else {
            edgeMap = new HashMap<>();
        }
        edgeMap.put(node2, value);
        adjMap.put(node1, edgeMap);
    }

    private double evaluateQuery(List<String> query, Map<String, Map<String, Double>> adjMap) {
        String src = query.get(0);
        String dst = query.get(1);
        if(!adjMap.containsKey(src)) return -1;
        if(!adjMap.containsKey(dst)) return -1;
        if(src.equals(dst)) return 1;

        Stack<String> path = new Stack<String>();
        Map<String, Boolean> visited = new HashMap<String, Boolean>();
        boolean pathFound = dfsUtil(adjMap, src, dst, path, visited);
        if(!pathFound) return -1;
        return evaluateAnswerFromPathStack(adjMap, path);
    }

    private boolean dfsUtil(Map<String, Map<String, Double>> adjMap, String src, String dst, Stack<String> path, Map<String, Boolean> visited) {
        visited.put(src, true);
        path.push(src);
        if(src.equals(dst)) return true;
        Map<String, Double> edges = adjMap.get(src);
        for(String node: edges.keySet()) {
            if(!visited.containsKey(node) || !visited.get(node)) {
                if(dfsUtil(adjMap, node, dst, path, visited)) return true;
            }
        }
        path.pop();
        return false;
    }

    private double evaluateAnswerFromPathStack(Map<String, Map<String, Double>> adjMap, Stack<String> path) {
        String dst = path.pop();
        String src;
        double edgeVal;
        double ans = 1;
        while(!path.isEmpty()) {
            src = path.pop();
            edgeVal = adjMap.get(src).get(dst);
            ans = ans*edgeVal;
            dst = src;
        }
        return ans;
    }
}
