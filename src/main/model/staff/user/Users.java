package staff.user;

abstract public class Users {
    private String name;
    private String patronymic;
    private String surname;
    private String phone;

    public Users(String name, String patronymic, String surname, String phone) {
        this.name = name.replace(" ", "").toLowerCase();
        this.patronymic = patronymic.replace(" ", "").toLowerCase();
        this.surname = surname.replace(" ", "").toLowerCase();
        this.phone = phone.replace(" ", "").toLowerCase();
    }

    public Users() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
