package com.revature.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.ErsConnectable;
import com.revature.model.ErsUserRole;

/**
 * Purpose:
 * - CRUD methods for the ers.user_roles table.
 * @author Shane Avery Sistoza
 *
 */
public class ErsUserRoleImpl extends ErsConnectable implements ErsUserRoleDao {

	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsUserRoleDao#createRole(java.lang.String)
	 */
	@Override
	public int createRole(String newRole) {
		// NO SUPPORT. Intentionally left bank.
		
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsUserRoleDao#selectRole(int)
	 */
	@Override
	public ErsUserRole selectRole(int id) {
		ErsUserRole eur = null;
		
		try ( Connection conn = this.getConnection() ){
			String sql = "SELECT * FROM " + ErsUserRoleDao.TABLE + " WHERE ur_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt( 1, id );
			ResultSet rs = ps.executeQuery();
			
			if ( rs.next() ) {
				eur = new ErsUserRole (
					rs.getInt("ur_id"),
					rs.getString("ur_role")
				);
				
			}
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return eur;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsUserRoleDao#selectRole(java.lang.String)
	 */
	@Override
	public ErsUserRole selectRole(String role) {
		ErsUserRole eur = null;
		
		try ( Connection conn = this.getConnection() ){
			String sql = "SELECT * FROM " + ErsUserRoleDao.TABLE + " WHERE ur_role = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString( 1, role );
			ResultSet rs = ps.executeQuery();
			
			if ( rs.next() ) {
				eur = new ErsUserRole (
					rs.getInt("ur_id"),
					rs.getString("ur_role")
				);
				
			}
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return eur;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsUserRoleDao#selectAllRoles()
	 */
	@Override
	public List<ErsUserRole> selectAllRoles() {
		List<ErsUserRole> eurs = new ArrayList<>();
		
		try ( Connection conn = this.getConnection() ){
			String sql = "SELECT * FROM " + ErsUserRoleDao.TABLE;
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while ( rs.next() ) {
				eurs.add( new ErsUserRole (
					rs.getInt("ur_id"),
					rs.getString("ur_role")
				));
				
			}
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return eurs;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsUserRoleDao#updateRole(com.revature.model.ErsUserRole, java.lang.String)
	 */
	@Override
	public int updateRole(ErsUserRole toModify, String newRole) {
		int set = 0;
		
		try ( Connection conn = this.getConnection() ){
			String sql = "UPDATE " + ErsUserRoleDao.TABLE + " SET ur_role = ? WHERE ur_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, newRole);
			ps.setInt(2, toModify.getId() );

			set = ps.executeUpdate();
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return set;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsUserRoleDao#deleteRole(com.revature.model.ErsUserRole)
	 */
	@Override
	public int deleteRole(ErsUserRole toDelete) {
		// NO SUPPORT. Intentionally left blank.
		
		/*
		 * 1. Add a deleted_at timestamp field. 
		 * 2. Records that rely on this role should be move to a different role.
		 * 3. Fill this field with the current timestamp.
		 * 3. Dereference the object, so it is available for garbage collection.
		 */
		return 0;
	}

}
