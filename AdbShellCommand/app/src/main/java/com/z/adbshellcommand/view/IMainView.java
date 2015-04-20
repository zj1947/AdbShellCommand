package com.z.adbshellcommand.view;

/**
 * Created by Administrator on 15-4-20.
 */
public interface IMainView {
    public void showProgressDialog();
    public void showResult(String commandResult);
    public void setInputError(String errorMsg);
    public void dismissProgressDialog();
}
