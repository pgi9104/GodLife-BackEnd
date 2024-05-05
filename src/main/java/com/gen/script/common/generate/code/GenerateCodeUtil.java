package com.gen.script.common.generate.code;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 코드를 생성하는 유틸
 * @author rbdlf
 *
 */
public class GenerateCodeUtil {
	/**
	 * 1000~9999 사이의 임의의 수를 생성합니다.
	 * @return code
	 */
	public static String generateCode() {
		String code;
		
		try {
			//임의의 값을 생성하는 인스턴스 생성
			SecureRandom random = SecureRandom.getInstanceStrong();
			//0에서 8999의 사이 값을 생성하고 1000을 더해서 1000~9999사이의 값을 얻는다.
			int c = random.nextInt(9000) + 1000;
			code = String.valueOf(c);
		}catch(NoSuchAlgorithmException nsae) {
			throw new RuntimeException("Problem when generating the random code.");
		}
		
		return code;
	}
}
