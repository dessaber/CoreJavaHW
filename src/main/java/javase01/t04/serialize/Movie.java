package javase01.t04.serialize;

import java.io.File;
import java.io.Serializable;
import java.util.*;

/**
 * Created by m-levin on 31.05.2017.
 */
public class Movie implements Serializable {

    private String title;
    private Set<Actor> actorTreeSet = new TreeSet<>(new CompareActorsLastName().thenComparing(new CompareActorsFirstName()));

    public Set<Actor> getActorTreeSet() {
        return actorTreeSet;
    }

    public void setActorTreeSet(TreeSet<Actor> actorTreeSet) {
        this.actorTreeSet = actorTreeSet;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Movie(String title) {
        this.title = title;
    }

    public Movie(String title, Set<Actor> actorSet) {
        this.title = title;
        this.actorTreeSet = actorTreeSet;
    }

    public void assignActor(Actor actor) {
        actorTreeSet.add(actor);
    }

    public void removeActor(Actor actor) {
        actorTreeSet.remove(actor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movies = (Movie) o;
        return Objects.equals(title, movies.title) &&
                Objects.equals(actorTreeSet, movies.actorTreeSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, actorTreeSet);
    }

    @Override
    public String toString() {
        return title;
    }
}

class CompareMoviesTitle implements Comparator<Movie>, Serializable {

    @Override
    public int compare(Movie o1, Movie o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}

class CompareMoviesActorSetSize implements Comparator<Movie>, Serializable {

    @Override
    public int compare(Movie o1, Movie o2) {
        return Integer.compare(o1.getActorTreeSet().size(), o2.getActorTreeSet().size());
    }
}


