import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Books {
    private ArrayList<String[]> books = new ArrayList<>();
    public Books(){
        try{
            //파일 객체 생성
            File file = new File("./Data/books.txt");
            //입력 스트림 생성
            FileReader filereader = new FileReader(file);
            //입력 버퍼 생성
            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            while((line = bufReader.readLine()) != null){
                StringTokenizer stringTokenizer =  new StringTokenizer(line,",");
                String[] book = new String[9];
                int i=0;
                while (stringTokenizer.hasMoreTokens()) {
                    book[i] = stringTokenizer.nextToken();
                    i++;
                }
                books.add(book);
            }
            //.readLine()은 끝에 개행문자를 읽지 않는다.
            bufReader.close();
        }catch (FileNotFoundException e) {
            // TODO: handle exception
        }catch(IOException e) {
            System.out.println(e);
        }
    }
    public ArrayList<String[]> getBooks(){
        return books;
    }

    public void removeBook( String[] removebook){
        boolean flag = false;
        for(int i=0;i<books.size();i++){
            if(Arrays.equals(books.get(i),removebook)){
                books.remove(i);
                flag = true;
                break;
            }
        }
        if(flag) {
            File file = new File("./Data/books.txt");
            FileWriter writer = null;

            try {
                // 기존 파일의 내용에 이어서 쓰려면 true를, 기존 내용을 없애고 새로 쓰려면 false를 지정한다.
                writer = new FileWriter(file, false);
                for (int i = 0; i < books.size(); i++) {
                    writer.write(books.get(i)[0] + "," + books.get(i)[1] + "," + books.get(i)[2] + "," + books.get(i)[3] + "," + books.get(i)[4] + "," + books.get(i)[5] + "," + books.get(i)[6] + "," + books.get(i)[7] + "," + books.get(i)[8] + "\n");
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

        // 재 작성
    }
    public void addBook(String[] book){
        if(book[0]!=null){
            books.add(book);
            File file = new File("./Data/books.txt");
            FileWriter writer = null;

            try {
                // 기존 파일의 내용에 이어서 쓰려면 true를, 기존 내용을 없애고 새로 쓰려면 false를 지정한다.
                writer = new FileWriter(file, true);
                writer.write(book[0]+","+book[1]+","+book[2]+","+book[3]+","+book[4]+","+book[5]+","+book[6]+","+book[7]+","+book[8]+"\n");

                writer.flush();

            } catch(IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(writer != null) writer.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
