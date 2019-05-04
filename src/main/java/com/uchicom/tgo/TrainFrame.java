package com.uchicom.tgo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * ダブルバッファで処理する
 * @author hex
 *
 */
public class TrainFrame extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int span = 20;
	private int move = span;
	private int wait = 10;
	
	private int back = -1;
	
	private BufferedImage image = new BufferedImage(1000, 500, BufferedImage.TYPE_INT_RGB);

	public TrainFrame() {
		initComponents();
		Thread thread = new Thread(this);
		thread.setDaemon(true);
		thread.start();
	}

	private void initComponents() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(1000, 500));
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(e);
				switch (e.getKeyCode()) {
				case 37://左
					if (back <= 0) {
						back = 0;
					}
					back++;
				
					break;
				case 38://上
					break;
				case 39://右
					if (back >= 0) {
						back = 0;
					}
					back--;
					break;
				case 40://下ブレーキ
					if (back == 0) {
						
					} else if (back > 0) {
						back--;
					} else {
						back++;
					}
					System.out.println(back);
					break;
					default:
				}
			}
		});
		pack();
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}
	@Override
	public void paint(Graphics orgG) {
		Graphics g = image.getGraphics();
		//背景描画
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 1000, 1000);
		int offsetX = 200;
		int offsetY = 200;
		int trainWidth = 80;
		int trainHeight = 40;
		int ovalSize = 20;
		int windowWidth = 10;
		int windowHeight = 14;
		//電車描画
		g.setColor(Color.BLACK);
		g.drawRect(offsetX, offsetY - ovalSize - trainHeight, trainWidth, trainHeight); //車体
		g.drawRect(offsetX + trainWidth - windowWidth * 3 / 2, offsetY - ovalSize - trainHeight + windowHeight / 2, windowWidth, windowHeight); //窓
		g.drawOval(offsetX + ovalSize / 2, offsetY - ovalSize, ovalSize, ovalSize); //後輪
		g.drawOval(offsetX + trainWidth - ovalSize * 3 / 2, offsetY - ovalSize, ovalSize, ovalSize); //前輪
		//線路描画
		g.drawLine(0, offsetY, 1000, offsetY);
		//枕木
		for (int i = 0; i < 1000; i+=span) {
			g.drawLine(i + move, offsetY, i + move, offsetY + 5);
		}
		orgG.drawImage(image, 0, 0, this);	
	}

	@Override
	public void run() {
		while (true) {
			try {
				move += back;
				if (move <= 0) {
					move = span;
				} else if (move >= span) {
					move = 0;
				}
				
				repaint();
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
