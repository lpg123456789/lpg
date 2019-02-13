package com.lpg.myTool.tool;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
	JCheckBox checkBox=null;
	
	List<MoneyData> moneyDataList=new Vector<>();
	Vector<Vector<String>> data=new Vector<>();

	public MoneyView() {
		initMoneyView();
	}
	
	public void refreshMoneyView() {
		initMoneyView();
	}
	
	public void initMoneyView() {
		moneyDataList.addAll(MoneyFile.readFileByLine());
		initData();
		creatTopPanel();
		createBottomPanel();
		init();
	}
	
	/**
	 * 初始化数据
	 */
	public void initData() {
		data.clear();
		for (MoneyData moneyData : this.moneyDataList) {
			Vector<String> v=new Vector<String>();
			v.add(moneyData.day);
			v.add(moneyData.zhiFuBao);
			v.add(moneyData.weiXin);
			v.add(moneyData.gongShang);
			v.add(moneyData.zhongGuo);
			v.add(moneyData.zhaoShang);
			v.add(moneyData.totalMoney);
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
		vector.add("总额");
		vector.add("备注");
			
		// 创建表格
		table = new JTable(new DefaultTableModel(data, vector));
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(7).setPreferredWidth(200);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int count=table.getSelectedRow();//获取你选中的行号（记录）
				MoneyData moneyData=moneyDataList.get(count);
				new MoneyAdd(moneyData);
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
		checkBox = new JCheckBox("勾选得到上一次的值");// 创建复选按钮
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
		checkBox.setHorizontalAlignment(SwingConstants.LEFT);
		actionButton.setHorizontalAlignment(SwingConstants.CENTER);
		buttonPanel.add(checkBox);
		buttonPanel.add(actionButton);
		actionButton.addActionListener(new newButtonFrame(this));
		// 加入一个 glue, glue 会挤占两个按钮之间的空间
		// buttonPanel.add(Box.createHorizontalGlue());
		// 将退出按钮加入到 buttonPanel
		buttonPanel.add(refreshButton);
		
		refreshButton.addActionListener(new myMouseAdapter(this));
		
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
		frame.setSize(new Dimension(1000, 800));
		frame.setContentPane(panelContainer);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}

}

class newButtonFrame implements ActionListener{
	
	MoneyView moneyView;
	
	public newButtonFrame(MoneyView moneyView) {
		this.moneyView = moneyView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(moneyView.checkBox.isSelected()){
			int index=moneyView.moneyDataList.size()-1;
			MoneyData moneyData=moneyView.moneyDataList.get(index);
			if(moneyData!=null){
				new MoneyAdd(moneyData);
			}else{
				new MoneyAdd();
			}
		}else{
			new MoneyAdd();
		}
	}
	
}

class myMouseAdapter implements ActionListener{
	
	MoneyView moneyView;
	
	public myMouseAdapter(MoneyView moneyView) {
		this.moneyView = moneyView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		moneyView.moneyDataList.clear();
		DefaultTableModel DefaultTableModel=(DefaultTableModel) moneyView.table.getModel();
		DefaultTableModel.getDataVector().clear();
		moneyView.moneyDataList.addAll(MoneyFile.readFileByLine());
		moneyView.initData();
		DefaultTableModel.getDataVector().addAll(moneyView.data);
		moneyView.table.repaint();
	}
	
}

