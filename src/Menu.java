import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menu extends JFrame implements ActionListener {

    JPanel firstPanel;
    JButton btn1 = new JButton("1. Slang List");
    JButton btn2 = new JButton("2. Slang quizz");

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

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        firstPanel.add(btn1, gbc);
        gbc.gridy = 1;
        firstPanel.add(btn2, gbc);
        gbc.gridy = 2;

    }

    public void addComponentsToContainer() {
        add(firstPanel, new GridBagConstraints());
    }

    public void addActionEvent() {
        btn1.addActionListener(this);
        btn2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btn1){
            getContentPane().removeAll();
            Find find = new Find();
            find.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            find.setDefaultLookAndFeelDecorated(true);
            find.setSize(800,800);
            setVisible(false);
            find.setVisible(true);
        }else if(e.getSource() == btn2){

        }
    }
}
