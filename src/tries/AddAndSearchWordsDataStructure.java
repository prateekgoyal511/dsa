package tries;

import java.util.HashMap;
import java.util.Map;

class WordDictionary {
    private Trie2 trie;

    public WordDictionary() {
        trie = new Trie2();
    }

    public void addWord(String word) {
        trie.insertWord(word);
    }

    public boolean search(String word) {
        return trie.search(word, trie.root);
    }
}

class TrieNode2 {
    public char c;
    public Map<Character, TrieNode2> children;
    public boolean isLeaf;

    public TrieNode2() {
        this.children = new HashMap<Character, TrieNode2>();
        this.isLeaf = false;
    }

    public TrieNode2(char c) {
        this.c = c;
        this.children = new HashMap<Character, TrieNode2>();
        this.isLeaf = false;
    }
}

class Trie2 {
    public TrieNode2 root;
    public Trie2() {
        root = new TrieNode2();
    }

    public void insertWord(String word) {
        TrieNode2 current = root;
        int n = word.length();

        int i;
        char currentChar;
        Map<Character, TrieNode2> currentNodeChildren;
        for(i=0; i<n; i++) {
            currentChar = word.charAt(i);
            currentNodeChildren = current.children;
            if(currentNodeChildren.containsKey(currentChar)) {
                current = currentNodeChildren.get(currentChar);
            } else {
                current = new TrieNode2(currentChar);
                currentNodeChildren.put(currentChar, current);
            }

            if(i == n-1) {
                current.isLeaf = true;
            }
        }
    }

    //This method takes word as input and also a node as input. It should see if word is present with this node
    //as root of the trie.
    public boolean search(String word, TrieNode2 node) {
        TrieNode2 current = node;
        int n = word.length();

        int i;
        char currentChar;
        Map<Character, TrieNode2> currentNodeChildren;

        //Base conditions
        //If no next character and this node is a leaf node
        if(n == 0) {
            if(node.isLeaf)return true;
            return false;
        }

        currentChar = word.charAt(0);
        currentNodeChildren = current.children;

        if(currentChar == '.') {
            for(TrieNode2 childNode: currentNodeChildren.values()) {
                current = childNode;
                if(search(word.substring(1, n), current)) return true;
            }
            return false;
        } else {
            if(currentNodeChildren.containsKey(currentChar)) {
                current = currentNodeChildren.get(currentChar);
                if(search(word.substring(1, n), current)) return true;
            } else {
                return false;
            }
        }
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
