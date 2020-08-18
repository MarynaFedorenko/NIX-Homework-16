package nix.data;

import nix.annotation.Value;

public class TableData {

    @Value("name")
    public String name;

    @Value("age")
    private int age;

    @Value("gender")
    public String gender;

    @Value("occupation")
    private String occupation;

    public TableData(){}

    public TableData(String name, int age, String gender, String occupation) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.occupation = occupation;
    }

    public void print() {
        System.out.println( "Name: " + name + ";   "
                + "Age: " + age + ";   "
                + "Gender: " + gender + ";   "
                + "Occupation: " + occupation);
    }

    public int getAge(){
        return this.age;
    }

    public String getOccupation(){
        return this.occupation;
    }

}
