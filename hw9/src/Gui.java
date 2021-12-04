
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
        
    }

}

public class Gui {
    static JFrame f;

    public void show(){  
    f = new JFrame("141OS");//creating instance of JFrame  

    JLabel name = new JLabel("<html>Name: Keyu Zhang<br/><br/>UCINetID: keyuz4</html>");
    JLabel speed = new JLabel("<html>Simulation speed slider:<br/>higher number means faster speed<br/>original speed: 100</html>");

    JButton start = new JButton("START");    
    start.addActionListener(new StartButtonListener(start));

    JSlider slider = new JSlider(JSlider.VERTICAL, 0, 200, 100);  
    slider.setMinorTickSpacing(5);  
    slider.setMajorTickSpacing(25);  
    slider.setPaintTicks(true);  
    slider.setPaintLabels(true);  

    JButton user1 = new JButton("USER1");    
    JButton user2 = new JButton("USER2");    
    JButton user3 = new JButton("USER3");    
    JButton user4 = new JButton("USER4");    

    JButton printer1 = new JButton("PAINTER1");    
    JButton printer2 = new JButton("PAINTER2");    
    JButton printer3 = new JButton("PAINTER3");    

    JButton disk1 = new JButton("DISK1");
    JButton disk2 = new JButton("DISK2");

    f.add(name); f.add(start); f.add(speed); f.add(slider);
    f.add(user1); f.add(user2); f.add(user3); f.add(user4);
    f.add(printer1); f.add(printer2); f.add(printer3); f.add(new JLabel());
    f.add(disk1); f.add(disk2); f.add(new JLabel()); f.add(new JLabel());

    f.setSize(1000,1000);//400 width and 500 height  
    f.setLayout(new GridLayout(4, 4, 20, 25));    
    f.setVisible(true);//making the frame visible  

    }  
}
