import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifybookScreen extends JFrame implements ActionListener {
    private JTextField titleField,publisherField,book_authorField,yearField,priceField,fairField,ISBNField;
    private JButton modify_button,backbtn;
    JLabel modifyText = new JLabel();

    Books books;
    String[] book = new String[9];
    //제목 ,출판사, 저자, 출판연도, 가격,상태,ISBN, 사용자 아이디, 이메일
    public ModifybookScreen(String[] book,Books books){
        this.book = book;
        this.books = books;
        JPanel titlePanel = new JPanel();
        JPanel publisherPanel = new JPanel();
        JPanel book_authorPanel = new JPanel();
        JPanel yearPanel = new JPanel();
        JPanel pricePanel = new JPanel();
        JPanel fairPanel = new JPanel();
        JPanel ISBNPanel = new JPanel();


        titleField = new JTextField(15);
        publisherField =new JTextField(15);
        book_authorField = new JTextField(15);
        yearField = new JTextField(15);
        priceField = new JTextField(15);
        fairField = new JTextField(15);
        ISBNField =  new JTextField(15);

        titleField.setText(book[0]);
        publisherField.setText(book[1]);
        book_authorField.setText(book[2]);
        yearField.setText(book[3]);
        priceField.setText(book[4]);
        fairField.setText(book[5]);
        ISBNField.setText(book[6]);


        JLabel titleLabel = new JLabel("책 제목 : ");
        JLabel publisherLabel = new JLabel("출판사 : ");
        JLabel book_authorLabel = new JLabel("저자  : ");
        JLabel yearLabel = new JLabel("출판년도 : ");
        JLabel priceLabel = new JLabel("가격(원) : ");
        JLabel fairLabel = new JLabel("책 상태 : ");
        JLabel ISBNLabel = new JLabel("ISBN 번호 : ");
        titleLabel.setSize(100,15);

        modify_button = new JButton("수정하기");
        backbtn = new JButton("뒤로가기");
        backbtn.addActionListener(this);
        modify_button.addActionListener(this);

        titlePanel.add(titleLabel);
        titlePanel.add(titleField);
        publisherPanel.add(publisherLabel);
        publisherPanel.add(publisherField);
        book_authorPanel.add(book_authorLabel);
        book_authorPanel.add(book_authorField);
        yearPanel.add(yearLabel);
        yearPanel.add(yearField);
        pricePanel.add(priceLabel);
        pricePanel.add(priceField);
        fairPanel.add(fairLabel);
        fairPanel.add(fairField);
        ISBNPanel.add(ISBNLabel);
        ISBNPanel.add(ISBNField);




        this.add(titlePanel);
        this.add(publisherPanel);
        this.add(book_authorPanel);
        this.add(yearPanel);
        this.add(pricePanel);
        this.add(fairPanel);
        this.add(ISBNPanel);

        this.add(modify_button);
        this.add(backbtn);

        setLayout(new FlowLayout());

        setTitle("책 수정");
        setSize(300, 500);
        setLocation(250,250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        if (str.equals("수정하기")&&!titleField.getText().equals("")) {
            books.removeBook(book);
            book[0] = titleField.getText();
            book[1] = publisherField.getText();
            book[2] = book_authorField.getText();
            book[3] = yearField.getText();
            book[4] = priceField.getText();
            book[5] = fairField.getText();
            book[6] = ISBNField.getText();
            books.addBook(book);
            this.setVisible(false);
            ModifyScreen modifyScreen = new ModifyScreen(books,book[7],book[8]);

        }else if(str.equals("뒤로가기")){
            this.setVisible(false);
            ModifyScreen modifyScreen = new ModifyScreen(books,book[7],book[8]);
        }else{
            JOptionPane.showMessageDialog(null,
                    "입력이 잘못 되어있습니다.", "알림",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
