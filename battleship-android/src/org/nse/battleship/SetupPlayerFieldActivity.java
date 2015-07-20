package org.nse.battleship;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onCreate(Bundle savedInstanceState) {
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
                        Log.e("ACTION_DROP","wrong drop!");
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
                    if(x < 140) {
                        Log.e("ACTION_DROP","zu weit links: " + x);
                        x = 140;
                    }

                    if(x > 965) {
                        x = 965;
                    }

                    // Begrenze nach oben
                    if(y < 249) {
                        Log.e("ACTION_DROP","zu weit oben: " + y);
                        y = 249;
                    }

                    //Begrenze nach unten, schiffsabhängig
                    switch (dragTag) {
                        case "shipCarrier":
                            if (y > 583) {
                                Log.e("ACTION_DROP","zu weit unten: " + y);
                                y = 583;
                            }
                            break;
                        case "shipCruiser":
                            if (y > 690) {
                                Log.e("ACTION_DROP","zu weit unten: " + y);
                                y = 690;
                            }
                            break;
                        case "shipGunboat":
                        case "shipSubmarine":
                            if (y > 805) {
                                Log.e("ACTION_DROP","zu weit unten: " + y);
                                y = 805;
                            }
                            break;
                        case "shipSpeedboat":
                            if (y > 924) {
                                Log.e("ACTION_DROP","zu weit unten: " + y);
                                y = 924;
                            }
                    }

                    // Speichere Schiff in Map ab
                    // rechne Koordinaten in Zellen
                    // Pruefe ob bereits belegt
                    // Falls belegt: kein Drop und kein Speichern


                    // Vermeide das Übereinanderlegen von Schiffen
                    dragView.setX(x);
                    dragView.setY(y);
                    dragView.setVisibility(View.VISIBLE);
//                    ViewGroup owner = (ViewGroup) dragView.getParent();
//                    owner.removeView(dragView);
//                    RelativeLayout container = (RelativeLayout) v;
//                    container.addView(dragView);
                    //dragView.setVisibility(View.VISIBLE);
                    Log.d("ACTION_DROP","dropped:" + dragView.getTag());
                    Log.d("ACTION_DROP:","x ist: " + (int) x + "y ist: " + (int) y);
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
    }
}