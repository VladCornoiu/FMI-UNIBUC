/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercitiul2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Vlad Cornoiu
 */
public class MyQueue<T> implements Iterable<T> {

    private T[] queue;
    public int front, back;
    private int size;
    public Iterator<T> iterator() {
        return new MyQueueIterator();
    }
    
    public MyQueue(int size) {
        if (size <= 0) {
            throw new IllegalStateException("Size cannot be less than or equal to zero");
        }
        this.size = size;
        front = -1;
        back = -1;
        queue = (T[]) new Object[size];
    }
    
    public boolean isEmpty() {
        return (front == -1 && back == -1);
    }
    
    public void add(T element) {
        if ((back + 1) % size == front) {
            throw new IllegalStateException("Queue is full");
        }
        else if (isEmpty()) {
            ++front;
            ++back;
            queue[back] = element;
        }
        else {
            back = (back + 1) % size;
            queue[back] = element;
        }
    }
    
    public T remove() {
        T value = null;
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        else if (front == back) {
            value = queue[front];
            front = back = -1;
        }
        else {
            value = queue[front];
            front = (front + 1) % size;
        }
        return value;
    }
    
    public T Peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty and cannot retrieve it's head");
        }
        else {
            return queue[front];
        }
    }
    
    public T GetIthElement(int i) {
        if (back >= front && back - front + 1 >= i) {
            return queue[front + i - 1];
        }
        else if (back <= front && size - front + back + 1 >= i) {
            return queue[(front + i - 1) % size];
        }
        else return null;
    }

    class MyQueueIterator<T> implements Iterator<T> {
        int poz = 0;
        
        @Override
        public boolean hasNext() {
            if (poz < queue.length) return true;
            return false;
        }
        
        @Override
        public T next() {
            if (hasNext()) {
                T element = (T) queue[poz];
                poz++;
                return element;
            }
            throw new NoSuchElementException();
        }
    }
    
}
