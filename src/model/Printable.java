package model;

public interface Printable {
    String toPrint();

    default void print(){
        System.out.println(toPrint());
    }

    static String separator(){
        return "-------------";
    }
}
