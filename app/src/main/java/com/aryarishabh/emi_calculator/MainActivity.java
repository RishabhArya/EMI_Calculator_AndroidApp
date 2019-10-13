package com.aryarishabh.emi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;
import java.lang.Math;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create references for both buttons---------
        Button cal =(Button)findViewById(R.id.buttonCal);
        Button reset =(Button)findViewById(R.id.buttonReset);

        //Setting up the RESET Button---------
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText loan =(EditText)findViewById(R.id.editTextAmount);
                EditText rate =(EditText)findViewById(R.id.editTextRate);
                EditText time =(EditText)findViewById(R.id.editTextTime);
                loan.setText("");
                rate.setText("");
                time.setText("");
            }
        });

        //Setting up the cal Button---------
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });
    }

    public void calculate()
    {
        EditText loan =(EditText)findViewById(R.id.editTextAmount);
        EditText rate =(EditText)findViewById(R.id.editTextRate);
        EditText time =(EditText)findViewById(R.id.editTextTime);
        TextView ViewLoan = (TextView)findViewById(R.id.LoanEmi);
        TextView ViewTotalAmount = (TextView)findViewById(R.id.total);
        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButton);
        String Principal = loan.getText().toString();
        String RateOfInterest = rate.getText().toString();
        String TimePeriod = time.getText().toString();

        //Converting String to float
        float L = Float.parseFloat(Principal);
        float I = Float.parseFloat(RateOfInterest);
        float N = Float.parseFloat(TimePeriod);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    N = N * 12;
                } else {
                    N = N;
                }
            }
        });

        //Calculating EMI
        float lt = L * I;
        float temp = 1 + 1;
        float numerator = (float) Math.pow(temp, N);
        float denominator = numerator - 1;
        float totalEmi = lt * denominator;
        float totalAmount = L + totalEmi;
        //---------------EMI Calculation Done

        //setting TextView values
        String totalEmiAsString = Float.toString(totalEmi);
        String totalAmountAsString = Float.toString(totalAmount);

        ViewLoan.setText(totalEmiAsString);
        ViewTotalAmount.setText(totalAmountAsString);

    }
}
