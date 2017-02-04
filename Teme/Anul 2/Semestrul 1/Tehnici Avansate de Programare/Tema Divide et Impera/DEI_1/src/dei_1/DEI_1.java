/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dei_1;

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
public class DEI_1 {

    static int numberOfElements = 0;
    static List<Integer> numbers = new ArrayList<>();
    static int position = -1;
    public static void main(String[] args) {
        Scanner scanner = null;
        int ok = 0;
                
        try
        {
            scanner = new Scanner(new BufferedReader(new FileReader("date.txt")));
            
            numberOfElements = scanner.nextInt();
            
            for (int i = 1; i <= numberOfElements; ++i)
            {
                numbers.add(scanner.nextInt());
            }
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("The file was not found");
        }
        finally
        {
            scanner.close();
        }
        
        ok = Binary_Search(1, numberOfElements, numbers);
        if (ok == -1) 
            System.out.println("Nu exista");
        else
            System.out.println(ok);
        
    }
    
    public static int Binary_Search(int left, int right, List<Integer> numbersList)
    {
        if (left > right) return -1;
        else
        {
            int mid = (left + right) / 2;
            int currentElement = numbersList.get(mid);
            if (currentElement == mid)
            {
                 return mid;
            }
            else if (currentElement < mid) 
                return Binary_Search(mid + 1, right, numbersList);
            else
                return Binary_Search(left, mid - 1, numbersList);
        }
    }
    
}
