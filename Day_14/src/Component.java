public class Component {
    String name;
    int amount;

    public Component(String name, int amount)
    {
        this.name = name;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return name + " " + amount;
    }
}
