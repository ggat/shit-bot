package drivers;

/**
 * Created by giga on 9/13/17.
 */
public interface BookieDriver {

    Long getBalance() throws Throwable;
    boolean isLoggedIn() throws Throwable;
    void createBet(String category, String subCategory, String teamOneName, String teamTwoName, Double amount, Double oddConfirmation);
}
