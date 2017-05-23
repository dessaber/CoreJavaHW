package javase01.t02.InnerClasses;

import javase01.t02.Annotations.TopSecret;

import java.util.Objects;

/**
 * Created by m-levin on 08.05.2017.
 */

@TopSecret(name = "USSR's miliraty forces secret intel #548БНК14",
           classificationDate = "08/05/1967",
           secrecy = TopSecret.SecrecyLevel.HIGH)
public class NuclearSubmarine {

    public static void main (String[] args) {

        NuclearSubmarine s = new NuclearSubmarine("K-19", 200, 60_000);
        s.go();
        NuclearSubmarine.SubmarineEngine e = s.new SubmarineEngine("BM-A", 2000);
        System.out.println(e);
        s.setEngine(null);
        s.setEngine(e);
        s.go();

    }

    // It would've been better with a static class here, but since the task said "...inner class..."...
    class SubmarineEngine {

        private String name;
        private int power; // in horse power

        public SubmarineEngine() {
            this("Just an engine", 1500);
        }

        public SubmarineEngine(String name) {
            this(name, 1500);
        }

        public SubmarineEngine(String name, int power) {
            this.name = name;
            this.power = power;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPower() {
            return power;
        }

        public void setPower(int power) {
            this.power = power;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SubmarineEngine that = (SubmarineEngine) o;
            return getPower() == that.getPower() &&
                    Objects.equals(getName(), that.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getName(), getPower());
        }


    }

    private String name;
    private int length;
    private int displacement; // in tons
    private SubmarineEngine engine;

    public NuclearSubmarine() {
        this("Just a submarine", 180, 50_000);
    }

    public NuclearSubmarine(String name) {
        this(name, 180, 50_000);
    }

    public NuclearSubmarine(String name, int length) {
        this(name, length, 50_000);
    }

    public NuclearSubmarine(String name, int length, int displacement) {
        this.name = name;
        this.length = length;
        this.displacement = displacement;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public int getDisplacement() {
        return displacement;
    }

    public SubmarineEngine getEngine() {
        return engine;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setDisplacement(int displacement) {
        this.displacement = displacement;
    }


    public void setEngine(SubmarineEngine engine) {
        if (engine != null) {
            this.engine = engine;
        } else
            System.out.println("Still no engine!");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NuclearSubmarine submarine = (NuclearSubmarine) o;
        return getLength() == submarine.getLength() &&
                getDisplacement() == submarine.getDisplacement() &&
                Objects.equals(getName(), submarine.getName()) &&
                getEngine().equals(submarine.getEngine());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLength(), getDisplacement(), getEngine());
    }

    @Override
    public String toString() {
        return "Submarine{" +
                "name='" + name + '\'' +
                ", length=" + length +
                ", displacement=" + displacement +
                ", engine=" + engine +
                '}';
    }

    public void go() {
        if (this.engine != null)
            System.out.println("Going to the deeeepest depths! Wish us luck!");
        else
            System.out.println("We don't have an engine! Can't go!");
    }
}
