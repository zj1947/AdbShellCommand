package com.z.adbshellcommand.util;



/**
 * result of command
 *


 *
 {@link CommandResult#result} means result of command, 0 means normal, else means error, same to excute in
 * linux shell

 *
 {@link CommandResult#successMsg} means success message of command result

 *
 {@link CommandResult#errorMsg} means error message of command result

 *


 *
 * @author Trinea 2013-5-16
 */
public  class CommandResult {


    /** result of command **/
    public int result;
    /** success message of command result **/
    public String successMsg;
    /** error message of command result **/
    public String errorMsg;


    public CommandResult(int result) {
        this.result = result;
    }


    public CommandResult(int result, String successMsg, String errorMsg) {
        this.result = result;
        this.successMsg = successMsg;
        this.errorMsg = errorMsg;
    }
}