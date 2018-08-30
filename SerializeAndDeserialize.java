package sharetree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//  a multi-child tree like below
//           1
//          /|\
//         2 3 4
//        / \
//       5   6
//  will be serialized as: "1#3,2#2,5#0,6#0,3#0,4#0,", representing: id#childCount

public class SerializeAndDeserialize {
    private static final String SPLITTER = ",";

    private String serialize(TreeNodeSp root) {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }

    private void preOrder(TreeNodeSp node, StringBuilder sb) {
        sb.append(node.id).append("#").append(node.children.size()).append(SPLITTER);
        for (int i = 0; i < node.children.size(); i++) {
            preOrder(node.children.get(i), sb);
        }
    }

    // Decodes encoded data to a tree.
    private TreeNodeSp deserialize(String data) {
        Queue<String> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(data.split(SPLITTER)));
        return buildTree(queue);
    }

    // Can be used to create a new tree during mock test, or deserialize a tree represented as a queue.
    private TreeNodeSp buildTree(Queue<String> queue) {
        String item = queue.poll();
        String[] item_array = item.split("#");
        String id = item_array[0];
        long childCount = Long.valueOf(item_array[1]);
        TreeNodeSp node = new TreeNodeSp(id);
        for (int j = 0; j < childCount; j++) {
            node.children.add(buildTree(queue));
        }
        return node;
    }

    // add a node as a child of the parent node
    private void addNode(TreeNodeSp root, String parentId, String childId) {
        recursion(root, parentId, childId);
    }

    private void recursion(TreeNodeSp node, String parentId, String childId) {
        if (node == null) return;
        if (parentId.equals(node.id)) {
            node.addChild(new TreeNodeSp(childId));
            return;
        }
        for (int i = 0; i < node.children.size(); i++) {
            recursion(node.children.get(i), parentId, childId);
        }
    }


    public static void main(String[] args) {
        //serialize
        TreeNodeSp node1 = new SerializeAndDeserialize().mockTest();
        System.out.println("Serialized tree node1: " + new SerializeAndDeserialize().serialize(node1));

        //deserialize
        TreeNodeSp node2 = new SerializeAndDeserialize().deserialize("1#3,2#2,5#0,6#0,3#0,4#0,");

        //deserialize node2
        System.out.println("Serialized tree node2: " + new SerializeAndDeserialize().serialize(node2));

        //add a node to node2
        new SerializeAndDeserialize().addNode(node2, "2", "777");
        System.out.println("Node2 after adding a node: " + new SerializeAndDeserialize().serialize(node2));

        System.out.println("---- End of Execution ----");

    }

    private TreeNodeSp mockTest() {
        TreeNodeSp node1 = new TreeNodeSp(1);
        TreeNodeSp node2 = new TreeNodeSp(2);
        TreeNodeSp node3 = new TreeNodeSp(3);
        TreeNodeSp node4 = new TreeNodeSp(4);
        TreeNodeSp node5 = new TreeNodeSp(5);
        TreeNodeSp node6 = new TreeNodeSp(6);
        TreeNodeSp node7 = new TreeNodeSp(7);
        node1.addChild(node2);
        node1.addChild(node3);
        node1.addChild(node4);
        node2.addChild(node5);
        node2.addChild(node7);
        node2.addChild(node6);
        return node1;
    }
}
