package org.nse.battleship;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.*;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.*;

/**
 * Created by thomasmundt on 28.06.15.
 * Field where player should set up his ships
 */
public class SetupPlayerFieldActivity extends Activity implements View.OnClickListener {


    public ImageView imagePlayfield;
    public ImageButton buttonSpielen;
    public ImageView imageShipCarrier;
    public ImageView imageShipCruiser;
    public ImageView imageShipGunboat;
    public ImageView imageShipSubmarine;
    public ImageView imageShipSpeedboat;
    public ImageView imageShotWasHit;
    public ImageView imageShotWasFail;

    // Buttons des Gegnerfeldes
    public ImageView imageButtonA1;
    public ImageView imageButtonA2;
    public ImageView imageButtonA3;
    public ImageView imageButtonA4;
    public ImageView imageButtonA5;
    public ImageView imageButtonA6;
    public ImageView imageButtonA7;
    public ImageView imageButtonA8;
    public ImageView imageButtonB1;
    public ImageView imageButtonB2;
    public ImageView imageButtonB3;
    public ImageView imageButtonB4;
    public ImageView imageButtonB5;
    public ImageView imageButtonB6;
    public ImageView imageButtonB7;
    public ImageView imageButtonB8;
    public ImageView imageButtonC1;
    public ImageView imageButtonC2;
    public ImageView imageButtonC3;
    public ImageView imageButtonC4;
    public ImageView imageButtonC5;
    public ImageView imageButtonC6;
    public ImageView imageButtonC7;
    public ImageView imageButtonC8;
    public ImageView imageButtonD1;
    public ImageView imageButtonD2;
    public ImageView imageButtonD3;
    public ImageView imageButtonD4;
    public ImageView imageButtonD5;
    public ImageView imageButtonD6;
    public ImageView imageButtonD7;
    public ImageView imageButtonD8;
    public ImageView imageButtonE1;
    public ImageView imageButtonE2;
    public ImageView imageButtonE3;
    public ImageView imageButtonE4;
    public ImageView imageButtonE5;
    public ImageView imageButtonE6;
    public ImageView imageButtonE7;
    public ImageView imageButtonE8;
    public ImageView imageButtonF1;
    public ImageView imageButtonF2;
    public ImageView imageButtonF3;
    public ImageView imageButtonF4;
    public ImageView imageButtonF5;
    public ImageView imageButtonF6;
    public ImageView imageButtonF7;
    public ImageView imageButtonF8;
    public ImageView imageButtonG1;
    public ImageView imageButtonG2;
    public ImageView imageButtonG3;
    public ImageView imageButtonG4;
    public ImageView imageButtonG5;
    public ImageView imageButtonG6;
    public ImageView imageButtonG7;
    public ImageView imageButtonG8;
    public ImageView imageButtonH1;
    public ImageView imageButtonH2;
    public ImageView imageButtonH3;
    public ImageView imageButtonH4;
    public ImageView imageButtonH5;
    public ImageView imageButtonH6;
    public ImageView imageButtonH7;
    public ImageView imageButtonH8;

    public ImageView imageComputerPlayerTitle;
    public ImageView imageOpponentPlayfield;

    public Ship carrier;
    public Ship cruiser;
    public Ship gunboat;
    public Ship submarine;
    public Ship speedboat;

    private Map mapPlayfieldPlayer;
    private Map mapPlayfieldOpponent;
    private Map mapPositionedShipsPlayer;
    private boolean[][] shotsPlayerAI;
    private int counterRounds;
    private int hitPointsPlayer;
    private int hitPointsOpponent;
    public ArrayList<ImageView> buttonList = new ArrayList<ImageView>();

    public SetupPlayerFieldActivity() {
        Log.i("CONSTRUCTOR","initialsing");
        carrier = new Ship(ShipType.CARRIER);
        cruiser = new Ship(ShipType.CRUISER);
        gunboat = new Ship(ShipType.GUNBOAT);
        submarine = new Ship(ShipType.SUBMARINE);
        speedboat = new Ship(ShipType.SPEEDBOAT);

        mapPlayfieldPlayer = new TreeMap<>();
        mapPlayfieldOpponent = new TreeMap<String, Boolean>();
        initializePlayfields();
        mapPositionedShipsPlayer = new TreeMap<String, Ship>();
        counterRounds = 0;
        hitPointsPlayer = 17;
        hitPointsOpponent = 17;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setupplayerfield);

        // Button für das Gegnerfeld generieren/laden


        buttonList.add(imageButtonA1 = (ImageView) findViewById(R.id.button_a1));
        buttonList.add(imageButtonA2 = (ImageView) findViewById(R.id.button_a2));
        buttonList.add(imageButtonA3 = (ImageView) findViewById(R.id.button_a3));
        buttonList.add(imageButtonA4 = (ImageView) findViewById(R.id.button_a4));
        buttonList.add(imageButtonA5 = (ImageView) findViewById(R.id.button_a5));
        buttonList.add(imageButtonA6 = (ImageView) findViewById(R.id.button_a6));
        buttonList.add(imageButtonA7 = (ImageView) findViewById(R.id.button_a7));
        buttonList.add(imageButtonA8 = (ImageView) findViewById(R.id.button_a8));
        buttonList.add(imageButtonB1 = (ImageView) findViewById(R.id.button_b1));
        buttonList.add(imageButtonB2 = (ImageView) findViewById(R.id.button_b2));
        buttonList.add(imageButtonB3 = (ImageView) findViewById(R.id.button_b3));
        buttonList.add(imageButtonB4 = (ImageView) findViewById(R.id.button_b4));
        buttonList.add(imageButtonB5 = (ImageView) findViewById(R.id.button_b5));
        buttonList.add(imageButtonB6 = (ImageView) findViewById(R.id.button_b6));
        buttonList.add(imageButtonB7 = (ImageView) findViewById(R.id.button_b7));
        buttonList.add(imageButtonB8 = (ImageView) findViewById(R.id.button_b8));
        buttonList.add(imageButtonC1 = (ImageView) findViewById(R.id.button_c1));
        buttonList.add(imageButtonC2 = (ImageView) findViewById(R.id.button_c2));
        buttonList.add(imageButtonC3 = (ImageView) findViewById(R.id.button_c3));
        buttonList.add(imageButtonC4 = (ImageView) findViewById(R.id.button_c4));
        buttonList.add(imageButtonC5 = (ImageView) findViewById(R.id.button_c5));
        buttonList.add(imageButtonC6 = (ImageView) findViewById(R.id.button_c6));
        buttonList.add(imageButtonC7 = (ImageView) findViewById(R.id.button_c7));
        buttonList.add(imageButtonC8 = (ImageView) findViewById(R.id.button_c8));
        buttonList.add(imageButtonD1 = (ImageView) findViewById(R.id.button_d1));
        buttonList.add(imageButtonD2 = (ImageView) findViewById(R.id.button_d2));
        buttonList.add(imageButtonD3 = (ImageView) findViewById(R.id.button_d3));
        buttonList.add(imageButtonD4 = (ImageView) findViewById(R.id.button_d4));
        buttonList.add(imageButtonD5 = (ImageView) findViewById(R.id.button_d5));
        buttonList.add(imageButtonD6 = (ImageView) findViewById(R.id.button_d6));
        buttonList.add(imageButtonD7 = (ImageView) findViewById(R.id.button_d7));
        buttonList.add(imageButtonD8 = (ImageView) findViewById(R.id.button_d8));
        buttonList.add(imageButtonE1 = (ImageView) findViewById(R.id.button_e1));
        buttonList.add(imageButtonE2 = (ImageView) findViewById(R.id.button_e2));
        buttonList.add(imageButtonE3 = (ImageView) findViewById(R.id.button_e3));
        buttonList.add(imageButtonE4 = (ImageView) findViewById(R.id.button_e4));
        buttonList.add(imageButtonE5 = (ImageView) findViewById(R.id.button_e5));
        buttonList.add(imageButtonE6 = (ImageView) findViewById(R.id.button_e6));
        buttonList.add(imageButtonE7 = (ImageView) findViewById(R.id.button_e7));
        buttonList.add(imageButtonE8 = (ImageView) findViewById(R.id.button_e8));
        buttonList.add(imageButtonF1 = (ImageView) findViewById(R.id.button_f1));
        buttonList.add(imageButtonF2 = (ImageView) findViewById(R.id.button_f2));
        buttonList.add(imageButtonF3 = (ImageView) findViewById(R.id.button_f3));
        buttonList.add(imageButtonF4 = (ImageView) findViewById(R.id.button_f4));
        buttonList.add(imageButtonF5 = (ImageView) findViewById(R.id.button_f5));
        buttonList.add(imageButtonF6 = (ImageView) findViewById(R.id.button_f6));
        buttonList.add(imageButtonF7 = (ImageView) findViewById(R.id.button_f7));
        buttonList.add(imageButtonF8 = (ImageView) findViewById(R.id.button_f8));
        buttonList.add(imageButtonG1 = (ImageView) findViewById(R.id.button_g1));
        buttonList.add(imageButtonG2 = (ImageView) findViewById(R.id.button_g2));
        buttonList.add(imageButtonG3 = (ImageView) findViewById(R.id.button_g3));
        buttonList.add(imageButtonG4 = (ImageView) findViewById(R.id.button_g4));
        buttonList.add(imageButtonG5 = (ImageView) findViewById(R.id.button_g5));
        buttonList.add(imageButtonG6 = (ImageView) findViewById(R.id.button_g6));
        buttonList.add(imageButtonG7 = (ImageView) findViewById(R.id.button_g7));
        buttonList.add(imageButtonG8 = (ImageView) findViewById(R.id.button_g8));
        buttonList.add(imageButtonH1 = (ImageView) findViewById(R.id.button_h1));
        buttonList.add(imageButtonH2 = (ImageView) findViewById(R.id.button_h2));
        buttonList.add(imageButtonH3 = (ImageView) findViewById(R.id.button_h3));
        buttonList.add(imageButtonH4 = (ImageView) findViewById(R.id.button_h4));
        buttonList.add(imageButtonH5 = (ImageView) findViewById(R.id.button_h5));
        buttonList.add(imageButtonH6 = (ImageView) findViewById(R.id.button_h6));
        buttonList.add(imageButtonH7 = (ImageView) findViewById(R.id.button_h7));
        buttonList.add(imageButtonH8 = (ImageView) findViewById(R.id.button_h8));

        imageComputerPlayerTitle = (ImageView) findViewById(R.id.imageComputerPlayer);
        imageOpponentPlayfield = (ImageView) findViewById(R.id.imagePlayfield2);

        imagePlayfield = (ImageView) findViewById(R.id.imagePlayfield);
        buttonSpielen = (ImageButton) findViewById(R.id.buttonSpielen);
        imageShipCarrier = (ImageView) findViewById(R.id.imageShipCarrier);
        imageShipCruiser = (ImageView) findViewById(R.id.imageShipCruiser);
        imageShipGunboat = (ImageView) findViewById(R.id.imageShipGunboat);
        imageShipSubmarine = (ImageView) findViewById(R.id.imageShipSubmarine);
        imageShipSpeedboat = (ImageView) findViewById(R.id.imageShipSpeedboat);

        // Set tags to identify imageviews = different ships
        imageShipCarrier.setTag("shipCarrier");
        imageShipCruiser.setTag("shipCruiser");
        imageShipGunboat.setTag("shipGunboat");
        imageShipSubmarine.setTag("shipSubmarine");
        imageShipSpeedboat.setTag("shipSpeedboat");
        imagePlayfield.setTag("imagePlayfield");

        imageButtonA1.setTag("A1");
        imageButtonA2.setTag("A2");
        imageButtonA3.setTag("A3");
        imageButtonA4.setTag("A4");
        imageButtonA5.setTag("A5");
        imageButtonA6.setTag("A6");
        imageButtonA7.setTag("A7");
        imageButtonA8.setTag("A8");
        imageButtonB1.setTag("B1");
        imageButtonB2.setTag("B2");
        imageButtonB3.setTag("B3");
        imageButtonB4.setTag("B4");
        imageButtonB5.setTag("B5");
        imageButtonB6.setTag("B6");
        imageButtonB7.setTag("B7");
        imageButtonB8.setTag("B8");
        imageButtonC1.setTag("C1");
        imageButtonC2.setTag("C2");
        imageButtonC3.setTag("C3");
        imageButtonC4.setTag("C4");
        imageButtonC5.setTag("C5");
        imageButtonC6.setTag("C6");
        imageButtonC7.setTag("C7");
        imageButtonC8.setTag("C8");
        imageButtonD1.setTag("D1");
        imageButtonD2.setTag("D2");
        imageButtonD3.setTag("D3");
        imageButtonD4.setTag("D4");
        imageButtonD5.setTag("D5");
        imageButtonD6.setTag("D6");
        imageButtonD7.setTag("D7");
        imageButtonD8.setTag("D8");
        imageButtonE1.setTag("E1");
        imageButtonE2.setTag("E2");
        imageButtonE3.setTag("E3");
        imageButtonE4.setTag("E4");
        imageButtonE5.setTag("E5");
        imageButtonE6.setTag("E6");
        imageButtonE7.setTag("E7");
        imageButtonE8.setTag("E8");
        imageButtonF1.setTag("F1");
        imageButtonF2.setTag("F2");
        imageButtonF3.setTag("F3");
        imageButtonF4.setTag("F4");
        imageButtonF5.setTag("F5");
        imageButtonF6.setTag("F6");
        imageButtonF7.setTag("F7");
        imageButtonF8.setTag("F8");
        imageButtonG1.setTag("G1");
        imageButtonG2.setTag("G2");
        imageButtonG3.setTag("G3");
        imageButtonG4.setTag("G4");
        imageButtonG5.setTag("G5");
        imageButtonG6.setTag("G6");
        imageButtonG7.setTag("G7");
        imageButtonG8.setTag("G8");
        imageButtonH1.setTag("H1");
        imageButtonH2.setTag("H2");
        imageButtonH3.setTag("H3");
        imageButtonH4.setTag("H4");
        imageButtonH5.setTag("H5");
        imageButtonH6.setTag("H6");
        imageButtonH7.setTag("H7");
        imageButtonH8.setTag("H8");

        imageShipCarrier.setOnTouchListener(new MyTouchListener());
        imageShipCruiser.setOnTouchListener(new MyTouchListener());
        imageShipGunboat.setOnTouchListener(new MyTouchListener());
        imageShipSubmarine.setOnTouchListener(new MyTouchListener());
        imageShipSpeedboat.setOnTouchListener(new MyTouchListener());

        imagePlayfield.setOnDragListener(new DragShipListener());
        RelativeLayout layout = (RelativeLayout)imagePlayfield.getParent();
        layout.setOnDragListener(new DragShipListener());
        buttonSpielen.setOnClickListener(this);

        for(ImageView button: buttonList) {
            button.setOnClickListener(new CellButtonListener());
        }
    }

    /**
     * Verabeitet Klick auf den Spiele-Button
     * @param v view des onClickListeners
     */
    public void onClick(View v) {
        Log.i("ON_CLICK","Listener für Spielen geklickt!");
        if(mapPositionedShipsPlayer.size() < 5) {
            Log.i("ON_CLICK","Nicht alle Schiffe gesetzt, ist: " + mapPositionedShipsPlayer.size());
        }
        if(mapPositionedShipsPlayer.size() == 5) {
            setShipsPositionsIntoPlayfieldPlayer();
            generateRandomSetupShipsOpponent();
            showOpponent();

        }
    }

    /**
     * Pruefe ob gegnerisches Schiff getroffen
     * @param coordinate
     * @return
     */
    private boolean checkIfShotOnOpponentHasHit(String coordinate) {
        boolean isHit = (boolean) mapPlayfieldOpponent.get(coordinate);
        return isHit;
    }

    /**
     * Pruefe ob eigenes Schiff getroffen
     * @param coordinate
     * @return
     */
    private boolean checkIfShotOnPlayerHasHit(String coordinate) {
        boolean isHit = (boolean) mapPlayfieldPlayer.get(coordinate);
        return isHit;
    }

    /**
     * Berechne Schiffskoordinaten in Spielfeldkoordinaten und speichere diese
     */
    private void setShipsPositionsIntoPlayfieldPlayer() {
        float x,y;
        int size;
        String cell;
        List<Ship> shipList = new ArrayList<Ship>(mapPositionedShipsPlayer.values());
        for (Ship ship: shipList) {
            cell = "";
            x = ship.getStartX();
            y = ship.getStartY();
            size = ship.getSize();

            if(y >= 260 & y < 270) {
                Log.i("CELL","A");
                cell += "A";
            }
            if(y >= 385 & y < 410) {
                Log.i("CELL","B");
                cell += "B";
            }
            if(y >= 505 & y < 535) {
                Log.i("CELL","C");
                cell += "C";
            }
            if(y >= 620 & y < 655) {
                Log.i("CELL","D");
                cell += "D";
            }
            if(y >= 750 & y < 790) {
                Log.i("CELL","E");
                cell += "E";
            }
            if(y >= 900 & y < 925) {
                Log.i("CELL","F");
                cell += "F";
            }
            if(y >= 1020 & y < 1045) {
                Log.i("CELL","G");
                cell += "G";
            }

            if(x >= 100 & x < 120) {
                Log.i("CELL","1");
                cell += "1";
            }
            if(x >= 230 & x < 250) {
                Log.i("CELL","2");
                cell += "2";
            }
            if(x >= 360 & x < 390) {
                Log.i("CELL","3");
                cell += "3";
            }
            if(x >= 500 & x < 525) {
                Log.i("CELL","4");
                cell += "4";
            }
            if(x >= 630 & x < 660) {
                Log.i("CELL","5");
                cell += "5";
            }
            if(x >= 750 & x < 790) {
                Log.i("CELL","6");
                cell += "6";
            }
            if(x >= 900 & x < 940) {
                Log.i("CELL","7");
                cell += "7";
            }
            if(x >= 1030 & x < 1055) {
                Log.i("CELL","8");
                cell += "8";
            }

            Log.i("CELL","generated coordinate: " + cell);
            // Speichere Koordinate
            mapPlayfieldPlayer.put(cell, true);
            String row = cell.substring(0,1);
            String column = cell.substring(1);
            String nextCell = "";
            for(int i=1; i < size; i++) {
                String newRow;
                switch (row) {
                    case "A":
                        newRow = "B";
                        row = newRow;
                        break;
                    case "B":
                        newRow = "C";
                        row = newRow;
                        break;
                    case "C":
                        newRow = "D";
                        row = newRow;
                        break;
                    case "D":
                        newRow = "E";
                        row = newRow;
                        break;
                    case "E":
                        newRow = "F";
                        row = newRow;
                        break;
                    case "F":
                        newRow = "G";
                        row = newRow;
                        break;
                    case "G":
                        newRow = "H";
                        row = newRow;
                        break;
                    default:
                        newRow = "FEHLER";
                }
                nextCell = newRow + column;
                Log.i("CELL","next generated coordinate: " + nextCell);
                // Speichere Koordinate
                mapPlayfieldPlayer.put(cell, true);
            }
        }

    }

    /**
     * Mache den Gegner sichtbar
     */
    private void showOpponent() {
        // Verstecke den Spielen Button
        View b = findViewById(R.id.buttonSpielen);
        b.setVisibility(View.INVISIBLE);

        // Grafik "Schiffe setzen" mit "Spieler 1" tauschen
//        View b2 = findViewById(R.id.imageSetzeSchiffe);
//        b2.setVisibility(View.INVISIBLE);
        ImageView img = (ImageView) findViewById(R.id.imageSetzeSchiffe);
        img.setImageResource(R.drawable.image_player1);
        img.setX(img.getX() - 560);

        // Zeige den Titel des Computergegners über das entsprechende Spielfeld an
        imageComputerPlayerTitle.setVisibility(View.VISIBLE);
        // Spielfeld des Gegners anzeigen
        imageOpponentPlayfield.setVisibility(View.VISIBLE);
        for(ImageView button: buttonList) {
            button.setVisibility(View.VISIBLE);
        }


    }

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            final int action = motionEvent.getAction();

            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    ClipData.Item item = new ClipData.Item((CharSequence)view.getTag());
                    //  ClipData dragData = new ClipData((CharSequence) view.getTag(),item);
                    ClipData dragData = new ClipData(ClipData.newPlainText((CharSequence)view.getTag(), ""));
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                    view.startDrag(dragData, shadowBuilder, view, 0);
                    view.setVisibility(View.INVISIBLE);
                    return true;
                case MotionEvent.ACTION_UP:
                    Log.d("ACTION_UP", "released ship");
                    break;
                case MotionEvent.ACTION_CANCEL:
                    Log.e("ACTION_CANCEL", "ship x-coord:" + view.getX());
                    view.setVisibility(View.VISIBLE);
                    break;

            }
            return true;
        }
    }

    class DragShipListener implements View.OnDragListener {

        protected boolean isCollided;
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            String viewTag = (String) v.getTag();
            float x = event.getX();
            float y = event.getY();
            boolean collide;
            Ship ship = null;

            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    Log.i("ACTION_DRAG_STARTED","target view is: " +v.getTag());
                    Log.i("ACTION_DRAG_STARTED","x ist: " +x+", y: "+ y);
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.d("ACTION_DRAG_ENTERED","entered:" + v.getTag());
                    Log.i("ACTION_DRAG_STARTED", "x ist: " + x + ", y: " + y);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:

                    break;
                case DragEvent.ACTION_DROP:
                    //String viewTag = (String) v.getTag();
                    Log.i("ACTION_DROP","target view is: " +v.getTag());
                    Log.i("ACTION_DROP","view v is: " + v);
                    if (v != imagePlayfield) {
//                    if (viewTag.equals("imagePlayfield")== false) {
                        Log.e("ACTION_DROP","wrong drop! Dropped into: "+viewTag);
//                        Log.e("ACTION_DROP","Collide is: "+collide);
                        v.setVisibility(View.VISIBLE);
                        return false;
                    }
                    // Dropped, reassign View to ViewGroup
                    View dragView = (View) event.getLocalState();
                    String dragTag = dragView.getTag().toString();

                    // Korrektur Positionen/Berechnung Modifizierung der Y-Koordinate
                    int modifierY;
                    switch (dragTag) {
                        case "shipCarrier":
                            if (ship == null) {
                                ship = new Ship(ShipType.CARRIER);
                            }
                            modifierY = 4;
                            break;
                        case "shipCruiser":
                            if (ship == null) {
                                ship = new Ship(ShipType.CRUISER);
                            }
                            modifierY = 5;
                            break;
                        case "shipGunboat":
                            if (ship == null) {
                                ship = new Ship(ShipType.GUNBOAT);
                            }

                            modifierY = 7;
                            break;
                        case "shipSubmarine":
                            if (ship == null) {
                                ship = new Ship(ShipType.SUBMARINE);
                            }
                            modifierY = 18;
                            break;
                        case "shipSpeedboat":
                            if (ship == null) {
                                ship = new Ship(ShipType.SPEEDBOAT);
                            }
                            modifierY = 44;
                            break;
                        default:
                            modifierY = 4;
                    }
                    Log.d("ACTION_DROP:","VOR Korrektur, x ist: " + (int) x + ",y ist: " + (int) y);
                    ;
                    x = x - (dragView.getWidth()/2);
                    y = y - (dragView.getHeight()/modifierY);
                    Log.d("ACTION_DROP:","NACH Korrektur, x ist: " + (int) x + ",y ist: " + (int) y);
                    // Begrenze den linken und rechten Rand
                    if(x < 100) {
                        Log.e("ACTION_DROP","zu weit links: " + x);
                        x = 100;
                    }

                    if(x > 1042) {
                        x = 1042;
                    }

                    // Begrenze nach oben
                    if(y < 260) {
                        Log.e("ACTION_DROP","zu weit oben: " + y);
                        y = 260;
                    }

                    //Begrenze nach unten und korrigiere vertikal, schiffsabhaengig
                    switch (dragTag) {
                        case "shipCarrier":
                            if (y > 646) {
                                Log.e("ACTION_DROP","zu weit unten: " + y);
                                y = 645;
                            }
                            if (y < 645){
                                // y < 645: korrigiere ggfs. vertikal
                                for( int i = 260; i <= 770; i += 130) {
                                    if(y > i && y < i + 120) {
                                        Log.i("ACTION_DROP","Optimiere, y ist: " + y);
                                        y = i;
                                        Log.i("ACTION_DROP","Optimiert: y ist jetzt: " + i);
                                    }
                                }
                            }
                            break;
                        case "shipCruiser":
                            if (y > 771) {
                                Log.e("ACTION_DROP","zu weit unten: " + y);
                                y = 770;
                            }
                            if (y < 770){
                                // y < 770: korrigiere vertikal
                                for( int i = 260; i <= 650; i += 130) {
                                    if(y > i && y < i + 120) {
                                        Log.i("ACTION_DROP","Optimiere, y ist: " + y);
                                        y = i;
                                        Log.i("ACTION_DROP","Optimiert: y ist jetzt: " + i);
                                    }
                                }
                            }

                            break;
                        case "shipGunboat":
                        case "shipSubmarine":
                            if (y > 901) {
                                Log.e("ACTION_DROP","zu weit unten: " + y);
                                y = 900;
                            }
                            if (y < 900){
                                // y < 770: korrigiere vertikal
                                for( int i = 260; i <= 770; i += 130) {
                                    if(y > i && y < i + 120) {
                                        Log.i("ACTION_DROP","Optimiere, y ist: " + y);
                                        y = i;
                                        Log.i("ACTION_DROP","Optimiert: y ist jetzt: " + i);
                                    }
                                }
                            }
                            break;
                        case "shipSpeedboat":
                            if (y > 1033) {
                                Log.e("ACTION_DROP","zu weit unten: " + y);
                                y = 1032;
                            }
                            if (y < 1032){
                                // y < 770: korrigiere vertikal
                                for( int i = 260; i <= 900; i += 130) {
                                    if(y > i && y < i + 120) {
                                        Log.i("ACTION_DROP","Optimiere, y ist: " + y);
                                        y = i;
                                        Log.i("ACTION_DROP","Optimiert: y ist jetzt: " + i);
                                    }
                                }
                            }
                    }

                    // Korrigiere horizontal an der X-Achse
                    for( int i = 100; i < 1040; i += 135) {
                        if(x > i && x < i + 120) {
                            Log.i("ACTION_DROP","Optimiere, x ist: " + x);
                            x = i;
                            Log.i("ACTION_DROP","Optimiert: x ist jetzt: " );
                        }
                    }
                    Log.i("ACTION_DROP","Class Ship: " + ship.getName());
                    // rechne Koordinaten in Zellen um
                    // Pruefe ob bereits belegt in Map
                    // Falls belegt: kein Drop und kein Speichern
                    // Speichere Schiff in Map ab wenn Schiff in endgültiger Position => Spielen Button gedrückt



                    // Vermeide das Uebereinanderlegen von Schiffen
                    dragView.setX(x);
                    dragView.setY(y);
                    dragView.setVisibility(View.VISIBLE);
//                    ViewGroup owner = (ViewGroup) dragView.getParent();
//                    owner.removeView(dragView);
//                    RelativeLayout container = (RelativeLayout) v;
//                    container.addView(dragView);
                    //dragView.setVisibility(View.VISIBLE);
                    Log.d("ACTION_DROP","dropped:" + dragView.getTag());
                    Log.d("ACTION_DROP:", "x ist: " + (int) x + "y ist: " + (int) y);

                    //setShotOnShip(dragTag, dragView);
                    //setHitImageOnShip(carrier, dragView);
                    collide = checkShipCollision(dragView);
                    Log.i("COLLIDING_CHECK","collide is: " + collide);
                    if (collide) {
                        Log.i("COLLIDING_CHECK","Colliding, collide is: ");
//                        dragView.setVisibility(View.INVISIBLE);
                        Log.i("ACTION_DROP","moving ship aside from " + x);
                        if(x - 135 <  100) {
                            x = x + 135;
                        } else {
                            x = x - 135;
                        }
                        Log.i("ACTION_DROP","moving ship aside to " + x);
                        dragView.setX(x);
//                        return false;
                    } else {
                        Log.i("COLLIDING_CHECK","Not colliding, collide is: " + collide);
                    }
                    ship.setStartX(x);
                    ship.setStartY(y);
                    mapPositionedShipsPlayer.put(ship.getName(), ship);
                    //setHitOnShip(ship);


                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    Log.d("ACTION_DRAG_ENDED","dropped into:" + v.getTag());
                    Log.d("ACTION_DRAG_ENDED", "isCollided:" + this.isCollided);
                    if (!event.getResult()) {
                        // Nicht gedroppt, d.h. kein "result"
//                        v.setVisibility(View.VISIBLE);
                        Log.i("ACTION_DRAG_ENDED", "not in drop zone:" + v.getTag());

                        return false;
                    }
                default:
                    return false;
            }
            return true;
        }



        /**
         * Stacken verschiedener imageViews zu einer neuen ImageView
         * @param dragTag
         * @param view
         */
        private void setShotOnShip(String dragTag, View view) {
            Log.i("DRAG_LISTENER","setShotOnShip: " +dragTag);
            Resources r = getResources();
            Drawable[] layers = new Drawable[2];
            //layers[0] = r.getDrawable(R.drawable.t);
            layers[0] = r.getDrawable(R.drawable.image_ship_carrier);
            layers[1] = r.getDrawable(R.drawable.image_getroffen);
            LayerDrawable layerDrawable = new LayerDrawable(layers);
            imageShipCarrier.setImageDrawable(layerDrawable);
        }

        private void setHitImageOnShip (Ship ship, View view) {
            ImageView hitImage = new ImageView(getApplicationContext());
            RelativeLayout layout = (RelativeLayout)findViewById(R.id.layoutSetupActivity);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
//            params.addRule(RelativeLayout.CENTER_IN_PARENT, view.getId());
            view.setLayoutParams(params);
            hitImage.setImageResource(R.drawable.image_getroffen);
            layout.addView(hitImage);
        }

        private boolean checkShipCollision(View movingView) {
            boolean isColliding = false;
            String movingViewName = movingView.getTag().toString();

            Log.i("COLLISION_CHECK","checking dragImage: " + movingViewName);
            Rect movingViewRect = new Rect();
            movingView.getHitRect(movingViewRect);

            Rect checkRect1 = new Rect();
            Rect checkRect2 = new Rect();
            Rect checkRect3 = new Rect();
            Rect checkRect4 = new Rect();
            Rect checkRect5 = new Rect();

            imageShipCarrier.getHitRect(checkRect1);
            imageShipCruiser.getHitRect(checkRect2);
            imageShipGunboat.getHitRect(checkRect3);
            imageShipSubmarine.getHitRect(checkRect4);
            imageShipSpeedboat.getHitRect(checkRect5);

            switch (movingViewName) {
                case "shipCarrier":
                    if(Rect.intersects(movingViewRect, checkRect2)) {
                        Log.i("COLLISION","Ship " + movingViewName + " with " + checkRect2);
                        isColliding = true;
                    }
                    if(Rect.intersects(movingViewRect, checkRect3)) {
                        Log.i("COLLISION","Ship " + movingViewName + " with " + checkRect3);
                        isColliding = true;
                    }
                    if(Rect.intersects(movingViewRect, checkRect4)) {
                        Log.i("COLLISION","Ship " + movingViewName + " with " + checkRect4.flattenToString());
                        isColliding = true;
                    }
                    if(Rect.intersects(movingViewRect, checkRect5)) {
                        Log.i("COLLISION","Ship " + movingViewName + " with " + checkRect5.flattenToString());
                        isColliding = true;
                    }
                    break;

                case "shipCruiser":
                    if(Rect.intersects(movingViewRect, checkRect1)) {
                        Log.i("COLLISION","Ship " + movingViewName + " with " + checkRect2.flattenToString());
                        isColliding = true;
                    }
                    if(Rect.intersects(movingViewRect, checkRect3)) {
                        Log.i("COLLISION","Ship " + movingViewName + " with " + checkRect3.flattenToString());
                        isColliding = true;
                    }
                    if(Rect.intersects(movingViewRect, checkRect4)) {
                        Log.i("COLLISION","Ship " + movingViewName + " with " + checkRect4.flattenToString());
                        isColliding = true;
                    }
                    if(Rect.intersects(movingViewRect, checkRect5)) {
                        Log.i("COLLISION","Ship " + movingViewName + " with " + checkRect5.flattenToString());
                        isColliding = true;
                    }
                    break;
                case "shipGunboat":
                    if(Rect.intersects(movingViewRect, checkRect1)) {
                        Log.i("COLLISION","Ship " + movingViewName + " with " + checkRect2.flattenToString());
                        isColliding = true;
                    }
                    if(Rect.intersects(movingViewRect, checkRect2)) {
                        Log.i("COLLISION","Ship " + movingViewName + " with " + checkRect3.flattenToString());
                        isColliding = true;
                    }
                    if(Rect.intersects(movingViewRect, checkRect4)) {
                        Log.i("COLLISION","Ship " + movingViewName + " with " + checkRect4.flattenToString());
                        isColliding = true;
                    }
                    if(Rect.intersects(movingViewRect, checkRect5)) {
                        Log.i("COLLISION","Ship " + movingViewName + " with " + checkRect5.flattenToString());
                        isColliding = true;
                    }
                    break;
                case "shipSubmarine":
                    if(Rect.intersects(movingViewRect, checkRect1)) {
                        Log.i("COLLISION","Ship " + movingViewName + " with " + checkRect2.flattenToString());
                        isColliding = true;
                    }
                    if(Rect.intersects(movingViewRect, checkRect2)) {
                        Log.i("COLLISION","Ship " + movingViewName + " with " + checkRect3.flattenToString());
                        isColliding = true;
                    }
                    if(Rect.intersects(movingViewRect, checkRect3)) {
                        Log.i("COLLISION","Ship " + movingViewName + " with " + checkRect4.flattenToString());
                        isColliding = true;
                    }
                    if(Rect.intersects(movingViewRect, checkRect5)) {
                        Log.i("COLLISION","Ship " + movingViewName + " with " + checkRect5.flattenToString());
                        isColliding = true;
                    }
                    break;
                case "shipSpeedboat":
                    if(Rect.intersects(movingViewRect, checkRect1)) {
                        Log.i("COLLISION","Ship " + movingViewName + " with " + checkRect2.flattenToString());
                        isColliding = true;
                    }
                    if(Rect.intersects(movingViewRect, checkRect2)) {
                        Log.i("COLLISION","Ship " + movingViewName + " with " + checkRect3.flattenToString());
                        isColliding = true;
                    }
                    if(Rect.intersects(movingViewRect, checkRect3)) {
                        Log.i("COLLISION","Ship " + movingViewName + " with " + checkRect4.flattenToString());
                        isColliding = true;
                    }
                    if(Rect.intersects(movingViewRect, checkRect4)) {
                        Log.i("COLLISION","Ship " + movingViewName + " with " + checkRect5.flattenToString());
                        isColliding = true;
                    }
            }
            return isColliding;
        }
    }

    /**
     * ClickListener für die Buttons/Schüsse auf den Gegner
     */
    class CellButtonListener implements View.OnClickListener {
        boolean isHit;

        @Override
        public void onClick(View v) {
            Log.i("BUTTON_LISTENER","Button clicked: " + v.getTag());
            if (counterRounds < 65) {
                isHit = checkIfShotOnOpponentHasHit(v.getTag().toString());
                if(isHit) {
                    setHitOnShip(v.getX(), v.getY());
                    hitPointsOpponent -= 1;
                    if(hitPointsOpponent == 0) {
                        playerHasWon();
                    }

                } else {
                    // Zeige Daneben-Icon an
                    setMissOnField(v.getX(), v.getY());
                }
            }
            counterRounds += 1;

            // Opponent/ComputerAI makes his move

        }
    }



    /**
     *
     * Initialsiere Map fuer das Spielfeld des Spielers
     * zum Abgleich mit Schüssen der Computer-Gegners
     */
    private void initializePlayfields() {
        shotsPlayerAI = new boolean[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                shotsPlayerAI[i][j] = false;
                //System.out.println("shotsPlayerAI at " + i+j+": " + shotsPlayerAI[i][j]);
            }
        }

        mapPlayfieldPlayer = new TreeMap<>();
        //mapPlayfieldOpponent = new TreeMap<String, Boolean>();
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
                mapPlayfieldPlayer.put(coord+Integer.toString(i), false);
                mapPlayfieldOpponent.put(coord+Integer.toString(i), false);
            }
        }

        Log.i("GENERATOR_MAP", "mapPlayfieldPlayer: " + mapPlayfieldPlayer);
        Log.i("GENERATOR_MAP", "mapPlayfieldOpponent: " + mapPlayfieldOpponent);
        Log.i("GENERATOR_MAP", "Länge des Feldes: " + mapPlayfieldPlayer.values().size());
    }

    /**
     * Generiere zufaelliges Spielfeld/Schiffspositionen fuer den Gegner
     */
    private void generateRandomSetupShipsOpponent() {
        String position;
        String nextPosition;
        int randomRowCoord;
        //int randomColumnCoord;
        String[] rowCoord = new String[]{"A","B","C","D","E","F","G","H"};

        Ship carrierOpponent = new Ship(ShipType.CARRIER);
        Ship cruiserOpponent = new Ship(ShipType.CRUISER);
        Ship gunboatOpponent = new Ship(ShipType.GUNBOAT);
        Ship submarineOpponent = new Ship(ShipType.SUBMARINE);
        Ship speedboatOpponent = new Ship(ShipType.SPEEDBOAT);
        ArrayList<Ship> shipList = new ArrayList<Ship>();
        int[] columnCoord = new int[5];
        int counterColumns = 0;
        columnCoord[0] = 2;
        columnCoord[1] = 3;
        columnCoord[2] = 5;
        columnCoord[3] = 7;
        columnCoord[4] = 8;



        shipList.add(carrierOpponent);
        shipList.add(cruiserOpponent);
        shipList.add(gunboatOpponent);
        shipList.add(submarineOpponent);
        shipList.add(speedboatOpponent);

        for(Ship ship: shipList) {

            randomRowCoord = randomInteger(0, 3);
            position = rowCoord[randomRowCoord] + Integer.toString(columnCoord[counterColumns]);


            Object o = mapPlayfieldOpponent.get(position);
            Log.i("OBJECT_CHECK","Found object: " + o);
            boolean bool = (boolean) o;
            if (bool == false){
                    // Nein, setze generierten Zufalls-Schuss
                    Log.i("SET_SHIP","Opponent "+ ship.getShipType() +" on: " + position);
                    mapPlayfieldOpponent.put(position, true);
            }

             //Belege die gesamte Grösse des Schiffes, vertikal nach unten
            for(int i = 1; i < ship.getSize();i++) {
                nextPosition = rowCoord[randomRowCoord + i] + Integer.toString(columnCoord[counterColumns]);
                // Schiff hoch positioniert und nur eins pro Spalte = keine Überschneidungen moeglich
                Log.i("SET_SHIP","Next opponent "+ ship.getShipType() +" on: " + nextPosition);
                mapPlayfieldOpponent.put(nextPosition, true);
            }
            // Erhoehe Zaehler auf die naechste zu beruecksichtigende Spalte/column fuer das naechste Schiff
            counterColumns += 1;
        }
    }

    private void setHitOnShip(float posX, float posY) {
        ImageView hitImage = new ImageView(getApplicationContext());
        hitImage.setImageResource(R.drawable.image_getroffen);
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.layoutSetupActivity);
//            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
//            params.addRule(RelativeLayout.CENTER_IN_PARENT, view.getId());
//            view.setLayoutParams(params);
        layout.addView(hitImage);
        hitImage.setX(posX);
        hitImage.setY(posY);

    }

    private void setMissOnField(float posX, float posY) {
        ImageView missImage = new ImageView(getApplicationContext());
        missImage.setImageResource(R.drawable.image_daneben);
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.layoutSetupActivity);
        layout.addView(missImage);
        missImage.setX(posX);
        missImage.setY(posY);
    }

    /**
     * Sprung in die Spieler hat gewonnen - Activity
     */
    private void playerHasWon() {
        Intent intent = new Intent(this, GameWonActivity.class);
        startActivity(intent);
    }

    /**
     * Sprung in die Spieler hat verloren - Activity
     */
    private void playerHasLost() {
        Intent intent = new Intent(this, GameWonActivity.class);
        startActivity(intent);
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


}