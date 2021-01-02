package il.ac.hit.costManager.view;

import il.ac.hit.costManager.model.CostItem;
import il.ac.hit.costManager.model.CostManagerException;
import il.ac.hit.costManager.model.Currency;
import il.ac.hit.costManager.viewmodel.IViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class View implements IView {

	private IViewModel vm;
	private ApplicationUI ui;

	@Override
	public void setViewModel(IViewModel vm) {
		this.vm = vm;
	}

	@Override
	public void showMessage(String text) {
		ui.showMessage(text);
	}

	@Override
	public void showItems(CostItem[] vec) {
		ui.showItems(vec);
	}

	public View() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				View.this.ui = new ApplicationUI();
				View.this.ui.start();
			}
		});
	}

	public class ApplicationUI //implements IView
	{

		/**
		 * Set General components
		 */
		private JPanel panelMain;
		private Panel panel1;
		private Panel panel2;
		private JPanel panel3;


		private JMenuBar menuBar;
		private JFrame frame;
		private JTabbedPane tabbedPane;
		private JMenu mnFile;
		private JMenuItem mntmExit;
		private JMenuItem mntmCreatePieDiagram;

		/**
		 * panel 1
		 */

		private JScrollPane SummaryMenu;
		private JTable table;
		private JTextField sumCategoryField;
		private JTextField categoryField;
		private JTextField SumItemNameField;
		private JTextField sumTotalCostField;
		private JTextField currencySumField;
		private JLabel lblSumCurrency;
		private JLabel lblSumCategoryName;
		private JLabel lblSumPurchDate;
		private JLabel lblSumItemName;
		private JLabel lblSumTotalCost;
		private JButton sumAddButtom;
		private JButton sumDeleteButton;

		/**
		 * panel 2
		 */
		private JTextField categoriesField;
		private JTable table2;
		private JLabel lblCategoriesName;
		private JLabel imgCostBackgroundCate;
		private JLabel imgCostBackgroundCate2;
		private JButton cateAddButton;
		private JButton cateRemoveButton;
		private JScrollPane categoriesMenu;
		/**
		 * panel 3
		 */
		private JTable table3;
		private JScrollPane ItemMenu;
		private JLabel lblItemName;
		private JLabel lblCategory;
		private JLabel lblPrice;
		private JLabel lblCurrency;
		private JLabel lblPurchaseDate;
		private JLabel ImgCostBackItems1;
		private JLabel imgCostBackground;
		private JTextField addItemField;
		private JTextField addCategoryField;
		private JTextField addPriceField;
		private JTextField addCurrencyField;
		private JTextField purchase_Date_DD;
		private JTextField purchase_Date_MM;
		private JTextField purchase_Date_YYYY;
		private JTextField txtMm;
		private JTextField txtYyyy;
		private JButton btnRemoveItems;
		private JButton btnAddItems;

		public ApplicationUI() {
			//creating the window
			frame = new JFrame("CostManager");
			//create the main panel
			panelMain = new JPanel();
			//Set the summary panel
			Panel panel1 = new Panel();
			//set the categories panel
			Panel panel2 = new Panel();
			//set the items panel
			JPanel panel3 = new JPanel();
			//Set the tabs of summary,categories,items on TOP
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

			//create the Jmenu
			JMenuBar menuBar = new JMenuBar();
			//add menu button on top
			JMenu mnFile = new JMenu("Menu");
			//add menu the buttom "exit"
			JMenuItem mntmExit = new JMenuItem("Exit");
			//Add a buttom in the menu to create a pie chart
			JMenuItem mntmCreatePieDiagram = new JMenuItem("Create Pie Diagram.");

			//create three panels
			/* Panel 1 related (Summary) */

			//Set the table in Summary tab
			JScrollPane SummaryMenu = new JScrollPane();
			table = new JTable();

			//In summary set the label "Category"
			JLabel lblSumCategoryName = new JLabel("Category");
			//In summary tab this is the label "purchase date"
			JLabel lblSumPurchDate = new JLabel("Purchase Date");
			//In summary tab this is the label of Item name
			JLabel lblSumItemName = new JLabel("Item Name");
			//In summary tab this is the label of total cost
			JLabel lblSumTotalCost = new JLabel("Total Cost");
			//In summary tab this is the label of Currency
			JLabel lblSumCurrency = new JLabel("Currency");

			//In summary tab this is the field of total cost
			sumTotalCostField = new JTextField();
			//In summary tab this is the field of Currency
			currencySumField = new JTextField();
			//In summary This is the text field of category
			sumCategoryField = new JTextField();
			//In summary set this is the field of DD in purchase Date
			categoryField = new JTextField();
			//In summary tab this is the field of item name
			SumItemNameField = new JTextField();

			//in summary tab this is the add button
			JButton sumAddButtom = new JButton("Add Item");
			//In summary tab this is the remove buttom
			JButton sumDeleteButton = new JButton("Remove Item");

			/* Panel 2 related (Categories) */
			//Add the box in categories tab to fill the table
			JScrollPane categoriesMenu = new JScrollPane();

			//Add background in panel 2 (categories)
			JLabel imgCostBackgroundCate = new JLabel("");
			JLabel imgCostBackground2 = new JLabel("");


			//Add an "add" buttom in the categories tab
			JButton cateAddButton = new JButton("Add");
			//In the categories tab add a buttom named remove
			JButton cateRemoveButton = new JButton("Remove");

			//In the categories tab add label "caregory name"
			JLabel lblCategoriesName = new JLabel("Category Name");

			//In the categories tab add categories text field
			categoriesField = new JTextField();

			/* Panel 3 related (Items) */

			//In items tab add table for adding data
			JScrollPane ItemMenu = new JScrollPane();

			//In the items tab add field to add the DD to purchase date
			purchase_Date_DD = new JTextField();
			//in items tab set field "MM"
			txtMm = new JTextField();
			//In the items tab add field to add the MM to purchase date
			purchase_Date_MM = new JTextField();
			//in items tab set field "YYYY"
			txtYyyy = new JTextField();
			//In the items tab add field to add the YYYY to purchase date
			purchase_Date_YYYY = new JTextField();
			//in items tab set add field for item
			addItemField = new JTextField();
			//In the Items tab add field to add categories
			addCategoryField = new JTextField();
			//In the items tab add field to add price
			addPriceField = new JTextField();
			//In the items tab add field to add currency
			addCurrencyField = new JTextField();

			//In items tab add label "item name"
			JLabel lblItemName = new JLabel("Item Name");
			//In the items tab add label "category"
			JLabel lblCategory = new JLabel("Category");
			//In the items tab add label Price
			JLabel lblPrice = new JLabel("Price\r\n");
			//In the Items tab add label "Currency"
			JLabel lblCurrency = new JLabel("Currency");
			//in the items tab add label "purchase date"
			JLabel lblPurchaseDate = new JLabel("Purchase Date");

			//In items tab add buttom "remove"
			JButton btnRemoveItems = new JButton("Remove");
			//in items tab add the buttom "add"
			//JButton btnAddItems = new JButton("Add");

			//In the items tab add a background
			JLabel ImgCostBackItems1 = new JLabel("");
			//In the items tab add a background2
			JLabel imgCostBackground = new JLabel("");
		}

		public void start() {

			//add the panels



			//panel 1
			panel1.add(SummaryMenu);             //table in summary tab
			panel1.add(lblSumCategoryName);       //In summary set the label "Category"
			panel1.add(lblSumCurrency);			//In summary set the label "Currency"
			panel1.add(sumCategoryField);			//In summary This is the text field of category
			panel1.add(currencySumField);			//In summary This is the text field of Currency
			panel1.add(categoryField);			//In summary set this is the field of DD in purchase Date
			panel1.add(SumItemNameField);			//In summary tab this is the field of item name
			panel1.add(lblSumPurchDate);			//In summary tab this is the label "purchase date"
			panel1.add(lblSumItemName);			//In summary tab this is the label of Item name
			panel1.add(sumTotalCostField);			//In summary tab this is the field of total cost
			panel1.add(lblSumTotalCost);			//In summary tab this is the label of total cost
			panel1.add(sumAddButtom);			//in summary tab this is the add button
			panel1.add(sumDeleteButton);			//In summary tab this is the remove buttom

			//panel 2
			panel2.add(cateAddButton);			//Add an "add" buttom in the categories tab
			panel2.add(cateRemoveButton);			//In the categories tab add a buttom named remove
			panel2.add(lblCategoriesName);			//In the categories tab add label "caregory name"
			panel2.add(categoriesField);			//In the categories tab add fireld for categories
			panel2.add(categoriesMenu);             //add the categories table in categories
			panel2.add(imgCostBackgroundCate);			//Add background in panel 2 (categories)
			panel2.add(imgCostBackgroundCate2);			//add another background in panel 2

			//panel 3
			panel3.add(txtMm);			//in items tab set field "MM"
			panel3.add(txtYyyy);			//in items tab set field "YYYY"
			panel3.add(addItemField);			//in items tab set add field for item
			panel3.add(lblItemName);			//In items tab add label "item name"
			panel3.add(btnRemoveItems);			//In items tab add button "remove"
			panel3.add(ItemMenu);			//In items tab add table for adding data
			panel3.add(btnAddItems);			//in items tab add the buttom "add"
			panel3.add(lblCategory);			//In the items tab add label "category"
			panel3.add(addCategoryField);			//In the Items tab add field to add categories
			panel3.add(lblPrice);			//In the items tab add label Price
			panel3.add(addPriceField);			//In the items tab add field to add price
			panel3.add(lblCurrency);			//In the Items tab add label "Currency"
			panel3.add(addCurrencyField);			//In the items tab add field to add currency
			panel3.add(purchase_Date_DD);			//In the items tab add field to add the DD to purchase date
			panel3.add(lblPurchaseDate);			//in the items tab add label "purchase date"
			panel3.add(purchase_Date_MM);			//In the items tab add field to add the MM to purchase date
			panel3.add(purchase_Date_YYYY);			//In the items tab add field to add the YYYY to purchase date
			panel3.add(ImgCostBackItems1);			//In the items tab add a background
			panel3.add(imgCostBackground);			//In the items tab add a background2

			//general components
			menuBar.add(mnFile);

			//Create a panel
			frame.setForeground(Color.BLACK);
			frame.setBackground(Color.WHITE);
			frame.setBounds(9,9,9,9);
			//frame.setBorder(new EmptyBorder(5, 5, 5, 5));
			//frame.setContentPane(frame); //not sure about this command?
			// contentPane.setLayout(null);

			//Set the tabs of summary,categories,items on TOP
			tabbedPane.setForeground(Color.WHITE);
			tabbedPane.setBounds(0, 0, 1128, 459);

			//Set an icon for the Jframe
			frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sagi\\Desktop\\\u05D0\u05D1\u05DF \u05D3\u05E8\u05DA 2\\CostManagerApp\\img\\CostMangerIcon.png"));
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setBounds(100, 100, 1140, 534);

			//set the menu bar on top
			menuBar.setBackground(Color.WHITE);
			frame.setJMenuBar(menuBar);

			//Add a menu buttom on the left side of the screen
			mnFile.setBackground(Color.WHITE);
			mnFile.setForeground(Color.BLACK);

			//Add the buttom "Exit" in the menu to close the app
			mntmExit.setBackground(SystemColor.WHITE);
			mntmExit.setForeground(Color.BLACK);


			//Add a buttom in the menu to create a pie chart
			mntmCreatePieDiagram.setForeground(Color.BLACK);
			mntmCreatePieDiagram.setBackground(Color.WHITE);


			//Add the buttons to mnFile
			mnFile.add(mntmCreatePieDiagram);
			mnFile.add(mntmExit);

			//Set the summary panel
			panel1.setBackground(SystemColor.activeCaption);
			tabbedPane.addTab("Summary", null, panelMain, null);
			tabbedPane.setFont(new Font("Cooper Black", Font.PLAIN, 16));
			panel1.setLayout(null);

			/*
			 * panel 1 (Summary)
			 */

			//Set the table in Summary tab
			SummaryMenu.setBounds(0, 0, 709, 424);
			SummaryMenu.setViewportView(table);

			//In summary set the label "Category"
			lblSumCategoryName.setOpaque(true);
			lblSumCategoryName.setBackground(Color.WHITE);
			lblSumCategoryName.setForeground(Color.BLACK);
			lblSumCategoryName.setFont(new Font("Cooper Black", Font.PLAIN, 18));
			lblSumCategoryName.setBounds(780, 98, 146, 20);

			//In summary set the label "Currency"
			lblSumCurrency.setOpaque(true);
			lblSumCurrency.setForeground(Color.BLACK);
			lblSumCurrency.setFont(new Font("Cooper Black", Font.PLAIN, 18));
			lblSumCurrency.setBackground(Color.WHITE);
			lblSumCurrency.setBounds(780, 210, 146, 20);

			//In summary This is the text field of category
			sumCategoryField.setEditable(true);
			sumCategoryField.setFont(new Font("Cooper Black", Font.PLAIN, 13));
			sumCategoryField.setBounds(958, 96, 146, 26);
			sumCategoryField.setColumns(10);

			//In summary This is the text field of Currency
			currencySumField.setFont(new Font("Cooper Black", Font.PLAIN, 13));
			currencySumField.setColumns(10);
			currencySumField.setBounds(958, 209, 146, 26);

			//In summary set this is the field of DD in purchase Date
			categoryField.setText("  DD");
			categoryField.setFont(new Font("Cooper Black", Font.PLAIN, 13));
			categoryField.setColumns(10);
			categoryField.setBounds(959, 165, 39, 26);

			//In summary tab this is the field of item name
			SumItemNameField.setEditable(true);
			SumItemNameField.setFont(new Font("Cooper Black", Font.PLAIN, 13));
			SumItemNameField.setColumns(10);
			SumItemNameField.setBounds(959, 130, 146, 26);

			//In summary tab this is the label "purchase date"
			lblSumPurchDate.setOpaque(true);
			lblSumPurchDate.setBackground(Color.WHITE);
			lblSumPurchDate.setForeground(Color.BLACK);
			lblSumPurchDate.setFont(new Font("Cooper Black", Font.PLAIN, 18));
			lblSumPurchDate.setBounds(780, 168, 146, 20);

			//In summary tab this is the label of Item name
			lblSumItemName.setOpaque(true);
			lblSumItemName.setBackground(Color.WHITE);
			lblSumItemName.setForeground(Color.BLACK);
			lblSumItemName.setFont(new Font("Cooper Black", Font.PLAIN, 18));
			lblSumItemName.setBounds(780, 133, 146, 20);

			//In summary tab this is the field of total cost
			sumTotalCostField.setEditable(false);
			sumTotalCostField.setFont(new Font("Cooper Black", Font.PLAIN, 13));
			sumTotalCostField.setColumns(10);
			sumTotalCostField.setBounds(958, 310, 146, 26);

			//In summary tab this is the label of total cost
			lblSumTotalCost.setOpaque(true);
			lblSumTotalCost.setBackground(Color.WHITE);
			lblSumTotalCost.setForeground(Color.BLACK);
			lblSumTotalCost.setFont(new Font("Cooper Black", Font.PLAIN, 18));
			lblSumTotalCost.setBounds(780, 313, 146, 20);

			//in summary tab this is the add button
			sumAddButtom.setFont(new Font("Cooper Black", Font.PLAIN, 20));
			sumAddButtom.setForeground(new Color(255, 255, 255));
			sumAddButtom.setBounds(796, 387, 115, 29);
			sumAddButtom.setOpaque(false);
			sumAddButtom.setContentAreaFilled(false);

			//In summary tab this is the remove buttom
			sumDeleteButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
			sumDeleteButton.setForeground(new Color(255, 255, 255));
			sumDeleteButton.addActionListener(new ActionListener() { //Delete item
				public void actionPerformed(ActionEvent arg0) {
					try {
						//This will remove an item
						System.out.println("Removed an item");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			sumDeleteButton.setBounds(958, 387, 127, 29);
			sumDeleteButton.setOpaque(false);
			sumDeleteButton.setContentAreaFilled(false);

			/*
			 * panel 2 (Categories)
			 */


			//set the name of tab 2 to categories
			panel2.setBackground(SystemColor.activeCaption);
			tabbedPane.addTab("Categories", null, panel2, null);
			panel2.setLayout(null);

			//Add an "add" buttom in the categories tab
			cateAddButton.setForeground(Color.BLACK);
			cateAddButton.setFont(new Font("Cooper Black", Font.PLAIN, 30));
			cateAddButton.setActionCommand("AddButton2");
			cateAddButton.setBounds(754, 265, 146, 29);
			cateAddButton.addActionListener(new ActionListener() { //Actually add category to app
				public void actionPerformed(ActionEvent arg0) {
					try {
						System.out.println("Added an category");
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			cateAddButton.setOpaque(false);
			cateAddButton.setContentAreaFilled(false);
			cateAddButton.setBorderPainted(false);

			//In the categories tab add a buttom named remove
			cateRemoveButton.setForeground(Color.BLACK);
			cateRemoveButton.setFont(new Font("Cooper Black", Font.PLAIN, 30));
			cateRemoveButton.setActionCommand("RemoveButton2");
			cateRemoveButton.setBounds(916, 265, 165, 29);
			cateRemoveButton.addActionListener(new ActionListener() { //Remove category from app
				public void actionPerformed(ActionEvent arg0) {
					try {
						System.out.println("Removed a category.");
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			cateRemoveButton.setOpaque(false);
			cateRemoveButton.setContentAreaFilled(false);
			cateRemoveButton.setBorderPainted(false);

			//In the categories tab add label "caregory name"
			lblCategoriesName.setBackground(Color.WHITE);
			lblCategoriesName.setOpaque(true);
			lblCategoriesName.setForeground(Color.BLACK);
			lblCategoriesName.setBounds(767, 93, 165, 20);
			lblCategoriesName.setFont(new Font("Cooper Black", Font.PLAIN, 20));

			//In the categories tab add fireld for categories
			categoriesField.setBackground(SystemColor.text);
			categoriesField.setBounds(958, 89, 146, 26);
			categoriesField.setFont(new Font("Cooper Black", Font.PLAIN, 18));
			categoriesField.setEditable(true);
			categoriesField.setColumns(10);

			//Add the table box in categories tab to fill the table
			categoriesMenu.setBounds(0, 0, 709, 424);
			categoriesMenu.setViewportView(table2);

			//Add background in panel 2 (categories)
			imgCostBackgroundCate.setVerticalTextPosition(SwingConstants.BOTTOM);
			imgCostBackgroundCate.setVerticalAlignment(SwingConstants.BOTTOM);
			imgCostBackgroundCate.setLocation(new Point(50, 50));
			imgCostBackgroundCate.setHorizontalTextPosition(SwingConstants.CENTER);
			imgCostBackgroundCate.setFont(new Font("Cooper Black", Font.PLAIN, 19));
			imgCostBackgroundCate.setAlignmentX(0.5f);
			imgCostBackgroundCate.setBounds(372, -23, 1472, 549);
			imgCostBackgroundCate.setIcon(new ImageIcon("C:\\Users\\Sagi\\Desktop\\\u05D0\u05D1\u05DF \u05D3\u05E8\u05DA 2\\CostManagerApp\\img\\MoneyBackground.png"));

			//add another background in panel 2
			imgCostBackgroundCate2.setVerticalTextPosition(SwingConstants.BOTTOM);
			imgCostBackgroundCate2.setVerticalAlignment(SwingConstants.BOTTOM);
			imgCostBackgroundCate2.setLocation(new Point(50, 50));
			imgCostBackgroundCate2.setHorizontalTextPosition(SwingConstants.CENTER);
			imgCostBackgroundCate2.setFont(new Font("Cooper Black", Font.PLAIN, 19));
			imgCostBackgroundCate2.setAlignmentX(0.5f);
			imgCostBackgroundCate2.setBounds(-95, 0, 1531, 549);
			imgCostBackgroundCate2.setIcon(new ImageIcon("C:\\Users\\Sagi\\Desktop\\\u05D0\u05D1\u05DF \u05D3\u05E8\u05DA 2\\CostManagerApp\\img\\MoneyBackground.png"));

			/*
			 * panel 3 (Items)
			 */

			//Define panel 3, Items
			panel3.setBackground(Color.WHITE);
			tabbedPane.addTab("Items", null, panel3, null);
			panel3.setLayout(null);

			//in items tab set field "MM"
			txtMm.setText("  MM");
			txtMm.setFont(new Font("Cooper Black", Font.PLAIN, 13));
			txtMm.setColumns(10);
			txtMm.setBounds(1008, 165, 39, 26);

			//in items tab set field "YYYY"
			txtYyyy.setText(" YYYY");
			txtYyyy.setFont(new Font("Cooper Black", Font.PLAIN, 13));
			txtYyyy.setColumns(10);
			txtYyyy.setBounds(1057, 165, 47, 26);

			//in items tab set add field for item
			addItemField.setFont(new Font("Cooper Black", addItemField.getFont().getStyle(), addItemField.getFont().getSize() + 4));
			addItemField.setBounds(936, 42, 154, 35);
			addItemField.setColumns(10);

			//In items tab add label "item name"
			lblItemName.setBackground(Color.WHITE);
			lblItemName.setOpaque(true);
			lblItemName.setForeground(Color.BLACK);
			lblItemName.setFont(new Font("Cooper Black", Font.PLAIN, 20));
			lblItemName.setBounds(744, 46, 125, 20);

			//In items tab add buttom "remove"
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

			//In items tab add table for adding data
			ItemMenu.setBounds(0, 0, 709, 424);
			table3 = new JTable();
			ItemMenu.setViewportView(table3);

			//in items tab add the buttom "add"
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

			//In the items tab add label "category"
			lblCategory.setOpaque(true);
			lblCategory.setForeground(Color.BLACK);
			lblCategory.setFont(new Font("Cooper Black", Font.PLAIN, 20));
			lblCategory.setBackground(Color.WHITE);
			lblCategory.setBounds(744, 103, 125, 20);

			//In the Items tab add field to add categories
			addCategoryField.setColumns(10);
			addCategoryField.setBounds(936, 99, 154, 35);

			//In the items tab add label Price
			lblPrice.setOpaque(true);
			lblPrice.setForeground(Color.BLACK);
			lblPrice.setFont(new Font("Cooper Black", Font.PLAIN, 20));
			lblPrice.setBackground(Color.WHITE);
			lblPrice.setBounds(744, 159, 125, 20);

			//In the items tab add field to add price
			addPriceField.setColumns(10);
			addPriceField.setBounds(936, 155, 154, 35);

			//In the Items tab add label "Currency"
			lblCurrency.setOpaque(true);
			lblCurrency.setForeground(Color.BLACK);
			lblCurrency.setFont(new Font("Cooper Black", Font.PLAIN, 20));
			lblCurrency.setBackground(Color.WHITE);
			lblCurrency.setBounds(744, 216, 125, 20);

			//In the items tab add field to add currency
			addCurrencyField.setColumns(10);
			addCurrencyField.setBounds(936, 212, 154, 35);
			addCurrencyField.setText("EURO/ILS/GBP/USD");

			//In the items tab add field to add the DD to purchase date
			purchase_Date_DD.setText("  DD");
			purchase_Date_DD.setColumns(10);
			purchase_Date_DD.setBounds(936, 262, 39, 35);

			//in the items tab add label "purchase date"
			lblPurchaseDate.setOpaque(true);
			lblPurchaseDate.setForeground(Color.BLACK);
			lblPurchaseDate.setFont(new Font("Cooper Black", Font.PLAIN, 20));
			lblPurchaseDate.setBackground(Color.WHITE);
			lblPurchaseDate.setBounds(719, 277, 154, 20);

			//In the items tab add field to add the MM to purchase date
			purchase_Date_MM.setText("  MM");
			purchase_Date_MM.setColumns(10);
			purchase_Date_MM.setBounds(985, 262, 39, 35);

			//In the items tab add field to add the YYYY to purchase date
			purchase_Date_YYYY.setText("  YYYY");
			purchase_Date_YYYY.setColumns(10);
			purchase_Date_YYYY.setBounds(1034, 262, 39, 35);

			//In the items tab add a background
			ImgCostBackItems1.setVerticalTextPosition(SwingConstants.BOTTOM);
			ImgCostBackItems1.setVerticalAlignment(SwingConstants.BOTTOM);
			ImgCostBackItems1.setLocation(new Point(50, 50));
			ImgCostBackItems1.setHorizontalTextPosition(SwingConstants.CENTER);
			ImgCostBackItems1.setFont(new Font("Cooper Black", Font.PLAIN, 19));
			ImgCostBackItems1.setAlignmentX(0.5f);
			ImgCostBackItems1.setBounds(391, -31, 1472, 549);
			ImgCostBackItems1.setIcon(new ImageIcon("C:\\Users\\Sagi\\Desktop\\\u05D0\u05D1\u05DF \u05D3\u05E8\u05DA 2\\CostManagerApp\\img\\MoneyBackground.png"));

			//In the items tab add a background2
			imgCostBackground.setVerticalTextPosition(SwingConstants.BOTTOM);
			imgCostBackground.setVerticalAlignment(SwingConstants.BOTTOM);
			imgCostBackground.setLocation(new Point(50, 50));
			imgCostBackground.setHorizontalTextPosition(SwingConstants.CENTER);
			imgCostBackground.setFont(new Font("Cooper Black", Font.PLAIN, 19));
			imgCostBackground.setAlignmentX(0.5f);
			imgCostBackground.setBounds(382, -29, 1472, 549);
			imgCostBackground.setIcon(new ImageIcon("C:\\Users\\Sagi\\Desktop\\\u05D0\u05D1\u05DF \u05D3\u05E8\u05DA 2\\CostManagerApp\\img\\MoneyBackground.png"));


			//setting the window layout manager
			frame.setLayout(new BorderLayout());

			//adding the two panels to the main panel
			panelMain.add(panel1, BorderLayout.NORTH);
			panelMain.add(panel2, BorderLayout.CENTER);
			panelMain.add(panel3, BorderLayout.CENTER);

			//adding tabs area panel
			frame.add(tabbedPane);
			//adding the main panel to the window
			frame.add(panelMain, BorderLayout.CENTER);

			//adding top panel to the window
			frame.add(panel1, BorderLayout.NORTH);

			//adding top panel to the window
			frame.add(panel2, BorderLayout.WEST);

			//adding the message panel to the window
			frame.add(panel3, BorderLayout.EAST);

			//handling window closing
			frame.addWindowListener(new WindowAdapter() {
				/**
				 * Invoked when a window is in the process of being closed.
				 * The close operation can be overridden at this point.
				 *
				 * @param e
				 */
				@Override
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});

			//handling closing tab from menu "Exit"
			mntmExit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.exit(JFrame.EXIT_ON_CLOSE);
				}
			});
			//handling the button createPieChart in menu
			mntmCreatePieDiagram.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//Open the pie chart window
					PieChart demo = new PieChart("CostManager Pie Chart", "Pie chart from dates: 11/12/2020 - 22/12/2020");
					demo.pack();
					demo.setVisible(true);
					System.out.println("Creates a pie chart.");
				}});
			//handling cost item adding button click on items tab
			sumAddButtom.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						//get name
						String itemName = addItemField.getText();
						if(itemName==null || itemName.length()==0) {
							throw new CostManagerException("Description cannot be empty");
						}
						//generate itemID
						int itemID = 0;
						//get price
						double sum = Double.parseDouble(sumTotalCostField.getText());
						String currencyStr = addPriceField.getText();
						//get currency
						Currency currency = null;
						switch (currencyStr) {
							case "EURO":
								currency = Currency.EURO;
								break;
							case "ILS":
								currency = Currency.ILS;
								break;
							case "GBP":
								currency = Currency.GBP;
								break;
							default:
								currency = Currency.USD;

						}
						//get category name
						String categoryName = addCategoryField.getText();
						//get category ID
						if(categoryName==null || categoryName.length()==0) {
							throw new CostManagerException("Category name cannot be empty");
						}
						int cateID = 0;
						//add purchase date
						String purchaseDate = purchase_Date_DD.getText() + "." + purchase_Date_MM.getText()
								+ "." + purchase_Date_YYYY.getText();
						CostItem item = new CostItem(itemID, cateID, itemName, currency,
								sum, purchaseDate);
						vm.addCostItem(item);

					} catch (NumberFormatException ex) {
						View.this.showMessage("problem with entered sum... "+ex.getMessage());
					} catch(CostManagerException ex){
						View.this.showMessage("problem with entered data... problem with description... "+ex.getMessage());
					}
				}
			});

			//displaying the window
			frame.setSize(1200, 600);
			frame.setVisible(true);
		}

		public void showMessage(String text) {
			if (SwingUtilities.isEventDispatchThread()) {

				addItemField.setText(text);
			} else {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						addItemField.setText(text);
					}
				});

			}
			//@Override
			//public void setViewModel(IViewModel vm) {
			//
			//}
		}

		public void showItems(CostItem[] items) {
			StringBuilder sb = new StringBuilder();
			for(CostItem item : items) {
				sb.append(item.toString());
				sb.append("\n");
			}
			String text = sb.toString();

			if (SwingUtilities.isEventDispatchThread()) {
				addItemField.setText(text);
			} else {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						addItemField.setText(text);
					}
				});

			}





		}
	}
}