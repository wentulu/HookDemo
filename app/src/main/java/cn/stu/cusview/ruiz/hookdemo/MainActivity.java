package cn.stu.cusview.ruiz.hookdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import cn.stu.cusview.ruiz.hookdemo.hook.RepleaceUtil;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RepleaceUtil.repleaceInstrumentation(this);

        text_view = findViewById(R.id.text_view);
        text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondaryActivity.class);
                getApplicationContext().startActivity(intent);
            }
        });


    }
}
