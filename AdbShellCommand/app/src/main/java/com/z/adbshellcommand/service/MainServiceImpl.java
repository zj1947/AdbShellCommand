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
    public void dealAdbShellCommand(final String strCommand, final IOnRunCommandFinishListener listener) {
        if (TextUtils.isEmpty(strCommand)){
            //如果输入为空，则返回输入为空的错误提示
            listener.OnInputError("command can not be Empty");
            return;
        }
        new Thread(){
            @Override
            public void run(){
                //执行adb命令
                CommandResult result= ShellUtils.execCommand(strCommand, true, true);
                //将结果格式化转换
               final String strResult= String.format(COMMAND_RESULT_FORMAT, result.result, result.successMsg, result.errorMsg);

                //获取主线程的handler
                Handler handler=new Handler(Looper.getMainLooper());
                //在主线程中处理结果
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
