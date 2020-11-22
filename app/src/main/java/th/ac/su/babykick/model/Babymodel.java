package th.ac.su.babykick.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "BabyCounttb")
public class Babymodel {

        @PrimaryKey(autoGenerate = true)
        public final int id;

        @ColumnInfo(name = "Date")
        public final  String date;

        @ColumnInfo(name = "Morning")
        public   int time1;

        @ColumnInfo(name = "Afternoon")
        public  int time2;

        @ColumnInfo(name = "Evening")
        public   int time3;

        @ColumnInfo(name = "LastTime")
        public   String lasttime;

        @ColumnInfo(name = "Count")
        public  int count;



        // Alt+Insert


    public Babymodel(int id, String date, int time1, int time2, int time3, String lasttime, int count) {
        this.id = id;
        this.date = date;
        this.time1 = time1;
        this.time2 = time2;
        this.time3 = time3;
        this.lasttime = lasttime;
        this.count = count;
    }
}
