package il.ac.hit.costManager.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Point;
import java.awt.Color;
import java.awt.Toolkit;

public class CostManagerMenu extends JFrame {

	/**
	 * panel 1
	 */

	public JTextField sumCategoryField;
	public JTextField CategoryField;
	public JTextField SumItemNameField;
	public JTextField SumTotalCostField;
	public JPanel contentPane;
	public static JTable table;
	
	/**
	 * panel 2
	 */
	
	public JTextField categoriesField;
	public static JTable table2;

	/**
	 * panel 3
	 */
	
	public static JTable table3;
	private JTextField addItemField;
	private JTextField addCategoryField;
	private JTextField addPriceField;
	private JTextField addCurrencyField;
	private JTextField purchase_Date_DD;
	private JTextField purchase_Date_MM;
	private JTextField purchase_Date_YYYY;
	private JTextField txtMm;
	private JTextField txtYyyy;
	
	public void ManagerMenu() { //Launches the window
		//Add an icon to the application
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sagi\\Desktop\\\u05D0\u05D1\u05DF \u05D3\u05E8\u05DA 2\\CostManagerApp\\img\\CostMangerIcon.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1140, 534);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);

		//Add a menu buttom on the left side of the screen
		JMenu mnFile = new JMenu("Menu");
		mnFile.setBackground(Color.WHITE);
		mnFile.setForeground(Color.BLACK);
		menuBar.add(mnFile);

		//Add the buttom "Exit" in the menu to close the app
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setBackground(SystemColor.WHITE);
		mntmExit.setForeground(Color.BLACK);
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});

		//Add the buttom "Logout" to the buttom menu
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.setBackground(SystemColor.WHITE);
		mntmLogout.setForeground(Color.BLACK);
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Returns to login screen.");
			}});

		//Add a buttom in the menu to create a pie chart
		JMenuItem mntmCreatePieDiagram = new JMenuItem("Create Pie Diagram.");
		mntmCreatePieDiagram.setForeground(Color.BLACK);
		mntmCreatePieDiagram.setBackground(Color.WHITE);
		mntmCreatePieDiagram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Open the pie chart window
				PieChart demo = new PieChart("CostManager Pie Chart", "Pie chart from dates: 11/12/2020 - 22/12/2020");
				demo.pack();
				demo.setVisible(true);
				System.out.println("Creates a pie chart.");
			}});

		//Add the buttons to mnFile
		mnFile.add(mntmCreatePieDiagram);
		mnFile.add(mntmLogout);
		mnFile.add(mntmExit);

		//Create a panel
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//Set the tabs of summary,categories,items on TOP
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(Color.WHITE);
		tabbedPane.setBounds(0, 0, 1128, 459);
		contentPane.add(tabbedPane);

		//Set the summary tab
	    Panel panel = new Panel();
	    panel.setBackground(SystemColor.activeCaption);
	    tabbedPane.addTab("Summary", null, panel, null);
	    tabbedPane.setFont(new Font("Cooper Black", Font.PLAIN, 16));
	    panel.setLayout(null);

	    //Set the table in Summary tab
	    JScrollPane SummaryMenu = new JScrollPane();
	    SummaryMenu.setBounds(0, 0, 709, 424);
	    panel.add(SummaryMenu);
	    table = new JTable();
	    SummaryMenu.setViewportView(table);

	    //In summary set the label "Category"
	    JLabel lblSumCategoryName = new JLabel("Category");
	    lblSumCategoryName.setOpaque(true);
	    lblSumCategoryName.setBackground(Color.WHITE);
	    lblSumCategoryName.setForeground(Color.BLACK);
	    lblSumCategoryName.setFont(new Font("Cooper Black", Font.PLAIN, 18));
	    lblSumCategoryName.setBounds(780, 98, 146, 20);
	    panel.add(lblSumCategoryName);

		//In summary This is the text field of category
		sumCategoryField = new JTextField();
	    sumCategoryField.setEditable(true);
	    sumCategoryField.setFont(new Font("Cooper Black", Font.PLAIN, 13));
	    sumCategoryField.setBounds(958, 96, 146, 26);
	    panel.add(sumCategoryField);
	    sumCategoryField.setColumns(10);

	    //In summary set this is the field of DD in purchase Date
	    CategoryField = new JTextField();
	    CategoryField.setText("  DD");
	    CategoryField.setFont(new Font("Cooper Black", Font.PLAIN, 13));
	    CategoryField.setColumns(10);
	    CategoryField.setBounds(959, 165, 39, 26);
	    panel.add(CategoryField);

	    //In summary tab this is the field of item name
	    SumItemNameField = new JTextField();
	    SumItemNameField.setEditable(true);
	    SumItemNameField.setFont(new Font("Cooper Black", Font.PLAIN, 13));
	    SumItemNameField.setColumns(10);
	    SumItemNameField.setBounds(959, 130, 146, 26);
	    panel.add(SumItemNameField);

	    //In summary tab this is the label "purchase date"
	    JLabel lblSumPurchDate = new JLabel("Purchase Date");
	    lblSumPurchDate.setOpaque(true);
	    lblSumPurchDate.setBackground(Color.WHITE);
	    lblSumPurchDate.setForeground(Color.BLACK);
	    lblSumPurchDate.setFont(new Font("Cooper Black", Font.PLAIN, 18));
	    lblSumPurchDate.setBounds(780, 168, 146, 20);
	    panel.add(lblSumPurchDate);

	    //In summary tab this is the label of Item name
	    JLabel lblSumItemName = new JLabel("Item Name");
	    lblSumItemName.setOpaque(true);
	    lblSumItemName.setBackground(Color.WHITE);
	    lblSumItemName.setForeground(Color.BLACK);
	    lblSumItemName.setFont(new Font("Cooper Black", Font.PLAIN, 18));
	    lblSumItemName.setBounds(780, 133, 146, 20);
	    panel.add(lblSumItemName);

	    //In summary tab this is the field of total cost
	    SumTotalCostField = new JTextField();
		SumTotalCostField.setEditable(false);
		SumTotalCostField.setFont(new Font("Cooper Black", Font.PLAIN, 13));
	    SumTotalCostField.setColumns(10);
	    SumTotalCostField.setBounds(958, 310, 146, 26);
	    panel.add(SumTotalCostField);

	    //In summary tab this is the label of total cost
	    JLabel lblSumTotalCost = new JLabel("Total Cost");
	    lblSumTotalCost.setOpaque(true);
	    lblSumTotalCost.setBackground(Color.WHITE);
	    lblSumTotalCost.setForeground(Color.BLACK);
	    lblSumTotalCost.setFont(new Font("Cooper Black", Font.PLAIN, 18));
	    lblSumTotalCost.setBounds(780, 313, 146, 20);
	    panel.add(lblSumTotalCost);

	    //in summary tab this is the add button
	    JButton SumAddButtom = new JButton("Add Item");
	    SumAddButtom.setFont(new Font("Cooper Black", Font.PLAIN, 20));
	    SumAddButtom.setForeground(new Color(255, 255, 255));
	    SumAddButtom.addActionListener(new ActionListener() { //Add item
			public void actionPerformed(ActionEvent arg0) {
				try {
					System.out.println("Added an item");
					} catch (Exception e1) {
					e1.printStackTrace();
					}
				}
			});
	    SumAddButtom.setBounds(796, 387, 115, 29);
	    SumAddButtom.setOpaque(false);
	    SumAddButtom.setContentAreaFilled(false);
	    panel.add(SumAddButtom);

	    //In summary tab this is the remove buttom
	    JButton SumDeleteButton = new JButton("Remove Item");
	    SumDeleteButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
	    SumDeleteButton.setForeground(new Color(255, 255, 255));
	    SumDeleteButton.addActionListener(new ActionListener() { //Delete item
			public void actionPerformed(ActionEvent arg0) {
				try {
					//This will remove an item
					System.out.println("Removed an item");
					} catch (Exception e) {
					e.printStackTrace();
					}
				}
			});
	    SumDeleteButton.setBounds(958, 387, 127, 29);
	    SumDeleteButton.setOpaque(false);
	    SumDeleteButton.setContentAreaFilled(false);
	    panel.add(SumDeleteButton);

	    //set the name of tab 2 to categories
	  	Panel panel2 = new Panel();
	  	panel2.setBackground(SystemColor.activeCaption);
	  	tabbedPane.addTab("Categories", null, panel2, null);
	  	panel2.setLayout(null);

	  	//Add an "add" buttom in the categories tab
	  	JButton CateAddButton = new JButton("Add");
	  	CateAddButton.setForeground(Color.BLACK);
	  	CateAddButton.setFont(new Font("Cooper Black", Font.PLAIN, 30));
	  	CateAddButton.setActionCommand("AddButton2");
	    CateAddButton.setBounds(754, 265, 146, 29);
		CateAddButton.addActionListener(new ActionListener() { //Actually add category to app
			public void actionPerformed(ActionEvent arg0) {
				try {
					System.out.println("Added an category");
					}catch (Exception e) {
					e.printStackTrace();
					}
					}
			});
		CateAddButton.setOpaque(false);
		CateAddButton.setContentAreaFilled(false);
		CateAddButton.setBorderPainted(false);
 		panel2.add(CateAddButton);

 		//In the categories tab add a buttom named remove
        JButton CateRemoveButton = new JButton("Remove");
        CateRemoveButton.setForeground(Color.BLACK);
        CateRemoveButton.setFont(new Font("Cooper Black", Font.PLAIN, 30));
	  	CateRemoveButton.setActionCommand("RemoveButton2");
	  	CateRemoveButton.setBounds(916, 265, 165, 29);
        CateRemoveButton.addActionListener(new ActionListener() { //Remove category from app
			public void actionPerformed(ActionEvent arg0) {
				try {
					System.out.println("Removed a category.");
					}catch (Exception e) {
					e.printStackTrace();
					}
					}
			});
        CateRemoveButton.setOpaque(false);
        CateRemoveButton.setContentAreaFilled(false);
        CateRemoveButton.setBorderPainted(false);
        panel2.add(CateRemoveButton);

        //In the categories tab add label "caregory name"
        JLabel lblCategoriesName = new JLabel("Category Name");
        lblCategoriesName.setBackground(Color.WHITE);
        lblCategoriesName.setOpaque(true);
        lblCategoriesName.setForeground(Color.BLACK);
        lblCategoriesName.setBounds(767, 93, 165, 20);
        lblCategoriesName.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        panel2.add(lblCategoriesName);

        //In the categories tab add categories text field
        categoriesField = new JTextField();
        categoriesField.setBackground(SystemColor.text);
        categoriesField.setBounds(958, 89, 146, 26);
        categoriesField.setFont(new Font("Cooper Black", Font.PLAIN, 18));
        categoriesField.setEditable(true);
        categoriesField.setColumns(10);
        panel2.add(categoriesField);

        //Add the box in categories tab to fill the table
        JScrollPane CategoriesMenu = new JScrollPane();
        CategoriesMenu.setBounds(0, 0, 709, 424);
        panel2.add(CategoriesMenu);
        table2 = new JTable();
        CategoriesMenu.setViewportView(table2);

        //Add background in panel 2
		 JLabel ImgCostBackground = new JLabel("");
		 ImgCostBackground.setVerticalTextPosition(SwingConstants.BOTTOM);
		 ImgCostBackground.setVerticalAlignment(SwingConstants.BOTTOM);
		 ImgCostBackground.setLocation(new Point(50, 50));
		 ImgCostBackground.setHorizontalTextPosition(SwingConstants.CENTER);
		 ImgCostBackground.setFont(new Font("Cooper Black", Font.PLAIN, 19));
		 ImgCostBackground.setAlignmentX(0.5f);
		 ImgCostBackground.setBounds(372, -23, 1472, 549);
		 ImgCostBackground.setIcon(new ImageIcon("C:\\Users\\Sagi\\Desktop\\\u05D0\u05D1\u05DF \u05D3\u05E8\u05DA 2\\CostManagerApp\\img\\MoneyBackground.png"));
		 panel2.add(ImgCostBackground);

		 //add another background in panel 2
		 JLabel ImgCostBackground2 = new JLabel("");
		 ImgCostBackground2.setVerticalTextPosition(SwingConstants.BOTTOM);
		 ImgCostBackground2.setVerticalAlignment(SwingConstants.BOTTOM);
		 ImgCostBackground2.setLocation(new Point(50, 50));
		 ImgCostBackground2.setHorizontalTextPosition(SwingConstants.CENTER);
		 ImgCostBackground2.setFont(new Font("Cooper Black", Font.PLAIN, 19));
		 ImgCostBackground2.setAlignmentX(0.5f);
		 ImgCostBackground2.setBounds(-95, 0, 1531, 549);
		 ImgCostBackground2.setIcon(new ImageIcon("C:\\Users\\Sagi\\Desktop\\\u05D0\u05D1\u05DF \u05D3\u05E8\u05DA 2\\CostManagerApp\\img\\MoneyBackground.png"));
		 panel2.add(ImgCostBackground2);
		 
		 /*
		  * panel 3
		  */

		//Define panel 3, Items
		 JPanel panel3 = new JPanel();
		 panel3.setBackground(Color.WHITE);
		 tabbedPane.addTab("Items", null, panel3, null);
		 panel3.setLayout(null);

		 //in items tab set field "MM"
		 txtMm = new JTextField();
		 txtMm.setText("  MM");
		 txtMm.setFont(new Font("Cooper Black", Font.PLAIN, 13));
		 txtMm.setColumns(10);
		 txtMm.setBounds(1008, 165, 39, 26);
		 panel.add(txtMm);

		//in items tab set field "YYYY"
		txtYyyy = new JTextField();
		 txtYyyy.setText(" YYYY");
		 txtYyyy.setFont(new Font("Cooper Black", Font.PLAIN, 13));
		 txtYyyy.setColumns(10);
		 txtYyyy.setBounds(1057, 165, 47, 26);
		 panel.add(txtYyyy);

		//in items tab set add field for item
		 addItemField = new JTextField();
		 addItemField.setFont(new Font("Cooper Black", addItemField.getFont().getStyle(), addItemField.getFont().getSize() + 4));
		 addItemField.setBounds(936, 42, 154, 35);
		 panel3.add(addItemField);
		 addItemField.setColumns(10);

		 //In items tab add label "item name"
		 JLabel lblItemName = new JLabel("Item Name");
		 lblItemName.setBackground(Color.WHITE);
		 lblItemName.setOpaque(true);
		 lblItemName.setForeground(Color.BLACK);
		 lblItemName.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		 lblItemName.setBounds(744, 46, 125, 20);
		 panel3.add(lblItemName);

		//In items tab add buttom "remove"
		JButton btnRemoveItems = new JButton("Remove");
		 btnRemoveItems.setDefaultCapable(false);
		 btnRemoveItems.setContentAreaFilled(false);
		 btnRemoveItems.setBackground(Color.WHITE);
		 btnRemoveItems.setForeground(Color.BLACK);
		 btnRemoveItems.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		 btnRemoveItems.setActionCommand("hidenRefreshButton3");
		 btnRemoveItems.setBounds(933, 343, 157, 29);
		 btnRemoveItems.addActionListener(new ActionListener() { //Added an item to db
			public void actionPerformed(ActionEvent arg0) {
				try {
						System.out.println("Removed an item.");
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		 });
		 btnRemoveItems.setBorderPainted(false);
		 panel3.add(btnRemoveItems); 

		 //In items tab add table for adding data
		 JScrollPane ItemMenu = new JScrollPane();
		 ItemMenu.setBounds(0, 0, 709, 424);
		 panel3.add(ItemMenu);
		 table3 = new JTable();
		 ItemMenu.setViewportView(table3);

		 //in items tab add the buttom "add"
		 JButton btnAddItems = new JButton("Add");
		 btnAddItems.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		 btnAddItems.setDefaultCapable(false);
		 btnAddItems.setContentAreaFilled(false);
		 btnAddItems.setBorderPainted(false);
		 btnAddItems.setBackground(Color.BLACK);
		 btnAddItems.setActionCommand("hidenRefreshButton3");
		 btnAddItems.setBounds(743, 343, 157, 29);
		 btnAddItems.addActionListener(new ActionListener() { //Added an item to db
			public void actionPerformed(ActionEvent arg0) {
				try {
					System.out.println("Added an item.");
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		 });
		 panel3.add(btnAddItems);

		 //In the items tab add label "category"
		 JLabel lblCategory = new JLabel("Category");
		 lblCategory.setOpaque(true);
		 lblCategory.setForeground(Color.BLACK);
		 lblCategory.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		 lblCategory.setBackground(Color.WHITE);
		 lblCategory.setBounds(744, 103, 125, 20);
		 panel3.add(lblCategory);

		 //In the Items tab add field to add categories
		 addCategoryField = new JTextField();
		 addCategoryField.setColumns(10);
		 addCategoryField.setBounds(936, 99, 154, 35);
		 panel3.add(addCategoryField);

		 //In the items tab add label Price
		 JLabel lblPrice = new JLabel("Price\r\n");
		 lblPrice.setOpaque(true);
		 lblPrice.setForeground(Color.BLACK);
		 lblPrice.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		 lblPrice.setBackground(Color.WHITE);
		 lblPrice.setBounds(744, 159, 125, 20);
		 panel3.add(lblPrice);

		 //In the items tab add field to add price
		 addPriceField = new JTextField();
		 addPriceField.setColumns(10);
		 addPriceField.setBounds(936, 155, 154, 35);
		 panel3.add(addPriceField);

		 //In the Items tab add label "Currency"
		 JLabel lblCurrency = new JLabel("Currency");
		 lblCurrency.setOpaque(true);
		 lblCurrency.setForeground(Color.BLACK);
		 lblCurrency.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		 lblCurrency.setBackground(Color.WHITE);
		 lblCurrency.setBounds(744, 216, 125, 20);
		 panel3.add(lblCurrency);

		 //In the items tab add field to add currency
		 addCurrencyField = new JTextField();
		 addCurrencyField.setColumns(10);
		 addCurrencyField.setBounds(936, 212, 154, 35);
		 panel3.add(addCurrencyField);

		 //In the items tab add field to add the DD to purchase date
		 purchase_Date_DD = new JTextField();
		 purchase_Date_DD.setText("  DD");
		 purchase_Date_DD.setColumns(10);
		 purchase_Date_DD.setBounds(936, 262, 39, 35);
		 panel3.add(purchase_Date_DD);

		 //in the items tab add label "purchase date"
		 JLabel lblPurchaseDate = new JLabel("Purchase Date");
		 lblPurchaseDate.setOpaque(true);
		 lblPurchaseDate.setForeground(Color.BLACK);
		 lblPurchaseDate.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		 lblPurchaseDate.setBackground(Color.WHITE);
		 lblPurchaseDate.setBounds(719, 277, 154, 20);
		 panel3.add(lblPurchaseDate);

		//In the items tab add field to add the MM to purchase date
		purchase_Date_MM = new JTextField();
		 purchase_Date_MM.setText("  MM");
		 purchase_Date_MM.setColumns(10);
		 purchase_Date_MM.setBounds(985, 262, 39, 35);
		 panel3.add(purchase_Date_MM);

		//In the items tab add field to add the YYYY to purchase date
		purchase_Date_YYYY = new JTextField();
		 purchase_Date_YYYY.setText("  YYYY");
		 purchase_Date_YYYY.setColumns(10);
		 purchase_Date_YYYY.setBounds(1034, 262, 39, 35);
		 panel3.add(purchase_Date_YYYY);

		 //In the items tab add a background
		 JLabel ImgCostBackItems1 = new JLabel("");
		 ImgCostBackItems1.setVerticalTextPosition(SwingConstants.BOTTOM);
		 ImgCostBackItems1.setVerticalAlignment(SwingConstants.BOTTOM);
		 ImgCostBackItems1.setLocation(new Point(50, 50));
		 ImgCostBackItems1.setHorizontalTextPosition(SwingConstants.CENTER);
		 ImgCostBackItems1.setFont(new Font("Cooper Black", Font.PLAIN, 19));
		 ImgCostBackItems1.setAlignmentX(0.5f);
		 ImgCostBackItems1.setBounds(391, -31, 1472, 549);
		 ImgCostBackItems1.setIcon(new ImageIcon("C:\\Users\\Sagi\\Desktop\\\u05D0\u05D1\u05DF \u05D3\u05E8\u05DA 2\\CostManagerApp\\img\\MoneyBackground.png"));
		 panel3.add(ImgCostBackItems1);

		//In the items tab add a background2
		JLabel imgCostBackground = new JLabel("");
		 imgCostBackground.setVerticalTextPosition(SwingConstants.BOTTOM);
		 imgCostBackground.setVerticalAlignment(SwingConstants.BOTTOM);
		 imgCostBackground.setLocation(new Point(50, 50));
		 imgCostBackground.setHorizontalTextPosition(SwingConstants.CENTER);
		 imgCostBackground.setFont(new Font("Cooper Black", Font.PLAIN, 19));
		 imgCostBackground.setAlignmentX(0.5f);
		 imgCostBackground.setBounds(382, -29, 1472, 549);
		 imgCostBackground.setIcon(new ImageIcon("C:\\Users\\Sagi\\Desktop\\\u05D0\u05D1\u05DF \u05D3\u05E8\u05DA 2\\CostManagerApp\\img\\MoneyBackground.png"));
		 panel.add(imgCostBackground);
	}
}
