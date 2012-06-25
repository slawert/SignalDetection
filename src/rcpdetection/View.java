package rcpdetection;

import java.awt.Frame;

import manager.DetectionManager;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import processing.core.PApplet;
import sketch.MyProcessingSketch;

public class View extends ViewPart {
	public static final String ID = "rcpdetection.view";


	private TableViewer viewer;
	
	private DetectionManager manager;
	
	private static Text txtNotice;
	
	private Label lblState;
	
	private Button btnStop;
	
	private static Display display;
	
	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl( Composite parent) {
//		TableViewer viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
//				| SWT.V_SCROLL);
		display = parent.getDisplay();
		
		manager = DetectionManager.getInstance();
		parent.getShell().setText("Object Detection");
		final Display display = parent.getDisplay();
		
		Composite composite = new Composite(parent, SWT.NONE);
        GridLayout grid = new GridLayout(1, false);
		composite.setLayout(grid);
		
		Composite videoComposite = new Composite(composite, SWT.NO_BACKGROUND | SWT.EMBEDDED);
		GridData rightData = new GridData();
		rightData.widthHint = 640;
		rightData.heightHint = 480;
		videoComposite.setLayoutData(rightData);
		Frame frame = SWT_AWT.new_Frame(videoComposite);
        PApplet pApplet = new MyProcessingSketch();
        pApplet.init();
        frame.add(pApplet);
		
        Composite bottom = new Composite(composite, SWT.NONE);
		bottom.setLayout(new GridLayout(3, true));
		GridData bottomData = new GridData();
		bottomData.widthHint=640;
		bottomData.heightHint = 50;
		bottom.setLayoutData(bottomData);
		
		
		Label lblCar = new Label(bottom, SWT.NONE);
		lblCar.setText("El coche está:");
		
		lblState = new Label(bottom, SWT.NONE);
		lblState.setText("EN MOVIMIENTO");
		lblState.setImage(Activator.getImageDescriptor("/icons/Green.png").createImage());
		GridData labelData = new GridData();
		labelData.widthHint = 150;
		labelData.heightHint = 60;
		lblState.setLayoutData(labelData);
		
		btnStop = new Button(bottom, SWT.NONE);
		btnStop.setText("Parar coche");
		GridData btnStateData = new GridData();
		btnStateData.widthHint = 90;
		btnStateData.heightHint= 22;
		btnStop.setLayoutData(btnStateData);
		btnStop.addListener(SWT.Selection, new Listener(){

			@Override
			public void handleEvent(Event arg0) {
			display.syncExec(new Runnable() {
				
				@Override
				public void run() {
					boolean state = manager.getIsStopped();
					if(state){
						//Estaba parado, hay que arrancarlo
						manager.setIsStopped(false);
						lblState.setText("EN MOVIMIENTO");
						lblState.setImage(Activator.getImageDescriptor("/icons/Green.png").createImage());
						btnStop.setText("Parar coche");
					}else{
						//Estaba arrancado, hay que pararlo
						manager.setIsStopped(true);
						lblState.setText("PARADO");
						lblState.setImage(Activator.getImageDescriptor("/icons/Red.png").createImage());
						btnStop.setText("Arrancar coche");
					}
				}
			});
				
				
			}
			
		});
		
		
		
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
	}
	
	
	public static void newNotice(final String  notice){
		display.syncExec(new Runnable() {
			
			@Override
			public void run() {
				if(txtNotice!=null){
				String old = txtNotice.getText();
				if(old == null){
					old = notice;
				}else{
					old = notice +"\n"+old;
				}
				txtNotice.setText(old);
				}
				
			}
		});
		
	}
	
}