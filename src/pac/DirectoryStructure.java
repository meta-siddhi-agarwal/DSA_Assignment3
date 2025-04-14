package pac;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DirectoryStructure {
    // denotes path of the dir. in which we are present
    static String path;

    // used for taking input from user
    static Scanner scannerObject = new Scanner(System.in);

    // will keep list of all the nodes present in the path
    static List<TreeNode> paths = new ArrayList<>();

    // will store list of possible/valid commands
    static List<String> commandsList = new ArrayList<>();

    // will store all the dir. created along with their names
    static Map<String, TreeNode> directory = new HashMap<>();

    public static void main(String[] args) {
        // class obj. for calling non static methods
        DirectoryStructure dirStructObj = new DirectoryStructure();

        // adding possible commands to the list
        commandsList.add("mkdir");
        commandsList.add("ls");
        commandsList.add("bk");
        commandsList.add("cd");
        commandsList.add("Tree");
        commandsList.add("exit");
        commandsList.add("find");

        path = "R" + ":" + "\\" + ">";

        // first creating root dir., which is{R}
        TreeNode rootNode = new TreeNode("R");

        directory.put("R", rootNode);
        // listOfNodes.add(rootNode);
        paths.add(rootNode);

        String input = "";

        // program will not terminate till user enters {exit} command
        while (!"exit".equals(input)) {
            try {
                System.out.print(path);
                for (int pathIndex = 1; pathIndex < paths.size(); pathIndex++) {
                    System.out.print(paths.get(pathIndex).root);
                    System.out.print(">");
                }

                input = scannerObject.nextLine();
                String inputArray[] = input.split(" ");

                // if input is a valid command
                if (commandsList.contains(inputArray[0])) {

                    switch (inputArray[0]) {

                        case "mkdir":
                            dirStructObj.createDir(inputArray);
                            break;

                        case "cd":
                            path = dirStructObj.changeDir(inputArray);
                            break;

                        case "find": {
                            String path = ".\\";
                            int sizeOfList = paths.size();
                            TreeNode node = paths.get(sizeOfList - 1);
                            dirStructObj.findDir(inputArray[1], node.child, path);
                            break;
                        }

                        case "ls":
                            dirStructObj.listDir();
                            break;

                        case "Tree":
                            System.out.println(".");
                            String space = "";
                            dirStructObj.printTree(rootNode.child, space);
                            break;

                        case "bk":
                            dirStructObj.getBackToPreviousDir();
                            break;

                        default:
                    }

                }
                // else input is not a valid command
                else {
                    System.out.println("Command does not exists");
                }

            } catch (Exception e) {
                if (e.getMessage() == null)
                    System.out.println("The syntax of the command is incorrect.");
                else
                    System.out.println(e.getMessage());
            }
        }
    }

    /**
     * will print the entire structure of the tree
     * 
     * @param node->denotes the sub. dir. of root dir.
     * @param space->used   for displaying directories in an oragnized manner
     */
    public void printTree(TreeNode node, String space) {
        if (node == null)
            return;

        System.out.print(space);
        System.out.print("\u251c");
        System.out.print("\u2500");
        System.out.print("\u2500");
        System.out.print("\u2500");
        System.out.println(node.root);

        if (node.child != null) {
            String newSpace = space + "     ";
            printTree(node.child, newSpace);
        }

        printTree(node.sibling, space);
    }

    /**
     * will Find a folder in current or subfolders
     * 
     * @param name->        denotes which folder we need to find
     * @param dir->denotes  current dir.
     * @param path->denotes path of the dir. found
     */
    public void findDir(String name, TreeNode dir, String path) {
        if (dir == null)
            return;
        if (dir.root.equals(name)) {
            System.out.println(path + "" + name);
            path += "";
            path += name;
        } else {
            System.out.println("Directory not present");
            return;
        }
        String childPath = path + dir.root + "\\";
        findDir(name, dir.child, childPath);
        findDir(name, dir.sibling, path);
    }

    /**
     * will Display list of all folders in the current folder along with their
     * date-time of creation. It also displays total subfolders (1st level only)
     * present in the current directory.
     */
    public void listDir() {
        int sizeOfList = paths.size();
        TreeNode node = paths.get(sizeOfList - 1);
        node = node.child;
        int count = 0;

        while (node != null) {
            System.out.println(node.createdAt + " " + node.root);
            node = node.sibling;
            count++;
        }
        System.out.println(count + " Folder<s>");
    }

    /**
     * will move back to the previous dir.
     */
    public void getBackToPreviousDir() {
        int sizeOfList = paths.size();
        paths.remove(sizeOfList - 1);
    }

    /**
     * will change dir. to provided one
     * 
     * @param inputArray->represents input provided by the user, which is converted
     *                               to array
     * @return updated path
     * @throws Exception->in case path is invalid
     */
    public String changeDir(String inputArray[]) throws Exception {
        int sizeOfList = paths.size();

        TreeNode node = paths.get(sizeOfList - 1);

        if (node.contains(inputArray[1])) {
            // change path to provided one
            TreeNode newDirectory = directory.get(inputArray[1]);
            paths.add(newDirectory);
            return path;
        } else {
            throw new Exception("The system cannot find the path specified.");
        }
    }

    /**
     * will create a dir.
     * 
     * @param inputArray->represents input provided by the user, which is converted
     *                               to array
     * @param curDir->represents     current dir.
     * @throws Exception->in case dir. already exists
     */
    public void createDir(String inputArray[]) throws Exception {
        String dirName = inputArray[1];
        int sizeOfList = paths.size();
        TreeNode curDir = paths.get(sizeOfList - 1);
        if (curDir.contains(dirName)) {
            throw new Exception(" A subdirectory or file already exits");
        } else {
            // creating new dir.
            TreeNode child = new TreeNode(dirName);

            // coonecting the child dir. with the parent dir.
            curDir.addChild(curDir, child);

            // updating list of choild nodes of parent dir.
            paths.get(sizeOfList - 1).childNodes.add(dirName);

            // adding dir. along with its object
            directory.put(dirName, child);
        }
    }
}
