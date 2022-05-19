package com.example.customedialogpractice1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SelectorDialog extends Dialog {
private String title;
    private Button btnSet;
    //we want some action on option selectes so that we use interface
        public interface onOptionSetListener{
         void  onOptionSet(ArrayList<String> selctedOption);
            }

            private onOptionSetListener onOptionSetListener;
            //creating object of interface
           public void setOnOptionSetListener(onOptionSetListener onOptionSetListener)
           {
               this.onOptionSetListener=onOptionSetListener;
           }

    TextView txtView;
    private String[] options;//we have passed array from mainacctivity which we want to diplay on our diloug fro that created this string to get that data
    private LinearLayout linearLayout;//we dont have fix chechbox  we are adding dynamically to display this on screen we need layout for that use this
    private Context context;//as we are using dialog we need to use context
    private ArrayList<CheckBox> chkoptionsList = new ArrayList<CheckBox>();//
        //we dont have fix cheboxes so to crete dynamic checkbox we user arraylist which can grow and shrink dynamically
    public SelectorDialog(@NonNull Context context, String[] options) {//constructor for dialog
        super(context);
        this.context = context;
        this.options = options;
        //options is the array which we passed from mainactivity
        setContentView(R.layout.activity_selector_dialog);
        init();
        initsetListeners();

    }

    private void initsetListeners(){
        btnSet.setOnClickListener(new BtnSetListener());

    }
    //we have created a btn when we check some data we collect into arraylist
    //and show data to our mainactivity
    private class BtnSetListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            //means on btnSet if nothins is selected do nothing
            if(onOptionSetListener==null){
                return;
            }//but if some action is selected then
            //get that data into selectesOption List
            ArrayList<String> selectesOptions=new ArrayList<>();
            //for every checkbox check checkbox in arraylist
            for(CheckBox chkOtion:chkoptionsList)
            {
                if(chkOtion.isChecked())
                {//if option is checked get that option text into selected arraylist and
                    selectesOptions.add(chkOtion.getText().toString());
                }

            }
            //call optionSetlistener interface with method on optionset and pass this selected data
            onOptionSetListener.onOptionSet(selectesOptions);
            dismiss();

        }
    }
   /* private class BtnSetListeners implements onOptionSetListener{
        @Override
        public void onOptionSet(ArrayList<String> selctedOption) {

        }
    }*/
    private void init() {
        btnSet = findViewById(R.id.btnSet);
        linearLayout = findViewById(R.id.mainContainer);
        txtView = findViewById(R.id.txtTitleDialog);
        for (int i = 0; i < options.length; i++) {
            CheckBox chkoption = new CheckBox(context);
            //create object of checkboc
            //setting layout for that checkbox
            chkoption.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            //setting text to checkbox which will show on checkbox this is the string array which we have passed
            chkoption.setText(options[i]);
            //we are adding this checkbox to our checklist
            chkoptionsList.add(chkoption);
            //and we have to add this to our layout also
            linearLayout.addView(chkoption);

        }
    }


    public void setTitle(String title){
        this.title=title;
        txtView.setText(title);

    }
}