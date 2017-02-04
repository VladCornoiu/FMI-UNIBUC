/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dei_4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
/**
 *
 * @author Vlad Cornoiu
 */

class Point implements Comparable<Point>
{   
    int x, y;
    
    public Point(int _x, int _y)
    {
        x = _x;
        y = _y;
    }
    @Override
    public int compareTo(Point other)
    {
        return x - other.x;
    }
}

public class DEI_4 {
    
    static int numberOfPoints = 0;
    static Point[] points;

    public static void main(String[] args) {
        Scanner scanner = null;
        
        try
        {
            scanner = new Scanner(new BufferedReader(new FileReader("date.txt")));
            
            numberOfPoints = scanner.nextInt();
            points = new Point[numberOfPoints];
            int x, y;
            
            for(int i = 0; i < numberOfPoints; ++i)
            {
                x = scanner.nextInt();
                y = scanner.nextInt();
                points[i] = new Point(x, y);
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
        
        Arrays.sort(points);
        
        double result = FindMinimumDistance(0, numberOfPoints - 1);
        
        System.out.println(Math.sqrt(result));
    }

    private static int FindMinimumDistance(int left, int right)
    {
        int minimumDistance = Integer.MAX_VALUE;
        if (right - left < 3)
        {
            for(int i = left; i < right ; ++i)
                for(int j = i + 1; j <= right; ++j)
                    minimumDistance = Math.min(DistanceBetweenPoints(points[i], points[j]), minimumDistance);
        }
        else
        {
            int middle = (left + right) / 2;
            minimumDistance = Math.min(FindMinimumDistance(left, middle), FindMinimumDistance(middle + 1, right));
            
            int first = -1, last = -1;
            for(int i = left; i <= right; ++i) 
            {
                if(Math.abs(points[i].x - points[middle].x) <= minimumDistance) 
                {
                    if(first == -1)
                        first = i;
                    last = i;
                }
            }

            for(int i = first; i <= last; ++i) 
                for(int j = i + 1; j <= Math.min(last, first + 7); ++j)
                    minimumDistance = Math.min(minimumDistance, DistanceBetweenPoints(points[i], points[j]));
        }
        
        return minimumDistance;
    }
    
    private static int DistanceBetweenPoints(Point a, Point b)
    {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }
    
}
