package com.example.mobileapplessoncalculator;

import android.graphics.Color;

public class Addiction extends Operation {
    @Override
    public float Do(float num1, float num2) {
        return num1 + num2;
    }

    @Override
    public String GetSign() {
        return "+";
    }

    @Override
    public int GetSignColor() {
        return Color.GREEN;
    }
}
