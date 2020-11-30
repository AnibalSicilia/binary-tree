import java.util.ArrayList;

public class BinaryNodeDriver 
{

    public static void main(String[] args) 
    {
        System.out.println("Testing Binary Node");
        System.out.println("===================\n\n");
        
        System.out.println("We create a binary tree.");
        BinaryNode<String> n1 = new BinaryNode<String>("Mark", null, null);
        BinaryNode<String> n2 = new BinaryNode<String>("Ann", null, null);
        BinaryNode<String> n3 = new BinaryNode<String>("Jane", null, null);
        BinaryNode<String> n4 = new BinaryNode<String>("Bob", null, null);
        BinaryNode<String> n5 = new BinaryNode<String>("Dan", null, null);
        BinaryNode<String> n6 = new BinaryNode<String>("Barbara", null, null);
        
        BinaryNode<String> n7 = new BinaryNode<String>("John", n1, null);
        BinaryNode<String> n8 = new BinaryNode<String>("Donna", null, n2);
        BinaryNode<String> n9 = new BinaryNode<String>("Bill", n3, n4);
        BinaryNode<String> n10 = new BinaryNode<String>("Carol", n5, n6);
        BinaryNode<String> n11 = new BinaryNode<String>("Peter", n7, n8);
        BinaryNode<String> n12 = new BinaryNode<String>("Odette", null, n9); 
        BinaryNode<String> n13 = new BinaryNode<String>("Lynn", n10, null);
        BinaryNode<String> n14 = new BinaryNode<String>("Cathy", n11,n12 );
        BinaryNode<String> n15 = new BinaryNode<String>("Joe", n13, n14);
        
        // now test the traversals
        System.out.println("\nThe nodes in preorder are:");
        n15.printPreOrder();
        System.out.println("\nThe nodes in iterative preorder");
        n15.iterativePreOrder();
        System.out.println("\nThe nodes in inorder are:");
        n15.printInOrder();
        System.out.println("\nThe nodes in iterative inorder");
        n15.iterativeInOrder();
        System.out.println("\nThe nodes in postorder are:");
        n15.printPostOrder();
        System.out.println("\nThe nodes in iterative postorder");
        n15.iterativePostOrder();
        System.out.println("\nThe nodes printed by levels are:");
        n15.printByLevels();
        
        // now let us reconstruct the trees
        System.out.println("Create a tree with the preorder traversal Papa Bill, ");
        System.out.println("Marcus, Hex, Grandpa, Tornado, and the inorder traversal, ");
        System.out.println(" Hex, Marcus, Papa Bill, Grandpa, Tornado.");
        String[] pre = {"Papa Bill" , "Marcus", "Hex", "Grandpa", "Tornado"};
        String[] in = {"Hex", "Marcus", "Papa Bill", "Grandpa", "Tornado"};
        BinaryNode<String> profs = BinaryNode.prePlusIn(pre,in);
        System.out.println("\nThe tree in preorder:");
        profs.printPreOrder();
        System.out.println("\nThe tree in inorder:");
        profs.printInOrder();
     
        // test levelsPlusIn
        System.out.println("\nTest levelsPlusIn");
        String[] inOrder = { "Ali Baba", "Geoff the Chef", "Granpa Smith", "Papa Bill",
               "Tornado", "Shrek", "Zorba"};
        String[] levels = { "Papa Bill", "Geoff the Chef", "Shrek", "Ali Baba", 
               "Granpa Smith", "Tornado", "Zorba"};
        BinaryNode<String> newTree = BinaryNode.levelsPlusIn(inOrder,levels);
        
        System.out.println("The in order is ");
        for (String s : inOrder)
            System.out.println(s);
        System.out.println("\nThe traversal by levels is ");
        for (String s : levels)
            System.out.println(s);
        System.out.println("\nThe in order traversal of the tree is ");
        newTree.printInOrder();
        System.out.println("\nThe traversal by levels of the tree is ");
        newTree.printByLevels();
        
        // test build the tree
        System.out.println("\nWe test buildTheTree");
        BinaryNode<Character> tree = BinaryNode.buildTheTree("+-a+bc-*def");
        System.out.println("The prefix form is +-a+bc-*def");
        tree.printPreOrder();
        
        
        System.out.println("\nCreate a tree with the postorder traversal Susan, ");
        System.out.println("Bill, Mark, Mary, Ann, Bob and the inorder traversal, ");
        System.out.println(" Susan, Mark, Bill, Mary, Bob, Ann.");
        String[] in2  = {"Susan" , "Mark", "Bill", "Mary", "Bob", "Ann"};
        String[] post = {"Susan", "Bill", "Mark", "Mary", "Ann", "Bob"};
        BinaryNode<String> friends = BinaryNode.postPlusIn(post,in2);
        System.out.println("\nThe tree in postorder:");
        friends.printPostOrder();
        System.out.println("\nThe tree in inorder:");
        friends.printInOrder();
                
        // we test longestPath
        ArrayList<String> labels = BinaryNode.longestPath(n15);
        System.out.println("\nWe test longest path for the first tree.");
        for (String s: labels)
            System.out.println(s);

        // test equals and isomorphic
        System.out.println("\nTesting equals and isomorphic");
        System.out.println("Tree 1 has root Bill and left child Jane.");
        BinaryNode<String> m1 = new BinaryNode<String>("Jane", null, null);
        BinaryNode<String> t1 = new BinaryNode<String>("Bill", m1, null);
        System.out.println("Tree 2 has root Bill and left child Jane.");        
        BinaryNode<String> m2 = new BinaryNode<String>("Jane", null, null);
        BinaryNode<String> t2 = new BinaryNode<String>("Bill", m2, null);
        
        System.out.println("Tree 3 has root null and left child Jane.");
        BinaryNode<String> m3 = new BinaryNode<String>("Jane", null, null);
        BinaryNode<String> t3 = new BinaryNode<String>(null, m3, null);
        
        System.out.println("Tree 4 has root Bill and right child Jane.");
        BinaryNode<String> m4 = new BinaryNode<String>("Jane", null, null);
        BinaryNode<String> t4 = new BinaryNode<String>("Bill", null, m4);
        
        System.out.println("t1.equals(t2) = " + t1.equals(t2));
        System.out.println("t1.equals(t3) = " + t1.equals(t3));
        System.out.println("t1.equals(t4) = " + t1.equals(t4));
        System.out.println("t1.equals(null) = " + t1.equals(null));
        
        System.out.println("\nt1.isomorphic(t2) = " + t1.isomorphic(t2));
        System.out.println("t1.isomorphic(t3) = " + t1.isomorphic(t3));
        System.out.println("t1.isomorphic(t4) = " + t1.isomorphic(t4));
        System.out.println("t1.isomorphic(null) = " + t1.isomorphic(null) );        
        
    }
}

