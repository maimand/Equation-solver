package com.example.ptb2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;

public class Details extends AppCompatActivity {
    private TextView equation_text, x_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        equation_text = findViewById(R.id.equation_text_view);
        x_text = findViewById(R.id.x_text_view);

        Intent intent = getIntent();
        if(intent != null){
            Equation equation = (Equation) intent.getSerializableExtra("data");

            equation_text.setText(equation.toString());
            x_text.setText(equation.resultOut());
        }

    }
}