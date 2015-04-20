package com.z.adbshellcommand.service;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.z.adbshellcommand.IOnRunCommandFinishListener;
import com.z.adbshellcommand.util.CommandResult;
import com.z.adbshellcommand.util.ShellUtils;

/**
 * Created by Administrator on 15-4-20.
 */
public class MainServiceImpl implements IMainService {
    private static final String COMMAND_RESULT_FORMAT="resultCode:%s,successMsg:%s,errorMsg:%s\n";
    @Override
    public void runAdbShellCommand(final String strCommand,final IOnRunCommandFinishListener listener) {
        if (TextUtils.isEmpty(strCommand)){
            listener.OnInputError("package name can not be Empty");
            return;
        }
        new Thread(){
            @Override
            public void run(){
                CommandResult result= ShellUtils.execCommand(strCommand, true, true);
               final String strResult= String.format(COMMAND_RESULT_FORMAT, result.result, result.successMsg, result.errorMsg);

                Handler handler=new Handler(Looper.getMainLooper());

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        listener.OnRunComplete(strResult);
                    }
                });
            }
        }.start();
    }
}
