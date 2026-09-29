package org.kenny.template;

public abstract class Game {
    //玩游戏的标准流程
    public final void play(){
        start();
        playing();
        if(needSound()){
            playSound();
        }
    }
    //开始游戏
    protected abstract void start();
    //游戏进行中
    protected abstract void playing();

    //播放音效，有默认实现
    private   void playSound(){
        System.out.println("播放音效...");
    }

    //钩子方法，是否需要音效
    protected boolean needSound(){
        return true;
    }
}
