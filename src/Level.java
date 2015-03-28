import java.util.ArrayList;

/**
 * Created by Dad on 3/7/2015.
 */
public class Level {
    private int[][] map;
    private int number;
    private int endX;
    private int endY;
    private ArrayList<Turret> turretlist;
    private int mstartx;
    private int mstarty;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public ArrayList<Turret> getTurretlist() {
        return turretlist;
    }

    public void setTurretlist(ArrayList<Turret> turretlist) {
        this.turretlist = turretlist;
    }

    public int getMstartx() {
        return mstartx;
    }

    public void setMstartx(int mstartx) {
        this.mstartx = mstartx;
    }

    public int getMstarty() {
        return mstarty;
    }

    public void setMstarty(int mstarty) {
        this.mstarty = mstarty;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Level(int[][]lmap,int num,ArrayList<Turret> turretl,int x,int y,String desc,int mx,int my){
        this.map=lmap;
        this.number=num;
        this.turretlist=turretl;
        this.endX=x;
        this.endY=y;
        this.description=desc;
        this.mstartx=mx;
        this.mstarty=my;
    }

    public Level(Level l){
        this.number=l.number;
        this.endX=l.endX;
        this.endY=l.endY;
        this.description=l.description;
        this.mstartx=l.mstartx;
        this.mstarty=l.mstarty;
        this.turretlist=new ArrayList<Turret>();
        for (Turret sourceTurret : l.turretlist) {
            this.turretlist.add(new Turret(sourceTurret));
        }
        this.map=l.map;
    }
}
