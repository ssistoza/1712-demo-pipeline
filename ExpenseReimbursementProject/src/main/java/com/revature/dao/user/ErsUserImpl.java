package com.revature.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.ErsConnectable;
import com.revature.model.ErsUser;

/**
 * Purpose:
 * 	- defining CRUD methods for the ers.users table. 
 * @author Shane Avery Sistoza
 *
 */
public class ErsUserImpl extends ErsConnectable implements ErsUserDao {

	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsUserDao#createUser(com.revature.model.ErsUser)
	 */
	@Override
	public int createUser(ErsUser current) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsUserDao#selectUser(int)
	 */
	@Override
	public ErsUser selectUser(int id) {
		ErsUser u = null;
		
		try ( Connection conn = this.getConnection() ){
			
			String sql = "SELECT * FROM " + ErsUserDao.TABLE + " WHERE u_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt( 1, id );
			ResultSet rs = ps.executeQuery();
			
			if ( rs.next() ) {
				u = new ErsUser (
					rs.getInt("u_id"),
					rs.getString("u_username"),
					rs.getString("u_password"),
					rs.getString("u_firstname"),
					rs.getString("u_lastname"),
					rs.getString("u_email"),
					rs.getInt("ur_id")
				);
				
			}
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return u;
	}

	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsUserDao#selectUser(java.lang.String)
	 */
	@Override
	public ErsUser selectUser(String username) {
		ErsUser u = null;
		
		try ( Connection conn = this.getConnection() ){	
			String sql = "SELECT * FROM  " + ErsUserDao.TABLE + "  WHERE u_username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString( 1, username );
			ResultSet rs = ps.executeQuery();
			
			if ( rs.next() ) {
				u = new ErsUser (
					rs.getInt("u_id"),
					rs.getString("u_username"),
					rs.getString("u_password"),
					rs.getString("u_firstname"),
					rs.getString("u_lastname"),
					rs.getString("u_email"),
					rs.getInt("ur_id")
				);
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return u;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsUserDao#selectAllUsers()
	 */
	@Override
	public List<ErsUser> selectAllUsers() {
		List<ErsUser> us = new ArrayList<>();
		
		try ( Connection conn = this.getConnection() ){
			String sql = "SELECT * FROM  " + ErsUserDao.TABLE;;
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while ( rs.next() ) {
				us.add( new ErsUser (
					rs.getInt("u_id"),
					rs.getString("u_username"),
					rs.getString("u_password"),
					rs.getString("u_firstname"),
					rs.getString("u_lastname"),
					rs.getString("u_email"),
					rs.getInt("ur_id")
					)
				);
			}
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return us;
	}

	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsUserDao#updateUsername(com.revature.model.ErsUser, java.lang.String)
	 */
	@Override
	public int updateUsername(ErsUser current, String username) {
		int set = 0;
		try ( Connection conn = this.getConnection() ){
			String sql = "UPDATE  " + ErsUserDao.TABLE + "  SET u_username = ? WHERE u_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString( 1, username );
			ps.setInt( 2, current.getId() );
			
			set = ps.executeUpdate();
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return set;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsUserDao#updatePassword(com.revature.model.ErsUser, java.lang.String)
	 */
	@Override
	public int updatePassword(ErsUser current, String password) {
		int set = 0;
		
		try ( Connection conn = this.getConnection() ){
			String sql = "UPDATE  " + ErsUserDao.TABLE + "  SET u_password = ? WHERE u_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString( 1, password );
			ps.setInt( 2, current.getId() );
			
			set = ps.executeUpdate();
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return set;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsUserDao#updateFirstName(com.revature.model.ErsUser, java.lang.String)
	 */
	@Override
	public int updateFirstName(ErsUser current, String firstName) {
		int set = 0;
		
		try ( Connection conn = this.getConnection() ){
			String sql = "UPDATE  " + ErsUserDao.TABLE + "  SET u_firstname = ? WHERE u_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString( 1, firstName );
			ps.setInt( 2, current.getId() );
			
			set = ps.executeUpdate();
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return set;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsUserDao#updateLastName(com.revature.model.ErsUser, java.lang.String)
	 */
	@Override
	public int updateLastName(ErsUser current, String lastName) {
		int set = 0;
		
		try ( Connection conn = this.getConnection() ){
			String sql = "UPDATE  " + ErsUserDao.TABLE + "  SET u_lastname = ? WHERE u_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString( 1, lastName );
			ps.setInt( 2, current.getId() );
			
			set = ps.executeUpdate();
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return set;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsUserDao#updateEmail(com.revature.model.ErsUser, java.lang.String)
	 */
	@Override
	public int updateEmail(ErsUser current, String email) {
		int set = 0;
		
		try ( Connection conn = this.getConnection() ){
			String sql = "UPDATE  " + ErsUserDao.TABLE + "  SET u_email = ? WHERE u_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString( 1, email );
			ps.setInt( 2, current.getId() );
			
			set = ps.executeUpdate();
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return set;
	}

	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsUserDao#updateRole(com.revature.model.ErsUser, int)
	 */
	@Override
	public int updateRole(ErsUser current, int role) {
		int set = 0;
				
		try ( Connection conn = this.getConnection() ){
			String sql = "UPDATE  " + ErsUserDao.TABLE + "  SET ur_id = ? WHERE u_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt( 1, role );
			ps.setInt( 2, current.getId() );
			
			set = ps.executeUpdate();
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return set;
	}

	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsUserDao#deleteUser(com.revature.model.ErsUser)
	 */
	@Override
	public int deleteUser(ErsUser current) {
		// NO SUPPORT. Intentionally left Blank.

		/*
		 * 1. Add a deleted_at timestamp field. 
		 * 2. No record reference change should be necessary.
		 * 3. Fill this field with the current timestamp.
		 * 4. Dereference the object, so it is available for garbage collection.
		 */
		return 0;
	}
	
	
	
}
