import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame {
         
	private JPanel panel;
	private JTextField display;
	private JButton[] buttons;
	private String[] buttonLabels = {
		"7","8","9","/",
		"4","5","6","*",
		"1","2","3","-",
		"0",".","=","+"
	    };

	private double result = 0;
	private String operator = "";
	private boolean isOperatorClicked = false;

	public Calculator() {
		panel = new JPanel();
		display = new JTextField(10);
		buttons = new JButton[buttonLabels.length];

		panel.setLayout(new GridLayout(4,4));

		for (int i = 0; i < buttonLabels.length; i++) {
			buttons[i] = new JButton(buttonLabels[i]);
			buttons[i].addActionListener(new ButtonClickListener());
			panel.add(buttons[i]);
                   }

		add(display, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);

		setTitle("Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setVisible(true);
            }

		private class ButtonClickListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				String command = e.getActionCommand();

			if(command.matches("[0-9.]")) {
				if (isOperatorClicked) {
					display.setText("");
					isOperatorClicked = false;
			}

			display.setText(display.getText() + command);
			} else if (command.matches("[/,*,-,+]")) {
				calculate();
				operator = command;
				result = Double.parseDouble(display.getText());
				isOperatorClicked = true;
			} else if (command.equals("=")) {
				calculate();
				operator = "";
                   }
		}

		private void calculate() {
			if(operator.equals("/")) {
				result /= Double.parseDouble(display.getText());
			} else if(operator.equals("*")) {
				result *= Double.parseDouble(display.getText());
			} else if(operator.equals("-")) {
				result -= Double.parseDouble(display.getText());
			} else if(operator.equals("+")) {
				result += Double.parseDouble(display.getText());
			} else {
				result = Double.parseDouble(display.getText());
			}

			display.setText(String.valueOf(result));
		}
	}

		public static void main(String[] args) {
			Calculator calculator = new Calculator();
		}
	}