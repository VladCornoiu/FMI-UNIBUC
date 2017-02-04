/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pd_1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author Vlad Cornoiu
 */

class Domino
{
    int first, last;
    
    Domino() {}
    
    Domino(int _first, int _last)
    {
        first = _first;
        last = _last;
    }
}
public class PD_1 {

    static int numberOfDominos = 0, maxim = -1, startPosition = -1, solutions = 0;
    static Domino[] dominos;
    static int[] best;
    static int[] pred;
    public static void main(String[] args) {
    
        Scanner scanner = null;
        
        try
        {
            scanner = new Scanner(new BufferedReader(new FileReader("date.txt")));
            numberOfDominos = scanner.nextInt();
            
            dominos = new Domino[numberOfDominos];
            best = new int[numberOfDominos];
            pred = new int[numberOfDominos];
            
            for (int i = 0; i < numberOfDominos; ++i)
            {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                Domino domino = new Domino(x, y);
                dominos[i] = domino;
            }
        }
        catch(FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            scanner.close();
        }
        
        FindLongestSubsequence();
        ReconstructSolution();
    }

    private static void FindLongestSubsequence() 
    {
        startPosition = -1;
        best[numberOfDominos - 1] = 1;
        pred[numberOfDominos - 1] = -1;
        for (int i = numberOfDominos - 2; i >= 0; --i)
        {
            best[i] = 1;
            pred[i] = -1;
            for (int j = i + 1; j < numberOfDominos; ++j)
                if (dominos[i].last == dominos[j].first && best[i] < best[j] + 1)
                {
                    best[i] = best[j] + 1;
                    pred[i] = j;
                    if (best[i] > maxim)
                    {
                        maxim = best[i];
                        startPosition = i;
                    }
                    else if (best[i] == maxim)
                        solutions++;
                }
        }
    }

    private static void ReconstructSolution() {
        int pos;
        pos = startPosition;
        while(pos != -1)
        {
            System.out.println(dominos[pos].first + " " + dominos[pos].last);
            pos = pred[pos];
        }
        System.out.println(solutions + 1);
    }
    
}
