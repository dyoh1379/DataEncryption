package kr.mrstudio;

import java.text.DecimalFormat;

public class EncryptionManager {

    // 문자열을 숫자로 변환하여 암호화
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder();

        for (char c : input.toCharArray()) {
            // 문자에 대해 ASCII 값을 구하고, 숫자로 변환
            int asciiValue = (int) c;
            encrypted.append(String.format("%03d", asciiValue)); // 3자리 숫자로 포맷
        }

        return encrypted.toString(); // 암호화된 숫자 반환
    }

    // 숫자를 다시 문자로 복호화
    public String decrypt(String encrypted) {
        StringBuilder decrypted = new StringBuilder();

        // 암호화된 숫자에서 3자리씩 분리하여 복호화
        for (int i = 0; i < encrypted.length(); i += 3) {
            // 3자리씩 자르기. (3자리 미만이면 앞에 0을 추가하여 자리수 맞춤)
            String numStr = encrypted.substring(i, Math.min(i + 3, encrypted.length()));

            // numStr이 3자리가 되도록 앞에 0을 채움
            while (numStr.length() < 3) {
                numStr = "0" + numStr;
            }

            // 숫자로 변환
            int asciiValue = Integer.parseInt(numStr);
            // ASCII 값을 문자로 변환
            decrypted.append((char) asciiValue);
        }

        return decrypted.toString(); // 복호화된 문자열 반환
    }

    // 무리함수로 데이터 암호화
    public String encryptIrrationalFunction(double x) {
        return String.valueOf(new DecimalFormat("#.####################").format(Math.sqrt(x-1) + 1));
    }

    // 무리함수로 암호화된 데이터 복호화
    public String decryptIrrationalFunction(double y) {
        System.out.println(new DecimalFormat("#.####################").format(Math.round((y-1)*(y-1) + 1)));

        return decrypt(new DecimalFormat("#.####################").format(Math.round(((y-1)*(y-1) + 1))));
    }
}
