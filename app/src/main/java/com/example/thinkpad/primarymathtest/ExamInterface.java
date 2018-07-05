package com.example.thinkpad.primarymathtest;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Created by Thinkpad on 2018/7/5.
 */

public class ExamInterface extends AppCompatActivity implements View.OnClickListener{


    int symbolNum;
    int numRange;

    String symbolStr = "+-*/";
    String questionStr = "";
    String answerStr = "233";
    int timeInt = 30;
    int mark = 0;

    private Toolbar mToolbar;
    private TextView timeTv;
    Button finishBt;
    List<itemInfo> list = new ArrayList<>();
    RecyclerView recyclerView;
    ExamAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        findView();

        for(int i=0;i<10;i++){
            randomExpression();
        }

        //初始化适配器
        adapter = new ExamAdapter(this,list,false);
        //配置layoutmanger
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


        /**
         * 倒计时300s，每1s执行一次onTick（）
         * 倒计时结束后执行onFinish（）
         */
        CountDownTimer timer = new CountDownTimer(300*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeTv.setText(millisUntilFinished/1000 + "s");
            }

            @Override
            public void onFinish() {
                showResult(adapter);
                Toast.makeText(ExamInterface.this, "考试结束", Toast.LENGTH_SHORT).show();
            }
        };
        timer.start();
    }

    //判断正误
    private void showResult(ExamAdapter adapter) {
        List<itemInfo> list = adapter.getList();
        adapter = new ExamAdapter(this,list,true);
        recyclerView.setAdapter(adapter);
        //adapter.update(list);
        for(int i=0;i<list.size();i++) {
            if (Objects.equals(list.get(i).answer, list.get(i).yourAnswer)) {
                mark += 10;
            }
        }
        Toast.makeText(ExamInterface.this,"本次测验你的成绩为"+adapter.mark,Toast.LENGTH_SHORT).show();

    }


    private void findView() {
        mToolbar = (Toolbar) findViewById(R.id.exam_toolbar);
        timeTv = findViewById(R.id.item_time);
        recyclerView = findViewById(R.id.rv_list);
        finishBt = findViewById(R.id.item_finish);
        finishBt.setOnClickListener(this);
    }

    //生成数学表达式expressStr
    private void randomExpression() {
        //符号个数
        int minSymbol = 1;
        int maxSymbol = 3;
        Random randomS = new Random();
        symbolNum = randomS.nextInt(maxSymbol)%(maxSymbol-minSymbol+1) + minSymbol;
        //数值范围
        int minNumber = 1;
        int maxNumber = 11;

        //生成随机表达式
        for(int i = 1; i <= symbolNum+(symbolNum+1) ;i++){
            if(i%2 == 1){
                Random randomN = new Random();
                numRange = randomN.nextInt(maxNumber)%(maxNumber-minNumber+1) + minNumber;
                questionStr +=  numRange + " ";
            }
            else{
                char symbol = (symbolStr.charAt((int)(Math.random() * 4)));
                questionStr += symbol + " ";
            }
        }
        //计算答案
        ToAnswer answer = new ToAnswer(questionStr);
        answerStr = answer.getAnswer();

        //生成问题
        questionStr += " = ______?";
        //将数据传入list中
        itemInfo info = new itemInfo(questionStr,answerStr,"");
        list.add(info);
        //清空字符串
        questionStr = "";
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_finish:
                showResult(adapter);
                break;
        }
    }
}
