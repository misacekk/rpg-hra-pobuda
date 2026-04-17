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
                enemy.generateStats();
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

            int utokA;
            int kritickyA = random.nextInt(5);
            if (kritickyA == 1) {
                utokA = a.getAttack() * 3;
                System.out.print("KRITICKÝ ZÁSAH! ");
            } else {
                utokA = a.getAttack();
            }
            b.setHp(b.getHp() - utokA);
            System.out.println(a.getName() + " zasáhl za " + utokA + " damage. " + b.getName() + " zbývá " + b.getHp() + " HP.");

            if (b.getHp() <= 0) {
                break;
            }

            int utokB;
            int kritickyB = random.nextInt(5);
            if (kritickyB == 1) {
                utokB = b.getAttack() * 3;
                System.out.print("KRITICKÝ ZÁSAH! ");
            } else {
                utokB = b.getAttack();
            }
            a.setHp(a.getHp() - utokB);
            System.out.println(b.getName() + " zasáhl za " + utokB + " damage. " + a.getName() + " zbývá " + a.getHp() + " HP.");

            if (a.getHp() <= 0) {
                break;
            }

            Iterator<String> itA = a.getInventory().iterator();
            while (itA.hasNext()) {
                if (itA.next().equals("Lektvar")) {
                    a.setHp(a.getHp() + 20);
                    itA.remove();
                    System.out.println(a.getName() + " použil lektvar (+20 HP). Aktuální HP: " + a.getHp());
                    break;
                }
            }

            Iterator<String> itB = b.getInventory().iterator();
            while (itB.hasNext()) {
                if (itB.next().equals("Lektvar")) {
                    b.setHp(b.getHp() + 20);
                    itB.remove();
                    System.out.println(b.getName() + " použil lektvar (+20 HP). Aktuální HP: " + b.getHp());
                    break;
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