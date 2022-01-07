import javax.swing.*;

public class SlangMain extends JFrame {
    public static void main(String[] args){
        Find find = new Find();
        find.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        find.setDefaultLookAndFeelDecorated(true);
        find.setSize(800,800);
        find.setVisible(true);
    }
}
