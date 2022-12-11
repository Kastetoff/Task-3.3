import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Basket {
    protected String[] productName;
    protected int[] productPrice;
    protected int[] list;
    protected int sum;

    public Basket(String[] productName, int[] productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.list = new int[productName.length];
    }

    public Basket() {

    }

    public void addToCart(int productNum, int amount) throws IOException {
        if (!(amount < 0 || productNum > productName.length || productNum < 0)) {
            list[productNum] += amount;
            sum += (productPrice[productNum]) * amount;
            saveTxt(new File("Basket/basket.txt"));
            System.out.println(productName[productNum] + " в количестве " + amount + " " + " - добавлено в корзину");
        }
    }

    public void printCart() {
        System.out.println();
        System.out.println("Ваша корзина: ");
        for (int i = 0; i < productName.length; i++) {
            if (list[i] != 0) {
                System.out.println(productName[i] + " - " + list[i] + " " + " по " + productPrice[i] + " руб / ");
                System.out.println("\t\t на сумму " + list[i] * productPrice[i] + " руб");
            }
        }
        System.out.println("ИТОГО: " + sum + " руб");
    }

    public void saveTxt(File textFile) throws IOException {
        try (PrintWriter out = new PrintWriter(textFile)) {
            out.println(productName.length);

            for (String product : productName) {
                out.print(product + " ");
            }
            out.println();

            for (int count : list) {
                out.print(count + " ");
            }
        }
    }

    public static Basket loadFromTxtFile(File textFile) throws IOException {
        if (textFile.exists()) {
            try (Scanner scanner = new Scanner(textFile)) {
                int countTypes = Integer.parseInt(scanner.nextLine());

                String[] productName = scanner.nextLine().trim().split(" ");

                int[] list = new int[countTypes];
                String[] countProducts = scanner.nextLine().trim().split(" ");
                for (int i = 0; i < countProducts.length; i++) {
                    list[i] = Integer.parseInt(countProducts[i]);
                }

                Basket basket = new Basket();
                basket.productName = productName;
                basket.list = list;
                System.out.println("Корзина восстановлена!");
                return basket;
            }
        } else {
            System.out.println("Корзина не найдена!");
            return null;
        }
    }
}