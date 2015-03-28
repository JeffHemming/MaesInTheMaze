import java.io.*;
import java.util.ArrayList;

/**
 * Created by Dad on 3/7/2015.
 */
public class Main {

    public static void levelcreator() throws IOException{
        BufferedReader input = new BufferedReader(new FileReader("levels.txt" ));
        int mapsizelist=Integer.parseInt(input.readLine());
        //ArrayList<Level> levelList=new ArrayList<Level>(mapsizelist);
        for(int i=0;i<mapsizelist;i++){
            int endx=0;
            int endy=0;
            int mx=0;
            int my=0;
            String[] cr=input.readLine().split(" ");
            int columns=Integer.parseInt(cr[0]);
            int rows=Integer.parseInt(cr[1]);
            int[][] map=new int[columns][rows];
            ArrayList<Turret> tList= new ArrayList<Turret>();
            String descript=input.readLine();
            for(int j=0;j<rows;j++){
                String [] rowString=input.readLine().split(" ");
                for(int k=0;k<columns;k++){
                    map[k][j]=Integer.parseInt(rowString[k]);
                    if(map[k][j]>20&&map[k][j]<35){
                        Turret t=new Turret(k,j,map[k][j]%10,map[k][j]/10);
                        tList.add(t);
                    }
                    if(map[k][j]==(-99)){
                        endx=k;
                        endy=j;
                    }
                    if(map[k][j]==(-11)){
                        mx=k;
                        my=j;
                    }
                }
            }
            Level l=new Level(map,i+1,tList,endx,endy,descript,mx,my);
            masterLevelList.add(l);
        }
        return;
    }

    public static void getSaveGame() throws IOException {
        File file = new File("save.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedReader input = new BufferedReader(new FileReader(file));
        String s=input.readLine();
        if (s == null ){
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("0");
            bw.close();
        }
        else currentLevel=Integer.parseInt(s);
        maxReached=currentLevel;
    }

    public static TitleScreen titleScreen;
    public static int currentLevel=0, maxReached=0;
    public static ArrayList<Level> masterLevelList=new ArrayList<Level>();

    public static void main(String[] args) throws IOException {
        levelcreator();
        getSaveGame();
        titleScreen = new TitleScreen(masterLevelList);
    }
}
