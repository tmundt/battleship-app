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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.view.View.OnDragListener;

/**
 * Created by thomasmundt on 28.06.15.
 * Field where player should set up his ships
 */
public class SetupPlayerFieldActivity extends Activity {


    private ImageView imagePlayfield;
    private ImageButton buttonDrehen;
    private ImageButton buttonSpielen;
    private ImageButton buttonShipBiggest;
    private ImageButton buttonShipBig;
    private ImageButton buttonShipMedium;
    private ImageButton buttonShipMedium2;
    private ImageButton buttonShipSmall;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setupplayerfield);
        imagePlayfield = (ImageView) findViewById(R.id.imagePlayfield);
        buttonDrehen = (ImageButton) findViewById(R.id.buttonDrehen);
        buttonSpielen = (ImageButton) findViewById(R.id.buttonSpielen);
        buttonShipBiggest = (ImageButton) findViewById(R.id.buttonShipBiggest);
        buttonShipBig = (ImageButton) findViewById(R.id.buttonShipBig);
        buttonShipMedium = (ImageButton) findViewById(R.id.buttonShipMedium);
        buttonShipMedium2 = (ImageButton) findViewById(R.id.buttonShipMedium2);
        buttonShipSmall = (ImageButton) findViewById(R.id.buttonShipSmall);
        findViewById(R.id.buttonShipBiggest).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.buttonShipBig).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.buttonShipMedium).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.buttonShipMedium2).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.buttonShipSmall).setOnTouchListener(new MyTouchListener());
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

    class MyDragListener implements View.OnDragListener {
        Drawable enterShape = getResources().getDrawable(R.drawable.image_playfield);
        Drawable normalShape = getResources().getDrawable(R.drawable.image_playfield);


        @Override
        public boolean onDrag(View v, DragEvent event) {
            return false;
        }
    }
}
