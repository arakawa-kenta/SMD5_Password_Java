
public class SMD5Test {
	public static void main(String[] args) throws Exception {
		
		byte[] password = "password".getBytes();
		
		System.out.print("�ϊ��O");
		System.out.println(byte2Hex(password));
		System.out.println("�ԊҌ�(1���)");
		byte[] encryptedPassword1 = SMD5.encryptPassword(password,null);
		System.out.println(byte2Hex(encryptedPassword1));
		System.out.println("�ԊҌ�(2���)");
		byte[] encryptedPassword2 = SMD5.encryptPassword(password,null);
		System.out.println(byte2Hex(encryptedPassword2));
		
		//��r
		System.out.print("�������p�X���[�h�Ƃ̔�r:");
		System.out.println(SMD5.comparePassword(password, encryptedPassword1));
		System.out.print("�Ԉ�����p�X���[�h�Ƃ̔�r:");
		byte[] wrongpass = "wrongpass".getBytes();
		System.out.println(SMD5.comparePassword(wrongpass, encryptedPassword1));
	}
	
	//�X�g���b�`���O����
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