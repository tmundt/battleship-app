package org.nse.battleship;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private ImageView startImage;
    private ImageButton startButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        startImage = (ImageView) findViewById(R.id.startImage);
        startButton = (ImageButton) findViewById(R.id.buttonSpielStarten);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setupPlayerField = new Intent(this, SetupPlayerFieldActivity.class);
                startActivity(setupPlayerField);


            }
        });
    }
}
