package com.zk.service;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zk.model.BatchInfo;
import com.zk.model.Participator;

@Component
public class GenerateClient extends Frame {
	public GenerateClient() {
	}

	/**
	 * 
	 */
	@Autowired
	AwardService as;
	boolean runflag = false;
	List<Participator> list = null;
	List<Integer> randomNums = null;
	private static final long serialVersionUID = 1L;
	public static final int ROWS = 30;
	public static final int COLS = 40;
	public static final int BLOCKSIZE = 20;
	TextField tf1 = null;
	TextField tf2 = null;
	TextField tf3 = null;
	Participator participator = null;
	int batchIndexFlag = 0;
	int totalDraw = 0;
	List<Integer> batchList = null;
	List<BatchInfo> baInfos = null;
	int perIndex = 0;

	public void lanch(List<BatchInfo> batchInfos) {
		System.out.println(batchInfos);
		baInfos = batchInfos;
		list = as.getAllParticipators();
		this.setBounds(0, 0, COLS * BLOCKSIZE, ROWS * BLOCKSIZE);
		this.setBackground(Color.GRAY);
		this.setResizable(false);
		this.addKeyListener(new keyMonitor());
		tf1 = new TextField(30);
		tf2 = new TextField(30);
		tf3 = new TextField(30);
		this.setLayout(new FlowLayout(FlowLayout.RIGHT, 40, 80));
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});
		new Thread(new AwardThread()).start();
	}

	Image imageBuffer = null;

	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		computeDraw(g, totalDraw);
		g.setColor(c);
	}

	public void computeDraw(Graphics g, int number) {
		int listSize = list.size();
		randomNums = as.getRandomNumList(number, 0, listSize);
		randomNums = as.sort(randomNums);
		for (int i = 0; i < randomNums.size(); i++) {
			participator = list.get(randomNums.get(i));
			String drawStr = participator.getBelong2Team().getBelong2Union().getUnionName() + " "
					+ participator.getBelong2Team().getTeanName() + " " + participator.getParticipatorName();
			g.drawString(drawStr, 100, 60 + i * 20);
		}
		String awardStr = "±¾ÂÖ½±Æ·ÊÇ: " + baInfos.get(batchIndexFlag).getAwardName();
		g.drawString(awardStr, 50, 40);
	}

	public void update(Graphics g) {
		if (imageBuffer == null) {
			imageBuffer = createImage(COLS * BLOCKSIZE, ROWS * BLOCKSIZE);

		}
		Graphics graphics = imageBuffer.getGraphics();
		Color color = graphics.getColor();
		graphics.setColor(Color.GRAY);
		graphics.fillRect(0, 0, COLS * BLOCKSIZE, ROWS * BLOCKSIZE);
		graphics.setColor(color);
		paint(graphics);
		graphics.dispose();
		g.drawImage(imageBuffer, 0, 0, null);
	}

	class keyMonitor extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if (keyCode == 10 && list.size() > 0 && batchIndexFlag < baInfos.size()) {
				
				if (runflag) {
					batchList = new ArrayList<>();
					int totalNum = baInfos.get(batchIndexFlag).getTotalNum();
					int turn = baInfos.get(batchIndexFlag).getBatchTurn();
					int perNum = 0;
					if (totalNum % turn != 0) {
						perNum = (int) Math.floor(totalNum / turn);
						for (int i = 0; i < turn; i++) {
							if (turn - 1 != i) {
								batchList.add(perNum);
							} else {
								batchList.add(totalNum-perNum*(turn-1));
							}
						}
					} else {
						perNum = totalNum / turn;
						for (int i = 0; i < turn; i++) {
							batchList.add(perNum);
						}
					}

					System.out.println(batchList);
					System.out.println(batchIndexFlag);
					System.out.println(perIndex);
					if (perIndex < batchList.size()) {
						totalDraw=batchList.get(perIndex);
						perIndex++;
					}else {
						perIndex=0;
						batchIndexFlag++;
						totalDraw=0;
					}

					for (int i = 0; i < randomNums.size(); i++) {
						int removeKey = randomNums.get(i);
						list.remove(removeKey);
					}
				}
				runflag = !runflag;
				System.out.println(runflag);
				// System.out.println(list.size());
			}
		}

	}

	class AwardThread implements Runnable {

		@Override
		public void run() {
			try {
				while (true) {
					Thread.sleep(200);
					if (runflag) {
						repaint();
//						System.out.println("repaint");
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
