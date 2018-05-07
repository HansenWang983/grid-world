//A helper class in using the LinkedList<OccupantInCol>
public class OccupantInCol {
    private Object occupant;
    private int col;

    public OccupantInCol(Object o, int c) {
        occupant = o;
        col = c;
    }

    public void setObject(Object o) {
        occupant = o;
    }

    public void setCol(int c) {
        col = c;
    }

    public Object getObject() {
        return occupant;
    }

    public int getCol() {
        return col;
    }
}
