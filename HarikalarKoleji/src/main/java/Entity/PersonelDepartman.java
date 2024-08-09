package Entity;

public class PersonelDepartman {
    private Personel personel;
    private Departman departman;

    public PersonelDepartman() {
       
    }

    public PersonelDepartman(Personel personel, Departman departman) {
        this.personel = personel;
        this.departman = departman;
    }

    public Personel getPersonel() {
        return personel;
    }

    public void setPersonel(Personel personel) {
        this.personel = personel;
    }

    public Departman getDepartman() {
        return departman;
    }

    public void setDepartman(Departman departman) {
        this.departman = departman;
    }
}
