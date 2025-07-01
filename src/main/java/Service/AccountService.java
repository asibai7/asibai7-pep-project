package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    AccountDAO accountDAO;

    public AccountService() {
        this.accountDAO = new AccountDAO();
    }
    public Account register(Account account) {
        if (account.getUsername() == null || account.getUsername().trim().isEmpty()) {
            return null;
        }
        if (account.getPassword() == null || account.getPassword().length() < 4) {
            return null;
        }

        return accountDAO.addAccount(account);
    }
    public Account login(String username, String password) {
        return accountDAO.getAccountByUsernameAndPassword(username, password);
    }
}
