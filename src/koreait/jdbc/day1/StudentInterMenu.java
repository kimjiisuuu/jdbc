package koreait.jdbc.day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentInterMenu {

   public static void main(String[] args) throws SQLException {
      String url = "jdbc:oracle:thin:@localhost:1521:xe";
         String user = "iclass";
         String password = "0419";
         boolean run = true;
         Scanner sc = new Scanner(System.in);
         System.out.println("::::::::::::학생 등록 메뉴 입니다.::::::::::::::::::");
         
         while(run) {
         try( Connection   conn = DriverManager.getConnection(url,user,password);
               ){    
                  if(conn!=null)
                     
                  System.out.println("오라클 데이터베이스 연결 성공 !!");
               
                  String sql = "insert into TBL_STUDENT values (?,?,?,?)"; 
                  
                  PreparedStatement pstmt = conn.prepareStatement(sql);
                  System.out.println("학번7자리를 입력하세요");
                  String stuno = sc.nextLine();
                  if(stuno.equals("0000")) {run = false;
                  break;
                  }
                  pstmt.setString(1, stuno);
                  
                  System.out.println("이름을 입력하세요");
                  String name = sc.nextLine();
                  pstmt.setString(2, name);
                  
                  System.out.println("나이를 입력하세요");
                  int age = Integer.parseInt(sc.nextLine()); //sc.nextInt();
                  pstmt.setInt(3, age);
                   
                                                              //sc.nextLine();
                  System.out.println("주소를 입력하세요.");
                  String address = sc.nextLine();
                  pstmt.setString(4, address);
                  
                  System.out.println("정상적으로 새로운 학생이 입력이 되었습니다.!!");
                  
                  pstmt.execute();      //insert 하면서 오류 발생.
                  pstmt.close();
               
            }catch (Exception e) {
                  System.out.println("오류메세지 = " + e.getMessage());
                 // e.printStackTrace(); 
            }
         
         
               
            System.out.println("학생번호 입력시 0000 입력은 종료입니다.");
            }
         
      }}