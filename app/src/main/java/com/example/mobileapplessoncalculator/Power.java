package com.example.mobileapplessoncalculator;

public class Power implements IOperation {
    @Override
    public float Do(float num1, float num2) {
        return (float) Math.pow(num1, num2);
    }
}
