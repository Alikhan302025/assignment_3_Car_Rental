package model;
import exception.*;

public abstract class BaseEntity implements Validatable{
    private int id;
    private String name;

    protected BaseEntity(int id, String name) {
        setId(id);
        setName(name);
    }

    public int getId(){
        return id;
    }
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id must be posotive");
        }
        this.id = id;
    }


    public String getName(){
        return name;
    }

    public void setName(String name){
        if(name == null){
            throw new IllegalArgumentException("Name must not be empty");
        }
        this.name = name;
    }




    public abstract String getEntityType();
    public abstract void validate();

    public String getDisplayName(){
        return name + " " + id;
    }


}

