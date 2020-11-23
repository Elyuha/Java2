package Lesson1;

import Lesson1.Obstacles.*;
import Lesson1.Team.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Course c = new Course(new Cross(2300), new Wall(7), new Water(10000)); // Создаем полосу препятствий
        Team team = new Team("GACHI MASTERS",
                new Human("Billy Harington", 10000, 20000, 10),
                new Human("Van Darkholme", 9888, 9888, 12),
                new Human("Ricardo Milos", 1200, 12222, 20000),
                new Human("Super Kazuya", 23, 12312312, 211212212)); // Создаем команду
        c.doIt(team); // Просим команду пройти полосу
        team.showResult(); // Показываем результаты
    }
}
