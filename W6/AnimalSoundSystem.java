interface Swimmable {
    void swim();
}
abstract class Animal {
    public abstract void makeSound();
    public void breathe() {
        System.out.println("动物在呼吸");
    }
}
class Dog extends Animal implements Swimmable {
    public void makeSound() {
        System.out.println("狗发出汪汪的叫声");
    }
    public void swim() {
        System.out.println("狗会游泳");
    }
}
class Cat extends Animal {
    public void makeSound() {
        System.out.println("猫发出喵喵的叫声");
    }
}
public class AnimalSoundSystem {
    public static void main(String[] args) {
        Animal dog = new Dog();
        Animal cat = new Cat();
        dog.makeSound();
        cat.makeSound();
        dog.breathe();
        cat.breathe();
        if (dog instanceof Dog) {
            ((Dog) dog).swim();
        }
    }
}
