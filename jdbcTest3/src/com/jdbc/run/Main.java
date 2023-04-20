package com.jdbc.run;

import com.jdbc.controller.ControllerImpl;

public class Main {

	public static void main(String[] args) {
		new ControllerImpl().mainMenu();
		System.out.println("내가 추가한 것");
	}
}