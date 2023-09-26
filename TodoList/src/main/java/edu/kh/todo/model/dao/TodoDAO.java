package edu.kh.todo.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import edu.kh.member.model.dao.MemberDAO;
import edu.kh.member.model.dto.Member;
import edu.kh.todo.model.dto.Todo;
import static edu.kh.todo.common.JDBCTemplete.*;

public class TodoDAO {
	
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	
	public TodoDAO() {
		
		try {
			prop = new Properties();

			String filePath = MemberDAO.class.getResource("/edu/kh/todo/sql/todo-sql.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}



	/**1) 조회하기
	 * @param conn
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public List<Todo> selectTodo(Connection conn, int memberNo) throws Exception {
		List<Todo>todoList = new ArrayList<Todo>();
		try {
			String sql = prop.getProperty("selectAll");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);

			rs = pstmt.executeQuery();
	
			while(rs.next()) {
				Todo todo = new Todo();
				todo.setTodoTitle(rs.getString(1));
				todo.setTodoMemo(rs.getString(2));
				todo.setTodoDate(rs.getString(3));
				todo.setMemberNo(rs.getInt(4));
				todo.setTodoNo(rs.getInt(5));
				todo.setTodoDeleteFlag(rs.getString(6));
				todoList.add(todo);
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		return todoList;
	}
	

	/**1-2) 조회하기 (num)
	 * @param conn
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public Todo select(Connection conn, int num) throws Exception{
		Todo todo = null;
		try {
			String sql = prop.getProperty("select");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				todo = new Todo();
				todo.setTodoTitle(rs.getString(1));
				todo.setTodoMemo(rs.getString(2));
				todo.setTodoDate(rs.getString(3));
				todo.setMemberNo(rs.getInt(4));  // 굳이..?
				todo.setTodoNo(rs.getInt(5));
				todo.setTodoDeleteFlag(rs.getString(6));
			}
		}finally{
			close(rs);
			close(pstmt);
		}
		return todo;
	}
	
	
	/**2) 등록하기
	 * @param conn
	 * @param insertTitle
	 * @param insertMemo
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int insert(Connection conn, String insertTitle, String insertMemo, int memberNo) throws Exception {
		int insertResult = 0;
		try {
			String sql = prop.getProperty("insert");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, insertTitle);
			pstmt.setString(2, insertMemo);
			pstmt.setInt(3, memberNo);
			
			insertResult = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return insertResult;
	}
	



//	3) 수정하기
//	public int update(Connection conn, String updateTitle, String updateMemo) throws Exception{
//
//		int updateResult = 0;
//		
//		try {
//			
//			String sql = prop.getProperty("update");
//			
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setString(1,updateTitle);
//			pstmt.setString(2, updateMemo);
//			pstmt.setString(3, updateTitle);
//			
//			updateResult = pstmt.executeUpdate();
//			
//		}finally {
//			close(pstmt);
//		}
//		
//		return updateResult;
//		
//	}


	/** 3) 수정하기
	 * @param conn
	 * @param updateTitle
	 * @param updateMemo
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public int update(Connection conn, String updateTitle, String updateMemo, int num) throws Exception {
		int updateResult = 0;
		try {
			String sql = prop.getProperty("update");
			
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setString(1,updateTitle);
			pstmt.setString(2, updateMemo);
			pstmt.setInt(3, num);
			
			updateResult = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return updateResult;
	}


	/** 4) 삭제하기
	 * @param conn
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public int del(Connection conn, int num) throws Exception {
		int delResult = 0;
		
		try {
			String sql = prop.getProperty("delete");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
		
			delResult = pstmt.executeUpdate();
			
		}finally {
			
			close(pstmt);
		}
		return delResult;
	}



}
