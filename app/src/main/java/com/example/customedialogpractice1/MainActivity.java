package com.example.customedialogpractice1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private Button btnCheck,btnShow;
private TextView fevCities;
private String[] cities={"NAshik","pune","nagpur"};
private String[] skills={"c","cpp","java","a","b","c","d","g","h"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();
    }
    private  void init()
    {
        fevCities=findViewById(R.id.txtTitleMain);
        btnShow=findViewById(R.id.btnShowSelectorDialaog);
        btnCheck=findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(new BtnCheckListener());
        btnShow.setOnClickListener(new BtnShowScaleListener());

    }
    class BtnShowScaleListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            //we have created selctor dialog
            //passed parameter as context and String array which contain our data

        SelectorDialog selectorDialog1=new SelectorDialog(MainActivity.this,skills);
        selectorDialog1.setTitle("It skills");
        selectorDialog1.setOnOptionSetListener(new MyonSkillsOptionSetListener());
       selectorDialog1.show();


        }
    }
    class BtnCheckListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            SelectorDialog selectorDialog=new SelectorDialog(MainActivity.this,
                    cities);
            selectorDialog.setOnOptionSetListener(new MyonCitiOptionSetListener());
            selectorDialog.setTitle("Fev Cities");
            selectorDialog.show();

        }
    }
private class MyonCitiOptionSetListener implements SelectorDialog.onOptionSetListener{
    @Override
    //it ovveride method optionset with slected options which are selectes checkbox
    public void onOptionSet(ArrayList<String> selctedCities) {
        fevCities.setText(" ");//it will creal old data if is there on textView
        for(String city:selctedCities)
        {
            fevCities.append(city+"\n");//apend our data on textview
        }
    }
}


    private class MyonSkillsOptionSetListener implements SelectorDialog.onOptionSetListener{
        @Override
        //it ovveride method optionset with slected options which are selectes checkbox
        public void onOptionSet(ArrayList<String> selctedSkills) {
            fevCities.setText(" ");//it will creal old data if is there on textView
            for(String Skill:selctedSkills)
            {
                fevCities.append(Skill+"\n");//apend our data on textview
            }
        }
    }


}