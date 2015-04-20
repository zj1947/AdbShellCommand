package com.z.adbshellcommand.view;

/**
 * Created by Administrator on 15-4-20.
 */
public interface IMainView {
    /**
     * 显示进度对话框
     */
    public void showProgressDialog();

    /**
     * 展示命令执行结果
     * @param commandResult 命令结果
     */
    public void showResult(String commandResult);

    /**
     * 设置输入错误信息
     * @param errorMsg 错误信息
     */
    public void setInputError(String errorMsg);

    /**
     * 隐藏进度对话框
     */
    public void dismissProgressDialog();
}
