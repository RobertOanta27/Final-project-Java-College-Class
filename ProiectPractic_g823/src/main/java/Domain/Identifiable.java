package Domain;

public interface Identifiable<Id> {
    public Id getId();
    public void setId(Id id);
}