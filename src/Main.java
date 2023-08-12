import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 100;
    public static String bossDefence;
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic","Medic"};
    public static int[] heroesHealth = {280, 270, 250, 500};
    public static int[] heroesDamage = {10, 15, 20, 0};
    public static int[] heroesHealing = {300};
    public static int roundNumber;

    public static void main(String[] args) {
        showStatistics();
        while (!isGameOver()) {
            playRound();
        }
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        bossAttack();
        heroesAttack();
        healing();
        showStatistics();
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); //  0, 1, 2
        bossDefence = heroesAttackType[randomIndex];
    }

    public static void bossAttack() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesAttack() {
        for (int i = 0; i < heroesDamage.length - 1; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = heroesDamage[i];
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    damage = heroesDamage[i] * coeff;
                    System.out.println("Critical damage: " + damage);
                }
                if (bossHealth - damage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - damage;
                }
            }
        }
    }

    public static void showStatistics() {
        System.out.println("ROUND " + roundNumber + " --------------");
        /*String defence;
        if (bossDefence == null) {
            defence = "No Defence";
        } else {
            defence = bossDefence;
        }*/
        System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage + " defence: " +
                (bossDefence == null ? "No Defence" : bossDefence));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " damage: " + heroesDamage[i]);

        }
    }
    public static void healing (){
        if (heroesHealth[3] > 0){
        for (int i = 0; i < heroesHealth.length - 1; i++) {
            if (heroesHealth[i] > 0 && heroesHealth[i] <= 100)
            {
                heroesHealth[i] += heroesHealing[0];

                System.out.println(heroesAttackType[i] + " получил лечение в размере " + heroesHealing[0] + " едиц, текущее здоровье : " + heroesHealth[i]);
break;
            } else {
                System.out.println(heroesAttackType[i] + " Лечения не будет, медик лечит только есть hp 100 или меньше");
            }
            }
        }       else if (heroesHealth[3] == 0)System.out.println("medic мертв лечения не будет");
    }
    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }
}
