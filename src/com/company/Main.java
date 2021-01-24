package com.company;

import java.io.*;
import java.util.logging.*;

public class Main {

    private final static Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private static void prLine(int l)
    {
        for (int i = 0; i < l; ++i) {
            System.out.printf("=");
        }
        System.out.printf("\n");
    }

    public static void main(String[] args) throws IOException {
        logr.setLevel(Level.INFO);
        FileHandler fh = new FileHandler("ProdLogs.log");
        logr.addHandler(fh);
        fh.setFormatter(new SimpleFormatter());

        String LinkFile = "C:\\product.txt";
        File notExist = new File(LinkFile);
        if (notExist.exists()) { //Истинность существования файла
            logr.info("Существование файла: " + notExist.exists()); //Файл существует
        }
        else{
            logr.info("Не возможно найти файл. Убедитесь, что файл существет и его имя верно."); //Файл не существует
        }
        if (notExist.exists()) { //Выполняется формирование чека при
            BufferedReader r = new BufferedReader(new FileReader(LinkFile));
                String s;
                double total = 0;

                System.out.printf("%-20s %-10s %-8s %9s\n", "Наименование", "Цена", "Кол-во", "Сумма");
                prLine(50);

                while (true) {
                    String name;
                    double price;
                    double quantity;
                    s = r.readLine();
                    if (s == null)
                        break;
                    name = s;

                    s = r.readLine();
                    if (s == null)
                        break;
                    quantity = Double.parseDouble(s);

                    s = r.readLine();
                    if (s == null)
                        break;
                    price = Double.parseDouble(s);

                    System.out.printf("%-20s %-10.2f %-8.3f %9.2f\n", name, price, quantity, (price * quantity));
                    total += (price * quantity);
                }
                r.close();

                prLine(50);
                System.out.printf("Итого: %g\n", total);
                prLine(50);
        }
    }
}
