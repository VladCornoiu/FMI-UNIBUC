/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercitiul2;
import java.util.ArrayDeque;
import java.util.ArrayList;
/**
 *
 * @author Vlad Cornoiu
 */
public class UndirectedGraph {
    
    private int _numberOfVertices, _numberOfLines;
    ArrayList<Integer>[] adjacencyList;
    
    public UndirectedGraph(int numberOfVertices, int numberOfLines)
    {
        _numberOfVertices = numberOfVertices;
        _numberOfLines = numberOfLines;
        adjacencyList = new ArrayList[_numberOfVertices + 1];
        for (int i = 0; i < adjacencyList.length ; ++i) {
            adjacencyList[i] = new ArrayList<>();
        }
    }
    
    public void AddEdge(int vertexin, int vertexout) {
        adjacencyList[vertexin].add(vertexout);
        adjacencyList[vertexout].add(vertexin);
    }
    
    public void PrintAdjacentLists() {
        for (int i = 1; i <= _numberOfVertices; ++i) {
            System.out.print(i + ":");
            for (int adjacentNode : adjacencyList[i]) {
                System.out.print(" " + adjacentNode);
            }
            System.out.println();
        }
    }
    
    public void BreadthFirstSearch(int startNode) {
        MyQueue<Integer> queue = new MyQueue<>(_numberOfVertices + 1);
        //ArrayDeque<Integer> queue = new ArrayDeque<>(_numberOfVertices + 1);
        boolean[] visited = new boolean[_numberOfVertices + 1];
        int currentNode;
        queue.add(startNode);
        visited[startNode] = true;
        while(!queue.isEmpty())
        {
            currentNode = queue.remove();
            System.out.print(currentNode + " ");
            for (int adjacentNode : adjacencyList[currentNode]) {
                if (!visited[adjacentNode]) {
                    visited[adjacentNode] = true;
                    queue.add(adjacentNode);
                }
            }   
        }
    }
}
