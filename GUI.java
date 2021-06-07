import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GUI {
    public static ArrayList<String> arrayList = new ArrayList<>();
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
        main_panel.setBackground(Color.lightGray);
        JLabel input_currency_label = new JLabel("From:");
        input_currency_label.setForeground(Color.black);
        input_currency_label.setFont(new Font("", Font.PLAIN, 16));
        JLabel output_currency_label = new JLabel("To:");
        output_currency_label.setForeground(Color.black);
        output_currency_label.setFont(new Font("", Font.PLAIN, 16));
        main_panel.add(input_currency_label);
        main_panel.add(output_currency_label);

        JSONParser jsonP = new JSONParser();
        try (FileReader fr = new FileReader("currencies.json")) {
            Object obj = jsonP.parse(fr);
            JSONArray empList = (JSONArray) obj;
            empList.forEach(emp -> parseEmpObj((JSONObject) emp));

            String[] curr_list = new String[118];
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



        frame.add(main_panel);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static void parseEmpObj(JSONObject emp) {
        String currency = (String) emp.get("code");
        arrayList.add(currency);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
