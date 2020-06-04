import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Users {
    private ArrayList<String[]> userList = new ArrayList<>();
    private ArrayList<String[]> bookList = new ArrayList<>();
    Books books = new Books();
    public Users(){
        bookList= books.getBooks();
        //user정보 :아이디 , 비밀번호,전화번호,email, 상태
        try{
            //파일 객체 생성
            File file = new File("./Data/users.txt");
            //입력 스트림 생성
            FileReader filereader = new FileReader(file);
            //입력 버퍼 생성
            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            while((line = bufReader.readLine()) != null){
                StringTokenizer stringTokenizer =  new StringTokenizer(line,",");
                String[] user = new String[5];
                int i=0;
                while (stringTokenizer.hasMoreTokens()) {
                    user[i] = stringTokenizer.nextToken();
                    i++;
                }
                userList.add(user);
            }
            //.readLine()은 끝에 개행문자를 읽지 않는다.
            bufReader.close();
        }catch (FileNotFoundException e) {
            // TODO: handle exception
        }catch(IOException e) {
            System.out.println(e);
        }
    }
    public ArrayList<String[]> getUsers(){
        return userList;
    }


    public void removeUser( String[] removeUser){
        for(int i=0;i<userList.size();i++){
            if(userList.get(i)[0].equals(removeUser[0])){
                userList.remove(i);
                break;
            }
        }
        File file = new File("./Data/users.txt");
        FileWriter writer = null;

        try {
            // 기존 파일의 내용에 이어서 쓰려면 true를, 기존 내용을 없애고 새로 쓰려면 false를 지정한다.
            writer = new FileWriter(file, false);
            for (int i = 0; i < userList.size(); i++) {
                writer.write(userList.get(i)[0] + "," + userList.get(i)[1] + "," + userList.get(i)[2] + "," + userList.get(i)[3] + "," + userList.get(i)[4] + "\n");
            }
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File book_file = new File("./Data/books.txt");
        FileWriter book_writer = null;

        try {
            // 기존 파일의 내용에 이어서 쓰려면 true를, 기존 내용을 없애고 새로 쓰려면 false를 지정한다.
            book_writer = new FileWriter(book_file, false);
            for (int i = 0; i < bookList.size(); i++) {
                if(!bookList.get(i)[7].equals(removeUser[0]))
                    book_writer.write(bookList.get(i)[0] + "," + bookList.get(i)[1] + "," + bookList.get(i)[2] + "," + bookList.get(i)[3] + "," + bookList.get(i)[4] + "," + bookList.get(i)[5] + "," + bookList.get(i)[6] + "," + bookList.get(i)[7] + "," + bookList.get(i)[8] + "\n");
            }
            book_writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (book_writer != null) book_writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 재 작성
    }


    public void activeUser(String[] user){
        String actived = user[4];
        if(actived.equals("actived")){
            user[4] = "deactived";
        }else{
            user[4] = "actived";
        }
        for(int i=0;i<userList.size();i++){
            if(userList.get(i)[0].equals(user[0])){
                userList.get(i)[4] = user[4];
                break;
            }
        }

        File file = new File("./Data/users.txt");
        FileWriter writer = null;

        try {
            // 기존 파일의 내용에 이어서 쓰려면 true를, 기존 내용을 없애고 새로 쓰려면 false를 지정한다.
            writer = new FileWriter(file, false);
            for (int i = 0; i < userList.size(); i++) {
                writer.write(userList.get(i)[0] + "," + userList.get(i)[1] + "," + userList.get(i)[2] + "," + userList.get(i)[3] + "," + userList.get(i)[4] + "\n");
            }
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
