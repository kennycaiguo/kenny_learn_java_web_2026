package org.kenny.template;

public class RacingGame extends Game {
    @Override
    protected void start() {
        System.out.println("3,2,1...开始！！！");
    }

    @Override
    protected void playing() {
        System.out.println("选手们你追我赶，非常激烈。。。");
    }
}
