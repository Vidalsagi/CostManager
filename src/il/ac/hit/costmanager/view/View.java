package il.ac.hit.costmanager.view;

import il.ac.hit.costmanager.model.Category;
import il.ac.hit.costmanager.model.CostItem;
import il.ac.hit.costmanager.model.CostManagerException;
import il.ac.hit.costmanager.model.Currency;
import il.ac.hit.costmanager.viewmodel.IViewModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class View implements IView {

    private IViewModel vm;
    private ApplicationUI ui;


    @Override
    public void setViewModel(IViewModel vm) {
        this.vm = vm;
    }    //This will connect the view with the viewmodel

    //This will show the report of items in selected dates
    @Override
    public void showReportItems (JTable itemsReportTable){ui.showReportItems(itemsReportTable); };


    //This func is related to piechart
    @Override
    public void getPieChartDataSet(DefaultPieDataset dataset) {
        ui.showPieChart(dataset);
    }

    //This func will display msgs related to errors
    @Override
    public void showMessage(String text) {
        ui.showMessage(text);
    }

    @Override
    public void showItemsTable(JTable itemsTable) {
        ui.showItemsTable(itemsTable);
    }

    @Override
    public void setItemsCateCB(List<Category> categories) {
        ui.loadCateListToCB(categories);
    }

    //This func will display the categories in database
    @Override
    public void showCategoriesTable(JTable cateTable) {
        ui.showCategoriesTable(cateTable);
    }

    public View() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View.this.ui = new ApplicationUI();
                View.this.ui.start();
                View.this.vm.loadItemTable();
                View.this.vm.loadCategoriesTable();
                View.this.vm.setCbItems();
            }
        });
    }

    public class ApplicationUI //implements IView
    {
        /*
          Set General components
         */

        private JFrame frame;             //main frame of the application
        private JPanel panelMain;         //the main panel that we use to set the other panels on
        private JPanel panelCate;         //the panel of categories
        private JPanel panelItem;         //the panel for items
        private JPanel panelReport;       //the panel for report
        private JPanel panelMessage;      //the panel for debug
        private JPanel panelPieMain;      //main panel of the pie chart
        private JPanel panelPieInfo;      //the panel we will use to set the info of piechart
        private JMenuBar menuBar;         //the menu bar on the upper left side
        private JMenu mnFile;
        private JMenuItem mntmExit;       //the exit button on the menu bar
        private JTabbedPane tabbedPane;   //the tabs panel

        /*
         panel Categories
         */

        private JScrollPane categoriesMenu;
        private JTable tableCategories;
        private JTextField tfCategory;
        private JLabel lblCategoryName;
        private JButton btnAddCategory;
        private JButton btnDeleteCategory;

        /*
          panel Report
         */

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

        /*
          panel Messages
         */

        private JLabel lbMessage;
        private JTextField tfMessage;

        /*
          panel Items
         */

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
        private JTextField tfItemPrice;
        private JTextField tfItemCurrency;
        private JComboBox cbDDItems;
        private JComboBox cbMMItems;
        private JComboBox cbYYYYItems;
        private JComboBox comboCurrencyOptions;
        private JComboBox cateItemsMenuCombo;
        private JComboBox cbItemsCate;

        /*
          panel PieChart
         */

        private PieDataset dataset;
        private JFreeChart chart;
        private ChartPanel chartPanel;
        private DefaultPieDataset result;
        private PieChart pieChart;
        private JButton btnGeneratePieChart;
        private JLabel lblSetDatesPieChart;
        private JLabel lblFromPieChart;
        private JLabel lblToPieChart;
        private JComboBox cbDDFromPieChart;
        private JComboBox cbMMFromPieChart;
        private JComboBox cbYYYYFromPieChart;
        private JComboBox cbDDToPieChart;
        private JComboBox cbMMToPieChart;
        private JComboBox cbYYYYToPieChart;

        /*
        Application UI Method
         */

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

            //create three panels
            /* PanelCate related (Categories) */

            //Set the table in categories tab
            tableCategories = new JTable();
            categoriesMenu = new JScrollPane(tableCategories);
            //tableCate = new JTable();

            //In Categories set the label "Category"
            lblCategoryName = new JLabel("Category");
            //In Categories This is the text field of category
            tfCategory = new JTextField();

            //in categories tab this is the add button
            btnAddCategory = new JButton("Add");
            //In categories tab this is the remove buttom
            btnDeleteCategory = new JButton("Remove");


            /* PanelItem related (Items) */
            //In items tab add table for adding data

            //In the items tab add field to add the DD to purchase date
            itemMenu = new JScrollPane();
            cbDDItems = new JComboBox();
            //In the items tab add field to add the MM to purchase date
            cbMMItems = new JComboBox();
            //In the items tab add field to add the YYYY to purchase date
            cbYYYYItems = new JComboBox();
            //create the comboBox in items tab to choose categories from list
            cbItemsCate = new JComboBox();
            //in items tab set add field for item
            tfItemName = new JTextField();
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

            /*PanelMessage related */
            lbMessage = new JLabel("Message:");
            tfMessage = new JTextField(30);

            /* PanelCate related (PieChart) */
            lblSetDatesPieChart = new JLabel("Set dates range" + "\n" + "to print the report:");
            lblFromPieChart = new JLabel("From/");
            lblToPieChart = new JLabel("To:");
            btnGeneratePieChart = new JButton("Generate Pie Chart");
            cbDDFromPieChart = new JComboBox();
            cbMMFromPieChart = new JComboBox();
            cbYYYYFromPieChart = new JComboBox();
            cbDDToPieChart = new JComboBox();
            cbMMToPieChart = new JComboBox();
            cbYYYYToPieChart = new JComboBox();
            panelPieMain = new JPanel();
            panelPieInfo = new JPanel();
            pieChart = new PieChart("CostManager Pie Chart","Costs from dates X to Y");
            dataset =  new DefaultPieDataset();
            result = new DefaultPieDataset();
        }

        public void start() {

            //Add the panels
            //Panel Categories
            panelCate.add(categoriesMenu);              //table in summary tab
            panelCate.add(lblCategoryName);             //In Categories set the label "Category"
            panelCate.add(tfCategory);                    //In Categories This is the text field of category
            panelCate.add(btnAddCategory);                //in Categories tab this is the add button
            panelCate.add(btnDeleteCategory);            //In Categories tab this is the remove buttom
            panelMain.setLayout(new BorderLayout());

            //Panel Items
            panelItem.add(tfItemName);            //in items tab set add field for item
            panelItem.add(lblItemName);            //In items tab add label "item name"
            panelItem.add(btnRemoveItems);        //In items tab add button "remove"
            panelItem.add(itemMenu);            //In items tab add table for adding data
            panelItem.add(btnAddItems);            //in items tab add the buttom "add"
            panelItem.add(lblCategory);            //In the items tab add label "category"
            panelItem.add(lblPrice);              //In the items tab add label Price
            panelItem.add(tfItemPrice);            //In the items tab add field to add price
            panelItem.add(lblCurrency);            //In the Items tab add label "Currency"
            panelItem.add(tfItemCurrency);        //In the items tab add field to add currency
            panelItem.add(cbMMItems);               //In the items tab add field to add the MM to purchase date
            panelItem.add(cbDDItems);             //In the items tab add field to add the DD to purchase date
            panelItem.add(lblPurchaseDate);        //in the items tab add label "purchase date"
            panelItem.add(cbYYYYItems);              //In the items tab add field to add the YYYY to purchase date
            panelItem.add(comboCurrencyOptions);
            panelItem.add(cbItemsCate);
            panelItem.add(cateItemsMenuCombo);

            //Panel Report
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

            //Panel PieChart;
            panelPieInfo.add(lblSetDatesPieChart);
            panelPieInfo.add(lblFromPieChart);
            panelPieInfo.add(lblToPieChart);
            panelPieInfo.add(cbDDFromPieChart);
            panelPieInfo.add(cbMMFromPieChart);
            panelPieInfo.add(cbYYYYFromPieChart);
            panelPieInfo.add(cbDDToPieChart);
            panelPieInfo.add(cbMMToPieChart);
            panelPieInfo.add(cbYYYYToPieChart);
            panelPieInfo.add(btnGeneratePieChart);

            /*
              panelPieChart Properties
             */

            chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(735, 500));
            chartPanel.setBackground(Color.GRAY);

            tabbedPane.addTab("Pie Chart", null, panelPieMain, null);

            panelPieMain.setBackground(Color.GRAY);
            panelPieMain.add(chartPanel, BorderLayout.EAST);
            panelPieMain.add(panelPieInfo, BorderLayout.CENTER);

            panelPieInfo.setPreferredSize(new java.awt.Dimension(450, 500));
            panelPieInfo.setBackground(Color.GRAY);

            pieChart = new PieChart("CostManager Pie Chart",
                    "Pie chart from dates: 01/01/2000 - 31/12/2021");
            pieChart.pack();

            //Change settings for the lbl "Set Dates" in pie chart
            lblSetDatesPieChart.setForeground(Color.BLACK);
            lblSetDatesPieChart.setFont(new Font("Cooper Black", Font.PLAIN, 25));
            lblSetDatesPieChart.setBounds(720, 151, 454, 28);

            //Change settings for the lbl "From" in pie chart
            lblFromPieChart.setForeground(Color.BLACK);
            lblFromPieChart.setFont(new Font("Cooper Black", Font.PLAIN, 25));
            lblFromPieChart.setBounds(748, 333, 98, 28);

            //Change settings for the lbl "To" in pie chart
            lblToPieChart.setForeground(Color.BLACK);
            lblToPieChart.setFont(new Font("Cooper Black", Font.PLAIN, 25));
            lblToPieChart.setBounds(976, 267, 53, 28);

            //Create settings for the button to generate pie chart according to dates
            btnGeneratePieChart.setFont(new Font("Cooper Black", Font.PLAIN, 26));
            btnGeneratePieChart.setBounds(872, 200, 297, 28);
            btnGeneratePieChart.setForeground(new Color(0, 0, 0));
            btnGeneratePieChart.setOpaque(false);
            btnGeneratePieChart.setContentAreaFilled(false);

            //Combobox of DD in the Piechart of the "from" settings
            cbDDFromPieChart.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
            cbDDFromPieChart.setSelectedIndex(0);
            cbDDFromPieChart.setBounds(748, 318, 44, 33);

            //Combobox of MM in the Piechart of the "from" settings
            cbMMFromPieChart.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
            cbMMFromPieChart.setSelectedIndex(0);
            cbMMFromPieChart.setBounds(802, 318, 44, 33);

            //Combobox of YYYY in the Piechart of the "from" settings
            cbYYYYFromPieChart.setModel(new DefaultComboBoxModel(new String[]{"2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021"}));
            cbYYYYFromPieChart.setSelectedIndex(0);
            cbYYYYFromPieChart.setBounds(861, 318, 76, 33);

            //Combobox of DD in the Piechart of the "to" settings
            cbDDToPieChart.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
            cbDDToPieChart.setSelectedIndex(0);
            cbDDToPieChart.setBounds(995, 318, 44, 33);

            //Combobox of MM in the Piechart of the "to" settings
            cbMMToPieChart.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
            cbMMToPieChart.setSelectedIndex(0);
            cbMMToPieChart.setBounds(1045, 318, 44, 33);

            //Combobox of MM in the Piechart of the "to" settings
            cbYYYYToPieChart.setModel(new DefaultComboBoxModel(new String[] {"2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021"}));
            cbYYYYToPieChart.setSelectedIndex(0);
            cbYYYYToPieChart.setBounds(1093, 318, 76, 33);

            //Messages panel
            panelMessage.add(lbMessage);
            panelMessage.add(tfMessage);
            tfMessage.setEditable(false);

            //Setting a different color for the panel message
            panelMessage.setBackground(Color.BLACK);

            //General components
            menuBar.add(mnFile);

            //Create a panel
            frame.setForeground(Color.BLUE);
            frame.setBackground(Color.BLACK);
            frame.setBounds(9, 22, 3, 16);
            frame.setContentPane(panelMain);

            //Set the tabs of items ,categories,items on TOP
            tabbedPane.setForeground(Color.BLACK);
            tabbedPane.setBounds(0, 0, 1128, 550);
            switch (tabbedPane.getSelectedIndex()) {
                case 0:
                    tabbedPane.setPreferredSize(new Dimension(1, 550));
                    break;
                case 1:
                    tabbedPane.setPreferredSize(new Dimension(1, 330));
                    break;
            }

            //Set an icon for the Jframe
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setBounds(100, 100, 1140, 880);

            //set the menu bar on top
            menuBar.setBackground(Color.BLACK);
            frame.setJMenuBar(menuBar);

            //Add a menu buttom on the left side of the screen
            mnFile.setBackground(Color.BLACK);
            mnFile.setForeground(Color.LIGHT_GRAY);

            //Add the buttom "Exit" in the menu to close the app
            mntmExit.setBackground(SystemColor.BLACK);
            mntmExit.setForeground(Color.LIGHT_GRAY);

            //Add the buttons to mnFile
            mnFile.add(mntmExit);

            //Set the Categories panel
            panelCate.setBackground(Color.GRAY);
            tabbedPane.addTab("Categories", null, panelCate, null);
            tabbedPane.setFont(new Font("Cooper Black", Font.PLAIN, 16));
            panelCate.setLayout(null);

            /*
             panelCategories
             */

            //Set the table in Categories tab
            categoriesMenu.setBounds(0, 0, 709, 424);

            //In categories set the label "Category"
            lblCategoryName.setOpaque(true);
            lblCategoryName.setBackground(Color.WHITE);
            lblCategoryName.setForeground(Color.BLACK);
            lblCategoryName.setFont(new Font("Cooper Black", Font.PLAIN, 18));
            lblCategoryName.setBounds(780, 98, 146, 20);

            //In categories tab This is the text field of category
            tfCategory.setEditable(true);
            tfCategory.setFont(new Font("Cooper Black", Font.PLAIN, 13));
            tfCategory.setBounds(958, 96, 146, 26);
            tfCategory.setColumns(10);


            //In categories tab this is the add button settings
            btnAddCategory.setFont(new Font("Cooper Black", Font.PLAIN, 30));
            btnAddCategory.setForeground(new Color(0, 0, 0));
            btnAddCategory.setBounds(743, 343, 157, 29);
            btnAddCategory.setOpaque(false);
            btnAddCategory.setContentAreaFilled(false);

            //In categories tab this is the remove buttom settings
            btnDeleteCategory.setFont(new Font("Cooper Black", Font.PLAIN, 30));
            btnDeleteCategory.setForeground(new Color(0, 0, 0));
            btnDeleteCategory.setBounds(933, 343, 157, 29);
            btnDeleteCategory.setOpaque(false);
            btnDeleteCategory.setContentAreaFilled(false);

            /*
             panelItems
             */

            //Define panel 2, Items
            panelItem.setBackground(Color.GRAY);
            tabbedPane.addTab("Items", null, panelItem, null);
            panelItem.setLayout(null);

            //In items tab set add  settings to field for item
            tfItemName.setFont(new Font("Cooper Black", tfItemName.getFont().getStyle(), tfItemName.getFont().getSize() + 4));
            tfItemName.setBounds(936, 42, 154, 35);
            tfItemName.setColumns(10);

            //In items tab add  settings to label "item name"
            lblItemName.setBackground(Color.WHITE);
            lblItemName.setOpaque(true);
            lblItemName.setForeground(Color.BLACK);
            lblItemName.setFont(new Font("Cooper Black", Font.PLAIN, 20));
            lblItemName.setBounds(744, 46, 125, 20);

            //In items tab add  settings to button "remove"
            btnRemoveItems.setDefaultCapable(false);
            btnRemoveItems.setContentAreaFilled(false);
            btnRemoveItems.setBackground(Color.WHITE);
            btnRemoveItems.setForeground(Color.BLACK);
            btnRemoveItems.setFont(new Font("Cooper Black", Font.PLAIN, 30));
            btnRemoveItems.setActionCommand("hidenRefreshButton3");
            btnRemoveItems.setForeground(new Color(0, 0, 0));
            btnRemoveItems.setBounds(933, 343, 157, 29);
            btnRemoveItems.setBorderPainted(false);

            //in items tab add settings to the buttom "add"
            btnAddItems.setFont(new Font("Cooper Black", Font.PLAIN, 30));
            btnAddItems.setDefaultCapable(false);
            btnAddItems.setContentAreaFilled(false);
            btnAddItems.setBorderPainted(false);
            btnAddItems.setBackground(Color.BLACK);
            btnAddItems.setForeground(new Color(0, 0, 0));
            btnAddItems.setActionCommand("hidenRefreshButton3");
            btnAddItems.setBounds(743, 343, 157, 29);

            //In the items tab add settings to label "category"
            lblCategory.setOpaque(true);
            lblCategory.setForeground(Color.BLACK);
            lblCategory.setFont(new Font("Cooper Black", Font.PLAIN, 20));
            lblCategory.setBackground(Color.WHITE);
            lblCategory.setBounds(744, 103, 125, 20);

            //In the items tab add settings to label Price
            lblPrice.setOpaque(true);
            lblPrice.setForeground(Color.BLACK);
            lblPrice.setFont(new Font("Cooper Black", Font.PLAIN, 20));
            lblPrice.setBackground(Color.WHITE);
            lblPrice.setBounds(744, 159, 125, 20);

            //In the items tab add settings to field to add price
            tfItemPrice.setColumns(10);
            tfItemPrice.setBounds(936, 155, 154, 35);

            //In the Items tab add settings to label "Currency"
            lblCurrency.setOpaque(true);
            lblCurrency.setForeground(Color.BLACK);
            lblCurrency.setFont(new Font("Cooper Black", Font.PLAIN, 20));
            lblCurrency.setBackground(Color.WHITE);
            lblCurrency.setBounds(744, 216, 125, 20);

            //In the items tab add settings to label "purchase date"
            lblPurchaseDate.setOpaque(true);
            lblPurchaseDate.setForeground(Color.BLACK);
            lblPurchaseDate.setFont(new Font("Cooper Black", Font.PLAIN, 20));
            lblPurchaseDate.setBackground(Color.WHITE);
            lblPurchaseDate.setBounds(719, 277, 154, 20);

            //In items tab add settings to combobox for DD of purchase date
            cbDDItems.setForeground(Color.BLACK);
            cbDDItems.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
            cbDDItems.setSelectedIndex(0);
            cbDDItems.setBackground(Color.WHITE);
            cbDDItems.setBounds(936, 262, 39, 35);

            //In items tab add settings to combobox for MM of purchase date
            cbMMItems.setForeground(Color.BLACK);
            cbMMItems.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
            cbMMItems.setSelectedIndex(0);
            cbDDItems.setBackground(Color.WHITE);
            cbMMItems.setBounds(985, 262, 39, 35);

            //In items tab add settings to combobox for YYYY of purchase date
            cbYYYYItems.setForeground(Color.BLACK);
            cbYYYYItems.setModel(new DefaultComboBoxModel(new String[] { "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021"}));
            cbYYYYItems.setSelectedIndex(0);
            cbYYYYItems.setBackground(Color.WHITE);
            cbYYYYItems.setBounds(1034, 262, 75, 35);

            //Add settings to the comboBox in items of currency
            comboCurrencyOptions.setForeground(Color.BLACK);
            comboCurrencyOptions.setModel(new DefaultComboBoxModel(new String[]{"USD", "ILS", "EUR", "GBP"}));
            comboCurrencyOptions.setSelectedIndex(0);
            comboCurrencyOptions.setBackground(Color.WHITE);
            comboCurrencyOptions.setBounds(936, 211, 137, 29);

            //Add settings in items tabs to the combobox to choose categories
            cbItemsCate.setForeground(new Color(0, 0, 0));
            cbItemsCate.setBounds(936, 97, 154, 39);

            /*
             panelReport Properties
             */

            //Add to the tabbed panel a "Report" tab and add settings
            tabbedPane.addTab("Report", null, panelReport, null);
            panelReport.setBackground(Color.GRAY); //set the background of
            panelReport.setLayout(null);

            //Add settings to lbl "set dates" to the report panel
            lblSetDatesReport.setForeground(Color.BLACK);
            lblSetDatesReport.setFont(new Font("Cooper Black", Font.PLAIN, 25));
            lblSetDatesReport.setBounds(720, 151, 454, 28);

            //Add settings to lbl "From" to the report panel
            lblFromReport.setForeground(Color.BLACK);
            lblFromReport.setFont(new Font("Cooper Black", Font.PLAIN, 25));
            lblFromReport.setBounds(748, 267, 98, 28);

            //Add settings to lbl "To" to the report panel
            lblToReport.setForeground(Color.BLACK);
            lblToReport.setFont(new Font("Cooper Black", Font.PLAIN, 25));
            lblToReport.setBounds(976, 267, 53, 28);

            //Add settings to btn "Generate Report" to the report panel
            btnGenerateReport.setFont(new Font("Cooper Black", Font.PLAIN, 26));
            btnGenerateReport.setBounds(872, 398, 297, 28);
            btnGenerateReport.setForeground(new Color(0, 0, 0));
            btnGenerateReport.setOpaque(false);
            btnGenerateReport.setContentAreaFilled(false);

            //Add settings to combobox DD From in the report panel
            cbDDFromReport.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
            cbDDFromReport.setSelectedIndex(0);
            cbDDFromReport.setBounds(748, 318, 44, 33);

            //Add settings to combobox MM From in the report panel
            cbMMFromReport.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
            cbMMFromReport.setSelectedIndex(0);
            cbMMFromReport.setBounds(802, 318, 44, 33);

            //Add settings to combobox YYYY From in the report panel
            cbYYYYFromReport.setModel(new DefaultComboBoxModel(new String[]{"2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021"}));
            cbYYYYFromReport.setSelectedIndex(0);
            cbYYYYFromReport.setBounds(861, 318, 76, 33);

            //Add settings to combobox DD To in the report panel
            cbDDToReport.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
            cbDDToReport.setSelectedIndex(0);
            cbDDToReport.setBounds(995, 318, 44, 33);

            //Add settings to combobox MM To in the report panel
            cbMMToReport.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
            cbMMToReport.setSelectedIndex(0);
            cbMMToReport.setBounds(1045, 318, 44, 33);

            //Add settings to combobox YYYY To in the report panel
            cbYYYYToReport.setModel(new DefaultComboBoxModel(new String[] {"2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021"}));
            cbYYYYToReport.setSelectedIndex(0);
            cbYYYYToReport.setBounds(1093, 318, 76, 33);

            /*
             TabbedPane position and layout
             */

            //Add settings to the tabbed panel
            tabbedPane.setBackgroundAt(0,Color.GRAY); //change tabs color to gray
            tabbedPane.setBackgroundAt(1,Color.GRAY);
            tabbedPane.setBackgroundAt(2,Color.GRAY);
            tabbedPane.setBackgroundAt(3,Color.GRAY);
            tabbedPane.setFont( new Font( "Cooper Black",Font.PLAIN, 25 ) ); //change font of tabs

            //Setting the window layout manager
            frame.setLayout(new BorderLayout());
            frame.setBackground(Color.BLACK);
            panelMain.setBackground(Color.BLACK);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.NORMAL);
            frame.setUndecorated(true); //hide the upper bar of the application.
            frame.add(tabbedPane, BorderLayout.CENTER);
            frame.add(panelMessage, BorderLayout.SOUTH);

            //Handling window closing
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

            //Handling closing tab from menu "Exit"
            mntmExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    System.exit(JFrame.EXIT_ON_CLOSE);
                }
            });

            //Handling cost item adding button click on items tab
            btnAddItems.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String itemName = tfItemName.getText();
                        if (itemName == null || itemName.length() == 0) {
                            throw new CostManagerException("Item name cannot be empty");
                        }
                        //ItemID will be generated in model, for now set it 0
                        int itemID = 0;
                        //Get price
                        double sum = Double.parseDouble(tfItemPrice.getText());
                        String chosenCurrency = comboCurrencyOptions.getSelectedItem().toString();
                        //Get currency
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
                        //CateID will be generated at model, set it 0 for now
                        int cateID = 0;
                        //Add purchase date
                        String purchaseDate = cbDDItems.getSelectedItem().toString() + "." + cbMMItems.getSelectedItem().toString()
                                + "." + cbYYYYItems.getSelectedItem().toString() ;
                        CostItem item = new CostItem(itemID, cateID, itemName, currency,
                                sum, purchaseDate);
                        //Get category name
                        String categoryName = cbItemsCate.getSelectedItem().toString();
                        vm.addCostItem(item, categoryName);
                    } catch (NumberFormatException ex) {
                        View.this.showMessage("problem with entered sum... " + ex.getMessage());
                    } catch (CostManagerException ex) {
                        View.this.showMessage("problem with entered data... problem with description... " + ex.getMessage());
                    }
                }
            });
            //Handling cost item delete button click on items tab
            btnRemoveItems.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        //Add purchase date
                        String purchaseDate = cbDDItems.getSelectedItem().toString() + "." + cbMMItems.getSelectedItem().toString()
                                + "." + cbYYYYItems.getSelectedItem().toString();
                        String itemName = tfItemName.getText();
                        if (itemName == null || itemName.length() == 0) {
                            throw new CostManagerException("Item name cannot be empty");
                        }
                        //Generate itemID, not really matter only name matter
                        int itemID = 1;
                        //Get price
                        double sum = 0;
                        String chosenCurrency = "0";
                        //Get currency
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
                        //Get category ID, doesn't matter
                        int cateID = 0;
                        //Define the object
                        CostItem item = new CostItem(itemID, cateID, itemName, currency, sum,purchaseDate);
                        //Send the object to the view model
                        vm.deleteCostItem(item);

                    } catch (CostManagerException ex) {
                        View.this.showMessage("problem with entered data... problem with description... " + ex.getMessage());
                    }
                }
            });

            //Handling category adding button click on items tab
            btnAddCategory.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        //Get category name
                        String cateName = tfCategory.getText();
                        //Validation Check
                        if (cateName == null || cateName.length() == 0) {
                            throw new CostManagerException("Category name cannot be empty");
                        }
                        //CateID will be set later
                        int minCateID = 1;
                        //Create the object
                        Category category = new Category(minCateID, cateName);
                        //Send the object to the view model
                        vm.addCategory(category);
                        //Update the categories in the combobox of items panel
                        vm.setCbItems();
                    } catch (NumberFormatException ex) {
                        View.this.showMessage("problem with entered ID... " + ex.getMessage());
                    } catch (CostManagerException ex) {
                        View.this.showMessage("problem with entered data... problem with description... " + ex.getMessage());
                    }
                }
            });

            //Handling category delete button click on items tab
            btnDeleteCategory.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        //Set cateID
                        int cateID = 0;
                        //Get category name
                        String cateName = tfCategory.getText();
                        //Validation check
                        if (cateName == null || cateName.length() == 0) {
                            throw new CostManagerException("Category name cannot be empty");
                        }
                        //Create the object
                        Category category = new Category(cateID, cateName);
                        //Send it to the view model for delete
                        vm.deleteCategory(category);
                        //Upadte the categories in the combobox of items panel
                        vm.setCbItems();
                    } catch (NumberFormatException ex) {
                        View.this.showMessage("problem with entered ID... " + ex.getMessage());
                    } catch (CostManagerException ex) {
                        View.this.showMessage("problem with entered data... problem with description... " + ex.getMessage());
                    }
                }
            });

            // This will save the selected dates by user, and will save into a string format, and will send to show report
            btnGenerateReport.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //Set DateFormat
                    DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                    //Get the From and To dates
                    int ddFrom = Integer.valueOf((cbDDFromReport.getSelectedItem().toString()));
                    int mmFrom = Integer.valueOf((cbMMFromReport.getSelectedItem().toString())) - 1;
                    int yyyyFrom =  Integer.valueOf((cbYYYYFromReport.getSelectedItem().toString())) - 1900;
                    Date dateFrom = new Date(yyyyFrom,mmFrom,ddFrom);
                    int ddTo = Integer.valueOf((cbDDToReport.getSelectedItem().toString()));
                    int mmTo = Integer.valueOf((cbMMToReport.getSelectedItem().toString())) - 1;
                    int yyyyTo =  Integer.valueOf((cbYYYYToReport.getSelectedItem().toString())) - 1900;
                    Date dateTo = new Date(yyyyTo,mmTo,ddTo);
                    //Send the dates to the view model
                    vm.handleReport(dateFrom,dateTo);
                }
            });

            //This will save the selected dates by user, save into a string format, and will send to the viewmodel
            btnGeneratePieChart.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    //Set DateFormat
                    DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                    //Get the From and To dates
                    int ddFrom = Integer.valueOf((cbDDFromPieChart.getSelectedItem().toString()));
                    int mmFrom = Integer.valueOf((cbMMFromPieChart.getSelectedItem().toString())) - 1;
                    int yyyyFrom =  Integer.valueOf((cbYYYYFromPieChart.getSelectedItem().toString())) - 1900;
                    Date dateFrom = new Date(yyyyFrom,mmFrom,ddFrom);
                    int ddTo = Integer.valueOf((cbDDToPieChart.getSelectedItem().toString()));
                    int mmTo = Integer.valueOf((cbMMToPieChart.getSelectedItem().toString())) - 1;
                    int yyyyTo =  Integer.valueOf((cbYYYYToPieChart.getSelectedItem().toString())) - 1900;
                    Date dateTo = new Date(yyyyTo,mmTo,ddTo);
                    //Empty the pie chart
                    result.clear();
                    //Send the dates to the view model
                    vm.getDataSetPie(dateTo, dateFrom);
                }
            });
            //Displaying the window
            frame.setSize(1200, 600);
            frame.setVisible(true);
        }

        //Show msg in the debug window
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
        }

        //This method gets the dataset from the model and create the pie chart in the view
        public void showPieChart(DefaultPieDataset DateSet){
            result = DateSet;
            dataset = result;
            //Set the variable chart
            chart = ChartFactory.createPieChart3D(
                    "CostManager Pie Chart",         // chart title
                    dataset,                              // data
                    true,                          // include legend
                    true,
                    false
            );
            //Initialize the variable plot according to chart
            PiePlot3D plot = (PiePlot3D) chart.getPlot();
            plot.setForegroundAlpha(0.5f);
            plot.setStartAngle(290);
            plot.setDirection(Rotation.CLOCKWISE);
            //Remove the previous Pie Chart panel
            panelPieMain.remove(chartPanel);
            //Set the new panel according to the new data
            chartPanel = new ChartPanel(chart);
            panelPieMain.add(chartPanel, BorderLayout.WEST);
        }

        public void loadCateListToCB(List<Category> categories){
            cbItemsCate.setModel(new DefaultComboBoxModel(categories.toArray()));
            //This method will empty the combobox of categories in items tab
                vm.loadCategoriesTable();
                for(int i = 0; i < categories.size() - 1; i++) {
                    cbItemsCate.removeItemAt(0);
                }
            String[] cate = new String[categories.size()]; //set an array of strings to send to the combobox
            for(int i = 0; i < categories.size();i++){
                    cate[i] = categories.get(i).getCategoryName();
            }
                cbItemsCate.setModel(new DefaultComboBoxModel(cate));
        }

        //This method will show all the available categories in db
        public void showCategoriesTable(JTable cateTable) {
            //Remove the previous scroll panel
            panelCate.remove(categoriesMenu);
            JTable table = cateTable;
            table.setBounds(0,0,709,424);
            table.setEnabled(false);
            table.setBackground(Color.GRAY);
            //prevent the user from dragging the columns of the table
            table.getTableHeader().setReorderingAllowed(false);
            categoriesMenu = new JScrollPane(table);
            categoriesMenu.setBounds(0, 0, 709, 424);
            panelCate.setBackground(Color.GRAY);
            categoriesMenu.setVisible(true);
            //In cate panel add the scroll panel
            panelCate.add(categoriesMenu);
            panelCate.setBounds(0,0,709,424);
            panelCate.setVisible(true);
        }

        //This method will show the items in the report text area
        public void showReportItems(JTable itemsReport) {
            //Remove the previous scroll panel
            panelReport.remove(spReport);
            JTable table = itemsReport;
            table.setBounds(0,0,709,424);
            table.setEnabled(false);
            table.setBackground(Color.GRAY);
            //Prevent the user from dragging the columns of the table
            table.getTableHeader().setReorderingAllowed(false);
            //Define a new scrollpanel and add it the table
            spReport = new JScrollPane(table);
            spReport.setBounds(0, 0, 700, 351);
            panelReport.setBackground(Color.GRAY);
            spReport.setVisible(true);
            //In report panel add the scroll panel
            panelReport.add(spReport);
            panelReport.setBounds(0,0,709,424);
            panelReport.setVisible(true);
        }

        public void showItemsTable(JTable itemsTable) {
            //Remove the previous scroll panel
            panelItem.remove(itemMenu);
            JTable table = itemsTable;
            table.setBounds(0,0,709,424);
            table.setEnabled(false);
            table.setBackground(Color.GRAY);
            //Prevent the user from dragging the columns of the table
            table.getTableHeader().setReorderingAllowed(false);
            //Define a new scrollpanel and add it the table
            itemMenu = new JScrollPane(table);
            itemMenu.setBounds(0, 0, 709, 424);
            panelItem.setBackground(Color.GRAY);
            itemMenu.setVisible(true);
            //In items panel add the scroll panel
            panelItem.add(itemMenu);
            panelItem.setBounds(0,0,709,424);
            panelItem.setVisible(true);
        }
    }
}