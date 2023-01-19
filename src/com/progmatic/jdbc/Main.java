package com.progmatic.jdbc;

import com.progmatic.jdbc.dao.PizzaDao;
import com.progmatic.jdbc.model.Client;
import com.progmatic.jdbc.model.Courier;
import com.progmatic.jdbc.model.Order;
import com.progmatic.jdbc.model.Pizza;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Scanner;

public class Main {

    private void printMenu() {
        System.out.println("(U)j rendeles");
        System.out.println("Kere(s)es");
        System.out.println("Ve(v)ok listaja");
        System.out.println("Fu(t)arok listaja");
        System.out.println("(P)izza menu");
        System.out.println("Ren(d)elesek listaja");
        System.out.println("(K)ilepes");
    }

    private void printPizza(){
        System.out.println("Uj pizza (h)ozzaadasa");
        System.out.println("Pizza (m)odositasa");
        System.out.println("Pizza (t)orlese");
        System.out.println("Pizzak (l)istaja");
        System.out.println("(V)issza a fomenube");
    }

    public void start() {
        try (
            Scanner sc = new Scanner(System.in);
            Controller controll = new Controller()
        ) {
            System.out.println("*".repeat(30));
            System.out.println("*" + StringUtils.center("Pizza Prog", 28) + "*");
            System.out.println("*".repeat(30) + "\n");

            String s;
            this.printMenu();
            while (!(s = sc.nextLine()).equalsIgnoreCase("k")) {
                switch (s.toLowerCase()) {
                    case "u" ->
                        System.out.println("uj rendeles");
                    case "s" ->
                        System.out.println("kereses");
//                        this.startSearch(engine);
                    case "v" -> {
                        List<Client> allC = controll.getAllClient();
                        for (Client c : allC) {
                            System.out.println(c);
                        }
                        System.out.println("\n");
                    }
                    case "d" -> {
                        List<Order> allO = controll.getAllOrder();
                        for (Order o : allO) {
                            System.out.println(o);
                        }
                        System.out.println("\n");
                    }
                    case "t" -> {
                        List<Courier> allC = controll.getAllCourier();
                        for (Courier c : allC) {
                            System.out.println(c);
                        }
                        System.out.println("\n");
                    }
                    case "p" -> {
                        printPizza();
                        while (!(s = sc.nextLine()).equalsIgnoreCase("v")) {
                            switch (s.toLowerCase()) {
                                case "h" -> {
                                    System.out.print("Kerlek add meg a pizza Id-jat: ");
                                    long id = Long.parseLong(sc.nextLine());
                                    System.out.print("\nKerlek add meg a pizza nevet: ");
                                    String name = sc.nextLine();
                                    System.out.print("\nKerlek add meg a pizza arat: ");
                                    int price = Integer.parseInt(sc.nextLine());
                                    controll.addPizza(new Pizza(id, name, price));
                                    System.out.println("\n");
                                }
                                case "m" ->{
                                    System.out.print("Kerlek add meg az modositani kivant pizza Id-jat: ");
                                    long id = Long.parseLong(sc.nextLine());
                                    System.out.print("Kerlek add meg a pizza uj Id-jat: ");
                                    String nid = sc.nextLine();
                                    System.out.print("\nKerlek add meg a pizza uj nevet: ");
                                    String name = sc.nextLine();
                                    System.out.print("\nKerlek add meg a pizza uj arat: ");
                                    String price = sc.nextLine();
                                    String[] params = {nid, name, price};
                                    controll.updatePizza(new Pizza(id, null, null), params);
                                    System.out.println("\n");
                                }
                                case "t" ->{
                                    System.out.print("Kerlek add meg a torolni kivant pizza Id-jat: ");
                                    long id = Long.parseLong(sc.nextLine());
                                    controll.deletePizza(new Pizza(id,null,null));
                                    System.out.println("\n");
                                }
                                case "p" -> {
                                    List<Pizza> allP = controll.getAllPizza();
                                    for (Pizza p : allP) {
                                        System.out.println(p);
                                    }
                                    System.out.println("\n");
                                }
                                default -> System.out.println("Ilyen menuelem nincs, kerem valasszon ujra.\n");
                            }
                            this.printPizza();
                        }
                    }
                    default -> System.out.println("Ilyen menuelem nincs, kerem valasszon ujra.\n");
                }
                this.printMenu();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        Main m = new Main();
        m.start();
    }
}
