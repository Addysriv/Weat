package com.weat.weat.common.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @author : anuj.kumar
 * Created At : {2019-09-26}
 */
public class RandomDataGenerator {

	private RandomDataGenerator() {

	}

	public static int getRandomNumber(int bound) {
		return RandomNumber.getRandomNumber(bound);
	}

	private static final class RandomNumber {
		private static Random rand;

		private static int getRandomNumber(int bound) {
			if (rand == null) {
				try {
					rand = SecureRandom.getInstanceStrong();
				} catch (NoSuchAlgorithmException e) {
					throw new IllegalStateException("Couldn't generate random data", e);
				}
			}
			return rand.nextInt(bound);
		}
	}
}