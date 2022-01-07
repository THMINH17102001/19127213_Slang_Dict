import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.util.*;
import java.io.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Find extends JFrame implements ActionListener, TableModelListener {
    Container container = getContentPane();
    JButton findBtn, addBtn, deleteBtn, editBtn, resetBtn, randomBtn, backBtn;
    JTextField findTextfield;
    JComboBox chooseBox;
    JPanel panel1, findWordsPanel, panel2, rightPanel, bottomPanel;
    DefaultTableModel model;
    JTable resultTable;
    SlangList slangList;
    JPanel leftPanel;

    AutoSuggestor autoSuggestor;
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
        autoSuggestor = new AutoSuggestor(findTextfield,this,null, Color.WHITE.brighter(), Color.BLACK, Color.RED, 0.75f) {
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
        resultTable.getModel().addTableModelListener(this);
        JScrollPane sp = new JScrollPane(resultTable);

        panel2.setLayout(new GridLayout(1, 1));
        panel2.add(sp);

        rightPanel = new JPanel(new BorderLayout());
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

        rightPanel.add(historyPane, BorderLayout.PAGE_START);
        rightPanel.add(new JScrollPane(historyList));


        JPanel outsideBottomPanel = new JPanel();
        outsideBottomPanel.setLayout(new FlowLayout());
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        addBtn = new JButton("Add");
        deleteBtn = new JButton("Delete");
        editBtn = new JButton("Edit");
        resetBtn = new JButton("Reset");
        randomBtn = new JButton("Random");
        backBtn = new JButton("Back"); backBtn.setBackground(Color.PINK);
        bottomPanel.add(Box.createRigidArea(new Dimension(20,0)));
        bottomPanel.add(addBtn);
        bottomPanel.add(Box.createRigidArea(new Dimension(20,0)));
        bottomPanel.add(deleteBtn);
        bottomPanel.add(Box.createRigidArea(new Dimension(20,0)));
        bottomPanel.add(editBtn);
        bottomPanel.add(Box.createRigidArea(new Dimension(20,0)));
        bottomPanel.add(resetBtn);
        bottomPanel.add(Box.createRigidArea(new Dimension(20,0)));
        bottomPanel.add(randomBtn);
        bottomPanel.add(Box.createRigidArea(new Dimension(20,0)));
        bottomPanel.add(backBtn);
        outsideBottomPanel.add(bottomPanel);


        leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(panel1, BorderLayout.PAGE_START);
        leftPanel.add(panel2,BorderLayout.CENTER);
        leftPanel.add(outsideBottomPanel, BorderLayout.PAGE_END);
    }

    public void addComponentsToContainer() {
        container.add(leftPanel, BorderLayout.CENTER);
        container.add(rightPanel, BorderLayout.EAST);
    }


    public void addActionEvent() {
        findBtn.addActionListener(this);
        addBtn.addActionListener(this);
        editBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        resetBtn.addActionListener(this);
        randomBtn.addActionListener(this);
        backBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == findBtn){
            String history = findTextfield.getText();
            String find = findTextfield.getText();
            if (find.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Empty word");
                return;
            }
            else {
                this.clearTable();
                historyListModel.insertElementAt(history, 0);
                historyArr.add(0, history);
                Integer findChoice = chooseBox.getSelectedIndex();
                System.out.println("findChoice: " + findChoice);
                if(findChoice == 0){
                    ArrayList<String> temp = slangList.getSlangDefinition(find);
                    String[][] s = new String[temp.size()][2];
                    int i = 0;
                    for(String x : temp){
                        s[i][0] = find;
                        s[i][1] = x;
                        i++;
                    }
                    for (String[]x : s) {
                        model.addRow(x);
                    }
                }
                else{

                }
                try {
                    slangList.saveHistory(historyArr);
                } catch (IOException ae) {
                    ae.printStackTrace();
                }
            }
        }
        else if(e.getSource() == addBtn){

        }
        else if(e.getSource() == editBtn){

        }
        else if(e.getSource() == deleteBtn){

        }
        else if(e.getSource() == resetBtn){
            slangList.reset();
            autoSuggestor = null;
            autoSuggestor = new AutoSuggestor(findTextfield,this,null, Color.WHITE.brighter(), Color.BLACK, Color.RED, 0.75f) {
                @Override
                boolean wordTyped(String typedWord) {
                    ArrayList<String> words = slangList.getSlangKeyList();
                    setDictionary(words);
                    return super.wordTyped(typedWord);//now call super to check for any matches against newest dictionary
                }
            };

        }
        else if(e.getSource() == randomBtn){

        }
        else if(e.getSource() == backBtn){
            dispose();
            Menu menu = new Menu();
            menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            menu.setSize(1000,1000);
            menu.setDefaultLookAndFeelDecorated(true);
            menu.setTitle("Slang words");
            menu.setVisible(true);
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        /**int row = jt.getSelectedRow();
        int col = jt.getSelectedColumn();
        if (row == col && row == -1)
            return;
        String Data = (String) jt.getValueAt(row, col);
        System.out.println("Table element selected is: " + row + col + " : " + Data);
        if (col == 2) {
            // edit meaning
            slangList.set((String) jt.getValueAt(row, 1), result[row][2], (String) jt.getValueAt(row, 2));
            JOptionPane.showMessageDialog(this, "Updated a row.");
        }
        jt.setFocusable(false);*/
    }

    void clearTable() {
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

}
