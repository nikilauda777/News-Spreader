package a9se2020ws;

import java.util.Date;
import java.util.LinkedList;

public class Observer {

    private String name;
    private String email;
    private LinkedList<String> regSources;

    public Observer(String name, String email) {
        this.name = name;
        this.email = email;
        this.regSources = new LinkedList<>();
    }




    public void notifyAboutSubscription(String source, String news) {  //
        Date data = new Date();
        if (regSources.contains(source)) {
            System.out.println("User : " + name + " is notified about : " + news + " \n\t\t source : " + source + " time : " + data.toString());
        }


    }

    public boolean hasSubscription(String source) {  // check if it from right source
        return regSources.contains(source);
    }


    public void addSource(String newSource) {
        regSources.add(newSource);
    }

    public void delSource(String removeSource) {
        regSources.remove(removeSource);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LinkedList<String> getRegSources() {
        return regSources;
    }

    public void setRegSources(LinkedList<String> regSources) {
        this.regSources = regSources;
    }


}
