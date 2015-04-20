package com.z.adbshellcommand;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.z.adbshellcommand.util.CommandResult;
import com.z.adbshellcommand.util.ShellUtils;
import com.z.adbshellcommand.view.IMainView;


public class MainActivity extends ActionBarActivity implements IMainView {

    private EditText edtPackageNm;
    private TextView tvResult;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {

        edtPackageNm=(EditText)findViewById(R.id.edt_main_act_package_nm);
        tvResult=(TextView)findViewById(R.id.tv_main_act_result);
    }
    private void intProgressDialog(){

    }

    public void onClickEvent(View view){
         String strPackageNm=edtPackageNm.getText().toString().trim();

        if (TextUtils.isEmpty(strPackageNm)){
            edtPackageNm.setError("sdf");
            return;
        }
        final String uninstallCommand="pm uninstall "+strPackageNm;
        new Thread(){
            @Override
            public void run(){
               CommandResult result= ShellUtils.execCommand(uninstallCommand, true, true);
               final String strResult= String.format("resultCode:%s,successMsg:%s,errorMsg:%s\n", result.result, result.successMsg, result.errorMsg);

            }
        }.start();
    }

    @Override
    public void showProcess() {

    }

    @Override
    public void showResult(String commandResult) {

        tvResult.append(commandResult);
    }

    @Override
    public void setInputError() {

    }
}
