package com.example.thinkpad.primarymathtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Thinkpad on 2018/7/5.
 */

public class MarkInterface extends AppCompatActivity implements View.OnClickListener{

    private Toolbar mToolbar;
    private TextView timeTv;

    ArrayList<itemInfo>list = new ArrayList<>();
    RecyclerView recyclerView;

    MarkAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        findView();

        list = (ArrayList<itemInfo>) getIntent().getSerializableExtra("list");

        //初始化适配器
        adapter = new MarkAdapter(this,list,false);
        //配置layoutmanger
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }



    private void findView() {
        mToolbar = (Toolbar) findViewById(R.id.exam_toolbar);
        setSupportActionBar(mToolbar);
        timeTv = findViewById(R.id.item_time);
        recyclerView = findViewById(R.id.rv_list);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}
