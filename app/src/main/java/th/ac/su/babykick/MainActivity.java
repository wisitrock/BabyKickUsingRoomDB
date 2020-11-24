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

        Button button_next = findViewById(R.id.button_showdata); // ปุ่ม SHOW ALL INFORNATION
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(i);
            }
        });

        Button button_advice = findViewById(R.id.button_advice); //ปุ่ม FATAL MOVEMENT
        button_advice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Advice_Activity.class);
                startActivity(i);
            }
        });

        final String currentDatecheck = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()); // กำหนดตัวแปร currentDatecheck เพื่อใช้เช็คเวลาปัจจุบัน
        AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = AppDatabase.getInstance(MainActivity.this);
                final Babymodel[] babymodels = db.userDao().getAllUser();
                for (Babymodel b : babymodels) {
                        if(b.date.equals(currentDatecheck)){  // เช็คเงื่อนไขว่า วันที่ใน database ว่าตรงกับวันนี้ไหมถ้าตรงจะให้จำนวนcount เท่ากับ count ของobject b ที่เรา get ข้อมูลมาจากใน database
                            count=b.count;
                            TextView count_babykick = findViewById(R.id.textview_countkick);
                            count_babykick.setText(String.valueOf(count));  //setText ให้กับTextView ที่แสดงค่า count
                            TextView lasttime_update = findViewById(R.id.lastupdate_textView);
                            lasttime_update.setText(getString(R.string.Lastcount)+ b.lasttime); //setText ให้กับTextView ที่แสดงค่าที่เก็บค่าเวลาล่าสุดที่ทำการกดนับ
                            ProgressBar bar = findViewById(R.id.progressBar);
                            bar.setProgress(count * 10);    //setProgresst ให้กับProgressBar เพื่อแสดงความคืนหน้าของการนับ
                        }
                }

            }
        });


        ImageView imageView_CountKick = findViewById(R.id.imageView_babykick); //ImageView รูปเด็กที่ใช้ในการกดนับการดิ้น
        imageView_CountKick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()); //กำหนดตัวแปร currentDate เพื่อเก็บค่าเวันปัจจุบันเมื่อทำการกดนับ
                final String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());//กำหนดตัวแปร currentTime เพื่อเก็บค่าเวลาปัจจุบันเมื่อทำการกดนับ

                count++;
                TextView count_babykick = findViewById(R.id.textview_countkick);
                count_babykick.setText(String.valueOf(count)); //setText ให้กับTextView ที่แสดงค่า count
                TextView lasttime_update = findViewById(R.id.lastupdate_textView);
                lasttime_update.setText(getString(R.string.Lastcount)+ currentTime);//setText ให้กับTextView ที่แสดงค่าที่เก็บค่าเวลาล่าสุดที่ทำการกดนับ
                ProgressBar bar = findViewById(R.id.progressBar);
                bar.setProgress(count * 10); //setProgresst ให้กับProgressBar เพื่อแสดงความคืนหน้าของการนับ


                AppExecutors executors = new AppExecutors();
                executors.diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase db = AppDatabase.getInstance(MainActivity.this);
                        final Babymodel[] babymodels = db.userDao().getAllUser();
                        boolean checkdate = true; //กำหนดตัวแปร checkdate เพื่อใช้เป็น boolean ในการเช็ควันว่ามีวันในปัจจุบันที่เก็บลงใน database แล้วหรือไม่
                        for(Babymodel b :babymodels ){
                            if(b.date.equals(currentDate)){  // เช็คเงื่อนไขว่า วันที่ใน database ว่าตรงกับวันนี้ไหม ถ้าตรงจะให้จำนวนcount ของ object b บวกเพิ่มไป 1 ครั้ง ทำการอัพเดทไปยัง database
                                b.count++;
                                if(Integer.parseInt(currentTime.substring(0,2))>=0&&Integer.parseInt(currentTime.substring(0,2))<11){ // ถ้าเวลาเปัจจุบันเป็นช่วงเช้าจะทำการอัพเดท object b ที่ตัวแปร time 1 เพิ่มไป 1 ครั้ง
                                    b.time1++;
                                }else if(Integer.parseInt(currentTime.substring(0,2))>=11&&Integer.parseInt(currentTime.substring(0,2))<14){ //ถ้าเวลาเปัจจุบันเป็นช่วงกลางวันจะทำการอัพเดท object b ที่ตัวแปร time 2 เพิ่มไป 1 ครั้ง
                                    b.time2++;
                                }else if(Integer.parseInt(currentTime.substring(0,2))>=14&&Integer.parseInt(currentTime.substring(0,2))<=23){//ถ้าเวลาเปัจจุบันเป็นช่วงเย็นจะทำการอัพเดท object b ที่ตัวแปร time 3 เพิ่มไป 1 ครั้ง
                                    b.time3++;
                                }
                                b.lasttime = currentTime; //กำหนดเวลาที่ทำการกดนับเก็บไว้ในตัวแปร lasttime ของ object b
                                db.userDao().updateUser(b); // ทำการอัพเดท object b ลงไปใน database
                                checkdate=false; //กำหนดให้checkdate เป็น false เพราะว่ามีวันปัจจุบันที่มีข้อมูลในdatabase แล้ว
                            }
                        }
                        if(checkdate){ //checkdate ยังเป็น true แสดงไม่มีวันปัจจุบันที่มีข้อมูลอยู่ในdatabase ดังนั้นเราจึงต้องสร้าง object ของ class Babymodel
                            Babymodel babymodel = null;
                            if(Integer.parseInt(currentTime.substring(0,2))>=0&&Integer.parseInt(currentTime.substring(0,2))<11){ //สร้าง object ของ class Babymodel  เมื่อเวลาเป็นช่วงเช้า
                                babymodel=  new Babymodel(0, currentDate, 1, 0,0,currentTime,1);
                            }else if(Integer.parseInt(currentTime.substring(0,2))>=11&&Integer.parseInt(currentTime.substring(0,2))<14){//สร้าง object ของ class Babymodel  เมื่อเวลาเป็นช่วงกลางวัน
                                babymodel=  new Babymodel(0, currentDate, 0, 1,0,currentTime,1);
                            }else if(Integer.parseInt(currentTime.substring(0,2))>=14&&Integer.parseInt(currentTime.substring(0,2))<=23){//สร้าง object ของ class Babymodel  เมื่อเวลาเป็นช่วงเย็น
                                babymodel=  new Babymodel(0, currentDate, 0, 0,1,currentTime,1);
                            }

                            db.userDao().addUser(babymodel); // เมื่อสร้าง object ของ class Babymodel เรียบร้อยแล้วจะทำการ add ข้อมูลลงไปใน database
                        }

                    }
                });
                if(count==10){ //เช็คจำนวนคั้งที่นับถ้าครบ 10 จะไปเรียก method openWindownDialog()
                    openWindownDialog();
                }
            }

         });


        Button button_down = findViewById(R.id.button_down_count); //ปุ่ม Decrease
        button_down.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(count==0){ //เช็คว่า ถ้าค่า count เท่ากับ 0 แล้วทีการกดปุ่ม Decrease จะ show dialog ว่า ไม่สามารถลดจำนวนการนับได้
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle(R.string.notification);
                    dialog.setMessage(R.string.cannotdecresecount);
                    dialog.setPositiveButton(R.string.ok, null);
                    dialog.show();
                }else{ //ถ้าถ้าค่า count ไม่เท่ากับ 0 จะ show dialog ว่า ต้องการลดจำนวนการนับ 1 ครั้งใช่หรือไม่
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle(R.string.notification);
                    dialog.setMessage(R.string.reducethenumber);
                    dialog.setNegativeButton(R.string.cancel, null); // ถ้ากด Cancel ก็จะไม่ทำการลดจำนวน
                    dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {// ถ้ากด OK ก็จะทำการลดจำนวนการนับไป 1 ครั้ง

                            final String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                            final String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

                            AppExecutors executors = new AppExecutors();
                            executors.diskIO().execute(new Runnable() {
                                @Override
                                public void run() {
                                    AppDatabase db = AppDatabase.getInstance(MainActivity.this);
                                    final Babymodel[] babymodels = db.userDao().getAllUser();
                                       for (Babymodel b : babymodels) {
                                           if (b.date.equals(currentDate)) { // เช็คเงื่อนไขว่า วันที่ใน database ว่าตรงกับวันนี้ไหม ถ้าตรงจะให้จำนวนcount ของ object b ลดจำนวนไป 1 ครั้ง แล้วทำการอัพเดทไปยัง database
                                               b.count--;
                                                count--;
                                               if (Integer.parseInt(currentTime.substring(0, 2)) >= 0 && Integer.parseInt(currentTime.substring(0, 2)) < 11) {// ถ้าเวลาเปัจจุบันเป็นช่วงเช้าจะทำการอัพเดท object b ที่ตัวแปร time 1 ลดไป 1 ครั้ง
                                                   b.time1--;
                                               } else if (Integer.parseInt(currentTime.substring(0, 2)) >= 11 && Integer.parseInt(currentTime.substring(0, 2)) < 14) {// ถ้าเวลาเปัจจุบันเป็นช่วงเช้าจะทำการอัพเดท object b ที่ตัวแปร time 2 ลดไป 1 ครั้ง
                                                   b.time2--;
                                               } else if (Integer.parseInt(currentTime.substring(0, 2)) >= 14 && Integer.parseInt(currentTime.substring(0, 2)) <= 23) {// ถ้าเวลาเปัจจุบันเป็นช่วงเช้าจะทำการอัพเดท object b ที่ตัวแปร time 3 ลดไป 1 ครั้ง
                                                   b.time3--;
                                               }
                                               db.userDao().updateUser(b); //ทำการอัพเดท object b ลงไปใน database

                                               TextView count_babykick = findViewById(R.id.textview_countkick);
                                               count_babykick.setText(String.valueOf(count));//setText ให้กับTextView ที่แสดงค่า count
                                               TextView lasttime_update = findViewById(R.id.lastupdate_textView);
                                               lasttime_update.setText(getString(R.string.Lastcount)+ b.lasttime);//setText ให้กับTextView ที่แสดงค่าที่เก็บค่าเวลาล่าสุดที่ทำการกดนับ
                                               ProgressBar bar = findViewById(R.id.progressBar);
                                               bar.setProgress(count * 10); //setProgresst ให้กับProgressBar เพื่อแสดงความคืนหน้าของการนับ

                                           }
                                       }

                                }

                            });

                            TextView count_babykick = findViewById(R.id.textview_countkick);
                            count_babykick.setText(String.valueOf(count));//setText ให้กับTextView ที่แสดงค่า count
                            TextView lasttime_update = findViewById(R.id.lastupdate_textView);
                            lasttime_update.setText(getString(R.string.Lastcount)+ currentTime);//setText ให้กับTextView ที่แสดงค่าที่เก็บค่าเวลาล่าสุดที่ทำการกดนับ
                            ProgressBar bar = findViewById(R.id.progressBar);
                            bar.setProgress(count * 10);//setProgresst ให้กับProgressBar เพื่อแสดงความคืนหน้าของการนับ
                        }
                    });

                    dialog.show();

                }
            }
        });


    }
    void openWindownDialog(){  //เป็น method ที่ใช้แแสดงการนับ count ครั้งที่10 ว่า ทารกมีสุขภาพดี show เป็น WindownDialog

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
