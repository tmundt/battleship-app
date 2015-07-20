package org.nse.battleship;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

/**
 * Created by thomasmundt on 28.06.15.
 * Field where player should set up his ships
 */
public class TestActivity extends Activity {


    public ImageButton buttonDrehen;
    public ImageButton buttonSpielen;
    public ImageView imageShipCarrier;
    public ImageView imageShipCruiser;
    public ImageView imageShipGunboat;
    public ImageView imageShipSpeedboat;
    public ImageView imageShipSubmarine;
   // public ImageView button_a1;
    public LinearLayout feld1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testschiffe);
        feld1 = (LinearLayout) findViewById(R.id.feld1);
        //button_a1 = (ImageView) findViewById(R.id.button_a1);
        buttonDrehen = (ImageButton) findViewById(R.id.buttonDrehen);
        buttonSpielen = (ImageButton) findViewById(R.id.buttonSpielen);
        imageShipCarrier = (ImageView) findViewById(R.id.imageShipCarrier);
        imageShipCruiser = (ImageView) findViewById(R.id.imageShipCruiser);
        imageShipGunboat = (ImageView) findViewById(R.id.imageShipGunboat);
        imageShipSpeedboat = (ImageView) findViewById(R.id.imageShipSpeedboat);
        imageShipSubmarine = (ImageView) findViewById(R.id.imageShipSubmarine);

        // Set tags to identify imageviews = different ships
        imageShipCarrier.setTag("shipCarrier");
        imageShipCruiser.setTag("shipCruiser");
        imageShipGunboat.setTag("shipGunboat");
        imageShipSpeedboat.setTag("shipSpeedboat");
        imageShipSubmarine.setTag("shipSubmarine");


        findViewById(R.id.imageShipCarrier).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.imageShipCruiser).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.imageShipGunboat).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.imageShipSpeedboat).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.imageShipSubmarine).setOnTouchListener(new MyTouchListener());

        //findViewById(R.id.button_a1).setOnDragListener(new MyDragListener());
        //button_a1.setOnDragListener(new MyDragListener());
        findViewById(R.id.feld1).setOnDragListener(new MyDragListener());
        feld1.setOnDragListener(new MyDragListener());

       // findViewById(R.id.imageShipCarrier).setOnDragListener(new MyDragListener());
       // findViewById(R.id.imageShipCruiser).setOnDragListener(new MyDragListener());
       // findViewById(R.id.imageShipGunboat).setOnDragListener(new MyDragListener());
       // findViewById(R.id.imageShipSpeedboat).setOnDragListener(new MyDragListener());
       // findViewById(R.id.imageShipSubmarine).setOnDragListener(new MyDragListener());


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
                case MotionEvent.ACTION_CANCEL:
                    Log.e("ACTION_CANCEL", "ship x-coord:" + view.getX());
            }
            return true;
        }
    }

    class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.d("ACTION_DRAG_ENTERED","entered:" + v.getTag());
                    break;
                case DragEvent.ACTION_DRAG_EXITED:

                    break;
                case DragEvent.ACTION_DROP:
                    String viewTag = (String) v.getTag();
                    Log.i("ACTION_DROP","target view is: " +v.getTag());
                    Log.i("ACTION_DROP","view v is: " + v);
                    if (v != feld1) {
//                    if (viewTag.equals("imagePlayfield")== false) {
                        Log.e("ACTION_DROP","wrong drop!");
                        v.setVisibility(View.VISIBLE);
                        return false;
                    }
                    // Dropped, reassign View to ViewGroup
                    View dragView = (View) event.getLocalState();
                    float X = event.getX();
                    float Y = event.getY();

                    Log.d("ACTION_DROP:","X " + (int) X + "Y " + (int) Y);
                    dragView.setX(X);
                    dragView.setY(Y);
                    dragView.setVisibility(View.VISIBLE);
//                    ViewGroup owner = (ViewGroup) dragView.getParent();
//                    owner.removeView(dragView);
//                    RelativeLayout container = (RelativeLayout) v;
//                    container.addView(dragView);
                    //dragView.setVisibility(View.VISIBLE);
                    Log.d("ACTION_DROP","dropped:" + dragView.getTag());
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    Log.d("ACTION_DRAG_ENDED","dropped:" + v.getId());
                default:
                    return false;
            }
            return true;
        }


    }
}
