package SerializeAandDeserializeMultinodeTree;


import java.util.LinkedList;
import java.util.Queue;

//  a multi-child tree like below
//           1
//          /|\
//         2 3 4
//        / \
//       5   6
//  will be serialized as: "1#3,2#2,3#0,4#0,5#0,6#0,", each item representing: id#childCount

public class Iterative {
    private static final String SPLITTER = ",";

    /**
     * Serialize a node to a string iteratively.
     */
    public String serialize(TreeNodeSp root) {
        if (root == null) return "";
        Queue<TreeNodeSp> queue = new LinkedList<>();
        StringBuilder res = new StringBuilder();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNodeSp node = queue.poll();
            res.append(node.id).append("#").append(node.children.size()).append(SPLITTER);
            queue.addAll(node.children);
        }
        return res.toString();
    }

    /**
     * Deserialize a string to a node iteratively.
     */
    public TreeNodeSp deserialize(String data) {
        if (data == null || data.equals("")) return null;
        Queue<TreeNodeSp> queue = new LinkedList<>();
        String[] pairs = data.split(SPLITTER); // => ["1#3","2#2","3#0","4#0","5#0","6#0"]
        String[] item = pairs[0].split("#"); // => ["1","3"]
        String id = item[0];
        TreeNodeSp root = new TreeNodeSp(id);
        root.childCount = Long.valueOf(item[1]);
        queue.add(root);
        for (int i = 1; i < pairs.length; i++) {
            // poll out a node from the queue to be the parent node
            TreeNodeSp parent = queue.poll();
            for (int j = 0; j < parent.childCount; j++) {
                item = pairs[i].split("#");
                // the outer for loop will perform i++ when j != parent.childCount - 1
                if (j != parent.childCount - 1) {
                    i++;
                }
                id = item[0];
                TreeNodeSp child = new TreeNodeSp(id);
                child.childCount = Long.valueOf(item[1]);
                parent.addChild(child);
                queue.add(child);
            }
        }
        return root;
    }

    /**
     * add a node as a child of the parent node
     * [Reminder]: it is ok to store nodes into a collection separately, as long as you don't assign
     * the node the another reference, you can always add nodes to a node queried from the collection.
     */
    private void addNode(TreeNodeSp root, String parentId, String childId) {
        int curNum = 1;
        int nextNum = 0;
        Queue<TreeNodeSp> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNodeSp node = queue.poll();
            if (parentId.equals(node.id)) {
                node.addChild(new TreeNodeSp(childId));
                return;
            }
            curNum--;
            for (int i = 0; i < node.childCount; i++) {
                queue.add(node.children.get(i));
                nextNum++;
            }
            if (curNum == 0) {
                curNum = nextNum;
                nextNum = 0;
            }
        }
    }

    public static void main(String[] args) {
        //serialize
        TreeNodeSp node1 = new Iterative().mockTest();
        System.out.println("Serialized tree node1: " + new Iterative().serialize(node1));

        //deserialize
        TreeNodeSp node2 = new Iterative().deserialize("1#3,2#2,3#0,4#0,5#0,6#0,");

        //deserialize node2
        System.out.println("Serialized tree node2: " + new Iterative().serialize(node2));

        //add a node to node2
        new Iterative().addNode(node2, "2", "777");
        System.out.println("Node2 after adding a node: " + new Iterative().serialize(node2));

        System.out.println("---- End of Execution ----");

    }

    private TreeNodeSp mockTest() {
        TreeNodeSp node1 = new TreeNodeSp(1);
        TreeNodeSp node2 = new TreeNodeSp(2);
        TreeNodeSp node3 = new TreeNodeSp(3);
        TreeNodeSp node4 = new TreeNodeSp(4);
        TreeNodeSp node5 = new TreeNodeSp(5);
        TreeNodeSp node6 = new TreeNodeSp(6);
        node1.addChild(node2);
        node1.addChild(node3);
        node1.addChild(node4);
        node2.addChild(node5);
        node2.addChild(node6);
        return node1;
    }
}
