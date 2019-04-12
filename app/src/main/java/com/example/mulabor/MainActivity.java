package com.example.mulabor;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.net.PasswordAuthentication;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String smsMessage = "";
    private String phoneNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Button janinaButton = (Button) findViewById(R.id.janina_button);
        janinaButton.setOnClickListener(this);
        Button sobieskiButton = (Button) findViewById(R.id.sobieski_button);
        sobieskiButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        DateFormat df = new SimpleDateFormat("HH:mm, dd.MM.yy");
        String date = df.format(Calendar.getInstance().getTime());

        final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.definite);

        switch (v.getId()) {
            case R.id.sobieski_button:

                smsMessage = "Sobieski " + date;

                // Confirmation popup
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Stuff to do
                        sendSms();
                        // play sound
                        mp.start();
                        // toast
                        Toast toast = Toast.makeText(MainActivity.this,
                                R.string.thank_you, Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 50);
                        toast.show();

                    }
                });
                builder.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Stuff to do
                        if (vibe != null) {
                            vibe.vibrate(100);
                        }
                    }
                });

                builder.setMessage(R.string.please_confirm);
                builder.setTitle(R.string.sobieski_mu);

                AlertDialog d = builder.create();
                d.show();
                // api 10 button text color
                d.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
                d.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.GREEN);

                break;

            case R.id.janina_button:

                smsMessage = "Janina " + date;

                // Confirmation popup
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Stuff to do
                        sendSms();
                        // play sound
                        mp.start();
                        // toast
                        Toast toast = Toast.makeText(MainActivity.this,
                                R.string.thank_you, Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 50);
                        toast.show();

                    }
                });
                builder2.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Stuff to do
                        if (vibe != null) {
                            vibe.vibrate(100);
                        }
                    }
                });

                builder2.setMessage(R.string.please_confirm);
                builder2.setTitle(R.string.janina_mu);

                AlertDialog d2 = builder2.create();
                d2.show();
                // api 10 button text color
                d2.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
                d2.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.GREEN);

                break;
        }
    }

    private void sendSms() {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, smsMessage, pi, null);
        finish();
    }
}
