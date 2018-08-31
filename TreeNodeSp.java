package SerializeAandDeserializeMultinodeTree;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeSp {
    /**
     * Node's id.
     */
    public String id;
    /**
     * Node's children List.
     */
    public List<TreeNodeSp> children = new ArrayList<>();
    /**
     * Number of children of this node.
     * note: sometimes when deserializing, we often need to assign a value to childCount
     * resolved from a string, since we just don't have children added in the list.
     */
    public long childCount = children.size();

    TreeNodeSp(String id) {
        this.id = id;
    }

    TreeNodeSp(long id) {
        this.id = id + "";
    }

    public void addChild(TreeNodeSp node) {
        if (node == null) {
            return;
        }
        children.add(node);
    }
}
