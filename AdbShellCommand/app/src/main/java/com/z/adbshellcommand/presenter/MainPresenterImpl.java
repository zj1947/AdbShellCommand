package com.z.adbshellcommand.presenter;

import com.z.adbshellcommand.IOnRunCommandFinishListener;
import com.z.adbshellcommand.service.MainServiceImpl;
import com.z.adbshellcommand.view.IMainView;

/**
 * Created by Administrator on 15-4-20.
 */
public class MainPresenterImpl implements IMainPresenter,IOnRunCommandFinishListener {

    private IMainView mainView;
    private MainServiceImpl service;

    public MainPresenterImpl(IMainView mainView) {
        this.mainView = mainView;
        service=new MainServiceImpl();
    }

    @Override
    public void runAdbShellCommand(String strCommand) {
        mainView.showProgressDialog();
        service.dealAdbShellCommand(strCommand, this);
    }

    @Override
    public void OnInputError(String errorMsg) {
        mainView.dismissProgressDialog();
        mainView.setInputError(errorMsg);
    }

    @Override
    public void OnRunComplete(String commandResult) {
        mainView.dismissProgressDialog();
        mainView.showResult(commandResult);
    }
}
