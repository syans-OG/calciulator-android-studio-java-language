package com.example.uikalkulator3;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.util.Output;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MaterialButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, off, ac, del, bagi, kali, min, plus, jadi, titik;
    TextView txtInput, txtOutput;
    String output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);
        off = findViewById(R.id.btn_off);
        ac = findViewById(R.id.btn_ac);
        del = findViewById(R.id.btn_del);
        bagi = findViewById(R.id.btn_bagi);
        kali = findViewById(R.id.btn_kali);
        min = findViewById(R.id.btn_mines);
        plus = findViewById(R.id.btn_plus);
        jadi = findViewById(R.id.btn_jadi);
        titik = findViewById(R.id.btn_titik);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        off.setOnClickListener(this);
        ac.setOnClickListener(this);
        del.setOnClickListener(this);
        bagi.setOnClickListener(this);
        kali.setOnClickListener(this);
        min.setOnClickListener(this);
        plus.setOnClickListener(this);
        jadi.setOnClickListener(this);
        titik.setOnClickListener(this);

        txtInput = findViewById(R.id.input);
        txtOutput = findViewById(R.id.output);

    }
    @Override
    public void onClick(View view) {
        MaterialButton materialButton = (MaterialButton) view;
        String buttonText = materialButton.getText().toString();
        output = txtOutput.getText().toString();
        if (buttonText.equals("AC")){
            txtOutput.setText("");
            txtInput.setText("");
            output ="";
        } else if (buttonText.equals("OFF")) {
            finish();
        } else if (buttonText.equals("del")) {
            txtInput.setText("");
            output = output.substring(0,output.length()-1);
            txtOutput.setText(output);
        } else if (buttonText.equals("=")) {
            double input = evaluateExpression(output);
            String finalinput = String.valueOf(input);
            if (finalinput.endsWith(".0")){
                finalinput = finalinput.replace(".0","");
            }
            txtOutput.setText(finalinput);
            txtInput.setText(output);
            output = "";
        } else {
            output = output + buttonText;
            txtOutput.setText(output);
            txtInput.setText("");
        }
    }

    private double evaluateExpression(String output) {
        try {
            double input = new ExpressionBuilder(output).build().evaluate();
            if (Double.isNaN(input)) {
                throw new ArithmeticException("Division by zero or invalid experission");
            }
            return input;
        }catch (Exception e){
            e.printStackTrace();
            return Double.NaN;
        }

    }
}
