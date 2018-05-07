import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Calculator extends JFrame implements ActionListener {
      private double a, b, result;
      private JTextField numJtf1;
      private JButton symbol;
      private JTextField numJtf2;
      private JButton equal;
      private JButton numJtf3;
	
      private JButton plus;
      private JButton minus;
      private JButton multiplication;
      private JButton division;
      private JButton ok;

      public Calculator() {
            Container c = getContentPane();
            setLayout(new GridLayout(2, 5, 3, 3));
            setTitle("Calaculator");
            plus = new JButton("+");
            minus = new JButton("-");
            multiplication = new JButton("*");
            division = new JButton("/");
            ok = new JButton("OK");

            numJtf1 = new JTextField(" ", 5);
            symbol = new JButton();
            numJtf2 = new JTextField(" ", 5);
            equal = new JButton("=");
            numJtf3 = new JButton();

            c.add(numJtf1);
            c.add(symbol);
            c.add(numJtf2);
            c.add(equal);
            c.add(numJtf3);

            c.add(plus);
            c.add(minus);
            c.add(multiplication);
            c.add(division);
            c.add(ok);

            plus.addActionListener(this);
            minus.addActionListener(this);
            multiplication.addActionListener(this);
            division.addActionListener(this);
            ok.addActionListener(this);
            setSize(800, 100);
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      }

      public void plus() {
            a = Double.parseDouble(numJtf1.getText().trim());
            b = Double.parseDouble(numJtf2.getText().trim());
            symbol.setText("+");
            result = a + b;
      }

      public void minus() {
            a = Double.parseDouble(numJtf1.getText().trim());
            b = Double.parseDouble(numJtf2.getText().trim());
            symbol.setText("-");
            result = a - b;
      }

      public void multiplication() {
            a = Double.parseDouble(numJtf1.getText().trim());
            b = Double.parseDouble(numJtf2.getText().trim());
            symbol.setText("*");
            result = a * b;
      }
	
      public void division() {
            a = Double.parseDouble(numJtf1.getText().trim());
            b = Double.parseDouble(numJtf2.getText().trim());
            symbol.setText("/");
            if(b == 0) {
                  JOptionPane.showMessageDialog(this, "Divisor cannot be zero!");
            } 
            else {
                  result = a / b;
            }
      }
	
      public void ok() {
            numJtf3.setText("" + result);
      }

      public void actionPerformed(ActionEvent e) {
            if (e.getSource() == plus) {
                  plus();
            } 
            else if (e.getSource() == minus) {
                  minus();
            } 
            else if (e.getSource() == multiplication) {
                  multiplication();
            }
            else if (e.getSource() == division) {
                  division();
            } 
            else if(e.getSource() == ok){
                  ok();
            }
      }

      public static void main(String[] arges) {
            new Calculator();
      }
}
