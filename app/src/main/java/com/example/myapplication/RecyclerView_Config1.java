package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Config1 {
    private Context mContext;
    private dataAdapter dataAdapter1;
    public void setConfig(RecyclerView recyclerView, Context context, List<UserHelperClass> data, List<String> keys){
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

        public void bind(UserHelperClass userHelperClass, String key){
            name.setText(userHelperClass.getName());
            money.setText(userHelperClass.getTime());
            totalTr.setText(userHelperClass.getTr());

            this.key = key;
        }
    }
    class dataAdapter extends RecyclerView.Adapter<dataItemView>{
        private List<UserHelperClass> mlist;
        private List<String> mkey;

        public dataAdapter(List<UserHelperClass> list, List<String> mkey) {
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
