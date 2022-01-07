import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Random extends JFrame implements ActionListener {
    Container con = getContentPane();
    DefaultTableModel model;
    JTable jtable;
    SlangList sList;
    JLabel title = new JLabel("Today random slang word ");
    JPanel panel;
    JScrollPane jScrollPane;
    JButton okBtn;
    public Random(SlangList slangList){
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

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(1,1));
        String column[] = { "Slang", "Definition" };
        jtable = new JTable(new DefaultTableModel(column, 0));
        jtable.setRowHeight(40);
        model = (DefaultTableModel) jtable.getModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jtable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        jtable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        jScrollPane = new JScrollPane(jtable);

        tablePanel.add(jScrollPane);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());
        okBtn = new JButton("OK");
        btnPanel.add(okBtn);
        panel.add(titlePanel);
        panel.add(tablePanel);
        panel.add(btnPanel);

        ArrayList<String> temp = sList.random();
        String[][] s = new String[temp.size()][2];
        int i = 0;
        String key = temp.get(0);
        for (String x : temp) {
            if(i > 0) {
                s[i][0] = key;
                s[i][1] = x;
            }
            i++;
        }
        i = 0;
        for (String[] x : s) {
            if (i > 0)
                model.addRow(x);
            i++;
        }


    }
    public void addComponentsToContainer(){
        con.add(panel, new GridBagConstraints());
    }


    public void addActionEvent(){
        okBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == okBtn){
            this.dispose();
        }
    }
}
