/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planificare;

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
    int duration, limit;
    int index;
}
public class Planificare {
    
    public static void main(String[] args) {
        
    List<Interval> range = new ArrayList<>();
    Scanner scanner = null;
        
    int numberOfActivities = 0;
        
        try {
            scanner = new Scanner(new BufferedReader(new FileReader("date.txt")));
            
            numberOfActivities = scanner.nextInt();
            
            for (int i = 1; i <= numberOfActivities; ++i)
            {
                Interval current = new Interval();
                current.duration = scanner.nextInt();
                current.limit = scanner.nextInt();
                current.index = i;
                
                range.add(current);
            }
        }
        catch(FileNotFoundException e) {
            System.err.println("File was not found");
        }
        finally {
            scanner.close();
        }
        
        Collections.sort(range, (Interval a, Interval b) -> {
            return a.limit - b.limit;
            });
        
        
        int ind = 0;
        Interval curr = new Interval();
        int left = 0;
        int lateness = 0;
        int maximumLateness = 0;
        
        while(ind < range.size())
        {
            curr = range.get(ind);
            lateness = Math.max(0, left + curr.duration - curr.limit);
            System.out.println("Activitatea " + curr.index + ": intervalul " + left + " " + (left + curr.duration) + " intarziere " + lateness);
            left = left + curr.duration;
            ind++;
            maximumLateness = Math.max(maximumLateness, lateness);
        }
        System.out.println("Intarziere planificare " + maximumLateness );
    }
    
}
