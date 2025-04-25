package ru.stqa.addressbook.model;

public record ContactData(
        String id,
        String firstname,
        String lastname,
        String address,
        String email,
        String home,
        String mobile,
        String work,
        String phone2
) {

    public ContactData() {
        this("", "", "", "", "", "", "", "", "");
    }


    public ContactData withId(String id) {
        return new ContactData(id, this.firstname, this.lastname, this.address, this.email, this.home, this.mobile, this.work,this.phone2);
    }

    public ContactData withFirstName(String firstname) {
        return new ContactData(this.id, firstname, this.lastname, this.address, this.email, this.home, this.mobile, this.work,this.phone2);
    }

    public ContactData withLastName(String lastname) {
        return new ContactData(this.id, this.firstname, lastname, this.address, this.email, this.home, this.mobile, this.work,this.phone2);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstname, this.lastname, address, this.email, this.home, this.mobile, this.work,this.phone2);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, email, this.home, this.mobile, this.work,this.phone2);
    }

    public ContactData withHome(String home) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.email, home, this.mobile, this.work,this.phone2);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.email, this.home, mobile, this.work,this.phone2);
    }

    public ContactData withWork(String work) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.email, this.home, this.mobile, work,this.phone2);
    }

    public ContactData withPhone2(String phone2) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.email, this.home, this.mobile, this.work, phone2);
    }

}
