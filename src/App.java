import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.entities.Department;
import model.entities.Seller;

public class App {
    public static void main(String[] args) throws Exception {
        Department obj = new Department(1, "Books");
        Seller obj2 = new Seller(1, "Gustavo", "gustavo@gmail.com", LocalDate.parse("22/03/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy")), 2000.0, obj);

        System.out.println(obj);
        System.out.println(obj2);
    }
}
