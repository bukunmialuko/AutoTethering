package com.example.autotethering;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button settings = findViewById(R.id.launchSettings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             launchSettings();
            }

        });



        }

    private void launchSettings(){
        boolean throwsException = false;
        //--
        try {
            final Intent intent = new Intent(Intent.ACTION_MAIN, null);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            final ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.TetherSettings");
            intent.setComponent(cn);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity( intent);
        }catch (Exception e) {
            e.printStackTrace();
            throwsException = true;
        }


        //--
        if (throwsException){
            Intent dialogIntent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
            dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(dialogIntent);
        }
    }


}