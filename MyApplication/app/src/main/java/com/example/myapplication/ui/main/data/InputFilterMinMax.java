package com.example.myapplication.ui.main.data;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

public class InputFilterMinMax implements InputFilter {
    private int minValue;
    private int maxValue;

    public InputFilterMinMax(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            // Combine the current text in the EditText with the new input
            String combinedInput = dest.subSequence(0, dstart) + source.toString() + dest.subSequence(dend, dest.length());

            int input = Integer.parseInt(combinedInput);

            if (isInRange(input)) {
                return null; // Input is within the allowed range, so accept it
            }
        } catch (NumberFormatException e) {
            // Do nothing if the input is not a valid number
        }

        // Return an empty string to indicate that the input is not allowed
        return "";
    }

    private boolean isInRange(int input) {
        return input >= minValue && input <= maxValue;
    }
}