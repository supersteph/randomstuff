package com.swu.healthmonitor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



import static android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS;


public class MainActivity extends AppCompatActivity {
    String s = "";
    public void setEmail(View view){
        EditText e = (EditText) findViewById(R.id.editText);
        s = e.getText().toString();
        System.out.println(s);

        SharedPreferences settings = getSharedPreferences("com.swu.AccessibilityDemo-master",0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("address",s);

        editor.commit();

    }

    private static final Intent sSettingsIntent = new Intent(ACTION_ACCESSIBILITY_SETTINGS);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(sSettingsIntent);
            }
        });

        EditText text = (EditText) findViewById(R.id.editText);


    }
}
