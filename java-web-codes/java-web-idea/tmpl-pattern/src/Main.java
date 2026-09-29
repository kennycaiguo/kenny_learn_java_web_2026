import org.kenny.template.ChessGame;
import org.kenny.template.RacingGame;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args) {
        System.out.println("玩象棋游戏");
        ChessGame chessGame = new ChessGame();
        chessGame.play();
        System.out.println("玩赛车游戏");
        RacingGame racingGame = new RacingGame();
        racingGame.play();
    }
}