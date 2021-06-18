package ui;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class QuanLySach extends JFrame  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtauthor;
	private JTextField txtprice;
	private JTable table;
	private JTextField txtbid;
	private JTextField txtamount;
	private JComboBox comboBoxSelect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLySach frame = new QuanLySach();
					frame.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public QuanLySach() {
		initialize();
		Connect();
		table_load();
	}

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField textField;

	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/bookstore", "root", "");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public void table_load() {
		try {
			pst = con.prepareStatement("select * from book");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1003, 805);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Book Manager");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(302, 10, 220, 49);
		frame.getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Nh\u1EADp Th\u00F4ng Tin S\u00E1ch", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GREEN));
		panel.setBounds(10, 113, 347, 229);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("T\u00EAn S\u00E1ch");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 35, 102, 25);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("T\u00E1c Gi\u1EA3");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 86, 102, 25);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("GI\u00E1 Ti\u1EC1n");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(10, 139, 102, 25);
		panel.add(lblNewLabel_1_1_1);

		txtbname = new JTextField();
		txtbname.setBounds(138, 39, 193, 26);
		panel.add(txtbname);
		txtbname.setColumns(10);

		txtauthor = new JTextField();
		txtauthor.setColumns(10);
		txtauthor.setBounds(138, 90, 193, 26);
		panel.add(txtauthor);

		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(138, 143, 193, 26);
		panel.add(txtprice);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("S\u1ED1 L\u01B0\u1EE3ng");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1.setBounds(10, 194, 102, 25);
		panel.add(lblNewLabel_1_1_1_1);

		txtamount = new JTextField();
		txtamount.setColumns(10);
		txtamount.setBounds(138, 193, 193, 26);
		panel.add(txtamount);

		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String bname, author, price, amount;

				bname = txtbname.getText();
				author = txtauthor.getText();
				price = txtprice.getText();
				amount = txtamount.getText();

				try {
					pst = con.prepareStatement("insert into book(name,author,price,amount)values(?,?,?,?)");
					pst.setString(1, bname);
					pst.setString(2, author);
					pst.setString(3, price);
					pst.setString(4, amount);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Added Thành Công!");
					table_load();
					// table_load();

					txtbname.setText("");
					txtauthor.setText("");
					txtprice.setText("");
					txtamount.setText("");
					txtbname.requestFocus();
				}

				catch (SQLException e1) {

					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(79, 380, 85, 40);
		frame.getContentPane().add(btnNewButton);

		JButton btnExit = new JButton("close");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(740, 718, 85, 40);
		frame.getContentPane().add(btnExit);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtbname.setText("");
				txtauthor.setText("");
				txtprice.setText("");
				txtamount.setText("");
				txtbname.requestFocus();

			}
		});
		btnClear.setBounds(194, 380, 85, 40);
		frame.getContentPane().add(btnClear);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(null, "Danh S\u00E1ch", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		scrollPane.setBounds(369, 139, 557, 561);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "T\u00ECm ki\u1EBFm v\u00E0 ch\u1EC9nh s\u1EEDa b\u1EB1ng ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		panel_1.setBounds(10, 457, 322, 184);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1_1_2 = new JLabel("Book ID");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_2.setBounds(10, 36, 102, 25);
		panel_1.add(lblNewLabel_1_1_2);

		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				try {

					String id = txtbid.getText();

					pst = con.prepareStatement("select name,author,price,amount from book where id = ?");
					pst.setString(1, id);
					ResultSet rs = pst.executeQuery();
					
					if (rs.next() == true) {

						String name = rs.getString(1);
						String author = rs.getString(2);
						String price = rs.getString(3);
						String amount = rs.getString(4);

						txtbname.setText(name);
						txtauthor.setText(author);
						txtprice.setText(price);
						txtamount.setText(amount);

					} else {
						txtbname.setText("");
						txtauthor.setText("");
						txtprice.setText("");
						txtamount.setText("");

					}

				}

				catch (SQLException ex) {

				}

			}
		});
		txtbid.setColumns(10);
		txtbid.setBounds(122, 36, 193, 26);
		panel_1.add(txtbid);
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(48, 118, 85, 40);
		panel_1.add(btnUpdate);
		
				JButton btnDelete = new JButton("Delete");
				btnDelete.setBounds(177, 118, 85, 40);
				panel_1.add(btnDelete);
				
				JLabel lblNewLabel_2 = new JLabel("T\u00ECm Ki\u1EBFm");
				lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblNewLabel_2.setBounds(369, 81, 79, 33);
				frame.getContentPane().add(lblNewLabel_2);
				
				textField = new JTextField();
				textField.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						try {

							String name = txtbname.getText();
							String selection = (String) comboBoxSelect.getSelectedItem();
							String query ="select id,name,author,price,amount from book where "+selection+" = ?";
							System.out.println(query);
							PreparedStatement pst = con.prepareStatement(query);
							pst.setString(1, textField.getText());
							ResultSet rs = pst.executeQuery();
							
							table.setModel(DbUtils.resultSetToTableModel(rs));
							
							pst.close();
						}

						catch (SQLException ex) {
							ex.printStackTrace();
						}
					}
				});
				textField.setBounds(575, 83, 263, 31);
				frame.getContentPane().add(textField);
				textField.setColumns(10);
				
				comboBoxSelect = new JComboBox();
				comboBoxSelect.setFont(new Font("Tahoma", Font.PLAIN, 15));
				comboBoxSelect.setModel(new DefaultComboBoxModel(new String[] {"ID", "Name", "Author", "Price", "Amount"}));
				comboBoxSelect.setBounds(458, 85, 79, 29);
				frame.getContentPane().add(comboBoxSelect);
				
				JButton btnReload = new JButton("Reset");
				btnReload.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textField.setText("");
						txtbid.setText("");
						txtbname.setText("");
						txtauthor.setText("");
						txtprice.setText("");
						txtamount.setText("");
						txtbname.requestFocus();
						table_load();
					}
				});
				btnReload.setBounds(857, 718, 85, 40);
				frame.getContentPane().add(btnReload);
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						String bid;
						bid = txtbid.getText();

						try {
							pst = con.prepareStatement("delete from book where id= ?");

							pst.setString(1, bid);
							pst.executeUpdate();
							JOptionPane.showMessageDialog(null, "Delete Thành Công");
							table_load();
							// table_load();

							txtbname.setText("");
							txtauthor.setText("");
							txtprice.setText("");
							txtamount.setText("");
							txtbname.requestFocus();
						}

						catch (SQLException e1) {

							e1.printStackTrace();
						}

					}

				});
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String bname, edition, price, amount, bid;

				bname = txtbname.getText();
				edition = txtauthor.getText();
				price = txtprice.getText();
				amount = txtamount.getText();
				bid = txtbid.getText();

				try {
					pst = con.prepareStatement("update book set name= ?,author=?,price= ?,amount= ? where id= ?");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, amount);
					pst.setString(5, bid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Update Thành Công");
					table_load();
					// table_load();

					txtbname.setText("");
					txtauthor.setText("");
					txtprice.setText("");
					txtamount.setText("");
					txtbname.requestFocus();
				}

				catch (SQLException e1) {

					e1.printStackTrace();
				}

			}

		});
	}
}
