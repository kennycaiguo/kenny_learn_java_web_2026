import java.io.*;
import java.sql.*;

public class Demo1 {
    // 数据库连接信息
    static final String URL = "jdbc:mysql://localhost:3306/company?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "root";
    public static void main(String[] args) throws IOException, SQLException {
//        for (int i = 0; i < 3; i++) {
//            System.out.println("Hello,java");
//        }
//        Demo1 demo1 = new Demo1();
////        demo1.writeFile("hello.txt");
//        demo1.readFile("hello.txt");
        // 连接数据库
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            String sql="select ename,sal from emp order by sal desc;";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                String ename = rs.getString("ename");
                String sal = rs.getString("sal");
                System.out.println(ename + ":" + sal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs.close();
            conn.close();
        }

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
