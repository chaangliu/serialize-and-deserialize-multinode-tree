## Serialize and Deserialize a Multi-node Tree

---

Serialize and deserialize a binary tree is a platitude, this repo demonstrates how to Serialize and deserialize a multi-node tree, both recursively and iteratively.

Basically it is the idea of recording the children amount of each node.

For instance, a multi-child tree like below
  ```
           1
          /|\
         2 3 4
        / \
       5   6
  ```
will be serialized as:

#### Recursively:
"1#3,2#2,5#0,6#0,3#0,4#0,", each item representing: id#childCount.

#### Iteratively:
"1#3,2#2,3#0,4#0,5#0,6#0,", each item representing: id#childCount.


*In Addition, recursive approach will probably cause ```StackOverFlow``` exception.
According to my test on my MBP(Core i5, 8GbRAM), 19,000~20,000 recursion will cause ```StackOverFlow``` exception,
which means when there's more than 19,000 nodes, recursive approach may not work.


---
To realize binary tree serialization/deserialization, see also:
[serialize-and-deserialize-binary-tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/)
