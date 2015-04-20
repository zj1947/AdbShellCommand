package com.z.adbshellcommand;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.z.adbshellcommand.presenter.MainPresenterImpl;
import com.z.adbshellcommand.view.IMainView;


public class MainActivity extends ActionBarActivity implements IMainView {

    private EditText edtPackageNm;
    private TextView tvResult;
    private ProgressDialog progressDialog;
    private MainPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }


    private void initView() {

        edtPackageNm=(EditText)findViewById(R.id.edt_main_act_package_nm);
        tvResult=(TextView)findViewById(R.id.tv_main_act_result);
        tvResult.setMovementMethod(ScrollingMovementMethod.getInstance());
        intProgressDialog();
    }
    private void initData() {
        presenter=new MainPresenterImpl(this);
    }
    private void intProgressDialog(){
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("running command...");
    }

    public void onClickEvent(View view){
         String strCommand=edtPackageNm.getText().toString().trim();
        presenter.runAdbShellCommand(strCommand);
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void showResult(String commandResult) {
        tvResult.append(commandResult);
    }

    @Override
    public void setInputError(String errorMsg) {
        edtPackageNm.setError(errorMsg);
    }

    @Override
    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }
}
