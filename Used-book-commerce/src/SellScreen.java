import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SellScreen extends JFrame implements ActionListener {
    private JTextField titleField,publisherField,book_authorField,yearField,priceField,fairField,ISBNField;
    private JButton sell_button;
    JLabel sellText = new JLabel();
    Books books;
    String[] create_book = new String[9];
    //제목 ,출판사, 저자, 출판연도, 가격,상태,ISBN, 사용자 아이디, 이메일
    public SellScreen(String userId,String userEmail){
        books = new Books();
        create_book[7] = userId;
        create_book[8] = userEmail;
        JPanel titlePanel = new JPanel();
        JPanel publisherPanel = new JPanel();
        JPanel book_authorPanel = new JPanel();
        JPanel yearPanel = new JPanel();
        JPanel pricePanel = new JPanel();
        JPanel fairPanel = new JPanel();
        JPanel ISBNPanel = new JPanel();


        titleField = new JTextField(15);
        publisherField = new JTextField(15);
        book_authorField = new JTextField(15);
        yearField = new JTextField(15);
        priceField = new JTextField(15);
        fairField = new JTextField(15);
        ISBNField =  new JTextField(15);

        sellText.setForeground(Color.RED);

        JLabel titleLabel = new JLabel("책 제목 : ");
        JLabel publisherLabel = new JLabel("출판사 : ");
        JLabel book_authorLabel = new JLabel("저자  : ");
        JLabel yearLabel = new JLabel("출판년도 : ");
        JLabel priceLabel = new JLabel("가격(원) : ");
        JLabel fairLabel = new JLabel("책 상태 : ");
        JLabel ISBNLabel = new JLabel("ISBN 번호 : ");


        sell_button = new JButton("판매하기");
        sell_button.addActionListener(this);

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

        this.add(sell_button);
        this.add(sellText);

        setLayout(new FlowLayout());

        setTitle("판매");
        setSize(300, 500);
        setLocation(250,250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        if (str.equals("판매하기")&&!titleField.getText().equals("")) {

            create_book[0] = titleField.getText();
            create_book[1] = publisherField.getText();
            create_book[2] = book_authorField.getText();
            create_book[3] = yearField.getText();
            create_book[4] = priceField.getText();
            create_book[5] = fairField.getText();
            create_book[6] = ISBNField.getText();
            books.addBook(create_book);
            this.setVisible(false);
            UserScreen.KeyEventHandler key = new UserScreen(create_book[7],create_book[8],books).new KeyEventHandler();
        }else if(titleField.getText().equals("")){
            JOptionPane.showMessageDialog(null,
                    "책 제목을 입력해주세요.", "알림",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
