package org.nse.battleship;

import android.app.Activity;
import android.content.ClipData;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by thomasmundt on 28.06.15.
 * Field where player should set up his ships
 */
public class SetupPlayerFieldActivity extends Activity {


    public ImageView imagePlayfield;
    public ImageButton buttonDrehen;
    public ImageButton buttonSpielen;
    public ImageView imageShipCarrier;
    public ImageView imageShipCruiser;
    public ImageView imageShipGunboat;
    public ImageView imageShipSubmarine;
    public ImageView imageShipSpeedboat;
    public ImageView imageShotWasHit;
    public ImageView imageShotWasFail;

    public Ship carrier;
    public Ship cruiser;
    public Ship gunboat;
    public Ship submarine;
    public Ship speedboat;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        carrier = new Ship(ShipType.CARRIER);
        cruiser = new Ship(ShipType.CRUISER);
        gunboat = new Ship(ShipType.GUNBOAT);
        submarine = new Ship(ShipType.SUBMARINE);
        speedboat = new Ship(ShipType.SPEEDBOAT);


        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setupplayerfield);
        imagePlayfield = (ImageView) findViewById(R.id.imagePlayfield);
        buttonDrehen = (ImageButton) findViewById(R.id.buttonDrehen);
        buttonSpielen = (ImageButton) findViewById(R.id.buttonSpielen);
        imageShipCarrier = (ImageView) findViewById(R.id.imageShipCarrier);
        imageShipCruiser = (ImageView) findViewById(R.id.imageShipCruiser);
        imageShipGunboat = (ImageView) findViewById(R.id.imageShipGunboat);
        imageShipSubmarine = (ImageView) findViewById(R.id.imageShipSubmarine);
        imageShipSpeedboat = (ImageView) findViewById(R.id.imageShipSpeedboat);
        //imageShotWasHit = (ImageView) findViewById(R.id.image_getroffen);

        // Set tags to identify imageviews = different ships
        imageShipCarrier.setTag("shipCarrier");
        imageShipCruiser.setTag("shipCruiser");
        imageShipGunboat.setTag("shipGunboat");
        imageShipSubmarine.setTag("shipSubmarine");
        imageShipSpeedboat.setTag("shipSpeedboat");
        imagePlayfield.setTag("imagePlayfield");

        imageShipCarrier.setOnTouchListener(new MyTouchListener());
        imageShipCruiser.setOnTouchListener(new MyTouchListener());
        imageShipGunboat.setOnTouchListener(new MyTouchListener());
        imageShipSubmarine.setOnTouchListener(new MyTouchListener());
        imageShipSpeedboat.setOnTouchListener(new MyTouchListener());

        imagePlayfield.setOnDragListener(new MyDragListener());
        RelativeLayout layout = (RelativeLayout)imagePlayfield.getParent();
        layout.setOnDragListener(new MyDragListener());

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

    class MyDragListener implements View.OnDragListener {


        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            String viewTag = (String) v.getTag();
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
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
                        v.setVisibility(View.VISIBLE);
                        return false;
                    }
                    // Dropped, reassign View to ViewGroup
                    View dragView = (View) event.getLocalState();
                    String dragTag = dragView.getTag().toString();
//                    float X = event.getX();
//                    float Y = event.getY();
                    // Korrektur Positionen/Berechnung Modifizierung der Y-Koordinate
                    int modifierY;
                    switch (dragTag) {
                        case "shipCarrier":
                            modifierY = 4;
                            break;
                        case "shipCruiser":
                            modifierY = 5;
                            break;
                        case "shipGunboat":
                            modifierY = 7;
                            break;
                        case "shipSubmarine":
                            modifierY = 18;
                            break;
                        case "shipSpeedboat":
                            Log.i("ACTION_DROP","detected Speedboat");
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
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    Log.d("ACTION_DRAG_ENDED","dropped:" + v.getParent().toString());
                    if (!event.getResult()) {
                        // Nicht gedroppt, d.h. kein "result"
                        v.setVisibility(View.VISIBLE);
                        Log.e("ACTION_DRAG_ENDED", "not in drop zone:" + v.getTag());
                        return false;
                    }
                default:
                    return false;
            }
            return true;
        }

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
    }
}