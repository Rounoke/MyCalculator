package com.example.mycalculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Locale;



public class MainActivity extends AppCompatActivity {
    double result, newResult;
    BigDecimal stResult;
    String[] values;
    boolean Addition, Subtraction, Multiplication, Division;
    EditText display, resultScreen;
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0, buttonAdd, buttonSub, buttonMul, buttonDiv, buttonRem, buttonDot, buttonEquals, buttonBack, buttonAC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.screen);
        display.addTextChangedListener(onTextChangedListener());

        resultScreen = findViewById(R.id.resultScreen);

        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonRem = findViewById(R.id.buttonRem);
        button0 = findViewById(R.id.button0);
        buttonDot = findViewById(R.id.buttonDot);
        buttonEquals = findViewById(R.id.buttonEquals);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSub = findViewById(R.id.buttonSub);
        buttonMul = findViewById(R.id.buttonMul);
        buttonDiv = findViewById(R.id.buttonDiv);
        buttonBack = findViewById(R.id.buttonBack);
        buttonAC = findViewById(R.id.buttonAC);


        //Every button containing its own function
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText(display.getText() + "0");

                calculation();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText(display.getText() + "1");

                calculation();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText(display.getText() + "2");

                calculation();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText(display.getText() + "3");

                calculation();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText(display.getText() + "4");

                calculation();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText(display.getText() + "5");

                calculation();
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText(display.getText() + "6");

                calculation();
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText(display.getText() + "7");

                calculation();
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText(display.getText() + "8");

                calculation();
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText(display.getText() + "9");

                calculation();
            }
        });


        buttonAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText(null);                           //deleting text in both the screens and making all booleans false
                resultScreen.setText(null);

                newResult = 0;
                result = 0;

                Addition = false;
                Subtraction = false;
                Multiplication = false;
                Division = false;

            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //----BUG----
                display.getText().delete(display.getText().length() - 1, display.getText().length());

                if(display.getText().length() == 0) {
                    newResult = 0;
                    result = 0;

                    resultScreen.setText(null);

                    Addition = false;
                    Subtraction = false;
                    Multiplication = false;
                    Division = false;
                }

            }                                                                                       //deleting the last possible digit
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (display.getText().length() != 0) {

                    if (display.getText().toString().endsWith("+") || display.getText().toString().endsWith("-") || display.getText().toString().endsWith("x") || display.getText().toString().endsWith("\u00F7") || display.getText().toString().endsWith("%")) {
                        // checking if the last value is a number

                        //if the last value is not a number then deleting the previous sign and printing the new sign
                        display.getText().delete(display.getText().length() - 1, display.getText().length());
                    }

                    if (resultScreen.getText().length() != 0) {   // if the result screen containing any result, the newResult becoming that number for future calculation
                        newResult = result;
                    } else {                                                           //else the first number will be the newResult
                        newResult = Double.parseDouble((display.getText().toString()).replace(",", ""));
                    }
                } else {
                    resultScreen.setText("Error");
                }


                Addition = true;
                Subtraction = false;
                Multiplication = false;
                Division = false;

                display.setText(display.getText() + "+");

            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (display.getText().length() != 0) {

                    if (display.getText().toString().endsWith("+") || display.getText().toString().endsWith("-") || display.getText().toString().endsWith("x") || display.getText().toString().endsWith("\u00F7") || display.getText().toString().endsWith("%")) {

                        display.getText().delete(display.getText().length() - 1, display.getText().length());
                    }

                    if (resultScreen.getText().length() != 0) {
                        newResult = result;
                    } else {
                        newResult = Double.parseDouble((display.getText().toString()).replace(",", ""));
                    }
                } else {
                    resultScreen.setText("Error");
                }

                Subtraction = true;
                Addition = false;
                Multiplication = false;
                Division = false;

                display.setText(display.getText() + "-");

            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (display.getText().length() != 0) {

                    if (display.getText().toString().endsWith("+") || display.getText().toString().endsWith("-") || display.getText().toString().endsWith("x") || display.getText().toString().endsWith("\u00F7") || display.getText().toString().endsWith("%")) {

                        display.getText().delete(display.getText().length() - 1, display.getText().length());
                    }

                    if (resultScreen.getText().length() != 0) {
                        newResult = result;
                    } else {
                        newResult = Double.parseDouble((display.getText().toString()).replace(",", ""));
                    }
                    Multiplication = true;
                    Addition = false;
                    Subtraction = false;
                    Division = false;

                    display.setText(display.getText() + "x");
                } else {
                    resultScreen.setText("Error");
                }
            }
        });

        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (display.getText().length() != 0) {

                    if (display.getText().toString().endsWith("+") || display.getText().toString().endsWith("-") || display.getText().toString().endsWith("x") || display.getText().toString().endsWith("\u00F7") || display.getText().toString().endsWith("%")) {

                        display.getText().delete(display.getText().length() - 1, display.getText().length());
                    }

                    if (resultScreen.getText().length() != 0) {
                        newResult = result;
                    } else {
                        newResult = Double.parseDouble((display.getText().toString()).replace(",", ""));
                    }
                    Division = true;
                    Addition = false;
                    Subtraction = false;
                    Multiplication = false;

                    display.setText(display.getText() + "\u00F7");
                } else {
                    resultScreen.setText("Error");
                }
            }
        });

        buttonRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (display.getText().length() != 0 && resultScreen.getText().length() != 0) {

                    result = result / 100;
                    stResult = BigDecimal.valueOf(result).stripTrailingZeros();

                    resultScreen.setText(stResult.toPlainString());

                } else if (display.getText().length() != 0) {

                    if (display.getText().toString().endsWith("+") || display.getText().toString().endsWith("-") || display.getText().toString().endsWith("x") || display.getText().toString().endsWith("\u00F7") || display.getText().toString().endsWith("%")) {

                        display.getText().delete(display.getText().length() - 1, display.getText().length());
                    }

                    newResult = Double.parseDouble((display.getText().toString()).replace(",", ""));
                    result = newResult / 100;
                    stResult = BigDecimal.valueOf(result).stripTrailingZeros();

                    resultScreen.setText(stResult.toPlainString());

                } else {
                    resultScreen.setText("Error");
                }

                Addition = false;
                Subtraction = false;
                Multiplication = false;
                Division = false;

                display.setText(display.getText() + "%");
            }
        });

        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText(display.getText() + ".");
            }
        });


        buttonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText(resultScreen.getText());    //setting the last result calculated or in the resultScreen to the display screen
                resultScreen.setText(null);

                Addition = false;
                Subtraction = false;
                Multiplication = false;
                Division = false;

            }
        });
    }

    public void calculation() {
        if (Addition) {
            values = display.getText().toString().split("\\+");     //spilting numbers with '+' in between them 

            result = newResult + Double.parseDouble((values[values.length - 1]).replace(",", ""));
            stResult = BigDecimal.valueOf(result).stripTrailingZeros();

            resultScreen.setText(stResult.toPlainString());

        } else if (Subtraction) {
            values = display.getText().toString().split("-");

            result = newResult - Double.parseDouble((values[values.length - 1]).replace(",", ""));
            stResult = BigDecimal.valueOf(result).stripTrailingZeros();

            resultScreen.setText(stResult.toPlainString());

        } else if (Multiplication) {
            values = display.getText().toString().split("x");

            result = newResult * Double.parseDouble((values[values.length - 1]).replace(",", ""));
            stResult = BigDecimal.valueOf(result).stripTrailingZeros();

            resultScreen.setText(stResult.toPlainString());

        } else if (Division) {
            values = display.getText().toString().split("\\u00F7");

            result = newResult / Double.parseDouble((values[values.length - 1]).replace(",", ""));
            stResult = BigDecimal.valueOf(result).stripTrailingZeros();

            resultScreen.setText(stResult.toPlainString());
        }
    }


    private TextWatcher onTextChangedListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (display.getText().length() <= 12) {

                    display.setTextSize(TypedValue.COMPLEX_UNIT_SP, 45);

                } else {
                    display.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
                }


                display.removeTextChangedListener(this);
                try {

                    Long mLong;
                    String stringForTransform = s.toString();

                    if (Addition) {
                        values = display.getText().toString().split("\\+");
                        stringForTransform = values[values.length - 1];
                    } else if (Subtraction) {
                        values = display.getText().toString().split("-");
                        stringForTransform = values[values.length - 1];
                    } else if (Multiplication) {
                        values = display.getText().toString().split("x");
                        stringForTransform = values[values.length - 1];
                    } else if (Division) {
                        values = display.getText().toString().split("\\u00F7");
                        stringForTransform = values[values.length - 1];
                    }

                    if (stringForTransform.contains(",")) {
                        stringForTransform = stringForTransform.replaceAll(",", "");
                    }

                    mLong = Long.parseLong(stringForTransform);

                    NumberFormat myFormat = NumberFormat.getInstance();
                    myFormat.setGroupingUsed(true);
                    String formattedString = myFormat.format(mLong);

                    if (Addition) {
                        display.setText(display.getText().toString().replace(values[values.length - 1], formattedString));
                    } else if (Subtraction) {
                        display.setText(display.getText().toString().replace(values[values.length - 1], formattedString));
                    } else if (Multiplication) {
                        display.setText(display.getText().toString().replace(values[values.length - 1], formattedString));
                    } else if (Division) {
                        display.setText(display.getText().toString().replace(values[values.length - 1], formattedString));
                    } else {
                        display.setText(formattedString);
                    }

                    display.setSelection(display.getText().length());
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }
                display.addTextChangedListener(this);


                resultScreen.removeTextChangedListener(this);
                try{

                    String resultForTransform = resultScreen.getText().toString();
                    Double mDouble;

                    if (resultForTransform.contains(",")) {
                        resultForTransform =  resultForTransform.replaceAll(",", "");
                    }

                    mDouble = Double.parseDouble(resultForTransform);

                    NumberFormat myFormat = NumberFormat.getInstance();
                    myFormat.setGroupingUsed(true);
                    String formattedResult = myFormat.format(mDouble);

                    resultScreen.setText(formattedResult);
                    resultScreen.setSelection(resultScreen.getText().length());
                } catch (NumberFormatException rnfe) {
                    rnfe.printStackTrace();
                }
                resultScreen.addTextChangedListener(this);

            }
        };
    }

}