package Domain;

public class Enrollment implements Identifiable<Integer>,Comparable<Enrollment> {
    private int Id;
    private String nameChild;
    private int age;
    private String nameParent;
    private WinterGame winterGame;

    public Enrollment(int Id, String nameChild, int age, String nameParent, WinterGame winterGame) {
        this.Id = Id;
        this.nameChild = nameChild;
        this.age = age;
        this.nameParent = nameParent;
        this.winterGame = winterGame;
    }

    public Enrollment(String nameChild, int age, String nameParent, WinterGame winterGame) {
        this.nameChild = nameChild;
        this.age = age;
        this.nameParent = nameParent;
        this.winterGame = winterGame;
    }

    public Enrollment() {
        this.Id = 0;
        this.nameChild = "";
        this.age = 0;
        this.nameParent = "";
        this.winterGame = new WinterGame();
    }

    public String getNameChild() {
        return nameChild;
    }

    public void setNameChild(String newName) {
        this.nameChild = newName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int newAge) {
        this.age = newAge;
    }

    public String getNameParent() {
        return nameParent;
    }

    public void setNameParent(String newName) {
        this.nameParent = newName;
    }

    public WinterGame getWinterGame() {
        return winterGame;
    }

    public void setWinterGame(WinterGame newWinterGame) {
        this.winterGame = newWinterGame;
    }


    @Override
    public String toString() {
        return
                "ID=" + Id +
                        ", Name of the child='" + nameChild + '\'' +
                        ", Age='" + age + '\'' +
                        ", Parent's name='" + nameParent + '\'' +
                        ", Winter game ='" + winterGame + '\'';

    }

    public boolean equals(Object otherEnroll) {
        if (otherEnroll instanceof Enrollment) {
            Enrollment enroll = (Enrollment) otherEnroll;
            if (enroll.nameChild.equals(nameChild) && enroll.age == age && enroll.nameParent.equals(nameParent) && enroll.winterGame.equals(winterGame)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Enrollment o) {
        return 0;
    }

    @Override
    public Integer getId() {
        return this.Id;
    }

    @Override
    public void setId(Integer newId) {
        this.Id = newId;
    }
}
