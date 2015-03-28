import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Dad on 3/7/2015.
 */
public class Playboard extends JFrame implements ActionListener, MouseListener {

    protected static boolean maesCanMove=true;
    int currentMover=1;
    Maes m;
    protected static JButton restart,quit;
    protected static JLabel maes,border,goal,start,floor,currentMoverPic,currName,levClear;
    protected static JTextArea desc;
    protected static JLabel [][] cells;
    protected static JLabel [] turs;
    protected static Level lev;
    protected static JLabel [] lasers;
    protected static boolean dead=false;
    protected static Actor actor;

    public Playboard(Level l) throws IOException {
        m=new Maes(l.getMstartx(),l.getMstarty());
        this.setResizable(false);
        Container pane = getContentPane();
        pane.setLayout(null);
        actor=new Actor();
        pane.setBackground(Color.BLACK);

        levClear=new JLabel();
        levClear.setSize(960, 640);
        levClear.setLocation(0, 0);
        try{
            levClear.setIcon(new ImageIcon(ImageIO.read(new File("image/levelClear.png"))));
        }catch(IOException e) {
            System.out.println("Image not found");
        }
        levClear.setVisible(false);
        pane.add(levClear);

        quit=new JButton("Quit Level");
        quit.setSize(120,40);
        quit.setLocation(800, 24);
        quit.addActionListener(actor);
        pane.add(quit);



        restart=new JButton("Restart Level");
        restart.setSize(120,40);
        restart.setLocation(800, 76);
        restart.addActionListener(actor);
        pane.add(restart);

        desc=new JTextArea(l.getDescription());
        desc.setSize(160,400);
        desc.setLocation(10, 120);
        desc.setEditable(false);
        desc.setFont(new Font("Georgia", Font.PLAIN, 18));
        desc.setLineWrap(true);
        desc.setWrapStyleWord(true);
        desc.setBackground(Color.BLACK);
        desc.setForeground(Color.WHITE);
        pane.add(desc);

        currName=new JLabel("Current Player");
        currName.setSize(170,20);
        currName.setLocation(20,5);
        currName.setFont(new Font("Georgia", Font.PLAIN, 16));
        currName.setBackground(Color.BLACK);
        currName.setForeground(Color.WHITE);
        pane.add(currName);

        maes=new JLabel();
        try{
            maes.setIcon(new ImageIcon(ImageIO.read(new File("image/maesStickFigureGreen.png"))));
        }catch(IOException e) {
            System.out.println("Image not found");
        }
        maes.setLocation(220 + m.getX() * 64, 48 + m.getY() * 64);
        maes.setSize(64, 64);
        pane.add(maes);

        goal=new JLabel();
        try{
            goal.setIcon(new ImageIcon(ImageIO.read(new File("image/stairsDown.png"))));
        }catch(IOException e) {
            System.out.println("Image not found");
        }
        goal.setLocation(220+l.getEndX()*64,48+l.getEndY()*64);
        goal.setSize(64,64);
        pane.add(goal);

        /*
        start=new JLabel();
        try{
            start.setIcon(new ImageIcon(ImageIO.read(new File("image/stairsUp.png"))));
        }catch(IOException e) {
            System.out.println("Image not found");
        }
        start.setLocation(220+l.getMstartx()*64,48+l.getMstarty()*64);
        start.setSize(64,64);
        pane.add(start);
        */

        currentMoverPic=new JLabel();
        currentMoverPic.setLocation(32,32);
        currentMoverPic.setSize(64,64);
        try{
            currentMoverPic.setIcon(new ImageIcon(ImageIO.read(new File("image/maesStickFigureGreen.png"))));
        }catch(IOException e) {
            System.out.println("Image not found");
        }
        pane.add(currentMoverPic);

        border=new JLabel();
        border.setLocation(188,16);
        border.setSize(576,576);
        try{
            border.setIcon(new ImageIcon(ImageIO.read(new File("image/stoneBorder.png"))));
        }catch(IOException e) {
            System.out.println("Image not found");
        }
        pane.add(border);

        turs=new JLabel[l.getTurretlist().size()];
        lasers=new JLabel[l.getTurretlist().size()];
        for(int i=0;i<turs.length;i++){
            lasers[i]=new JLabel();
            pane.add(lasers[i]);
            turs[i]=new JLabel();
            turs[i].setSize(64,64);
            turs[i].setLocation(220+l.getTurretlist().get(i).getX()*64,48+l.getTurretlist().get(i).getY()*64);
            try{
                switch(l.getTurretlist().get(i).getType()) {
                    case 2:
                        if (l.getTurretlist().get(i).getFace() == 1)
                            turs[i].setIcon(new ImageIcon(ImageIO.read(new File("image/turretRed1.png"))));
                        if (l.getTurretlist().get(i).getFace() == 2)
                            turs[i].setIcon(new ImageIcon(ImageIO.read(new File("image/turretRed2.png"))));
                        if (l.getTurretlist().get(i).getFace() == 3)
                            turs[i].setIcon(new ImageIcon(ImageIO.read(new File("image/turretRed3.png"))));
                        if (l.getTurretlist().get(i).getFace() == 4)
                            turs[i].setIcon(new ImageIcon(ImageIO.read(new File("image/turretRed4.png"))));
                        break;
                    case 3:
                        if (l.getTurretlist().get(i).getFace() == 1)
                            turs[i].setIcon(new ImageIcon(ImageIO.read(new File("image/turretGreen1.png"))));
                        if (l.getTurretlist().get(i).getFace() == 2)
                            turs[i].setIcon(new ImageIcon(ImageIO.read(new File("image/turretGreen2.png"))));
                        if (l.getTurretlist().get(i).getFace() == 3)
                            turs[i].setIcon(new ImageIcon(ImageIO.read(new File("image/turretGreen3.png"))));
                        if (l.getTurretlist().get(i).getFace() == 4)
                            turs[i].setIcon(new ImageIcon(ImageIO.read(new File("image/turretGreen4.png"))));
                        break;
                }
            }catch(IOException e) {
                System.out.println("Image not found");
            }
            boolean keepitup=true;
            while(keepitup) {
                for (int j = 1; j < l.getTurretlist().get(i).getY() + 1&&keepitup; j++) {
                    if (l.getMap()[l.getTurretlist().get(i).getX()][l.getTurretlist().get(i).getY() - j] <1)
                        l.getTurretlist().get(i).setMaxup(l.getTurretlist().get(i).getMaxup() + 1);
                    else keepitup=false;
                }
                keepitup=false;
            }
            keepitup=true;
            while (keepitup) {
                for (int j = l.getTurretlist().get(i).getY() + 1; j < 8&&keepitup; j++) {
                    if (l.getMap()[l.getTurretlist().get(i).getX()][j] <1)
                        l.getTurretlist().get(i).setMaxdown(l.getTurretlist().get(i).getMaxdown() + 1);
                    else keepitup=false;
                }
                keepitup=false;
            }
            keepitup=true;
            while (keepitup) {
                for (int j = 1; j < l.getTurretlist().get(i).getX() + 1&&keepitup; j++) {
                    if (l.getMap()[l.getTurretlist().get(i).getX() - j][l.getTurretlist().get(i).getY()] <1)
                        l.getTurretlist().get(i).setMaxleft(l.getTurretlist().get(i).getMaxleft() + 1);
                    else keepitup=false;
                }
                keepitup=false;
            }
            keepitup=true;
            while (keepitup) {
                for (int j = l.getTurretlist().get(i).getX() + 1; j < 8&&keepitup; j++) {
                    if (l.getMap()[j][l.getTurretlist().get(i).getY()] <1)
                        l.getTurretlist().get(i).setMaxright(l.getTurretlist().get(i).getMaxright() + 1);
                    else keepitup=false;
                }
                keepitup=false;
            }
            pane.add(turs[i]);
        }

        cells=new JLabel[8][8];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                cells[i][j]=new JLabel();
                cells[i][j].setSize(64,64);
                cells[i][j].setLocation(220+(i*64),48+j*64);
                if(j==0||l.getMap()[i][j-1]==(1)) {
                    try {
                        cells[i][j].setIcon(new ImageIcon(ImageIO.read(new File("image/stoneWall.png"))));
                    } catch (IOException e) {
                        System.out.println("Image not found");
                    }
                }
                if(l.getMap()[i][j]==1){
                    try{
                        cells[i][j].setIcon(new ImageIcon(ImageIO.read(new File("image/stoneTop.png"))));
                    }catch(IOException e) {
                        System.out.println("Image not found");
                    }
                    //cells[i][j].setBackground(Color.WHITE);
                    //cells[i][j].setOpaque(true);
                }

                pane.add(cells[i][j]);
            }
        }


        floor=new JLabel();
        floor.setSize(512,512);
        floor.setLocation(220, 48);
        floor.setBackground(Color.LIGHT_GRAY);
        floor.setOpaque(true);
        pane.add(floor);


        pane.addMouseListener(this);
        this.setSize(960,640);
        this.setVisible(true);
        this.setTitle("Maes in the Maze");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        lev=l;
    }

    public class Actor implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==quit){
                dispose();
            }
            if(e.getSource()==restart){
                dispose();
                try {
                    CreateNewLevel cl=new CreateNewLevel(Main.currentLevel);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
    public void actionPerformed(ActionEvent e){

    }
    private static class finishLevel extends TimerTask{
        @Override
        public void run() {
            CreateNewLevel.pb.dispose();
            Main.currentLevel++;
            try {
                CreateNewLevel a=new CreateNewLevel(Main.currentLevel);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    private static class redrawLasers extends TimerTask{
        @Override
        public void run() {
            for(int i=0;i<lasers.length;i++){
                lasers[i].setVisible(false);
            }

            maesCanMove=true;
        }
    }
    private class deadMaes extends TimerTask{
        @Override
        public void run(){
            JOptionPane.showMessageDialog(null, "MAES IS DEAD!  YOU MONSTER!");
            dead=false;
            dispose();
            try {
                CreateNewLevel cl=new CreateNewLevel(Main.currentLevel);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(maesCanMove) {
            boolean moved = false;
            int x = e.getX();
            int y = e.getY();
            if (x > 220 + m.getX() * 64 && x < 220 + m.getX() * 64 + 64 && y > 48 + m.getY() * 64 && y < 48 + m.getY() * 64 + 64) {
                currentMover = 1;

            } else if (y > 48 + m.getY() * 64 && y < 48 + m.getY() * 64 + 64) {
                if (x > 220 + m.getX() * 64 + 64) {
                    if (m.getX() < 7 && (lev.getMap()[m.getX() + 1][m.getY()] < 1)) {
                        m.setX(m.getX() + 1);
                        maes.setLocation(220 + m.getX() * 64, 48 + m.getY() * 64);
                        moved = true;
                    } else Sound.soundEffect("sound\\Bump.wav");
                } else if (x < 220 + m.getX() * 64) {
                    if (m.getX() > 0 && (lev.getMap()[m.getX() - 1][m.getY()] < 1)) {
                        m.setX(m.getX() - 1);
                        maes.setLocation(220 + m.getX() * 64, 48 + m.getY() * 64);
                        moved = true;
                    } else Sound.soundEffect("sound\\Bump.wav");
                }
            } else if (x > 220 + m.getX() * 64 && x < 220 + m.getX() * 64 + 64) {
                if (y > 48 + m.getY() * 64 + 64) {
                    if (m.getY() < 7 && (lev.getMap()[m.getX()][m.getY() + 1] < 1)) {
                        m.setY(m.getY() + 1);
                        maes.setLocation(220 + m.getX() * 64, 48 + m.getY() * 64);
                        moved = true;
                    } else Sound.soundEffect("sound\\Bump.wav");
                } else if (y < 48 + m.getY() * 64) {
                    if (m.getY() > 0 && (lev.getMap()[m.getX()][m.getY() - 1] < 1)) {
                        m.setY(m.getY() - 1);
                        maes.setLocation(220 + m.getX() * 64, 48 + m.getY() * 64);
                        moved = true;
                    } else Sound.soundEffect("sound\\Bump.wav");
                }
            }
            if (moved) {
                maesCanMove = false;
                Sound.soundEffect("sound\\Laser.wav");
                for (int i = 0; i < lev.getTurretlist().size(); i++) {
                    lev.getTurretlist().get(i).turn();
                    try {
                        if (lev.getTurretlist().get(i).getType() == 2) {
                            if (lev.getTurretlist().get(i).getFace() == 1)
                                turs[i].setIcon(new ImageIcon(ImageIO.read(new File("image/turretRed1.png"))));
                            if (lev.getTurretlist().get(i).getFace() == 2)
                                turs[i].setIcon(new ImageIcon(ImageIO.read(new File("image/turretRed2.png"))));
                            if (lev.getTurretlist().get(i).getFace() == 3)
                                turs[i].setIcon(new ImageIcon(ImageIO.read(new File("image/turretRed3.png"))));
                            if (lev.getTurretlist().get(i).getFace() == 4)
                                turs[i].setIcon(new ImageIcon(ImageIO.read(new File("image/turretRed4.png"))));
                        } else if (lev.getTurretlist().get(i).getType() == 3) {
                            if (lev.getTurretlist().get(i).getFace() == 1)
                                turs[i].setIcon(new ImageIcon(ImageIO.read(new File("image/turretGreen1.png"))));
                            if (lev.getTurretlist().get(i).getFace() == 2)
                                turs[i].setIcon(new ImageIcon(ImageIO.read(new File("image/turretGreen2.png"))));
                            if (lev.getTurretlist().get(i).getFace() == 3)
                                turs[i].setIcon(new ImageIcon(ImageIO.read(new File("image/turretGreen3.png"))));
                            if (lev.getTurretlist().get(i).getFace() == 4)
                                turs[i].setIcon(new ImageIcon(ImageIO.read(new File("image/turretGreen4.png"))));
                        }
                    } catch (IOException ee) {
                        System.out.println("Image not found");
                    }
                }
                for (int i = 0; i < lev.getTurretlist().size(); i++) {
                    Turret t = lev.getTurretlist().get(i);
                    int face = t.getFace();
                    switch (face) {
                        case 1:
                            lasers[i].setLocation(220 + t.getX() * 64, 48 + t.getY() * 64 - t.getMaxup() * 64);
                            lasers[i].setSize(64, t.getMaxup() * 64);
                            try {
                                if (t.getType() == 2) {
                                    switch (t.getMaxup()) {
                                        case 1:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical1.png"))));
                                            break;
                                        case 2:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical2.png"))));
                                            break;
                                        case 3:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical3.png"))));
                                            break;
                                        case 4:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical4.png"))));
                                            break;
                                        case 5:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical5.png"))));
                                            break;
                                        case 6:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical6.png"))));
                                            break;
                                        case 7:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical7.png"))));
                                            break;
                                    }
                                } else if (t.getType() == 3) {
                                    switch (t.getMaxup()) {
                                        case 1:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical1g.png"))));
                                            break;
                                        case 2:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical2g.png"))));
                                            break;
                                        case 3:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical3g.png"))));
                                            break;
                                        case 4:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical4g.png"))));
                                            break;
                                        case 5:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical5g.png"))));
                                            break;
                                        case 6:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical6g.png"))));
                                            break;
                                        case 7:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical7g.png"))));
                                            break;
                                    }
                                }
                            } catch (IOException ee) {
                                System.out.println("Image not found");
                            }

                            if (m.getX() == t.getX() && m.getY() > t.getY() - t.getMaxup() - 1 && m.getY() < t.getY() && !dead) {
                                dead = true;
                                Timer timer2 = new Timer();
                                timer2.schedule(new deadMaes(), 500);
                            }
                            break;
                        case 2:
                            lasers[i].setLocation(220 + (t.getX() - t.getMaxleft()) * 64, 48 + t.getY() * 64);
                            lasers[i].setSize(t.getMaxleft() * 64, 64);
                            try {
                                if (t.getType() == 2) {
                                    switch (t.getMaxleft()) {
                                        case 1:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal1.png"))));
                                            break;
                                        case 2:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal2.png"))));
                                            break;
                                        case 3:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal3.png"))));
                                            break;
                                        case 4:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal4.png"))));
                                            break;
                                        case 5:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal5.png"))));
                                            break;
                                        case 6:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal6.png"))));
                                            break;
                                        case 7:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal7.png"))));
                                            break;
                                    }
                                } else if (t.getType() == 3) {
                                    switch (t.getMaxleft()) {
                                        case 1:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal1g.png"))));
                                            break;
                                        case 2:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal2g.png"))));
                                            break;
                                        case 3:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal3g.png"))));
                                            break;
                                        case 4:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal4g.png"))));
                                            break;
                                        case 5:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal5g.png"))));
                                            break;
                                        case 6:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal6g.png"))));
                                            break;
                                        case 7:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal7g.png"))));
                                            break;
                                    }
                                }
                            } catch (IOException ee) {
                                System.out.println("Image not found");
                            }

                            if (m.getY() == t.getY() && m.getX() > t.getX() - t.getMaxleft() - 1 && m.getX() < t.getX() && !dead) {
                                dead = true;
                                Timer timer2 = new Timer();
                                timer2.schedule(new deadMaes(), 500);
                            }
                            break;
                        case 3:
                            lasers[i].setLocation(220 + t.getX() * 64, 48 + t.getY() * 64 + 64);
                            lasers[i].setSize(64, t.getMaxdown() * 64);
                            try {
                                if (t.getType() == 2) {
                                    switch (t.getMaxdown()) {
                                        case 1:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical1.png"))));
                                            break;
                                        case 2:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical2.png"))));
                                            break;
                                        case 3:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical3.png"))));
                                            break;
                                        case 4:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical4.png"))));
                                            break;
                                        case 5:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical5.png"))));
                                            break;
                                        case 6:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical6.png"))));
                                            break;
                                        case 7:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical7.png"))));
                                            break;
                                    }
                                } else if (t.getType() == 3) {
                                    switch (t.getMaxdown()) {
                                        case 1:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical1g.png"))));
                                            break;
                                        case 2:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical2g.png"))));
                                            break;
                                        case 3:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical3g.png"))));
                                            break;
                                        case 4:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical4g.png"))));
                                            break;
                                        case 5:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical5g.png"))));
                                            break;
                                        case 6:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical6g.png"))));
                                            break;
                                        case 7:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserVertical7g.png"))));
                                            break;
                                    }
                                }
                            } catch (IOException ee) {
                                System.out.println("Image not found");
                            }
                            if (m.getX() == t.getX() && m.getY() <= t.getY() + t.getMaxdown() && m.getY() > t.getY() && !dead) {
                                dead = true;
                                Timer timer2 = new Timer();
                                timer2.schedule(new deadMaes(), 500);
                            }
                            break;
                        case 4:
                            lasers[i].setLocation(220 + t.getX() * 64 + 64, 48 + t.getY() * 64);
                            lasers[i].setSize(t.getMaxright() * 64, 64);
                            try {
                                if (t.getType() == 2) {
                                    switch (t.getMaxright()) {
                                        case 1:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal1.png"))));
                                            break;
                                        case 2:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal2.png"))));
                                            break;
                                        case 3:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal3.png"))));
                                            break;
                                        case 4:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal4.png"))));
                                            break;
                                        case 5:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal5.png"))));
                                            break;
                                        case 6:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal6.png"))));
                                            break;
                                        case 7:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal7.png"))));
                                            break;
                                    }
                                } else if (t.getType() == 3) {
                                    switch (t.getMaxright()) {
                                        case 1:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal1g.png"))));
                                            break;
                                        case 2:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal2g.png"))));
                                            break;
                                        case 3:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal3g.png"))));
                                            break;
                                        case 4:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal4g.png"))));
                                            break;
                                        case 5:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal5g.png"))));
                                            break;
                                        case 6:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal6g.png"))));
                                            break;
                                        case 7:
                                            lasers[i].setIcon(new ImageIcon(ImageIO.read(new File("image/laserHorizontal7g.png"))));
                                            break;
                                    }
                                }
                            } catch (IOException ee) {
                                System.out.println("Image not found");
                            }

                            if (m.getY() == t.getY() && m.getX() <= t.getX() + t.getMaxright() && m.getX() > t.getX() && !dead) {
                                dead = true;
                                Timer timer2 = new Timer();
                                timer2.schedule(new deadMaes(), 500);
                            }
                            break;
                        default:
                            break;
                    }
                    for (int k = 0; k < lasers.length; k++) {
                        lasers[k].setVisible(true);
                    }
                }
                Timer timer1 = new Timer();
                timer1.schedule(new redrawLasers(), 500);
                if (m.getX() == lev.getEndX() && m.getY() == lev.getEndY()) {
                    levClear.setVisible(true);
                    // Main.currentLevel++;
                    Sound.soundEffect("sound\\Egress.wav");
                    File file = new File("save.txt");

                    try {
                        BufferedReader input = new BufferedReader(new FileReader(file));
                        String s = input.readLine();
                        if (Main.currentLevel + 1 > Integer.parseInt(s)) {
                            FileWriter fw = null;
                            try {
                                fw = new FileWriter(file.getAbsoluteFile());
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            BufferedWriter bw = new BufferedWriter(fw);
                            try {
                                bw.write(Integer.toString(Main.currentLevel++));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            try {
                                bw.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else {
                            FileWriter fw = null;
                            try {
                                fw = new FileWriter(file.getAbsoluteFile());
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            BufferedWriter bw = new BufferedWriter(fw);
                            try {
                                bw.write(s);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            try {
                                bw.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    //make it wait
                    Timer timerFL = new Timer();
                    timer1.schedule(new finishLevel(), 2000);

                }
            }
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
