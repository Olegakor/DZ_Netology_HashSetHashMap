import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

class Address {
    private String country;
    private String city;

    public Address(String country, String city) {
        this.country = country;
        this.city = city;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Address)) return false;
        Address address = (Address) obj;
        return country.equals(address.country) && city.equals(address.city);
    }

    @Override
    public int hashCode() {
        return 31 * country.hashCode() + city.hashCode();
    }

    @Override
    public String toString() {
        return country + ", " + city;
    }
}

public class DeliveryService {
    public static void main(String[] args) {
        Map<Address, Double> costPerAddress = new HashMap<>();

        // Заполнение мапы адресами и ценами
        costPerAddress.put(new Address("Россия", "Москва"), 200.0);
        costPerAddress.put(new Address("Россия", "Санкт-Петербург"), 200.0);
        costPerAddress.put(new Address("Россия", "Сочи"), 200.0);
        costPerAddress.put(new Address("Великобритания", "Лондон"), 250.0);
        costPerAddress.put(new Address("Турция", "Стамбул"), 250.0);
        costPerAddress.put(new Address("США", "Нью-Йорк"), 300.0);

        Scanner scanner = new Scanner(System.in);
        double totalCost = 0;
        Set<String> uniqueCountries = new HashSet<>();

        while (true) {
            System.out.println("Заполнение нового заказа.");
            System.out.print("Введите страну (или 'end' для завершения): ");
            String country = scanner.nextLine();
            if (country.equalsIgnoreCase("end")) {
                break;
            }

            System.out.print("Введите город: ");
            String city = scanner.nextLine();

            System.out.print("Введите вес (кг): ");
            double weight = scanner.nextDouble();
            scanner.nextLine(); // очистка буфера

            Address address = new Address(country, city);
            if (costPerAddress.containsKey(address)) {
                double costPerKg = costPerAddress.get(address);
                double deliveryCost = costPerKg * weight;
                totalCost += deliveryCost;

                // Добавляем страну в множество уникальных стран
                uniqueCountries.add(country);

                System.out.printf("Стоимость доставки составит: %.2f руб.\n", deliveryCost);
                System.out.printf("Общая стоимость всех доставок: %.2f руб.\n", totalCost);
            } else {
                System.out.println("Доставки по этому адресу нет");
            }
        }

        // Вывод информации о количестве уникальных стран
        System.out.printf("Количество уникальных стран: %d\n", uniqueCountries.size());

        scanner.close();
        System.out.println("Программа завершена.");
    }
}