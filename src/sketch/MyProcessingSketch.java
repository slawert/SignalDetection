package sketch;



import hypermedia.video.OpenCV;

import java.awt.Rectangle;
import java.util.ArrayList;

import manager.DetectionManager;
import processing.core.PApplet;
import rcpdetection.View;
import speech.Voice;
import training.Training;

public class MyProcessingSketch extends PApplet {


	// Forma de arrancar PApplet.main(new String[] { "--present", "MyProcessingSketch" });


	private OpenCV myCapture;

	
	private DetectionManager manager;
	
	public void setup() 
	{	      
		size( 640, 480 );//  Window size of 640 x 480
		myCapture = new OpenCV( this );            //  Initialises the OpenCV library
		myCapture.capture( 640	, 480 );         
		//		myCapture.cascade(OpenCV.CASCADE_FULLBODY);
//		myCapture.cascade( "data.xml" );
		manager = DetectionManager.getInstance();

	}

	void captureEvent(OpenCV myCapture) {
		myCapture.read();

	}

	
	public void draw() {
		myCapture.read();
		image( myCapture.image(), 0, 0 );
		
		videoparams();		
		if(!manager.getIsStopped()){
		
		
		ArrayList<Training> trainings = manager.getTrainings();
		if(trainings !=null && trainings.size()>0){
			for(int i=0;i< trainings.size();i++){
				if(trainings.get(i).getState()){
					myCapture.cascade(trainings.get(i).getFileName());
					Rectangle[] objects = myCapture.detect();
					noFill();
					stroke(255,0,0);
					
					for( int j=0; j<objects.length; j++ ) {
						rect( objects[j].x, objects[j].y, objects[j].width, objects[j].height ); 
					}
					
					if(objects.length>0){
						Voice.speak(trainings.get(i).getNotice());
					}
					myCapture.restore();
				}
			}
			
		}
		}
	}

	/**
	 * Establece los parámetros de vídeo.
	 */
	private void videoparams() {
		
		myCapture.brightness(manager.getVideoManager().getBrightness());
		
		myCapture.threshold(manager.getVideoManager().getThreshold());
		
		if(manager.getVideoManager().isConvertGray()){
			myCapture.convert(OpenCV.GRAY);                
		}
		
		if(manager.getVideoManager().isBlur()){
			myCapture.blur(OpenCV.BLUR, 3);
		}
		
	}
}