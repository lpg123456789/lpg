package com.lpg.myTool.tool;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class MoneyAdd extends JFrame {

	private String zhiFuBaoData=""; 
	private String weiXinData=""; 
	private String gongShangData=""; 
	private String zhongGuoData=""; 
	private String zhaoShangData=""; 
	private String descData=""; 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MoneyAdd() {
		init();
	}
	
	public MoneyAdd(String zhiFuBaoData,String weiXinData,String gongShangData,String zhongGuoData,String zhaoShangData,String descData) {
		this.zhiFuBaoData=zhiFuBaoData;
		this.weiXinData=weiXinData;
		this.gongShangData=gongShangData;
		this.zhongGuoData=zhongGuoData;
		this.zhaoShangData=zhaoShangData;
		this.descData=descData;
		init();
	}
	

	public void init() {
		// 设置顶部提示文字和主窗体的宽，高，x值，y值
		setTitle("数据信息");
		setBounds(300, 200, 300, 400);
		Container cp = getContentPane(); // 添加一个cp容器
		cp.setLayout(null); // 设置添加的cp容器为流布局管理器

		JLabel jl1 = new JLabel("支付宝：");
		jl1.setBounds(40, 10, 200, 18);
		JTextField jt1 = new JTextField();
		jt1.setBounds(100, 10, 150, 18); // 设置宽，高，x值，y值
		jt1.setText(zhiFuBaoData);

		JLabel jl2 = new JLabel("微信：");
		jl2.setBounds(40, 50, 200, 18);
		final JTextField jt2 = new JTextField();
		jt2.setBounds(100, 50, 150, 18); // 设置宽，高，x值，y值
		jt2.setText(weiXinData);

		JLabel jl3 = new JLabel("工商：");
		jl3.setBounds(40, 90, 200, 18);
		final JTextField jt3 = new JTextField();
		jt3.setBounds(100, 90, 150, 18); // 设置宽，高，x值，y值
		jt3.setText(gongShangData);

		JLabel jl4 = new JLabel("中国：");
		jl4.setBounds(40, 130, 200, 18);
		final JTextField jt4 = new JTextField();
		jt4.setBounds(100, 130, 150, 18); // 设置宽，高，x值，y值
		jt4.setText(zhongGuoData);

		JLabel jl5 = new JLabel("招商：");
		jl5.setBounds(40, 170, 200, 18);
		final JTextField jt5 = new JTextField();
		jt5.setBounds(100, 170, 150, 18);// 设置宽，高，x值，y值
		jt5.setText(zhaoShangData);

		JLabel jl6 = new JLabel("备注：");
		jl6.setBounds(40, 210, 200, 18);
		final JTextField jt6 = new JTextField();
		jt6.setBounds(100, 210, 150, 18); // 设置宽，高，x值，y值
		jt6.setText(descData);

		// label加入容器
		cp.add(jl1);
		cp.add(jl2);
		cp.add(jl3);
		cp.add(jl4);
		cp.add(jl5);
		cp.add(jl6);

		// text加入容器
		cp.add(jt1);
		cp.add(jt2);
		cp.add(jt3);
		cp.add(jt4);
		cp.add(jt5);
		cp.add(jt6);

		// 确定按钮
		JButton jb = new JButton("确定"); // 添加一个确定按钮
		jb.setBounds(80, 300, 60, 18); // 设置确定按钮的宽，高，x值，y值
		getContentPane().add(jb); // 将确定按钮添加到cp容器中

		jb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MoneyData moneyData = new MoneyData(jt1.getText(), jt2.getText(), jt3.getText(), jt4.getText(), jt5.getText(), jt6.getText());
				MoneyFile.writeInFileByfb(moneyData);
			}
		});

		// 重置按钮
		JButton button = new JButton("重置");
		button.setBounds(150, 300, 60, 18); // 设置重置按钮的宽，高，x值，y值
		getContentPane().add(button);

		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
}
