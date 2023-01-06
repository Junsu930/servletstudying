package sec01.ex02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	private PreparedStatement pstmt;
	private Connection con;
	
	public List<MemberVO> listMembers(){
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			connDB();
			String query = "select * from t_member";
			System.out.println("prepateStatement : " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setEmail(email);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setJoinDate(joinDate);
				list.add(vo);
				
			}
			rs.close();
			pstmt.close();
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	private void connDB()
	{
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("드라이버 로딩 성공");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "sys as sysdba", "830751");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
