import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Find extends JFrame implements ActionListener{
    Container container = getContentPane();
    JButton findBtn;
    JTextField findTextfield;
    JComboBox chooseBox;
    JPanel panel1, findWordsPanel, panel2, panelRight, panelBottom;
    DefaultTableModel model;
    JTable resultTable;
    SlangList slangList;
    JPanel leftPanel;

    private ArrayList<String> historyArr;
    private DefaultListModel<String> historyListModel;
    private JList<String> historyList;

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

        panel2 = new JPanel();
        String column[] = { "Slang", "Definition" };


        resultTable = new JTable(new DefaultTableModel(column, 0));
        resultTable.setRowHeight(30);
        model = (DefaultTableModel) resultTable.getModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        resultTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        resultTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        JScrollPane sp = new JScrollPane(resultTable);

        panel2.setLayout(new GridLayout(1, 1));
        panel2.add(sp);

        panelRight = new JPanel(new BorderLayout());
        historyListModel = new DefaultListModel<>();
        try {
            historyArr = slangList.getHistory();
            for(String x : historyArr){
                historyListModel.addElement(x);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        historyList = new JList<>(historyListModel);
        JLabel historyLabel = new JLabel("History");
        JPanel historyPane = new JPanel();
        historyPane.setPreferredSize(new Dimension(200,50));
        historyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        historyPane.add(historyLabel);
        panelRight.add(historyPane, BorderLayout.PAGE_START);
        panelRight.add(new JScrollPane(historyList));

        leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(panel1, BorderLayout.PAGE_START);
        leftPanel.add(panel2,BorderLayout.CENTER);

    }

    public void addComponentsToContainer() {
        container.add(leftPanel, BorderLayout.CENTER);
        container.add(panelRight, BorderLayout.EAST);
    }


    public void addActionEvent() {
        findBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == findBtn){
            String history = findTextfield.getText();
            historyListModel.insertElementAt(history, 0);
            historyArr.add(0, history);
            try {
                slangList.saveHistory(historyArr);
            }
            catch(IOException ae){
                ae.printStackTrace();
            }
        }

    }

}
