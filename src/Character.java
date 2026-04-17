import java.util.ArrayList;
import java.util.Random;

public class Character {
    Random random = new Random();

    public void setRandom(Random random) {
        this.random = random;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setInventory(ArrayList<String> inventory) {
        this.inventory = inventory;
    }

    public ArrayList<String> getInventory(ArrayList<String> inventory) {
        return inventory;
    }

    public Random getRandom() {
        return random;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    private String name;
    private int hp;
    private int attack;
    private ArrayList<String> inventory = new ArrayList<>();

    public Character(String name) {
        this.name = name;
    }

    public void generateStats() {
        hp = random.nextInt(50, 100);
        attack = random.nextInt(5, 20);
    }

    public void addItem(String item) {
        inventory.add(item);
    }

    public void removeItem(int index) {
        if (index >= 0 && index < inventory.size()) {
            inventory.remove(index);
        } else {
            System.out.println("Chyba");
        }

    }

    public void printInfo() {
        System.out.println("Name: " + this.name);
        System.out.println("HP: " + this.hp);
        System.out.println("Attack: " + this.attack);
        System.out.println("Inventory: " + this.inventory);
        for (int i = 0; i < this.inventory.size(); i++) {
            System.out.println(i + ": " + this.inventory.get(i));
        }
    }
}