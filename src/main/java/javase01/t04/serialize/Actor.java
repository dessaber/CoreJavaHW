package javase01.t04.serialize;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

/**
 * Created by m-levin on 31.05.2017.
 */
public class Actor implements Serializable {
    private String lastName;
    private String firstName;

    Actor(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Objects.equals(lastName, actor.lastName) &&
                Objects.equals(firstName, actor.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName);
    }

    @Override
    public String toString() {
        return lastName + " " + firstName;
    }
}

class CompareActorsLastName implements Comparator<Actor>, Serializable {
    @Override
    public int compare(Actor o1, Actor o2) {
        return o1.getLastName().compareTo(o2.getLastName());
    }
}

class CompareActorsFirstName implements Comparator<Actor>, Serializable {
    @Override
    public int compare(Actor o1, Actor o2) {
        return o1.getFirstName().compareTo(o2.getFirstName());
    }
}




