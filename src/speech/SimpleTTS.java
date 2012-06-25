package speech;
/**
 *
 * FreeTTS
 * requiere en el CLASSPATH: cmu_time_awb.jar, cmu_us_kal.jar, cmudict04.jar, cmulex.jar, cmutimelex.jar,
 * en_us.jar, freets.jar, jsapi.jar
 *
 * @author jose
 * @version 0.0.0.1
 * @since JDK 1.5 / Eclipse Callisto
 */

import com.sun.speech.freetts.Voice;

import com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory;




public class SimpleTTS
{
	Voice voice=null;
	public SimpleTTS() throws Exception
	{
		
		KevinVoiceDirectory kevin = new  KevinVoiceDirectory();
		Voice[] voices = kevin.getVoices();
		if(voices != null && voices.length>0){
		

		this.voice = voices[0];
		}
		if (this.voice == null)
		{
			throw new Exception("No se encuentra la voz.");
		}
		this.voice.allocate();
	}
	//----
	public void speak(String text) throws Exception
	{
		this.voice.speak(text);
	}
	
	public void close() throws Exception
	{
		this.voice.deallocate();
	}
}
//end of class SimpleTTS