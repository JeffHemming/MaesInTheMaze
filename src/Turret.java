/**
 * Created by Dad on 3/7/2015.
 */
public class Turret {
    private int x;
    private int y;
    private int face;
    private int type;//2=red,3=green,4=blue,5=purple
    private int maxup;
    private int maxleft;
    private int maxright;
    private int maxdown;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMaxup() {
        return maxup;
    }

    public void setMaxup(int maxup) {
        this.maxup = maxup;
    }

    public int getMaxleft() {
        return maxleft;
    }

    public void setMaxleft(int maxleft) {
        this.maxleft = maxleft;
    }

    public int getMaxright() {
        return maxright;
    }

    public void setMaxright(int maxright) {
        this.maxright = maxright;
    }

    public int getMaxdown() {
        return maxdown;
    }

    public void setMaxdown(int maxdown) {
        this.maxdown = maxdown;
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void turn(){
        if(this.type==2) {
            if (this.face == 4) this.face = 1;
            else this.face++;
        }
        else if(this.type==3) {
            if (this.face == 1) this.face = 4;
            else this.face--;
        }
    }

    public void fire() {

    }

    public Turret(int placex,int placey,int placeface,int typ){
        this.x=placex;
        this.y=placey;
        this.face=placeface;
        this.maxdown=0;
        this.maxleft=0;
        this.maxright=0;
        this.maxup=0;
        this.type=typ;
    }

    public Turret(Turret t){
        this.x=t.x;
        this.y=t.y;
        this.face=t.face;
        this.type=t.type;
        this.maxdown=t.maxdown;
        this.maxleft=t.maxleft;
        this.maxright=t.maxright;
        this.maxup=t.maxup;
    }
}
