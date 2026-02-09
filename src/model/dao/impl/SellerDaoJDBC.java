package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public void update(Seller obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public Seller findByID(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
                                    + "FROM seller INNER JOIN department "
                                    + "ON seller.DepartmentId = department.Id "
                                    + "WHERE seller.Id = ?");
            st.setInt(1, id);

            rs = st.executeQuery();
            
            if (rs.next()) {
                Department dep = instatiateDepartment(rs);
                Seller seller = instatiateSeller(rs, dep);
                return seller;
            }
            return null;

        }
        catch(SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatment(st);
        }
    }

    private Department instatiateDepartment(ResultSet rs) throws SQLException {
        int departmentId = rs.getInt("DepartmentID"); 
        String departmentName = rs.getString("DepName");
        Department dep = new Department(departmentId, departmentName);
        return dep; 
    }

    private Seller instatiateSeller(ResultSet rs, Department dep) throws SQLException {
        int id = rs.getInt("Id");
        String name = rs.getString("Name");
        String email = rs.getString("Email");
        LocalDate date = rs.getDate("BirthDate").toLocalDate();
        double baseSalary = rs.getDouble("BaseSalary");
        Seller seller = new Seller(id, name, email, date, baseSalary, dep);
        return seller;

    }

    @Override
    public List<Seller> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findall'");
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try{
            st  = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE DepartmentId = ? "
                    + "ORDER BY Name");
            
            st.setInt(1,department.getId());
            
            rs = st.executeQuery();
            
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap();

            while (rs.next()) {

            Department dep = map.get(rs.getInt("DepartmentId"));
            
            if (dep == null) {
                dep = instatiateDepartment(rs);
                map.put(rs.getInt("DepartmentId"), dep);
            }    
            
            Seller seller = instatiateSeller(rs , dep);
            list.add(seller);
            }
            return list;
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatment(st);
        }
    }
    
    

}
