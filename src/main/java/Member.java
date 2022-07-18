
public class Member {
    private String name;
    private Double core;

    public Member() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCore() {
        return core;
    }

    public void setCore(Double core) {
        this.core = core;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", core=" + core +
                '}';
    }
}
