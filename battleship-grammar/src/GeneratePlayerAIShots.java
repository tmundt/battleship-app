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
import java.sql.Array;
import java.util.*;
import java.util.function.BooleanSupplier;


/**
 * Created by thomasmundt on 11.06.15.
 */

public class GeneratePlayerAIShots extends PlayerAIShotsBaseListener{

    private static StringBuilder code;
    private static String ls = System.lineSeparator();
    private static int counter = 0;
    private static boolean[][] shotsPlayerAI;
    private static Map<String, Boolean> mapShotsPlayerAI;

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
        File file;

        // Pfad zur Metadaten-Datei in Windows - TOM
        String filePath = "E:\\repo\\github\\battleship-app\\battleship-grammar\\src\\ShotsPlayerAI.txt";
        // Pfad zur Metadaten-Datei in MAC - TOM
        //String filePath = "/Users/thomasmundt/repo/github/battleship-app/battleship-grammar/src/ShotsPlayerAI.txt";

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

        // Absoluter Pfad zum Android-Projekt zur Speicherung der generierten Java-Klasse
        // Einstellung für Josi/Windows
        //String pathToFile = "D:/coding/repo/github/battleship-app/battleship-android/src"

        // Einstellung für Tom/Windows
        String pathToFile = "E:/repo/github/battleship-app/battleship-android/src";

        // Einstellung für Tom/Mac
        //String pathToFile = "/Users/thomasmundt/repo/github/battleship-app/battleship-android/src";
        pathToFile += "/org/nse/battleship";
        // Einstellung für Tom/Windows
//        String pathToFile = "E:/repo/github/battleship-app/battleship-android/src";
        pathToFile += "/GeneratedPlayerAI.java";
        System.out.println("Generating Java-Class: \n" + pathToFile);
        Path fileOut = Paths.get(pathToFile);

        // Generating/writing java file
        Files.write(fileOut, data);
    }

    public void enterRandomshot(PlayerAIShotsParser.RandomshotContext ctx ) {
        String moveType = ctx.getText();
        System.out.println("enterRandomShot");
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

    /**
     * Verarbeitung einer Zeile (row) zu Beginn
     * @param ctx Parserkontext
     */
    public void exitRow(PlayerAIShotsParser.RowContext ctx) {
        boolean isRandom = false;
        boolean isLeft = false;
        boolean isRight = false;
        boolean isCentral = false;
        String shot = "";

        // Ausgabe der gesamten Zeile
        String output = ctx.getText();
        System.out.println("ROW EXIT: " + output);
        System.out.print("Schuss ist:  ");
        if (output.contains("zufaellig")) {
            System.out.print("zufällig");
            isRandom = true;
        }
        if(output.contains("linkslastig")) {
            if(isRandom) {
                System.out.print(", ");
            }
            isLeft = true;
            if(isRandom) {
                System.out.println("im linken Bereich.");
            }

        }
        if(output.contains("rechtslastig")) {
            if(isRandom) {
                System.out.print(", ");
            }
            isRight = true;
            System.out.println("im rechten Bereich.");
        }
        if(output.contains("zentral")) {
            if(isRandom) {
                System.out.print(", ");
            }
            isCentral = true;
            System.out.println("im zentralen Bereich.");
        }
        System.out.println("TEST: Zufall ist:" + isRandom + "links: " + isLeft);
//        if (isRandom & isLeft) {
//
//        }
        // Generiere zufaelligen Schuss, je nach Bereich
        if (isRandom) {
            if(isLeft) {
                System.out.println("zufällig, links");
                shot = generateRandomShot("links");
            }
            if (isRight) {
                shot = generateRandomShot("rechts");
            }
            if(isCentral) {
                shot = generateRandomShot("zentral");
            }
        }
        // Generiere naechst-moeglichen Schuss im jeweiligen Bereich
        // naechst-moeglich: erstes freies Feld, von links nach rechts, und von oben nach unten
        if (isRandom == false) {
            if(isLeft) {

            }
            if (isRight) {

            }
            if(isCentral) {
                System.out.println("Kein zufälliger Schuss, zentraler Bereich");
            }
        }


        System.out.println("Generierter Schuss auf Koordinate: " + shot);
    }

    @Override
    public void enterDirection(PlayerAIShotsParser.DirectionContext ctx) {
       // System.out.println("enterDirection: " + ctx.getText());
    }

    /**
     * Generiere Zufallschuss der KI, abhängig von dem Bereich wo
     * geschossen werden soll
     * @param area Bereich des zu generierenden Schusses
     * @return
     */
    private static String generateRandomShot(String area) {
        String shot;
        int randomRowCoord;
        int randomColumnCoord;
        String[] coord = new String[]{"A","B","C","D","E","F","G","H"};
        do {
            switch(area) {
                case "links":
                    randomRowCoord = randomInteger(0, 2);
                    randomColumnCoord = randomInteger(0, 2) + 1;
                    break;
                case "zentral":
                    randomRowCoord = randomInteger(0, 7);
                    randomColumnCoord = randomInteger(3, 4) + 1;
                    break;
                case "rechts":
                    randomRowCoord = randomInteger(5, 7);
                    randomColumnCoord = randomInteger(5, 7) + 1;
                    break;
                case "ueberall":
                    randomRowCoord = randomInteger(0, 7);
                    randomColumnCoord = randomInteger(0, 7) + 1;
                    break;
                // Abfangen: kein erlaubter Wert fuer "area", setze -1
                default:
                    randomRowCoord = -1;
                    randomColumnCoord = -1;
            }
            if (randomRowCoord == -1 | randomColumnCoord == -1) {
                System.out.println("Unbekannter angegebener Bereich: " + area);
                System.out.println("Kann keine Koordiante erzeugen!");
                throw new IllegalArgumentException();
            }
            shot = coord[randomRowCoord] + Integer.toString(randomColumnCoord);
            System.out.println("generateRandomShot(), shot: " + shot);

            // Ueberpruefe ob Schuss bereits im Feld vorhanden bzw.
            // bereits auf diese Koordiante geschossen wurde
            if (mapShotsPlayerAI.get(shot) == false){
                // Nein, setze generierten Zufalls-Schuss
                GeneratePlayerAIShots.mapShotsPlayerAI.put(shot, true);
            }
        // Generiere Zufalls-Koordinate solange, bis Koordinate nicht gefunden/Schuss nicht gesetzt wurde
        } while (mapShotsPlayerAI.get(shot) == false);

        return shot;
    }

    private static String generateShotInNextFreeSpace (String area) {
        String shot = "";
        return shot;
        
    }


    /**
     * Erstellen eines Zufallsschusses der KI
     * @return
     */
    public static String generateRandomMove() {
//		System.out.println("Generating random move");
        int randomColumnCoord;
        int randomRowCoord;
        String shot;
        do {
            // Generate random move
//			System.out.println("Entering do");
            String[] coord = new String[]{"A","B","C","D","E","F","G","H"};
            randomRowCoord = randomInteger(0,7);
            randomColumnCoord = randomInteger(0,7);
            shot = coord[randomRowCoord] + Integer.toString(randomColumnCoord);
//			System.out.println("Leaving do, has  randomRow: "+ randomRowCoord+", randowmColumn: "+ randomColumnCoord);
//            if (GeneratePlayerAIShots.checkIfShotIsPresentAtField(randomRowCoord, randomColumnCoord) == false){
                if (GeneratePlayerAIShots.isShotPresentAtField(shot) == false){
                //System.out.println("Wurde schon drauf geschossen!");
                // this field has not been shot at so mark it as been shot for this move
//                GeneratePlayerAIShots.shotsPlayerAI[randomRowCoord][randomColumnCoord] = true;
                    GeneratePlayerAIShots.mapShotsPlayerAI.put(shot, true);
            } // else {
//				System.out.println("Wurde noch nicht drauf geschossen");
//			}
//        } while (GeneratePlayerAIShots.isShotPresentAtField(shot));
        } while (GeneratePlayerAIShots.isShotPresentAtField(shot));
        //GeneratePlayerAIShots.shotsPlayerAI[randomColumnCoord][randomRowCoord] = true;
//        move = GeneratePlayerAIShots.changeIntToCoordinates(randomRowCoord, randomColumnCoord);
        //System.out.println("generateRandomMove(), move: " + move);
        return shot;
    }


    /**
     *
     * Initialsiere Map fuer die Schuesse des Computer-Gegners
     */
    private static void initializeShotsPlayerAI() {
        shotsPlayerAI = new boolean[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                shotsPlayerAI[i][j] = false;
                //System.out.println("shotsPlayerAI at " + i+j+": " + shotsPlayerAI[i][j]);
            }
        }

        mapShotsPlayerAI = new LinkedHashMap<>();
        List<String> al = new ArrayList<String>();
        al.add("A");
        al.add("B");
        al.add("C");
        al.add("D");
        al.add("E");
        al.add("F");
        al.add("G");
        al.add("H");
//        for (int i = 0; i < 8; i++) {
//            for (String coord: al) {
//                mapShotsPlayerAI.put(coord+Integer.toString(i), false);
//            }
//        }
        // Generierung: zeilenweise, also A0,A1,...A7,B0,B1...B7, usw.
        for (String coord: al) {
            for (int i = 1; i < 9; i++) {
                mapShotsPlayerAI.put(coord+Integer.toString(i), false);
            }
        }


        System.out.println("Generierte mapShotsPlayer ist: " + mapShotsPlayerAI);
        System.out.println("Länge des Feldes: " + mapShotsPlayerAI.values().size());
    }

    /**
     * Generiere int Zufallszahl
     * @param min untere Grenze fuer Zufallszahlen-Generierung
     * @param max obere Grenze fuer Zufallszahlen-Generierung
     * @return randomNum generierte Zufallszahl
     */

    private static int randomInteger(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    /**
     * Ueberpruefe ob Schuss bereits im Feld vorhanden bzw.
     * bereits auf diese Koordiante geschossen wurde
     *
     * @param shot Koordinate/Schuss der zu überprüfen ist
     * @return false : falls nein, true : ja (= vorhanden)
     */
    private static boolean isShotPresentAtField (String shot) {
        //if(GeneratePlayerAIShots.shotsPlayerAI[row][column] == false) {
        //if(mapShotsPlayerAI.get(shot) == true) {

          //return false;
        //} else {
           // return true;
        //}
        //return true;
        return mapShotsPlayerAI.get(shot);
    }

    /**
     * Setze Schuss in erstes freies Feld im linken Bereich
     * @TODO Bereich begrenzen, erstes freies Feld finden, Feld als markiert setzen=TRUE
     * @TODO: switch/case: parameter des teilbereichs uebergeben (ggfs. weniger Code)
     * @param area
     * @return
     */
    private static boolean setShotInLeftField (String area) {
//        case area:

        return false;
    }

    /**
     *
     * @param row
     * @param column
     * @return
     */
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
                break;
            case 7: coord.append("H");
                break;
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
    /*
    setShotIntoFieldAI
    @param String shot: shot to be marked in coordinates of field
     */

    /**
     *
     * @param shot
     */
    private static void setShotIntoFieldAI(String shot) {
        mapShotsPlayerAI.put(shot, true);
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
        //GeneratePlayerAIShots.shotsPlayerAI[row][column] = true;
    }
}
