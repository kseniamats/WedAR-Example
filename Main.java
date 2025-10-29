Завдання 1. Базовий тип товару 
  
// Завдання 1.1 — Абстрактний клас Product
public abstract class Product {
    protected String name;
    protected double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public abstract String getInfo();
}

// Завдання 1.2 — Клас Pizza, що наслідує Product
public class Pizza extends Product implements Discountable {
    private String size;

    public Pizza(String name, double price, String size) {
        super(name, price);
        this.size = size;
    }

    @Override
    public String getInfo() {
        return "Pizza " + name + " (" + size + ") - " + price + " UAH";
    }

    @Override
    public double applyDiscount(double percent) {
        return price - (price * percent / 100.0);
    }
}

// Завдання 1.3 — Клас Drink, що наслідує Product
public class Drink extends Product implements Discountable {
    private boolean isCold;

    public Drink(String name, double price, boolean isCold) {
        super(name, price);
        this.isCold = isCold;
    }

    @Override
    public String getInfo() {
        String temp = isCold ? "cold" : "hot";
        return name + " [" + temp + "] - " + price + " UAH";
    }

    @Override
    public double applyDiscount(double percent) {
        return price - (price * percent / 100.0);
    }
}

Завдання 2. Знижки через інтерфейс

// Завдання 2 — Інтерфейс Discountable
public interface Discountable {
    double applyDiscount(double percent);
}

Завдання 3. Узагальнений кошик (дженерік + масив)

  // Завдання 3 — Дженерік клас Cart<T>
import java.util.function.Function;

public class Cart<T> {
    private T[] items;
    private int count;

    @SuppressWarnings("unchecked")
    public Cart() {
        items = (T[]) new Object[10]; // масив на 10 елементів
        count = 0;
    }

    public void addItem(T item) {
        if (count < items.length) {
            items[count++] = item;
        } else {
            System.out.println("Cart is full. Cannot add more items.");
        }
    }

    public int getCount() {
        return count;
    }

    public String printCart(Function<T, String> formatter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(formatter.apply(items[i])).append("\n");
        }
        return sb.toString();
    }
}

Завдання 4. Перевірка роботи (main)

  // Завдання 4 — Перевірка роботи
public class Main {
    public static void main(String[] args) {

        // 1. Створюємо товари
        Pizza p1 = new Pizza("Margherita", 189.0, "Large");
        Pizza p2 = new Pizza("Pepperoni", 210.0, "Medium");

        Drink d1 = new Drink("Cola", 45.0, true);
        Drink d2 = new Drink("Tea", 30.0, false);

        // 2. Знижка
        double newPrice = p1.applyDiscount(10); // 10%
        System.out.println("Price after discount: " + newPrice);

        // 3. Створюємо кошики
        Cart<Pizza> pizzaCart = new Cart<>();
        Cart<Drink> drinkCart = new Cart<>();

        // 4. Додаємо товари
        pizzaCart.addItem(p1);
        pizzaCart.addItem(p2);

        drinkCart.addItem(d1);
        drinkCart.addItem(d2);

        // 5. Друк з використанням лямбд
        String pizzasText = pizzaCart.printCart(
                item -> item.getInfo()
        );

        String drinksText = drinkCart.printCart(
                drink -> "Drink: " + drink.getInfo()
        );

        System.out.println("=== Pizza cart ===");
        System.out.println(pizzasText);

        System.out.println("=== Drink cart ===");
        System.out.println(drinksText);

        // 6. Кількість
        System.out.println("Pizza count = " + pizzaCart.getCount());
        System.out.println("Drink count = " + drinkCart.getCount());
    }
}
