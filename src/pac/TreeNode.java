package pac;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TreeNode {
    // name of the dir.
    String root;
    // time of creation of the dir.
    Date createdAt = new Date();
    // child of the dir.
    TreeNode child;
    // sibling of the child of the dir.
    TreeNode sibling;

    // contain list of child nodes in the present dir.
    List<String> childNodes = new ArrayList<>();

    /**
     * checks whether the dir. contains particular sub dir. or not
     * 
     * @param node->denotes the sub dir.
     * @return whether the dir. contains particular sub dir. or not
     */
    boolean contains(String node) {
        if (childNodes.contains(node))
            return true;
        else
            return false;
    }

    /**
     * constructor for initializing the dir.
     * 
     * @param root->name of the dir.
     */
    TreeNode(String root) {
        this.root = root;
        this.child = null;
        this.sibling = null;
        createdAt = new Date();
    }

    /**
     * function used to add child nodes to the parent dir.
     * 
     * @param node->denotes   the parent dir.
     * @param subDir->denotes the child/sub dir.
     * @return
     */
    public boolean addChild(TreeNode node, TreeNode subDir) {
        if (node.child == null) {
            node.child = subDir;
            return true;
        }

        else {
            TreeNode childNode = node.child;
            while (childNode.sibling != null) {
                childNode = childNode.sibling;
            }
            childNode.sibling = subDir;
            return true;
        }
    }
}
