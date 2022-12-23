package graphs;

import java.util.*;
//Problem - https://leetcode.com/problems/bus-routes/description/
//The solution gives TLE for some of the cases.
class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        //This map is supposed to hold graph details. Each key is a bus no => index in routes array. Value against that key is a list of buses. It indicates,
        //if I am on a bus1, what all buses can I get to next.
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        int i, j;
        int n = routes.length;
        int[] keyRoute;
        int[] candidateValueRoute;
        boolean edgeExists;

        if(source == target) return 0;

        for(i=0; i<n; i++) {
            keyRoute = routes[i];
            for(j=0; j<n; j++) {
                //i==j would mean both are same buses.
                if(i != j) {
                    candidateValueRoute = routes[j];
                    edgeExists = checkIfEdgeExists(routes[i], routes[j]);
                    if(edgeExists) {
                        addDirectedEdge(i, j, adjMap);
                    }
                }
            }
        }

        List<Integer> srcBuses = getBusesByStop(routes, source);
        List<Integer> dstBuses = getBusesByStop(routes, target);

        if(srcBuses.size() == 0) return -1;
        if(dstBuses.size() == 0) return -1;

        int candidateDistance;
        int minSoFar = Integer.MAX_VALUE;

        for(Integer bus: srcBuses) {
            candidateDistance = bfs(adjMap, bus, dstBuses);
            System.out.println("candidateDistance: " + candidateDistance);
            if(candidateDistance >= 0 && candidateDistance < minSoFar) {
                minSoFar = candidateDistance;
            }
        }

        if(minSoFar == Integer.MAX_VALUE) return -1;

        return (minSoFar+1);
    }

    private boolean checkIfEdgeExists(int[] route1, int[] route2) {
        int i;
        int n1 = route1.length;
        int busStopNo1, busStopNo2;
        int j;
        int n2 = route2.length;

        for(i=0; i<n1; i++) {
            busStopNo1 = route1[i];
            for(j=0; j<n2; j++) {
                busStopNo2 = route2[j];
                if(busStopNo1 == busStopNo2) {
                    return true;
                }
            }
        }
        return false;
    }

    private void addDirectedEdge(int srcNode, int dstNode, Map<Integer, List<Integer>> adjMap) {
        List<Integer> valueList;
        if(adjMap.containsKey(srcNode)) {
            valueList = adjMap.get(srcNode);
        } else {
            valueList = new ArrayList<>();
        }
        valueList.add(dstNode);
        adjMap.put(srcNode, valueList);
    }

    private List<Integer> getBusesByStop(int[][] routes, int stop) {
        int i;
        int n = routes.length;
        int[] route;
        List<Integer> result = new ArrayList<>();
        int m;
        int j;
        int candidateStop;
        for(i=0; i<n; i++) {
            route = routes[i];
            m = route.length;
            for(j=0; j<m; j++) {
                candidateStop = route[j];
                if(candidateStop == stop) {
                    result.add(i);
                }
            }
        }
        return result;
    }

    private int bfs(Map<Integer, List<Integer>> adjMap, int src, List<Integer> dstBuses) {
        Map<Integer, Boolean> visited = new HashMap<>();
        Queue<QueueNode> queue = new LinkedList<>();
        visited.put(src, true);
        queue.add(new QueueNode(src, 0));
        QueueNode queueNode;
        List<Integer> neighbourBuses;
        while(!queue.isEmpty()) {
            queueNode = queue.poll();
            if(dstBuses.contains(queueNode.busNo)) return queueNode.dist;
            neighbourBuses = adjMap.get(queueNode.busNo);
            if(neighbourBuses == null || neighbourBuses.size() == 0) continue;
            for(Integer bus: neighbourBuses) {
                if(!visited.containsKey(bus) || !visited.get(bus)) {
                    visited.put(bus, true);
                    QueueNode queueNode2 = new QueueNode(bus, queueNode.dist+1);
                    queue.add(queueNode2);
                }
            }
        }
        return -1;
    }
}

class QueueNode {
    int busNo;
    int dist;

    public QueueNode(int busNo, int dist) {
        this.busNo = busNo;
        this.dist = dist;
    }
}
