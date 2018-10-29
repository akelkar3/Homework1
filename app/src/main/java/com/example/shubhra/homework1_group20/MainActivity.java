package com.example.shubhra.homework1_group20;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rg;
    TextView tview1;
    TextView customTipRepresentation;
    TextView tview2;
    EditText bill;
    SeekBar sb;
    RadioButton rb;
    double tipAmount = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sb = findViewById(R.id.d);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME |ActionBar.DISPLAY_SHOW_TITLE  | ActionBar.DISPLAY_USE_LOGO);
        actionBar.setIcon(R.mipmap.ic_launcher);

        tview2 = (TextView) findViewById(R.id.textView8);
        tview1 = (TextView) findViewById(R.id.textView6);
        customTipRepresentation = (TextView) findViewById(R.id.tvCustomTip);
        bill = findViewById(R.id.editText);

        //As soon as app is created, seekbar should be first disabled
        sb.setEnabled(false);

        //Handling the behaviour of Radio buttons
        rg = findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                rb = findViewById(checkedId);
                int id = rg.getCheckedRadioButtonId();
                if (id == R.id.rb10) {
                    String billStr = bill.getText().toString();
                    tipAmount = 10;
                    if(billStr.length()>0){
                        Double billDbl = Double.valueOf(billStr);
                        Double billPercent = billDbl*0.10;
                        tview1.setText(String.valueOf(billPercent));
                        tview2.setText(String.valueOf(billDbl + billPercent));
                    }
                    sb.setEnabled(false);
                } else if (id == R.id.rb15) {
                    String billStr = bill.getText().toString();
                    tipAmount = 15;
                    if(billStr.length()>0){
                        Double billDbl = Double.valueOf(billStr);
                        Double billPercent = billDbl*0.15;
                        tview1.setText(String.valueOf(billPercent));
                        tview2.setText(String.valueOf(billDbl + billPercent));
                    }
                    sb.setEnabled(false);
                } else if (id == R.id.rb18) {
                    String billStr = bill.getText().toString();
                    tipAmount = 18;
                    if(billStr.length()>0){
                        Double billDbl = Double.valueOf(billStr);
                        Double billPercent = billDbl*0.18;
                        tview1.setText(String.valueOf(billPercent));
                        tview2.setText(String.valueOf(billDbl + billPercent));
                    }
                    sb.setEnabled(false);
                } else if (id == R.id.rb40) {
                    sb.setEnabled(true);
                    String[] customSplitRepAfterSplit = String.valueOf(customTipRepresentation.getText()).split("%");
                    //tview1.setText(customSplitRepAfterSplit[0]);//As soon as custom is selected, its value should be populated in tip textview

                    String billStr = bill.getText().toString();
                    if(billStr.length()>0){
                        Double billDbl = Double.valueOf(billStr);
                        Double billPercent = billDbl*Double.valueOf(customSplitRepAfterSplit[0])*0.01;
                        tview1.setText(String.valueOf(billPercent));
                        tview2.setText(String.valueOf(billDbl + billPercent));
                    }

                    sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int customTip, boolean b) {
                            customTipRepresentation.setText(String.valueOf(customTip) + "%");
                            int tip = Integer.valueOf(String.valueOf(customTip));
                            tipAmount = customTip;

                            String billStr = bill.getText().toString();
                            if(billStr.length()>0){
                                Double billDbl = Double.valueOf(billStr);
                                Double billPercent = billDbl*tip*0.01;
                                tview1.setText(String.valueOf(billPercent));
                                tview2.setText(String.valueOf(billDbl + billPercent));
                            }
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });
                }
            }

        });


        //Exit button implementation
        Button btn1 = (Button) findViewById(R.id.button);
        //Log.d("demo", "The button text is : "+btn1.getText().toString());
        // 1st approach to handle event is to make the implementation through the Anonymous inner class
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("demo", "OK button clicked");
                finish();
            }
        });


        //Bill Total Edit Text Implementation
        bill.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().length()==0){
                    bill.setError("Enter Bill Amount");
                    tview1.setText("0.0");
                    tview2.setText("0.0");
                }else{
                    Double billDbl = Double.valueOf(charSequence.toString());
                    tview1.setText(String.valueOf(billDbl*tipAmount*0.01));
                    double tip = Double.valueOf(tview1.getText().toString());
                    double totalBill = billDbl + (billDbl*tip*0.01);
                    tview2.setText(String.valueOf(totalBill));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}
