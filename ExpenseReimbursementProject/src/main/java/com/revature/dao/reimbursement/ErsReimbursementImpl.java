package com.revature.dao.reimbursement;

import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.ErsConnectable;
import com.revature.model.ErsReimbursement;

/**
 * Purpose: 
 * - CRUD methods for ers.reimbursements.
 * @author Shane Avery Sistoza
 *
 */
public class ErsReimbursementImpl extends ErsConnectable implements ErsReimbursementDao{

	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsReimbursementDao#createReimbursement()
	 */
	@Override
	public int createReimbursement(ErsReimbursement toSubmit) {
		int set = 0;
		
		// create_newreimbursement( {amount}, {description}, {receipt}, {author}, {type} )
		try ( Connection conn = this.getConnection() ){
			CallableStatement cs = conn.prepareCall("{call create_newreimbursement(?,?,?,?,?)}");

			cs.setDouble(1, toSubmit.getAmount());
			cs.setString(2, toSubmit.getDescription());
			cs.setBlob(3, new ByteArrayInputStream( toSubmit.getReceipt() ) );
			cs.setInt(4, toSubmit.getAuthor());
			cs.setInt(5, toSubmit.getType());
			
			set = cs.executeUpdate();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return set;
	}

	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsReimbursementDao#selectReimbursement(int)
	 */
	@Override
	public ErsReimbursement selectReimbursement(int id) {
		ErsReimbursement er = null;
		try ( Connection conn = this.getConnection() ){
			
			String sql = "SELECT * FROM " + ErsReimbursementDao.TABLE + " WHERE r_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt( 1, id );
			ResultSet rs = ps.executeQuery();
			
			
			if ( rs.next() ) {
				
				byte[] imgBytes = null;
				Blob img = rs.getBlob("r_receipt");
				if (img != null) {
					int imgLength = (int) img.length();  
					imgBytes = img.getBytes(1, imgLength); 
					img.free();
				}
				
				er = new ErsReimbursement (
					rs.getInt("r_id"),
					rs.getDouble("r_amount"),
					rs.getString("r_description"),
					imgBytes,
					rs.getString("r_submitted"),
					rs.getString("r_resolved"),
					rs.getInt("u_id_author"),
					rs.getInt("u_id_resolver"),
					rs.getInt("rt_type"),
					rs.getInt("rt_status")
				);
				
			}
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return er;
	}

	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsReimbursementDao#selectReimbursements(int)
	 */
	@Override
	public List<ErsReimbursement> selectReimbursements(int employeeid) {
		List<ErsReimbursement> ers = new ArrayList<>();
		try ( Connection conn = this.getConnection() ){
			
			String sql = "SELECT * FROM  " + ErsReimbursementDao.TABLE + "  WHERE u_id_author = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt( 1, employeeid );
			ResultSet rs = ps.executeQuery();
			
			while ( rs.next() ) {
				
				byte[] imgBytes = null;
				Blob img = rs.getBlob("r_receipt");
				if (img != null) {
					int imgLength = (int) img.length();  
					imgBytes = img.getBytes(1, imgLength); 
					img.free();
				}
				
				ers.add( new ErsReimbursement (
					rs.getInt("r_id"),
					rs.getDouble("r_amount"),
					rs.getString("r_description"),
					imgBytes,
					rs.getString("r_submitted"),
					rs.getString("r_resolved"),
					rs.getInt("u_id_author"),
					rs.getInt("u_id_resolver"),
					rs.getInt("rt_type"),
					rs.getInt("rt_status")
				));
			}
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return ers;
	}

	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsReimbursementDao#selectAllReimbursements()
	 */
	@Override
	public List<ErsReimbursement> selectAllReimbursements() {
		List<ErsReimbursement> ers = new ArrayList<>();
		try ( Connection conn = this.getConnection() ){
			String sql = "SELECT * FROM  " + ErsReimbursementDao.TABLE;
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while ( rs.next() ) {
				
				byte[] imgBytes = null;
				Blob img = rs.getBlob("r_receipt");
				if (img != null) {
					int imgLength = (int) img.length();  
					imgBytes = img.getBytes(1, imgLength); 
					img.free();
				}
				
				ers.add( new ErsReimbursement (
					rs.getInt("r_id"),
					rs.getDouble("r_amount"),
					rs.getString("r_description"),
					imgBytes,
					rs.getString("r_submitted"),
					rs.getString("r_resolved"),
					rs.getInt("u_id_author"),
					rs.getInt("u_id_resolver"),
					rs.getInt("rt_type"),
					rs.getInt("rt_status")
				));
				
			}
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return ers;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.reimbursement.ErsReimbursementDao#updateReimbursement(com.revature.model.ErsReimbursement)
	 */
	@Override
	public int updateReimbursement(ErsReimbursement toModify) {
int set = 0;
		
		try ( Connection conn = this.getConnection() ){
			// close_reimbursement( {approve/deny}, {r_id}, {resolver} );
			CallableStatement cs = conn.prepareCall("{call close_reimbursement(?,?,?)}");
			
			cs.setInt(1, toModify.getStatus());
			cs.setInt(2, toModify.getId());
			cs.setInt(3, toModify.getResolver());
			
			set = cs.executeUpdate();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return set;
	}

	/*
	 * (non-Javadoc)
	 * @see com.revature.dao.ErsReimbursementDao#updateReimbursementAmount(com.revature.model.ErsReimbursement, double)
	 */
	@Override
	public int updateReimbursementAmount(ErsReimbursement toModify, double newAmount) {
		int set = 0;
		try ( Connection conn = this.getConnection() ){
			String sql = "UPDATE  " + ErsReimbursementDao.TABLE + "  SET r_amount = ? WHERE r_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, newAmount);
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
	 * @see com.revature.dao.ErsReimbursementDao#updateReimbursementDescription(com.revature.model.ErsReimbursement, java.lang.String)
	 */
	@Override
	public int updateReimbursementDescription(ErsReimbursement toModify, String newDescription) {
		int set = 0;
		try ( Connection conn = this.getConnection() ){
			String sql = "UPDATE  " + ErsReimbursementDao.TABLE + "  SET r_description = ? WHERE r_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, newDescription);
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
	 * @see com.revature.dao.ErsReimbursementDao#updateReimbursementReceipt(com.revature.model.ErsReimbursement, java.sql.Blob)
	 */
	@Override
	public int updateReimbursementReceipt(ErsReimbursement toModify, Blob newReceipt) {
		int set = 0;
		
		try ( Connection conn = this.getConnection() ){
			String sql = "UPDATE  " + ErsReimbursementDao.TABLE + "  SET r_receipt = ? WHERE r_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setBlob(1, newReceipt);
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
	 * @see com.revature.dao.ErsReimbursementDao#updateReimbursementSubmitted(com.revature.model.ErsReimbursement, java.lang.String)
	 */
	@Override
	public int updateReimbursementSubmitted(ErsReimbursement toModify, String newSubmitted) {
		int set = 0;
		
		try ( Connection conn = this.getConnection() ){
			String sql = "UPDATE  " + ErsReimbursementDao.TABLE + "  SET r_submitted = ? WHERE r_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, newSubmitted);
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
	 * @see com.revature.dao.ErsReimbursementDao#updateReimbursementResolved(com.revature.model.ErsReimbursement, java.lang.String)
	 */
	@Override
	public int updateReimbursementResolved(ErsReimbursement toModify, String newResolved) {
		int set = 0;
		try ( Connection conn = this.getConnection() ){
			String sql = "UPDATE  " + ErsReimbursementDao.TABLE + "  SET r_resolved = ? WHERE r_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, newResolved);
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
	 * @see com.revature.dao.ErsReimbursementDao#updateReimbursementAuthor(com.revature.model.ErsReimbursement, int)
	 */
	@Override
	public int updateReimbursementAuthor(ErsReimbursement toModify, int newEmployeeId) {
		int set = 0;
		try ( Connection conn = this.getConnection() ){
			String sql = "UPDATE  " + ErsReimbursementDao.TABLE + "  SET u_id_author = ? WHERE r_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, newEmployeeId);
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
	 * @see com.revature.dao.ErsReimbursementDao#updateReimbursementResolver(com.revature.model.ErsReimbursement, int)
	 */
	@Override
	public int updateReimbursementResolver(ErsReimbursement toModify, int newManagerId) {
		int set = 0;
		try ( Connection conn = this.getConnection() ){
			String sql = "UPDATE  " + ErsReimbursementDao.TABLE + "  SET u_id_resolver = ? WHERE r_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, newManagerId);
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
	 * @see com.revature.dao.ErsReimbursementDao#updateReimbursementType(com.revature.model.ErsReimbursement, int)
	 */
	@Override
	public int updateReimbursementType(ErsReimbursement toModify, int newType) {
		int set = 0;
		try ( Connection conn = this.getConnection() ){
			String sql = "UPDATE  " + ErsReimbursementDao.TABLE + "  SET rt_type = ? WHERE r_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, newType);
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
	 * @see com.revature.dao.ErsReimbursementDao#updateReimbursementStatus(com.revature.model.ErsReimbursement, int)
	 */
	@Override
	public int updateReimbursementStatus(ErsReimbursement toModify, int newStatus) {
		int set = 0;
		try ( Connection conn = this.getConnection() ){
			String sql = "UPDATE  " + ErsReimbursementDao.TABLE + "  SET rt_status = ? WHERE r_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, newStatus);
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
	 * @see com.revature.dao.ErsReimbursementDao#deleteReimbursement(com.revature.model.ErsReimbursement)
	 */
	@Override
	public int deleteReimbursement(ErsReimbursement toDelete) {
		// NO SUPPORT. Intentionally Left Blank.

		/*
		 * 1. Add a deleted_at timestamp field. 
		 * 2. No records should be relying in reimbursement.
		 * 3. Fill this field with the current timestamp.
		 * 3. Dereference the object, so it is available for garbage collection.
		 */
		return 0;
	}

}
