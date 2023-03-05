package app;

import java.util.regex.Pattern;

import message.MessageShow;

public class TestClass {

	public static void main(String[] args) {
		String patternName = "^([ㄱ-ㅎㅏ-ㅣ가-힣]+){2,}$";
		String txt = "나다";
		System.out.println(Pattern.matches(patternName, txt));

		if(!Pattern.matches(patternName, txt)) {
			System.out.println("Sadfasdf");
			return;
		}
	}

}
