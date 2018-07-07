package com.example.thinkpad.primarymathtest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Han on 2018/7/7.
 * 试题界面
 */

public class ExamInterfaceView extends AppCompatActivity implements View.OnClickListener{

    TextView questionIdTv;
    TextView remainingTimeTv;
    TextView questionTv;
    TextView nextTv;
    EditText yourAnswerEt;
    Button confirmBtn;

    List<itemInfo> list = new ArrayList<itemInfo>();

    int questionNum;                //问题个数
    int symbolNum;                  //符号个数（1-3）
    int numRange;                   //数值范围（1-11）
    int countDown;                  //倒计时，，默认20s
    int questionId=0;                 //当前题号
    int totalTime;                  //总时间

    String symbolStr = "+-*/";
    String questionStr = "";        //数学表达式
    String answerStr = "233";       //答案

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_view);
        findView();
        init();
        updateUI(0);

        /**
         * 倒计时300s，每1s执行一次onTick（）
         * 倒计时结束后执行onFinish（）
         */
        CountDownTimer timer = new CountDownTimer(totalTime*1000,1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                if(countDown == 0){
                    countDown = 20;
                    list.get(questionId).yourAnswer = yourAnswerEt.toString()+"";   //保存用户该题答案
                    questionId++;
                    updateUI(questionId);
                }
                remainingTimeTv.setText(countDown + "s");
                countDown--;
            }

            @Override
            public void onFinish() {
                Toast.makeText(ExamInterfaceView.this, "考试结束", Toast.LENGTH_SHORT).show();
            }
        };
        timer.start();
    }

    /**
     * 用于动态更新题目与题号
     * @param questionId 当前题号
     */
    @SuppressLint("SetTextI18n")
    private void updateUI(int questionId) {

        questionTv.setText(list.get(questionId).question);
        questionIdTv.setText(questionId + 1 + "");

    }

    /**
     * 初始化总时间、题号、题目数量，
     * 随机生成数学表达式
     */
    private void init() {

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            questionNum = bundle.getInt("sum",0);
            totalTime = questionNum*20;
            questionId = 0;
        }

        //随机生成试题与对应的答案
        for(int i=0;i<questionNum;i++){
            randomExpression();
        }

    }

    /**
     * 绑定控件
     */
    private void findView() {
        questionIdTv = findViewById(R.id.Tv_questionId);
        questionTv = findViewById(R.id.Tv_question);
        remainingTimeTv = findViewById(R.id.Tv_remainingTime);
        nextTv = findViewById(R.id.Tv_next);
        yourAnswerEt = findViewById(R.id.Et_yourAnswer);
        confirmBtn = findViewById(R.id.Btn_confirm);

        confirmBtn.setOnClickListener(this);
        nextTv.setOnClickListener(this);
    }

    /**
     * 生成数学表达式expressStr,并计算答案
     */
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
        questionStr += " =";
        //将数据传入list中
        itemInfo info = new itemInfo(questionStr,answerStr,"");
        list.add(info);
        //清空字符串
        questionStr = "";
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Btn_confirm:
                countDown = 20;
                list.get(questionId).yourAnswer = yourAnswerEt.toString()+"";           //保存用户该题答案
                questionId++;
                if(questionId<20)
                    updateUI(questionId);
                else{
                    Intent intent = new Intent();
                    intent.setClass(ExamInterfaceView.this,ExamInterface.class);
                    intent.putExtra("lists",(Serializable)list );
                    startActivity(intent);
                }

                break;
            case R.id.Tv_next:
                countDown = 20;
                list.get(questionId).yourAnswer = yourAnswerEt.toString()+"";           //保存用户该题答案
                questionId++;
                updateUI(questionId);
                break;
        }
    }

}
