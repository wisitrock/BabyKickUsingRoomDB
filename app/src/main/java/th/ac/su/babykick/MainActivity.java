package th.ac.su.babykick;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import th.ac.su.babykick.db.AppDatabase;
import th.ac.su.babykick.model.Babymodel;
import th.ac.su.babykick.util.AppExecutors;

public class MainActivity extends AppCompatActivity {
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_next = findViewById(R.id.button_showdata);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(i);
            }
        });

        Button button_advice = findViewById(R.id.button_advice);
        button_advice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Advice_Activity.class);
                startActivity(i);
            }
        });

        final String currentDatecheck = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = AppDatabase.getInstance(MainActivity.this);
                final Babymodel[] babymodels = db.userDao().getAllUser();
                for (Babymodel b : babymodels) {
                        if(b.date.equals(currentDatecheck)){
                            count=b.count;
                            TextView count_babykick = findViewById(R.id.textview_countkick);
                            count_babykick.setText(String.valueOf(count));
                            TextView lasttime_update = findViewById(R.id.lastupdate_textView);
                            lasttime_update.setText("นับครั้งล่าสุดเมื่อเวลา "+ b.lasttime);
                            ProgressBar bar = findViewById(R.id.progressBar);
                            bar.setProgress(count * 10);
                        }
                }

            }
        });


        ImageView imageView_CountKick = findViewById(R.id.imageView_babykick);
        imageView_CountKick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                final String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

                count++;
                TextView count_babykick = findViewById(R.id.textview_countkick);
                count_babykick.setText(String.valueOf(count));
                TextView lasttime_update = findViewById(R.id.lastupdate_textView);
                lasttime_update.setText("นับครั้งล่าสุดเมื่อเวลา "+ currentTime);
                ProgressBar bar = findViewById(R.id.progressBar);
                bar.setProgress(count * 10);


                AppExecutors executors = new AppExecutors();
                executors.diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase db = AppDatabase.getInstance(MainActivity.this);
                        final Babymodel[] babymodels = db.userDao().getAllUser();
                        boolean checkdate = true;
                        for(Babymodel b :babymodels ){
                            if(b.date.equals(currentDate)){
                                b.count++;
                                if(Integer.parseInt(currentTime.substring(0,2))>=0&&Integer.parseInt(currentTime.substring(0,2))<11){
                                    b.time1++;
                                }else if(Integer.parseInt(currentTime.substring(0,2))>=11&&Integer.parseInt(currentTime.substring(0,2))<14){
                                    b.time2++;
                                }else if(Integer.parseInt(currentTime.substring(0,2))>=14&&Integer.parseInt(currentTime.substring(0,2))<=23){
                                    b.time3++;
                                }
                                b.lasttime = currentTime;
                                db.userDao().updateUser(b);
                                checkdate=false;
                            }
                        }
                        if(checkdate){
                            Babymodel babymodel = null;
                            if(Integer.parseInt(currentTime.substring(0,2))>=0&&Integer.parseInt(currentTime.substring(0,2))<11){
                                babymodel=  new Babymodel(0, currentDate, 1, 0,0,currentTime,1);
                            }else if(Integer.parseInt(currentTime.substring(0,2))>=11&&Integer.parseInt(currentTime.substring(0,2))<14){
                                babymodel=  new Babymodel(0, currentDate, 0, 1,0,currentTime,1);
                            }else if(Integer.parseInt(currentTime.substring(0,2))>=14&&Integer.parseInt(currentTime.substring(0,2))<=23){
                                babymodel=  new Babymodel(0, currentDate, 0, 0,1,currentTime,1);
                            }

                            db.userDao().addUser(babymodel);
                        }

                    }
                });
                if(count==10){
                    openWindownDialog();
                }
            }

         });


        Button button_down = findViewById(R.id.button_down_count);
        button_down.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(count==0){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("แจ้งเตือน");
                    dialog.setMessage("ไม่สามารถลดจำนวนครั้งได้");
                    dialog.setPositiveButton("ตกลง", null);
                    dialog.show();
                }else{
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("แจ้งเตือน");
                    dialog.setMessage("ต้องการลดจำนวนการนับ 1 ครั้งหรือไม่");
                    dialog.setNegativeButton("ยกเลิก", null);
                    dialog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            final String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                            final String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

                            AppExecutors executors = new AppExecutors();
                            executors.diskIO().execute(new Runnable() {
                                @Override
                                public void run() {
                                    AppDatabase db = AppDatabase.getInstance(MainActivity.this);
                                    final Babymodel[] babymodels = db.userDao().getAllUser();
                                   if(count>0) {
                                       for (Babymodel b : babymodels) {
                                           if (b.date.equals(currentDate)) {
                                               b.count--;
                                               count--;
                                               if (Integer.parseInt(currentTime.substring(0, 2)) >= 0 && Integer.parseInt(currentTime.substring(0, 2)) < 11) {
                                                   b.time1--;
                                               } else if (Integer.parseInt(currentTime.substring(0, 2)) >= 11 && Integer.parseInt(currentTime.substring(0, 2)) < 14) {
                                                   b.time2--;
                                               } else if (Integer.parseInt(currentTime.substring(0, 2)) >= 14 && Integer.parseInt(currentTime.substring(0, 2)) <= 23) {
                                                   b.time3--;
                                               }
                                               db.userDao().updateUser(b);

                                               TextView count_babykick = findViewById(R.id.textview_countkick);
                                               count_babykick.setText(String.valueOf(count));
                                               TextView lasttime_update = findViewById(R.id.lastupdate_textView);
                                               lasttime_update.setText("นับครั้งล่าสุดเมื่อเวลา "+ b.lasttime);
                                               ProgressBar bar = findViewById(R.id.progressBar);
                                               bar.setProgress(count * 10);

                                           }
                                       }

                                   }
                                }

                            });

                            TextView count_babykick = findViewById(R.id.textview_countkick);
                            count_babykick.setText(String.valueOf(count));
                            TextView lasttime_update = findViewById(R.id.lastupdate_textView);
                            lasttime_update.setText("นับครั้งล่าสุดเมื่อเวลา "+ currentTime);
                            ProgressBar bar = findViewById(R.id.progressBar);
                            bar.setProgress(count * 10);
                        }
                    });

                    dialog.show();

                }
            }
        });


    }
    void openWindownDialog(){

        final Dialog dialog =  new Dialog(this);
        dialog.setContentView(R.layout.dialog_show);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView imageViewClose = dialog.findViewById(R.id.imageViewClose);
        Button btnok =dialog.findViewById(R.id.BtnOK);
        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}
