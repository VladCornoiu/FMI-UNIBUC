/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercitiul4;

/**
 *
 * @author Vlad Cornoiu
 */
public class CompareClass1 implements Comparable<CompareClass1> {
    int first, second;
    
    public CompareClass1(int _first, int _second) {
        first = _first;
        second = _second;
    }
    
    public int compareTo(CompareClass1 term) {
        return term.first - this.first + term.second - this.second;
    }
}
