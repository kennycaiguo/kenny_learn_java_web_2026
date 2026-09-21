import java.io.*;

public class Demo1 {
    public static void main(String[] args) throws IOException {
//        for (int i = 0; i < 3; i++) {
//            System.out.println("Hello,java");
//        }
        Demo1 demo1 = new Demo1();
//        demo1.writeFile("hello.txt");
        demo1.readFile("hello.txt");
    }
    public void writeFile(String filename) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        bw.write("Hello from java!!!");
        bw.close();
    }
    public void readFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while((line=reader.readLine())!=null){
            System.out.println(line+"\r\n");
        }
    }
}
