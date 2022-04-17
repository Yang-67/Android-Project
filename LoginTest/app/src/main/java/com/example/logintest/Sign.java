package com.example.logintest;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Sign extends Activity {
    private EditText edtNewUserName;
    private EditText edtPwd1,edtPwd2;
    private EditText edtYear;
    String firstPwd,secondPwd;
    private CheckBox cbRead,cbSing,cbComputer,cbSwing,cbGame,cbDancing,cbWuShu,cbRun;
    private RadioGroup rdgGender;
    private Spinner spnProvince,spnCity;
    private Button btnReg,btnYear;

    boolean isYes=true;
    String userSex;

    private List<String> provinceList;
    private List<String> cityList;

//    private List<String> registerInformation;
//
//    private JSONObject object;  //JSONObject对象，处理一个一个的对象
//    private JSONObject objRegisterInfo;
//    private JSONArray jsonArrayRegisterInfo;//JSONObject对象，处理一个一个集合或者数组
//    private String jsonString;  //保存带集合的json字符串

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign);
        initView();
        edtNewUserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String name=edtNewUserName.getText().toString();
                if(name.length()<4){
                    Toast.makeText(Sign.this,"用户名长度不能小于4",Toast.LENGTH_LONG).show();
                }
            }
        });

        edtPwd1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                firstPwd=edtPwd1.getText().toString();
                if(firstPwd.length()<6||firstPwd.length()>12){
                    Toast.makeText(Sign.this,"密码必须在6~12个字符之间",Toast.LENGTH_LONG).show();
                }
            }
        });
        edtPwd2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                secondPwd=edtPwd2.getText().toString();
                if(secondPwd.length()<6||secondPwd.length()>12){
                    Toast.makeText(Sign.this,"密码必须在6~12个字符之间",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(cbDancing.isChecked()||cbRun.isChecked()||cbWuShu.isChecked()||cbGame.isChecked()||cbComputer.isChecked()||cbSing.isChecked()||cbSwing.isChecked()||cbRead.isChecked())){
                    Toast.makeText(Sign.this,"爱好必须选择一个",Toast.LENGTH_LONG).show();
                    isYes=false;
                }else{
                    isYes=true;
                }

                if(!(edtPwd1.getText().toString().equals(edtPwd2.getText().toString()))){
                    Toast.makeText(Sign.this,"两次输入密码要一致",Toast.LENGTH_LONG).show();
                    isYes=false;
                }else{
                    isYes=true;
                }


                AlertDialog.Builder builder=new AlertDialog.Builder(Sign.this);
                builder.setMessage("确认注册？");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Sign.this, MainActivity.class);
                        intent.putExtra("username", edtNewUserName.getText().toString());
                        intent.putExtra("password", edtPwd1.getText().toString());
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        isYes=false;
                    }
                });
                builder.create().show();
            }});
        rdgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rdMale:
                        Toast.makeText(Sign.this,"男",Toast.LENGTH_LONG).show();
                        userSex="男";
                        break;
                    case R.id.rdFemale:
                        Toast.makeText(Sign.this,"女",Toast.LENGTH_LONG).show();
                        userSex="女";
                        break;
                }
            }
        });

        provinceList = new ArrayList<>();
        cityList = new ArrayList<>();
//
//        //初始化省数据 读取省数据并显示到下拉框
        try {
            InputStream inputStream =getResources().getAssets().open("City2.txt");
            BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String lines =bufferedReader.readLine();
            while (lines != null){
                stringBuffer.append(lines);
                lines =bufferedReader.readLine();
            }
            Log.d("lines",stringBuffer.toString());
            final JSONArray Data =new JSONArray(stringBuffer.toString());
//            循环这个文件数组、获取数组中每个省对象的名字
            for (int i = 0; i < Data.length(); i++){
                JSONObject provinceJsonObject = Data.getJSONObject(i);
                String provinceName = provinceJsonObject.getString("name");
                provinceList.add(provinceName);
            }
            Log.d("lines",provinceList.toString());

            btnYear.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {
                    DatePickerDialog dialog=new DatePickerDialog(Sign.this);
                    dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            edtYear.setText(year+"年"+(month+1)+"月"+dayOfMonth+"日");
                        }
                    });
                    dialog.show();
                }
            });

            //定义省份显示适配器
            ArrayAdapter<String> provinceAdapter = new ArrayAdapter<>(Sign.this,android.R.layout.simple_spinner_item,provinceList);
            provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spnProvince.setAdapter(provinceAdapter);

            spnProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        //根据当前位置的省份所在的数组位置、获取城市的数组
                        JSONObject provinceObject = Data.getJSONObject(position);
                        final JSONArray cityArray = provinceObject.getJSONArray("city");

                        //更新列表数据
                        if (cityList != null){
                            cityList.clear();
                        }

                        for (int i = 0; i < cityArray.length(); i++){
                            cityList.add(cityArray.getString(i));
                        }
                        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(Sign.this,android.R.layout.simple_spinner_item,cityList);
                        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        spnCity.setAdapter(cityAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void initView(){

        edtNewUserName= (EditText) this.findViewById(R.id.edtNewUserName);
        edtPwd1= (EditText) this.findViewById(R.id.edtPwd);
        edtPwd2= (EditText) this.findViewById(R.id.edtSndPwd);

        cbRead= (CheckBox) this.findViewById(R.id.cbRead);
        cbComputer= (CheckBox) this.findViewById(R.id.cbCompute);
        cbSing= (CheckBox) this.findViewById(R.id.cbSing);
        cbSwing= (CheckBox) this.findViewById(R.id.cbSwing);
        cbGame= (CheckBox) this.findViewById(R.id.cbGame);
        cbWuShu= (CheckBox) this.findViewById(R.id.cbWushu);
        cbRun= (CheckBox) this.findViewById(R.id.cbRunning);
        cbDancing= (CheckBox) this.findViewById(R.id.cbDancing);
        btnReg= (Button) this.findViewById(R.id.btnRegister);//确认注册
        rdgGender= (RadioGroup) this.findViewById(R.id.rdgGender);//性别
        spnProvince= (Spinner) this.findViewById(R.id.spnProvinces);
        spnCity= (Spinner) this.findViewById(R.id.spnCities);
        edtYear= (EditText) this.findViewById(R.id.edtYear);
        btnYear= (Button) this.findViewById(R.id.btnYear);
        isYes=true;
    }

}


