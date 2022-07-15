package com.joke.test2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PictureFrame extends JFrame {

    //定义一个二维数组
    private int[][] datas = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20},
            {21, 22, 23, 24, 0}
    };
    private int x0;
    private int y0;
    private JButton shangButton;
    private JButton xiaButton;
    private JButton zuoButton;
    private JButton youButton;
    private JButton helpButton;
    private JButton resetButton;

    private JPanel picturePanel;

    private int[][] winData = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20},
            {21, 22, 23, 24, 25}
    };

    public PictureFrame() {
        //窗体基本设置  方法
        initFrame();
        randomData();
        paintView();
        addButtonEvent();


        //设置窗体可见
        this.setVisible(true);

    }

    //判断是否成功
    public boolean isSuccess() {
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                if (datas[i][j] != winData[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void success() {
        datas = new int[][]{
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };
        shangButton.setEnabled(false);
        xiaButton.setEnabled(false);
        zuoButton.setEnabled(false);
        youButton.setEnabled(false);

    }

    public void rePaintView() {
        //移除所有组件
        picturePanel.removeAll();
        //遍历数组
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {

                JLabel jLabel = new JLabel(new ImageIcon("images//joke_" + datas[i][j] + ".jpg"));
                //System.out.println(data[i][j]);
                jLabel.setBounds(j * 180, i * 101, 180, 101);
                picturePanel.add(jLabel);
            }
        }
        this.add(picturePanel);

        //重新绘制
        picturePanel.repaint();
    }


    //按钮添加事件
    public void addButtonEvent() {
        shangButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("上");
                //边界处理
                if (x0 == 4) {
                    return;
                }

                //位置交换
                datas[x0][y0] = datas[x0 + 1][y0];
                datas[x0 + 1][y0] = 0;
                x0 = x0 + 1;
                if (isSuccess()) {
                    success();
                }

                //调用重绘的方法
                rePaintView();
            }
        });
        xiaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("下");
                //边界处理
                if (x0 == 0) {
                    return;
                }

                //位置交换
                datas[x0][y0] = datas[x0 - 1][y0];
                datas[x0 - 1][y0] = 0;
                x0 = x0 - 1;
                if (isSuccess()) {
                    success();
                }

                //调用重绘的方法
                rePaintView();
            }
        });
        zuoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("左");
                //边界处理
                if (y0 == 4) {
                    return;
                }

                //位置交换
                datas[x0][y0] = datas[x0][y0 + 1];
                datas[x0][y0 + 1] = 0;
                y0 = y0 + 1;
                if (isSuccess()) {
                    success();
                }

                //调用重绘的方法
                rePaintView();
            }
        });
        youButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("右");
                //边界处理
                if (y0 == 0) {
                    return;
                }

                //位置交换
                datas[x0][y0] = datas[x0][y0 - 1];
                datas[x0][y0 - 1] = 0;
                y0 = y0 - 1;

                if (isSuccess()) {
                    success();
                }

                //调用重绘的方法
                rePaintView();
            }
        });
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // System.out.println("帮助");
                success();

                //重绘
                rePaintView();
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // System.out.println("重置");
                datas = new int[][]{
                        {1, 2, 3, 4, 5},
                        {6, 7, 8, 9, 10},
                        {11, 12, 13, 14, 15},
                        {16, 17, 18, 19, 20},
                        {21, 22, 23, 24, 0}
                };
                randomData();
                rePaintView();
                shangButton.setEnabled(true);
                xiaButton.setEnabled(true);
                zuoButton.setEnabled(true);
                youButton.setEnabled(true);
            }
        });

    }


    //二维数组打乱
    public void randomData() {
        Random r = new Random();
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                int x = r.nextInt(datas.length);
                int y = r.nextInt(datas[i].length);

                int temp = datas[i][j];
                datas[i][j] = datas[x][y];
                datas[x][y] = temp;
            }
        }
        //记录0图片位置
        wc:
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                if (datas[i][j] == 0) {
                    x0 = i;
                    y0 = j;
                    break wc;
                }
            }
        }


    }


    public void paintView() {
        //标题
        JLabel titleLabel = new JLabel("Joke");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.white);
        titleLabel.setBounds(600, 20, 50, 50);
        this.add(titleLabel);

        //定义一个二维数组
//        int[][] datas={
//                {1,2,3,4,5},
//                {6,7,8,9,10},
//                {11,12,13,14,15},
//                {16,17,18,19,20},
//                {21,22,23,24,25}
//        };
        //创建面板
        picturePanel = new JPanel();
        picturePanel.setLayout(null);
        picturePanel.setBounds(50, 100, 900, 506);
        picturePanel.setBorder(BorderFactory.createLineBorder(Color.white, 3));

        //遍历二维数组
        //图片按照顺序展示
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {

                JLabel jLabel = new JLabel(new ImageIcon("images//joke_" + datas[i][j] + ".jpg"));
                //System.out.println(data[i][j]);
                jLabel.setBounds(j * 180, i * 101, 180, 101);
                picturePanel.add(jLabel);
            }
        }
        this.add(picturePanel);

        //参考图
        JLabel cankaoLable = new JLabel(new ImageIcon("images//cankao.jpg"));
        cankaoLable.setBounds(980, 100, 267, 150);
        this.add(cankaoLable);

        //上下左右按钮
        shangButton = new JButton("上");
        shangButton.setBounds(1090, 300, 50, 50);
        this.add(shangButton);

        xiaButton = new JButton("下");
        xiaButton.setBounds(1090, 400, 50, 50);
        this.add(xiaButton);

        zuoButton = new JButton("左");
        zuoButton.setBounds(1040, 350, 50, 50);
        this.add(zuoButton);

        youButton = new JButton("右");
        youButton.setBounds(1130, 350, 50, 50);
        this.add(youButton);

        helpButton = new JButton("帮助");
        helpButton.setBounds(1050, 550, 50, 50);
        this.add(helpButton);

        resetButton = new JButton("重置");
        resetButton.setBounds(1120, 550, 50, 50);
        this.add(resetButton);

        //背景图
        JLabel backgroundLable = new JLabel(new ImageIcon("images/bg.jpg"));
        backgroundLable.setBounds(0, 0, 1280, 720);
        this.add(backgroundLable);


    }

    public void initFrame() {
        //窗体大小
        this.setSize(1280, 720);
        //标题
        this.setTitle("JOKE Pictures");
        //居中显示
        this.setLocationRelativeTo(null);
        //关闭的时候退出
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //位于其他窗体之上
        this.setAlwaysOnTop(true);
        //取消默认布局
        this.setLayout(null);

    }


}
