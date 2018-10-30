package com.example.hlc.randomgenerator;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText IntegerMinValue, IntegerMaxValue, DecimalMinValue, DecimalMaxValue, StringMinLength, StringMaxLength;
    private TextView IntegerResult, DecimalResult, StringResult;
    private Button GenIntegerButton, CopyIntegerButton, GenDecimalButton, CopyDecimalButton, GenStringButton, CopyStringButton;
    private CheckBox LowercaseCheck, UppercaseCheck, NumbersCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntegerMinValue = findViewById(R.id.IntegerMinValue);
        IntegerMaxValue = findViewById(R.id.IntegerMaxValue);
        IntegerResult = findViewById(R.id.IntegerResult);
        GenIntegerButton = findViewById(R.id.GenIntegerButton);
        CopyIntegerButton = findViewById(R.id.CopyIntegerButton);

        DecimalMinValue = findViewById(R.id.DecimalMinValue);
        DecimalMaxValue = findViewById(R.id.DecimalMaxValue);
        DecimalResult = findViewById(R.id.DecimalResult);
        GenDecimalButton = findViewById(R.id.GenDecimalButton);
        CopyDecimalButton = findViewById(R.id.CopyDecimalButton);

        StringMinLength = findViewById(R.id.StringMinLength);
        StringMaxLength = findViewById(R.id.StringMaxLength);
        StringResult = findViewById(R.id.StringResult);
        GenStringButton = findViewById(R.id.GenStringButton);
        CopyStringButton = findViewById(R.id.CopyStringButton);
        LowercaseCheck = findViewById(R.id.LowercaseCheck);
        UppercaseCheck = findViewById(R.id.UppercaseCheck);
        NumbersCheck = findViewById(R.id.NumbersCheck);

        GenIntegerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid(IntegerMinValue.getText().toString(), IntegerMaxValue.getText().toString())) {
                    IntegerResult.setText(String.valueOf(generateRandomNumber(Integer.parseInt(IntegerMinValue.getText().toString()), Integer.parseInt(IntegerMaxValue.getText().toString()))));
                } else {
                    Toast.makeText(MainActivity.this, "Must enter valid min and max range", Toast.LENGTH_LONG).show();
                }
            }
        });

        CopyIntegerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!IntegerResult.getText().toString().isEmpty()){
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Copied to clipboard", IntegerResult.getText().toString());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(MainActivity.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
                }
            }
        });

        GenDecimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid(DecimalMinValue.getText().toString(), DecimalMaxValue.getText().toString())) {
                    DecimalResult.setText(convertDecimal(DecimalMinValue.getText().toString(), DecimalMaxValue.getText().toString()));
                } else {
                    Toast.makeText(MainActivity.this, "Must enter valid min and max range", Toast.LENGTH_LONG).show();
                }
            }
        });

        CopyDecimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!DecimalResult.getText().toString().isEmpty()){
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Copied to clipboard", DecimalResult.getText().toString());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(MainActivity.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
                }
            }
        });

        GenStringButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid(StringMinLength.getText().toString(), StringMaxLength.getText().toString())) {
                    String[] options = {"abcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "0123456789"};
                    String result = "";

                    for (int i = 0; i < generateRandomNumber(Integer.parseInt(StringMinLength.getText().toString()), Integer.parseInt(StringMaxLength.getText().toString())); i++) {
                        if (LowercaseCheck.isChecked() && UppercaseCheck.isChecked() && NumbersCheck.isChecked()) {
                            int randInt = generateRandomNumber(0, 2);
                            result += options[randInt].charAt(generateRandomNumber(0, options[randInt].length() - 1));
                            System.out.print(randInt);
                        } else if (LowercaseCheck.isChecked() && UppercaseCheck.isChecked()) {
                            int randInt = generateRandomNumber(0, 1);
                            result += options[randInt].charAt(generateRandomNumber(0, options[randInt].length() - 1));
                            System.out.print(randInt);
                        } else if (LowercaseCheck.isChecked() && NumbersCheck.isChecked()) {
                            if (generateRandomNumber(0, 1) == 0) {
                                result += options[0].charAt(generateRandomNumber(0, options[0].length() - 1));
                            } else {
                                result += options[2].charAt(generateRandomNumber(0, options[2].length() - 1));
                            }
                        } else if (UppercaseCheck.isChecked() && NumbersCheck.isChecked()) {
                            int randInt = generateRandomNumber(1, 2);
                            result += options[randInt].charAt(generateRandomNumber(0, options[randInt].length() - 1));
                            System.out.print(randInt);
                        } else if (UppercaseCheck.isChecked()) {
                            result += options[1].charAt(generateRandomNumber(0, options[1].length() - 1));
                        } else if (NumbersCheck.isChecked()) {
                            result += options[2].charAt(generateRandomNumber(0, options[2].length() - 1));
                        } else {
                            result += options[0].charAt(generateRandomNumber(0, options[0].length() - 1));
                        }
                    }

                    StringResult.setText(result);
                } else {
                    Toast.makeText(MainActivity.this, "Must enter valid min and max length", Toast.LENGTH_LONG).show();
                }
            }
        });

        CopyStringButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!StringResult.getText().toString().isEmpty()){
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Copied to clipboard", StringResult.getText().toString());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(MainActivity.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
                }
            }
        });

        LowercaseCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!LowercaseCheck.isChecked() && !UppercaseCheck.isChecked() && !NumbersCheck.isChecked()) {
                    LowercaseCheck.setChecked(true); // Make lowercase default criteria for string
                }
            }
        });
    }

    public int generateRandomNumber(int min, int max) {
        return (int) (Math.random() * (max-min+1) + min);
    }

    public double generateRandomNumber(double min, double max) {
        return (Math.random() * (max-min) + min);
    }

    public String convertDecimal(String min, String max) {
        double minNumber = Double.parseDouble(min);
        double maxNumber = Double.parseDouble(max);

        return Double.toString(generateRandomNumber(minNumber, maxNumber));
    }

    public boolean isValid(String min, String max) {
        if (!min.isEmpty() && !max.isEmpty()) {
            if (isNumeric(min) && isNumeric(max)) {
                if (Double.parseDouble(min) > Integer.MIN_VALUE && Double.parseDouble(max) < Integer.MAX_VALUE) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch(NumberFormatException nfe) {
            return false;
        }

        return true;
    }
}