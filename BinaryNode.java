package assignment4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/* COURSE      : COP 3530
 * Section     : U02
 * Semester    : Fall 2012
 * Instructor  : Alex Pelin
 * Author      : Anibal Sicilia
 * Assignment #: 4
 * Due date    : November 15 , 2012
 * Description : An implementation of the Binary Node class with some 
 * added methods
 *
 *
 *
 *  I certify that the work is my own and did not consult with
 *  anyone.
 *
 * 
 * 
 * Anibal Sicilia
 */
/**
 *
 * @author pelina
 */
public class BinaryNode<T> {
    
    /** Create a default binary node */
    public BinaryNode() 
    {
        this(null, null, null);
    }
    
    // create a node with given value and children
    public BinaryNode(T theElement, BinaryNode<T> lt, BinaryNode<T> rt)
    {
        element = theElement;
        left = lt;
        right = rt;
    }
    
    // return the element
    public T getElement()
    {
        return element;
    }
    
    // return the left child
    public BinaryNode<T> getLeft()
    {
        return left;
    }
    
    // return the right child
    public BinaryNode<T> getRight()
    {
        return right;
    }
    
    // set the element to a given value
    // @param x is the new value
    public void setElement( T x)
    {
        element = x;
    }
    
    // set the left child
    // @param t is the left child
    public void setLeft(BinaryNode<T> t)
    {
        left = t;
    }
    
    // set the right child
    // @param t is the right child
    public void setRight(BinaryNode<T> t)
    {
        right = t;
    }
    
    // @return the size of the subtree at node t 
    public static <T> int size( BinaryNode<T> t)
    {
        if ( t == null)
            return 0;
        else
            return 1 + size(t.left) + size(t.right);
    }
    
    // @return the height of the subtree of node t
    public static <T> int height( BinaryNode<T> t)
    {
        if (t == null)
            return -1;
        else 
            return 1 + Math.max(height(t.left), height(t.right));
    }

    // find if an item occurs in the tree
    // @param inq is the inquiry
    public BinaryNode<T> find(T inq)
    {
        if (element.equals(inq))
            return this;
        BinaryNode<T> out = null;
        if ( left != null)
        {
            out = left.find(inq);
        }    
        if ( out != null)
            return out;
        else if ( right != null)
            out = right.find(inq);
        return out;
    }    
    
    // create a duplicate tree
    // @return the root of the duplicate tree
    public BinaryNode<T> duplicate()
    {
        BinaryNode<T> root = new BinaryNode<T>(element,null,null);
        if ( left != null)
            // create a duplicate tree for the left subtree
            root.left = left.duplicate();
        if (right != null)
            root.right = right.duplicate();
        return root;
    }
    
    // print the tree elements in preorder
    public void printPreOrder()
    {
        System.out.println(element);
        if (left != null)
            left.printPreOrder();
        if (right != null)
            right.printPreOrder();
    }
    
   
    // print the tree elements in postorder // iterativeInorder
    public void iterativePreOrder()
    {
      
        Stack<BinaryNode<T>> s = new Stack();
        BinaryNode<T> current = this;
        while (current != null || !s.empty())
        {
            if (current != null)
            {
                System.out.println(current.element);
                // process it, put it in the stack, and go left
                s.push(current);
                current = current.left;
            }
            else // pop the stack and go right
            {
                current = s.pop();
                current = current.right;
            }
        }    
    }
   
       
    // print in post order
    public void printPostOrder()
    {
        if (left != null)
            left.printPostOrder();
        if (right != null)
            right.printPostOrder();
        System.out.println(element);
    }
   
    
    // print the tree elements in inorder
    public void printInOrder()
    {
        if (left != null)
            left.printInOrder();
        System.out.println(element);
        if (right != null)
            right.printInOrder();
    }
    
  
    /**
     * Prints the nodes of the tree with root this in postorder, without
     * using recursion.
     */
    public void iterativePostOrder()
    {
        Stack <BinaryNode <T>> s = new Stack();
        BinaryNode <T> current = this;
        BinaryNode <T> previous = null;

	while(!s.empty() || current !=null)
	{
            if(current != null)
            {
                s.push(current);
                current = current.left;
            }
            else
            {
                current = s.peek();
                if(current.right == null || current.right == previous)
                {
                    System.out.println(current.element);
                    previous = current;
                    s.pop();
                    current = null;
                }
                else
                {
                    current = current.right;
                }
            }
        }        
    }
    
    /**
     * Prints the nodes of the tree with root this using breadthsearch.
     */
    public void printByLevels()
    {
        Queue <BinaryNode <T>> queue = new LinkedList <> ();
        BinaryNode current = this;
        boolean visited = false;
        System.out.println(current.element);
        
        while(current != null)
        {
            if(current.left != null && visited == false)
            {
                queue.offer(current.left);
                System.out.println(current.left.element);
                visited = true;
            }
            else if(current.right != null)
            {
                queue.offer(current.right);
                System.out.println(current.right.element);
                current = queue.poll();
                visited = false;
            }
            else if (!queue.isEmpty())
            {
                current = queue.poll();
                visited = false;
            }
            else
                current = null;
        }
    }
    
    /**
     * Takes as input the inorder traversal and the traversal by levels and
     * reconstructs the tree. Throws an IllegalArgumentException if this cannot
     * be done.
     * @param <T>
     * @param in
     * @param levels
     * @return 
     */
    public static <T> BinaryNode<T> levelsPlusIn(T[] in, T[] levels)
    {
        if(levels.length == 0)
            return null;
        
        BinaryNode <T> t = (BinaryNode <T>) new BinaryNode(levels[0], null, null);
        int partition = 0;
        for (int i = 0; i < in.length; i++)
        {
            if(in[i] != null && in[i].equals(levels[0])){
                partition = i; // if found
                break;}        // no need to continue     
        }
            
        T[] inLeft = (T[]) new Object [partition];  // left side of the tree
        int inL = 0;
        for (int i = 0; i < partition; i++)
            inLeft[inL++] = in[i];
        int inR = 0;
        T[] inRight = (T[]) new Object [in.length - partition - 1];
        for(int i = partition+1; i< in.length; i++)
            inRight[inR++] = in[i];
        
        T[] levelLeft = (T[]) new Object[inLeft.length];
        T[] levelRight = (T[]) new Object[inRight.length];
        int lR = 0; // counter for levels right side array
        int lL = 0; // counter for levels left side array
        int levelCounter = levels.length - 1; // test if both arrays are equal
        for(int i = 0; i < levels.length; i++)  // traverse levels
        {
            for(int u = 0; u < inR; u++)          // add to levels right the 
                if(levels[i].equals(inRight[u])){  // elements contained in 
                    levelRight[lR++] = levels[i]; // inorder right in level's
                    levelCounter--;
                }                               // order
            for(int u = 0; u < inL; u++) // the same, but for the left side
                if(levels[i].equals(inLeft[u])){
                    levelLeft[lL++] = levels[i];
                    levelCounter--;
                }
        }
        if(levelCounter != 0) // it must be empty by now...
            throw new IllegalArgumentException("These arrays contain "
                    + "different elements");
        
        t.right = levelsPlusIn(inRight, levelRight); // now call function again
        t.left = levelsPlusIn(inLeft, levelLeft);    // the same but for left 
        return t;          
    }
     
    /**
     * returns the longest path in the tree by using recursion
     * @param <T>
     * @param root
     * @return 
     */
    public static <T> ArrayList<T> longestPath(BinaryNode <T> root)
    {
        ArrayList <T> longest = new ArrayList <> ();
        int sLeft = 0;
        int sRight = 0;
        if(root != null) 
        {
            longest.add(root.element);
            if(root.getLeft() != null)
            {
                sLeft = height(root.getLeft());
            }
            if(root.getRight() != null)
            {
                sRight = height(root.getRight());
            }
            if(sLeft >= sRight)
            {
                root = root.getLeft();
            }
            else
            {
                root = root.getRight();
            }
            longest.addAll(longestPath(root));
        }
        return longest;
    }
    
    /**
     * takes as input a prefix expression and outputs a binary tree whose
     * preorder traversal is the prefix expression
     * @param in
     * @return 
     */
    public static BinaryNode<Character> buildTheTree(String in)
    {
        Stack <BinaryNode> stack = new Stack <>();
        char x;
        BinaryNode root = new BinaryNode();
        for (int i = in.length()-1; i >= 0; i--)
        {
            x = in.charAt(i);
            root = new BinaryNode(x, null, null);
            if(Character.isLetter(x))
            {
                stack.push(root);          
            }
            else if(x == '*' || x == '/' || x == '+' || x == '-')
            {
                if(!stack.isEmpty())
                {
                   root.left = stack.pop();
                   root.right = stack.pop();
                }
                stack.push(root);
            }
        }
        return root;          
    }
   
    
    // the fields
    private T element;
    private BinaryNode<T> left;
    private BinaryNode<T> right;
}


