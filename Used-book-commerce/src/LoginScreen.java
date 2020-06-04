import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class LoginScreen extends JFrame implements ActionListener {
    private String id = "";
    private String password = "";
    private String email = "";
    private String manager_id = "admin";
    private String manager_password = "nayana";
    Users users;
    Books books;
    private ArrayList<String[]> usersList = new ArrayList<>();
    private JTextField tf;
    private JPasswordField pf;
    private JButton login;
    JLabel loginText = new JLabel();
    private boolean isLogin = false;
    JFrame frame;
    boolean actived = false;
    public LoginScreen() {
        frame = new JFrame("로그인 화면");
        frame.setLocation(250,250);
        JPanel idPanel = new JPanel();
        JPanel passPanel = new JPanel();
        tf = new JTextField(12);
        pf = new JPasswordField(10);
        loginText.setForeground(Color.RED);
        users = new Users();
        usersList = users.getUsers();
        books = new Books();

        JLabel idLabel = new JLabel("ID : ");
        JLabel passLabel = new JLabel("PASSWORD : ");
        login = new JButton("LOGIN");
        login.addActionListener(this);

        idPanel.add(idLabel);
        idPanel.add(tf);

        passPanel.add(passLabel);
        passPanel.add(pf);

        frame.add(idPanel);
        frame.add(passPanel);
        frame.add(login);
        frame.add(loginText);

        frame.setLayout(new FlowLayout());

        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        int number = 0;
        if (e.getSource() == login) {
            try {
                if(manager_id.equals(tf.getText()) && manager_password.equals(pf.getText())){
                    isLogin = true;
                    number = 2;
                    actived = true;
                }else{
                    for(int i=0;i<usersList.size();i++){
                        if(usersList.get(i)[0].equals(tf.getText())&&usersList.get(i)[1].equals(pf.getText())){
                            isLogin =  true;
                            id = usersList.get(i)[0];
                            email = usersList.get(i)[3];
                            number = 1;
                            if(usersList.get(i)[4].equals("actived")){
                                actived = true;
                            }else{
                                actived = false;
                            }
                        }
                    }
                }

                if (isLogin&&number ==1&&actived) {
                    UserScreen.KeyEventHandler key = new UserScreen(id,email,books).new KeyEventHandler();
                    //여기가 프레임 전환 역할
                    frame.setVisible(false);
                }else if(isLogin&&number ==2){
                     //여기가 프레임 전환 역할
                    AdminScreen.KeyEventHandler key = new AdminScreen(books,users).new KeyEventHandler();
                    frame.setVisible(false);                }
                else if(isLogin&&!actived){
                    loginText.setText("비활성화된 ID입니다.");
                }
                else{
                    loginText.setText("ID 또는 password가 잘못되었습니다.");
                }
            } catch (Exception e1) {
                System.out.println("false");
            }
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        LoginScreen my = new LoginScreen();
    }
}



