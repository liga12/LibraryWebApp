package console;

import java.util.List;

import staff.autorization.Security;
import staff.item.Book;
import staff.user.Administrator;

public class RunnerAdministrator {
    int deep1 = 0;
    int deep2;
    String number;
    String s2 = " (Не обязательно)";
    private Runner runner = new Runner();
    private String s0 = runner.s0;
    private String s1 = "1 - Найти книгу 2 - Найти клиента 3 - Добавить книгу\n" +
            "4 - Ввести все  данные авторизации 5 - Добавить данны в авторизацию" + new Runner().s00 + s0;
    private String s3 = "Введите логин";
    private String s4 = "Введите пароль";
    private String s5 = "Введите Тип 1 - Администратор 2 - Библиотекарь 3 - Клиент";

    public void run() {
        while (deep1 < 1) {
            number = runner.enterNumber(s1);
            deep2 = runner.checkNumbers(number, 5);
            if (deep2 == 2) {
                deep1++;
            }
            while (deep2 < 2) {
                if (number.equals("1")) {
                    deep2 = runner.seachBook(deep2);
                }
                if (number.equals("2")) {
                    deep2 = runner.searhUser(deep2);
                }
                if (number.equals("3")) {
                    addBook();
                    deep2++;
                }
                if (number.equals("4")) {
                    findAllAutorization();
                    deep2++;
                }
                if (number.equals("5")) {
                    addAutorization();
                    deep2++;
                }
            }
        }
    }

    public void addBook() {

        String name = runner.enterNumber(runner.s3);
        String discr = runner.enterNumber(runner.s4 + s2);
        String autorName = runner.enterNumber(runner.s5);
        String autorPatr = runner.enterNumber(runner.s7 + s2);
        String autorSername = runner.enterNumber(runner.s6);
        Book book = new Book(name, discr, autorName, autorPatr, autorSername);
        if (name.equals("") || autorName.equals("") || autorSername.equals("")) {
            System.out.println("Не ввели нужные данные о книге");
        } else {
            if (book.addBook() == true) {
                System.out.println("Книга добавлена в библиотеку");
            } else {
                System.out.println("Такая книга уже есть в библиотеке");
            }
        }
    }

    public void addAutorization() {
        String login = null;
        String password;
        String type = null;
        boolean result = false;
        while (!result) {
            login = runner.enterNumber(s3);
            Security security = new Security(login, null, null);
            if (!security.loginExist()) {
                System.out.println("Логин существует придумайте другой");
            } else {
                result = true;
            }
        }
        password = runner.enterNumber(s4);
        while (result) {
            type = runner.enterNumber(s5);
            if (type.equals("1")) {
                result = false;
            } else if (type.equals("2")) {
                result = false;
            } else if (type.equals("3")) {
                result = false;
            } else {
                System.out.println("Ввели некорректное число");
            }
        }
        Security s = new Security(login, password, Integer.valueOf(type));
        if (s.addAuthorization()) {
            System.out.println("Данные добавлены");
        }
    }


    public void findAllAutorization() {
        Administrator administrator = new Administrator();
        List<List<String>> type = administrator.findAllAutorization();
        if (type.size() != 0) {
            for (List<String> strings : type) {
                String name = strings.get(0);
                String login = strings.get(1);
                String password = strings.get(2);
                if (name.equals("Administrator")) {
                    name = "Администратор";
                }
                if (name.equals("Librarian")) {
                    name = "Библиотекарь";
                }
                if (name.equals("Client")) {
                    name = "Клиент";
                }
                System.out.println("Тип  = " + name);
                System.out.println("Логин  = " + login);
                System.out.println("Пароль  = " + password);
                System.out.println();
            }
        } else {
            System.out.println("В аренде нет книг");
        }
    }


}
