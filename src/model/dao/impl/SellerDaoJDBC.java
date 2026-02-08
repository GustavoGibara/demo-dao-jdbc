package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

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
                String name = rs.getString("Name");
                String email = rs.getString("Email");
                LocalDate date = rs.getDate("BirthDate").toLocalDate();
                double baseSalary = rs.getDouble("BaseSalary");
                int departmentId = rs.getInt("DepartmentID"); 
                String departmentName = rs.getString("DepName");

                Department department = new Department(departmentId, departmentName);

                Seller seller = new Seller(id, name, email, date, baseSalary, department);
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

    @Override
    public List<Seller> findall() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findall'");
    }

}
