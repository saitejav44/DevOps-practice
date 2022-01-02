package com.example.chatin.chatin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class startActivity extends AppCompatActivity {

    private Button mRegBtn;
    private Button mloginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getSupportActionBar().setTitle("ChatIn");

        mRegBtn =(Button)findViewById(R.id.start_reg_btn);

        mRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent reg_intent = new Intent(startActivity.this,RegisterActivity.class);
                startActivity(reg_intent);
            }
        });

        mloginBtn = (Button)findViewById(R.id.login_btn);
        mloginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login_intent = new Intent(startActivity.this,LoginActivity.class);
                startActivity(login_intent);
            }
        });

    }
}

