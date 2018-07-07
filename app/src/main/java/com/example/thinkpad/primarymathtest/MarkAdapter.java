package com.example.thinkpad.primarymathtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Thinkpad on 2018/7/5.
 */

public class MarkAdapter extends RecyclerView.Adapter<MarkAdapter.MyHolder>{

   Context context;
   List<itemInfo> list;
   MyHolder myHolder;


   public MarkAdapter(Context context, List<itemInfo> list){
       this.context = context;
       this.list = list;
   }


    /**
     * 这个viewholder是用来初始化控件的
     */
    class MyHolder extends RecyclerView.ViewHolder{
        TextView questionTv;
        TextView youAnswerTv;
        TextView answerTv;
        public MyHolder(View itemView) {
            super(itemView);
            questionTv = itemView.findViewById(R.id.item_question);
            youAnswerTv = itemView.findViewById(R.id.item_youranswer);
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
                        inflate(R.layout.mark_item_layout, parent, false);
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
        myHolder.youAnswerTv.setText(list.get(position).yourAnswer);
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
