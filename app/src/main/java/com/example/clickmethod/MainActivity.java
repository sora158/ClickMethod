package com.example.clickmethod;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements Button.OnClickListener {
  //  @BindView(R.id.button6)
  //  public Button b6;
    TextView t1;
    private View v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        t1 = (TextView) findViewById(R.id.editText);

        Button b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(this);

        Button b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.setText("点击了匿名内部类作为监听器");
            }
        });

        Button b4 = (Button) findViewById(R.id.button4);
        MyButtonListener listener = new MyButtonListener();
        b4.setOnClickListener(listener);
//外部类*
        Button b5 = (Button) findViewById(R.id.button5);
        OutButton listener1 = new OutButton();
        b5.setOnClickListener(listener1);


    }

    @OnClick(R.id.button6)
    public void show(){
        t1.setText("ButterKnife");
    }

    public void Button2Click(View source) {

        t1.setText("点击了绑定到标签的监听器");
    }

    class MyButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            t1.setText("内部类作为监听器");

        }

    }

    @Override
    public void onClick(View v) {
        t1.setText("Activity本身作为监听器");
    }

    //系统信息
    @OnClick(R.id.button7)
    public void change(){
        Intent intent = new Intent(this,ConfigurationTest.class);
        startActivity(intent);
    }
    @OnClick(R.id.button8)
    public void change2(){
        Intent intent = new Intent(this,ProgressDialogTest.class);
        startActivity(intent);
    }
}
