import java.time.format.DateTimeFormatter;
import java.util.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;

public class Patient {
    private String firstName;
    private String lastName;
    private List<String> problemList;
    private LocalDate birthday;
    private int acuity;
    private int idNumber;
    private static int idCounter = 100000;
    final LocalDate currentDate = LocalDate.now();

    public Patient(){

    }
    public Patient(String name, LocalDate birthday, List<String> problemList, int idNumber, int acuityScore){
        this.birthday = birthday;
        this.problemList = new ArrayList<>();
        this.idNumber = idNumber;
        this.acuity = acuityScore;

    }
    public void setName(Scanner scnr){
        System.out.println("Enter Patient First Name:");
        this.firstName = scnr.next();
        System.out.println("Enter Patient Last Name:");
        this.lastName = scnr.next();
    }
    public void getName(){
        System.out.println("Patient Name: ");
        System.out.println(lastName + ", "+ firstName);
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }

    public static synchronized int createID(){
        return idCounter++;
    }

    public void setBirthday(Scanner sc) {
        boolean dateCorrect = false;

        //Converting String to Date

        // I made a loop so that if it fails, it asks again
        LocalDate ld = null;
        while(!dateCorrect){
            try{
                System.out.println("Enter your date of birth (dd/MM/yyyy): ");
                String dob = sc.next();

                if (dob.toLowerCase().startsWith("q")) break;

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                ld = LocalDate.parse(dob, formatter);
            } catch (Exception e){
                System.out.println("I'm sorry, I wasn't able to parse that, please try again. (Enter Q/q to quit");
                continue;
            }
            dateCorrect = true;
        }
        this.birthday = ld;

    }

    public void setBirthday(LocalDate ld){
        this.birthday = ld;
    }
    public void printBirthday(){
        System.out.println("DOB: " + birthday);
    }
    public LocalDate getBirthday(){
        return birthday;
    }
    public void printAge(){
        System.out.println("Age: " + Period.between(birthday, currentDate).getYears());
    }
    public int getAge(){
        return Period.between(birthday, currentDate).getYears();
    }

    public void setProblemList(Scanner scnr){
        this.problemList = new ArrayList<>();
        System.out.println("Input patient diagnosis: \nEnter 'exit' when finished");
        String input = scnr.next();
        while(!input.equals("exit")){
            problemList.add(input);
            input = scnr.next();
        }
    }
    public void setProblemList(List<String> newList){
        this.problemList = newList;
    }
    public List<String> getProblemList(){return problemList;}
    public void printProblemList(){
        System.out.println("Problem List: ");
        for (String s :
                problemList) {
            System.out.println(s);
        }
    }

    public void setIdNumber(){
        this.idNumber = createID();
    }
    public void setIdNumber(int newId){
        this.idNumber = newId;
    }
    public void printIdNumber(){
        System.out.println("Patient ID: " + idNumber);
    }
    public int getIdNumber(){return this.idNumber;}
    public int getAcuity(){
        return acuity;
    }
    public void setAcuity(int acuityScore){
        this.acuity = acuityScore;
    }
    public void printAcuity(){System.out.println("Patient Acuity: " + acuity);}
    public void printPatientInfo(){
        this.getName();
        this.printIdNumber();
        this.printBirthday();
        this.getAge();
        this.printProblemList();
    }
    public void newPatient(Scanner scnr){
        this.setName(scnr);
        this.setBirthday(scnr);
        this.setIdNumber();
        this.setProblemList(scnr);
    }


}
