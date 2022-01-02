import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menu extends JFrame implements ActionListener {

    JPanel firstPanel;
    JButton btn1 = new JButton("1. Find Slang word");
    JButton btn2 = new JButton("2. View history");
    JButton btn3 = new JButton("3. Add Slang word");
    JButton btn4 = new JButton("4. Edit Slang word");
    JButton btn5 = new JButton("5. Delete Slang word");
    JButton btn6 = new JButton("6. Reset Slang word list");
    JButton btn7 = new JButton("7. Random Slang word");
    JButton btn8 = new JButton("8. Slang word questions");

    public Menu() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLayoutManager() {
        setLayout(new GridBagLayout());

    }

    public void setLocationAndSize() {
        firstPanel = new JPanel();
        firstPanel.setLayout(new GridBagLayout());

        btn1.setPreferredSize(new Dimension(300, 50));
        btn2.setPreferredSize(new Dimension(300, 50));
        btn3.setPreferredSize(new Dimension(300, 50));
        btn4.setPreferredSize(new Dimension(300, 50));
        btn5.setPreferredSize(new Dimension(300, 50));
        btn6.setPreferredSize(new Dimension(300, 50));
        btn7.setPreferredSize(new Dimension(300, 50));
        btn8.setPreferredSize(new Dimension(300, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        firstPanel.add(btn1, gbc);
        gbc.gridy = 1;
        firstPanel.add(btn2, gbc);
        gbc.gridy = 2;
        firstPanel.add(btn3, gbc);
        gbc.gridy = 3;
        firstPanel.add(btn4, gbc);
        gbc.gridy = 4;
        firstPanel.add(btn5, gbc);
        gbc.gridy = 5;
        firstPanel.add(btn6, gbc);
        gbc.gridy = 6;
        firstPanel.add(btn7, gbc);
        gbc.gridy = 7;
        firstPanel.add(btn8, gbc);

    }

    public void addComponentsToContainer() {
        add(firstPanel, new GridBagConstraints());
    }

    public void addActionEvent() {
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
        btn6.addActionListener(this);
        btn7.addActionListener(this);
        btn8.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btn1){

        }else if(e.getSource() == btn2){

        }else if(e.getSource() == btn3){

        }else if(e.getSource() == btn4){

        }else if(e.getSource() == btn5){

        }else if(e.getSource() == btn6){

        }else if(e.getSource() == btn7){

        }else if(e.getSource() == btn8){

        }
    }
}
