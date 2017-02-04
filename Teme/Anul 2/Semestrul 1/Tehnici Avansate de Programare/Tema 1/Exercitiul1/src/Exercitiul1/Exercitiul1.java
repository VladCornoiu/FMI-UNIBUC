/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Exercitiul1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import static Exercitiul1.CosineSimilarity.CosineTextSimilarity;
/**
 *
 * @author Vlad Cornoiu
 */

public class Exercitiul1 {

    /**
     * @param args the command line arguments
     */
    
    private static ArrayList<String> firstFileWords = new ArrayList<>();
    private static ArrayList<String> secondFileWords = new ArrayList<>();
    public static void main(String[] args) {
        
        Scanner scanner1 = null;
        Scanner scanner2 = null;
        try {
            scanner1 = new Scanner(new BufferedReader(new FileReader("F1.txt"))).useDelimiter("[^a-zA-Z0-9]");
            scanner2 = new Scanner(new BufferedReader(new FileReader("F2.txt"))).useDelimiter("[^a-zA-Z0-9]");
        }
        catch(FileNotFoundException e) {
            System.err.println("File was not found");
        }
        finally {
            scanner1.close();
            scanner2.close();
        }
        
        while(scanner1.hasNext()) {
            firstFileWords.add(scanner1.next());
        }
        while(scanner2.hasNext()) {
            secondFileWords.add(scanner2.next());
        }
        System.out.println(CosineTextSimilarity(firstFileWords, secondFileWords));
    }
}
