
import javax.swing.*;  
import java.awt.*;
import java.awt.event.*;

class StartButtonListener implements ActionListener {
    JButton button;

    StartButtonListener(JButton b) {
        button = b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        button.setBackground(Color.black);
        System.out.println("clicked");
        mainClass.startUserThreads(4);
    
        for (int i = 1; i < 5; i++) {
            mainClass.gui.changeButtonStatus("user", i, "idle", Color.green);
        }
        for (int i = 1; i < 4; i++) {
            mainClass.gui.changeButtonStatus("printer", i, "idle", Color.green);
        }
        for (int i = 1; i < 3; i++) {
            mainClass.gui.changeButtonStatus("disk", i, "idle", Color.green);
        }
        
    }

}

public class Gui {
    static JFrame f;
    static JButton[] user;
    static JButton[] printer;
    static JButton[] disk;
    static JSlider slider;

    public void changeButtonStatus(String type, int num, String text, Color color){
        text = "<html>" + type.toUpperCase() + num + ": " + text + "</html>";
        switch (type) {
            case "user":
                user[num-1].setText(text);
                user[num-1].setBackground(color);
                break;
            case "printer":
                printer[num-1].setText(text);
                printer[num-1].setBackground(color);
                break;
            case "disk":
                disk[num-1].setText(text);
                disk[num-1].setBackground(color);
                break;
        }
    }

    public static double getSpeed() {
        return 100.0 / (double) slider.getValue();
    }

    public void show(){  
    f = new JFrame("141OS");//creating instance of JFrame  
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JLabel name = new JLabel("<html>Name: Keyu Zhang<br/><br/>UCINetID: keyuz4</html>");
    JLabel speed = new JLabel("<html>Simulation speed slider:<br/>higher number means faster speed<br/>original speed: 100</html>");

    JButton start = new JButton("START");    
    start.addActionListener(new StartButtonListener(start));

    slider = new JSlider(JSlider.VERTICAL, 0, 200, 100);  
    slider.setMinorTickSpacing(5);  
    slider.setMajorTickSpacing(25);  
    slider.setPaintTicks(true);  
    slider.setPaintLabels(true);  

    user = new JButton[4];
    printer = new JButton[3];
    disk = new JButton[2];

    for (int i = 0; i < 4; i++) {
        user[i] = new JButton("USER"+(i+1));
    }
    for (int i = 0; i < 3; i++) {
        printer[i] = new JButton("PRINTER"+(i+1));
    }
    for (int i = 0; i < 2; i++) {
        disk[i] = new JButton("DISK"+(i+1));
    }

    f.add(name); f.add(start); f.add(speed); f.add(slider);
    for (JButton b : user) {
        f.add(b);
    }
    for (JButton b : printer) {
        f.add(b);
    }
    f.add(new JLabel());
    for (JButton b : disk) {
        f.add(b);
    }

    f.setSize(1200,1000);//400 width and 500 height  
    f.setLayout(new GridLayout(4, 4, 20, 25));    
    f.setVisible(true);//making the frame visible  

    }  
}
