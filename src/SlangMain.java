import javax.swing.*;

public class SlangMain extends JFrame {
    public static void main(String[] args){
        Menu menu = new Menu();
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(1000,1000);
        menu.setDefaultLookAndFeelDecorated(true);
        menu.setTitle("Slang words");
        menu.setVisible(true);

    }
}
