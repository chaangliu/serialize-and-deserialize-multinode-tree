package sharetree;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeSp {

    public String id;
    public List<TreeNodeSp> children = new ArrayList<>();

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
