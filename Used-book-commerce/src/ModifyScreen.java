import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class ModifyScreen extends Frame implements ActionListener,MouseListener {
    ArrayList<String[]> bookList = new ArrayList<>();
    //제목, 출판사, 저자 정보, 출판년도를 비롯하여, 등록된 가격과 상태

    Object book_title,book_publisher,book_author,book_year,book_price,book_fair,book_ISBN,book_sellerId,book_sellerEmail = null;
    Panel button_panel;

    JTable jTable;
    JButton delect,modify,backBtn;
    DefaultTableModel model;
    JFrame frame;
    String userId,userEmail;
    Books books;
    String[] select_book= new String[9];
    public ModifyScreen(Books books,String userId,String userEamil){
        this.userId = userId;
        this.userEmail = userEmail;
        this.books = books;
        frame = new JFrame("내 책 수정");
        frame.setLocation(50,250);
        frame.setSize(1000,300);
        bookList = books.getBooks();
        Container container = frame.getContentPane();

        button_panel = new Panel();

        String colNames[] = { "제목", "출판사", "저자","출판일","가격(원)","상태","ISBN","판매자 ID","판매자 이메일"};
        model = new DefaultTableModel(colNames,0 ){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        jTable = new JTable(model);
        jTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(jTable);

        // 초기화
        for (int i = 0; i < bookList.size(); i++) {
            if (userId.equals(bookList.get(i)[7])) {
                model.addRow(bookList.get(i));
            }
        }


        delect = new JButton("삭제");
        modify = new JButton("내 책 수정");
        backBtn= new JButton("뒤로 가기");

        button_panel.add(delect);
        button_panel.add(modify);
        button_panel.add(backBtn);
        container.add(scrollPane,BorderLayout.CENTER);
        container.add(button_panel,BorderLayout.SOUTH);
        frame.setVisible(true);


        delect.addActionListener(this);
        modify.addActionListener(this);
        backBtn.addActionListener(this);
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
                ModifyScreen modifyScreen = new ModifyScreen(books,userId,userEmail);
            }
        }else if(str.equals("뒤로 가기")){
            frame.setVisible(false);
            UserScreen userScreen = new UserScreen(userId,userEmail,books);
        }else if(str.equals("내 책 수정")){
            ModifybookScreen modifybookScreen= new ModifybookScreen(select_book,books);
            frame.setVisible(false);
        }
    }

}
