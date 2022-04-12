package com.spdp.master;

import io.helidon.microprofile.cdi.Main;

public class MasterMain {

	public static void main(String[] args) {

		System.out.println("inside application main");
		try {
			Main.main(args);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * s port = CDI.current() .getBeanManager()
		 * .getExtension(ServerCdiExtension.class) .port();
		 */
	}

}
