/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercitiul4;

import java.util.Objects;

/**
 *
 * @author Vlad Cornoiu
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        
        public Node(Key _key, Value _value) {
            key = _key;
            value = _value;
            left = null;
            right = null;
        }
        
        @Override
        public int hashCode() {
            int hash = key.hashCode();
            if (value != null) {
                hash ^= value.hashCode();
            }
            
            return hash;
        }
    }
        
        Node root;
        
        public BinarySearchTree() {
            root = null;
        }
        
        public boolean Add(Key key, Value value) {
            Node currentNode = root;
            int ind = 0;
            Node toAddNode = new Node (key, value);
            if (currentNode == null) {
                root = new Node(key, value);
            }
            else {
                while(currentNode != null) {
                    ind = currentNode.key.compareTo(key);
                    if (ind == 0) {
                        return false;
                    }
                    else if (ind > 0 && currentNode.left == null) {
                       currentNode.left = new Node(key, value);
                       return true;
                    }
                    else if (ind > 0  && currentNode.left != null)
                        currentNode = currentNode.left;
                    else if (ind < 0 && currentNode.right != null) {
                        currentNode = currentNode.right;
                    }
                    else {
                        currentNode.right = new Node(key, value);
                        return true;
                    }
                }
            }
            return true;
        }
        
        private String InOrder(Node node) {
            if (node == null) {
                return "";
            }
            else {
                return InOrder(node.left) + node.value + " " + InOrder(node.right);
            }
        }
        
        @Override
        public String toString() {
            return InOrder(root);
        }
        
        @Override
        public boolean equals(Object other) {
            BinarySearchTree<Key, Value> otherBst = (BinarySearchTree<Key, Value>) other;
            return CheckEquality(root, otherBst.root);
        }
        
        private boolean CheckEquality(Node first, Node second) {
            if (first == null && second == null) {
                return true;
            }
            return first.key.compareTo(second.key) == 0 && CheckEquality(first.left, second.left) && CheckEquality(first.right, second.right);
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 79 * hash + HashFunction(this.root, 1);
            return hash;
        }   
        
        public int HashFunction(Node node, int counter) {
            if (node == null) {
                return 0;
            }
            return counter * Objects.hashCode(node) + HashFunction(node.left, 2 * counter) + HashFunction(node.right, 2 * counter + 1);
        }
}

