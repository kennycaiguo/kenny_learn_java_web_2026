package com.kenny.adapter;

//目标接口
interface MyLogger{
    void log(String message);
}


//被适配类1
class Log4jLogger{
    public void logMessage(String msg){
        System.out.println("Log4jLogger: "+msg);
    }
}

// 被适配类2
class Slf4jLogger{
    public void log(String msg){
        System.out.println("slf4jLogger: "+msg);
    }
}

//适配器1
class Log4jAdapter implements MyLogger{
    private  Log4jLogger logger;

    public Log4jAdapter(Log4jLogger logger) {
        this.logger = logger;
    }

    @Override
    public void log(String message) {
        logger.logMessage(message);
    }
}

//适配器2
class Slf4jAdapter implements MyLogger{
    private  Slf4jLogger logger;

    public Slf4jAdapter(Slf4jLogger logger) {
        this.logger = logger;
    }

    @Override
    public void log(String message) {
        logger.log(message);
    }
}

public class Client {
    public static void main(String[] args) {
        MyLogger log4j = new Log4jAdapter(new Log4jLogger());
        MyLogger slf4j= new Slf4jAdapter(new Slf4jLogger());
        log4j.log("用Log4j记录日志");
        slf4j.log("用Slf4j记录日志");
    }
}
