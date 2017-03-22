package console;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import staff.item.Book;
import staff.user.Librarian;

public class RunnerLibrarian {
    int deep1 = 0;
    int deep2;
    String number;
    private Runner test = new Runner();
    private RunnerAdministrator administrator = new RunnerAdministrator();
    private String s0 = test.s0;
    private String s1 = "1 - Найти книгу 2 - Найти клиента 3 - Добавить клиента\n" +
            "4 - Редактировать клиента 5 - Выдать книгу 6- Получить книгу\n" +
            "7- Посмотреть книги в аренде 8 - Посмотреть список аренды" + new Runner().s00 + s0;
    private String s2 = administrator.s2;
    private String s3 = "Введите телефон";
    String s4 = "Введите ID";
    private String s5 = "Ввели не корректный ид";
    String s6 = "Найдите клиента и запомните его ID";
    private String s7 = "Найдите книжку и запомните ее ID";
    private String s8 = "(Не обязательно)Введите через сколько дней  вернетк книжки максимум 14";
    private String s9 = "Ввели не корректное число";

    public void run() {
        while (deep1 < 1) {
            number = test.enterNumber(s1);
            deep2 = test.checkNumbers(number, 8);
            if (deep2 == 2) {
                deep1++;
            }
            while (deep2 < 2) {
                if (number.equals("1")) {
                    deep2 = test.seachBook(deep2);
                }
                if (number.equals("2")) {
                    deep2 = test.searhUser(deep2);
                }
                if (number.equals("3")) {
                    addClient();
                    deep2++;
                }
                if (number.equals("4")) {
                    editClient();
                    deep2++;
                }
                if (number.equals("5")) {
                    issueBook();
                    deep2++;
                }
                if (number.equals("6")) {
                    returnBook();
                    deep2++;
                }
                if (number.equals("7")) {
                    findAllBookByRent();
                    deep2++;
                }
                if (number.equals("8")) {
                    findAllRent();
                    deep2++;
                }
            }
        }
    }

    public void addClient() {
        String name = test.enterNumber(test.s9);
        String patronim = test.enterNumber(test.s10 + s2);
        String surname = test.enterNumber(test.s11);
        String phone = test.enterNumber(s3);
        Librarian librarian = new Librarian(name, patronim, surname, phone);
        if (name.equals("") || surname.equals("") || phone.equals("")) {
            System.out.println("Не ввели нужные данные о клиенте");
        } else {
            if (librarian.addClient() == true) {
                System.out.println("Пользователь  добавлен");
            } else {
                System.out.println("Такой пользователь уже существует");
            }
        }
    }

    public void editClient() {
        int id = new Runner().chekIdByUserDb();
        String name = test.enterNumber(test.s9);
        String patronim = test.enterNumber(test.s10 + s2);
        String surname = test.enterNumber(test.s11);
        String phone = test.enterNumber(s3);
        Librarian librarian = new Librarian(name, patronim, surname, phone);

        if (name.equals("") || surname.equals("") || phone.equals("")) {
            System.out.println("Не ввели нужные данные о клиенте");
        } else {
            if (librarian.editClient(id) == true) {
                System.out.println("Пользователь  сохранен");
            } else {
                System.out.println("Такой пользователь не существует");
            }
        }
    }

    public void issueBook() {
        String date = null;
        int count = 0;
        int j = 0;
        int idUser = new Runner().chekIdByUserDb();
        int idBook = chekIdByBookDb();
        while (j < 1) {
            date = test.enterNumber(s8);
            if (date.equals("")) {
                date = "0";
                j++;
            }
            for (int i = 0; i < date.length(); i++) {
                if (date.charAt(i) < '0' || date.charAt(i) > '9') {
                    System.out.println(s9);
                    count++;
                    break;
                }
            }
            if (count == 0) {
                j++;
            }
            count = 0;
        }
        int dateInt = Integer.valueOf(date);
        if (dateInt < 1 || dateInt > 14) {
            System.out.println("Дата возврата через 14 дней");
            dateInt = 14;
        }
        LocalDate dateReturn = LocalDate.now().plusDays(dateInt);
        Librarian librarian = new Librarian();
        if (librarian.issueBook(idUser, idBook, dateReturn)) {
            System.out.println("Добавлено в аренду");
        }


    }

    public int chekIdByBookDb() {

        String id = null;
        int i = 0;
        int j = 0;
        System.out.println(s7);
        while (i < 1) {
            while (j < 1) {
                id = new Runner().chekId(2);
                Book book = new Book();
                int res = book.findBookById(Integer.valueOf(id)).size();
                if (res < 1) {
                    System.out.println("Такой ID не существует");
                } else {
                    j++;
                }
            }
            j = 0;
            Librarian librarian = new Librarian();
            int r = librarian.findBookByRent(Integer.valueOf(id)).size();
            if (r > 0) {
                System.out.println("Книга находиться в аренде");
            } else {
                i++;
            }
        }
        return Integer.valueOf(id);
    }

    public void returnBook() {
        boolean result;
        String idRent = null;
        int k = 0;
        int j = 0;
        int count = 0;
        int idUser = new Runner().chekIdByUserDb();

        Librarian librarian = new Librarian();
        List<List<String>> user = new ArrayList<>();
        user = librarian.findUserByRent(idUser);
        printRent(user);
        System.out.println("Введите  ID из предложенных ");
        while (j < 1) {
            while (k < 1) {
                idRent = test.enterNumber(s4);
                if (idRent.equals("")) {
                    System.out.println(s5);
                }
                for (int i = 0; i < idRent.length(); i++) {
                    if (idRent.charAt(i) < '0' || idRent.charAt(i) > '9') {
                        System.out.println(s5);
                        count++;
                        break;
                    }
                }
                if (count == 0) {
                    if (!idRent.equals("")) {
                        k++;
                    }
                }
                count = 0;
            }
            k = 0;
            if (new Librarian().findByRent(Integer.valueOf(idRent)).size() > 0) {
                j++;
            } else {
                System.out.println("Такого ID нет в аренде");
            }
        }
        result = new Librarian().returnBook(Integer.valueOf(idRent));
        if (result) {
            System.out.println("Книга получена у клиента");
        } else {
            System.out.println("Книга не получена у клиента");
        }

    }

    private void findAllBookByRent() {
        List<List<String>> bookRent = new Librarian().findAllBookByRent();
        if (bookRent.size() != 0) {
            for (List<String> list : bookRent) {
                String autorName = list.get(0);
                String autorPat = list.get(1);
                String autorSername = list.get(2);
                String name = list.get(3);
                System.out.println("Имя автора = " + autorName);
                System.out.println("Отчество автора = " + autorPat);
                System.out.println("Фамилия автора = " + autorSername);
                System.out.println("Название книги = " + name);
                System.out.println();
            }
        } else {
            System.out.println("В аренде нет пользователей");
        }
    }

    private void findAllUserByRent() {
        List<List<String>> bookRent = new Librarian().findAllUserByRent();
        if (bookRent.size() != 0) {
            for (List<String> list : bookRent) {
                String autorName = list.get(0);
                String autorPat = list.get(1);
                String autorSername = list.get(2);
                String phone = list.get(3);
                System.out.println("Имя  = " + autorName);
                System.out.println("Отчество  = " + autorPat);
                System.out.println("Фамилия  = " + autorSername);
                System.out.println("Телефон = " + phone);
                System.out.println();
            }
        } else {
            System.out.println("В аренде нет книг");
        }
    }

    public void findAllRent() {
        List<List<String>> rent = new Librarian().findAllRent();
        if (rent.size() != 0) {
            for (List<String> list : rent) {
                String autorName = list.get(0);
                String autorPat = list.get(1);
                String autorSername = list.get(2);
                String name = list.get(3);
                String userName = list.get(4);
                String userPat = list.get(5);
                String userSername = list.get(6);
                String phone = list.get(7);
                System.out.println("Имя автора  = " + autorName);
                System.out.println("Отчество автора  = " + autorPat);
                System.out.println("Фамилия автора  = " + autorSername);
                System.out.println("Название книги = " + name);
                System.out.println("Имя  = " + userName);
                System.out.println("Отчество  = " + userPat);
                System.out.println("Фамилия  = " + userSername);
                System.out.println("Телефон = " + phone);
                System.out.println();
            }
        } else {
            System.out.println("В аренде записей");
        }
    }



    public boolean printRent(List<List<String>> search) {
        boolean result = false;
        if (search.size() != 0) {
            for (List<String> list : search) {
                String id = list.get(0);
                String userName = list.get(1);
                String userSername = list.get(2);
                String name = list.get(3);
                String autorSurname = list.get(4);
                System.out.println("ID = " + id);
                System.out.println("Имя пользователя = " + userName);
                System.out.println("Фамилия пользователя = " + userSername);
                System.out.println("Название книги = " + name);
                System.out.println("Автор книги = " + autorSurname);
                System.out.println();
                result = true;
            }
        } else {
            System.out.println("Вы не должны книг");
        }
        return result;
    }
}
