package test.com.bkj.validator;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.bkj.validator.PasswordValidator;
import com.bkj.validator.strategy.PasswordVerificationStrategy;
import com.bkj.validator.strategy.VerificationStrategy;

public class PasswordValidatorTest {

	PasswordValidator validator;

	@Before
	public void init() {
		validator = new PasswordValidator();
	}

	@Test
	public void testIsPresentWithNullPassowrd() {
		String password = null;
		VerificationStrategy strategy = new PasswordVerificationStrategy.PasswordVerificationBuilder(validator,
				password, 8).build();
		boolean result = strategy.applyStrategy();
		assertFalse(result);
	}
	
	@Test
	public void testIsPresentWithEmptyPassowrd() {
		String password = "";
		VerificationStrategy strategy = new PasswordVerificationStrategy.PasswordVerificationBuilder(validator,
				password, 8).build();
		boolean result = strategy.applyStrategy();
		assertFalse(result);
	}
	
	@Test
	public void testValidateLengthWithNoLowerCaseLetter() {
		String password = "ABCS";
		VerificationStrategy strategy = new PasswordVerificationStrategy.PasswordVerificationBuilder(validator,
				password, 8).build();
		boolean result = strategy.applyStrategy();
		assertFalse(result);
	}
	
	@Test
	public void testValidateLengthWithNoUpperCaseLetter() {
		String password = "abcd";
		VerificationStrategy strategy = new PasswordVerificationStrategy.PasswordVerificationBuilder(validator,
				password, 8).build();
		boolean result = strategy.applyStrategy();
		assertFalse(result);
	}
	
	@Test
	public void testValidateLengthWithNoNumberPresent() {
		String password = "abcdef";
		VerificationStrategy strategy = new PasswordVerificationStrategy.PasswordVerificationBuilder(validator,
				password, 8).build();
		boolean result = strategy.applyStrategy();
		assertFalse(result);
	}
	
	@Test
	public void testValidateLengthWithUpperCaseLetter() {
		String password = "ABCasS";
		VerificationStrategy strategy = new PasswordVerificationStrategy.PasswordVerificationBuilder(validator,
				password, 8).build();
		boolean result = strategy.applyStrategy();
		assertTrue(result);
	}
	
	@Test
	public void testValidateLengthWithNumber() {
		String password = "abcd1";
		VerificationStrategy strategy = new PasswordVerificationStrategy.PasswordVerificationBuilder(validator,
				password, 8).build();
		boolean result = strategy.applyStrategy();
		assertTrue(result);
	}
	
	@Test
	public void testValidatePasswordWithMinThreeSuccess() {
		String password = "ABCDefgh";
		VerificationStrategy strategy = new PasswordVerificationStrategy.PasswordVerificationBuilder(validator,
				password, 8).build();
		boolean result = strategy.applyStrategy();
		assertTrue(result);
	}
	
	@Test
	public void testValidatePasswordWithAllSuccess() {
		String password = "ABCDefgh1";
		VerificationStrategy strategy = new PasswordVerificationStrategy.PasswordVerificationBuilder(validator,
				password, 8).build();
		boolean result = strategy.applyStrategy();
		assertTrue(result);
	}
}
