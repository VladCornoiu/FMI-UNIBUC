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
public class CompareClass2 extends CompareClass1 {
    int third;
    
    CompareClass2(int _first, int _second, int _third) {
        super(_first, _second);
        third = _third;
    }
    
    public int compareTo(CompareClass2 term) {
        return term.first - this.first + term.second - this.second + term.third - this.third;
    }
    
}
