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

    // @Column(name = "firstname")
    public String firstname;
    public String middlename = "Сергеевич";

   // @Column(name = "lastname")
    public String lastname;
    public String nickname = "pushkin";
    public String company = "December";
    public String title = "Best";

  //  @Column(name = "address")
    public String address;
    public String home = "home";
    public String mobile ="+7(915)007-06-99";
    public String work = "work";
    public String fax ="+7(915)07-06-1799";

  //  @Column(name = "email")
    public String email;
    public String email2 = "pushkin@ya.ru";
    public String email3 = "pushkin_a@ya.ru";
    public String homepage = "www.pushkin.ru";


    public ContactRecord() {}

    public ContactRecord(int id, String firstname, String lastname, String address, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.email = email;

    }



}
