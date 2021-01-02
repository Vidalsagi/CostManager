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
        private JFrame frame;

        private JPanel panelMain;
        private JPanel panelCate;
        private JPanel panelItem;

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
        //private JTable table1;
        private JTextField tfCategory;
        private JTextField categoryField;
        private JLabel lblCategoryName;
        private JButton btnAddCategory;
        private JButton btnDeleteCategory;

        /**
         * panel Items
         */

        private JTextArea textAreaItems;
        private JScrollPane itemMenu;
        //private JTable table2;
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
        private JTextField purchase_Date_DD;
        private JTextField purchase_Date_MM;
        private JTextField purchase_Date_YYYY;
        private JComboBox comboCurrencyOptions;
        private JComboBox cateItemsMenuCombo;


        public ApplicationUI() {
            //creating the window
            frame = new JFrame("CostManager");
            //create the main panel
            panelMain = new JPanel();
            //Set the Categories panel
            panelCate = new JPanel();
            //set the items panel
            panelItem = new JPanel();
            //Set the tabs of summary,categories,items on TOP
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

            //Set the table in Summary tab
            textAreaCate = new JTextArea();
            categoriesMenu = new JScrollPane(textAreaCate);
            //tableCate = new JTable();

            //In Categories set the label "Category"
            lblCategoryName = new JLabel("Category");
            //In Categories This is the text field of category
            tfCategory = new JTextField();
            ////In Categories set this is the field CategoryName
            //categoryField = new JTextField();

            //in summary tab this is the add button
            btnAddCategory = new JButton("Add");
            //In summary tab this is the remove buttom
            btnDeleteCategory = new JButton("Remove");

            /* PanelItem related (Items) */
            //tableItem = new JTable();
            //In items tab add table for adding data

            textAreaItems = new JTextArea();
            itemMenu = new JScrollPane(textAreaItems);

            //In the items tab add field to add the DD to purchase date
            purchase_Date_DD = new JTextField();
            //in items tab set field "MM"
            //txtMm = new JTextField();
            //In the items tab add field to add the MM to purchase date
            purchase_Date_MM = new JTextField();
            //in items tab set field "YYYY"
            //txtYyyy = new JTextField();
            //In the items tab add field to add the YYYY to purchase date
            purchase_Date_YYYY = new JTextField();
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

        }

        public void start() {

            //add the panels
            //panel Categories
            panelCate.add(categoriesMenu);              //table in summary tab
            panelCate.add(lblCategoryName);             //In Categories set the label "Category"
            panelCate.add(tfCategory);                    //In Categories This is the text field of category
            panelCate.add(btnAddCategory);                //in Categories tab this is the add button
            panelCate.add(btnDeleteCategory);            //In Categories tab this is the remove buttom

            panelMain.setLayout(new BorderLayout());

            //panel Items
            panelItem.add(tfItemName);            //in items tab set add field for item
            panelItem.add(lblItemName);            //In items tab add label "item name"
            panelItem.add(btnRemoveItems);        //In items tab add button "remove"
            panelItem.add(itemMenu);            //In items tab add table for adding data
            panelItem.add(btnAddItems);            //in items tab add the buttom "add"
            panelItem.add(lblCategory);            //In the items tab add label "category"
            panelItem.add(tfItemCateName);        //In the Items tab add field to add categories
            panelItem.add(lblPrice);            //In the items tab add label Price
            panelItem.add(tfItemPrice);            //In the items tab add field to add price
            panelItem.add(lblCurrency);            //In the Items tab add label "Currency"
            panelItem.add(tfItemCurrency);        //In the items tab add field to add currency
            panelItem.add(purchase_Date_DD);    //In the items tab add field to add the DD to purchase date
            panelItem.add(lblPurchaseDate);        //in the items tab add label "purchase date"
            panelItem.add(purchase_Date_MM);    //In the items tab add field to add the MM to purchase date
            panelItem.add(purchase_Date_YYYY);    //In the items tab add field to add the YYYY to purchase date
            panelItem.add(comboCurrencyOptions);
            panelItem.add(cateItemsMenuCombo);
            //panel3.add(imgCostBackItems1);			//In the items tab add a background
            //panel3.add(imgCostBackground);			//In the items tab add a background2

            //general components
            menuBar.add(mnFile);

            //Create a panel
            frame.setForeground(Color.BLACK);
            frame.setBackground(Color.WHITE);
            frame.setBounds(9, 9, 9, 9);
            frame.setContentPane(panelMain);

            //Set the tabs of summary,categories,items on TOP
            tabbedPane.setForeground(Color.WHITE);
            tabbedPane.setBounds(0, 0, 1128, 459);

            //Set an icon for the Jframe
            //frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sagi\\Desktop\\\u05D0\u05D1\u05DF \u05D3\u05E8\u05DA 2\\CostManagerApp\\img\\CostMangerIcon.png"));
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
            btnAddCategory.setFont(new Font("Cooper Black", Font.PLAIN, 20));
            btnAddCategory.setForeground(new Color(255, 255, 255));
            btnAddCategory.setBounds(796, 387, 115, 29);
            btnAddCategory.setOpaque(false);
            btnAddCategory.setContentAreaFilled(false);

            //In Categories tab this is the remove buttom
            btnDeleteCategory.setFont(new Font("Cooper Black", Font.PLAIN, 20));
            btnDeleteCategory.setForeground(new Color(255, 255, 255));
            btnDeleteCategory.addActionListener(new ActionListener() { //Delete item
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        //This will remove an item
                        System.out.println("Removed an item");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            btnDeleteCategory.setBounds(958, 387, 127, 29);
            btnDeleteCategory.setOpaque(false);
            btnDeleteCategory.setContentAreaFilled(false);

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
            purchase_Date_MM.setText("MM");
            purchase_Date_MM.setColumns(10);
            purchase_Date_MM.setBounds(985, 262, 39, 35);

            //In the items tab add field to add the YYYY to purchase date
            purchase_Date_YYYY.setText("YYYY");
            purchase_Date_YYYY.setColumns(10);
            purchase_Date_YYYY.setBounds(1034, 262, 39, 35);

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
            //imgCostBackItems1.setIcon(new ImageIcon("C:\\Users\\Sagi\\Desktop\\\u05D0\u05D1\u05DF \u05D3\u05E8\u05DA 2\\CostManagerApp\\img\\MoneyBackground.png"));

            //In the items tab add a background2
            imgCostBackground.setVerticalTextPosition(SwingConstants.BOTTOM);
            imgCostBackground.setVerticalAlignment(SwingConstants.BOTTOM);
            imgCostBackground.setLocation(new Point(50, 50));
            imgCostBackground.setHorizontalTextPosition(SwingConstants.CENTER);
            imgCostBackground.setFont(new Font("Cooper Black", Font.PLAIN, 19));
            imgCostBackground.setAlignmentX(0.5f);
            imgCostBackground.setBounds(382, -29, 1472, 549);
            //imgCostBackground.setIcon(new ImageIcon("C:\\Users\\Sagi\\Desktop\\\u05D0\u05D1\u05DF \u05D3\u05E8\u05DA 2\\CostManagerApp\\img\\MoneyBackground.png"));


            //setting the window layout manager
            frame.setLayout(new BorderLayout());

            frame.add(tabbedPane, BorderLayout.CENTER);


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
                        String purchaseDate = purchase_Date_DD.getText() + "." + purchase_Date_MM.getText()
                                + "." + purchase_Date_YYYY.getText();
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

            //displaying the window
            frame.setSize(1200, 600);
            frame.setVisible(true);
        }

        public void showMessage(String text) {
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
            //@Override
            //public void setViewModel(IViewModel vm) {
            //
            //}
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
    }
}