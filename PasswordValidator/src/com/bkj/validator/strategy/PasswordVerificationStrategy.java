package com.bkj.validator.strategy;

import com.bkj.validator.PasswordValidator;

import com.bkj.validator.exception.PasswordValidatorException;

public class PasswordVerificationStrategy implements VerificationStrategy {

	private PasswordValidator validator;

	private int passwordMinLength;

	private String password;

	public PasswordVerificationStrategy(PasswordVerificationBuilder builder) {
		this.validator = builder.validator;
		this.password = builder.password;
		this.passwordMinLength = builder.passwordMinLength;
	}

	// Builder Class
	public static class PasswordVerificationBuilder {

		private PasswordValidator validator;

		private int passwordMinLength;

		private String password;

		public PasswordVerificationBuilder(PasswordValidator validator, String password, int passwordMinLength) {
			this.validator = validator;
			this.password = password;
			this.passwordMinLength = passwordMinLength;
		}

		public PasswordVerificationStrategy build() {
			return new PasswordVerificationStrategy(this);
		}

	}

	@Override
	public boolean applyStrategy() {
		int successCount = 0;
		try {
			validator.isPresent(password);
			successCount++;
		} catch (PasswordValidatorException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
		try {
			validator.hasLowerCase(password);
			successCount++;
		} catch (PasswordValidatorException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
		try {
			validator.validateLength(password, passwordMinLength);
			successCount++;
		} catch (PasswordValidatorException ex) {
			System.out.println(ex.getMessage());
		}
		if (successCount < 3) {
			try {
				validator.hasUpperCase(password);
				successCount++;
			} catch (PasswordValidatorException ex) {
				System.out.println(ex.getMessage());
			}
		}
		if (successCount < 3) {
			try {
				validator.hasNumber(password);
				successCount++;
			} catch (PasswordValidatorException ex) {
				System.out.println(ex.getMessage());
			}
		}
		if (successCount >= 3) {
			return true;
		} else {
			return false;
		}
	}
}
