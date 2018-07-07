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
import java.util.List;

/**
 * Created by Thinkpad on 2018/7/5.
 * 成绩展示界面
 */

public class MarkInterface extends AppCompatActivity implements View.OnClickListener{

    private Toolbar mToolbar;
    int mark=0;

    List<itemInfo> list = new ArrayList<>();
    RecyclerView recyclerView;
    TextView markTv;
    MarkAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark);

        list = (ArrayList<itemInfo>) getIntent().getSerializableExtra("list");
        for(int i=0;i<list.size();i++){
            if(list.get(i).yourAnswer.equals(list.get(i).answer))
                mark++;
        }

        findView();
        //初始化适配器
        adapter = new MarkAdapter(this,list);
        //配置layoutmanger
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void findView() {
        mToolbar = (Toolbar) findViewById(R.id.exam_toolbar);
        setSupportActionBar(mToolbar);
        recyclerView = findViewById(R.id.rv_list);
        markTv = findViewById(R.id.Tv_mark);
        markTv.setText(mark+"/"+list.size());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}
