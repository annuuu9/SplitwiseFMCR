package model;

//Defines a user (payer or splitter) with ID and name.
//Used across all components for identity tracking.
//Encapsulation is applied: fields are private with public getters.

public class User {
    private String id;
    private String name;

    public User(String id,String name){
        this.id=id;
        this.name=name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return name;
    }
}
