/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pd_4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Vlad Cornoiu
 */
public class PD_4 {
    
    private static boolean[][] dp;
    private static List<Integer>[] vectors;
    private static int numberOfVectors = 0;
    private static int totalSum = 0;
    
    public static void main(String[] args) {
        Scanner scanner = null;
        
        try
        {
            scanner = new Scanner(new BufferedReader(new FileReader("date.txt")));
            
            numberOfVectors = scanner.nextInt();
            totalSum = scanner.nextInt();
            
            dp = new boolean[numberOfVectors + 1][totalSum + 1];
            dp[0][0] = true;
            vectors = new ArrayList[numberOfVectors + 1];
            
            scanner.nextLine();
            for (int i = 1; i <= numberOfVectors; ++i)
            {
                String[] tokens = scanner.nextLine().split(" ");
                vectors[i] = new ArrayList<>();
                
                for(String nrString : tokens)
                {
                    int number = Integer.parseInt(nrString);
                    vectors[i].add(number);
                    
                    for (int j = 0; j + number <= totalSum; ++j)
                        dp[i][j + number] |= (dp[i - 1][j] == true);
                }
            }
            
            ReconstructSolution();
        }
        catch(FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            scanner.close();
        }
    }

    private static void ReconstructSolution() {
        if (dp[numberOfVectors][totalSum] == false)
            System.out.println("There is no solution");
        else
        {
            int[] solution = new int[numberOfVectors];
            
            for (int i = numberOfVectors; i >= 1; --i)
                for (int j = 0; j < vectors[i].size(); ++j)
                {
                    int element = vectors[i].get(j);
                    if (totalSum >= element && dp[i - 1][totalSum - element] == true)
                    {
                        totalSum -= element;
                        solution[i - 1] = element;
                        break;
                    }
                }      
            for (int i = 0; i < numberOfVectors; ++i)
                System.out.print(solution[i] + " ");
            System.out.println();
        }
    }
    
}
