package com.example.tranvanmanh.week5excersise;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Calendar;

/**
 * Created by tranvanmanh on 4/4/2018.
 */

public class DialgFragment extends android.support.v4.app.DialogFragment {

   private View view;

   private EditText edtNameTask;
   private DatePicker datePicker;
   private RadioGroup radioGroup;
   private Button btnSave, btnCancel;
   private RadioButton rdioHigh, rdioNormal, rdioLow;

   dataTaskInterface dataTaskInterface;

    private String name, date, priority, tag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.dialgfragment, container, false);
        findView();
        tag = getTag();
        dataTaskInterface = (dataTaskInterface)getTargetFragment();
        btnCancel.setOnClickListener(onCancel);
        btnSave.setOnClickListener(onSave);
        return view;
    }

    private void findView()
    {
        edtNameTask = (EditText) view.findViewById(R.id.edtNameTask);
        datePicker = (DatePicker) view.findViewById(R.id.datepicker);
        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        rdioHigh = (RadioButton) view.findViewById(R.id.radioHigh);
        rdioLow = (RadioButton) view.findViewById(R.id.rdioLow);
        rdioNormal = (RadioButton) view.findViewById(R.id.rdioNormal);

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                date = i + "/" + "i1" +"i2";
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    case R.id.radioHigh:
                        priority = "High";
                        break;
                    case R.id.rdioNormal:
                        priority = "Normal";
                        break;
                    case R.id.rdioLow:
                        priority = "Low";
                }
            }
        });


    }
    private View.OnClickListener onCancel = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dismiss();
        }
    };
    private View.OnClickListener onSave = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!edtNameTask.getText().toString().equals("")){
                name = edtNameTask.getText().toString();
            }
            date = datePicker.getDayOfMonth() +"/" + (datePicker.getMonth()+1)+"/" + datePicker.getYear();
            if(rdioHigh.isChecked()){
                priority = "High";
            }
            if(rdioLow.isChecked()){
                priority = "Low";
            }
            if(rdioNormal.isChecked()){

                priority = "Normal";
            }
//            ToDolistFragment toDolistFragment = new ToDolistFragment();
//            Bundle bundle = new Bundle();
//            bundle.putString("name", name);
//            bundle.putString("date", date);
//            bundle.putString("priority", priority);
//            bundle.putString("tag", tag);
//            toDolistFragment.setArguments(bundle);
            dataTaskInterface.data(name,date,priority,tag);
            dismiss();

        }
    };
}
