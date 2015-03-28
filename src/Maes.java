/**
 * Created by Dad on 3/7/2015.
 */
public class Maes {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    private boolean moved;

    public Maes(int startx,int starty) {
        this.x=startx;
        this.y=starty;
        moved=false;
    }
}
