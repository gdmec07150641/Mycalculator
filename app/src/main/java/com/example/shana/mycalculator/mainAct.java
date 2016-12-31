package com.example.shana.mycalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class mainAct extends AppCompatActivity {
    private EditText weightET;
    private RadioButton man,woman;
    private Button calculator;
    private TextView resultTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        weightET= (EditText) findViewById(R.id.weight);
        man= (RadioButton) findViewById(R.id.man);
        woman= (RadioButton) findViewById(R.id.woman);
        calculator= (Button) findViewById(R.id.calculator);
        resultTV= (TextView) findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();
    }

    private void registerEvent(){
        calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!weightET.getText().toString().trim().equals("")){
                    if(man.isChecked()||woman.isChecked()){
                        Double weight=Double.parseDouble(weightET.getText().toString());
                        StringBuilder sb=new StringBuilder();
                        sb.append("测评结果:\n");
                        if(man.isChecked()){
                            sb.append("男性标准身高:");

                            double result=evaluateHeight(weight,"男");
                            sb.append((int)result+"厘米");
                        }else{
                            sb.append("女性标准身高:");

                            double result=evaluateHeight(weight,"女");
                            sb.append((int)result+"厘米");
                        }
                        resultTV.setText(sb.toString());
                    }else{
                        showMessage("请选择性别!");
                    }
                }else{
                    showMessage("请选择体重!");
                }
            }


        });
    }
//消息提示框
    private void showMessage(String message) {
        AlertDialog alert=new AlertDialog.Builder(this).create();
        alert.setTitle("系统信息");
        alert.setMessage(message);
        alert.setButton(DialogInterface.BUTTON_POSITIVE,"确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }
    //根据计算标准身高
    private double evaluateHeight(double weight,String sex){
        double height;
        if(sex.equals("男")){
            height=170-(62-weight)/0.6;
        }else{
            height=158-(52-weight)/0.5;
        }
        return height;
    }
//菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"退出");
        return super.onCreateOptionsMenu(menu);
    }
//菜单事件处理
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
