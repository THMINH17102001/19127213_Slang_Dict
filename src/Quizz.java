import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quizz extends JFrame implements ActionListener {
    Container con = getContentPane();
    SlangList sList;
    JLabel title = new JLabel("CHOOSE THE CORRECT ANSWER");
    JLabel questionLabel;
    JButton btn1, btn2, btn3, btn4;
    JPanel panel;
    JButton nextBtn, backBtn;
    int qType;
    String[] quiz;
    public Quizz(SlangList slangList, int type){
        this.sList = slangList;
        this.qType = type;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setLayoutManager(){
        setLayout(new GridBagLayout());
    }
    public void setLocationAndSize(){
        quiz = sList.quiz(qType);
        if(qType == 1)
            questionLabel = new JLabel("What does slang '" + quiz[0] + "' mean ?");
        else
            questionLabel = new JLabel("What is the slang word for \"" + quiz[1] + "\" ?");
        questionLabel.setAlignmentX(Box.CENTER_ALIGNMENT);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        title.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(title);
        titlePanel.add(questionLabel);

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        btn1 = new JButton("1. " + quiz[2]);
        btn1.setPreferredSize(new Dimension(200,150));
        btn2 = new JButton("2. " + quiz[3]);
        btn2.setPreferredSize(new Dimension(200,150));

        btn3 = new JButton("3. " + quiz[4]);
        btn3.setPreferredSize(new Dimension(200,150));
        btn4 = new JButton("4. " + quiz[5]);
        btn4.setPreferredSize(new Dimension(200,150));


        gbc.insets = new Insets(50,10,10,10);
        gbc.gridx = 0; gbc.gridy = 0;
        middlePanel.add(btn1, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        middlePanel.add(btn2, gbc);

        gbc.insets = new Insets(10,10,50,10);
        gbc.gridx = 0; gbc.gridy = 1;
        middlePanel.add(btn3, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        middlePanel.add(btn4, gbc);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        btnPanel.add(Box.createRigidArea(new Dimension(20,20)));
        nextBtn = new JButton("Next");
        btnPanel.add(nextBtn);
        btnPanel.add(Box.createRigidArea(new Dimension(20,20)));
        backBtn = new JButton("Back");
        btnPanel.add(backBtn);

        panel.add(titlePanel);
        panel.add(middlePanel);
        panel.add(btnPanel);


    }
    public void addComponentsToContainer(){
        con.add(panel, new GridBagConstraints());
    }

    public void addActionEvent(){
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);

        nextBtn.addActionListener(this);
        backBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btn1)
        {
            answer(2, btn1);
        }
        else if(e.getSource() == btn2){
            answer(3, btn2);
        }
        else if(e.getSource() == btn3){
            answer(4, btn3);
        }
        else if(e.getSource() == btn4){
            answer(5, btn4);
        }
        else if(e.getSource() == nextBtn) {
            this.dispose();
            Quizz q = new Quizz(sList, qType);
            q.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            q.setDefaultLookAndFeelDecorated(true);
            q.setSize(800,800);
            q.setVisible(true);

        }
        else if(e.getSource() == backBtn){
            this.dispose();
            QuizzType qType = new QuizzType(sList);
            qType.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            qType.setDefaultLookAndFeelDecorated(true);
            qType.setSize(800,800);
            qType.setVisible(true);
        }
    }

    private void answer(int choice, JButton btn){
        if(qType == 1) {
            if (quiz[1].equals(quiz[choice])) {
                btn.setBackground(Color.GREEN);
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
                btn4.setEnabled(false);

                JOptionPane.showMessageDialog(null, "Correct answer");
            } else {
                JOptionPane.showMessageDialog(null, "Wrong answer");
            }
        }
        else {
            if (quiz[0].equals(quiz[choice])) {
                btn.setBackground(Color.GREEN);
                JOptionPane.showMessageDialog(null, "Correct answer");
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
                btn4.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Wrong answer");
            }
        }

    }

}
