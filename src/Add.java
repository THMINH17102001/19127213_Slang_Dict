import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Add extends JFrame implements ActionListener {
    Container con = getContentPane();
    SlangList sList;
    JLabel title = new JLabel("Add Slang word");
    JLabel newSlangLabel = new JLabel("Slang word");
    JLabel newSlangDefLabel = new JLabel("Definition");
    JTextField newSlangText = new JTextField();
    JTextField newSlangDefText = new JTextField();
    JPanel panel;
    JButton addBtn, backBtn;
    public Add(SlangList slangList){
        this.sList = slangList;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setLayoutManager(){
        setLayout(new GridBagLayout());
    }
    public void setLocationAndSize(){
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        title.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(title);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        newSlangDefLabel.setPreferredSize(new Dimension(100,40));
        newSlangDefText.setPreferredSize(new Dimension(200,30));
        newSlangLabel.setPreferredSize(new Dimension(100,40));
        newSlangText.setPreferredSize(new Dimension(200,30));

        gbc.insets = new Insets(50,0,0,0);
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(newSlangLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        formPanel.add(newSlangText, gbc);

        gbc.insets = new Insets(0,0,50,0);
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(newSlangDefLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        formPanel.add(newSlangDefText, gbc);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        btnPanel.add(Box.createRigidArea(new Dimension(20,0)));
        addBtn = new JButton("Add");
        btnPanel.add(addBtn);
        btnPanel.add(Box.createRigidArea(new Dimension(20,0)));
        backBtn = new JButton("Back");
        btnPanel.add(backBtn);

        panel.add(titlePanel);
        panel.add(formPanel);
        panel.add(btnPanel);


    }
    public void addComponentsToContainer(){
        con.add(panel, new GridBagConstraints());
    }


    public void addActionEvent(){
        addBtn.addActionListener(this);
        backBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == addBtn) {
            String slang = newSlangText.getText();
            String def = newSlangDefText.getText();
            if (slang.isEmpty() || def.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Slang or Definition can not be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (sList.contains(slang)) {
                Object[] options = { "Overwrite", "Duplicate" };
                int n = JOptionPane.showOptionDialog(this,
                        "Slang '" + slang+ "' has already exist on  SlangWord  List", "Warning",
                        JOptionPane.YES_NO_CANCEL_OPTION, 0,null, options, null);
                if (n == 0) {
                    // Overwrite
                    sList.add(3, slang, def);
                    JOptionPane.showMessageDialog(this, "Overwriting Slang word successfully");
                } else if (n == 1) {
                    // Duplicate
                    sList.add(2,slang, def);
                    JOptionPane.showMessageDialog(this, "Dupilicateing slang Word successfully");
                }
            } else {
                // Add new slag
                sList.add(1,slang, def);
                JOptionPane.showMessageDialog(this, "Adding new Slang Word successfully");
            }
            newSlangText.setText("");
            newSlangDefText.setText("");

        }
        if(e.getSource() == backBtn){
            this.dispose();
            Find find = new Find();
            find.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            find.setDefaultLookAndFeelDecorated(true);
            find.setSize(800,800);
            find.setVisible(true);

        }
    }

}
