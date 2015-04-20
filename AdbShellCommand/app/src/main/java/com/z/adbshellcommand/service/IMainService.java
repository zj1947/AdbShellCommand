package com.z.adbshellcommand.service;

import com.z.adbshellcommand.IOnRunCommandFinishListener;

/**
 * Created by Administrator on 15-4-20.
 */
public interface IMainService {
    /**
     * 处理命令
     * @param strCommand 命令字符
     * @param listener 处理结果监听{@link com.z.adbshellcommand.IOnRunCommandFinishListener}
     */
    public void dealAdbShellCommand(String strCommand, IOnRunCommandFinishListener listener);
}
