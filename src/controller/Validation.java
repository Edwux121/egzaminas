package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	private static final String VALID_ID_REGEX ="^[0-9]{1,7}$";
	private static final String VALID_CREDENTIALS_ADD_REGEX ="^[A-Za-z ]{1,30}$";
	private static final String VALID_EMAIL_ADDRESS_REGEX = "^^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
	private static final String VALID_ATSILIEPIMAS_REGEX = "^[A-Za-z0-9., ]{5,150}$";
	
	public static boolean isValidID(String ID){
		Pattern IDPattern = Pattern.compile(VALID_ID_REGEX);
		Matcher IDMatcher = IDPattern.matcher(ID);
		return IDMatcher.find();
	}
	
	public static boolean isValidCredentialsForAdd(String text){
		Pattern CredentialsNamePattern = Pattern.compile(VALID_CREDENTIALS_ADD_REGEX);
		Matcher CredentialsNameMatcher = CredentialsNamePattern.matcher(text);
		return CredentialsNameMatcher.find();
	}
	
	public static boolean isValidEmail(String email){
		Pattern emailPattern = Pattern.compile(VALID_EMAIL_ADDRESS_REGEX);
		Matcher emailMatcher = emailPattern.matcher(email);
		return emailMatcher.find();
	}
	
	public static boolean isValidAtsiliepimas(String Atsiliepimas){
		Pattern AtsiliepiasPattern = Pattern.compile(VALID_ATSILIEPIMAS_REGEX);
		Matcher AtsiliepimasMatcher = AtsiliepiasPattern.matcher(Atsiliepimas);
		return AtsiliepimasMatcher.find();
	}
}
