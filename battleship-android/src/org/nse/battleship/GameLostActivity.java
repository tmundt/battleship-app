package org.nse.battleship;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Tom on 23.07.2015.
 */
public class GameLostActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loose);
    }
}