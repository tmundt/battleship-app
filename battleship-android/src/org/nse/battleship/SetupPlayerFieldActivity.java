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

        // Set tags to identify imageviews = different ships
        imageShipBiggest.setTag("shipBiggest");
        imageShipBig.setTag("shipBig");
        imageShipMedium.setTag("shipMedium");
        imageShipMedium2.setTag("shipMedium2");
        imageShipSmall.setTag("shipSmall");
        imagePlayfield.setTag("imagePlayfield");

        imageShipBiggest.setOnTouchListener(new MyTouchListener());
        imageShipBig.setOnTouchListener(new MyTouchListener());
        imageShipMedium.setOnTouchListener(new MyTouchListener());
        imageShipMedium2.setOnTouchListener(new MyTouchListener());
        imageShipSmall.setOnTouchListener(new MyTouchListener());

//        findViewById(R.id.imageShipBiggest).setOnDragListener(new MyDragListener());
//        findViewById(R.id.imageShipMedium).setOnDragListener(new MyDragListener());
//        findViewById(R.id.imageShipMedium2).setOnDragListener(new MyDragListener());
//        findViewById(R.id.imageShipBig).setOnDragListener(new MyDragListener());
//        findViewById(R.id.imageShipSmall).setOnDragListener(new MyDragListener());
        findViewById(R.id.button_a1).setOnDragListener(new MyDragListener());
        imagePlayfield.setOnDragListener(new MyDragListener());

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
                    if (v != imagePlayfield) {
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


