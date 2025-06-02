package ai;

public class Node {
    Node parent;
    public int col;
    public int row;
    int fCost;
    int hCost;
    int gCost;
    boolean solid;
    boolean open;
    boolean checked;

    public Node(int col, int row) {
        this.col = col;
        this.row = row;
    }
}
