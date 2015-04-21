##AdbShellCommand
在Android项目中执行adb Shell命令，通过java代码调用执行，本程序采用MVP架构。
###Java中执行adb shell命令
本项目中，adb shell命令执行的实现方法主要参考网文（[Android Java代码执行adb Shell命令](http://www.2cto.com/kf/201501/371925.html)）。
文章中，把执行代码集成在ShellUtils工具类中，执行结果返回CommandResult这个类。ShellUtils与CommandResult这两个类的说明可查看[原文](http://www.2cto.com/kf/201501/371925.html)。<br>
在java中直接调用ShellUtils的静态方法即可，但涉及到输入输出流的操作，最好是在工作线程中调用，否则会阻塞UI界面。
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
###MVP架构说明
程序的架构参照了这个项目，[androidmvp](https://github.com/antoniolg/androidmvp)。
本程序中，分为四个模块：<br>
>(1)service模块：提供IMainService接口，业务处理，判断输入命令是否为空，执行adb命令以及返回执行结果(通过IOnRunCommandFinshListener接口返回执行结果，接口在调用时提供)。在presenter模块调用<br>
>(2)util模块：包含ShellUtils工具类，adb命令操作集成在这个类中。在service层调用ShellUtils这个类的静态函数执行adb命令。<br>
>(3)view模块：提供view操作接口，IMainView，接口的方法有：显示进度框，隐藏进度款，显示错误提示，显示执行命令结果。通过presenter模块调用,并在MainActivity中实现该接口，直接操作UI。<br>
>(4)presenter模块：实现两个功能，1)调用service层处理业务；2)根据service层的处理结果，调用view操作接口，执行UI更新。该类实现两个接口，一个是IMainPresenter接口，功能是调用service层处理业务，在MainActivity中调用；2)是实现IOnRunCommandFinshListener，service层处理业务后会调用这个接口，而这个接口会继续调用view操作接口更新UI<br>
具体的类图的如下：<br>
![UML类图](https://raw.githubusercontent.com/zj1947/AdbShellCommand/master/UML%E7%B1%BB%E5%9B%BE.png"UML类图")
