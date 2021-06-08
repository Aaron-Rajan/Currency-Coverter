import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GUI implements ActionListener {
    public static ArrayList<String> arrayList = new ArrayList<>();
    public static String[] curr_list;
    public static JComboBox<String> from_curr;
    public static JComboBox<String> to_curr;

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
        main_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        main_panel.setBackground(Color.lightGray);
        JLabel input_currency_label = new JLabel("From:");
        input_currency_label.setForeground(Color.black);
        input_currency_label.setFont(new Font("", Font.PLAIN, 16));
        JLabel output_currency_label = new JLabel("To:");
        output_currency_label.setForeground(Color.black);
        output_currency_label.setFont(new Font("", Font.PLAIN, 16));

        JSONParser jsonP = new JSONParser();
        try (FileReader fr = new FileReader("currencies.json")) {
            Object obj = jsonP.parse(fr);
            JSONArray empList = (JSONArray) obj;
            empList.forEach(emp -> parseEmpObj((JSONObject) emp));

            curr_list = new String[118];
            for (int i=0; i<118; i++) {
                curr_list[i] = arrayList.get(i);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        from_curr = new JComboBox<>(curr_list);
        from_curr.setEditable(true);
        from_curr.setSelectedIndex(0);
        from_curr.setPreferredSize(new Dimension(60,25));
        from_curr.addActionListener(this);
        to_curr = new JComboBox<>(curr_list);
        to_curr.setEditable(true);
        to_curr.setSelectedIndex(0);
        to_curr.setPreferredSize(new Dimension(60,25));
        to_curr.addActionListener(this);

        main_panel.add(input_currency_label);
        main_panel.add(from_curr);
        main_panel.add(output_currency_label);
        main_panel.add(to_curr);

        frame.add(main_panel,BorderLayout.CENTER);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static void parseEmpObj(JSONObject emp) {
        String currency = (String) emp.get("code");
        arrayList.add(currency);
    }
    public static boolean verification(String s) {
        boolean b = false;
        for (String element: curr_list) {
            if (element.equals(s)) {
                b = true;
            }
        }
        return b;

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("comboBoxChanged") && verification((String) from_curr.getSelectedItem()) && verification((String) to_curr.getSelectedItem())) {
            if(e.getSource() == from_curr) {
                String curr_1 = (String) from_curr.getSelectedItem();
                System.out.println(curr_1);
            }
            else if (e.getSource() == to_curr) {
                String curr_2 = (String) to_curr.getSelectedItem();
                System.out.println(curr_2);
            }
        }
    }
    public static void main(String[] args) {
        new GUI();
    }
}
