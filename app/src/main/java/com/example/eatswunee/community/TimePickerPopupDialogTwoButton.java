package com.example.eatswunee.community;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;

import com.example.eatswunee.R;

public class TimePickerPopupDialogTwoButton extends Dialog {

    private Context context;
    private TimePickerPopupDialogClickListener TimePickerPopupDialogClickListener;
    private TimePicker timePicker;
    private TextView tvTitle, tvNegative, tvPositive;
    private String text;
    private String title;
    private static String TAG = friend_writeActivity.TAG;

    private int setHourValue;
    private int setMinuteValue;

    public TimePickerPopupDialogTwoButton(@NonNull Context context, TimePickerPopupDialogClickListener TimePickerPopupDialogClickListener) {
        super(context);
        this.context = context;
        this.TimePickerPopupDialogClickListener = TimePickerPopupDialogClickListener;

    }

    public void setText (String text) {
        this.text = text;
    }

    public void setHourValue(int setHourValue) {
        this.setHourValue = setHourValue;
    }
    public void setMinuteValue(int setMinuteValue) {
        this.setMinuteValue = setMinuteValue;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timepicker_alert_dialog_two);

        timePicker = (TimePicker) findViewById(R.id.timepicker_alert_two);

        timePicker.setIs24HourView(true);
        timePicker.setHour(setHourValue);
        timePicker.setMinute(setMinuteValue);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                setHourValue = hourOfDay;
                setMinuteValue = minute;
                Log.d(TAG, setHourValue + "시" + setMinuteValue + "분");
            }
        });

        // popup dialog button event
        tvPositive = findViewById(R.id.time_btn_yes);
        tvPositive.setOnClickListener(v -> {
            this.TimePickerPopupDialogClickListener.onPositiveClick(setHourValue, setMinuteValue);
            dismiss();
        });

        tvNegative = findViewById(R.id.time_btn_no);
        tvNegative.setOnClickListener(v -> {
            this.TimePickerPopupDialogClickListener.onNegativeClick();
            dismiss();
        });
    }
}
