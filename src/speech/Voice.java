package speech;



public class Voice {

	public static void speak(String text){
		SimpleTTS voice;
		try {
			voice = new SimpleTTS();
			voice.speak(text);
			voice.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
