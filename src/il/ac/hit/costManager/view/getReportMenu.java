package il.ac.hit.costManager.view;

import il.ac.hit.costManager.model.Category;
import il.ac.hit.costManager.model.CostItem;
import il.ac.hit.costManager.viewmodel.IViewModel;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Point;

public class getReportMenu{
		public JFrame frame;
		private JTextField tfSumTotalReport;

		/**
		 * Create the application.
		 */

		public getReportMenu() {
			initialize();
		}

		/**
		 * Initialize the contents of the frame.
		 */

		private void initialize() {

			frame = new JFrame("Report");
			frame.setResizable(false);
			frame.setAlwaysOnTop(true);
			frame.getContentPane().setBackground(Color.BLUE);
			frame.setBackground(Color.BLACK);
			frame.setForeground(Color.GRAY);
			frame.getContentPane().setForeground(Color.GREEN);
			//frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getReportMenu.class.getResource("/IconRentGames.jpg")));
			frame.setLocation(new Point(50, 50));
			frame.setBounds(100, 100, 828, 631);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			frame.setLocationRelativeTo(null);

			JLabel lblSetDatesReport = new JLabel("Set dates range to print the report");
			lblSetDatesReport.setForeground(Color.BLACK);
			lblSetDatesReport.setFont(new Font("Cooper Black", Font.PLAIN, 25));
			lblSetDatesReport.setBounds(27, 386, 454, 28);
			frame.getContentPane().add(lblSetDatesReport);

			JScrollPane spReport = new JScrollPane();
			spReport.setBounds(27, 32, 760, 351);
			frame.getContentPane().add(spReport);


			JLabel lblFromReport = new JLabel("From:");
			lblFromReport.setForeground(Color.BLACK);
			lblFromReport.setFont(new Font("Cooper Black", Font.PLAIN, 25));
			lblFromReport.setBounds(27, 459, 98, 28);
			frame.getContentPane().add(lblFromReport);

			JLabel lblToReport = new JLabel("To:");
			lblToReport.setForeground(Color.BLACK);
			lblToReport.setFont(new Font("Cooper Black", Font.PLAIN, 25));
			lblToReport.setBounds(255, 459, 53, 28);
			frame.getContentPane().add(lblToReport);


			JComboBox cbDDFromReport = new JComboBox();
			cbDDFromReport.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
			cbDDFromReport.setSelectedIndex(0);
			cbDDFromReport.setBounds(27, 510, 44, 33);
			frame.getContentPane().add(cbDDFromReport);


			JComboBox cbMMFromReport = new JComboBox();
			cbMMFromReport.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
			cbMMFromReport.setSelectedIndex(0);
			cbMMFromReport.setBounds(81, 510, 44, 33);
			frame.getContentPane().add(cbMMFromReport);


			JComboBox cbYYYYFromReport = new JComboBox();
			cbYYYYFromReport.setModel(new DefaultComboBoxModel(new String[] {"1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021"}));
			cbYYYYFromReport.setSelectedIndex(0);
			cbYYYYFromReport.setBounds(140, 510, 76, 33);
			frame.getContentPane().add(cbYYYYFromReport);

			JComboBox cbDDToReport = new JComboBox();
			cbDDToReport.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
			cbDDToReport.setSelectedIndex(0);
			cbDDToReport.setBounds(264, 510, 44, 33);
			frame.getContentPane().add(cbDDToReport);


			JComboBox cbMMToReport = new JComboBox();
			cbMMToReport.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
			cbMMToReport.setSelectedIndex(0);
			cbMMToReport.setBounds(318, 510, 44, 33);
			frame.getContentPane().add(cbMMToReport);


			JComboBox cbYYYYToReport = new JComboBox();
			cbYYYYToReport.setModel(new DefaultComboBoxModel(new String[] {"1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021"}));
			cbYYYYToReport.setSelectedIndex(0);
			cbYYYYToReport.setBounds(372, 510, 76, 33);
			frame.getContentPane().add(cbYYYYToReport);

			tfSumTotalReport = new JTextField();
			tfSumTotalReport.setEditable(false);
			tfSumTotalReport.setBounds(664, 513, 123, 28);
			frame.getContentPane().add(tfSumTotalReport);
			tfSumTotalReport.setColumns(10);

			JLabel lblTotalCost = new JLabel("Total Cost:");
			lblTotalCost.setForeground(Color.BLACK);
			lblTotalCost.setFont(new Font("Cooper Black", Font.PLAIN, 25));
			lblTotalCost.setBounds(495, 510, 159, 28);
			frame.getContentPane().add(lblTotalCost);

		}
	}
