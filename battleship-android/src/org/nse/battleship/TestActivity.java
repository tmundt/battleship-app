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


    public ImageView imagePlayfield;
    public ImageButton buttonDrehen;
    public ImageButton buttonSpielen;
    public ImageView imageShipBiggest;
    public ImageView imageShipBig;
    public ImageView imageShipMedium;
    public ImageView imageShipMedium2;
    public ImageView imageShipSmall;
    public FrameLayout button_a1;
    public FrameLayout button_a2;
    public FrameLayout button_a3;
    public FrameLayout button_a4;
    public FrameLayout button_a5;
    public FrameLayout button_a6;
    public FrameLayout button_a7;
    public FrameLayout button_a8;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testschiffe);
        imagePlayfield = (ImageView) findViewById(R.id.imagePlayfield);
        buttonDrehen = (ImageButton) findViewById(R.id.buttonDrehen);
        buttonSpielen = (ImageButton) findViewById(R.id.buttonSpielen);
        imageShipBiggest = (ImageView) findViewById(R.id.imageShipBiggest);
        imageShipBig = (ImageView) findViewById(R.id.imageShipBig);
        imageShipMedium = (ImageView) findViewById(R.id.imageShipMedium);
        imageShipMedium2 = (ImageView) findViewById(R.id.imageShipMedium2);
        imageShipSmall = (ImageView) findViewById(R.id.imageShipSmall);
        button_a1 = (FrameLayout) findViewById(R.id.button_a1);



        findViewById(R.id.imageShipBiggest).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.imageShipBig).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.imageShipMedium).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.imageShipMedium2).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.imageShipSmall).setOnTouchListener(new MyTouchListener());

        findViewById(R.id.imageShipBiggest).setOnDragListener(new MyDragListener());
        findViewById(R.id.imageShipMedium).setOnDragListener(new MyDragListener());
        findViewById(R.id.imageShipMedium2).setOnDragListener(new MyDragListener());
        findViewById(R.id.imageShipBig).setOnDragListener(new MyDragListener());
        findViewById(R.id.imageShipSmall).setOnDragListener(new MyDragListener());
        findViewById(R.id.button_a1).setOnDragListener(new MyDragListener());
        findViewById(R.id.button_a2).setOnDragListener(new MyDragListener());
        findViewById(R.id.button_a3).setOnDragListener(new MyDragListener());
        findViewById(R.id.button_a4).setOnDragListener(new MyDragListener());
        findViewById(R.id.button_a5).setOnDragListener(new MyDragListener());
        findViewById(R.id.button_a6).setOnDragListener(new MyDragListener());
        findViewById(R.id.button_a7).setOnDragListener(new MyDragListener());
        findViewById(R.id.button_a8).setOnDragListener(new MyDragListener());
        findViewById(R.id.button_b1).setOnDragListener(new MyDragListener());
        findViewById(R.id.button_b2).setOnDragListener(new MyDragListener());

    }

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    Log.i("Hallo" ,"Action is DragEvent.ACTION_DRAG_STARTED");
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.i("Hallo" ,"Action is DragEvent.ACTION_DRAG_ENTERED");


                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    Log.i("Hallo" ,"Action is DragEvent.ACTION_DRAG_EXITED");



                    break;
                case DragEvent.ACTION_DROP:
                    Log.i("Hallo" ,"Action is DragEvent.ACTION_DROP");
                    // Dropped, reassign View to ViewGroup
                    // View view = (View) event.getLocalState();
                    //ViewGroup owner = (ViewGroup) view.getParent();
                    //owner.removeView(view);
                    //RelativeLayout container = (RelativeLayout) v;
                    //container.addView(view);
                    // view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    Log.i("Hallo" ,"Action is DragEvent.ACTION_DRAG_ENDED");

                default:
                    break;
            }
            return true;
        }

    }
}
