/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stirlingnumbers;

/**
 *
 * @author Vlad Cornoiu
 */
public class StirlingNumbers {
    
    static final int NMAX = 10000;
    static int[] prec = new int[NMAX];
    static int[] act = new int[NMAX];
    
    public static void main(String[] args) {
        prec[1] = 1;
        act[1] = 1;
        act[0] = 2;
        
        for (int j = 1; j <= 4; ++j)
        {
            for (int i = 2; i <= act[0]; ++i)
                if (i == act[0]) act[act[0]] = 1;
                else act[i] = prec[i - 1] + i * prec[i];
        
            for (int i = 1; i <= act[0]; ++i)
            {
                int aux = prec[i];
                prec[i] = act[i];
                act[i] = 0;
            }
            act[0]++;
        }
        System.out.println(prec[3]);
    }
    
}
