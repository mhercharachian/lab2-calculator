package mhercharachian.com.myloancalculator;

import android.icu.text.DecimalFormat;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    EditText etNum1, etNum2, etNum3,etNum4, etNum5, etNum6;
    TextView result;
    double num1, num3;
    int num2;
    boolean error = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNum1 = (EditText) findViewById(R.id.editText1);
        etNum2 = (EditText) findViewById(R.id.editText2);
        etNum3 = (EditText) findViewById(R.id.editText3);
        etNum4 = (EditText) findViewById(R.id.editText4);
        etNum5 = (EditText) findViewById(R.id.editText5);
        etNum6 = (EditText) findViewById(R.id.editText6);
        result = (TextView) findViewById(R.id.textView11);
    }

    public boolean tryParseDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void calculate(View v) {
        if(etNum1.getText().toString().equals("") || tryParseDouble(etNum1.getText().toString()) == false)
        {
            result.setText("ERROR");
            error = true;
            clear(v);
        }
        else if(etNum2.getText().toString().equals("") || tryParseInt(etNum2.getText().toString()) == false)
        {
            result.setText("ERROR");
            error = true;
            clear(v);
        }
        else if(etNum3.getText().toString().equals("") || tryParseDouble(etNum3.getText().toString()) == false)
        {
            result.setText("ERROR");
            error = true;
            clear(v);
        }
        else{
            num1 = Double.parseDouble(etNum1.getText().toString());
            num2 = Integer.parseInt(etNum2.getText().toString());
            num3 = Double.parseDouble(etNum3.getText().toString());

            LoanCalculator loanCalc = new LoanCalculator(num1, num2, num3);

            String monthlyPayment = String.valueOf(loanCalc.getMonthlyPayment());
            etNum4.setText(monthlyPayment);

            String totalPayment = String.valueOf(loanCalc.getTotalCostOfLoan());
            etNum5.setText(totalPayment);

            String totalInterest = String.format("%.2f", loanCalc.getTotalInterest());
            etNum6.setText(totalInterest);
        }

    }

    public void clear(View w){

        if(error == false) {
            result.setText("Results");
        }
        etNum1.setText("");
        etNum2.setText("");
        etNum3.setText("");
        etNum4.setText("");
        etNum5.setText("");
        etNum6.setText("");
        num1 = 0;
        num2 = 0;
        num3 = 0;

    }
}
