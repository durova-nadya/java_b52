package model;

public record ContactData(String firstname, String lastname, String address, String email) {

    public ContactData() {
        this("", "", "", "");
    }

    public ContactData withFirstName(String firstname) {
        return new ContactData(firstname, this.lastname, this.address, this.email);
    }

    public ContactData withLastName(String lastname) {
        return new ContactData(this.firstname, lastname, this.address, this.email);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.firstname, this.lastname, address, this.email);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.firstname, this.lastname, this.address, email);
    }
}
