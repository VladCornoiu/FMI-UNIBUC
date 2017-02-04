/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pd_3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Vlad Cornoiu
 */

class Position
{
    int lin, col;
    
    Position() {}
    
    Position (int _lin, int _col)
    {
        lin = _lin;
        col = _col;
    }
}

public class PD_3 
{
    
    private static String s1;
    private static String s2;
    
    private static int cost1 = 0;
    private static int cost2 = 0;
    private static int cost3 = 0;
    
    private static int[][] sol;
    
    private static Stack<String> solution = new Stack<>();

    public static void main(String[] args) {
        
        Scanner scanner = null;
        
        try
        {
            scanner = new Scanner(new BufferedReader(new FileReader("date.txt")));
            
            s1 = scanner.nextLine();
            s2 = scanner.nextLine();
            
            cost1 = scanner.nextInt();
            cost2 = scanner.nextInt();
            cost3 = scanner.nextInt();     
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            scanner.close();
        }
        sol = new int[s2.length() + 1][s1.length() + 1];
        System.out.println(LevenshteinDistance(s1, s2));
        ReconstructSolution();
    }

    private static int LevenshteinDistance(String s1, String s2) 
    {
        if (s1 == s2) return 0;
        int[] v0 = new int[s1.length() + 1];
        int[] v1 = new int[s1.length() + 1];
        
        for (int i = 0; i <= s1.length(); ++i)
            v0[i] = i;
        
        for (int i = 0; i < s2.length(); ++i)
        {
            v1[0] = i + 1;
            
            for (int j = 0; j < s1.length(); ++j)
            {
                int cost = (s1.charAt(j) == s2.charAt(i)) ? 0 : cost3;
                int a = v1[j] + cost1;
                int b = v0[j + 1] + cost2;
                int c = v0[j] + cost;
                int minValue = Math.min(Math.min(a,b), c);
                v1[j + 1] = minValue;
                if (minValue == a)
                    sol[i + 1][j + 1] = 1;
                else if (minValue == b)
                    sol[i + 1][j + 1] = 2;
                else if (minValue == c && cost!= 0)
                    sol[i + 1][j + 1] = 3;
                else sol[i + 1][j + 1] = 4;
            }
            
            for (int j = 0; j <= s1.length(); ++j)
                v0[j] = v1[j];
        }
        
        return v1[s1.length()];
    }

    private static void ReconstructSolution() {
        int lin = s2.length();
        int col = s1.length();
        sol[0][1] = 1;
        sol[1][0] = 2;
        
        while(lin != 0 || col != 0)
        {
            if (lin != 0 && col !=0)
            {
                if (sol[lin][col] == 1)
                {
                    solution.push("Stergem " + s1.charAt(col - 1));
                    col--;
                }
                 else if (sol[lin][col] == 2)
                {
                    solution.push("Inseram " + s2.charAt(lin - 1));
                    lin--;
                }
                else if (sol[lin][col] == 3)
                {
                    solution.push("Inlocuim " + s1.charAt(col - 1) + "-" + s2.charAt(lin - 1));
                    lin--;
                    col--;
                }
                else
                {
                    solution.push("Pastram " + s1.charAt(col - 1));
                    lin--;
                    col--;
                }
            }
            else if (lin == 0)
            {
                solution.push("Stergem " + s1.charAt(col - 1));
                col--;
            }
            else if (col == 0)
            {
                solution.push("Inseram " + s2.charAt(lin - 1));
                lin--;
            }
        }
                
        while(!solution.isEmpty())
            System.out.println(solution.pop());
    }
}

