import java.text.DecimalFormat;
import java.util.*;

class Present {
    double weight;
    double length;
    double width;
    double height;

    public Present(double weight, double length, double width, double height) {
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
    }
    public String getDimensionsAndWeight() {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(weight) + "kg " + df.format(length) + "cm x " + df.format(width) + "cm x " + df.format(height) + "cm";
    }
}

class Sleigh {
    double maxWeight;
    double maxLength;
    double maxWidth;
    double maxHeight;
    boolean driverHasThickBones;
    double currentWeight;
    double currentLength;
    double currentWidth;
    double currentHeight;

    public Sleigh(double maxWeight, double maxLength, double maxWidth, double maxHeight, boolean driverHasThickBones) {
        this.maxWeight = maxWeight;
        this.maxLength = maxLength;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.driverHasThickBones = driverHasThickBones;
        this.currentWeight = 0;
        this.currentLength = 0;
        this.currentWidth = 0;
        this.currentHeight = 0;
    }
    public double getMaxWeight() {
        return driverHasThickBones ? maxWeight - 25 : maxWeight;
    }
    public boolean isDriverHasThickBones() {
        return driverHasThickBones;
    }
    public String getMaxDimensions() {
        DecimalFormat df = new DecimalFormat("#.##");
        return "Maksymalna waga: " + df.format(maxWeight) + "kg, Maksymalna długość: " + df.format(maxLength) +
                "cm, Maksymalna szerokość: " + df.format(maxWidth) + "cm, Maksymalna wysokość: " + df.format(maxHeight) + "cm";
    }
    public boolean canLoadPresent(Present present) {
        return (currentWeight + present.weight <= getMaxWeight()) &&
                (currentLength + present.length <= maxLength) &&
                (currentWidth + present.width <= maxWidth) &&
                (currentHeight + present.height <= maxHeight);
    }
    public void loadPresent(Present present) {
        currentWeight += present.weight;
        currentLength += present.length;
        currentWidth += present.width;
        currentHeight += present.height;
    }
}

class Warehouse {
    List<Present> presents;

    public Warehouse(List<Present> presents) {
        this.presents = presents;
    }
}

public class SantaClausTask {

    public static void main(String[] args) {
        // generowanie losowych danych związanych z magazynami i saniami
        List<Warehouse> warehouses = generateWarehouses();
        List<Sleigh> sleighs = generateSleighs();

        // wyliczenie ile prezentów zostało załadowanych, a ile nie zmieściło się na sanie
        int loadedPresents = loadPresents(warehouses, sleighs);
        int remainingPresents = getTotalPresents(warehouses) - loadedPresents;

        // wyświetlenie danych o prezentach w magazynach
        for (int i = 0; i < warehouses.size(); i++) {
            Warehouse warehouse = warehouses.get(i);
            System.out.println("Magazyn " + (i + 1) + ":");

            for (int j = 0; j < warehouse.presents.size(); j++) {
                Present present = warehouse.presents.get(j);
                System.out.println("  Prezent " + (j + 1) + ": " + present.getDimensionsAndWeight());
            }
        }

        // wyświetlenie danych o kierowcach sań
        for (int i = 0; i < sleighs.size(); i++) {
            Sleigh sleigh = sleighs.get(i);
            System.out.println("Kierowca sań " + (i + 1) + ": " +
                    (sleigh.isDriverHasThickBones() ? "ma grube kości" : "nie ma grubych kości"));
            System.out.println("  " + sleigh.getMaxDimensions());
        }

        System.out.println("Załadowano prezenty: " + loadedPresents);
        System.out.println("Niezaładowane prezenty, które się nie mieszczą: " + remainingPresents);

    }

    // metoda odpowiedzialna za wygenerowanie losowych danych dla magazynów
    private static List<Warehouse> generateWarehouses() {
        List<Warehouse> warehouses = new ArrayList<>();
        Random random = new Random();

        int numWarehouses = random.nextInt(5) + 1; // wygenerowanie losowej liczby magazynów (1-5)

        for (int i = 0; i < numWarehouses; i++) {
            List<Present> presents = new ArrayList<>();
            int numPresents = random.nextInt(10) + 1; // wygenerowanie losowej liczby prezentów w magazynie (1-10)

            for (int j = 0; j < numPresents; j++) {
                double weight = random.nextDouble() * 20 + 1; // wygenerowanie losowej wagi (1-20 kg)
                double length = random.nextDouble() * 50 + 1; // wygenerowanie losowej długości (1-50 cm)
                double width = random.nextDouble() * 30 + 1;  // wygenerowanie losowej szerokości (1-30 cm)
                double height = random.nextDouble() * 40 + 1; // wygenerowanie losowej wysokości (1-40 cm)

                presents.add(new Present(weight, length, width, height));
            }

            warehouses.add(new Warehouse(presents));
        }

        return warehouses;
    }

    // metoda odpowiedzialna za wygenerowanie losowych danych dla sań
    private static List<Sleigh> generateSleighs() {
        List<Sleigh> sleighs = new ArrayList<>();
        Random random = new Random();

        int numSleighs = random.nextInt(3) + 1; // Losowa liczba sań (1-3)

        for (int i = 0; i < numSleighs; i++) {
            double maxWeight = random.nextDouble() * 100 + 50;    // Losowa maksymalna waga sanek (50-150 kg)
            double maxLength = random.nextDouble() * 200 + 100;   // Losowa maksymalna długość sanek (100-300 cm)
            double maxWidth = random.nextDouble() * 100 + 50;     // Losowa maksymalna szerokość sanek (50-150 cm)
            double maxHeight = random.nextDouble() * 120 + 60;    // Losowa maksymalna wysokość sanek (60-180 cm)
            boolean driverHasThickBones = random.nextDouble() < 0.2; // Szansa 20% na to, że kierowca ma grube kości

            sleighs.add(new Sleigh(maxWeight, maxLength, maxWidth, maxHeight, driverHasThickBones));
        }

        return sleighs;
    }

    // metoda odpowiedzialna za obliczanie liczby załadowanych prezentów
    private static int loadPresents(List<Warehouse> warehouses, List<Sleigh> sleighs) {
        int loadedPresents = 0;

        for (Warehouse warehouse : warehouses) {
            for (Present present : warehouse.presents) {
                boolean fitOnAnySleigh = false;

                for (Sleigh sleigh : sleighs) {
                    if (sleigh.canLoadPresent(present)) {
                        sleigh.loadPresent(present);
                        fitOnAnySleigh = true;
                        loadedPresents++;
                        break;
                    }
                }

                if (!fitOnAnySleigh) {
                    System.out.println("Nie można załadować prezentu: " + present.getDimensionsAndWeight() +
                            " z powodu przekroczenia limitu na jednych z sanek. Powód: " + System.lineSeparator() + getOverLimitReason(present, sleighs));
                }
            }
        }

        return loadedPresents;
    }

    private static String getOverLimitReason(Present present, List<Sleigh> sleighs) {
        StringBuilder reason = new StringBuilder();

        DecimalFormat df = new DecimalFormat("#.##");

        for (Sleigh sleigh : sleighs) {
            if (!sleigh.canLoadPresent(present)) {
                int sleighIndex = sleighs.indexOf(sleigh) + 1;

                if (sleigh.currentWeight + present.weight > sleigh.getMaxWeight()) {
                    reason.append("Przekroczona waga o ").append(df.format(sleigh.currentWeight + present.weight - sleigh.getMaxWeight()))
                            .append("kg (Sanie ").append(sleighIndex).append("), ");
                }
                if (sleigh.currentLength + present.length > sleigh.maxLength) {
                    reason.append("Przekroczona długość o ").append(df.format(sleigh.currentLength + present.length - sleigh.maxLength))
                            .append("cm (Sanie ").append(sleighIndex).append("), ");
                }
                if (sleigh.currentWidth + present.width > sleigh.maxWidth) {
                    reason.append("Przekroczona szerokość o ").append(df.format(sleigh.currentWidth + present.width - sleigh.maxWidth))
                            .append("cm (Sanie ").append(sleighIndex).append("), ");
                }
                if (sleigh.currentHeight + present.height > sleigh.maxHeight) {
                    reason.append("Przekroczona wysokość o ").append(df.format(sleigh.currentHeight + present.height - sleigh.maxHeight))
                            .append("cm (Sanie ").append(sleighIndex).append("), ");
                }
                reason.append(System.lineSeparator());
            }
        }

        if (!reason.isEmpty()) {
            reason.delete(reason.length() - 2, reason.length());
        }

        return reason.toString();
    }


    // metoda odpowiedzialna za obliczanie liczby wszystkich prezentów
    private static int getTotalPresents(List<Warehouse> warehouses) {
        int totalPresents = 0;

        for (Warehouse warehouse : warehouses) {
            totalPresents += warehouse.presents.size();
        }

        return totalPresents;
    }
    
}
