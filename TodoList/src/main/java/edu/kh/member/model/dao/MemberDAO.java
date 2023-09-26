package edu.kh.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.member.model.dto.Member;

import static edu.kh.todo.common.JDBCTemplete.*;

public class MemberDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	
	public MemberDAO() {
		
		try {
			prop = new Properties();

			String filePath = MemberDAO.class.getResource("/edu/kh/todo/sql/member-sql.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	/** 1) 멤버 회원가입
	 * @param conn
	 * @param signupId
	 * @param signupPw
	 * @param signupNick
	 * @return
	 * @throws Exception
	 */
	public int signupMember(Connection conn, String signupId, String signupPw, String signupNick) throws Exception{
		
		int newMember = 0;
		
		try {
			String sql = prop.getProperty("signup");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, signupId);
			pstmt.setString(2, signupPw);
			pstmt.setString(3, signupNick);
			
			newMember = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return newMember;
	}


	/** 2) 멤버 로그인
	 * @param conn
	 * @param loginId
	 * @param loginPw
	 * @return
	 * @throws Exception
	 */
	public Member loginMember(Connection conn, String loginId, String loginPw) throws Exception{

		Member loginMember = null;
		
		try {
			
			String sql = prop.getProperty("login");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, loginId);
			pstmt.setString(2, loginPw);
			
			rs = pstmt.executeQuery();

			
			if(rs.next()) {
				
				loginMember = new Member();
				loginMember.setMemberId(rs.getString(1));
				loginMember.setMemberNickName(rs.getString(2));
				loginMember.setMemberNo(rs.getInt(3));
				
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return loginMember;
	}

}
