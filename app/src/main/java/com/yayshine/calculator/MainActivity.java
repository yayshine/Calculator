package com.yayshine.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected int first = 0;
    protected int second = 0;
    protected boolean isNewInput = true;
    protected int operator = -1;
    protected TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onButtonClick(View v) {
        // calculated result is displayed in this textView
        result = (TextView)findViewById(R.id.result);

        // switch according to button id
        switch(v.getId()){
            // when c button is pushed
            case R.id.btn_c:
                // reset first, second and operator values
                first = 0;
                second = 0;
                operator = -1;

                // 0 is displayed as result
                result.setText(Integer.toString(first));
                // new input will be accepted
                isNewInput = true;
                break;
            // when operators are pushed
            case R.id.btn_multiplier:
                // operator will be 2 eventually
                operator++;
            case R.id.btn_minus:
                // operator will be 1
                operator++;
            case R.id.btn_plus:
                // operator will be 0
                operator++;
                // store input as first value
                first = Integer.parseInt(result.getText().toString());
                // new input will be accepted
                isNewInput = true;
                break;
            // when = is pushed
            case R.id.btn_equals:
                // store input as second value
                second = Integer.parseInt(result.getText().toString());
                // num will be displayed as result
                int num = 0;
                switch (operator){
                    // plus
                    case 0:
                        num = first+second;
                        break;
                    // minus
                    case 1:
                        num = first-second;
                        break;
                    // multiplier
                    case 2:
                        num = first*second;
                        break;
                }
                // check if operator was clicked
                if(operator != -1) {
                    // result is displayed
                    String s = Integer.toString(num);
                    result.setText(s);
                    // reset operator
                    operator = -1;
                }
                break;
            // when a number button is pushed
            default:
                // get the number input as string
                Button btn_num = (Button)v;
                String number = btn_num.getText().toString();

                if(isNewInput){
                    // reset the result display
                    result.setText(number);
                    isNewInput = false;
                }else{
                    // concat past digit(s) with new input
                    String past = result.getText().toString();
                    // if first digit entered is 0, get rid of it
                    if(past.equals("0")){
                        result.setText(number);
                    }else{
                        result.setText(past+number);
                    }
                }
                break;
        }
    }
}
