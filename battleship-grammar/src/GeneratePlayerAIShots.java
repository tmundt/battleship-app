import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


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
//        String filePath = "E:\\repo\\github\\battleship-app\\battleship-grammar\\src\\ShotsPlayerAI.txt";
        // Pfad zur Metadaten-Datei in MAC - TOM
        String filePath = "/Users/thomasmundt/repo/github/battleship-app/battleship-grammar/src/ShotsPlayerAI.txt";

        file = new File(filePath);
        if(file.exists()) {
            System.out.println("file is: " + file);
        } else {
            System.out.println("Metadaten nicht gefunden, bitte erstellen!");
            throw new FileNotFoundException("Datei nicht gefunden: " + file);
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
//        String pathToFile = "D:/coding/repo/github/battleship-app/battleship-android/src";

        // Einstellung für Tom/Windows
//        String pathToFile = "E:/repo/github/battleship-app/battleship-android/src";

        // Einstellung für Tom/Mac
        String pathToFile = "/Users/thomasmundt/repo/github/battleship-app/battleship-android/src";
        pathToFile += "/org/nse/battleship";

        pathToFile += "/GeneratedPlayerAI.java";
        System.out.println("Generating Java-Class: \n" + pathToFile);
        Path fileOut = Paths.get(pathToFile);

        // Generating/writing java file
        Files.write(fileOut, data);
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

        // Auswertung der Zeile
        if (output.contains("zufaellig")) {
            System.out.print("zufällig.");
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

        // Generiere zufaelligen Schuss, je nach Bereich
        if (isRandom) {
            if(isLeft) {
                System.out.println("zufällig, links");
                shot = generateRandomShot("links");
            } else if (isRight) {
                shot = generateRandomShot("rechts");
            } else if(isCentral) {
                shot = generateRandomShot("zentral");
            } else {
                // Keine der erlaubten Bereiche angegeben = keine Richtung gemaess Grammatik
                shot = generateRandomShot("ueberall");
            }
        }
        // Generiere naechst-moeglichen Schuss im jeweiligen Bereich
        // naechst-moeglich: erstes freies Feld, von links nach rechts, und von oben nach unten
        if (isRandom == false) {
            if(isLeft) {
                shot = generateShotInNextFreeSpace("links");
            } else if (isRight) {
                shot = generateShotInNextFreeSpace("rechts");
            } else if(isCentral) {
                System.out.println("Kein zufälliger Schuss, zentraler Bereich");
                shot = generateShotInNextFreeSpace("zentral");
            } else {
                // Keine der erlaubten Bereiche angegeben = keine Richtung gemaess Grammatik
                // => schiesse auf die nächste noch nicht beschossene Koordinate
                shot = generateShotInNextFreeSpace("ueberall");
            }
        }


        System.out.println("Generierter Schuss auf Koordinate: " + shot);
        code.append("        Shots.add(\"" + shot + "\");\n");
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
        String[] rowCoord = new String[]{"A","B","C","D","E","F","G","H"};
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
            shot = rowCoord[randomRowCoord] + Integer.toString(randomColumnCoord);

            // Ueberpruefe ob Schuss bereits im Feld vorhanden bzw.
            // bereits auf diese Koordiante geschossen wurde
            if (mapShotsPlayerAI.get(shot) == false){
                // Nein, setze generierten Zufalls-Schuss
                System.out.println("generateRandomShot(), shot: " + shot);
                GeneratePlayerAIShots.mapShotsPlayerAI.put(shot, true);
            }
        // Generiere Zufalls-Koordinate solange, bis Koordinate nicht gefunden/Schuss nicht gesetzt wurde
        } while (mapShotsPlayerAI.get(shot) == false);

        return shot;
    }

    /**
     * Generiere Schuss an der nächst möglichen freien Stelle eines Bereichs
     * @param area Bereich der Schussgenerierung
     * @return generierter Schuss
     */
    private static String generateShotInNextFreeSpace (String area) {
        String shot = "";

        // Moegliche Zeilen-Koordinaten
        String[] rowCoords = new String[]{"A","B","C","D","E","F","G","H"};
        String tempCoord; // temporaere Koordinate
        int startColumn = 0;
        int endColumn = 0;

        switch (area) {
            case "links":
                startColumn = 1;
                endColumn = 3;
                break;
            case "zentral":
                startColumn = 4;
                endColumn = 5;
                break;
            case "rechts":
                startColumn = 6;
                endColumn = 8;
                break;
            case "ueberall":
                startColumn = 1;
                endColumn = 8;
                break;
        }
        for (String coord: rowCoords) {
            for( int i=startColumn; i <= endColumn; i++ ) {
                // Erstelle temporaere Koordinate
                tempCoord = coord+Integer.toString(i);
                // Pruefe ob diese Koordinate bereits zum Beschiessen vorgesehen war
                System.out.println("generateShotInNextFreeSpace(), überprüfe Koordinate " + tempCoord);

                if(mapShotsPlayerAI.get(tempCoord) == false) {
                    //Koordinate noch nicht belegt: belegen
                    System.out.println("Koordinate zum Belegen gefunden:  " + tempCoord);
                    shot = tempCoord;
                    mapShotsPlayerAI.put(tempCoord, true);
                    // Sprung aus Methode zur Verhinderung weiterer Belegungen
                    return shot;
                }
            }
        }
        // Kein Schuss im Bereich gefunden: finde den nächst möglichen in der gesamten Map
        // Es muss mindestens noch ein freies Feld in der Map geben wg. der Grammatik!
        if (shot.isEmpty()) {
            System.out.println("Keinen möglichen Schuss für Bereich " + area + "gefunden.");
            System.out.println("Generiere nächstmöglichen Schuss im gesamten Feld!");

            // Rekursiver Aufruf der Methode: Suche ueberall!
            generateShotInNextFreeSpace("ueberall");
        }
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

        mapShotsPlayerAI = new TreeMap<>();
        List<String> al = new ArrayList<String>();
        al.add("A");
        al.add("B");
        al.add("C");
        al.add("D");
        al.add("E");
        al.add("F");
        al.add("G");
        al.add("H");

        // Generierung: zeilenweise, also A1,...A7,B0,B1...B7, usw.
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
}