import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import java.io.*;
import java.util.*;

public class Add extends JPanel {
    SlangList slangList;
    JButton btnBack, btnAdd;
    JTextField textFieldMeaning, textFieldSlang;
    JLabel titleLabel = new JLabel("Add Slang Words");
    public Add(){
        slangList = slangList.getList();
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

        // Form
        JPanel form = new JPanel();
        JPanel slagPanel = new JPanel();
        form.setBackground(Color.CYAN);
        SpringLayout layout = new SpringLayout();
        slagPanel.setLayout(layout);
        JLabel labelForSlang = new JLabel("Slang word: ");
        textFieldSlang = new JTextField("", 20);
        labelForSlang.setPreferredSize(new Dimension(100, 20));
        slagPanel.add(labelForSlang);
        slagPanel.add(textFieldSlang);
        layout.putConstraint(SpringLayout.WEST, labelForSlang, 6, SpringLayout.WEST, slagPanel);
        layout.putConstraint(SpringLayout.NORTH, labelForSlang, 6, SpringLayout.NORTH, slagPanel);
        layout.putConstraint(SpringLayout.WEST, textFieldSlang, 6, SpringLayout.EAST, labelForSlang);
        layout.putConstraint(SpringLayout.NORTH, textFieldSlang, 6, SpringLayout.NORTH, slagPanel);
        layout.putConstraint(SpringLayout.EAST, slagPanel, 6, SpringLayout.EAST, textFieldSlang);
        layout.putConstraint(SpringLayout.SOUTH, slagPanel, 6, SpringLayout.SOUTH, textFieldSlang);

        JPanel meaningPanel = new JPanel();
        SpringLayout layout1 = new SpringLayout();
        meaningPanel.setLayout(layout1);
        JLabel labelForMeaning = new JLabel("Meaning: ");
        labelForMeaning.setPreferredSize(new Dimension(100, 20));
        textFieldMeaning = new JTextField("", 20);
        meaningPanel.add(labelForMeaning);
        meaningPanel.add(textFieldMeaning);
        layout1.putConstraint(SpringLayout.WEST, labelForMeaning, 6, SpringLayout.WEST, meaningPanel);
        layout1.putConstraint(SpringLayout.NORTH, labelForMeaning, 6, SpringLayout.NORTH, meaningPanel);
        layout1.putConstraint(SpringLayout.WEST, textFieldMeaning, 6, SpringLayout.EAST, labelForMeaning);
        layout1.putConstraint(SpringLayout.NORTH, textFieldMeaning, 6, SpringLayout.NORTH, meaningPanel);
        layout1.putConstraint(SpringLayout.EAST, meaningPanel, 6, SpringLayout.EAST, textFieldMeaning);
        layout1.putConstraint(SpringLayout.SOUTH, meaningPanel, 6, SpringLayout.SOUTH, textFieldMeaning);

        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(0, 10)));
        form.add(slagPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        form.add(meaningPanel);
        // Button Back and button Add
        JPanel bottomPanel = new JPanel();
        btnBack = new JButton("Back ");
        // btnBack.addActionListener(this);
        btnBack.setFocusable(false);
        //btnBack.addActionListener(this);
        btnBack.setAlignmentX(CENTER_ALIGNMENT);
        btnAdd = new JButton("Add");
        // btnBack.addActionListener(this);
        btnAdd.setFocusable(false);
        //btnAdd.addActionListener(this);
        btnAdd.setAlignmentX(CENTER_ALIGNMENT);
        bottomPanel.add(btnBack);
        bottomPanel.add(btnAdd);

        // Setting content
        //setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(titleLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(form);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(bottomPanel);
        // Setting Frame
        /**
        this.setTitle("Add Slang word");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        */
    }

    public void setLayoutManager(){
        titleLabel.setForeground(Color.green);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titleLabel.setPreferredSize(new Dimension(300, 100));
    }
    public void setLocationAndSize(){

    }
    public void addComponentsToContainer(){

    }
    public void addActionEvent(){

    }

}
