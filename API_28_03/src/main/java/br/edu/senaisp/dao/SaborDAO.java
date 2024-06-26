package br.edu.senaisp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.senaisp.model.Sabor;

public class SaborDAO {

	private final String SQLINSERT = 
	"INSERT INTO sabor (nome, descricao, preco) VALUES(?, ?, ?)";
	
	private final String SQLSELECT = 
	"SELECT id, nome, descricao, preco FROM sabor";	
	
	private final String SQLSELECT_ID = 
	"SELECT id, nome, descricao, preco FROM sabor WHERE id = ?";		
	
	private final String SQLUPDATE = 
	"UPDATE sabor SET nome = ?, descricao = ?, preco = ? WHERE id = ?";			
	
	private final String SQLDELETE = 
	"DELETE FROM sabor WHERE id = ?";
	
	public void novo(Sabor sabor) {
		
		try {
			Connection con = Dao.conexao();
			
            if (!con.isClosed()) {
            	PreparedStatement ps = 
            			con.prepareStatement(SQLINSERT);
            	ps.setString(1, sabor.getNome());
            	ps.setString(2, sabor.getDescricao());
            	ps.setFloat(3, sabor.getPreco());
            	
            	ps.execute();
            }
            	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public List<Sabor> lista() {
		List<Sabor> sabores = new ArrayList<Sabor>();
		
		try {
			Connection con = Dao.conexao();
			
            if (!con.isClosed()) {
            	PreparedStatement ps = 
            	con.prepareStatement(SQLSELECT);
            	ResultSet rs = ps.executeQuery();
            	
            	Sabor tmp = null;
            	while (rs.next()) {
            		
            		tmp = new Sabor();
            		tmp.setId( rs.getInt("id") );
            		tmp.setNome(rs.getString("nome") );
            		tmp.setDescricao(rs.getString("descricao"));
            		tmp.setPreco(rs.getFloat("preco"));
            		
            		sabores.add(tmp);
            	}
            	
            	con.close();
            }
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return sabores;
	}
	
	public Sabor buscaPorId(int id) {
		Sabor sabor = null; 
		
		try {
			Connection con = Dao.conexao();
			
            if (!con.isClosed()) {
            	PreparedStatement ps = 
            	con.prepareStatement(SQLSELECT_ID);
            	ps.setInt(1, id);
            	
            	ResultSet rs = ps.executeQuery();
            	
            	
            	if (rs.next()) {
            		
            		sabor = new Sabor();
            		sabor.setId( rs.getInt("id") );
            		sabor.setNome(rs.getString("nome") );
            		sabor.setDescricao(rs.getString("descricao"));
            		sabor.setPreco(rs.getFloat("preco"));
               	}
            	
            	con.close();
            }
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return sabor;
	}
	
	
	public void remove(int id) {

		try {
			Connection con = Dao.conexao();
			
            if (!con.isClosed()) {
            	PreparedStatement ps = 
            			con.prepareStatement(SQLDELETE);
            	ps.setInt(1, id);
            	
            	ps.execute();
            }
            	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}	
	
	public void altera(Sabor sabor) {

		try {
			Connection con = Dao.conexao();
			
            if (!con.isClosed()) {
            	PreparedStatement ps = 
            			con.prepareStatement(SQLUPDATE);
            	ps.setString(1, sabor.getNome());
            	ps.setString(2, sabor.getDescricao());
            	ps.setFloat(3, sabor.getPreco());
            	
            	ps.setInt(4, sabor.getId());
            	
            	ps.execute();
            }
            	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}	
}
