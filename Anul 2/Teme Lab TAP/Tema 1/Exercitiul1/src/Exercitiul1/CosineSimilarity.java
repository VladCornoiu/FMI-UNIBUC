/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercitiul1;

import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
/**
 *
 * @author Vlad Cornoiu
 */
public class CosineSimilarity {
    public static double CosineTextSimilarity(ArrayList<String> firstFileWords, ArrayList<String> secondFileWords) {
        Integer count;
        int firstFileWordCount = 0, secondFileWordCount = 0;
        int numerator = 0, firstFactor = 0, secondFactor = 0;
        
        Map<String, Integer> firstFileMap = new HashMap<>();
        Map<String, Integer> secondFileMap = new HashMap<>();
        Set<String> allWordsSet = new HashSet<>();
        
        for (String word : firstFileWords) {
            count = firstFileMap.get(word);
            if (count == null) {
                firstFileMap.put(word, 1);
                allWordsSet.add(word);
            }
            else {
                firstFileMap.put(word, count + 1);
            }
        }
        
        for (String word : secondFileWords) {
            count = secondFileMap.get(word);
            if (count == null) {
                secondFileMap.put(word, 1);
                allWordsSet.add(word);
            }
            else {
                secondFileMap.put(word, count + 1);
            }
        }
        
        for (String word : allWordsSet) {
            count = firstFileMap.get(word);
            firstFileWordCount = (count == null ? 0 : count);
            
            count = secondFileMap.get(word);
            secondFileWordCount = (count == null ? 0 : count);
            
            numerator += (firstFileWordCount * secondFileWordCount);
            firstFactor += (firstFileWordCount * firstFileWordCount);
            secondFactor += (secondFileWordCount * secondFileWordCount);
        }
        
        double denominator = Math.sqrt(firstFactor) * Math.sqrt(secondFactor);
        double result = numerator / denominator;
        
        return result;
    }
}
