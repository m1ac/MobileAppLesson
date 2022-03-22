package com.example.mobileapplessoncalculator;

public class Multiplication extends Operation {
    @Override
    public float Do(float num1, float num2) {
        return num1 * num2;
    }

    @Override
    public String GetSign() {
        return "*";
    }
}
