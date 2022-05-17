package com.app.pickamovie.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;

import com.app.pickamovie.R;

public class PopUp extends Activity {

    static final String KEY = "signOut";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_window);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * .9), (int)(height * .2));

        Button yesButton = findViewById(R.id.yesButton);
        yesButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(KEY, "signOut");
            startActivity(intent);
            finish();
        });

        Button noButton = findViewById(R.id.noButton);
        noButton.setOnClickListener(view -> finish());
    }
}
