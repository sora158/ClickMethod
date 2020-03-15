package com.example.clickmethod;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ProgressDialogTest extends AppCompatActivity {

    private ProgressBar progressBar;
    private int mProgress = 0;
    private  Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_dialog_test);

        //获取进度条控件
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        //Android 不支持在主线程中更新UI组件 所以提供了Handler对象
        mHandler = new Handler(){
            //通过消息代码msg进行区分是否加载完成
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what ==0x111)
                {
                    //给进度条赋值
                    progressBar.setProgress(mProgress);
                }else
                {
                    Toast.makeText(ProgressDialogTest.this,"加载完成",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        };

        //新建一个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                {
                    //具体执行的方法的进度，返回进度
                    mProgress = doWork();
                    //定义一个标识，标识进度条是否加载完成
                    Message message = new Message();
                    if(mProgress <100)
                    {
                        //表示还没加载到100  则向进度条报告进度
                        message.what = 0x111;
                        mHandler.sendMessage(message);
                    }else
                    {
                        //表示此时已经加载完成，向Handler发送消息  并跳出循环
                        message.what =0x110;
                        mHandler.sendMessage(message);
                        break;
                    }
                }
            }
            //具体执行 并返回进度的方法
            private int doWork()
            {
                //进度条随机加进度
                mProgress +=Math.random()*10;
                try {
                    //进程休眠0.5秒
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return mProgress;
            }
        }).start(); //启动进程
    }
}