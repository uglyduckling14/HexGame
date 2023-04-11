import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Assignment6Driver {
    public static void main(String[] args) {

        //testGame();
        playGame("moves1.txt");
        System.out.println();
        playGame("moves2.txt");
    }

    private static void playGame(String filename) {
        File file = new File(filename);
        HexGame game = new HexGame(11);
        boolean red= true;
        boolean won=false;
        try (Scanner input = new Scanner(file)) {
            while(input.hasNextLine()&& !won){
                String line = input.nextLine();
                int value = Integer.parseInt(line);
                if(red){
                    won=game.playBlue(value,false);
                    red= false;
                }else{
                    won=game.playRed(value, false);
                    red=true;
                }
            }
            printGrid(game);
        } catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the moves file: " + ex);
        }
    }
    private static void testGame() {
        HexGame game = new HexGame(11);

        System.out.println("--- red ---");
        game.playRed(1, true);
        game.playRed(11, true);
        game.playRed(122 - 12, true);
        game.playRed(122 - 11, true);
        game.playRed(122 - 10, true);
        game.playRed(121, true);
        game.playRed(61, true);

        System.out.println("--- blue ---");
        game.playBlue(1, true);
        game.playBlue(2, true);
        game.playBlue(11, true);
        game.playBlue(12, true);
        game.playBlue(121, true);
        game.playBlue(122 - 11, true);
        game.playBlue(62, true);

        printGrid(game);
    }


    private static void printGrid(HexGame game) {
        int i =1;
        String tabs="";
        for (String s:game.teams) {
            if(i>(game.size* game.size)+1||i==1){
                i++;
                continue;
            }
            if(i%11==2){
                System.out.println();
                System.out.print(tabs);
                tabs+=" ";
            }
            if(s==null){
                System.out.print("\u001B[37m"+0+"\u001B[37m"+" ");
            }else{
                if(s.equals("R")){
                    System.out.print("\u001B[31m"+s+"\u001B[31m"+" ");
                }else {
                    System.out.print("\u001B[34m"+s+"\u001B[34m" + " ");
                }
            }
            i++;
        }
    }

}
