
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.JTabbedPane;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Gui extends JFrame {

	private JPanel contentPane;
	double[][] matrixN;
	double [] matrixW;
	private JTable table_1;
	private JTable table;
	private JTable table_2;
	private DefaultTableModel modelA;
	private DefaultTableModel modelB;
	private DefaultTableModel modelC;
	private JTextField Czaspomiaru;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Gui() {
		
		 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel Wprowadzenie = new JPanel();
		tabbedPane.addTab("Wprowadzenie", null, Wprowadzenie, null);
		Wprowadzenie.setLayout(null);
		
		JPanel Dane = new JPanel();
		Dane.setBounds(10, 10, 411, 62);
		Wprowadzenie.add(Dane);
		Dane.setLayout(null);
		SpinnerModel model2 = new SpinnerNumberModel(1, 0, 9999, 1);
		JSpinner spinner = new JSpinner(model2);
		spinner.setBounds(10, 32, 68, 20);
		Dane.add(spinner);
		
		JButton Rysuj = new JButton("Rysuj");
		Rysuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				 int n = (int) spinner.getValue();
				

                 
                 table.setModel(new DefaultTableModel(n, n));
                 table_1.setModel(new DefaultTableModel(n, 1));
                 table_2.setModel(new DefaultTableModel(n, 1));
                 for (int i=0; i<n; i++) {
                	  TableColumn column = table.getColumnModel().getColumn(i);
                	  column.setPreferredWidth(20);
                	  column.setMaxWidth(20);
                      column.setMinWidth(20);
                	}
                 TableColumn column_1 = table_1.getColumnModel().getColumn(0);
                 column_1.setMaxWidth(60);
                 column_1.setMinWidth(60);
                 
                 TableColumn column_2 = table_2.getColumnModel().getColumn(0);
                 column_2.setMaxWidth(60);
                 column_2.setMinWidth(60);
			}
			
		});
		Rysuj.setBounds(88, 31, 85, 21);
		Dane.add(Rysuj);
		
		JLabel lblNewLabel = new JLabel("Liczba kolumn i wierszy macierzy");
		lblNewLabel.setBounds(10, 10, 189, 13);
		Dane.add(lblNewLabel);
		
		JButton Oblicz = new JButton("Oblicz");
		Oblicz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.isEditing())
				    table.getCellEditor().stopCellEditing();
				modelA = (DefaultTableModel) table.getModel();
				modelB = (DefaultTableModel) table_1.getModel();
				modelA.fireTableDataChanged();
				modelB.fireTableDataChanged();
				table.repaint();
				table_1.repaint();
				int nRow = modelA.getRowCount();
				int colu=modelA.getColumnCount();
				double[][] tableData = new double[nRow][colu];
				
				for (int i = 0 ; i < nRow ; i++)
				        for (int j = 0 ; j < colu ; j++)
				           tableData[i][j] = Double.parseDouble(String.valueOf(modelA.getValueAt(i,j)));
				int nRow2 = modelB.getRowCount();
				int colu2=modelB.getColumnCount();
				double[] tableData2 = new double[nRow2];
				for (int i = 0 ; i < nRow2 ; i++)
				        for (int j = 0 ; j < colu2 ; j++)
				           tableData2[i] = Double.parseDouble(String.valueOf(modelB.getValueAt(i,j)));
			
				Subsitution subsitutionExample = new Subsitution(nRow, tableData, tableData2);
				matrixW=subsitutionExample.calculate();
				Czaspomiaru.setText(String.valueOf(subsitutionExample.getTimeInNano()));
				
				System.out.println(Arrays.toString(matrixW));
				
				
				
			}
		});
		Oblicz.setBounds(183, 31, 85, 21);
		Dane.add(Oblicz);
		
		Czaspomiaru = new JTextField();
		Czaspomiaru.setEditable(false);
		Czaspomiaru.setBounds(291, 32, 96, 19);
		Dane.add(Czaspomiaru);
		Czaspomiaru.setColumns(10);
		
		JLabel lblCzaspomiaru = new JLabel("Czas pomiaru");
		lblCzaspomiaru.setBounds(291, 10, 96, 13);
		Dane.add(lblCzaspomiaru);
		
		JPanel Macierz = new JPanel();
		Macierz.setBounds(10, 71, 411, 155);
		Wprowadzenie.add(Macierz);
		Macierz.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 23, 139, 117);
		Macierz.add(scrollPane);
		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setFillsViewportHeight(true);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(20);
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(159, 23, 81, 117);
		Macierz.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setCellSelectionEnabled(true);
		table_1.setFillsViewportHeight(true);
		table_1.setPreferredScrollableViewportSize(table_1.getPreferredSize());
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_1.setRowHeight(20);
		table_1.getTableHeader().setReorderingAllowed(false);
		scrollPane_1.setViewportView(table_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(250, 23, 81, 117);
		Macierz.add(scrollPane_2);
		
		table_2 = new JTable();
		table_2.setEnabled(false);
		table_2.setCellSelectionEnabled(true);
		table_2.setFillsViewportHeight(true);
		table_2.setPreferredScrollableViewportSize(table_2.getPreferredSize());
		table_2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_2.setRowHeight(20);
		table_2.getTableHeader().setReorderingAllowed(false);
		scrollPane_2.setViewportView(table_2);
		
		JPanel Obliczenia = new JPanel();
		tabbedPane.addTab("Obliczenia", null, Obliczenia, null);
		
		JPanel Wyniki = new JPanel();
		tabbedPane.addTab("Wynik", null, Wyniki, null);
	}	
}
