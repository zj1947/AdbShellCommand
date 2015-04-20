package com.z.adbshellcommand;

/**
 * Created by Administrator on 15-4-20.
 */
public interface IOnRunCommandFinishListener {
    public void OnInputError(String errorMsg);
    public void OnRunComplete(String commandResult);
}
