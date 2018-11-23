package com.zk.service;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zk.model.BatchInfo;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@Component
public class EditBatchInfos extends JFrame {
	public EditBatchInfos() {
	}

	/**
	 * 
	 */
	
	@Autowired
	BatchInfoService batchInfoService;
	@Autowired
	GenerateClient generateClient;
	JFrame jFrame = null;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the frame.
	 */
	public void editBatchInfosLauch() {
		jFrame=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u5956\u54C1\u540D\u79F0");
		lblNewLabel.setBounds(25, 23, 54, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(89, 20, 66, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("\u5956\u54C1\u6570\u91CF");
		label.setBounds(25, 81, 54, 15);
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setBounds(89, 75, 66, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u62BD\u5956\u6B21\u6570");
		lblNewLabel_1.setBounds(25, 131, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(89, 128, 66, 21);
		contentPane.add(textField_2); 
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.setBounds(10, 203, 93, 23);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("¿ªÊ¼³é½±");
		button.setBounds(151, 203, 93, 23);
		contentPane.add(button);
		this.setVisible(true);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("OK clicked");
				String textFieldStr=textField.getText().trim();
				String textField1Str=textField_1.getText().trim();
				String textField2Str=textField_2.getText().trim();
				System.out.println(textFieldStr+" "+textField1Str+" "+textField2Str);
				BatchInfo batchInfo=new BatchInfo();
				batchInfo.setAwardName(textFieldStr);
				batchInfo.setTotalNum(Integer.parseInt(textField1Str));
				batchInfo.setBatchTurn(Integer.parseInt(textField2Str));
				batchInfoService.addBatch(batchInfo);
				BatchInfoServiceImpl bImpl=(BatchInfoServiceImpl) batchInfoService;
				System.out.println(bImpl.getBathInfoList().size());
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
			}
		});
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jFrame.dispose();
				generateClient.lanch(batchInfoService.batchInfos());
			}
		});
	}

}
