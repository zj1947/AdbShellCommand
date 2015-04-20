package com.z.adbshellcommand;

/**
 * Created by Administrator on 15-4-20.
 */
public interface IOnRunCommandFinishListener {

    /**
     * 输入错误提示
     * @param errorMsg 提示信息
     */
    public void OnInputError(String errorMsg);

    /**
     * 处理结束返回
     * @param commandResult 将处理结果转成字符串返回
     */
    public void OnRunComplete(String commandResult);
}
