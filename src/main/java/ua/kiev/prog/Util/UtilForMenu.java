package ua.kiev.prog.Util;

import ua.kiev.prog.Entity.Menu;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UtilForMenu {
    public static Scanner scan = new Scanner(System.in);

    public static void showPriceDiapason(EntityManager em, double from,
                                         double to) {
        Query query = em.createQuery("SELECT dish FROM Menu dish WHERE dish.price BETWEEN " + from + " AND " + to, Menu.class);
        List<Menu> list = query.getResultList();
        for (Menu m : list) {
            System.out.println(m);
        }
    }

    public static void showOnlyWithSale(EntityManager em) {
        Query query = em.createQuery(
                "SELECT dish FROM Menu dish WHERE dish.sale='Y'", Menu.class);
        List<Menu> list = query.getResultList();
        for (Menu m : list) {
            System.out.println(m);
        }
    }

    public static void setOfDishesLessThanKg(EntityManager em) {
        Query query = em.createQuery("SELECT menu FROM Menu menu", Menu.class);
        List<Menu> menu = query.getResultList();
        for (Menu m : menu) {
            System.out.println(m);
        }
        System.out.println("Please make your order(Enter id " + "If you want to finish order enter -1): ");
        long id = 0;
        int totalWeight = 0;
        Menu dish = null;
        List<Menu> order = new ArrayList<Menu>();
        while (true) {
            id = Long.parseLong(scan.nextLine());
            if (id == -1) break;
            try {
                dish = em.find(Menu.class, id);
                totalWeight += dish.getWeight();
            } catch (Exception ex) {
                System.out.println("Invalid id!");
                continue;
            }
            if (totalWeight <= 1000) {
                order.add(dish);
                System.out.println(dish.getDish() + " choosed");
            } else {
                System.out.println("Total weight more than 1 kg!");
                break;
            }
        }
        System.out.println("Total weight = " + totalWeight);
        System.out.println(Arrays.toString(order.toArray()));
    }
}
