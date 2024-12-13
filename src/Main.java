// Интерфейс просмотра фильма
interface Movie {
    void play();
}

// Реальный объект — фильм
class RealMovie implements Movie {
    @Override
    public void play() {
        System.out.println("Фильм включён. Приятного просмотра!");
    }
}

// Прокси — проверка доступа к фильму
class MovieProxy implements Movie {
    private RealMovie realMovie;
    private boolean hasAccess; // Проверка: купил ли пользователь фильм

    public MovieProxy(boolean hasAccess) {
        this.hasAccess = hasAccess;
    }

    @Override
    public void play() {
        if (hasAccess) {
            if (realMovie == null) {
                realMovie = new RealMovie(); // Создаём фильм только если доступ разрешён
            }
            realMovie.play();
        } else {
            System.out.println("Доступ к фильму запрещён. Купите фильм, чтобы его посмотреть.");
        }
    }
}

// Интерфейс базы данных
interface Database {
    void insert();

    void select();

    void remove();
}

// Приложение, выполняющее операции с объектами
class App {
    public void saveObject() {
        System.out.println("Сохранение объекта...");
    }

    public void loadObject() {
        System.out.println("Загрузка объекта...");
    }

    public void delObject() {
        System.out.println("Удаление объекта...");
    }
}

// Адаптер между приложением и интерфейсом базы данных
class AdapterAppToDatabase extends App implements Database {
    @Override
    public void insert() {
        saveObject();
    }

    @Override
    public void select() {
        loadObject();
    }

    @Override
    public void remove() {
        delObject();
    }
}

// Абстрактный класс Program — связывает систему (Program) и разработчика (Developer)
abstract class Program {
    protected Developer developer; // Разработчик, который будет писать код

    // Конструктор, связывающий программу с разработчиком
    protected Program(Developer developer) {
        this.developer = developer;
    }

    // Абстрактный метод, который будет реализован в подклассах
    public abstract void developProgram();
}

// Интерфейс разработчика
interface Developer {
    void writeCode(); // Метод, который будет реализовывать написание кода
}

// Конкретный разработчик — Java-разработчик
class JavaDeveloper implements Developer {
    @Override
    public void writeCode() {
        System.out.println("Java-разработчик пишет код...");
    }
}

// Конкретный разработчик — Python-разработчик
class PythonDeveloper implements Developer {
    @Override
    public void writeCode() {
        System.out.println("Python-разработчик пишет код...");
    }
}

// Конкретная программа — банковская система
class BankSystem extends Program {
    protected BankSystem(Developer developer) {
        super(developer); // Передаём разработчика в родительский класс
    }

    @Override
    public void developProgram() {
        System.out.println("Разработка банковской системы в процессе...");
        developer.writeCode(); // Вызываем метод разработчика
    }
}

// Конкретная программа — система биржи
class StockExchange extends Program {
    protected StockExchange(Developer developer) {
        super(developer); // Передаём разработчика в родительский класс
    }

    @Override
    public void developProgram() {
        System.out.println("Разработка биржевой системы в процессе...");
        developer.writeCode(); // Вызываем метод разработчика
    }
}

// Главный класс программы
public class Main {
    public static void main(String[] args) {
        // История 1: Миролюб пытается посмотреть фильм без покупки
        System.out.println("Миролюб пытается включить фильм:");
        Movie person1Movie = new MovieProxy(false); // Миролюб не купил фильм
        person1Movie.play();

        // История 2: Светозар купил фильм и смотрит его
        System.out.println("\nСветозар пытается включить фильм:");
        Movie person2Movie = new MovieProxy(true); // Светозар купил фильм
        person2Movie.play();

        System.out.println("\n");

        // Работа с базой данных через адаптер
        Database db = new AdapterAppToDatabase();
        db.insert();
        db.select();
        db.remove();

        System.out.println("\n");

        // Создаём массив программ с разными разработчиками
        Program[] progs = {
                new BankSystem(new JavaDeveloper()), // Банковская система с Java-разработчиком
                new StockExchange(new PythonDeveloper()) // Биржевая система с Python-разработчиком
        };

        // Обходим массив программ и запускаем их разработку
        for (Program prog : progs) {
            prog.developProgram();
        }
    }
}