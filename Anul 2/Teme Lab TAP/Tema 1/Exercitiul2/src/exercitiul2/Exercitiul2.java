/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercitiul2;

import java.io.*;
import java.util.Scanner;
/**
 *
 * @author Vlad Cornoiu
 */
public class Exercitiul2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = null;
        int numberOfLines, startNode;
        int i;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader("date.txt")));
        }
        catch(FileNotFoundException e) {
            System.err.println("File was not found");
        }
        finally {
            scanner.close();
        }
        
        UndirectedGraph graph = new UndirectedGraph(scanner.nextInt(), numberOfLines = scanner.nextInt());
        for (i = 0; i < numberOfLines; ++i) {
            graph.AddEdge(scanner.nextInt(), scanner.nextInt());
        }
        startNode = scanner.nextInt();
            
        graph.PrintAdjacentLists();
        graph.BreadthFirstSearch(startNode);
            
        MyQueue<Integer> queue1 = new MyQueue<>(5);
        MyQueue<Integer> queue4 = new MyQueue<>(7);
        MyQueue<Integer> queue5 = new MyQueue<>(3);
        
        
        MyQueue<String> queue2 = new MyQueue<>(10);
        MyQueue<MyQueue<Integer>> queue3 = new MyQueue<>(8);
        
        queue1.add(1);
        queue1.add(5);
        queue1.add(77);
        queue1.add(3);
        queue1.remove();
        queue1.add(15);
        queue1.add(12);
        
        queue4.add(7);
        queue4.add(23);
        queue4.add(22);
        queue4.remove();
        
        queue5.add(412);
        queue5.remove();
        queue5.add(234);
        queue5.add(22);
        
        queue2.add("DoiZece");
        queue2.add("123asd");
        queue2.add("Aidemine");
        queue2.remove();
        queue2.remove();
        queue2.add("Show");
        
        queue3.add(queue1);
        queue3.remove();
        queue3.add(queue4);
        queue3.add(queue5);
        queue3.remove();
        queue3.remove();
        
        
    }  
}
