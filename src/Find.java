import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.*;
import javax.swing.event.*;

public class Find extends JFrame {
    Container container = getContentPane();
    JLabel label1 = new JLabel("Input Slang word or Slang definition");
    JButton findBtn;
    JTextField findTextfield;
    JComboBox chooseBox;
    JPanel panel1, findWordsPanel;
    SlangList slangList;
    public Find(){
        slangList = SlangList.getList();
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
        AutoSuggestor autoSuggestor = new AutoSuggestor(findTextfield,this,null, Color.WHITE.brighter(), Color.BLACK, Color.RED, 0.75f) {
            @Override
            boolean wordTyped(String typedWord) {
                ArrayList<String> words = slangList.getSlangKeyList();
                setDictionary(words);
                return super.wordTyped(typedWord);//now call super to check for any matches against newest dictionary
            }
        };
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
        container.add(panel1, BorderLayout.PAGE_START);
    }


    public void addActionEvent() {

    }

}
