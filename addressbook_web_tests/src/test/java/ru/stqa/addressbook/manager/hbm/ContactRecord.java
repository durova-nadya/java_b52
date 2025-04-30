package ru.stqa.addressbook.manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    @Column(name = "id")
    public int id;

    public String firstname;
    public String middlename = "Sergeevich";
    public String lastname;
    public String nickname = "pushkin";
    public String company = "December";
    public String title = "Best";
    public String address;
    public String home;
    public String mobile;
    public String work;
    public String phone2;
    public String fax ="+7(915)07-06-1799";
    public String email;
    public String email2 = "pushkin@ya.ru";
    public String email3 = "pushkin_a@ya.ru";
    public String homepage = "www.pushkin.ru";


    public ContactRecord() {}

    public ContactRecord(int id, String firstname, String lastname, String address, String email, String home, String mobile, String work) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.home = home;
        this.mobile = mobile;
        this.work = work;

    }
}
