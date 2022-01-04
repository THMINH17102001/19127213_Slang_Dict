import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.*;
import javax.swing.event.*;

public class Find extends JPanel {
    JLabel label1 = new JLabel("Input Slang word or Slang definition");
    JButton findBtn;
    JTextField findTextfield;
    JComboBox autoSuggestBox;
    JComboBox chooseBox;
    JPanel panel1, findWordsPanel;
    SlangList slangList;
    public Find(){
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }
    public void setLayoutManager() {
        setLayout(new BorderLayout());
    }

    public void setLocationAndSize() {
        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.LINE_AXIS));
        findWordsPanel = new JPanel();
        findWordsPanel.setLayout(new BoxLayout(findWordsPanel, BoxLayout.LINE_AXIS));

        String[] choices = {"Find by Slang word", "Find by Slang definition"};
        chooseBox = new JComboBox(choices);
        findWordsPanel.add(label1);

        findWordsPanel.add(Box.createRigidArea(new Dimension(20,20)));
        findTextfield = new JTextField();
        autoSuggestBox = new JComboBox();
        findTextfield.setLayout(new BorderLayout());
        findTextfield.add(autoSuggestBox, BorderLayout.SOUTH);
        /**JComboBox cbInput = new JComboBox(model) {
            public Dimension getPreferredSize() {
                return new Dimension(super.getPreferredSize().width, 0);
            }
        };*/

        findWordsPanel.add(findTextfield);

        findWordsPanel.add(Box.createRigidArea(new Dimension(20,20)));
        findWordsPanel.add(chooseBox);

        findWordsPanel.add(Box.createRigidArea(new Dimension(20,20)));
        findBtn = new JButton("Find");
        findWordsPanel.add(findBtn);

        findWordsPanel.setAlignmentX(CENTER_ALIGNMENT);

        panel1.setBorder(BorderFactory.createEmptyBorder(50,10,10,10));
        panel1.add(findWordsPanel);
    }

    public void addComponentsToContainer() {
        add(panel1, BorderLayout.PAGE_START);
    }


    public void addActionEvent() {
        slangList = SlangList.getList();

        findTextfield.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                //updateList();
            }

            public void removeUpdate(DocumentEvent e) {
                //updateList();
            }

            public void changedUpdate(DocumentEvent e) {
                //updateList();
            }

            /**private void updateList() {
                setAdjusting(autoSuggestBox, true);
                model.removeAllElements();
                String input = txtInput.getText();
                if (!input.isEmpty()) {
                    for (String item : items) {
                        if (item.toLowerCase().startsWith(input.toLowerCase())) {
                            model.addElement(item);
                        }
                    }
                }
                cbInput.setPopupVisible(model.getSize() > 0);
                setAdjusting(cbInput, false);
            }*/
        });


    }

}
