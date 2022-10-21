package tries;

import java.util.HashMap;
import java.util.Map;

//https://www.interviewbit.com/problems/shortest-unique-prefix/
public class ShortestUniquePrefix {
    public String[] prefix(String[] A) {
        //We'll put all of these words in a trie. Then, for every word, as we traverse through trie,
        //whenever there is only one child for a node => it'll be a unique prefix.
        Trie trie = new Trie();

        int n = A.length;
        int i;
        String currentString;
        //Iterate through each of these strings. Put them in trie.
        for(i=0; i<n; i++) {
            currentString = A[i];
            trie.insert(currentString);
        }

        String[] result = new String[n];
        String uniquePrefix;
        for(i=0; i<n; i++) {
            currentString = A[i];
            uniquePrefix = trie.findUniquePrefix(currentString);
            result[i] = uniquePrefix;
        }
        return result;
    }
}
//This class will contain the following information:-
//a. The character in the TrieNode
//b. Mapping to next children trienodes based on character.
//c. Whether this is a leaf node or not.
//d. Number of links - How many number of links are there from this node. Eg:- bear, bert. Here, b has 2 links to e.
class TrieNode {
    private char c;
    private Map<Character, TrieNode> children;
    private boolean isLeaf;
    private int numberOfLinks;

    //This constructor is used for the root node. It doesn't have a character.
    public TrieNode() {
        this.children = new HashMap<Character, TrieNode>();
        this.numberOfLinks = 0;
    }
    //This constructor is used for any node. It has a character.
    public TrieNode(char c) {
        this.c = c;
        children = new HashMap<Character, TrieNode>();
    }

    public Map<Character, TrieNode> getChildren() {
        return this.children;
    }

    public void setLeaf(boolean value) {
        this.isLeaf = value;
    }

    public void incrementNumberOfLinks() {
        this.numberOfLinks++;
    }

    public int getNumberOfLinks() {
        return this.numberOfLinks;
    }
}

//How will we denote a Trie. If we have a root node,
//we have sufficient information to represent a trie.
class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String key) {
        int n = key.length();
        int i;
        TrieNode currentNode = root;
        Map<Character, TrieNode> childrenMap;
        //In every iteration, we'll take currentChar. We'll also have a currentNode. We'll first check If
        //that char is present among children. If it is => we'll move to the next node. If it is not => we'll
        //create a new node.
        for(i=0; i<n; i++) {
            char currentChar = key.charAt(i);
            childrenMap = currentNode.getChildren();
            //If that character is already present => move to that node.
            if(childrenMap.containsKey(currentChar)) {
                currentNode.incrementNumberOfLinks();
                currentNode = childrenMap.get(currentChar);
            } else {
                currentNode.incrementNumberOfLinks();
                //if it is not present => create a new node and link it to the current node.
                currentNode = new TrieNode(currentChar);
                childrenMap.put(currentChar, currentNode);
            }

            if(i == n-1) {
                currentNode.setLeaf(true);
            }
        }
    }

    //This method will take a key as an input. It'll return a prefix of this key that can uniquely identify this key.
    public String findUniquePrefix(String key) {
        int n = key.length();
        int i;
        TrieNode currentNode = root;
        Map<Character, TrieNode> childrenMap;
        String constructedPrefix = "";
        //In every iteration, we'll have a currentChar and a currentNode. We'll check if this char is present among children
        //of this node. If it is, we'll move to this node. We'll update the constructedPrefix. We'll check if it has only 1 link.
        //If it has we'll return from there.
        for(i=0; i<n; i++) {
            char currentChar = key.charAt(i);
            childrenMap = currentNode.getChildren();
            if(childrenMap.containsKey(currentChar)) {
                currentNode = childrenMap.get(currentChar);
                constructedPrefix = constructedPrefix + currentChar;
            }
            if(currentNode.getNumberOfLinks() == 1) return constructedPrefix;
        }
        return constructedPrefix;
    }
}

