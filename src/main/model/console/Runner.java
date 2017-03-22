package console;

import java.util.List;
import java.util.Scanner;

import staff.item.Book;
import staff.user.FinderUser;
import staff.user.Librarian;

public class Runner {

    String s0 = " 0 - Выйти\nВведите цифру запроса";
    String s00 = " 9 - На одну выше";
    String s2 = "1 - Поиск по названию книги 2 - Поиск по описанию 3 - Поиск по имени автора\n" +
            "4 - Поиск по отчеству автора 5 - Поиск по фамилии автора 6 - Поиск по ключевому слову\n" +
            "7 - Вывести все книги\n" + s00 + s0;
    String s3 = "Введите название книги";
    String s4 = "Введите описание";
    String s5 = "Введите имя автора";
    String s6 = "Введите фамилию автора";
    String s7 = "Введите отчество автора";
    String s8 = "Введите слово";
    String s9 = "Введите имя";
    String s10 = "Введите отчество";
    String s11 = "Введите фамилию";
    String s12 = "1- Поиск по имени 2 - Поиск по отчеству 3 - Поиск по фамилии\n" +
            "4 - Вывести всех пользователей" + s00 + s0;
    String s13 = "Ввели недопустимую цифру команды";
    String s14 = "Ничего не нашел";

    public void run() {
        while (true) {
            Autorization autorization = new Autorization();
            String typeUser = autorization.runApp();

            if (typeUser.equals("Client")) {
                System.out.println("Вы клиент");
                RunnerClient client = new RunnerClient();
                client.run();
            }
            if (typeUser.equals("Administrator")) {
                System.out.println("Вы администратор");
                RunnerAdministrator administrator = new RunnerAdministrator();
                administrator.run();
            }
            if (typeUser.equals("Librarian")) {
                System.out.println("Вы библиотекарь");
                RunnerLibrarian librarian = new RunnerLibrarian();
                librarian.run();
            }
        }
    }


    public int searhUser(int result) {

        while (result < 2) {
            String searh = enterNumber(s12);
            if (searh.equals("0")) {
                new Autorization().exit(0);
            } else if (searh.equals("1")) {
                FinderUser user = new FinderUser(enterNumber(s9), "", "", "");
                printUser(user.findUser(2));
            } else if (searh.equals("2")) {
                FinderUser user = new FinderUser("", enterNumber(s10), "", "");
                printUser(user.findUser(4));
            } else if (searh.equals("3")) {
                FinderUser user = new FinderUser("", "", enterNumber(s11), "");
                printUser(user.findUser(3));
            } else if (searh.equals("4")) {
                FinderUser user = new FinderUser();
                printUser(user.findUser(1));
            } else if (searh.equals("9")) {
                result += 2;
            } else {
                System.out.println(s13);

            }
        }
        return result;
    }



    public int seachBook(int result) {

        while (result < 2) {
            String searh = enterNumber(s2);
            if (searh.equals("0")) {
                new Autorization().exit(0);
            } else if (searh.equals("1")) {
                Book book = new Book(enterNumber(s3), "", "", "", "");
                printBook(book.findBook(2));
            } else if (searh.equals("2")) {
                Book book = new Book("", enterNumber(s4), "", "", "");
                printBook(book.findBook(3));
            } else if (searh.equals("3")) {
                Book book = new Book("", "", enterNumber(s5), "", "");
                printBook(book.findBook(4));
            } else if (searh.equals("4")) {
                Book book = new Book("", "", "", enterNumber(s7), "");
                printBook(book.findBook(5));
            } else if (searh.equals("5")) {
                Book book = new Book("", "", "", "", enterNumber(s6));
                printBook(book.findBook(6));
            } else if (searh.equals("6")) {
                Book book = new Book(enterNumber(s8), "", "", "", "");
                printBook(book.findBook(7));
            } else if (searh.equals("7")) {
                Book book = new Book();
                printBook(book.findBook(1));
            } else if (searh.equals("9")) {
                result++;
            } else {
                System.out.println(s13);
            }
        }
        return result;
    }


    public String enterNumber(String str) {
        Scanner in = new Scanner(System.in);
        System.out.println(str);
        return in.nextLine();
    }

    public int checkNumbers(String num, int str) {
        int result = 0;
        if (num.equals("0")) {
            new Autorization().exit(0);
        } else if (num.length() > 1) {
            System.out.println(s13);
            return result = 3;
        } else if (num.equals("9")) {
            return 2;
        } else if (num.equals("")) {
            System.out.println(s13);
            return result = 3;
        }
        int j = 0;
        while (j < 1) {
            for (int i = 1; i <= str; i++) {
                if (num.equals(String.valueOf(i))) {
                    result = 1;
                    return result;
                }
            }

            System.out.println(s13);
            return result;
        }

        return result;
    }

    public void printBook(List<List<String>> search) {
        if (search.size() != 0) {
            for (List<String> list : search) {
                String id = list.get(0);
                String name = list.get(1);
                String discr = list.get(2);
                String autorName = list.get(3);
                String autorPatr = list.get(4);
                String autorSername = list.get(5);
                System.out.println("ID = " + id);
                System.out.println("Название книги = " + name);
                System.out.println("Описание = " + discr);
                System.out.println("Имя автора = " + autorName);
                System.out.println("Отчество автора = " + autorPatr);
                System.out.println("Фамилия автора = " + autorSername);
                System.out.println();
            }
        } else {
            System.out.println(s14);
        }
    }

    private  void printUser(List<List<String>> search) {
        if (search.size() != 0) {
            for (List<String> list : search) {
                String id = list.get(0);
                String userName = list.get(1);
                String userPatr = list.get(2);
                String userSername = list.get(3);
                String userPhone = list.get(4);
                System.out.println("ID = " + id);
                System.out.println("Имя пользователя = " + userName);
                System.out.println("Отчество пользователя = " + userPatr);
                System.out.println("Фамилия пользователя = " + userSername);
                System.out.println("Телефон пользователя = " + userPhone);
                System.out.println();
            }
        } else {
            System.out.println(s14);
        }
    }

    public int chekIdByUserDb() {
        String id = null;
        int i = 0;
        System.out.println(new RunnerLibrarian().s6);
        while (i < 1) {
            id = chekId(1);
            Librarian librarian = new Librarian();
            int res = librarian.findUserById(Integer.valueOf(id)).size();
            if (res < 1) {
                System.out.println("Такой ID не существует");
            } else {
                i++;
            }
        }
        return Integer.valueOf(id);
    }

    public String chekId(int num) {
        String id = null;
        int count = 0;
        int j = 0;
        if (num == 1) {
            searhUser(1);
        }
        if (num == 2) {
            seachBook(1);
        }

        while (j < 1) {
            while (j < 1) {
                id = enterNumber(new RunnerLibrarian().s4);
                if (id.equals("")) {
                    System.out.println(s5);
                    continue;
                }
                for (int i = 0; i < id.length(); i++) {
                    if (id.charAt(i) < '0' || id.charAt(i) > '9') {
                        System.out.println(s5);
                        count++;
                        break;
                    }
                }

                if (count == 0) {
                    j++;
                }
                count = 0;
            }
        }
        return id;
    }
}
