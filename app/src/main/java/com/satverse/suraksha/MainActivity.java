package com.satverse.suraksha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    CardView Siren, Emergency, Helpline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        setupHyperlink();

        Intent backgroundService = new Intent( getApplicationContext(), ScreenOnOffBackgroundService.class );
        this.startService( backgroundService );
        Log.d( ScreenOnOffReceiver.SCREEN_TOGGLE_TAG, "Activity onCreate" );
        int permissionCheck = ContextCompat.checkSelfPermission (MainActivity.this, Manifest.permission.SEND_SMS);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission (MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission (MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions (MainActivity.this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE, Manifest.permission.READ_CONTACTS}, 0);
        }

        //this is a special permission required only by devices using
        //Android Q and above. The Access Background Permission is responsible
        //for populating the dialog with "ALLOW ALL THE TIME" option
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, 100);
        }


        Siren = findViewById( R.id.siren );
        Emergency = findViewById( R.id.emergency );
        Helpline = findViewById( R.id.helpline );
        Siren.setOnClickListener(v -> startActivity( new Intent( getApplicationContext(), Flashing.class ) ));
        Emergency.setOnClickListener( v -> startActivity( new Intent( getApplicationContext(), SmsActivity.class ) ) );
//
        Helpline.setOnClickListener(v -> startActivity( new Intent( getApplicationContext(), HelplineActivity.class ) ));
    }

    private void setupHyperlink() {

        TextView reviewform = findViewById(R.id.review);
        reviewform.setMovementMethod(LinkMovementMethod.getInstance());

    }

}