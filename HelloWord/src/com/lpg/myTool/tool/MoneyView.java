package com.lpg.myTool.tool;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class MoneyView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel topPanel = null;
	JPanel bottomPanel = null;
	JTable table=null;
	
	Vector<Vector<String>> data=new Vector<>();

	public MoneyView() {
		initMoneyView();
	}
	
	public void refreshMoneyView() {
		this.setVisible(false);
		initMoneyView();
	}
	
	public void initMoneyView() {
		List<MoneyData> moneyDataList=MoneyFile.readFileByLine();
		initData(moneyDataList);
		creatTopPanel();
		createBottomPanel();
		init();
	}
	
	/**
	 * 初始化数据
	 */
	private void initData(List<MoneyData> moneyDataList) {
		data.clear();
		for (MoneyData moneyData : moneyDataList) {
			Vector<String> v=new Vector<String>();
			v.add(moneyData.day);
			v.add(moneyData.zhiFuBao);
			v.add(moneyData.weiXin);
			v.add(moneyData.gongShang);
			v.add(moneyData.zhongGuo);
			v.add(moneyData.zhaoShang);
			v.add(moneyData.desc);
			data.add(v);
		}
	}
	
	private void creatTopPanel() {
		topPanel = new JPanel();
		
		Vector<String> vector=new Vector<String>();
		vector.add("日期");
		vector.add("支付宝");
		vector.add("微信");
		vector.add("工商银行");
		vector.add("中国银行");
		vector.add("招商银行");
		vector.add("备注");
			
		// 创建表格
		table = new JTable(new DefaultTableModel(data, vector));
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(6).setPreferredWidth(200);
	
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int count=table.getSelectedRow();//获取你选中的行号（记录）
				String zhiFuBao= table.getValueAt(count, 1).toString();//读取你获取行号的某一列的值（也就是字段）
				String weiXin= table.getValueAt(count, 2).toString();//读取你获取行号的某一列的值（也就是字段）
				String gongShang= table.getValueAt(count, 3).toString();//读取你获取行号的某一列的值（也就是字段）
				String zhongGuo= table.getValueAt(count, 4).toString();//读取你获取行号的某一列的值（也就是字段）
				String zhaoShang= table.getValueAt(count, 5).toString();//读取你获取行号的某一列的值（也就是字段）
				String desc= table.getValueAt(count, 6).toString();//读取你获取行号的某一列的值（也就是字段）
				new MoneyAdd(zhiFuBao,weiXin,gongShang,zhongGuo,zhaoShang,desc);
			}		
		});
		
		// 创建包含表格的滚动窗格
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// 定义 topPanel 的布局为 BoxLayout，BoxLayout 为垂直排列
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		// 先加入一个不可见的 Strut，从而使 topPanel 对顶部留出一定的空间
		topPanel.add(Box.createVerticalStrut(10));
		// 加入包含表格的滚动窗格
		topPanel.add(scrollPane);
		// 再加入一个不可见的 Strut，从而使 topPanel 和 middlePanel 之间留出一定的空间
		topPanel.add(Box.createVerticalStrut(10));
	}

	private void createBottomPanel() {
		// 创建查询按钮
		JButton actionButton = new JButton("新增");
		// 创建退出按钮
		JButton refreshButton = new JButton("刷新");
		// 创建 bottomPanel
		bottomPanel = new JPanel();
		// 设置 bottomPanel 为垂直布局
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
		// 创建包含两个按钮的 buttonPanel
		JPanel buttonPanel = new JPanel();
		// 设置 bottomPanel 为水平布局
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		// 将查询按钮加入到 buttonPanel
		actionButton.setHorizontalAlignment(SwingConstants.CENTER);
		buttonPanel.add(actionButton);
		actionButton.addActionListener(new newButtonFrame());
		// 加入一个 glue, glue 会挤占两个按钮之间的空间
		// buttonPanel.add(Box.createHorizontalGlue());
		// 将退出按钮加入到 buttonPanel
		buttonPanel.add(refreshButton);
		
		refreshButton.addMouseListener(new myMouseAdapter(this));
		
		// 加入一个 Strut，从而使 bottomPanel 和 middlePanel 上下之间留出距离
		bottomPanel.add(Box.createVerticalStrut(10));
		// 加入 buttonPanel
		bottomPanel.add(buttonPanel);
		// 加入一个 Strut，从而使 bottomPanel 和底部之间留出距离
		bottomPanel.add(Box.createVerticalStrut(10));
	}
	
	private void init() {
		JPanel panelContainer = new JPanel();
		panelContainer.setLayout(new GridBagLayout());

		GridBagConstraints c1 = new GridBagConstraints();
		c1.gridx = 0;
		c1.gridy = 0;
		c1.weightx = 1.0;
		c1.weighty = 1.0;
		c1.fill = GridBagConstraints.BOTH;
		// 加入 topPanel
		panelContainer.add(topPanel, c1);

		GridBagConstraints c3 = new GridBagConstraints();
		c3.gridx = 0;
		c3.gridy = 1;
		c3.weightx = 1.0;
		c3.weighty = 0;
		c3.fill = GridBagConstraints.HORIZONTAL;
		// 加入 bottomPanel
		panelContainer.add(bottomPanel, c3);
		// 创建窗体
		JFrame frame = new JFrame("资料数据");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelContainer.setOpaque(true);
		frame.setSize(new Dimension(600, 400));
		frame.setContentPane(panelContainer);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}

}

class newButtonFrame implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		new MoneyAdd();
	}
	
}

class myMouseAdapter extends MouseAdapter{
	
	MoneyView moneyView;
	
	public myMouseAdapter(MoneyView moneyView) {
		this.moneyView = moneyView;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
}

