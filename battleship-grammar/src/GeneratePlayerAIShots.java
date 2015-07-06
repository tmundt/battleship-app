import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;


/**
 * Created by thomasmundt on 11.06.15.
 */

public class GeneratePlayerAIShots extends PlayerAIShotsBaseListener{

    private static StringBuilder code;
    private static String ls = System.lineSeparator();
    private static int counter = 0;
    private static boolean[][] shotsPlayerAI;

    /**
     * @param args
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        initializeShotsPlayerAI();
        //showShotsPlayerAI();
        code = new StringBuilder();
        code.append("package org.nse.battleship;\n");
        code.append(GeneratePlayerAIShots.ls);
        code.append("import java.util.ArrayList;\n");
        code.append(GeneratePlayerAIShots.ls);
        code.append("public class GeneratedPlayerAI {\n");
        code.append("	public static ArrayList<String> generateGameShots() {\n");
        code.append("		ArrayList<String> Shots = new ArrayList<String>();\n");
        // get txt file with Shots/coordinates from player AI
        File file = null;

        // Pfad zur Metadaten-Datei in Windows - TOM
        String filePath = "E:\\repo\\github\\battleship-app\\battleship-grammar\\src\\ShotsPlayerAI.txt";
        // Pfad zur Metadaten-Datei in MAC - TOM
        //String filePath = "src/ShotsPlayerAI.txt";

        file = new File(filePath);
        if(file.exists()) {
            System.out.println("file is: " + file);
        } else {
            System.out.println("Metadaten nicht gefunden, bitte erstellen!");
            throw new FileNotFoundException("Not found");
        }
        // Get CSV lexer
        PlayerAIShotsLexer lexer = new PlayerAIShotsLexer(new ANTLRInputStream(new FileReader(file)));
        // Get a list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Pass the tokens to the parser
        PlayerAIShotsParser parser = new PlayerAIShotsParser(tokens);

        // Specify our entry point
        PlayerAIShotsParser.FileContext fileContext = parser.file();
//		PlayerAIShotsParser.RowContext rowContext = parser.row();

        // Walk it and attach our listener
        ParseTreeWalker walker = new ParseTreeWalker();
        PlayerAIShotsBaseListener listener = new GeneratePlayerAIShots();
//		walker.walk(listener, rowContext);
//		lexer.reset();
        walker.walk(listener, fileContext);

        code.append("        return Shots;\n");
        code.append("    }\n");
        code.append("}\n");
        //System.out.println("Code: ");
        //System.out.println(GeneratePlayerAIShots.code);

        byte data[] = code.toString().getBytes();

        // Get the URL to the project where the java file will be generated
        //URL url = Controller.class.getProtectionDomain().getCodeSource().getLocation();
        //String pathToFile = url.getFile().toString();

        // change path where java file will be stored
        //pathToFile = pathToFile.replace("/bin/", "/src/");
        //pathToFile = pathToFile + "org/nse/battleship/GeneratedPlayerAI.java";

        // Workaround: generate path for windows
        //pathToFile = pathToFile.replace("/D", "D");
//        String pathToFile = "/Users/thomasmundt/repo/github/battleship-app/battleship-android/src";
        String pathToFile = "E:/repo/github/battleship-app/battleship-android/src";
        pathToFile += "/GeneratedPlayerAI.java";
        System.out.println("Generating Java-Class: \n" + pathToFile);
        Path fileOut = Paths.get(pathToFile);

        // Generating/writing java file
        Files.write(fileOut, data);
    }

    public void exitRandomShot(PlayerAIShotsParser.RandomshotContext ctx ) {
        String moveType = ctx.getText();
        String move = "";
        if(moveType.equals("zufällig")) {
			System.out.println("exitValue(), Zufall!");
//            move = GeneratePlayerAIShots.generateRandomMove();
           // setShotIntoFieldAI(move);

        } else {
            move = moveType;
            setShotIntoFieldAI(move);
        }
        code.append("        Shots.add(\"" + move + "\");\n");
    }

    public static String generateRandomMove() {
//		System.out.println("Generating random move");
        int randomColumnCoord;
        int randomRowCoord;
        String move;
        do {
            // Generate random move
//			System.out.println("Entering do");
            randomRowCoord = randomInteger(0,7);
            randomColumnCoord = randomInteger(0,7);
//			System.out.println("Leaving do, has  randomRow: "+ randomRowCoord+", randowmColumn: "+ randomColumnCoord);
            if (GeneratePlayerAIShots.checkIfShotIsPresentAtField(randomRowCoord, randomColumnCoord) == false){
                //System.out.println("Wurde schon drauf geschossen!");
                // this field has not been shot at so mark it as been shot for this move
                GeneratePlayerAIShots.shotsPlayerAI[randomRowCoord][randomColumnCoord] = true;
            } // else {
//				System.out.println("Wurde noch nicht drauf geschossen");
//			}
        } while (GeneratePlayerAIShots.checkIfShotIsPresentAtField(randomColumnCoord, randomRowCoord)== false);
        //GeneratePlayerAIShots.shotsPlayerAI[randomColumnCoord][randomRowCoord] = true;
        move = GeneratePlayerAIShots.changeIntToCoordinates(randomRowCoord, randomColumnCoord);
        //System.out.println("generateRandomMove(), move: " + move);
        return move;
    }

	/*
	 * Initialize field for shots of playerAI, holds given shots of playerAI
	 */

    private static void initializeShotsPlayerAI() {
        shotsPlayerAI = new boolean[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                shotsPlayerAI[i][j] = false;
                //System.out.println("shotsPlayerAI at " + i+j+": " + shotsPlayerAI[i][j]);
            }
        }
    }

    private static int randomInteger(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    private static boolean checkIfShotIsPresentAtField (int row, int column) {
        if(GeneratePlayerAIShots.shotsPlayerAI[row][column] == false) {
            return false;
        } else {
            return true;
        }
        //return true;
    }

    private static String changeIntToCoordinates(int row, int column) {
        StringBuilder coord = new StringBuilder();
        coord.append("");
        switch(row) {
            case 0: coord.append("A");
                break;
            case 1: coord.append("B");
                break;
            case 2: coord.append("C");
                break;
            case 3: coord.append("D");
                break;
            case 4: coord.append("E");
                break;
            case 5: coord.append("F");
                break;
            case 6: coord.append("G");
        };
        coord.append(column+1); // add 1 because index starts at 0
        String coordinates = coord.toString();
//		System.out.println("changeIntToCoordinates(), coordinates: " + coordinates);
        return coordinates;
    }

    private static void showShotsPlayerAI() {
        for(int i = 0; i < 7; i++)
        {
            for(int j = 0; j < 7; j++)
            {

                //System.out.print(GeneratePlayerAIShots.shotsPlayerAI[i][j]);
            }
            System.out.println();
        }
    }

    private static void setShotIntoFieldAI(String shot) {
        int row = 0;
        //System.out.println("column of shot, setShot(): " + shot.charAt(1));
        int column =  Integer.valueOf(shot.substring(1));
        column -= 1; //reduce column number because we need the index beginning at 0
        switch(shot.charAt(0)) {
            case 'A':
                row = 0;
                break;
            case 'B':
                row = 1;
                break;
            case 'C':
                row = 2;
                break;
            case 'D':
                row = 3;
                break;
            case 'E':
                row = 4;
                break;
            case 'F':
                row = 5;
                break;
            case 'G':
                row = 6;
        }

        //System.out.println("setShotIntoFieldAI(), Coords:" + row+"," + column);
        GeneratePlayerAIShots.shotsPlayerAI[row][column] = true;
    }
}
