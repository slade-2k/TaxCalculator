package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.NumberFormatter;

import model.Order;

public class GUI extends JFrame{
	
	private JLabel lblAmount = new JLabel("Amount:");
	private JLabel lblState = new JLabel("State:");
	private JLabel lblTotal = new JLabel("Total:");
	private JLabel lblSubtotal = new JLabel("Subtotal:");
	private JLabel lblTax = new JLabel("Tax:");
	
	private JPanel pnlInput = new JPanel();
	private JPanel pnlInputLabels = new JPanel();
	private JPanel pnlInputTextFields = new JPanel();
	private JPanel pnlOutput = new JPanel();
	private JPanel pnlOutputLabels = new JPanel();
	private JPanel pnlOutputTextFields = new JPanel();
	private JPanel pnlCalculate = new JPanel();
	
	private JFormattedTextField txtAmount;
	private JFormattedTextField txtSubtotal;
	private JFormattedTextField txtTax;
	private JFormattedTextField txtTotal;
	private JTextField txtState;
	
	private JButton btnCalc = new JButton("Calculate");
	private JButton btnReset = new JButton("Reset");
	
	private Dimension dimTxt = new Dimension(100, 20);
	private NumberFormat numberFormat;
	private NumberFormatter formatter; 
	
	public GUI(){
		this.setVisible(true);
		this.setSize(300, 250);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		this.setTitle("TaxCalculator");
		
		DecimalFormat df = new DecimalFormat("#.##");
		formatter = new NumberFormatter(df);
		formatter.setAllowsInvalid(false);
		
		numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);
		
		init();
	}

	public void init(){
		
		pnlInput.setLayout(new BoxLayout(pnlInput, BoxLayout.X_AXIS));
		pnlOutput.setLayout(new BoxLayout(pnlOutput, BoxLayout.X_AXIS));
		
		pnlInputLabels.setLayout(new BoxLayout(pnlInputLabels, BoxLayout.Y_AXIS));
		pnlInputTextFields.setLayout(new BoxLayout(pnlInputTextFields, BoxLayout.Y_AXIS));
		pnlOutputLabels.setLayout(new BoxLayout(pnlOutputLabels, BoxLayout.Y_AXIS));
		pnlOutputTextFields.setLayout(new BoxLayout(pnlOutputTextFields, BoxLayout.Y_AXIS));
		
		pnlInput.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
		
		txtAmount = new JFormattedTextField(formatter);
		txtSubtotal = new JFormattedTextField();
		txtTax = new JFormattedTextField();
		txtTotal = new JFormattedTextField();
		txtState = new JTextField();
		
		txtAmount.setMaximumSize(dimTxt);
		txtState.setMaximumSize(dimTxt);
		txtSubtotal.setMaximumSize(dimTxt);
		txtTax.setMaximumSize(dimTxt);
		txtTotal.setMaximumSize(dimTxt);
		
		lblSubtotal.setVisible(false);
		lblTax.setVisible(false);
		txtSubtotal.setVisible(false);
		txtTax.setVisible(false);
		
		txtSubtotal.setEditable(false);
		txtTax.setEditable(false);
		txtTotal.setEditable(false);
		
		lblAmount.setMaximumSize(dimTxt);
		lblSubtotal.setMaximumSize(dimTxt);
		lblTax.setMaximumSize(dimTxt);
		lblTotal.setMaximumSize(dimTxt);
		lblState.setMaximumSize(dimTxt);
		
		btnCalc.setPreferredSize(new Dimension(200, 50));
		btnReset.setPreferredSize(new Dimension(200, 30));
		
		this.add(pnlInput);
		this.add(pnlOutput);
		this.add(pnlCalculate);
		
		
		pnlInput.add(pnlInputLabels);
		pnlInput.add(pnlInputTextFields);
		
		pnlInputLabels.add(lblAmount);
		pnlInputLabels.add(lblState);
		
		pnlInputTextFields.add(txtAmount);
		pnlInputTextFields.add(txtState);
		
		pnlOutput.add(pnlOutputLabels);
		pnlOutput.add(pnlOutputTextFields);
		
		pnlOutputLabels.add(lblSubtotal);
		pnlOutputLabels.add(lblTax);
		pnlOutputLabels.add(lblTotal);
		
		pnlOutputTextFields.add(txtSubtotal);
		pnlOutputTextFields.add(txtTax);
		pnlOutputTextFields.add(txtTotal);
		
		pnlCalculate.add(btnCalc);
		pnlCalculate.add(btnReset);
		
		btnReset.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearFields();
			}
		});
		
		validate();
	}
	
	public void addButtonListener(ActionListener listener){
		btnCalc.addActionListener(listener);
	}
	
	public Order getOrder(){
		if(!txtAmount.getText().isEmpty() && !txtState.getText().isEmpty() && Integer.parseInt(txtAmount.getText()) != 0){
			Order order = new Order(Integer.parseInt(txtAmount.getText()), txtState.getText());
			return order;			
		}
		return null;
	}
	
	public void setTotal(double total){
		toggleTaxFields(false);
		txtTotal.setText("$"+numberFormat.format(total));
	}
	
	public void setTotal(double total, double subtotal, double tax){
		toggleTaxFields(true);
		txtSubtotal.setText("$"+numberFormat.format(subtotal));
		txtTax.setText("$"+numberFormat.format(tax));
		txtTotal.setText("$"+numberFormat.format(total));
	}
	
	public void toggleTaxFields(boolean state){
		txtSubtotal.setVisible(state);
		lblSubtotal.setVisible(state);
		txtTax.setVisible(state);
		lblTax.setVisible(state);
	}
	
	public void clearFields(){
		txtTotal.setText("");
		txtState.setText("");
		txtAmount.setText("0");
		toggleTaxFields(false);
	}
}
