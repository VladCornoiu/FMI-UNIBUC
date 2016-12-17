/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package descompunere;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

/**
 *
 * @author Vlad Cornoiu
 */

class Pair
{
    int value;
    int index;
    
    Pair(int _value, int _index)
    {
        value = _value;
        index = _index;
    }
}

public class Descompunere {

    static final int NMAX = 100005;
    static int[] numbers = new int[NMAX];
    static ArrayList<Integer>[] arrays = new ArrayList[NMAX];
    
    
    public static void main(String[] args) {
        
        Scanner scanner = null;
        int numberOfNumbers = 0;
        
        try {
            scanner = new Scanner(new BufferedReader(new FileReader("date.txt")));
            
            numberOfNumbers = scanner.nextInt();
            
            for (int i = 1; i <= numberOfNumbers; ++i)
            {
                numbers[i] = scanner.nextInt();
            }
        }
        catch(FileNotFoundException e) {
            System.err.println("File was not found");
        }
        finally {
            scanner.close();
        }
        
        TreeSet<Pair> minimums = new TreeSet<>((Pair a, Pair b) -> a.value - b.value);
        int numberOfArrays = 0;
        
        for (int i = 1; i <= numberOfNumbers; ++i)
        {
            Pair current = new Pair(numbers[i], 0);
            Pair find = minimums.ceiling(current);
            
            if (find != null)
            {
                arrays[find.index].add(numbers[i]);
                minimums.remove(find);
                
                minimums.add(new Pair(numbers[i], find.index));
            }
            else
            {
                numberOfArrays++;
                arrays[numberOfArrays] = new ArrayList<Integer>();
                arrays[numberOfArrays].add(numbers[i]);
                minimums.add(new Pair(numbers[i], numberOfArrays));
            }
        }
        
        for (int i = 1; i <= numberOfArrays; ++i)
        {
            for (int j = 0 ; j < arrays[i].size(); ++j)
                System.out.print(arrays[i].get(j) + " ");
            System.out.println();
        }
    }
}
