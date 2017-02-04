/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dei_3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author Vlad Cornoiu
 */
public class DEI_3 {
    
    static int numberOfElementsFirstArray = 0, numberOfElementsSecondArray = 0;
    static int[] firstArray;
    static int[] secondArray;

    public static void main(String[] args) {
        Scanner scanner = null;
        
        try
        {
            scanner = new Scanner(new BufferedReader(new FileReader("date.txt")));
            
            numberOfElementsFirstArray = scanner.nextInt();
            firstArray = new int[numberOfElementsFirstArray];
            
            for (int i = 0; i < numberOfElementsFirstArray; ++i)
                firstArray[i] = scanner.nextInt();
                
            numberOfElementsSecondArray = scanner.nextInt();
            secondArray = new int[numberOfElementsSecondArray];
            
            for (int i = 0; i < numberOfElementsSecondArray; ++i)
                secondArray[i] = scanner.nextInt();
            
        }
        catch(FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            scanner.close();
        }
        
        double result = FindMedian(numberOfElementsFirstArray, firstArray, numberOfElementsSecondArray, secondArray);
        System.out.println(result);
    }

    private static double FindMedian(int numberOfElements1, int[] fArray, int numberOfElements2, int[] sArray) 
    {
        if (numberOfElements1 > numberOfElements2)
            return FindMedian(numberOfElements2, sArray, numberOfElements1, fArray);
            
        int left = 0;
        int right = numberOfElements1;
        int i = 0, j = 0;
        int max_left, min_right;
            
        int middle = (numberOfElements1 + numberOfElements2 + 1) / 2;
            
        while(left <= right)
        {
            i = (left + right) / 2;
            j = middle - i;
                
            if (i < numberOfElements1 && sArray[j - 1] > fArray[i])
                left = i + 1;
            else if (i > 0 && fArray[i - 1] > sArray[j])
                right = i - 1;
            else
                break;
        }
        if (i == 0) max_left = sArray[j - 1];
        else if (j == 0) max_left = fArray[i - 1];
        else
            max_left = Math.max(sArray[j - 1], fArray[i - 1]);
                    
        if ((numberOfElements1 + numberOfElements2) % 2 == 1)
            return max_left;
                    
        if (i == numberOfElements1) min_right = sArray[j];
        else if (j == numberOfElements2) min_right = fArray[i];
        else
        {
            min_right = Math.min(sArray[j], fArray[i]);
        }
                    
        return (max_left + min_right) / 2.0;
    }
}
