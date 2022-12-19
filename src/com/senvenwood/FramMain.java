package com.senvenwood;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;

public class FramMain extends JFrame implements ActionListener {
    JPanel       contentPane;
    JMenuBar     jMenuBar1     = new JMenuBar();
    JMenu        jMenuMain     = new JMenu();
    JMenu        jMenu2        = new JMenu();
    JMenuItem    jMenuItem1    = new JMenuItem();
    JMenuItem    jMenuItem2    = new JMenuItem();
    JMenuItem    jMenuItem3    = new JMenuItem();
    JMenuItem    jMenuItem7    = new JMenuItem();
    JMenu        jMenu1        = new JMenu();
    JMenuItem    jMenuItem4    = new JMenuItem();
    JMenuItem    jMenuItem5    = new JMenuItem();
    JMenuItem    jMenuItem6    = new JMenuItem();
    JMenuItem    jMenuItem8    = new JMenuItem();
    BorderLayout borderLayout1 = new BorderLayout();
    JPanel       jPanel2       = new JPanel();
    GridLayout   gridLayout1   = new GridLayout();
    JPanel       jPanel1       = new JPanel();
    TitledBorder titledBorder1 = new TitledBorder("");
    BorderLayout borderLayout2 = new BorderLayout();
    JPanel       jPanel3       = new JPanel();
    JPanel       jPanel4       = new JPanel();
    JPanel       jPanel5       = new JPanel();
    JLabel       jLabel1       = new JLabel();
    JLabel       jLabel2       = new JLabel();
    JLabel       jLabel3       = new JLabel();
    public JLabel jLabel4 = new JLabel(); //把对象传入Time
    JButton jButton1  = new JButton();
    JLabel  jLab[]    = new JLabel[900];
    int     gameStart = 0; //游戏是否开始 0准备好，但未开始游戏  1开始游戏  2游戏结束
    int     xx, yy; //雷数目
    GameButtons gameButtons; //雷区
    int         nowmine, z; //当前的雷数目,雷总数
    int       yesmine;//标记正确的雷数目
    Timer     timer; //记时器
    JMenuItem jMenuItem9 = new JMenuItem(); /*Time tme=new Time(this);*/

    public FramMain() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public FramMain(int xx, int yy, int zz) {
        this.xx = xx;
        this.yy = yy;
        this.z = zz;
        try {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(borderLayout1);
        this.setJMenuBar(jMenuBar1);
        this.setResizable(false);
        setSize(new Dimension((int) 24.6 * xx, (int) 24.6 * yy));
        setTitle("扫雷");
        jMenuMain.setText("游戏");
        jMenu2.setText("帮助");
        jMenuItem1.setText("关于");
        jMenuItem7.setText("退出");
        jMenuItem7.addActionListener(new Frame1_jMenuItem7_actionAdapter(this));
        jMenu1.setText("级别");
        jMenuItem4.setText("初级");
        jMenuItem5.setText("中级");
        jMenuItem6.setText("高级");
        jMenuItem8.setText("自定义");
        jPanel2.setLayout(gridLayout1);
        jPanel2.setBorder(titledBorder1);
        jPanel1.setLayout(borderLayout2);
        jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 17));
        jLabel1.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel1.setText("雷数:");
        jLabel2.setBackground(UIManager.getColor("Button.background"));
        jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 17));
        jLabel2.setForeground(Color.red);
        jLabel3.setFont(new java.awt.Font("Dialog", Font.BOLD, 17));
        jLabel3.setText("时间:");
        jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 17));
        jLabel4.setForeground(Color.red);
        jLabel4.setBorder(null);
        jLabel4.setDisplayedMnemonic('0');
        jLabel4.setText("000");
        jButton1.setActionCommand("N");
        jButton1.setMnemonic('N');
        jButton1.setText("New");
        jButton1.addActionListener(new Frame1_jButton1_actionAdapter(this));
        gridLayout1.setColumns(xx);
        gridLayout1.setHgap(0);
        gridLayout1.setRows(yy);
        gridLayout1.setVgap(0);
        jMenuItem2.addActionListener(new Frame1_jMenuItem2_actionAdapter(this));
        jMenuItem3.addActionListener(new Frame1_jMenuItem3_actionAdapter(this));
        jMenuItem3.setText("重新开始");
        jButton1.addActionListener(new Frame1_jButton1_actionAdapter(this));
        jMenuItem2.setText("开局");
        jMenuItem9.setText("游戏说明");
        jMenuBar1.add(jMenuMain);
        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);
        jMenu2.add(jMenuItem9);
        jMenu2.add(jMenuItem1);
        jMenuMain.add(jMenuItem2);
        jMenuMain.add(jMenuItem3);
        jMenuMain.addSeparator();
        jMenuMain.add(jMenuItem7);
        jMenu1.add(jMenuItem4);
        jMenu1.add(jMenuItem5);
        jMenu1.add(jMenuItem6);
        jMenu1.addSeparator();
        jMenu1.add(jMenuItem8);
        contentPane.add(jPanel2, java.awt.BorderLayout.CENTER);
        contentPane.add(jPanel1, java.awt.BorderLayout.NORTH);
        jPanel3.add(jLabel1);
        jPanel3.add(jLabel2);
        jPanel1.add(jPanel4, java.awt.BorderLayout.EAST);
        jPanel4.add(jLabel3);
        jPanel4.add(jLabel4);
        jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);
        jPanel5.add(jButton1);
        jPanel1.add(jPanel3, java.awt.BorderLayout.WEST);
        //-----------------------------------------//生成雷区
        gameButtons = new GameButtons(xx, yy, z); //生成雷
        this.nowmine = z;
        //-----------------------------------------//生成900个元素
        int n = 0;
        for (int i = 0; i < xx; i++)
            for (int j = 0; j < yy; j++) {
                jLab[n] = new JLabel();
                jLab[n].setBorder(titledBorder1);
                jLab[n].setFont(new Font(jLab[n].getFont().getFontName(), Font.BOLD, jLab[n].getFont().getSize()));
                jLab[n].setHorizontalAlignment(JLabel.CENTER);
                jLab[n].addMouseListener(new Frame1_jLab_mouseAdapter(this));
                jPanel2.add(jLab[n]);
                n++;
            }
        jInitGame(0); //初始化元素
        jLabel2.setText(Integer.toString(nowmine));
        //-----------------------------------------
    }

    public void jMenuItem7_actionPerformed(ActionEvent e) {
        System.exit(0); //退出程序
    }

    public void jLab_mouseClicked(MouseEvent e) {
        JLabel tmpE = (JLabel) e.getSource();
        String tmpEtxt = tmpE.getToolTipText();
        int x = Integer.parseInt(tmpEtxt.split(",")[0]);
        int y = Integer.parseInt(tmpEtxt.split(",")[1]);
        /*
             if (gameStart == true) {
            timer = new Timer(); //记时器
            timer.schedule(tme, 1000);

             }*/
        if (gameStart != 2) {
            gameStart = 1; //开始游戏
            switch (e.getButton()) {
                case 1: //1 鼠标左键
                    if (gameButtons.gameButs[x][y].state != 1) {
                        if (gameButtons.gameButs[x][y].mine == true) { //是雷
                            JOptionPane jOptionPane = new JOptionPane();
                            jOptionPane.showMessageDialog(this, "您踩到雷上了", "扫雷", JOptionPane.WARNING_MESSAGE);
                            gameStart = 2; //游戏结束
                            //timer.cancel();
                            jInitGame(1);
                        } else { //不是雷
                            tmpE.setForeground(new Color(gameButtons.gameButs[x][y].color)); //设置颜色
                            if (tmpE.getText().equals("_") == true & (tmpE.getText().equals("?") == false | tmpE.getText().equals("P") == false))
                                nowmine++;
                            gameButtons.gameButs[x][y].en = true; //已经被点开
                            gameButtons.gameButs[x][y].state = 3; //已经被点开
                            tmpE.setText(Integer.toString(gameButtons.gameButs[x][y].value)); //设置显示
                            if (gameButtons.gameButs[x][y].value == 0) { //是否为0
                                if (tmpE.getText().equals("_") == true & (tmpE.getText().equals("?") == false | tmpE.getText().equals("P") == false))
                                    nowmine++;
                                ClickZero clickZero = new ClickZero(gameButtons, xx, yy);
                                clickZero.click(x, y);
                                jInitGame(2);
                            }
                        }
                    }
                    break;
                case 2: //2 鼠标中间键
                    break;
                case 3: //3 鼠标右键
                    tmpEtxt = tmpE.getToolTipText();
                    //0 默认 1 确定是雷 2标记为雷 3 已点开 -1的确是雷
                    if (gameButtons.gameButs[x][y].state == 0) { //如果默认
                        gameButtons.gameButs[x][y].state = 1; //1 标记旗子
                        nowmine--; //还有多少个雷
                        tmpE.setText("P"); //标记旗子
                        tmpE.setForeground(Color.red); //红
                        if (gameButtons.gameButs[x][y].value == -1)
                            yesmine++;
                    } else if (gameButtons.gameButs[x][y].state == 1) { //
                        gameButtons.gameButs[x][y].state = 2; //1 怀疑是雷
                        tmpE.setText("?"); //怀疑是雷
                        tmpE.setForeground(Color.darkGray); //
                    } else if (gameButtons.gameButs[x][y].state == 2) { //
                        gameButtons.gameButs[x][y].state = 0; //1 标记默认
                        tmpE.setForeground(Color.BLACK); //黑
                        tmpE.setText(""); //标记默认
                        nowmine++; //还有多少个雷
                        yesmine--;
                    }
                    break;
            }
            jLabel2.setText(Integer.toString(nowmine));
            if (yesmine == z) { //当用户把所有雷都找出来时候
                JOptionPane jOptionPane = new JOptionPane();
                jOptionPane.showConfirmDialog(this, "恭喜您扫雷成功", "扫雷", JOptionPane.QUESTION_MESSAGE);
            }
        }
    }

    void jInitGame(int stati) {
        int n = 0;
        for (int i = 0; i < xx; i++)
            for (int j = 0; j < yy; j++) {
                if (stati == 0) { //初始化时
                    jLab[n].setText("");
                    jLab[n].setForeground(new Color(0)); //设置默认颜色 黑
                } else if (stati == 2) {
                    if (gameButtons.gameButs[i][j].en = true & gameButtons.gameButs[i][j].state == 3) {
                        gameButtons.gameButs[i][j].state = 3;
                        jLab[n].setForeground(new Color(gameButtons.gameButs[i][j].color)); //设置颜色
                        if (gameButtons.gameButs[i][j].value != -1)
                            jLab[n].setText(Integer.toString(gameButtons.gameButs[i][j].value));
                        if (gameButtons.gameButs[i][j].value == 0) {
                            if (jLab[n].getText() == "P" | jLab[n].getText() == "?")
                                nowmine++;
                            jLab[n].setText("_");
                        }
                    }
                } else { //用户点到雷上时候
                    jLab[n].setForeground(new Color(gameButtons.gameButs[i][j].color)); //设置颜色
                    if (gameButtons.gameButs[i][j].value == -1) {
                        jLab[n].setForeground(Color.red);
                        jLab[n].setText("*");
                    } else if (gameButtons.gameButs[i][j].value == 0)
                        jLab[n].setText("_");
                    else
                        jLab[n].setText(Integer.toString(gameButtons.gameButs[i][j].value));
                }
                jLab[n].setToolTipText(Integer.toString(i) + "," + Integer.toString(j));
                n++;
            }
    }

    public void jButton1_actionPerformed(ActionEvent e) {
        gameStart = 0;
        jButton1.setVisible(false);
        nowmine = z;
        jLabel2.setText(Integer.toString(nowmine));
        gameButtons = new GameButtons(xx, yy, z); //生成 900个元素其中有200是雷
        jInitGame(0);
        jButton1.setVisible(true);
    }

    public void jMenuItem2_actionPerformed(ActionEvent e) {
        gameStart = 0;
    }

    public void jMenuItem3_actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e ActionEvent
     * @todo Implement this java.awt.event.ActionListener method
     */
    public void actionPerformed(ActionEvent e) {
    }

    /**
     * <p>Title: </p>
     *
     * <p>Description: </p>
     *
     * <p>Copyright: Copyright (c) 2022</p>
     *
     * <p>Company: </p>
     * @author not attributable
     * @version 1.0
     */
    private class Class1 {
        Class1() {
        }
    }
}

class Frame1_jMenuItem3_actionAdapter implements ActionListener {
    private FramMain adaptee;

    Frame1_jMenuItem3_actionAdapter(FramMain adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem3_actionPerformed(e);
    }
}

class Frame1_jMenuItem2_actionAdapter implements ActionListener {
    private FramMain adaptee;

    Frame1_jMenuItem2_actionAdapter(FramMain adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem2_actionPerformed(e);
    }
}

class Frame1_jButton1_actionAdapter implements ActionListener {
    private FramMain adaptee;

    Frame1_jButton1_actionAdapter(FramMain adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1_actionPerformed(e);
    }
}

class Frame1_jLab_mouseAdapter extends MouseAdapter {
    private FramMain adaptee;

    Frame1_jLab_mouseAdapter(FramMain adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseClicked(MouseEvent e) {
        adaptee.jLab_mouseClicked(e);
    }
}

class Frame1_jMenuItem7_actionAdapter implements ActionListener {
    private FramMain adaptee;

    Frame1_jMenuItem7_actionAdapter(FramMain adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem7_actionPerformed(e);
    }
}
