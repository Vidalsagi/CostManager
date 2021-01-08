package il.ac.hit.costManager.view;

import il.ac.hit.costManager.model.Category;
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
import java.text.ParseException;
import java.util.List;

public class View implements IView {

    private IViewModel vm;
    private ApplicationUI ui;

    @Override
    public void setViewModel(IViewModel vm) {
        this.vm = vm;
    }

    @Override
    public void showMessageCate(String text) {
        ui.showMessageCate(text);
    }

    @Override
    public void showMessage(String text) {
        ui.showMessage(text);
    }

    @Override
    public void showReportItems (List<CostItem> itemsReport){ui.showReportItems(itemsReport); };


    @Override
    public void showItems(CostItem[] items) {
        ui.showItems(items);
    }

    @Override
    public void showCategories(Category[] categories) {
        ui.showCategories(categories);
    }

    //this will recieve the selected dates from action listener and will call the vm handleReport
    @Override
    public void showReport(String dateFrom , String dateTo) throws ParseException, CostManagerException {
    }


    @Override
    public void showMessageItemsLoaded(String message) {
        ui.showMessageItemsLoaded(message);
    }

    public View() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View.this.ui = new ApplicationUI();
                View.this.ui.start();
                View.this.vm.loadItems();

            }
        });
    }

    public class ApplicationUI //implements IView
    {

        /**
         * Set General components
         */
        private JFrame frame;

        private JPanel panelMain;
        private JPanel panelCate;
        private JPanel panelItem;
        private JPanel panelReport;
        private JPanel panelMessage;

        private JMenuBar menuBar;


        private JMenu mnFile;
        private JMenuItem mntmExit;
        private JMenuItem mntmCreatePieDiagram;

        private JTabbedPane tabbedPane;

        /**
         * panel Categories
         */
        private JScrollPane categoriesMenu;
        private JTextArea textAreaCate;
        private JTextField tfCategory;
        private JTextField categoryField;
        private JLabel lblCategoryName;
        private JLabel imgCateBackground1;
        private JLabel imgCateBackground2;
        private JButton btnAddCategory;
        private JButton btnDeleteCategory;


        /**
         * panel Report
         */
        private JTextArea textAreaReport;
        private JTextField tfSumTotalReport;
        private JButton btnGenerateReport;
        private JLabel lblSetDatesReport;
        private JScrollPane spReport;
        private JLabel lblFromReport;
        private JLabel lblToReport;
        private JComboBox cbDDFromReport;
        private JComboBox cbMMFromReport;
        private JComboBox cbYYYYFromReport;
        private JComboBox cbDDToReport;
        private JComboBox cbMMToReport;
        private JComboBox cbYYYYToReport;
        private JLabel lblTotalCost;

        /**
         * panel Items
         */

        private JTextArea textAreaItems;
        private JScrollPane itemMenu;
        private JLabel lblItemName;
        private JLabel lblCategory;
        private JLabel lblPrice;
        private JLabel lblCurrency;
        private JLabel lblPurchaseDate;
        private JLabel imgCostBackItems1;
        private JLabel imgCostBackground;
        private JButton btnRemoveItems;
        private JButton btnAddItems;
        private JTextField tfItemName;
        private JTextField tfItemCateName;
        private JTextField tfItemPrice;
        private JTextField tfItemCurrency;
        private JComboBox cbDDItems;
        private JComboBox cbMMItems;
        private JComboBox cbYYYYItems;
        private JComboBox comboCurrencyOptions;
        private JComboBox cateItemsMenuCombo;

        /**
         * panel Messages
         */
        private JLabel lbMessage;
        private JTextField tfMessage;


        public ApplicationUI() {
            //creating the window
            frame = new JFrame("CostManager");
            //create the main panel
            panelMain = new JPanel();
            //Set the Categories panel
            panelCate = new JPanel();
            //set the items panel
            panelItem = new JPanel();
            //set the report panel
            panelReport = new JPanel();

            //set messages panel
            panelMessage = new JPanel();
            //Set the tabs of report,categories,items on TOP
            tabbedPane = new JTabbedPane(JTabbedPane.TOP);

            //create the Jmenu
            menuBar = new JMenuBar();
            //add menu button on top
            mnFile = new JMenu("Menu");
            //add menu the buttom "exit"
            mntmExit = new JMenuItem("Exit");
            //Add a buttom in the menu to create a pie chart
            mntmCreatePieDiagram = new JMenuItem("Create Pie Diagram.");


            //create three panels
            /* PanelCate related (Categories) */

            //Set the table in categories tab
            textAreaCate = new JTextArea();
            categoriesMenu = new JScrollPane(textAreaCate);
            //tableCate = new JTable();

            //In Categories set the label "Category"
            lblCategoryName = new JLabel("Category");
            //In Categories This is the text field of category
            tfCategory = new JTextField();

            //in categories tab this is the add button
            btnAddCategory = new JButton("Add");
            //In categories tab this is the remove buttom
            btnDeleteCategory = new JButton("Remove");

            //In the categories tab add a background
            imgCateBackground1 = new JLabel("");
            //In the categories tab add a background2
            imgCateBackground2 = new JLabel("");

            /* PanelItem related (Items) */
            //tableItem = new JTable();
            //In items tab add table for adding data

            textAreaItems = new JTextArea();
            itemMenu = new JScrollPane(textAreaItems);

            //In the items tab add field to add the DD to purchase date
            cbDDItems = new JComboBox();
            //purchase_Date_DD = new JTextField();
            //in items tab set field "MM"
            //txtMm = new JTextField();
            //In the items tab add field to add the MM to purchase date
            cbMMItems = new JComboBox();
            //purchase_Date_MM = new JTextField();
            //in items tab set field "YYYY"
            //txtYyyy = new JTextField();
            //In the items tab add field to add the YYYY to purchase date
            cbYYYYItems = new JComboBox();
            //purchase_Date_YYYY = new JTextField();
            //in items tab set add field for item
            tfItemName = new JTextField();
            //In the Items tab add field to add categories
            tfItemCateName = new JTextField();
            //In the items tab add field to add price
            tfItemPrice = new JTextField();
            //In the items tab add field to add currency
            tfItemCurrency = new JTextField();

            //In items tab add label "item name"
            lblItemName = new JLabel("Item Name");
            //In the items tab add label "category"
            lblCategory = new JLabel("Category");
            //In the items tab add label Price
            lblPrice = new JLabel("Price\r\n");
            //In the Items tab add label "Currency"
            lblCurrency = new JLabel("Currency");
            //in the items tab add label "purchase date"
            lblPurchaseDate = new JLabel("Purchase Date");

            //In items tab add buttom "remove"
            btnRemoveItems = new JButton("Remove");
            //in items tab add the buttom "add"
            btnAddItems = new JButton("Add");
            //In the items tab add a background
            imgCostBackItems1 = new JLabel("");
            //In the items tab add a background2
            imgCostBackground = new JLabel("");
            comboCurrencyOptions = new JComboBox();
            cateItemsMenuCombo = new JComboBox();

            /* PanelCate related (Report) */
            textAreaReport = new JTextArea();
            lblSetDatesReport = new JLabel("Set dates range to print the report");
            spReport = new JScrollPane();
            lblFromReport = new JLabel("From:");
            lblToReport = new JLabel("To:");
            btnGenerateReport = new JButton("Generate Report");
            cbDDFromReport = new JComboBox();
            cbMMFromReport = new JComboBox();
            cbYYYYFromReport = new JComboBox();
            cbDDToReport = new JComboBox();
            cbMMToReport = new JComboBox();
            cbYYYYToReport = new JComboBox();
            tfSumTotalReport = new JTextField();
            lblTotalCost = new JLabel("Total Cost:");

            /*PanelMessage related */
            lbMessage = new JLabel("Message:");
            tfMessage = new JTextField(30);

        }

        public void start() {

            //add the panels
            //panel Categories
            panelCate.add(categoriesMenu);              //table in summary tab
            panelCate.add(lblCategoryName);             //In Categories set the label "Category"
            panelCate.add(tfCategory);                    //In Categories This is the text field of category
            panelCate.add(btnAddCategory);                //in Categories tab this is the add button
            panelCate.add(btnDeleteCategory);            //In Categories tab this is the remove buttom
            panelCate.add(imgCateBackground1);            //In the Categories tab add a background
            panelCate.add(imgCateBackground2);            //In the Categories tab add a background2
            panelMain.setLayout(new BorderLayout());

            //panel Items
            panelItem.add(tfItemName);            //in items tab set add field for item
            panelItem.add(lblItemName);            //In items tab add label "item name"
            panelItem.add(btnRemoveItems);        //In items tab add button "remove"
            panelItem.add(itemMenu);            //In items tab add table for adding data
            panelItem.add(btnAddItems);            //in items tab add the buttom "add"
            panelItem.add(lblCategory);            //In the items tab add label "category"
            panelItem.add(tfItemCateName);        //In the Items tab add field to add categories
            panelItem.add(lblPrice);              //In the items tab add label Price
            panelItem.add(tfItemPrice);            //In the items tab add field to add price
            panelItem.add(lblCurrency);            //In the Items tab add label "Currency"
            panelItem.add(tfItemCurrency);        //In the items tab add field to add currency
            panelItem.add(cbMMItems);               //In the items tab add field to add the MM to purchase date
            panelItem.add(cbDDItems);             //In the items tab add field to add the DD to purchase date
            panelItem.add(lblPurchaseDate);        //in the items tab add label "purchase date"
            panelItem.add(cbYYYYItems);              //In the items tab add field to add the YYYY to purchase date
            panelItem.add(comboCurrencyOptions);
            panelItem.add(cateItemsMenuCombo);
            panelItem.add(imgCostBackItems1);            //In the items tab add a background
            panelItem.add(imgCostBackground);            //In the items tab add a background2

            //panel Report
            panelReport.add(textAreaReport);
            panelReport.add(lblSetDatesReport);
            panelReport.add(spReport);
            panelReport.add(lblFromReport);
            panelReport.add(lblToReport);
            panelReport.add(btnGenerateReport);
            panelReport.add(cbDDFromReport);
            panelReport.add(cbMMFromReport);
            panelReport.add(cbYYYYFromReport);
            panelReport.add(cbDDToReport);
            panelReport.add(cbMMToReport);
            panelReport.add(cbYYYYToReport);
            panelReport.add(tfSumTotalReport);
            panelReport.add(lblTotalCost);

            //messages panel
            panelMessage.add(lbMessage);
            panelMessage.add(tfMessage);

            //setting a different color for the panel message
            panelMessage.setBackground(Color.GREEN);


            //general components
            menuBar.add(mnFile);

            //Create a panel
            frame.setForeground(Color.BLUE);
            frame.setBackground(Color.BLACK);
            frame.setBounds(9, 9, 3, 12);
            frame.setContentPane(panelMain);

            //Set the tabs of items ,categories,items on TOP
            tabbedPane.setForeground(Color.BLACK);
            tabbedPane.setBounds(0, 0, 1128, 459);
            switch (tabbedPane.getSelectedIndex()) {
                case 0:
                    tabbedPane.setPreferredSize(new Dimension(1, 550));
                    break;
                case 1:
                    tabbedPane.setPreferredSize(new Dimension(1, 330));
                    break;
            }

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

            //Set the Categories panel
            panelCate.setBackground(SystemColor.activeCaption);
            tabbedPane.addTab("Categories", null, panelCate, null);
            tabbedPane.setFont(new Font("Cooper Black", Font.PLAIN, 16));
            panelCate.setLayout(null);

            /**
             * panelCategories
             **/

            //Set the table in Categories tab
            categoriesMenu.setBounds(0, 0, 709, 424);
            //summaryMenu.setViewportView(table1);
            categoriesMenu.setViewportView(textAreaCate);

            //In Categories set the label "Category"
            lblCategoryName.setOpaque(true);
            lblCategoryName.setBackground(Color.WHITE);
            lblCategoryName.setForeground(Color.BLACK);
            lblCategoryName.setFont(new Font("Cooper Black", Font.PLAIN, 18));
            lblCategoryName.setBounds(780, 98, 146, 20);

            //In Categories This is the text field of category
            tfCategory.setEditable(true);
            tfCategory.setFont(new Font("Cooper Black", Font.PLAIN, 13));
            tfCategory.setBounds(958, 96, 146, 26);
            tfCategory.setColumns(10);


            //in Categories tab this is the add button
            btnAddCategory.setFont(new Font("Cooper Black", Font.PLAIN, 30));
            btnAddCategory.setForeground(new Color(0, 0, 0));
            btnAddCategory.setBounds(743, 343, 157, 29);
            btnAddCategory.setOpaque(false);
            btnAddCategory.setContentAreaFilled(false);

            //In Categories tab this is the remove buttom
            btnDeleteCategory.setFont(new Font("Cooper Black", Font.PLAIN, 30));
            btnDeleteCategory.setForeground(new Color(0, 0, 0));
            btnDeleteCategory.setBounds(933, 343, 157, 29);
            btnDeleteCategory.setOpaque(false);
            btnDeleteCategory.setContentAreaFilled(false);


            //In the categories tab add a background
            imgCateBackground1.setVerticalTextPosition(SwingConstants.BOTTOM);
            imgCateBackground1.setVerticalAlignment(SwingConstants.BOTTOM);
            imgCateBackground1.setLocation(new Point(50, 50));
            imgCateBackground1.setHorizontalTextPosition(SwingConstants.CENTER);
            imgCateBackground1.setFont(new Font("Cooper Black", Font.PLAIN, 19));
            imgCateBackground1.setAlignmentX(0.5f);
            imgCateBackground1.setBounds(391, -31, 1472, 549);
            imgCateBackground1.setIcon(new ImageIcon("C:\\Users\\Sagi\\Desktop\\\u05D0\u05D1\u05DF \u05D3\u05E8\u05DA 2\\CostManagerApp\\img\\MoneyBackground.png"));

            //In the categories tab add a background2
            imgCateBackground2.setVerticalTextPosition(SwingConstants.BOTTOM);
            imgCateBackground2.setVerticalAlignment(SwingConstants.BOTTOM);
            imgCateBackground2.setLocation(new Point(50, 50));
            imgCateBackground2.setHorizontalTextPosition(SwingConstants.CENTER);
            imgCateBackground2.setFont(new Font("Cooper Black", Font.PLAIN, 19));
            imgCateBackground2.setAlignmentX(0.5f);
            imgCateBackground2.setBounds(-100, -29, 1472, 549);
            imgCateBackground2.setIcon(new ImageIcon("C:\\Users\\Sagi\\Desktop\\\u05D0\u05D1\u05DF \u05D3\u05E8\u05DA 2\\CostManagerApp\\img\\MoneyBackground.png"));


            /**
             * panelItems
             **/

            //Define panel 2, Items
            panelItem.setBackground(Color.WHITE);
            tabbedPane.addTab("Items", null, panelItem, null);
            panelItem.setLayout(null);

            //in items tab set add field for item
            tfItemName.setFont(new Font("Cooper Black", tfItemName.getFont().getStyle(), tfItemName.getFont().getSize() + 4));
            tfItemName.setBounds(936, 42, 154, 35);
            tfItemName.setColumns(10);

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
            btnRemoveItems.setForeground(new Color(0, 0, 0));
            btnRemoveItems.setBounds(933, 343, 157, 29);
            btnRemoveItems.setBorderPainted(false);

            //In items tab add table for adding data
            itemMenu.setBounds(0, 0, 709, 424);
            //itemMenu.setViewportView(table3);
            itemMenu.setViewportView(textAreaItems);

            //in items tab add the buttom "add"
            btnAddItems.setFont(new Font("Cooper Black", Font.PLAIN, 30));
            btnAddItems.setDefaultCapable(false);
            btnAddItems.setContentAreaFilled(false);
            btnAddItems.setBorderPainted(false);
            btnAddItems.setBackground(Color.BLACK);
            btnAddItems.setForeground(new Color(0, 0, 0));
            btnAddItems.setActionCommand("hidenRefreshButton3");
            btnAddItems.setBounds(743, 343, 157, 29);

            //In the items tab add label "category"
            lblCategory.setOpaque(true);
            lblCategory.setForeground(Color.BLACK);
            lblCategory.setFont(new Font("Cooper Black", Font.PLAIN, 20));
            lblCategory.setBackground(Color.WHITE);
            lblCategory.setBounds(744, 103, 125, 20);


            //In the items tab add label Price
            lblPrice.setOpaque(true);
            lblPrice.setForeground(Color.BLACK);
            lblPrice.setFont(new Font("Cooper Black", Font.PLAIN, 20));
            lblPrice.setBackground(Color.WHITE);
            lblPrice.setBounds(744, 159, 125, 20);

            //In the items tab add field to add price
            tfItemPrice.setColumns(10);
            tfItemPrice.setBounds(936, 155, 154, 35);

            //In the Items tab add label "Currency"
            lblCurrency.setOpaque(true);
            lblCurrency.setForeground(Color.BLACK);
            lblCurrency.setFont(new Font("Cooper Black", Font.PLAIN, 20));
            lblCurrency.setBackground(Color.WHITE);
            lblCurrency.setBounds(744, 216, 125, 20);

            //in the items tab add label "purchase date"
            lblPurchaseDate.setOpaque(true);
            lblPurchaseDate.setForeground(Color.BLACK);
            lblPurchaseDate.setFont(new Font("Cooper Black", Font.PLAIN, 20));
            lblPurchaseDate.setBackground(Color.WHITE);
            lblPurchaseDate.setBounds(719, 277, 154, 20);

            cbDDItems.setForeground(Color.BLACK);
            cbDDItems.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
            cbDDItems.setSelectedIndex(0);
            cbDDItems.setBackground(Color.WHITE);
            cbDDItems.setBounds(936, 262, 39, 35);


            cbMMItems.setForeground(Color.BLACK);
            cbMMItems.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
            cbMMItems.setSelectedIndex(0);
            cbDDItems.setBackground(Color.WHITE);
            cbMMItems.setBounds(985, 262, 39, 35);

            cbYYYYItems.setForeground(Color.BLACK);
            cbYYYYItems.setModel(new DefaultComboBoxModel(new String[] {"1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021"}));
            cbYYYYItems.setSelectedIndex(0);
            cbYYYYItems.setBackground(Color.WHITE);
            cbYYYYItems.setBounds(1034, 262, 75, 35);

            //add in items tab currency combo box
            comboCurrencyOptions.setForeground(Color.BLACK);
            comboCurrencyOptions.setModel(new DefaultComboBoxModel(new String[]{"USD", "ILS", "EUR", "GBP"}));
            comboCurrencyOptions.setSelectedIndex(0);
            comboCurrencyOptions.setBackground(Color.WHITE);
            comboCurrencyOptions.setBounds(936, 211, 137, 29);

            //add in items the name of category
            tfItemCateName.setForeground(Color.BLACK);
            tfItemCateName.setBackground(Color.WHITE);
            tfItemCateName.setBounds(936, 106, 154, 29);

            /*
			//add in items tab Categories comboBox
			cateItemsMenuCombo.setForeground(Color.BLACK);
			cateItemsMenuCombo.setBackground(Color.WHITE);
			cateItemsMenuCombo.setBounds(936, 106, 154, 29);
             */

            //In the items tab add a background
            imgCostBackItems1.setVerticalTextPosition(SwingConstants.BOTTOM);
            imgCostBackItems1.setVerticalAlignment(SwingConstants.BOTTOM);
            imgCostBackItems1.setLocation(new Point(50, 50));
            imgCostBackItems1.setHorizontalTextPosition(SwingConstants.CENTER);
            imgCostBackItems1.setFont(new Font("Cooper Black", Font.PLAIN, 19));
            imgCostBackItems1.setAlignmentX(0.5f);
            imgCostBackItems1.setBounds(391, -31, 1472, 549);
            imgCostBackItems1.setIcon(new ImageIcon("C:\\Users\\Sagi\\Desktop\\\u05D0\u05D1\u05DF \u05D3\u05E8\u05DA 2\\CostManagerApp\\img\\MoneyBackground.png"));

            //In the items tab add a background2
            imgCostBackground.setVerticalTextPosition(SwingConstants.BOTTOM);
            imgCostBackground.setVerticalAlignment(SwingConstants.BOTTOM);
            imgCostBackground.setLocation(new Point(50, 50));
            imgCostBackground.setHorizontalTextPosition(SwingConstants.CENTER);
            imgCostBackground.setFont(new Font("Cooper Black", Font.PLAIN, 19));
            imgCostBackground.setAlignmentX(0.5f);
            imgCostBackground.setBounds(-100, -29, 1472, 549);
            imgCostBackground.setIcon(new ImageIcon("C:\\Users\\Sagi\\Desktop\\\u05D0\u05D1\u05DF \u05D3\u05E8\u05DA 2\\CostManagerApp\\img\\MoneyBackground.png"));


            /**
             * panelReport Properties
             **/
            panelReport.setBackground(Color.WHITE);
            tabbedPane.addTab("Report", null, panelReport, null);
            panelReport.setLayout(null);

            lblSetDatesReport.setForeground(Color.BLACK);
            lblSetDatesReport.setFont(new Font("Cooper Black", Font.PLAIN, 25));
            lblSetDatesReport.setBounds(27, 386, 454, 28);

            spReport.setBounds(27, 32, 760, 351);

            lblFromReport.setForeground(Color.BLACK);
            lblFromReport.setFont(new Font("Cooper Black", Font.PLAIN, 25));
            lblFromReport.setBounds(27, 459, 98, 28);

            lblToReport.setForeground(Color.BLACK);
            lblToReport.setFont(new Font("Cooper Black", Font.PLAIN, 25));
            lblToReport.setBounds(255, 459, 53, 28);

            btnGenerateReport.setFont(new Font("Cooper Black", Font.PLAIN, 26));
            btnGenerateReport.setBounds(490, 461, 297, 28);

            cbDDFromReport.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
            cbDDFromReport.setSelectedIndex(0);
            cbDDFromReport.setBounds(27, 510, 44, 33);

            cbMMFromReport.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
            cbMMFromReport.setSelectedIndex(0);
            cbMMFromReport.setBounds(81, 510, 44, 33);

            cbYYYYFromReport.setModel(new DefaultComboBoxModel(new String[] {"1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021"}));
            cbYYYYFromReport.setSelectedIndex(0);
            cbYYYYFromReport.setBounds(140, 510, 76, 33);

            cbDDToReport.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
            cbDDToReport.setSelectedIndex(0);
            cbDDToReport.setBounds(264, 510, 44, 33);

            cbMMToReport.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
            cbMMToReport.setSelectedIndex(0);
            cbMMToReport.setBounds(318, 510, 44, 33);

            cbYYYYToReport.setModel(new DefaultComboBoxModel(new String[] {"1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021"}));
            cbYYYYToReport.setSelectedIndex(0);
            cbYYYYToReport.setBounds(372, 510, 76, 33);

            tfSumTotalReport = new JTextField();
            tfSumTotalReport.setEditable(false);
            tfSumTotalReport.setBounds(664, 513, 123, 28);
            tfSumTotalReport.setColumns(10);

            //In report tab add table for adding data
            spReport.setBounds(0, 0, 709, 424);
            //itemMenu.setViewportView(table3);
            spReport.setViewportView(textAreaReport);

            lblTotalCost.setForeground(Color.BLACK);
            lblTotalCost.setFont(new Font("Cooper Black", Font.PLAIN, 25));
            lblTotalCost.setBounds(495, 510, 159, 28);

            /**
             * TabbedPane position and layout
             **/

            //setting the window layout manager
            frame.setLayout(new BorderLayout());

            frame.add(tabbedPane, BorderLayout.CENTER);
            frame.add(panelMessage, BorderLayout.SOUTH);


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
                }
            });
            //handling cost item adding button click on items tab
            btnAddItems.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String itemName = tfItemName.getText();
                        if (itemName == null || itemName.length() == 0) {
                            throw new CostManagerException("Item name cannot be empty");
                        }
                        //generate itemID
                        int itemID = 0;
                        //get price
                        double sum = Double.parseDouble(tfItemPrice.getText());
                        String chosenCurrency = comboCurrencyOptions.getSelectedItem().toString();
                        //get currency
                        Currency currency = null;
                        switch (chosenCurrency) {
                            case "EUR":
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
                        String categoryName = tfItemCateName.getText();
                        //get category ID
                        if (categoryName == null || categoryName.length() == 0) {
                            throw new CostManagerException("Category name cannot be empty");
                        }
                        int cateID = 0;
                        //add purchase date
                        String purchaseDate = cbDDItems.getSelectedItem().toString() + "." + cbMMItems.getSelectedItem().toString()
                                + "." + cbYYYYItems.getSelectedItem().toString() ;
                        CostItem item = new CostItem(itemID, cateID, itemName, currency,
                                sum, purchaseDate);
                        vm.addCostItem(item);

                    } catch (NumberFormatException ex) {
                        View.this.showMessage("problem with entered sum... " + ex.getMessage());
                    } catch (CostManagerException ex) {
                        View.this.showMessage("problem with entered data... problem with description... " + ex.getMessage());
                    }
                }
            });
            //handling cost item delete button click on items tab
            btnRemoveItems.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String itemName = tfItemName.getText();
                        if (itemName == null || itemName.length() == 0) {
                            throw new CostManagerException("Item name cannot be empty");
                        }
                        //generate itemID
                        int itemID = 0;
                        //get price
                        double sum = 0;
                        String chosenCurrency = "0";
                        //get currency
                        Currency currency = null;
                        switch (chosenCurrency) {
                            case "EUR":
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
                        String categoryName = tfItemCateName.getText();
                        //get category ID
                        int cateID = 0;
                        //add purchase date
                        String purchaseDate = cbDDItems.getSelectedItem().toString() + "." + cbMMItems.getSelectedItem().toString()
                                + "." + cbYYYYItems.getSelectedItem().toString();
                        CostItem item = new CostItem(itemID, cateID, itemName, currency,
                                sum, purchaseDate);
                        vm.deleteCostItem(item);

                    } catch (CostManagerException ex) {
                        View.this.showMessage("problem with entered data... problem with description... " + ex.getMessage());
                    }
                }
            });
            //handling category adding button click on items tab
            btnAddCategory.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        //get category name
                        String cateName = tfCategory.getText();
                        if (cateName == null || cateName.length() == 0) {
                            throw new CostManagerException("Category name cannot be empty");
                        }
                        //get category ID
                        int cateID = 0;
                        Category category = new Category(cateID, cateName);
                        vm.addCategory(category);

                    } catch (NumberFormatException ex) {
                        View.this.showMessageCate("problem with entered ID... " + ex.getMessage());
                    } catch (CostManagerException ex) {
                        View.this.showMessageCate("problem with entered data... problem with description... " + ex.getMessage());
                    }
                }
            });
            //handling category adding button click on items tab
            btnDeleteCategory.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        //get category name
                        String cateName = tfCategory.getText();
                        if (cateName == null || cateName.length() == 0) {
                            throw new CostManagerException("Category name cannot be empty");
                        }
                        //get category ID
                        int cateID = 0;
                        Category category = new Category(cateID, cateName);
                        vm.deleteCategory(category);

                    } catch (NumberFormatException ex) {
                        View.this.showMessageCate("problem with entered ID... " + ex.getMessage());
                    } catch (CostManagerException ex) {
                        View.this.showMessageCate("problem with entered data... problem with description... " + ex.getMessage());
                    }
                }
            });

            // this will save the selected dates by user, and will save into a string format, and will send to show report
            btnGenerateReport.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String purchaseDateFrom = cbDDFromReport.getSelectedItem().toString() + "." + cbMMFromReport.getSelectedItem().toString()
                            + "." + cbYYYYFromReport.getSelectedItem().toString() ;
                    String purchaseDateTo = cbDDToReport.getSelectedItem().toString() + "." + cbMMToReport.getSelectedItem().toString()
                            + "." + cbYYYYToReport.getSelectedItem().toString() ;
                    try {
                        vm.handleReport(purchaseDateFrom,purchaseDateTo);
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    } catch (CostManagerException costManagerException) {
                        costManagerException.printStackTrace();
                    }
                }

            });

            //displaying the window
            frame.setSize(1200, 600);
            frame.setVisible(true);
        }

        public void showMessage(String text) {
            if (SwingUtilities.isEventDispatchThread()) {
                tfMessage.setText(text);
            } else {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        tfMessage.setText(text);
                    }
                });
            }
            //@Override
            //public void setViewModel(IViewModel vm) {
            //
            //}
        }

        public void showMessageCate(String text) {
            if (SwingUtilities.isEventDispatchThread()) {
                textAreaCate.setText(text);
            } else {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        textAreaCate.setText(text);
                    }

                });

            }
            //@Override
            //public void setViewModel(IViewModel vm) {
            //
            //}
        }


        public void showReportItems(List<CostItem> itemsReport) {
            StringBuilder sb = new StringBuilder();
            for (CostItem item : itemsReport) {
                sb.append(item.toString());
                sb.append("\n");
            }
            String text = sb.toString();

            if (SwingUtilities.isEventDispatchThread()) {

                textAreaReport.setText(text);
            }
            else {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        textAreaReport.setText(text);
                    }
                });

            }
        }


        public void showItems(CostItem[] items) {
            StringBuilder sb = new StringBuilder();
            for (CostItem item : items) {
                sb.append(item.toString());
                sb.append("\n");
            }
            String text = sb.toString();

            if (SwingUtilities.isEventDispatchThread()) {
                textAreaItems.setText(text);
            } else {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        textAreaItems.setText(text);
                    }
                });

            }

        }

        public void showCategories(Category[] categories) {
            StringBuilder sb = new StringBuilder();
            for (Category category : categories) {
                sb.append(category.toString());
                sb.append("\n");
            }
            String text = sb.toString();

            if (SwingUtilities.isEventDispatchThread()) {
                textAreaCate.setText(text);
            } else {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        textAreaCate.setText(text);
                    }
                });

            }

        }

        public void showMessageItemsLoaded(String message) {
            if (SwingUtilities.isEventDispatchThread()) {
                textAreaItems.setText(message);
            } else {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        textAreaItems.setText(message);
                    }

                });
            }
        }
    }
}