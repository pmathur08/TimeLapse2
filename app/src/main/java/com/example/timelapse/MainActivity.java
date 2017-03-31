package com.example.timelapse;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Photo(View v)
    {
        Log.d(TAG, "here2");

        Intent intent1 = new Intent(this, Photocapture.class);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 001);
        startActivity(intent1);

    }

    public void Photoview(View v)
    {
        Log.d(TAG, "here2");

        Intent intent1 = new Intent(this, PhotoView.class);
        startActivity(intent1);

    }

    //@Override
    public void onRequestPermissionsResult(String permissions[], int[] grantResults) {

        boolean haveAllPerms = true;
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                haveAllPerms = false;
            }
        }

        if (!haveAllPerms) {
            Toast.makeText(getApplicationContext(), "This application requires these permissions", Toast.LENGTH_LONG).show();
        }

        return;
    }
}
