## Serialize and Deserialize a Multi-node Tree

---

Serialize and deserialize a binary tree is a platitude, this repo demonstrates how to Serialize and deserialize a multi-node tree.

Basically it is the idea of preOrder dfs. Unlike binary tree, I record the child number of each node.

As a result, a multi-child tree like below
  ```
           1
          /|\
         2 3 4
        / \
       5   6
  ```
will be serialized as: "1#3,2#2,5#0,6#0,3#0,4#0,", representing: id#childCount.



---
See also:
[serialize-and-deserialize-binary-tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/)
