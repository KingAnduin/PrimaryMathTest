package com.example.thinkpad.primarymathtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.weigan.loopview.LoopView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fb;
    private LoopView loopView;
    private List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();

    }

    private void findView() {
        list=new ArrayList<>();
        for(int i=5;i<=100;i++)
        {
            list.add(String.valueOf(i));
        }
        loopView=(LoopView)findViewById(R.id.loopview);
        loopView.setItems(list);
        loopView.setInitPosition(0);
        loopView.setItemsVisibleCount(5);
        loopView.setTextSize(20);
        loopView.setCameraDistance(20);
        fb=(FloatingActionButton)findViewById(R.id.start);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, ""+(loopView.getSelectedItem()+5), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,ExamInterface.class);
                Bundle bundle=new Bundle();
                bundle.putInt("sum",(loopView.getSelectedItem()+5));
                startActivity(intent);
            }
        });
    }

}
