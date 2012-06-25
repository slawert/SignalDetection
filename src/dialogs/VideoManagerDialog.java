package dialogs;

import manager.DetectionManager;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;


/**
 * Clase encargada de mostrar la ventana para la configuración de vídeo.
 * @author Robert
 *
 */
public class VideoManagerDialog  extends Dialog{

	/**
	 * Spinner para el manejo de los valores del brillo.
	 */
	private Spinner spBrightness;
	
	
	/**
	 * Spinner para el manejo de los valores del Umbral.
	 */
	private Spinner spThreshold;
	
	/**
	 * CheckBox para guardar el estado de la difuminación.
	 */
	private Button btnBlur;
	
	/**
	 * Checkbox para guardar el estado del escalado de grises.
	 */
	private Button btnGray;
	
	/**
	 * Detection manager.
	 */
	private DetectionManager manager;
	
	
	/**
	 * Constructor de la clase.
	 * @param parentShell
	 */
	public VideoManagerDialog(Shell parentShell) {
		super(parentShell);
		manager = DetectionManager.getInstance();
	}
	
	protected Control createDialogArea(Composite parent){
		Composite area = new Composite(parent, SWT.NONE);
		area.setLayout(new GridLayout(2,false));
		area.getShell().setText("Vídeo Manager");
		
		GridData data = new GridData();
		data.heightHint = 20;
		data.widthHint = 150;
		
		Label lblBrightness = new Label(area, SWT.NONE);
		lblBrightness.setText("Brightness");
		lblBrightness.setLayoutData(data);
		
		spBrightness = new Spinner(area, SWT.NONE);
		spBrightness.setValues(manager.getVideoManager().getBrightness(), -128, 128, 0, 1, 1);
		spBrightness.setLayoutData(data);
		
		Label lblThreshold = new Label(area, SWT.NONE);
		lblThreshold.setText("Threshold");
		lblThreshold.setLayoutData(data);
		
		spThreshold = new Spinner(area, SWT.NONE);
		spThreshold.setValues(manager.getVideoManager().getThreshold(), 0, 255, 0, 1, 1);
		spThreshold.setLayoutData(data);
		
		btnBlur = new Button(area, SWT.CHECK);
		btnBlur.setText("Blur");
		btnBlur.setLayoutData(data);
		btnBlur.setSelection(manager.getVideoManager().isBlur());
		
		btnGray = new Button(area, SWT.CHECK);
		btnGray.setText("Gray Escale");
		btnGray.setLayoutData(data);
		btnGray.setSelection(manager.getVideoManager().isConvertGray());
		
		return parent;
	}
	
	
	protected Control createButtonBar(final Composite parent){
		Composite buttonArea = new Composite(parent, SWT.NONE);
		buttonArea.setLayout(new GridLayout(2, false));
		GridData data = new GridData();
		data.heightHint = 24;
		data.widthHint = 80;
		
		Button btnOK =  createButton(buttonArea, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		btnOK.setLayoutData(data);
		Button btnCancel = createButton(buttonArea, IDialogConstants.CANCEL_ID,IDialogConstants.CANCEL_LABEL, false);
		btnCancel.setLayoutData(data);
		
		return parent;
	}
	
	@Override
	protected void okPressed(){
		if(spBrightness!=null){
			manager.getVideoManager().setBrightness(spBrightness.getSelection());
		}
		if(spThreshold !=null){
			manager.getVideoManager().setThreshold(spThreshold.getSelection());
		}
		
		if(btnBlur!=null){
			manager.getVideoManager().setBlur(btnBlur.getSelection());
		}
		
		if(btnGray !=null){
			manager.getVideoManager().setConvertGray(btnGray.getSelection());
		}
		
		manager.getVideoManager().saveSettings();
		super.okPressed();
	}
}
