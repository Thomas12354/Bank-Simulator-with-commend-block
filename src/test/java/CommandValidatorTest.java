import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandValidatorTest {
	public static final String ID_FIRST_ACCOUNT = "99999999";
	public static final double APR = 3;
	public static final double CHECKING_AND_DEPOSIT_STARTING_BALANCE = 0;
	CommandValidator commandValidator;
	boolean actual;
	Bank bank;

	@BeforeEach
	public void setUp() {
		bank = new Bank();
		commandValidator = new CommandValidator(bank);
	}

	@Test
	public void create_an_account_with_valid_comment() {
		actual = commandValidator.validate("Create checking 12345678 0.6");
		assertTrue(actual);
	}

	@Test
	public void deposit_into_an_account_with_valid_comment() {
		bank.addSavingAccount(ID_FIRST_ACCOUNT, APR, CHECKING_AND_DEPOSIT_STARTING_BALANCE);
		actual = commandValidator.validate("Deposit 99999999 500");
		assertTrue(actual);
	}

	@Test
	public void completely_wrong_comment() {
		actual = commandValidator.validate("des sd 500");
		assertFalse(actual);
	}

}