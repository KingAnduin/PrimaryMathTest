package com.example.thinkpad.primarymathtest;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import com.weigan.loopview.LoopView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton mStartFb;
    private FloatingActionButton mLanguageFb;
    private LoopView mLoopView;
    private List<String> list;
    private String[] languageType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
    }

    private void findView() {
        languageType = getResources().getStringArray(R.array.languageType);
        list = new ArrayList<>();
        for (int i = 5; i <= 100; i++) {
            list.add(String.valueOf(i));
        }
        mLoopView = (LoopView) findViewById(R.id.loopview);
        mLoopView.setItems(list);
        mLoopView.setInitPosition(0);
        mLoopView.setItemsVisibleCount(5);
        mLoopView.setTextSize(20);
        mLoopView.setCameraDistance(20);
        mStartFb = (FloatingActionButton) findViewById(R.id.start);
        mLanguageFb = (FloatingActionButton) findViewById(R.id.language);
        mStartFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "" + (mLoopView.getSelectedItem() + 5), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ExamInterface.class);
                Bundle bundle = new Bundle();
                bundle.putInt("sum", (mLoopView.getSelectedItem() + 5));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        mLanguageFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLanguageDialog();
            }
        });
    }

    private void showLanguageDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.language_change))
                .setIcon(R.mipmap.ic_dialog_language)
                .setSingleChoiceItems(languageType, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            setLang(Locale.SIMPLIFIED_CHINESE);
                            dialog.dismiss();
                        } else if (which == 1) {
                            setLang(Locale.ENGLISH);

                        } else if (which == 2) {
                            setLang(Locale.JAPAN);
                            dialog.dismiss();
                        } else if (which == 3) {
                            setLang(Locale.FRANCE);
                            dialog.dismiss();
                        }
                    }
                }).create();
        dialog.show();
    }

    private void setLang(Locale l) {

        // 获得res资源对象

        Resources resources = getResources();

        // 获得设置对象

        Configuration config = resources.getConfiguration();

        // 获得屏幕参数：主要是分辨率，像素等。

        DisplayMetrics dm = resources.getDisplayMetrics();

        // 语言

        config.locale = l;

        resources.updateConfiguration(config, dm);


        // 刷新activity才能马上奏效

        startActivity(new Intent().setClass(MainActivity.this,

                MainActivity.class));

        MainActivity.this.finish();

    }

}
