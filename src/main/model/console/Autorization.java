package console;

import java.util.Scanner;

import staff.autorization.Security;

public class Autorization {

    public String runApp(){
        String type = null;
        int i = 0;
        while (i < 1) {
            String[] s = autoriz();
            String log = s[0];
            String pas = s[1];
            type = client(log, pas);
            if (type != null) {
                System.out.println("Прошел авторизацию");
                i++;
            } else {
                System.out.println("Не прошел авторизацию");
            }
        }
        return type;
    }


    public String client(String l, String p) {
        String result = null;
        Security security = new Security(l, p, null);
        String autoriz = security.getAuthorization();
        if (autoriz != null) {
            result = autoriz;
        }
        return result;
    }

    public String[] autoriz() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите в лююом поле 0 для выхода");
        System.out.println("Введите логин");
        String login = in.nextLine();
        exit(login);
        System.out.println("Введите пароль");
        String password = in.nextLine();
        exit(password);
        return new String[]{login, password};
    }


    public  void exit(Object number){
        if (number instanceof String){
            if (number.equals("0")){
                System.out.println("Программа завершилась");
                System.exit(0);
            }
        }else {
            if (String.valueOf(number).equals("0")){
                System.out.println("Программа завершилась");
                System.exit(0);
            }
        }

    }






}
