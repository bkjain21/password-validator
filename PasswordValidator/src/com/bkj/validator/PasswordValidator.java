package com.bkj.validator;

import com.bkj.validator.exception.PasswordValidatorException;

//Java code to validate a password

public class PasswordValidator {

	public boolean isPresent(String password) throws PasswordValidatorException {
		if (password == null || password.isEmpty())
			throw new PasswordValidatorException("Password should not be null or empty");
		return true;
	}

	public boolean validateLength(String password, int minLength) throws PasswordValidatorException {
		if (password.length() < minLength)
			throw new PasswordValidatorException("Password doesn't meet the minimum length criteria");
		return true;
	}

	public boolean hasLowerCase(String password) throws PasswordValidatorException {
		int count = 0;

		// checking small letters
		for (int i = 97; i <= 122; i++) {

			// type casting
			char c = (char) i;
			String resultantString = Character.toString(c);

			if (password.contains(resultantString)) {
				count = 1;
			}
		}
		if (count == 0) {
			throw new PasswordValidatorException("Password should have atleast one lower case letter");
		}
		return true;
	}

	public boolean hasUpperCase(String password) throws PasswordValidatorException {
		int count = 0;

		// checking capital letters
		for (int i = 65; i <= 90; i++) {

			// type casting
			char c = (char) i;

			String resultantString = Character.toString(c);
			if (password.contains(resultantString)) {
				count = 1;
			}
		}
		if (count == 0) {
			throw new PasswordValidatorException("Password should have atleast one upper case letter");
		}
		return true;
	}

	public boolean hasNumber(String password) throws PasswordValidatorException {
		int count = 0;

		// check digits from 0 to 9
		for (int i = 0; i <= 9; i++) {

			// to convert int to string
			String str1 = Integer.toString(i);

			if (password.contains(str1)) {
				count = 1;
			}
		}
		if (count == 0) {
			throw new PasswordValidatorException("Password should have atleast one number");
		}
		return true;
	}
}
