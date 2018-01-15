package com.revature.dao.reimbursement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.ErsConnectable;
import com.revature.model.ErsReimbursementStatus;

/**
 * Purpose:
 * - CRUD methods for ers.reimbursement_status table.
 * @author Shane Avery Sistoza
 *
 */
public class ErsReimbursementStatusImpl extends ErsConnectable implements ErsReimbursementStatusDao{
	
	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsReimbursementStatusDao#createStatus(java.lang.String)
	 */
	@Override
	public int createStatus(String newStatus) {
		// Not Supported. Intetionally left blank.
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsReimbursementStatusDao#selectStatus(int)
	 */
	@Override
	public ErsReimbursementStatus selectStatus(int id) {
		ErsReimbursementStatus u = null;
		
		try ( Connection conn = this.getConnection() ){
			
			String sql = "SELECT * FROM " + ErsReimbursementStatusDao.TABLE + " WHERE rs_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt( 1, id );
			ResultSet rs = ps.executeQuery();
			
			if ( rs.next() ) {
				u = new ErsReimbursementStatus (
					rs.getInt("rs_id"),
					rs.getString("rs_status")
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
	 * @see com.revature.dao.ErsReimbursementStatusDao#selectStatus(java.lang.String)
	 */
	@Override
	public ErsReimbursementStatus selectStatus(String status) {
		ErsReimbursementStatus u = null;
		
		try ( Connection conn = this.getConnection() ){
			
			String sql = "SELECT * FROM " + ErsReimbursementStatusDao.TABLE + " WHERE rs_status = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString( 1, status );
			ResultSet rs = ps.executeQuery();
			
			if ( rs.next() ) {
				u = new ErsReimbursementStatus (
					rs.getInt("rs_id"),
					rs.getString("rs_status")
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
	 * @see com.revature.dao.ErsReimbursementStatusDao#selectAllStatuses()
	 */
	@Override
	public List<ErsReimbursementStatus> selectAllStatuses() {
		List<ErsReimbursementStatus> rstatuss = new ArrayList<>();
		
		try ( Connection conn = this.getConnection() ){
			
			String sql = "SELECT * FROM " + ErsReimbursementStatusDao.TABLE;
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while ( rs.next() ) {
				rstatuss.add( new ErsReimbursementStatus (
					rs.getInt("rs_id"),
					rs.getString("rs_status")
					)
				);
				
				
			}
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return rstatuss;
	}

	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsReimbursementStatusDao#updateStatus(com.revature.model.ErsReimbursementStatus, java.lang.String)
	 */
	@Override
	public int updateStatus(ErsReimbursementStatus toModify, String newStatus) {
		int set = 0;
		
		try ( Connection conn = this.getConnection() ){
			String sql = "UPDATE " + ErsReimbursementStatusDao.TABLE + " SET rs_status = ? WHERE rs_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString( 1, newStatus );
			ps.setInt( 2, toModify.getId() );
			
			set = ps.executeUpdate();
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return set;
	}

	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsReimbursementStatusDao#deleteStatus(com.revature.model.ErsReimbursementStatus)
	 */
	@Override
	public int deleteStatus(ErsReimbursementStatus toDelete) {
		// NO SUPPORT. Intentionally left blank.

		/*
		 * 1. Add a deleted_at timestamp field. 
		 * 2. Records that rely on this status should be move to a different status.
		 * 3. Fill this field with the current timestamp.
		 * 3. Dereference the object, so it is available for garbage collection.
		 */
		return 0;
	}

}
