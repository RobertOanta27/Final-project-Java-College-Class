package Domain;

public class WinterGame implements Identifiable<Integer>, Comparable<WinterGame> {
    private int Id;
    private String name;
    private String type;
    private int minAge;
    private int maxAge;
    private String date;

    public WinterGame(String name, String type, int minAge, int maxAge, String date) {
        this.name = name;
        this.type = type;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.date = date;
    }

    public WinterGame(int Id, String name, String type, int minAge, int maxAge, String date) {
        this.Id = Id;
        this.name = name;
        this.type = type;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.date = date;
    }

    public WinterGame() {
        this.Id = 0;
        this.name = "";
        this.type = "";
        this.minAge = 0;
        this.maxAge = 0;
        this.date = "";
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String newType) {
        this.type = newType;
    }

    public int getMinAge() {
        return this.minAge;
    }

    public void setMinAge(int newMinAge) {
        this.minAge = newMinAge;
    }

    public int getMaxAge() {
        return this.maxAge;
    }

    public void setMaxAge(int newMaxAge) {
        this.maxAge = newMaxAge;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String newDate) {
        this.date = newDate;
    }

    public Integer getId() {
        return this.Id;
    }

    public void setId(Integer newId) {
        this.Id = newId;
    }

    @Override
    public String toString() {
        return
                "ID=" + Id +
                        ", Name='" + name + '\'' +
                        ", Type='" + type + '\'' +
                        ", Required minimum age='" + minAge + '\'' +
                        ", Required maximum age='" + maxAge + '\'' +
                        ", Date='" + date + '\'';

    }

    public boolean equals(Object otherWinter) {
        if (otherWinter instanceof WinterGame) {
            WinterGame winterGame = (WinterGame) otherWinter;
            if (winterGame.name.equals(name) && winterGame.type.equals(type) && winterGame.minAge == minAge && winterGame.maxAge == maxAge && winterGame.date == date) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(WinterGame winterGame) {
        return 0;
    }
}
