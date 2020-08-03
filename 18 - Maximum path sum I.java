// Java 14.0.1
// frostrivera19
// Project Euler Problem 18: Maximum path sum I
// https://projecteuler.net/archives
// Solved 21 May 2020

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(maxPathSum("Question 18 Text.txt"));
    }

    private static int bruteForce(String fileName) throws FileNotFoundException{
        File file = new File(fileName);
        Scanner scan = new Scanner(file);
        Tree tree = new Tree();

        while (scan.hasNextInt()) {
            int toAdd = scan.nextInt();
            tree.insertNode(toAdd);
        }
        scan.close();

        int currentPath;
        int maxPath = 0;

        for (int i = 0; i < Math.pow(2, tree.height - 1); i++) {
            StringBuilder path = new StringBuilder(Integer.toBinaryString(i));
            while (path.length() < tree.height - 1) {
                path.insert(0, '0');
            }
            char[] pathInChar = path.toString().toCharArray();
            currentPath = thisPathSum(pathInChar, tree.root).sum;
            if (currentPath > maxPath) {
                maxPath = currentPath;
            }
        }

        return maxPath;
    }

    /*
    * if any path in total have exceeded path A + 99 * (h - h(A)), kill the
    * path
    * */
    private static int maxPathSum(String fileName)
            throws FileNotFoundException {

        File file = new File(fileName);
        Scanner scan = new Scanner(file);
        Tree tree = new Tree();
        final int MAX_VALUE = TreeNode.MAX_VALUE;

        while (scan.hasNextInt()) {
            int toAdd = scan.nextInt();
            tree.insertNode(toAdd);
        }
        scan.close();

        // tree completely filled

        // find path sum for 3/4 of h (arbitrary number)

        int currentPath = 0;
        int maxHeightToCheck = (int) (0.75 * tree.height - 1);
        int maxPath = (int) Math.pow(2, maxHeightToCheck);
        int maxSum = 0;
        HashMap<String, ShortPathEnd> shortPathSums = new HashMap<>();

        while (currentPath < maxPath) {
            StringBuilder pathBuild = new StringBuilder();
            pathBuild.append(Integer.toBinaryString(currentPath));
            while (pathBuild.length() < maxHeightToCheck) {
                pathBuild.insert(0, '0');
            }
            String path = pathBuild.toString();

            char[] pathInChar = path.toCharArray();
            ShortPathEnd pathSum = thisPathSum(pathInChar, tree.root);
            if (pathSum.sum > maxSum) {
                maxSum = pathSum.sum;
                shortPathSums.put(path, pathSum);
            } else {
                if (pathSum.sum + MAX_VALUE * (tree.height - maxPath  - 1)
                        < maxPath) {
                    // do nothing
                } else {
                    shortPathSums.put(path, pathSum);
                }
            }
            currentPath++;
        }

        maxSum = 0;
        int initialPathLength;
        for (String pathTraversed : shortPathSums.keySet()) {
            StringBuilder initialPath = new StringBuilder(pathTraversed);
            initialPathLength = initialPath.length();
            int heightLeft = tree.height - initialPathLength;

            for (int i = 0; i <= Math.pow(2, heightLeft - 1) - 1; i++) {
                StringBuilder pathLeft =
                        new StringBuilder(Integer.toBinaryString(i));
                while (pathLeft.length() < heightLeft - 1) {
                    pathLeft.insert(0, '0');
                }
                char[] pathLeftInChar = pathLeft.toString().toCharArray();
                ShortPathEnd finalPathSum;
                finalPathSum = thisPathSum(pathLeftInChar,
                        shortPathSums.get(pathTraversed).end);
                finalPathSum.sum -= shortPathSums.get(pathTraversed).end.data;

                maxSum = Math.max(maxSum, finalPathSum.sum
                        + shortPathSums.get(pathTraversed).sum);
            }
        }


        return maxSum;

    }

    private static ShortPathEnd thisPathSum(char[] path, TreeNode root) {

        TreeNode current = root;
        int sum = current.data;
        for (char c : path) {
            if (c == '0') {
                sum += current.left.data;
                current = current.left;
            } else if (c == '1') {
                sum += current.right.data;
                current = current.right;
            } else {
                System.out.println("ERROR 371");
                System.exit(-1);
            }
        }
        return new ShortPathEnd(sum, current);
    }


}

class Tree {
    int height = 0;
    int nodesInThisHeight = 0;
    TreeNode root = null;
    TreeNode lastAdded;
    ArrayList<TreeNode> startingNodes = new ArrayList<>();

    public void insertNode(int nodeData) {
        TreeNode t = new TreeNode(nodeData);
        if (root == null) { // tree initially empty
            root = t;
            nodesInThisHeight = 1;
            height++;
            startingNodes.add(null);
            startingNodes.add(root);
        } else if (lastAdded == root) { // adding second node after root
            t.parent2 = root;
            root.left = t;
            nodesInThisHeight = 1;
            height++;
            startingNodes.add(t);
        } else if (lastAdded == root.left) { // third node
            t.parent1 = root;
            root.right = t;
            nodesInThisHeight++;
        } else if (nodesInThisHeight == height) { // this level full
            t.parent2 = startingNodes.get(height);
            startingNodes.get(height).left = t;
            startingNodes.add(t);
            height++;
            nodesInThisHeight = 1;
        } else if (nodesInThisHeight == height - 1) { // this level almost full
            t.parent1 = lastAdded.parent2;
            t.parent1.right = t;
            nodesInThisHeight++;
        } else { // everything in between
            t.parent1 = lastAdded.parent2;
            t.parent1.right = t;
            t.parent2 = lastAdded.parent2.parent2.right;
            t.parent2.left = t;
            nodesInThisHeight++;
        }

        lastAdded = t;
    }

}

class TreeNode {
    static int MAX_VALUE = 99;
    int data = 0;

    TreeNode parent1;
    TreeNode parent2;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public TreeNode(int data) {
        this(data, null, null);
    }
}

class ShortPathEnd {
    int sum;
    TreeNode end;

    public ShortPathEnd(int pathSum, TreeNode end) {
        this.sum = pathSum;
        this.end = end;
    }
}