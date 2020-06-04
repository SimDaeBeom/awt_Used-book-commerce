import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;


class AdminScreen extends Frame implements ActionListener,MouseListener {
    ArrayList<String[]> bookList = new ArrayList<>();
    //제목, 출판사, 저자 정보, 출판년도를 비롯하여, 등록된 가격과 상태
    Object book_title,book_publisher,book_author,book_year,book_price,book_fair,book_ISBN,book_sellerId,book_sellerEmail = null;
    String[] select_book= new String[9];


    String[] values = {"제목","ISBN 번호","저자","판매자 ID"};
    Object value = null;
    Panel search_panel,button_panel;

    TextField tf;
    JTable jTable;
    JButton b1, b2,messageBtn,logoutBtn;
    DefaultTableModel model;
    JComboBox jb = new JComboBox(values);;
    JFrame frame;
    Books books;
    Users users;
    public AdminScreen(Books books,Users users) {
        frame = new JFrame("관리자 화면");
        frame.setLocation(50,250);
        frame.setSize(1000,300);
        this.books = books;
        this.users = users;
        bookList = books.getBooks();
        String buy_value="";
        Container container = frame.getContentPane();

        search_panel = new Panel();
        button_panel = new Panel();

        tf = new TextField(20);
        String colNames[] = { "제목", "출판사", "저자","출판일","가격","상태","ISBN","판매자 ID","판매자 이메일"};
        model = new DefaultTableModel(colNames,0 ){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        jTable = new JTable(model);
        jTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(jTable);


        b1 = new JButton("삭제");
        b2 = new JButton("사용자 관리");
        logoutBtn = new JButton("Log out");
        search_panel.add(jb);
        search_panel.add(tf);

        button_panel.add(b1);
        button_panel.add(b2);
        button_panel.add(logoutBtn);
        container.add(scrollPane,BorderLayout.CENTER);
        container.add(search_panel,BorderLayout.NORTH);
        container.add(button_panel,BorderLayout.SOUTH);
        frame.setVisible(true);


        b1.addActionListener(this);
        b2.addActionListener(this);
        logoutBtn.addActionListener(this);
        tf.addKeyListener(new KeyEventHandler());
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable.addMouseListener((MouseListener) this);

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
        int row = jTable.getSelectedRow();
        book_title = jTable.getValueAt(row,0);
        select_book[0] = book_title.toString();
        book_publisher= jTable.getValueAt(row,1);
        select_book[1] = book_publisher.toString();
        book_author= jTable.getValueAt(row,2);
        select_book[2] = book_author.toString();
        book_year = jTable.getValueAt(row,3);
        select_book[3] = book_year.toString();
        book_price = jTable.getValueAt(row,4);
        select_book[4] = book_price.toString();
        book_fair = jTable.getValueAt(row,5);
        select_book[5] = book_fair.toString();
        book_ISBN = jTable.getValueAt(row,6);
        select_book[6] = book_ISBN.toString();
        book_sellerId = jTable.getValueAt(row,7);
        select_book[7] = book_sellerId.toString();
        book_sellerEmail = jTable.getValueAt(row,8);
        select_book[8] = book_sellerEmail.toString();
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
            if(book_sellerEmail!=null){
                JOptionPane.showMessageDialog(null,
                        book_title+"은(는) 삭제 되었습니다", "알림",
                        JOptionPane.ERROR_MESSAGE);
                //해당 책 없앤다.
                books.removeBook(select_book);
                frame.setVisible(false);
                AdminScreen adminScreen = new AdminScreen(books,users);
            }
        } else if (str.equals("사용자 관리")) {
            frame.setVisible(false);
            UserModifyScreen userModifyScreen = new UserModifyScreen(books,users);
        }else if(str.equals("Log out")){
            frame.setVisible(false);
            LoginScreen loginScreen = new LoginScreen();
        }
    }

    class KeyEventHandler extends KeyAdapter {//멤버 이너클래스

        public void keyTyped(KeyEvent e) {
            if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                model.setNumRows(0);
                if(jb.getSelectedIndex()==0) {
                    for (int i = 0; i < bookList.size(); i++) {
                        if (tf.getText().equals(bookList.get(i)[0])) {
                            model.addRow(bookList.get(i));
                        }
                    }

                }else if(jb.getSelectedIndex()==1){
                    for (int i = 0; i < bookList.size(); i++) {
                        if (tf.getText().equals(bookList.get(i)[6])) {
                            model.addRow(bookList.get(i));

                        }
                    }
                }else if(jb.getSelectedIndex()==2){
                    for (int i = 0; i < bookList.size(); i++) {
                        if (tf.getText().equals(bookList.get(i)[2])) {
                            model.addRow(bookList.get(i));

                        }
                    }
                }else if(jb.getSelectedIndex()==3){
                    for (int i = 0; i < bookList.size(); i++) {
                        if (tf.getText().equals(bookList.get(i)[7])) {
                            model.addRow(bookList.get(i));

                        }
                    }
                }
            }
        }
    }
}