package com.example.khum.demo0223.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khum.demo0223.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 水平滑动的recyclerView
 */

public class SweepHorizontalActivity extends AppCompatActivity {

    private RecyclerView recyclerview;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sweep_horizontal);
        initView();
    }

    private void initView() {
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview.setLayoutManager(layoutManager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,5);
//        recyclerview.setLayoutManager(gridLayoutManager);
        initData();
        myAdapter myAdapter = new myAdapter(data);
        recyclerview.setAdapter(myAdapter);
    }

    private void initData() {
        data = new ArrayList<>();
        data.add("nihao1");
        data.add("nihao2");
        data.add("nihao3");
        data.add("nihao4");
        data.add("nihao5");
        data.add("nihao6");
        data.add("nihao7");
        data.add("nihao8");
        data.add("nihao9");
        data.add("nihao10");
        data.add("nihao11");
    }

    class myAdapter extends RecyclerView.Adapter<MyHolder>{

        private List<String> list;

        public myAdapter(List<String> list) {
            this.list = list;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view  = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_h,parent,false);
            MyHolder myHolder = new MyHolder(view);
            return myHolder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.mTextview.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    static class MyHolder extends RecyclerView.ViewHolder{

        private final TextView mTextview;

        MyHolder(View itemView) {
            super(itemView);
            mTextview = itemView.findViewById(R.id.textview);
        }
    }
}
