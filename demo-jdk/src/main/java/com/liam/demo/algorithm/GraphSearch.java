package com.liam.demo.algorithm;

import java.util.*;

public class GraphSearch {

    private Map<Character, Node> graph;

    private class Node {
        int weight;
        char val;
        Node next;

        Node(char val, int weight) {
            this.weight = weight;
            this.val = val;
        }
    }

    /**
     * init graph data structure: adjacent table
     * use linked list array to store node and edge
     *
     * @param graphStr towns graph in string type, like AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
     */
    public void initGraph(String graphStr) {
        if (graphStr == null || graphStr.isEmpty()) {
            throw new RuntimeException("input parameter graph should not be null");
        }


        Map<Character, Node> graph = new HashMap<>();
        for (String path : graphStr.split(",")) {
            path = path.trim();
            if (path.length() != 3) {
                throw new RuntimeException("graph path length is not equal to 3, which is illegal");
            }

            char start = path.charAt(0);
            char end = path.charAt(1);
            Integer weight = Integer.valueOf(path.substring(2, 3));
            Node nextNode = new Node(end, weight);

            if (graph.get(start) == null) {
                graph.put(start, nextNode);
            } else {
                Node cursor = graph.get(start);
                while (cursor.next != null) {
                    cursor = cursor.next;
                }
                cursor.next = nextNode;
            }
        }

        this.graph = graph;
    }

    /**
     * get route distance
     * @param route route in string, like A-E-B-C-D
     * @return distance of route
     */
    public String distance(String route) {
        if (route == null || route.isEmpty()) {
            throw new RuntimeException("input param route should not be null");
        }

        String[] routeNodes = route.split("-");
        if (routeNodes.length < 2) {
            throw new RuntimeException("route should contains at least 2 nodes");
        }

        int distance  = findDistance(routeNodes, 0, 0);
        if (distance == 0) {
            return "NO SUCH ROUTE";
        }

        return String.valueOf(distance);
    }


    /**
     * find distance from start to end, DFS
     * @param routeNodes route nodes
     * @param startIndex start index in nodes
     * @param distance current distance in route
     * @return distance in route
     */
    private int findDistance(String[] routeNodes, int startIndex, int distance) {

        if (startIndex >= routeNodes.length - 1) {
            return distance;
        }

        Character start = routeNodes[startIndex].charAt(0);
        int endIndex = startIndex + 1;
        Character end = routeNodes[endIndex].charAt(0);

        Node cursor = graph.get(start);
        while (cursor != null) {
            if (cursor.val == end) {
                distance += cursor.weight;
                return findDistance(routeNodes, endIndex, distance);
            }
            cursor = cursor.next;
        }

        return 0;
    }


}
