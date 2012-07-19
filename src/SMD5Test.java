
public class SMD5Test {
	public static void main(String[] args) throws Exception {
		
		byte[] password = "password".getBytes();
		
		System.out.print("変換前");
		System.out.println(byte2Hex(password));
		System.out.println("返還後(1回目)");
		byte[] encryptedPassword1 = SMD5.encryptPassword(password,null);
		System.out.println(byte2Hex(encryptedPassword1));
		System.out.println("返還後(2回目)");
		byte[] encryptedPassword2 = SMD5.encryptPassword(password,null);
		System.out.println(byte2Hex(encryptedPassword2));
		
		//比較
		System.out.print("正しいパスワードとの比較:");
		System.out.println(SMD5.comparePassword(password, encryptedPassword1));
		System.out.print("間違ったパスワードとの比較:");
		byte[] wrongpass = "wrongpass".getBytes();
		System.out.println(SMD5.comparePassword(wrongpass, encryptedPassword1));
	}
	
	//ストレッチング処理
	private static String byte2Hex(byte[] bytes) {
		StringBuilder hex = new StringBuilder();
		for (int i = 0;i < bytes.length; i++){
			if( (0xFF & bytes[i]) < 16){
				hex.append("0");
			}
			hex.append(Integer.toHexString(0xFF & bytes[i]));
		}
		return hex.toString();
	}
	
}