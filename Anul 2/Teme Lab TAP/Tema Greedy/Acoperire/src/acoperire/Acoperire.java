/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acoperire;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Vlad Cornoiu
 */

class Interval 
{
    int st, dr;
}

public class Acoperire {

    /**
     * @param args the command line arguments
     * 
     */
    
    public static void main(String[] args) {
        
        List<Interval> range = new ArrayList<>();
        Scanner scanner = null;
        
        int numberOfIntervals = 0;
        int left = 0, right = 0;
        Interval toAdd = new Interval();
        
        try {
            scanner = new Scanner(new BufferedReader(new FileReader("date.txt")));
            left = scanner.nextInt();
            right = scanner.nextInt();
            numberOfIntervals = scanner.nextInt();
            
            for (int i = 1; i <= numberOfIntervals; ++i)
            {
                Interval current = new Interval();
                current.st = scanner.nextInt();
                current.dr = scanner.nextInt();
                
                if (current.dr < left || current.st > right) continue;
                else
                {
                    range.add(current);
                }
            }
        }
        catch(FileNotFoundException e) {
            System.err.println("File was not found");
        }
        finally {
            scanner.close();
        }
        
        if (range.isEmpty())
        {
            System.out.println("-1");   
        }
        else
        {
            Collections.sort(range, (Interval a, Interval b) -> {
                if (a.st != b.st)
                    return a.st - b.st;
                else
                    return a.dr - b.dr;
            });
            
            
            Interval[] stack = new Interval[1004];
            Interval curr = new Interval();
            curr = range.get(0);
            int ind = 1;
            int scInd = 1;
            
            stack[1] = curr;
            
            boolean ok = true;
            while(ind < range.size())
            {
                curr = stack[scInd];
                toAdd = range.get(ind);
                
                if (toAdd.st > stack[scInd].dr)
                {
                    ok = false;
                    break;
                }

                if (toAdd.st <= left && curr.st < left && toAdd.dr >= curr.dr) 
                    scInd--;
                if (curr.dr >= right) break;

                while(scInd >= 2 && toAdd.st <= stack[scInd - 1].dr && toAdd.dr >= stack[scInd].dr)
                    scInd--;

                if (toAdd.dr > stack[scInd].dr)
                    stack[++scInd] = toAdd;
                ind++;
            }
            
            if (ok == true && stack[scInd].dr >= right)
                for (int i = 1; i <= scInd; ++i)
                    System.out.println(stack[i].st + " " + stack[i].dr);
            else
                System.out.println("-1");
        }
    }
}