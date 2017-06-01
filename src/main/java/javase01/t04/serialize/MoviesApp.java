package javase01.t04.serialize;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by m-levin on 31.05.2017.
 */
public class MoviesApp implements Serializable {

    private static final long serialVersionUID = 19375283527258655L;
    private MovieCollection movieCollection = new MovieCollection();
    private String filename = "out/t04/serialize/MoviesApp.txt";
    private File directory = new File("out/t04/serialize");
    private File file = new File("out/t04/serialize/MoviesApp.txt");
    private ArrayList<MoviesAppAction> moviesAppActionList;

    public MovieCollection getMovieCollection() {
        return movieCollection;
    }

    public void setMovieCollection(MovieCollection movieCollection) {
        this.movieCollection = movieCollection;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public ArrayList<MoviesAppAction> getMoviesAppActionList() {
        return moviesAppActionList;
    }

    public void setMoviesAppActionList(ArrayList<MoviesAppAction> moviesAppActionList) {
        this.moviesAppActionList = moviesAppActionList;
    }

    public static void main(String[] args) {

        MoviesApp moviesApp = new MoviesApp();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Welcome to the MovieApp!");
            moviesApp.initializeActionList();
            int index;
            while (true) {
                moviesApp.showMenu();
                index = Integer.parseInt(reader.readLine());
                checkIndex(moviesApp.moviesAppActionList, index);
                moviesApp.moviesAppActionList.get(index).executeAction();
                System.out.println();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading movie collection.");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Input/output error.");
        }
    }

    class MovieCollection implements Serializable {

        private String collectionName;
        private Set<Actor> allActorsSet = new TreeSet<>(new CompareActorsLastName().thenComparing(new CompareActorsFirstName()));
        private Set<Movie> allMoviesSet = new TreeSet<>(new CompareMoviesTitle().thenComparing(new CompareMoviesActorSetSize()));

        public Set<Actor> getAllActorsSet() {
            return allActorsSet;
        }

        public void setAllActorsSet(Set<Actor> allActorsSet) {
            this.allActorsSet = allActorsSet;
        }

        public Set<Movie> getAllMoviesSet() {
            return allMoviesSet;
        }

        public void setAllMoviesSet(Set<Movie> allMoviesSet) {
            this.allMoviesSet = allMoviesSet;
        }

        public String getCollectionName() {
            return collectionName;
        }

        public void setCollectionName(String collectionName) {
            this.collectionName = collectionName;
        }
    }

    interface MoviesAppAction extends Serializable {
        void executeAction() throws IOException, ClassNotFoundException;
    }

    class AddActor implements MoviesAppAction {

        @Override
        public void executeAction() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Input actor's last name:");
            String lastName = reader.readLine();
            System.out.println("Input actor's first name:");
            String firstName = reader.readLine();
            movieCollection.allActorsSet.add(new Actor(lastName, firstName));
        }
    }

    class RemoveActor implements MoviesAppAction {

        @Override
        public void executeAction() throws IOException {
            if (movieCollection.allActorsSet.size() == 0)
                System.out.println("No actors in this collection!");
            else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                ArrayList<Actor> actorArrayList = new ArrayList<>(movieCollection.allActorsSet);
                System.out.println("Which actor would you like to remove?");
                for (int i = 0; i < actorArrayList.size(); i++) {
                    System.out.println(i + ". " + actorArrayList.get(i));
                }
                int index = Integer.parseInt(reader.readLine());
                checkIndex(actorArrayList, index);
                Actor actorToRemove = actorArrayList.get(index);
                for (Movie movie : movieCollection.allMoviesSet)
                    movie.removeActor(actorToRemove);
                movieCollection.allActorsSet.remove(actorArrayList.get(index));
            }
        }
    }

    class AddMovie implements MoviesAppAction {

        @Override
        public void executeAction() throws IOException {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Input movie's title:");
            movieCollection.allMoviesSet.add(new Movie(reader.readLine()));
        }
    }

    class RemoveMovie implements MoviesAppAction {

        @Override
        public void executeAction() throws IOException {
            if (movieCollection.allMoviesSet.size() == 0)
                System.out.println("No movies in this collection!");
            else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                ArrayList<Movie> movieArrayList = new ArrayList<>(movieCollection.allMoviesSet);
                System.out.println("Which movie would you like to remove?");
                for (int i = 0; i < movieArrayList.size(); i++) {
                    System.out.println(i + ". " + movieArrayList.get(i));
                }
                System.out.println();
                int index = Integer.parseInt(reader.readLine());
                checkIndex(movieArrayList, index);
                movieCollection.allMoviesSet.remove(movieArrayList.get(index));
            }
        }
    }

    class AssignActor implements MoviesAppAction {

        @Override
        public void executeAction() throws IOException {
            if (movieCollection.allMoviesSet.size() == 0)
                System.out.println("No movies in this collection!");
            else if (movieCollection.allActorsSet.size() == 0)
                System.out.println("No actors in this collection!");
            else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                ArrayList<Actor> actorArrayList = new ArrayList<>(movieCollection.allActorsSet);
                ArrayList<Movie> movieArrayList = new ArrayList<>(movieCollection.allMoviesSet);
                System.out.println("Which movie would you like to assign an actor to?");
                for (int i = 0; i < movieArrayList.size(); i++) {
                    System.out.println(i + ". " + movieArrayList.get(i));
                }
                System.out.println();
                int movieIndex = Integer.parseInt(reader.readLine());
                checkIndex(movieArrayList, movieIndex);
                System.out.println("Which actor would you like to choose?");
                for (int i = 0; i < actorArrayList.size(); i++) {
                    System.out.println(i + ". " + actorArrayList.get(i));
                }
                System.out.println();
                int actorIndex = Integer.parseInt(reader.readLine());
                checkIndex(actorArrayList, actorIndex);
                movieArrayList.get(movieIndex).assignActor(actorArrayList.get(actorIndex));
            }
        }
    }

    class UnassignActor implements MoviesAppAction {

        @Override
        public void executeAction() throws IOException {
            if (movieCollection.allMoviesSet.size() == 0)
                System.out.println("No movies in this collection!");
            else if (movieCollection.allActorsSet.size() == 0)
                System.out.println("No actors in this collection!");
            else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                ArrayList<Actor> actorArrayList = new ArrayList<>(movieCollection.allActorsSet);
                ArrayList<Movie> movieArrayList = new ArrayList<>(movieCollection.allMoviesSet);
                System.out.println("Which movie would you like to unassign an actor from?");
                for (int i = 0; i < movieArrayList.size(); i++) {
                    System.out.println(i + ". " + movieArrayList.get(i));
                }
                System.out.println();
                int movieIndex = Integer.parseInt(reader.readLine());
                checkIndex(movieArrayList, movieIndex);
                System.out.println("Which actor would you like to remove?");
                for (int i = 0; i < actorArrayList.size(); i++) {
                    System.out.println(i + ". " + actorArrayList.get(i));
                }
                System.out.println();
                int actorIndex = Integer.parseInt(reader.readLine());
                checkIndex(actorArrayList, actorIndex);
                movieArrayList.get(movieIndex).removeActor(actorArrayList.get(actorIndex));
            }
        }
    }

    class ShowAllActors implements MoviesAppAction {

        @Override
        public void executeAction() {
            if (movieCollection.allActorsSet.size() == 0)
                System.out.println("No actors in this collection!");
            else {
                System.out.println("Here's the list of all actors in the movie collection");
                for (Actor actor : movieCollection.allActorsSet)
                    System.out.println(actor);
            }
        }
    }

    class ShowAllMovies implements MoviesAppAction {

        @Override
        public void executeAction()  {
            if (movieCollection.allMoviesSet.size() == 0)
                System.out.println("No movies in this collection!");
            else {
                System.out.println("Here's the list of all movies in the movie collection");
                for (Movie movie : movieCollection.allMoviesSet)
                    System.out.println(movie);
            }
        }
    }

    class ShowActorsFromMovie implements MoviesAppAction {

        @Override
        public void executeAction() throws IOException {
            if (movieCollection.allMoviesSet.size() == 0)
                System.out.println("No movies in this collection!");
            else  if (movieCollection.allMoviesSet.size() == 0)
                System.out.println("No actors in this collection!");
            else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                ArrayList<Movie> movieArrayList = new ArrayList<>(movieCollection.allMoviesSet);
                System.out.println("Which movie starring list would you like to see?");
                for (int i = 0; i < movieArrayList.size(); i++) {
                    System.out.println(i + ". " + movieArrayList.get(i));
                }
                System.out.println();
                int movieIndex = Integer.parseInt(reader.readLine());
                checkIndex(movieArrayList, movieIndex);
                if (movieArrayList.get(movieIndex).getActorTreeSet().size() == 0)
                    System.out.println("No actors in this movie!");
                else
                    for (Actor actor : movieArrayList.get(movieIndex).getActorTreeSet())
                        System.out.println(actor);
            }
        }
    }

    public static void checkIndex(Collection collection, int index) {
        if (index < 0 || index > collection.size())
            throw new NoSuchElementException("No element corresponding to this index.");
    }

    class SaveMovieCollection implements MoviesAppAction {

        @Override
        public void executeAction() throws IOException {
            if (!MoviesApp.this.directory.exists())
                MoviesApp.this.directory.mkdirs();
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(movieCollection);
            System.out.println("Current MoviesApp collection is saved.");
            objectOutputStream.close();
            fileOutputStream.close();
        }
    }

    class LoadMovieCollection implements MoviesAppAction {

        @Override
        public void executeAction() throws IOException, ClassNotFoundException {
            File file = new File(filename);
            if (file.exists() && file.canRead()) {
                FileInputStream fileInputStream = new FileInputStream(filename);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                movieCollection = (MovieCollection) objectInputStream.readObject();
                System.out.println("Last MoviesApp collection is loaded.");
                objectInputStream.close();
                fileInputStream.close();
            } else {
                System.out.println("No configuration file found!");
            }
        }
    }

    class CloseApp implements MoviesAppAction {
        @Override
        public void executeAction() throws IOException, ClassNotFoundException {
            System.out.println("See ya!");
            System.exit(0);
        }
    }

    public void initializeActionList() {
        moviesAppActionList = new ArrayList<>();
        moviesAppActionList.add(0, new AddMovie());
        moviesAppActionList.add(1, new RemoveMovie());
        moviesAppActionList.add(2, new AddActor());
        moviesAppActionList.add(3, new RemoveActor());
        moviesAppActionList.add(4, new AssignActor());
        moviesAppActionList.add(5, new UnassignActor());
        moviesAppActionList.add(6, new ShowAllMovies());
        moviesAppActionList.add(7, new ShowAllActors());
        moviesAppActionList.add(8, new ShowActorsFromMovie());
        moviesAppActionList.add(9, new SaveMovieCollection());
        moviesAppActionList.add(10, new LoadMovieCollection());
        moviesAppActionList.add(11, new CloseApp());
    }

    public void showMenu() {
        System.out.println("0 - add a movie to the movie collection;");
        System.out.println("1 - remove a movie from the movie collection;");
        System.out.println("2 - add an actor to the movie collection;");
        System.out.println("3 - remove an actor from the movie collection;");
        System.out.println("4 - assign an actor to a movie;");
        System.out.println("5 - unassign an actor from a movie;");
        System.out.println("6 - show all movies;");
        System.out.println("7 - show all actors;");
        System.out.println("8 - show all actors from a movie;");
        System.out.println("9 - save the movie collection;");
        System.out.println("10 - load the movie collection;");
        System.out.println("11 - close the app.");
        System.out.println();
    }
}


