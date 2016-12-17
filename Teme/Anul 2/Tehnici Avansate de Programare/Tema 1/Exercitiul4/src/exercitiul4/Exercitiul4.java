/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercitiul4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author Vlad Cornoiu
 */
public class Exercitiul4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int scannedInt = 0;
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        
        Scanner scanner = null;
        
        try {
            scanner = new Scanner(new BufferedReader(new FileReader("date.txt")));
        }
        catch(FileNotFoundException e) {
            System.err.println("File was not found");
        }
        
        while(scanner.hasNextInt()) {
            scannedInt = scanner.nextInt();
            bst.Add(scannedInt, scannedInt);
        }
        
        System.out.println(bst.toString());
        
        BinarySearchTree<CompareClass1, Integer> bst2 = new BinarySearchTree<>();
        for (int i = 1; i <= 3; ++i)
            for (int j = 1; j <= 3; ++j) {
                if ((i + j) % 2 ==0) {
                    bst2.Add(new CompareClass2(i, j, i + j), i * j);
                }
                else {
                    bst2.Add(new CompareClass1(i, j), i * j);
                }
            }
        
        System.out.println(bst2.toString());
        
        BinarySearchTree<Integer, Integer> bst3 = new BinarySearchTree<>();
        BinarySearchTree<Integer, Integer> bst4 = new BinarySearchTree<>();
        
        bst3.Add(1,1);
        bst3.Add(1,3);
        bst3.Add(2,5);
        bst3.Add(4,6);
        bst3.Add(5,7);
        
        bst4.Add(1,1);
        bst4.Add(1,3);
        bst4.Add(5,7);
        bst4.Add(2,5);
        bst4.Add(4,6);
        
        BinarySearchTree<Integer, String> bst5 = new BinarySearchTree<>();
        BinarySearchTree<Integer, String> bst6 = new BinarySearchTree<>();
        
        bst5.Add(1, "Vlad");
        bst5.Add(2, "merge");
        bst5.Add(3, "la");
        bst5.Add(4, "magazin.");
        
        bst6.Add(1, "Vlad");
        bst6.Add(2, "merge");
        bst6.Add(3, "la");
        bst6.Add(4, "magazin.");
        
        System.out.println(bst5.hashCode());
        System.out.println(bst6.hashCode());
        
        System.out.println(bst3.hashCode());
        System.out.println(bst4.hashCode());
    }
}