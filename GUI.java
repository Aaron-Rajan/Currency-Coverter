import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GUI implements ActionListener {
    private static ArrayList<String> arrayList = new ArrayList<>();
    private static String[] curr_list;
    private static JComboBox<String> from_curr;
    private static JComboBox<String> to_curr;
    private static JTextField input_curr;
    private static JLabel output_curr;
    private static String curr_1;
    private static String curr_2;
    private static String input_value;
    private static double rate;
    public static ArrayList<String> arrayList_2 = new ArrayList<>();
    private static String name_1;
    private static String name_2;
    private static JLabel curr_line;
    
    public GUI() {
        JFrame frame = new JFrame();
        frame.setSize(500, 355);
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

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(new BorderLayout());


        JPanel main_panel = new JPanel();
        main_panel.setPreferredSize(new Dimension(100, 60));
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

        JPanel curr_panel = new JPanel();
        curr_panel.setBackground(Color.lightGray);
        curr_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
        input_curr = new JTextField();
        input_curr.setFont(new Font("", Font.BOLD, 18));
        input_curr.setPreferredSize(new Dimension(110, 40));
        input_curr.addActionListener(this);
        output_curr = new JLabel("");
        output_curr.setPreferredSize(new Dimension(120, 25));
        output_curr.setForeground(Color.black);
        output_curr.setFont(new Font("", Font.BOLD, 18));


        curr_panel.add(input_curr);
        curr_panel.add(output_curr);

        curr_line = new JLabel("");
        curr_line.setPreferredSize(new Dimension(155, 160));
        curr_line.setForeground(Color.black);
        curr_line.setFont(new Font("", Font.BOLD, 16));
        curr_panel.add(curr_line);


        panel_1.add(curr_panel, BorderLayout.CENTER);
        panel_1.add(main_panel, BorderLayout.NORTH);
        
        frame.add(panel_1, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static void parseEmpObj(JSONObject emp) {
        String currency = (String) emp.get("code");
        arrayList.add(currency);
        String name = (String) emp.get("name");
        arrayList_2.add(name);

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
    public static double get_rate(String curr_1, String curr_2) {
        double rate = 0;
        try {
            URL url = new URL("https://free.currconv.com/api/v7/convert?q=" + curr_1 + "_" + curr_2 + "&compact=ultra&apiKey=db509adad901627f19a3");
            String readline = null;
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("USD_PHP", "2");
            int response_code = con.getResponseCode();

            if (response_code == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuffer response = new  StringBuffer();
                while ((readline = br.readLine()) != null) {
                    response.append(readline);
                }
                br.close();
                response.replace(0,11,"");
                response.replace(response.length() - 1,response.length(),"");
                rate = Double.parseDouble(response.toString());
            }
            return rate;
        }
        catch (Exception e) {
            return rate;
        }
    }
    public static void convert(String input_value, String curr_1, String curr_2) {
        try {
            if (Double.parseDouble(input_value) == 0) {
                output_curr.setText("");
            }
            rate = get_rate(curr_1, curr_2);
            double output_value = rate * Double.parseDouble(input_value);
            double output_value_rnd = Math.round(output_value*1000.0)/1000.0;
            String output_value_string = "" + output_value_rnd + "";

            if (output_value != 0) {
                output_curr.setText("<html><div style='text-align: center;'>" + output_value_string + "</div><html>");
            }
        }
        catch (Exception e) {
        }
    }
    public static void convert_line() {
        try {
            if ((rate != 0) && (!name_2.equals(null))) {
                String text = "<html>1 " + name_1 + " equals " + rate + " " + name_2 +"<html>";
                curr_line.setText("<html><div style='text-align: center;'>" + text + "</div><html>");
            }

            if (Double.parseDouble(input_value) == 0) {
                curr_line.setText("");
            }
        }
        catch (Exception e) {
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("comboBoxChanged") && verification((String) from_curr.getSelectedItem()) && verification((String) to_curr.getSelectedItem())) {
            if(e.getSource() == from_curr) {
                curr_1 = (String) from_curr.getSelectedItem();
                int name_1_index = from_curr.getSelectedIndex();
                name_1 = arrayList_2.get(name_1_index);
            }
            else if (e.getSource() == to_curr) {
                curr_2 = (String) to_curr.getSelectedItem();
                int name_2_index = to_curr.getSelectedIndex();
                name_2 = arrayList_2.get(name_2_index);
            }
        }
        if (verification((String) from_curr.getSelectedItem()) && verification((String) to_curr.getSelectedItem())) {
            input_value = input_curr.getText();
            if (!input_value.equals(null)) {
                convert(input_value, curr_1, curr_2);
                convert_line();
            }
        }
    }
    public static void main(String[] args) {
        new GUI();
    }
}
