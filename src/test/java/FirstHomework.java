import org.apache.commons.math3.util.Precision;
import org.junit.jupiter.api.Test;

public class FirstHomework {

    @Test
    public void HomeworkTasks() {
        TaskOne("Dima", 5);
        TaskTwo(517, 48.7);
        TaskThree(9);
        TaskFour(10, 5, 2, 5);
    }

    private void TaskOne(String name, int age) {
        System.out.println("Привет, " + name + ", тебе увы аж " + age);
    }

    private void TaskTwo(double distance, double fuel) {
        System.out.println("Ты проехал " + distance + " км. Залил -  " + fuel + " литров бензина. Твой расход: " + Precision.round(((fuel / distance) * 100), 1) + " л / 100км");
    }

    private void TaskThree(double radius) {
        System.out.println("Радиус твоего колеса: " + radius + " см. Длина окружности составляет: " + Precision.round((radius * 2 * (Math.PI)), 2) + " см. Количество оборотов для преодоления 1000км = " + Precision.round((100000000 / (radius * 2 * (Math.PI))), 1));
    }

    private void TaskFour(double x1, double y1, double x2, double y2) {
        System.out.println("Расстояние между двумя координатами: " + Precision.round(Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2)), 2) + " метра. ");
    }
}

