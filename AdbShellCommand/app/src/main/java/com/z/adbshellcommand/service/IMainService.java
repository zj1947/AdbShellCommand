package com.z.adbshellcommand.service;

import com.z.adbshellcommand.IOnRunCommandFinishListener;

/**
 * Created by Administrator on 15-4-20.
 */
public interface IMainService {
    public void runAdbShellCommand(String strPackageNm,IOnRunCommandFinishListener listener);
}
