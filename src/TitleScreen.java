import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Dad on 3/9/2015.
 */
public class TitleScreen extends JFrame implements ActionListener {
    public JButton newGame,con,pickLevel,exit;
    public JLabel background;
    public static newGameAction nga;
    public static exitAction exita;
    public static plAction pla;
    public static continueAction ca;
    public ArrayList<Level> lL;

    public TitleScreen (ArrayList<Level> levelList){
        lL=levelList;
        this.setResizable(false);
        Container pane = getContentPane();
        pane.setLayout(null);

        nga=new newGameAction();
        newGame=new JButton("New Game");
        newGame.setSize(400, 40);
        newGame.setLocation(260, 420);
        newGame.setFont(new Font("Georgia", Font.BOLD, 24));
        newGame.addActionListener(nga);
        pane.add(newGame);

        ca=new continueAction();
        con=new JButton("Continue");
        con.setSize(400,40);
        con.setLocation(260, 480);
        con.setFont(new Font("Georgia", Font.BOLD, 24));
        con.addActionListener(ca);
        pane.add(con);

        pla=new plAction();
        pickLevel=new JButton("Choose Level");
        pickLevel.setSize(200,40);
        pickLevel.setLocation(260, 540);
        pickLevel.setFont(new Font("Georgia", Font.BOLD, 24));
        pickLevel.addActionListener(pla);
        pane.add(pickLevel);

        exita=new exitAction();
        exit=new JButton("Quit Game");
        exit.setSize(190,40);
        exit.setLocation(470, 540);
        exit.setFont(new Font("Georgia", Font.BOLD, 24));
        exit.addActionListener(exita);
        pane.add(exit);


        background=new JLabel();
        background.setSize(960, 640);
        background.setLocation(0, 0);
        try{
            background.setIcon(new ImageIcon(ImageIO.read(new File("image/allkids.png"))));
        }catch(IOException e) {
            System.out.println("Image not found");
        }
        pane.add(background);

        this.setSize(960,640);
        this.setVisible(true);
        this.setTitle("Maes in the Maze");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


    private class newGameAction implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Main.currentLevel=0;
            try {
                CreateNewLevel a=new CreateNewLevel(Main.currentLevel);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class continueAction implements ActionListener{
        public void actionPerformed(ActionEvent e){
            try {
                CreateNewLevel a=new CreateNewLevel(Main.currentLevel);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class exitAction implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }

    private class plAction implements ActionListener{
        public void actionPerformed(ActionEvent e){
            LevelSelect ls=new LevelSelect();
        }
    }
}
