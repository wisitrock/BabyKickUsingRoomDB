package th.ac.su.babykick.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import th.ac.su.babykick.R;
import th.ac.su.babykick.model.Babymodel;


public class BabyAdapter extends RecyclerView.Adapter<BabyAdapter.MyViewHolder> {
    public  Context mContext;
    public Babymodel [] mBabymodel;
    public BabyAdapter(Context context, Babymodel[] users){
        this.mContext=context;
        this.mBabymodel =users;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.item_baby,parent,false);
       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) { // set ข้อมูลเราจะให้แสดงผล  MyViewHolder
       Babymodel baby = mBabymodel[position];
         holder.dateresult.setText(baby.date);
         holder.countreult.setText(String.valueOf(baby.count));
         holder.threetime.setText(String.valueOf(baby.time1)+" - "+String.valueOf(baby.time2)+" - "+String.valueOf(baby.time3));
         holder.genderImageView.setImageResource(baby.count>=10 ? R.drawable.babyr:0);
    }

    @Override
    public int getItemCount() {
        return mBabymodel.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView dateresult;
        TextView countreult;
        ImageView genderImageView;
        TextView threetime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.dateresult=itemView.findViewById(R.id.date_text_view);
            this.countreult=itemView.findViewById(R.id.count_text_view);
            this.genderImageView=itemView.findViewById(R.id.baby_imageView);
            this.threetime=itemView.findViewById(R.id.threetime_textview);
        }
    }
}
