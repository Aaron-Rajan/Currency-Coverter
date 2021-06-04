import javax.swing.*;
import java.awt.*;

public class GUI {
    public GUI() {
        JFrame frame = new JFrame();
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Currency Converter");

        JPanel title_panel = new JPanel();
        title_panel.setPreferredSize(new Dimension(100, 50));
        title_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        title_panel.setBackground(Color.black);
        JLabel title = new JLabel("Currency Converter");
        title.setForeground(Color.yellow);
        title.setFont(new Font("Serif", Font.PLAIN, 22));
        title_panel.add(title);
        frame.add(title_panel, BorderLayout.NORTH);

        JPanel main_panel = new JPanel();
        main_panel.setPreferredSize(new Dimension(100, 300));
        main_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 75, 10));
        main_panel.setBackground(Color.darkGray);
        JLabel input_currency_label = new JLabel("From:");
        input_currency_label.setForeground(Color.black);
        input_currency_label.setFont(new Font("", Font.PLAIN, 16));
        JLabel output_currency_label = new JLabel("To:");
        output_currency_label.setForeground(Color.black);
        output_currency_label.setFont(new Font("", Font.PLAIN, 16));
        main_panel.add(input_currency_label);
        main_panel.add(output_currency_label);
        frame.add(main_panel);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
