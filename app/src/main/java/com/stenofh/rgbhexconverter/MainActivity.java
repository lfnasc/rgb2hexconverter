package com.stenofh.rgbhexconverter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Selection;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.editTextRed)
    EditText editTextRed;
    @BindView(R.id.editTextGreen)
    EditText editTextGreen;
    @BindView(R.id.editTextBlue)
    EditText editTextBlue;
    @BindView(R.id.editTextHex)
    EditText editTextHex;

    @BindView(R.id.seekBarRed)
    SeekBar seekBarRed;
    @BindView(R.id.seekBarGreen)
    SeekBar seekBarGreen;
    @BindView(R.id.seekBarBlue)
    SeekBar seekBarBlue;

    @BindView(R.id.textViewRedValue)
    TextView textViewRedValue;
    @BindView(R.id.textViewGreenValue)
    TextView textViewGreenValue;
    @BindView(R.id.textViewBlueValue)
    TextView textViewBlueValue;

    @BindView(R.id.viewColor)
    View viewColor;

    private boolean isInputText = false;
    private int inputPositionCursor;
    private Editable inputEditable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // setting ButterKnife
        ButterKnife.bind(this);

        // setting SeekBar change listener
        seekBarRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressRed = 0;
            String progressRedStr = "";

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressRed = i;
                progressRedStr = String.valueOf(progressRed);
                textViewRedValue.setText(progressRedStr);
                if (!isInputText) {
                    editTextRed.setText(progressRedStr);
                }
                onConvertRgbToHex();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "RED: "+progressRedStr, Toast.LENGTH_SHORT).show();
            }
        });

        seekBarGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressGreen = 0;
            String progressGreenStr = "";

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressGreen = i;
                progressGreenStr = String.valueOf(progressGreen);
                textViewGreenValue.setText(progressGreenStr);
                if (!isInputText) {
                    editTextGreen.setText(progressGreenStr);
                }
                onConvertRgbToHex();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "GREEN: "+progressGreenStr, Toast.LENGTH_SHORT).show();
            }
        });

        seekBarBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressBlue = 0;
            String progressBlueStr = "";

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressBlue = i;
                progressBlueStr = String.valueOf(progressBlue);
                textViewBlueValue.setText(progressBlueStr);
                if (!isInputText) {
                    editTextBlue.setText(progressBlueStr);
                }
                onConvertRgbToHex();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "BLUE: "+progressBlueStr, Toast.LENGTH_SHORT).show();
            }
        });

    }

    // setting edit text change listeners

    @OnTextChanged(R.id.editTextRed)
    public void onTextRedChanged(CharSequence textRed, int start, int count, int after) {

        if (textRed.length() > 0) {
            isInputText = true;
            seekBarRed.setProgress(Integer.parseInt(textRed.toString()));
            inputPositionCursor = editTextRed.length();
            inputEditable = editTextRed.getText();
            Selection.setSelection(inputEditable, inputPositionCursor);
            isInputText = false;
        } else {
            Toast.makeText(getApplicationContext(), "Set RED Value!", Toast.LENGTH_SHORT).show();
        }

    }

    @OnTextChanged(R.id.editTextGreen)
    public void onTextGreenChanged(CharSequence textGreen, int start, int count, int after) {

        if (textGreen.length() > 0) {
            isInputText = true;
            seekBarGreen.setProgress(Integer.parseInt(textGreen.toString()));
            inputPositionCursor = editTextGreen.length();
            inputEditable = editTextGreen.getText();
            Selection.setSelection(inputEditable, inputPositionCursor);
            isInputText = false;
        } else {
            Toast.makeText(getApplicationContext(), "Set GREEN Value!", Toast.LENGTH_SHORT).show();
        }

    }

    @OnTextChanged(R.id.editTextBlue)
    public void onTextBlueChanged(CharSequence textBlue, int start, int count, int after) {

        if (textBlue.length() > 0) {
            isInputText = true;
            seekBarBlue.setProgress(Integer.parseInt(textBlue.toString()));
            inputPositionCursor = editTextBlue.length();
            inputEditable = editTextBlue.getText();
            Selection.setSelection(inputEditable, inputPositionCursor);
            isInputText = false;
        } else {
            Toast.makeText(getApplicationContext(), "Set BLUE Value!", Toast.LENGTH_SHORT).show();
        }

    }

    @OnTextChanged(R.id.editTextHex)
    public void onTextHexChanged(CharSequence textHex, int start, int count, int after) {

        if (textHex.length() > 0) {
            onConvertHexToRgb();
        } else {
            Toast.makeText(getApplicationContext(), "Set HEX Value!", Toast.LENGTH_SHORT).show();
        }

    }

    // business methods

    private boolean isValidRGB(int redValue, int greenValue, int blueValue) {
        if (redValue > 255 || greenValue > 255 || blueValue > 255) {
            return false;
        }
        return true;
    }

    private boolean isValidHex(String hexValue) {
        if (hexValue.length() < 6) {
            return false;
        }
        return true;
    }

    private int rgbToIntColor(int redValue, int greenValue, int blueValue) {
        return android.graphics.Color.rgb(redValue, greenValue, blueValue);
    }

//    private String intColorToHexString(int color) {
//        return Integer.toHexString(color);
//    }

    private String intColorToString(int color) {
        return String.format("%06X", 0xFFFFFF & color);
    }

    private int stringToIntColor(String color) {
        return Color.parseColor("#ff"+color);
    }

    private int[] inColorToRGB(int color) {
        int[] rgb = new int[3];
        rgb[0] = Color.red(color);
        rgb[1] = Color.green(color);
        rgb[2] = Color.blue(color);
        return rgb;
    }

    // converter methods

    private void onConvertRgbToHex() {
        String red = editTextRed.getText().toString();
        String green = editTextGreen.getText().toString();
        String blue = editTextBlue.getText().toString();

        if (!red.isEmpty() && !green.isEmpty() && !blue.isEmpty()) {
            int redValue = Integer.parseInt(red);
            int greenValue = Integer.parseInt(green);
            int blueValue = Integer.parseInt(blue);
            int colorInt;
            String colorStr;

            if (isValidRGB(redValue, greenValue, blueValue)) {
                colorInt = rgbToIntColor(redValue, greenValue, blueValue);
                viewColor.setBackgroundColor(colorInt);
                colorStr = intColorToString(colorInt);
                editTextHex.setText(colorStr);
            } else {
                Toast.makeText(this, "RGB Values > 255", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Invalid RGB Values", Toast.LENGTH_SHORT).show();
        }


    }

    private void onConvertHexToRgb() {
        int redValue;
        int greenValue;
        int blueValue;
        String hexValue = editTextHex.getText().toString();
        int colorInt;
        int[] rgb;

        if (isValidHex(hexValue)) {
            colorInt = stringToIntColor(hexValue);
            rgb = inColorToRGB(colorInt);
            viewColor.setBackgroundColor(colorInt);
            redValue = rgb[0];
            greenValue = rgb[1];
            blueValue = rgb[2];
            editTextRed.setText(String.valueOf(redValue));
            editTextGreen.setText(String.valueOf(greenValue));
            editTextBlue.setText(String.valueOf(blueValue));
            inputPositionCursor = editTextHex.length();
            inputEditable = editTextHex.getText();
            Selection.setSelection(inputEditable, inputPositionCursor);
        }
    }

    // setting options menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.about_title)
                    .setMessage(R.string.about_message)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
