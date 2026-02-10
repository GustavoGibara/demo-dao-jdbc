import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TEST 1: seller findById ===");
        Seller seller = sellerDao.findByID(3);
        System.out.println(seller);

        System.out.println("=== TEST 2: seller findByDepartment ===");
        List<Seller> sellers =  sellerDao.findByDepartment(seller.getDepartment());

        sellers.forEach(System.out::println);

        System.out.println("=== TEST 3: seller findAll ===");
        sellers =  sellerDao.findAll();

        sellers.forEach(System.out::println);
        System.out.println("=== TEST 4: seller insert ===");
        Seller newSeller = new Seller(null ,"Gustavo", "gustavo@gmail.com", LocalDate.parse("12/12/2012", DateTimeFormatter.ofPattern("dd/MM/yyyy")), 3000.00, new Department(1, null));
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id = " + newSeller.getId());

        System.out.println("=== TEST 5: seller update ===");
        seller = sellerDao.findByID(1);
        seller.setName("Martha Waine");
        sellerDao.update(seller);
        System.out.println("Update completed!");

        System.out.println("=== TEST 6: seller delete ===");
        System.out.print("Enter id for delete test: ");
        int id = sc.nextInt();
        sellerDao.deleteById(id);
        System.out.println("Delete completed!");

        sc.close();

    }
}
