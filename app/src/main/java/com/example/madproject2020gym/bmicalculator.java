package com.example.madproject2020gym;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class bmicalculator extends AppCompatActivity {

    private EditText height;
    private EditText weight;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);

        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        result = (TextView) findViewById(R.id.result);
    }
    public void calculateBMI(View v){
        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();

        if (heightStr != null && !"".equals(heightStr)
                && weightStr != null && !"".equals(weightStr)){

            float heightValue = Float.parseFloat(heightStr) / 100;
            float weightValue = Float.parseFloat(weightStr);

            float bmi = weightValue / (heightValue * heightValue);

            displayBIM(bmi);
        }
    }

    private void displayBIM(float bmi) {

        String bmiLable = "";
        if(Float.compare(bmi , 15f) <= 0){
            bmiLable = getString(R.string.Underweight);
        }else if(Float.compare(bmi , 15f) > 0 && Float.compare(bmi,16f) <= 0) {
            bmiLable = getString(R.string.Underweight);
        }else if(Float.compare(bmi , 16f) > 0 && Float.compare(bmi,18.5f) <= 0) {
            bmiLable = getString(R.string.Underweight);
        }else if(Float.compare(bmi , 18.5f) > 0 && Float.compare(bmi,25f) <= 0) {
            bmiLable = getString(R.string.Normal);
        }else if(Float.compare(bmi , 25f) > 0 && Float.compare(bmi,30f) <= 0) {
            bmiLable = getString(R.string.overweight);
        }else if(Float.compare(bmi , 30f) > 0 && Float.compare(bmi,35f) <= 0) {
            bmiLable = getString(R.string.overweight);
        }else if(Float.compare(bmi , 35f) > 0 && Float.compare(bmi,40f) <= 0) {
            bmiLable = getString(R.string.overweight);
        }else{
            bmiLable = getString(R.string.overweight);

        }
        bmiLable = bmi + "\n\n" + bmiLable;
        result.setText(bmiLable);

    }
}