/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dei_2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.io.BufferedReader;

/**
 *
 * @author Vlad Cornoiu
 */

class Node
{
    Node left, right;
    int value;
    
    public Node(int _value)
    {
        value = _value;
        left = null;
        right = null;
    }
}

public class DEI_2 {
    
    static int numberOfNodes = 0, index = 0;
    static boolean isValid = true;
    static int postord[];
    static int inord[];
    static int preord[];
    static int position[];
    
    public static Node FindBinaryTree(int left, int right, int rootpos)
    {
        if (left > right)
            return null;
        
        int root = postord[rootpos];
            
        if (position[root] >= left && position[root] <= right)
        {
            Node node = new Node(root);
                
            if (left < right)
            {
                node.left = FindBinaryTree(left, position[root] - 1, rootpos - right + position[root] - 1);
                node.right = FindBinaryTree(position[root] + 1, right, rootpos - 1);
            }
                
            return node;
        }
        else
        {
            isValid = false;
            return null;
        }
    }
    
    public static void CreatePreord(Node node)
    {
        if (node == null)
            return;
        
        preord[++index] = node.value;
        CreatePreord(node.left);
        CreatePreord(node.right);
    }

    public static void main(String[] args) {
        Scanner scanner = null;
        
        try
        {
            scanner = new Scanner(new BufferedReader(new FileReader("date.txt")));
            
            numberOfNodes = scanner.nextInt();
            postord = new int[numberOfNodes + 1];
            inord = new int[numberOfNodes + 1];
            preord = new int[numberOfNodes + 1];
            position = new int[numberOfNodes + 1];
            
            
            for (int i = 1; i <= numberOfNodes; ++i)
            {
                postord[i] = scanner.nextInt();
            }
            
            for (int i = 1; i <= numberOfNodes; ++i)
            {
                inord[i] = scanner.nextInt();
                position[inord[i]] = i;
            }
            
            Node root = FindBinaryTree(1, numberOfNodes, numberOfNodes);
            if (isValid)
            {
                CreatePreord(root);
            
                for(int i = 1; i <= numberOfNodes; ++i)
                    System.out.print(preord[i] + " ");
                System.out.println();

                for(int i = 1; i <= numberOfNodes; ++i)
                    System.out.print(inord[i] + " ");
                System.out.println();

                for(int i = 1; i <= numberOfNodes; ++i)
                    System.out.print(postord[i] + " ");
                System.out.println();
            }
            else
                System.out.println("Not correct");
        }
        catch(FileNotFoundException ex)
        {
            System.err.println("File was not found");
        }
        finally
        {
            scanner.close();
        }
    }
}
    