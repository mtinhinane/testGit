package consumermanagement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import database.Pool;


/**
 * CRUD class, handle Create Read Update queries
 * @author  Tinhinane
 */

public class CrudConsumer {
	
	Connection conn ;
	Statement stmt;
	
	/**
	 * CONSTRUCTOR
	 * @throws SQLException 
	 */
	public CrudConsumer () throws SQLException {
		Pool.initialize(1, 25);
	}
	
	
	// read all purchases 
	
	public ArrayList <String> readAll () throws SQLException {
		conn = Pool.getConnection();
		
		ArrayList <String> ret = new ArrayList<String>();
		stmt = conn.createStatement();
		
		String sql = "SELECT p.id as per_id, p.surname as per_surname, p.name as per_name, pu.id as pu_id, m.name as m_name, tm.name as tm_name" + 
				"				FROM PURCHASSE pu, MERCHANDISE m, CONCERN c, TYPEMERCHANDISE tm, PERSON p" + 
				"				WHERE pu.id = c.id" + 
				"				AND c.id_merchandise = m.id" + 
				"				AND m.id_TypeMerchandise = tm.id" + 
				"				AND pu.id_person = p.id" + 
				"				GROUP BY per_id, pu_id, m_name, tm_name ORDER BY  p.id";
		ResultSet rs = stmt.executeQuery(sql);
		
		String line = "";
		while ( rs.next() ) {
			line = "";
			line += "per_id=" + rs.getInt("per_id");
			line += "|per_surname=" + rs.getString("per_surname");
			line += "|per_name=" + rs.getString("per_name");
			line += "|pu_id=" + rs.getInt("pu_id");
			line += "|m_name=" + rs.getString("m_name");
			line += "|tm_name=" + rs.getString("tm_name");
			ret.add(line);
		}
		
		stmt.close();
		Pool.release(conn);
		return ret ;
	}
	
	
	
	// get the id of consumer to see its purchasses
		public ArrayList <String> readAllID (int id) throws SQLException {
			conn = Pool.getConnection();
			
			ArrayList <String> ret = new ArrayList<String>();
			stmt = conn.createStatement();
			
			String sql = "SELECT p.id as per_id, p.surname as per_surname, p.name as per_name, pu.id as pu_id, m.name as m_name, tm.name as tm_name" + 
					"				FROM PURCHASSE pu, MERCHANDISE m, CONCERN c, TYPEMERCHANDISE tm, PERSON p" + 
					"				WHERE pu.id = c.id" + 
					"				AND c.id_merchandise = m.id" + 
					"				AND m.id_TypeMerchandise = tm.id" + 
					"				AND pu.id_person = p.id and p.id="+id;
			ResultSet rs = stmt.executeQuery(sql);
			
			String line = "";
			while ( rs.next() ) {
				line = "";
				line += "per_id=" + rs.getInt("per_id");
				line += "|per_surname=" + rs.getString("per_surname");
				line += "|per_name=" + rs.getString("per_name");
				line += "|pu_id=" + rs.getInt("pu_id");
				line += "|m_name=" + rs.getString("m_name");
				line += "|tm_name=" + rs.getString("tm_name");
				ret.add(line);
			}
		stmt.close();
		Pool.release(conn);
		return ret ;
	}
		public ArrayList <String> showProfils () throws SQLException {
			conn = Pool.getConnection();
			
			ArrayList <String> ret = new ArrayList<String>();
			stmt = conn.createStatement();
			stmt.executeUpdate("delete from belongs");
			stmt.executeUpdate("insert into belongs \n" + 
					"select  \n" + 
					"distinct \n" + 
					"tmc.id as typemar, pc.id as per_id\n" + 
					"from purchasse pu, typemerchandise tmc, person pc, merchandise m, concern c\n" + 
					"where pu.id = c.id and c.id_Merchandise = m.id\n" + 
					"and m.id_TypeMerchandise = tmc.id\n" + 
					"and pu.id_Person= pc.id\n" + 
					"group by  pc.id,  pu.id , tmc.id\n" + 
					"\n" + 
					"having \n" + 
					"(select\n" + 
					"count(*)\n" + 
					"from purchasse pu, merchandise m, concern c\n" + 
					"where pu.id = c.id and c.id_Merchandise = m.id\n" + 
					"and m.id_TypeMerchandise = tmc.id\n" + 
					"and pu.id_Person= pc.id and  current_date- interval '6' month <pu.datepurchasse \n" + 
					"group by tmc.id\n" + 
					") >=2");
			
			
			
			
			
			String sql = "SELECT pe.id, pe.surname, pe.name, string_agg(ct.name, ', ')\n" + 
					"from person pe, belongs b, category_person ct\n" + 
					"where pe.id= b.id_person\n" + 
					"and ct.id= b.id\n" + 
					"group by pe.id";
		
			
			ResultSet rs = stmt.executeQuery(sql);
			
			String line = "";
			while ( rs.next() ) {
				line = "";
				line += "pe.id=" + rs.getInt(1);
				line += "|pe.surname=" + rs.getString(2);
				line += "|pe.name=" + rs.getString(3);
				line += "|ct.name=" + rs.getString(4);
				ret.add(line);
			}
			
			stmt.close();
			Pool.release(conn);
			return ret ;
		}
			
		
	public void close () {
		Pool.closeAll();
	}
}
