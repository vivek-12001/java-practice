package user;

import atm.Card;
import lombok.Data;

@Data
public class User {

    Card card;
    UserBankAccount userBankAccount;
}
