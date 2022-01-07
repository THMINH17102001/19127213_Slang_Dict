import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizzType extends JFrame implements ActionListener {
    Container container = getContentPane();
    JLabel title = new JLabel("Choose quiz type to play");
    JButton slangToDefBtn, defToSlangbtn, backBtn;
    JPanel panel;
    JPanel topPanel, middlePanel, bottomPanel;
    SlangList sList;
    public QuizzType(SlangList slangList){
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
        panel.setLayout(new BorderLayout());

        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        title.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(title);

        middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
        middlePanel.add(Box.createRigidArea(new Dimension(20,0)));
        slangToDefBtn = new JButton("Slang --> Definition");
        slangToDefBtn.setBackground(Color.GREEN);
        slangToDefBtn.setBorderPainted(false);
        slangToDefBtn.setPreferredSize(new Dimension(100,100));
        middlePanel.add(slangToDefBtn);
        middlePanel.add(Box.createRigidArea(new Dimension(20,0)));
        defToSlangbtn = new JButton("Definition --> Slang");
        defToSlangbtn.setBackground(Color.GREEN);
        defToSlangbtn.setBorderPainted(false);
        defToSlangbtn.setPreferredSize(new Dimension(100,100));
        middlePanel.add(defToSlangbtn);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        backBtn = new JButton("Back");
        bottomPanel.add(backBtn);

        panel.add(topPanel, BorderLayout.PAGE_START);
        panel.add(middlePanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.PAGE_END);
    }
    public void addComponentsToContainer(){
        container.add(panel);
    }
    public void addActionEvent() {
        defToSlangbtn.addActionListener(this);
        slangToDefBtn.addActionListener(this);
        backBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        Quizz quizz;
        if(e.getSource() == slangToDefBtn){
            dispose();
            quizz = new Quizz(sList, 1);
            quizz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            quizz.setDefaultLookAndFeelDecorated(true);
            quizz.setSize(800,800);
            quizz.setVisible(true);

        }
        else if(e.getSource() == defToSlangbtn){
            dispose();
            quizz = new Quizz(sList, 2);
            quizz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            quizz.setDefaultLookAndFeelDecorated(true);
            quizz.setSize(800,800);
            quizz.setVisible(true);

        }
        else if(e.getSource() == backBtn){
            this.dispose();
            Find find = new Find();
            find.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            find.setDefaultLookAndFeelDecorated(true);
            find.setSize(800,800);
            find.setVisible(true);

        }
    }
}
