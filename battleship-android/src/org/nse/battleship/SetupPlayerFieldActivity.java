package org.nse.battleship;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
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
    public ImageView imageShipBiggest;
    public ImageView imageShipBig;
    public ImageView imageShipMedium;
    public ImageView imageShipMedium2;
    public ImageView imageShipSmall;


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setupplayerfield);
        imagePlayfield = (ImageView) findViewById(R.id.imagePlayfield);
        buttonDrehen = (ImageButton) findViewById(R.id.buttonDrehen);
        buttonSpielen = (ImageButton) findViewById(R.id.buttonSpielen);
        imageShipBiggest = (ImageView) findViewById(R.id.imageShipBiggest);
        imageShipBig = (ImageView) findViewById(R.id.imageShipBig);
        imageShipMedium = (ImageView) findViewById(R.id.imageShipMedium);
        imageShipMedium2 = (ImageView) findViewById(R.id.imageShipMedium2);
        imageShipSmall = (ImageView) findViewById(R.id.imageShipSmall);

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

    }

    private final class MyTouchListener implements View.OnTouchListener {
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
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

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    class MyDragListener implements View.OnDragListener {


        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:

                    break;
                case DragEvent.ACTION_DRAG_EXITED:

                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    //RelativeLayout container = (RelativeLayout) v;
                    //container.addView(view);
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:

                default:
                    break;
            }
            return true;
        }

    }


}


