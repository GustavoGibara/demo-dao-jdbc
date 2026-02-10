package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class App2 {

    public static void main(String[] args) {
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("=== TEST 1: findByID ===");
        Department dep = departmentDao.findByID(1);
        System.out.println(dep);

        System.out.println("=== TEST 2: findAll ===");
        List<Department> list = departmentDao.findall();
        list.forEach(System.out::println);
        
        System.out.println("=== TEST 3: insert ===");
        Department newDep =  new Department(null, "Office");
        departmentDao.insert(newDep);
        System.out.println("Inserted! New id: " + newDep.getId() );

        System.out.println("=== TEST 4: update ===");
        dep.setName("D2");
        departmentDao.update(dep);
        System.out.println("Update completed");        
    }

    
}
