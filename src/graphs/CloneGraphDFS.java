package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}


class CloneGraphDFS {

    public Node cloneGraph(Node node) {
        if(node == null)return node;
        //We won't need visited here since nodeMap will serve as visited/not visited also.'
        Map<Node, Node> nodeMap = new HashMap<Node, Node>();
        Node result = dfsUtil(node, nodeMap);
        return result;
    }

    private Node dfsUtil(Node node, Map<Node, Node> nodeMap) {
        //As soon as we visit a node, we clone the node.
        Node cloneNode = new Node(node.val);
        nodeMap.put(node, cloneNode);

        Node cloneNeighborHead;
        List<Node> neighbors = node.neighbors;
        //As we iterate through node's neighbours, we want our cloneNode also to have same neighbours. If the clone of neighbour exists => simply add that edge. If it does not, then do dfs for that neighbour. While doing dfs, a clone graph will be constructed with that neighbour's clone as root. We can add that neighbour's clone to our clone node's neighbours.'
        for(Node neighbor: neighbors) {
            if(!nodeMap.containsKey(neighbor)) {
                cloneNeighborHead = dfsUtil(neighbor, nodeMap);
                cloneNode.neighbors.add(cloneNeighborHead);
            } else {
                cloneNeighborHead = nodeMap.get(neighbor);
                cloneNode.neighbors.add(cloneNeighborHead);
            }
        }
        return cloneNode;
    }
}
