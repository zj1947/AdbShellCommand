##AdbShellCommand
在Android项目中执行adb Shell命令，通过java代码调用执行
###Java中执行adb shell命令
本项目中，adb shell命令执行的实现方法主要参考网文（[Android Java代码执行adb Shell命令](http://www.2cto.com/kf/201501/371925.html)）。
文章中，把执行代码集成在ShellUtils工具类中，执行结果返回CommandResult这个类。ShellUtils与CommandResult这两个类的说明可查看[原文](http://www.2cto.com/kf/201501/371925.html)。<br>
在java中直接调用ShellUtils的静态方法即可
```JAVA
  //执行adb命令,
  //参数中strCommand为adb命令,第一个布尔值为执行该代码是否需要root,第二个布尔值是否需要返回结果
  CommandResult result= ShellUtils.execCommand(strCommand, true, true);
```
#####Java中执行adb shell命令关键代码
```JAVA
          //权限设置,需要root权限为"su"，否则为"sh";
            Process process = Runtime.getRuntime().exec("su");
            //获取输出流
            DataOutputStream dataOutputStream=new DataOutputStream(process.getOutputStream());
            //将命令写入
            dataOutputStream.write(strCommand.getBytes());
            dataOutputStream.writeBytes("\n");
            dataOutputStream.writeBytes("exit\n");
            //提交命令
            dataOutputStream.flush();
            
            //获取执行结果状态码
            result = process.waitFor();
            
            //读取执行信息
            StringBuilder successMsg = new StringBuilder();
            StringBuilder errorMsg = new StringBuilder();
            BufferedReader successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String s;
            while ((s = successResult.readLine()) != null) {
                    successMsg.append(s);
            }
           while ((s = errorResult.readLine()) != null) {
                    errorMsg.append(s);
           }
           
            //关闭流操作
            dataOutputStream.close();
            successResult.close();
            errorResult.close();
 
```
