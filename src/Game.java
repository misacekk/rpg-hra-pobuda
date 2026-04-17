import java.util.*;

public class Game {

    Scanner sc = new Scanner(System.in);
    Random random = new Random();

    public void start() {
        System.out.println("Program začal.");
        System.out.println("Zadej jméno:");
        String name = sc.nextLine().trim();
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();

        Character player = new Character(name);
        player.generateStats();

        List<String> itemy = new ArrayList<>(Arrays.asList("Musil", "Borec", "Lektvar", "Eismann", "Megatron", "Optimus"));

        for (int i = 0; i < 3; i++) {
            int nahodnyIndex = random.nextInt(itemy.size());
            String vybranaVec = itemy.remove(nahodnyIndex);
            player.addItem(vybranaVec);
        }

        int choice;
        System.out.println();

        do {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("1 - vypiš postavu");
            System.out.println("2 - souboj");
            System.out.println("3 - načtení itemu");
            System.out.println("4 - odebráni itemu");
            System.out.println("5 - konec");
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Volba: ");
            choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                player.printInfo();
            }

            if (choice == 2) {
                Character enemy = new Character("BOSSOVSKÝMAJITELPEŇÁZÍ");
                fight(player, enemy);
            }

            if (choice == 3) {
                System.out.println("Načti item: ");
                String item = sc.nextLine();
                player.addItem(item);
            }

            if (choice == 4) {
                System.out.println("Načti index: ");
                int index = sc.nextInt();
                player.removeItem(index);
            }

            System.out.println();
        } while (choice != 5);

        System.out.println("Program skončil.");
    }

    public void fight(Character a, Character b) {

        System.out.println("Souboj začíná!");
        System.out.println(a.getName() + " VS " + b.getName());

        while (a.getHp() > 0 && b.getHp() > 0) {

            int kritickyA = random.nextInt(5);
            if (kritickyA == 1) {
                int utokA = a.getAttack() * 3;
                b.setHp(b.getHp() - utokA);
            } else {
                int utokA = a.getAttack();
                b.setHp(b.getHp() - utokA);
            }
            if (b.getHp() <= 0) {
                break;
            }

            int kritickyB = random.nextInt(5);
            if (kritickyB == 1) {
                int utokB = b.getAttack() * 3;
                a.setHp(a.getHp() - utokB);
            } else {
                int utokB = b.getAttack();
                a.setHp(a.getHp() - utokB);
            }
            if (a.getHp() <= 0) {
                break;
            }

            for (String item : a.getInventory()) {
                if (item.equals("Lektvar")) {
                    a.setHp(a.getHp() + 20);
                }
            }

            for (String item : b.getInventory()) {
                if (item.equals("Lektvar")) {
                    b.setHp(b.getHp() + 20);
                }
            }

        }
        if (a.getHp() > 0) {
            System.out.println("Vítěz je: " + a.getName());
        } else {
            System.out.println("Vítěz je: " + b.getName());
        }

    }
}