package com.example.aasaqt.calculator;

import android.os.Bundle;

/**
 * Created by aasaqt on 3/1/15.
 */
public class CalculatorBrain {
    private double mOperand;
    private double mWaitingOperand;
    private double mCalculatorMemory;
    private String mWaitingOperator;

    private static final String ADD = "+";
    private static final String SUBTRACT = "-";
    private static final String DIVIDE = "/";
    private static final String MULTIPLY = "*";
    private static final String CLEAR = "C";
    private static final String CLEARMEMORY = "MC";
    private static final String ADDTOMEMORY = "M+";
    private static final String SUBTRACTFROMMEMORY = "M-";
    private static final String RECALLMEMORY = "MR";
    private static final String TOGGLESIGN = "+/-";
    private static final String SIN = "sin";
    private static final String COS = "cos";
    private static final String TAN = "tan";
    private static final String SQUARED = "X^2";
    private static final String SQUAREROOT = "sqrt";
    private static final String INVERSE = "1/x";



    public CalculatorBrain(){
        mOperand = 0;
        mWaitingOperand = 0;
        mCalculatorMemory = 0;
        mWaitingOperator = "";
    }


    public void setOperand(double v) {
        mOperand = v;
    }

    public double getResult() {
        return mOperand;
    }

    public void setMemory(double memory) {
        mCalculatorMemory = memory;
    }

    public double getMemory() {
        return mCalculatorMemory;
    }

    public String toString(){
        return Double.toString(mOperand);
    }

    public double performOperation(String buttonPressed) {
        if(buttonPressed.contains(CLEAR)){
            mOperand = 0;
            mWaitingOperand = 0;
            mWaitingOperator = "";
        }else if(buttonPressed.contains(CLEARMEMORY)){
            mCalculatorMemory = 0;
        }else if(buttonPressed.contains(ADDTOMEMORY)){
            mCalculatorMemory = mCalculatorMemory+mOperand;
        }else if(buttonPressed.contains(SUBTRACTFROMMEMORY)){
            mCalculatorMemory = mCalculatorMemory-mOperand;
        }else if(buttonPressed.contains(RECALLMEMORY)){
            mOperand = mCalculatorMemory;
        }else if(buttonPressed.contains(TOGGLESIGN)){
            mOperand=-mOperand;
        }else if(buttonPressed.contains(SIN)){
            mOperand=Math.sin(Math.toRadians(mOperand));
        }else if(buttonPressed.contains(COS)){
            mOperand=Math.cos(Math.toRadians(mOperand));
        }else if(buttonPressed.contains(TAN)){
            mOperand=Math.tan(Math.toRadians(mOperand));
        }else if(buttonPressed.contains(SQUARED)){
            mOperand=mOperand*mOperand;
        }else if(buttonPressed.contains(SQUAREROOT)){
            mOperand=Math.sqrt(mOperand);
        }else if(buttonPressed.contains(INVERSE)){
            if(mOperand != 0){
                mOperand = 1/mOperand;
            }
        }else{
            performWaitingOperation();
            mWaitingOperator = buttonPressed;
            mWaitingOperand = mOperand;
        }
        return mOperand;
    }

    private void performWaitingOperation() {
        if(mWaitingOperator.contains(ADD)){
            mOperand = mWaitingOperand+mOperand;
        }else if(mWaitingOperator.contains(SUBTRACT)){
            mOperand=mWaitingOperand-mOperand;
        }else if(mWaitingOperator.contains(MULTIPLY)){
            mOperand=mWaitingOperand*mOperand;
        }else if(mWaitingOperator.contains(DIVIDE)){
            if(mOperand != 0){
                mOperand=mWaitingOperand/mOperand;
            }
        }

    }

}
