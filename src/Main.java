import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String[] product = {"Хлеб", "Молоко", "Крупа", "Кока-Кола"};
        int[] price = {50, 75, 90, 500};

        System.out.println("Список товаров:");
        for (int i = 0; i < product.length; i++) {
            System.out.println(i + 1 + ") " + product[i] + " - " + price[i] + " " + "руб");
        }
        System.out.println();

        Basket basket = new Basket(product, price);
        basket.addToCart(0, 10);
        basket.addToCart(1, 5);
        basket.addToCart(2, 3);
        basket.addToCart(0, 2);
        basket.printCart();

        Basket loadedBasket1 = Basket.loadFromTxtFile(new File("Basket/basket.txt"));

        Basket loadedBasket2 = Basket.loadFromBinFile(new File("Basket/basket.bin"));
    }
}