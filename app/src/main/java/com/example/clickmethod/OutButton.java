package com.example.clickmethod;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.CollationElementIterator;

public class OutButton implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        MainActivity m1 = new MainActivity();
        m1.setContentView(R.layout.activity_main);
        m1.t1.setText("点击了外部类监听");

    }
}
