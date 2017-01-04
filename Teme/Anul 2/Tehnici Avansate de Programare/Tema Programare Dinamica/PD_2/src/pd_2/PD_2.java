/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pd_2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Vlad Cornoiu
 */

class Cell
{
    int value, lin, col;
    
    Cell() {}
    
    Cell(int _value)
    {
        value = _value;
        lin = -1;
        col = -1;
    }
}

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
public class PD_2 {

    static int numberOfColumns = 0, numberOfRows = 0;
    static Cell[][] chessTable;
    static int[][] dp;
    static Stack<Position> sol = new Stack<>();
    public static void main(String[] args) {
    
        Scanner scanner = null;
        
        try
        {
            scanner = new Scanner(new BufferedReader(new FileReader("date.txt")));
            
            numberOfRows = scanner.nextInt();
            numberOfColumns = scanner.nextInt();
            
            chessTable = new Cell[numberOfRows][numberOfColumns];
            dp = new int[2][numberOfColumns];
            
            for (int i = 0; i < numberOfRows; ++i)
            {
                for (int j = 0; j < numberOfColumns; ++j)
                {
                    if (i == 0)
                    {
                        int number = scanner.nextInt();
                        Cell cell = new Cell(number);
                        chessTable[i][j] = cell;
                        if (j != 0) 
                        {
                            dp[0][j] = dp[0][j - 1] + chessTable[0][j].value;
                            chessTable[i][j].lin = i;
                            chessTable[i][j].col = j - 1;
                        }
                        else 
                            dp[0][j] = chessTable[0][j].value;
                    }
                    else
                    {
                        int number = scanner.nextInt();
                        Cell cell = new Cell(number);
                        chessTable[i][j] = cell;
                    }
                }
                
                if (i != 0)
                    computeDP(i);
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
        
        System.out.println(dp[0][numberOfColumns - 1]);
        ReconstructSolution();
    }

    private static void computeDP(int row)
    {
        for (int j = 0; j < numberOfColumns; ++j)
            if (j != 0)
            {
                if (dp[0][j] > dp[1][j - 1])
                {
                    dp[1][j] = dp[0][j] + chessTable[row][j].value;
                    chessTable[row][j].lin = row - 1;
                    chessTable[row][j].col = j;
                }
                else
                {
                    dp[1][j] = dp[1][j - 1] + chessTable[row][j].value;
                    chessTable[row][j].lin = row;
                    chessTable[row][j].col = j - 1;
                }
            }
            else
            {
                dp[1][j] = dp[0][j] + chessTable[row][j].value;
                chessTable[row][j].lin = row - 1;
                chessTable[row][j].col = j;
            }
        for (int j = 0; j < numberOfColumns; ++j)
           dp[0][j] = dp[1][j];
    }

    private static void ReconstructSolution() {
        int row = chessTable[numberOfRows - 1][numberOfColumns - 1].lin;
        int column = chessTable[numberOfRows - 1][numberOfColumns - 1].col;
        sol.push(new Position(numberOfRows - 1, numberOfColumns - 1));
        Position position = new Position(row, column);
        while(position.lin != -1 && position.col != -1)
        {
            sol.push(position);
            row = chessTable[row][column].lin;
            column = chessTable[row][column].col;
            position = new Position(row, column);
        }
        
        while(!sol.isEmpty())
            System.out.println(sol.peek().lin + 1 + " " + (sol.pop().col + 1));
    } 
}
