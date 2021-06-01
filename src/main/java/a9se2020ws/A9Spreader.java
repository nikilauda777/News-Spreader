package a9se2020ws;



import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * TODO: Complete the implementation. Do not change class name and existing method signatures. You may add base class and other interfaces. Make sure that the class works with the default constructor.
 */
public class A9Spreader implements NewsSpreader {

    private TreeSet<String> registeredSources; // TODO Replace this with your implementation
    private HashMap<String, String> storedEncryptedPasswords; // TODO Replace this with your implementation
    private ArrayList<Observer> users; // TODO Replace this with your implementation


    // Default constructor must be present, you may add implementation if needed
    public A9Spreader() {
        registeredSources = new TreeSet<>();
        storedEncryptedPasswords = new HashMap<>();
        users = new ArrayList<>();

    }

    /**
     * Registers a trusted news-source.
     *
     * @param source a string used to identify the source
     * @param pwd    a password that allows to authenticate the source when
     *               spreading news
     * @return false if source is null or already registered or if pwd is null or empty , true otherwise
     */
    public boolean registerTrustedSource(String source, String pwd) {

        // TODO Replace below with your implementation
        if (registeredSources.contains(source) || pwd == null || pwd.equals("") || pwd.trim().equals("") || pwd.isEmpty()) {
            return false;
        }

        registeredSources.add(source);

        try {
            storedEncryptedPasswords.put(source, encryptPassword(pwd));
            

        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {

            registeredSources.remove(source);
            noSuchAlgorithmException.printStackTrace();

            return false;
        }

        return true;
    }


    /**
     * @param news   a string that contains the news to be spread
     * @param source the source of the news (which must be already registered)
     * @param pwd    the password (must match the registered password for this source)
     * @throws UntrustedSourceException when the source was not registered before
     * @throws AuthenticationException  when the source was registered with a different password
     */
    public void spreadNews(String news, String source, String pwd) throws UntrustedSourceException, AuthenticationException {


        // TODO Replace below with your implementation
        if (!registeredSources.contains(source)) {
            throw new UntrustedSourceException(source);
        }

        try {

            if (!storedEncryptedPasswords.get(source).equals(encryptPassword(pwd))) {
                throw new AuthenticationException(source);
            }


        } catch (NoSuchAlgorithmException ex) {
            System.err.println(" Error when processing encryption algorithm! ");
            return;
        }


        //users durchgehen
        users.forEach(m -> {

            if (m.hasSubscription(source)) {
                m.notifyAboutSubscription(source, news);
            }


        });


    }

    public boolean addObserver(Observer observer) {

        if (users.contains(observer)) {
            System.err.println("Users already registered!");
            return false;
        }

        users.add(observer);
        return true;
    }

    private static String encryptPassword(String originalString) throws NoSuchAlgorithmException {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder();

        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }

}
