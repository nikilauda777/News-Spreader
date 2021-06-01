package a9se2020ws;





public class Main {


    public static void main(String[] args) throws AuthenticationException, UntrustedSourceException {

        A9Spreader a9Spreader = new A9Spreader();

        a9Spreader.registerTrustedSource("news.ru", "12345");
        a9Spreader.registerTrustedSource("politics.ru", "654321");
        a9Spreader.registerTrustedSource("VldaimirPutinNews.gov.ru", "Trump2020");


        Observer observer1 = new Observer("Vasiliy Pupkin", "pohiy@mail.ru");
        observer1.addSource("news.ru");

        Observer observer2 = new Observer("Petya Pupkin", "pohiy@mail.ru");
        observer2.addSource("news.ru");
        observer2.addSource("politics.ru");

        Observer observer3 = new Observer("Natasha Pupkina", "pohiy@mail.ru");
        observer3.addSource("news.ru");
        observer3.addSource("politics.ru");



        a9Spreader.addObserver(observer1);
        a9Spreader.addObserver(observer2);
        a9Spreader.addObserver(observer3);


        a9Spreader.spreadNews("LOCKDOWN 6.0", "news.ru", "12345");
        a9Spreader.spreadNews("TRUMP WON 2020 ELECTIONS", "politics.ru", "654321");


    }


}
