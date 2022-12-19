package com.senvenwood;
import java.util.ArrayList;

public class ClickZero {
    public ClickZero() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    GameButtons gameButtons;
    int         x, y;

    ClickZero(GameButtons Ojb, int x, int y) {
        gameButtons = Ojb;//接收对象
        this.x = x;
        this.y = y;
    }

    public void click(int i, int j) {
        ArrayList arrayList = new ArrayList();
        if (i - 1 >= 0 & j - 1 >= 0 && gameButtons.gameButs[i - 1][j - 1].en == false) {//x-1,y-1
            gameButtons.gameButs[i - 1][j - 1].state = 3;
            gameButtons.gameButs[i - 1][j - 1].en = true;
            if (gameButtons.gameButs[i - 1][j - 1].value == 0)//如果 x-1,y-1 元素为0则加入队列
                arrayList.add(gameButtons.gameButs[i - 1][j - 1]);
        }
        if (j - 1 >= 0 && gameButtons.gameButs[i][j - 1].en == false) {    // x  ,y-1
            gameButtons.gameButs[i][j - 1].state = 3;
            gameButtons.gameButs[i][j - 1].en = true;
            if (gameButtons.gameButs[i][j - 1].value == 0)//如果 x  ,y-1元素为0则加入队列
                arrayList.add(gameButtons.gameButs[i][j - 1]);
        }
        if (i + 1 < x & j - 1 >= 0 && gameButtons.gameButs[i + 1][j - 1].en == false) {    // x+1,y-1
            gameButtons.gameButs[i + 1][j - 1].state = 3;
            gameButtons.gameButs[i + 1][j - 1].en = true;
            if (gameButtons.gameButs[i + 1][j - 1].value == 0)//如果 x+1,y-1元素为0则加入队列
                arrayList.add(gameButtons.gameButs[i + 1][j - 1]);
        }
        if (i - 1 >= 0 && gameButtons.gameButs[i - 1][j].en == false) {    //x-1,y
            gameButtons.gameButs[i - 1][j].state = 3;
            gameButtons.gameButs[i - 1][j].en = true;
            if (gameButtons.gameButs[i - 1][j].value == 0)//如果 x-1,y元素为0则加入队列
                arrayList.add(gameButtons.gameButs[i - 1][j]);
        }
        if (i + 1 < x && gameButtons.gameButs[i + 1][j].en == false) {    //x+1,y
            gameButtons.gameButs[i + 1][j].state = 3;
            gameButtons.gameButs[i + 1][j].en = true;
            if (gameButtons.gameButs[i + 1][j].value == 0)//如果 x+1,y元素为0则加入队列
                arrayList.add(gameButtons.gameButs[i + 1][j]);
        }
        if (i - 1 >= 0 & j + 1 < y && gameButtons.gameButs[i - 1][j + 1].en == false) {    //x-1,y+1
            gameButtons.gameButs[i - 1][j + 1].state = 3;
            gameButtons.gameButs[i - 1][j + 1].en = true;
            if (gameButtons.gameButs[i - 1][j + 1].value == 0)//如果x-1,y+1元素为0则加入队列
                arrayList.add(gameButtons.gameButs[i - 1][j + 1]);
        }
        if (j + 1 < y && gameButtons.gameButs[i][j + 1].en == false) {    // x  ,y+1
            gameButtons.gameButs[i][j + 1].state = 3;
            gameButtons.gameButs[i][j + 1].en = true;
            if (gameButtons.gameButs[i][j + 1].value == 0)//如果 x  ,y+1元素为0则加入队列
                arrayList.add(gameButtons.gameButs[i][j + 1]);
        }
        if (i + 1 < x & j + 1 < y && gameButtons.gameButs[i + 1][j + 1].en == false) {    //x+1,y+1
            gameButtons.gameButs[i + 1][j + 1].state = 3;
            gameButtons.gameButs[i + 1][j + 1].en = true;
            if (gameButtons.gameButs[i + 1][j + 1].value == 0)//如果 x+1,y+1元素为0则加入队列
                arrayList.add(gameButtons.gameButs[i + 1][j + 1]);
        }
        GameBut tmpGameBut;
        for (int z = 0; z < arrayList.size(); z++) {
            tmpGameBut = (GameBut) arrayList.get(z);
            click(tmpGameBut.x, tmpGameBut.y);
        }
        arrayList.clear();
    }

    private void jbInit() throws Exception {
    }
}
