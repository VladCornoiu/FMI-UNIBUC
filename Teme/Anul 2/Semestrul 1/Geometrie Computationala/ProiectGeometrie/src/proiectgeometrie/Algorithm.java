/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proiectgeometrie;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Vlad Cornoiu
 */
public class Algorithm 
{
    public static Point[] pts;
    public static Point firstPoint;
    
    private static int PolarAngle(Point p1, Point p2, Point p3)
    {
        double value = (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
        if (value > 0)
            return 1;
        else if (value < 0)
            return -1;
        return 0;
    }
    
    public static ArrayList<Point> ConvexHull(Point[] points)
    {
        SortPoints(points);
        
        Point[] stack = new Point[points.length];
        stack[1] = points[1];
        stack[2] = points[2];
        
        int head = 2;
        
        for (int i = 3; i <= points.length - 1; ++i)
        {
            while (head >= 2 && PolarAngle(stack[head - 1], stack[head], points[i]) > 0)
                --head;
            stack[++head] = points[i];
        }
        
        ArrayList<Point> solution = new ArrayList<>();
        for (int i = 1; i <= head; ++i)
            solution.add(stack[i]);
        
        return solution;
    }
    
    private static void SortPoints(Point[] points) 
    {
        int position = 1;
        
        for (int i = 2; i < points.length; ++i)
            if (points[i].y < points[position].y)
                position = i;
        
        Point temporary = new Point(points[1].x, points[1].y);
        points[1] = points[position];
        points[position] = temporary;
        
        Arrays.sort(points, (Point a, Point b) -> {
            return PolarAngle(points[1], a, b);
        });
    }
    
}
