package com.revature.dao.reimbursement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.ErsConnectable;
import com.revature.model.ErsReimbursementType;

/**
 * Purpose: 
 * - CRUD Methods for ers_reimbursement_type
 * @author Shane Avery Sistoza
 *
 */
public class ErsReimbursementTypeImpl extends ErsConnectable implements ErsReimbursementTypeDao {

	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsReimbursementTypeDao#createType(java.lang.String)
	 */
	@Override
	public int createType(String type) {
		// No SUPPORT. Intentionally left blank.
		return 0;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsReimbursementTypeDao#selectType(int)
	 */
	@Override
	public ErsReimbursementType selectType(int id) {
		ErsReimbursementType rt = null;
		
		try ( Connection conn = this.getConnection() ){
			String sql = "SELECT * FROM " + ErsReimbursementTypeDao.TABLE + " WHERE rt_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt( 1, id );
			ResultSet rs = ps.executeQuery();
			
			if ( rs.next() ) {
				rt = new ErsReimbursementType ( 
					rs.getInt("rt_id"),
					rs.getString("rt_type")
				);
				
				
			}
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return rt;
	}

	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsReimbursementTypeDao#selectType(java.lang.String)
	 */
	@Override
	public ErsReimbursementType selectType(String type) {
		ErsReimbursementType rt = null;
		try ( Connection conn = this.getConnection() ){	
			String sql = "SELECT * FROM  " + ErsReimbursementTypeDao.TABLE + " WHERE rt_type = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString( 1, type );
			ResultSet rs = ps.executeQuery();
			
			if ( rs.next() ) {
				rt = new ErsReimbursementType ( 
					rs.getInt("rt_id"),
					rs.getString("rt_type")
				);
				
				
			}
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return rt;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsReimbursementTypeDao#selectAllTypes()
	 */
	@Override
	public List<ErsReimbursementType> selectAllTypes() {
		List<ErsReimbursementType> rts = new ArrayList<>();
		
		try ( Connection conn = this.getConnection() ){
			String sql = "SELECT * FROM  " + ErsReimbursementTypeDao.TABLE;
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while ( rs.next() ) {
				rts.add( new ErsReimbursementType(
					rs.getInt("rt_id"),
					rs.getString("rt_type")
				));
				
			}
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return rts;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsReimbursementTypeDao#updateType(com.revature.model.ErsReimbursementType, java.lang.String)
	 */
	@Override
	public int updateType(ErsReimbursementType toModify, String newType) {
		int set = 0;
		
		try ( Connection conn = this.getConnection() ){
			String sql = "UPDATE  " + ErsReimbursementTypeDao.TABLE + "  SET rt_type = ? WHERE rt_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt( 2, toModify.getId() );
			ps.setString ( 1, newType );
			
			set = ps.executeUpdate();
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return set;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsReimbursementTypeDao#deleteType(com.revature.model.ErsReimbursementType)
	 */
	@Override
	public int deleteType(ErsReimbursementType toDelete) {
		// NOT SUPPORTING. Intentionally left blank.
		
		/*
		 * 1. Add a deleted_at timestamp field. 
		 * 2. References on table do not need to change.
		 * 3. Fill this field with the current timestamp.
		 * 3. Dereference the object, so it is available for garbage collection.
		 */
		return 0;
	}

}
