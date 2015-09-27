package com.example.aasaqt.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import java.io.StringReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class Home extends Activity implements View.OnClickListener {

    private List<Button> buttons;
    private static final int[] BUTTON_ID = {
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4,
            R.id.button5,
            R.id.button6,
            R.id.button7,
            R.id.button8,
            R.id.button9,
            R.id.buttonAdd,
            R.id.buttonSubtract,
            R.id.buttonMultiply,
            R.id.buttonDivide,
            R.id.buttonDot,
            R.id.buttonToggleSign,
            R.id.buttonClear,
            R.id.buttonEquals,
            R.id.button0,
            R.id.buttonMemoryAdd,
            R.id.buttonMemoryClear,
            R.id.buttonMemoryRead,
            R.id.buttonMemorySubtract,
            R.id.buttonSin,
            R.id.buttonCos,
            R.id.buttonTan,
            R.id.buttonSquared,
            R.id.buttonInverse,
            R.id.buttonSquareRoot
    };
    private TextView text;
    private Boolean userIsInTheMiddleOfTypingANumber = false;
    private static final String DIGITS = "0123456789.";
    DecimalFormat df = new DecimalFormat("@###########");
    private CalculatorBrain mCalculatorBrain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        buttons = new ArrayList<Button>();
        for (int id : BUTTON_ID) {
            findViewById(id).setOnClickListener(this);
        }
        text = (TextView) findViewById(R.id.textView);
        df.setMaximumIntegerDigits(7);
        df.setMinimumIntegerDigits(1);
        df.setMinimumFractionDigits(0);
        mCalculatorBrain = new CalculatorBrain();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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

    @Override
    public void onClick(View v) {
        String buttonPressed = ((Button) v).getText().toString();

        if(DIGITS.contains(buttonPressed)){
            if(userIsInTheMiddleOfTypingANumber){
                if(buttonPressed.contains(".") && text.getText().toString().contains(".")){

                }else{
                    text.append(buttonPressed);
                }

            }else{
                if(buttonPressed.contains(".")){
                    text.setText(0 + buttonPressed);
                }else{
                    text.setText(buttonPressed);
                }
                userIsInTheMiddleOfTypingANumber = true;
            }
        }else{
            if(userIsInTheMiddleOfTypingANumber){
                mCalculatorBrain.setOperand(Double.parseDouble(text.getText().toString()));
                userIsInTheMiddleOfTypingANumber = false;
            }
            mCalculatorBrain.performOperation(buttonPressed);
            text.setText(df.format(mCalculatorBrain.getResult()));
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putDouble("OPERAND",mCalculatorBrain.getResult());
        outState.putDouble("MEMORY",mCalculatorBrain.getMemory());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        mCalculatorBrain.setOperand(savedInstanceState.getDouble("OPERAND"));
        mCalculatorBrain.setMemory(savedInstanceState.getDouble("MEMORY"));
        text.setText(df.format(mCalculatorBrain.getResult()));

    }

}