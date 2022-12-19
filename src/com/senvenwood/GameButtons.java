package com.senvenwood;
import java.util.ArrayList;
import java.util.Random;

class GameBut {
    int     countItems; //在所有中的编号
    int     value; //所在的位置值
    boolean mine; //是否为雷
    int     x, y; //雷的坐标
    int     state; //当前状态  0 默认 2标记为雷 1 确定是雷 3 已点开
    int     color; //表示字体颜色值
    boolean en; //当前是否已经被点开

    void setValueColor() {
        switch (value) { //颜色初始化
            case 1:
                color = 2901742;//蓝
                break;
            case 2:
                color = 4559879;//绿
                break;
            case 3:
                color = 13057834;//红
                break;
            case 4:
                color = 1592749;//la
                break;
            case 5:
                color = 9119757;//深老蓝
                break;
            case 6:
                color = 16711927;//橙
                break;
            case 7:
                color = 16711927;//粉色
                break;
            case 8:
                color = 15752307;//姿色
                break;
            default:
                color = 0;//黑色
        }
    }
}

class GameButtons {
    private Random random = new Random();
    int     mine; //存在的雷数目
    GameBut gameButs[][];//雷区元素集合

    GameButtons(int x, int y /*雷区数目 0下标开始*/, int mine /*雷的数目*/) {
        //--------------------------------------------------雷数目设置  OK
        int tmpXY = (x + 1) * (y + 1);
        if (mine >= tmpXY * 0.8) //如果设置的雷数目超过 80%则安80%算
            this.mine = (int) (tmpXY * 0.8) + 1;
        else
            this.mine = mine + 1;
        mine = this.mine;
        //--------------------------------------------------生成雷区   OK
        GameBut gameButs[][] = new GameBut[x + 1][y + 1];
        for (int i = 0; i < (x + 1); i++)
            for (int j = 0; j < (y + 1); j++) {
                gameButs[i][j] = new GameBut();
                gameButs[i][j].countItems = i;
                gameButs[i][j].value = 0; //初始化  值
                gameButs[i][j].state = 0; //初始化  状态
                gameButs[i][j].x = i;
                gameButs[i][j].y = j;
                gameButs[i][j].en = false;
            }
        //--------------------------------------------------在雷区中生成雷
        ArrayList arrayList = new ArrayList(); //用语存放为被刨除的元素集合
        int n = 0; //已经生成的雷数
        for (int i = 0; i < (x + 1); i++)
            for (int j = 0; j < (y + 1); j++) {
                arrayList.add(gameButs[i][j]); //添加元素
            }
        //---------产生雷
        int ranDomNumberTmp; //用与存放随机数
        GameBut tmpGameBut;
        while (n != this.mine) { //得到雷
            ranDomNumberTmp = ranDomNumber(arrayList.size()); //得到指定范围的随机整数
            tmpGameBut = (GameBut) arrayList.get(ranDomNumberTmp);
            tmpGameBut.mine = true;
            arrayList.remove(ranDomNumberTmp);
            n++;
        }
        //--------------------------------------------------把雷区中的数值全部求出
        n = 0;
        for (int i = 0; i <= x; i++)
            for (int j = 0; j <= y; j++) {
                //x-1,y-1  x  ,y-1  x+1,y-1
                //x-1,y             x+1,y
                //x-1,y+1  x  ,y+1  x+1,y+1
                //得到 元素一周的雷数目
                //i表示X  j表示 Y
                if (gameButs[i][j].mine == true) {
                    gameButs[i][j].value = -1;
                    continue;
                }
                if (i - 1 >= 0 & j - 1 >= 0 && gameButs[i - 1][j - 1].mine == true)
                    //x-1,y-1
                    n++;
                if (j - 1 >= 0 && gameButs[i][j - 1].mine == true)
                    // x  ,y-1
                    n++;
                if (i + 1 < x & j - 1 >= 0 && gameButs[i + 1][j - 1].mine == true)
                    // x+1,y-1
                    n++;
                if (i - 1 >= 0 && gameButs[i - 1][j].mine == true)
                    //x-1,y
                    n++;
                if (i + 1 < x && gameButs[i + 1][j].mine == true)
                    //x+1,y
                    n++;
                if (i - 1 >= 0 & j + 1 < y && gameButs[i - 1][j + 1].mine == true)
                    //x-1,y+1
                    n++;
                if (j + 1 < y && gameButs[i][j + 1].mine == true)
                    // x  ,y+1
                    n++;
                if (i + 1 < x & j + 1 < y && gameButs[i + 1][j + 1].mine == true)
                    //x+1,y+1
                    n++;
                gameButs[i][j].value = n;
                gameButs[i][j].setValueColor();
                n = 0;
            }
        this.gameButs = gameButs;
    }

    public int ranDomNumber(int Max) {
        //产生Min到Max之间的随机整数
        return (int) (random.nextFloat() * Max);
    }
}
