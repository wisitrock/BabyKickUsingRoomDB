package th.ac.su.babykick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.TextView;

import th.ac.su.babykick.adapter.BabyAdapter;
import th.ac.su.babykick.db.AppDatabase;
import th.ac.su.babykick.model.Babymodel;
import th.ac.su.babykick.util.AppExecutors;

public class ShowActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        mRecyclerView = findViewById(R.id.baby_count_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ShowActivity.this)); // setLayout ให้กับ RecyclerView
        final AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = AppDatabase.getInstance(ShowActivity.this);
                final Babymodel[] Baby = db.userDao().getAllUser(); // get ข้อมูลจาก database แล้วนำไปเก็บใน array baby ซึ่งมี type เป็น class Babymodel


                executors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        BabyAdapter adapter = new   BabyAdapter(ShowActivity.this, Baby); // สร้าง  object  adapter จาก class  BabyAdapter
                        mRecyclerView.setAdapter(adapter); // setAdapter ให้กับ RecyclerView
                    }
                });


            }
        });

    }
}