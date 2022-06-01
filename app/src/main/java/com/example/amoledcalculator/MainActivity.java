package com.example.amoledcalculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {


    public void operandClick(View view) { // taking in numbers and displaying logic of the taken input
        Button btn = findViewById(view.getId());
        TextView display = findViewById(R.id.display);

        String dataOnDisplay = display.getText().toString();
        String btnData = btn.getText().toString();

        if (dataOnDisplay.equals("0")) {
            display.setText(btnData);
        }
        else {
            String res = dataOnDisplay + btn.getText().toString();
            display.setText(res); }

    }

    public boolean delete(View view) {  // delete button logic
        TextView t1 = findViewById(R.id.display);
        t1.setText("0");
        return true;
    }


    public boolean clear(View view) {  // clear button logic
        TextView t1 = findViewById(R.id.display);
        String displayData;
        displayData = t1.getText().toString();

        if (displayData.equals("0") || displayData.equals("Error"))
            t1.setText("0");
        else {
            String clearData;
            clearData = displayData.substring(0, displayData.length() - 1);

            if (clearData.equals(""))
                clearData = "0";
            t1.setText(clearData);

        }
        return true;
    }


    public void operatorClick(View view) { // taking in operators and displaying logic of the taken input
        Button btn = findViewById(view.getId());
        TextView display = findViewById(R.id.display);

        String dataOnDisplay = display.getText().toString();
        String btnData = btn.getText().toString();

        if (dataOnDisplay.equals("0")) {
            display.setText("Enter a number first"); }

        else {
            String res = dataOnDisplay + btn.getText().toString();
            display.setText(res); }
    }


    public void resultClick(View view) { // result calculation
        Button btn = findViewById(view.getId());
        TextView display = findViewById(R.id.display);

        String dataOnDisplay = display.getText().toString();


        if (dataOnDisplay.equals("0")) {
            display.setText("Enter something to calculate"); }

        else {
                int a = EvaluateString.evaluate(dataOnDisplay);
            Log.d("w", String.valueOf(a));
            display.setText(String.valueOf(a));
            Log.d("w", "clear");}
    }

   static class EvaluateString { // for evaluating the final string

        public static int evaluate(String expression) {
            char[] tokens = expression.toCharArray();

            Stack<Integer> values = new Stack<Integer>();
            Stack<Character> ops = new Stack<Character>();

            for (int i = 0; i < tokens.length; i++) {

                if (tokens[i] >= '0' && tokens[i] <= '9') { // if it is a number

                    StringBuffer sbuf = new StringBuffer();

                    while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                        sbuf.append(tokens[i++]);
                         i--;

                    values.push(Integer.parseInt(sbuf.toString()));
                }

                else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/')
                {
                    while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                        values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                    ops.push(tokens[i]);
                }
            }

            while (!ops.empty())
                values.push(applyOp(ops.pop(), values.pop(), values.pop()));

            return values.pop();
        }

        public static boolean hasPrecedence(char op1, char op2) {
            if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
                return false;
            else
                return true;
        }

        public static int applyOp(char op, int b, int a) {
            switch (op) {
                case '+':
                    return (a+b);
                case '-':
                    return (a-b);
                case '*':
                    return (a*b);
                case '/':
                    return (a/b);
            }
            return 0;
        }
    }
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
