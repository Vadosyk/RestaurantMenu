package ua.kiev.prog.Main;

import ua.kiev.prog.Entity.Menu;
import ua.kiev.prog.Util.UtilForMenu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);

    public static Menu addDish(String name_of_dish, double price, double weight, char sale) {
        return new Menu(name_of_dish, price, weight, sale);
    }
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Menu");
        EntityManager em = emf.createEntityManager();

        int key = -1;
        while (key != 0) {
            System.out.println("*************MENU*************");
            System.out.println("Enter: ");
            System.out.println("1 to add dishes");
            System.out.println("2 to choose dishes from price diapazon");
            System.out.println("3 to show only dishes with sale");
            System.out
                    .println("4 to choose set of dishes with summary weight less when 1 kg");
            System.out.println("0 to exit");
            key = Integer.parseInt(scan.nextLine());

            switch (key) {
                case 1:
                    System.out.println("Enter name of dish: ");
                    String name_of_dish = scan.nextLine();
                    System.out.println("Enter price: ");
                    double price = Double.parseDouble(scan.nextLine());
                    System.out.println("Enter weight: ");
                    double weight = Double.parseDouble(scan.nextLine());
                    System.out.println("Enter sale(Y or N)");
                    char sale = scan.nextLine().charAt(0);
                    try {
                        em.getTransaction().begin();
                        em.persist(addDish(name_of_dish, price, weight, sale));
                        em.getTransaction().commit();
                    } catch (Exception ex) {
                        em.getTransaction().rollback();
                        ex.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Enter price from: ");
                    double from = Double.parseDouble(scan.nextLine());
                    System.out.println("Enter price to: ");
                    double to = Double.parseDouble(scan.nextLine());
                    UtilForMenu.showPriceDiapason(em,from, to);
                    break;
                case 3:
                    UtilForMenu.showOnlyWithSale(em);
                    break;
                case 4:
                    UtilForMenu.setOfDishesLessThanKg(em);
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid number!");
                    break;

            }
        }
        em.close();
        emf.close();
    }
}
