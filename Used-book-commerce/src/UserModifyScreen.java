import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;


class UserModifyScreen extends Frame implements ActionListener,MouseListener {
    ArrayList<String[]> userList = new ArrayList<>();

    Object userName,userPassword,userNumber,userEmail,userFair= null;
    String[] select_user= new String[5];

    int row = -1;
    String[] values = {"유저 이름","비밀번호","전화번호","이메일","상태"};
    // 0 ,2,3,4
    Object value = null;
    Panel search_panel,button_panel;

    TextField tf;
    JTable jTable;
    JButton b1, b2,logoutBtn,activeBtn;
    DefaultTableModel model;
    JComboBox jb = new JComboBox(values);;
    JFrame frame;
    Books books;
    Users users;
    public UserModifyScreen(Books books,Users users) {
        frame = new JFrame("사용자 관리 화면");
        frame.setLocation(50,250);
        frame.setSize(1000,300);
        this.books = books;
        this.users = users;
        userList = users.getUsers();
        String buy_value="";
        Container container = frame.getContentPane();

        search_panel = new Panel();
        button_panel = new Panel();

        tf = new TextField(20);
        String colNames[] = {"유저 이름","비밀번호","전화번호","이메일","상태"};
        model = new DefaultTableModel(colNames,0 ){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        jTable = new JTable(model);
        jTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(jTable);


        b1 = new JButton("삭제");
        activeBtn = new JButton("상태 바꾸기");
        b2 = new JButton("뒤로 가기");
        logoutBtn = new JButton("Log out");
        search_panel.add(jb);
        search_panel.add(tf);

        button_panel.add(b1);
        button_panel.add(activeBtn);
        button_panel.add(b2);
        button_panel.add(logoutBtn);
        container.add(scrollPane,BorderLayout.CENTER);
        container.add(search_panel,BorderLayout.NORTH);
        container.add(button_panel,BorderLayout.SOUTH);
        frame.setVisible(true);


        b1.addActionListener(this);
        activeBtn.addActionListener(this);
        b2.addActionListener(this);
        logoutBtn.addActionListener(this);
        tf.addKeyListener(new KeyEventHandler());
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable.addMouseListener((MouseListener) this);
        //
        jTable.setAutoCreateColumnsFromModel(true);


        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                // TODO Auto-generated method stub
                System.exit(0);
            }
        });

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e){
        row = jTable.getSelectedRow();
        userName = jTable.getValueAt(row,0);
        select_user[0] = userName.toString();
        userPassword = jTable.getValueAt(row,1);
        select_user[1] = userPassword.toString();
        userNumber= jTable.getValueAt(row,2);
        select_user[2] = userNumber.toString();
        userEmail= jTable.getValueAt(row,3);
        select_user[3] = userEmail.toString();
        userFair = jTable.getValueAt(row,4);
        select_user[4] = userFair.toString();

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        if (str.equals("삭제")) {
            if(row !=-1) {
                if (userFair.equals("deactived")) {
                    JOptionPane.showMessageDialog(null,
                            userName + "은(는) 삭제 되었습니다", "알림",
                            JOptionPane.ERROR_MESSAGE);
                    //해당 유저를 없앤다.
                    users.removeUser(select_user);
                    frame.setVisible(false);
                    UserModifyScreen userModifyScreen = new UserModifyScreen(books, users);
                } else {
                    JOptionPane.showMessageDialog(null,
                            userName + "은(는) 비활성화된 사용자가 아닙니다. 먼저 비활성화 시켜주세요.", "알림",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (str.equals("상태 바꾸기")) {
            users.activeUser(select_user);
            String before = select_user[4];
            String after ;
            if(before.equals("actived")){
                after = "deactived";
            }else{
                after = "actived";
            }
            JOptionPane.showMessageDialog(null,
                    userName + "은(는) " +before +" 상태에서 "+after+ " 상태로 변경되었습니다.", "알림",
                    JOptionPane.ERROR_MESSAGE);
            jTable.repaint();
        }else if(str.equals("Log out")){
            frame.setVisible(false);
            LoginScreen loginScreen = new LoginScreen();
        }else if(str.equals("뒤로 가기")){
            AdminScreen adminScreen = new AdminScreen(books,users);
            frame.setVisible(false);

        }
    }

    class KeyEventHandler extends KeyAdapter {//멤버 이너클래스

        public void keyTyped(KeyEvent e) {
            if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                model.setNumRows(0);
                if(jb.getSelectedIndex()==0) {
                    for (int i = 0; i < userList.size(); i++) {
                        if (tf.getText().equals(userList.get(i)[0])) {
                            model.addRow(userList.get(i));
                        }
                    }

                }else if(jb.getSelectedIndex()==1){
                    for (int i = 0; i < userList.size(); i++) {
                        if (tf.getText().equals(userList.get(i)[6])) {
                            model.addRow(userList.get(i));

                        }
                    }
                }else if(jb.getSelectedIndex()==2){
                    for (int i = 0; i < userList.size(); i++) {
                        if (tf.getText().equals(userList.get(i)[2])) {
                            model.addRow(userList.get(i));

                        }
                    }
                }else if(jb.getSelectedIndex()==3){
                    for (int i = 0; i < userList.size(); i++) {
                        if (tf.getText().equals(userList.get(i)[7])) {
                            model.addRow(userList.get(i));

                        }
                    }
                }
            }
        }
    }
}