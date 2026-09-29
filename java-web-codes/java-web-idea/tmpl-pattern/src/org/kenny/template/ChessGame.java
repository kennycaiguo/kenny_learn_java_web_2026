package org.kenny.template;

public class ChessGame extends Game{
    @Override
    protected void start() {
        System.out.println("摆好棋盘，红方先走");
    }

    @Override
    protected void playing() {
        System.out.println("双方斗得有来有回，非常激烈。。。");
    }

    @Override
    protected boolean needSound() {
        return false; //象棋游戏可以不要音效
    }
}
