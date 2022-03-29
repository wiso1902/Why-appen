package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private dataAdapter dataAdapter1;
    public void setConfig(RecyclerView recyclerView, Context context, List<UserHelperClass2> data, List<String> keys){
        mContext = context;
        dataAdapter1 = new dataAdapter(data, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(dataAdapter1);
    }

    class dataItemView extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView totalTr;
        private TextView money;

        private String key;

        public dataItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.data_list_item, parent, false));

            name = (TextView) itemView.findViewById(R.id.test1);
            totalTr = (TextView) itemView.findViewById(R.id.textView4);
            money = (TextView) itemView.findViewById(R.id.test2);

        }

        public void bind(UserHelperClass2 userHelperClass2, String key){
            name.setText(userHelperClass2.getName());
            money.setText(userHelperClass2.getMoney() + " Kr");
            totalTr.setText(userHelperClass2.getTotalTr() + " Träningar");

            this.key = key;
        }
    }
    class dataAdapter extends RecyclerView.Adapter<dataItemView>{
        private List<UserHelperClass2> mlist;
        private List<String> mkey;

        public dataAdapter(List<UserHelperClass2> list, List<String> mkey) {
            this.mlist = list;
            this.mkey = mkey;
        }

        @NonNull
        @Override
        public dataItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new dataItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull dataItemView holder, int position) {
            holder.bind(mlist.get(position), mkey.get(position));
        }

        @Override
        public int getItemCount() {
            return mlist.size();
        }
    }


}
