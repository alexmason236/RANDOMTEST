package com.zk.service;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zk.model.Participator;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JTextPane;
import java.awt.BorderLayout;

@Component
public class EditPaticipators extends JFrame {
	private static final long serialVersionUID = 1L;
	@Autowired
	AwardService awardService;
	private JPanel contentPane;
	/**
	 * 
	 */
	@Autowired
	GenerateClient generateClient;
	@Autowired
	EditBatchInfos editBatchInfos;
	JFrame jFrame = null;
	List<Participator> participators = null;

	@Autowired
	PaticipatorRepo paticipatorRepo;

	public EditPaticipators() {
		
	}

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public void newClient() {
		jFrame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btnNewButton = new JButton("导入名单");
		btnNewButton.setBounds(273, 218, 151, 23);
		contentPane.add(btnNewButton);
        getContentPane().setLayout(null);
		
		JButton btnNewButton_2 = new JButton("编辑抽奖信息");
		btnNewButton_2.setBounds(159, 78, 93, 23);
		getContentPane().add(btnNewButton_2);
		JTextPane textPane = new JTextPane();
		getContentPane().add(textPane, BorderLayout.CENTER);
		btnNewButton.addActionListener(new ActionListener() {
			@Transactional
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int value = jfc.showSaveDialog(null);
				if (value == JFileChooser.APPROVE_OPTION) {
					File file = jfc.getSelectedFile();
					System.out.println("文件:" + file.getAbsolutePath());
					System.out.println(jfc.getSelectedFile().getName());
					participators = awardService.readExcelData(file.getAbsolutePath());
					paticipatorRepo.clearAllPaticipators();
					paticipatorRepo.savePaticipators(participators);
				} else {
					System.out.println("取消");
				}
			}
		});
		JButton btnNewButton_1 = new JButton("启动抽奖");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jFrame.dispose();
				generateClient.lanch(editBatchInfos.batchInfoService.batchInfos());
			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jFrame.dispose();
				editBatchInfos.editBatchInfosLauch();
			}
		});
		btnNewButton_1.setBounds(36, 218, 139, 23);
		contentPane.add(btnNewButton_1);
		this.setVisible(true);
	}
}
