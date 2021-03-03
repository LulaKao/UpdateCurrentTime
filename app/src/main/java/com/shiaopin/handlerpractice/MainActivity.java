package com.shiaopin.handlerpractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView txt_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initial View
        txt_time = findViewById(R.id.txt_time);

        // new Thread
        Thread thread = new Thread(runnable);

        // start Thread
        thread.start();
    }

//    //========= Method 1 START =========//
//    // new Runnable
//    private Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            while (true){ // update current time every sec
//                try {
//                    Thread.sleep(1000); // sleep 1 sec
//                    Message msg = new Message(); // new massage
//                    msg.what = 1; // set message = 1
//                    mHandler.sendMessage(msg); // send message to handler
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    };
//
//    // new Handler
//    private Handler mHandler = new Handler(){
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//            if(msg.what == 1){
//                long currentTime = System.currentTimeMillis(); // get current time
//                Date date = new Date(currentTime); // new Date
//                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss  EEEE", new Locale("en", "US")); // set date format
//                format.setTimeZone(TimeZone.getTimeZone("GMT+8")); // set time zone
//                txt_time.setText(format.format(date)); // set text to txt_time
//            }
//        }
//    };
//    //========= Method 1 END =========//

    //========= Method 2 START =========//
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (true){ // update current time every sec
                try {
                    Thread.sleep(1000); // sleep 1 sec
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            long currentTime = System.currentTimeMillis(); // get current time
                            Date date = new Date(currentTime); // new Date
                            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss  EEEE", new Locale("en", "US")); // set date format
                            format.setTimeZone(TimeZone.getTimeZone("GMT+8")); // set time zone
                            txt_time.setText(format.format(date)); // set text to txt_time
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    //========= Method 2 END =========//
}