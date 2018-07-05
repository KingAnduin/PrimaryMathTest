package com.example.thinkpad.primarymathtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Thinkpad on 2018/7/5.
 */

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.MyHolder>{

   Context context;
   List<itemInfo> list;

   //使ExamInterface能够得到更新后的list
    public List<itemInfo> getList() {
        return list;
    }

    boolean isVisible;
    MyHolder myHolder;

   public ExamAdapter(Context context,List<itemInfo> list,boolean isVisible){
       this.context = context;
       this.list = list;
       this.isVisible = isVisible;
   }


    /**
     * 这个viewholder是用来初始化控件的
     */
    class MyHolder extends RecyclerView.ViewHolder{
        TextView questionTv;
        EditText youAnswerEv;
        TextView answerTv;
        public MyHolder(View itemView) {
            super(itemView);
            questionTv = itemView.findViewById(R.id.item_question);
            youAnswerEv = itemView.findViewById(R.id.item_youranswer);
            answerTv = itemView.findViewById(R.id.item_answer);
        }
    }

    /**
     * 用这个方法导入布局文件并创建View holder
     *
     */
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.exam_item_layout, parent, false);
        myHolder = new MyHolder(view);
        return myHolder;
    }

    /**
     * 在这里操作item
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        itemInfo info = list.get(position);

        myHolder.questionTv.setText(info.getQuestion());
        myHolder.answerTv.setText(info.getAnswer());
        //隐藏答案
        if(!isVisible){
            myHolder.answerTv.setVisibility(View.GONE);
        }
        else{
            myHolder.answerTv.setVisibility(View.VISIBLE);
            myHolder.youAnswerEv.setText(list.get(position).yourAnswer);
            //不可被点击
            myHolder.youAnswerEv.setEnabled(false);
        }
        //监听EditView是否改变，如果改变就更新list中的yourAnswer
        myHolder.youAnswerEv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                list.get(position).yourAnswer = editable.toString();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //防止数据错位，非常重要！！！！！
    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
