import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class App {
    public static void main(String[] args) throws Exception {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TEST 1: seller findById ===");
        Seller seller = sellerDao.findByID(3);
        System.out.println(seller);

        System.out.println("=== TEST 2: seller findByDepartment ===");
        List<Seller> sellers =  sellerDao.findByDepartment(seller.getDepartment());

        sellers.forEach(System.out::println);
    }
}
