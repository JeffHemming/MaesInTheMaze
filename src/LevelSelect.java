import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by Dad on 3/9/2015.
 */
public class LevelSelect extends JFrame implements ActionListener {
    protected static JButton[] levelButtons;
    protected static lsAction lsa;
    protected static JLabel title;

    public LevelSelect(){
        this.setResizable(false);
        Container pane = getContentPane();
        pane.setLayout(null);
        lsa=new lsAction();

        title=new JLabel("Select a Level");
        title.setFont(new Font("Georgia", Font.BOLD, 24));
        title.setSize(453,64);
        title.setLocation(253,0);
        try{
            title.setIcon(new ImageIcon(ImageIO.read(new File("image/selectLevelTitle.png"))));
        }catch(IOException ee) {
            System.out.println("Image not found");
        }
        pane.add(title);

        levelButtons=new JButton[Main.maxReached];
        for(int i=0;i<Main.maxReached+1;i++){
            levelButtons[i]=new JButton(Integer.toString(i+1));
            levelButtons[i].setSize(50,50);
            levelButtons[i].setLocation(50+55*(i/20),100+(55*(i%20)));
            levelButtons[i].addActionListener(lsa);
            levelButtons[i].setFont(new Font("Georgia", Font.BOLD, 16));
            pane.add(levelButtons[i]);
        }

        pane.setBackground(Color.BLACK);
        this.setSize(960,640);
        this.setVisible(true);
        this.setTitle("Maes in the Maze");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private class lsAction implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Object l=e.getSource();
            JButton lb=(JButton)l;
            try {
                Main.currentLevel=(Integer.parseInt(lb.getText())-1);
                CreateNewLevel a=new CreateNewLevel(Main.currentLevel);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            dispose();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
