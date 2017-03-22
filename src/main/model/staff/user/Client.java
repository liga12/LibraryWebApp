package staff.user;

import org.apache.log4j.Logger;

import static util.ClassNameUtil.getCurrentClassName;

public class Client extends Users {
    private static final Logger log = Logger.getLogger(getCurrentClassName());
    public Client(String name, String patronymic, String surname, String phone) {
        super(name, patronymic, surname, phone);
    }

    public Client() {
        super();

    }

    public String getfirstSumbolToUpperCsse(String str){
        log.debug("The input string \""+str+"\"");
        if (str.equals("")){
            log.debug("Don't exist a space \""+str+"\"");
            log.debug("Return string =  \""+str+"\"");
            return str;
        }
        String resultString = str.substring(0,1).toUpperCase()  + str.substring(1, str.length());
        log.debug("Don't exist a space \""+resultString+"\"");
        return  resultString;

    }
}
