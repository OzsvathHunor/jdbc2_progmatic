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
        System.out.println("(R)endeles menu");
        System.out.println("(V)evo menu");
        System.out.println("(F)utar menu");
        System.out.println("(P)izza menu");
        System.out.println("Kere(s)es");
        System.out.println("(K)ilepes");
    }

    private void printPizza(){
        System.out.println("Uj pizza (h)ozzaadasa");
        System.out.println("Pizza (m)odositasa");
        System.out.println("Pizza (t)orlese");
        System.out.println("Pizzak (l)istaja");
        System.out.println("(V)issza a fomenube");
    }

    private void printClient(){
        System.out.println("Uj vasarlo (h)ozzaadasa");
        System.out.println("Vasarlo (m)odositasa");
        System.out.println("Vasarlo (t)orlese");
        System.out.println("Vasarlok (l)istaja");
        System.out.println("(V)issza a fomenube");
    }

    private void printCourier(){
        System.out.println("Uj futar (h)ozzaadasa");
        System.out.println("Futar (m)odositasa");
        System.out.println("Futar (t)orlese");
        System.out.println("Futarok (l)istaja");
        System.out.println("(V)issza a fomenube");
    }

    private void printOrder(){
        System.out.println("Uj rendeles (h)ozzaadasa");
        System.out.println("Rendeles (m)odositasa");
        System.out.println("Rendeles (t)orlese");
        System.out.println("Rendeles (l)istaja");
        System.out.println("(V)issza a fomenube");
    }

    private void printOrderItem(){
        System.out.println("Uj tetel (h)ozzaadasa");
        System.out.println("Tetel (m)odositasa");
        System.out.println("Tetel (t)orlese");
        System.out.println("Rendeles (l)istaja");
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
                    case "r" -> {
                        printOrder();
                        while (!(s = sc.nextLine()).equalsIgnoreCase("v")) {
                            switch (s.toLowerCase()) {
                                case "h" -> {
                                    System.out.println("\n");
                                }
                                case "m" -> {
                                    System.out.println("\n");
                                }
                                case "t" -> {
                                    System.out.println("\n");
                                }
                                case "l" -> {
                                    List<Order> allO = controll.getAllOrder();
                                    for (Order o : allO) {
                                        System.out.println(o);
                                    }
                                    System.out.println("\n");
                                }
                                default -> System.out.println("Ilyen menuelem nincs, kerem valasszon ujra.\n");
                            }
                            this.printOrder();
                        }
                    }
                    case "v" -> {
                        printClient();
                        while (!(s = sc.nextLine()).equalsIgnoreCase("v")) {
                            switch (s.toLowerCase()) {
                                case "h" -> {
                                    System.out.println("\n");
                                }
                                case "m" -> {
                                    System.out.println("\n");
                                }
                                case "t" -> {
                                    System.out.println("\n");
                                }
                                case "l" -> {
                                    List<Client> allC = controll.getAllClient();
                                    for (Client c : allC) {
                                        System.out.println(c);
                                    }
                                    System.out.println("\n");
                                }
                                default -> System.out.println("Ilyen menuelem nincs, kerem valasszon ujra.\n");
                            }
                            this.printClient();
                        }
                    }
                    case "f" -> {
                        printCourier();
                        while (!(s = sc.nextLine()).equalsIgnoreCase("v")) {
                            switch (s.toLowerCase()) {
                                case "h" -> {
                                    System.out.println("\n");
                                }
                                case "m" -> {
                                    System.out.println("\n");
                                }
                                case "t" -> {
                                    System.out.println("\n");
                                }
                                case "l" -> {
                                    List<Courier> allC = controll.getAllCourier();
                                    for (Courier c : allC) {
                                        System.out.println(c);
                                    }
                                    System.out.println("\n");
                                }
                                default -> System.out.println("Ilyen menuelem nincs, kerem valasszon ujra.\n");
                            }
                            this.printCourier();
                        }
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
                                case "l" -> {
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
                    case "s" ->
                            System.out.println("kereses");
//                        this.startSearch(engine);
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
