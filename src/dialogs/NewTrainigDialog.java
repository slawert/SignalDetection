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
import org.eclipse.swt.widgets.Text;

public class NewTrainigDialog extends Dialog{
	
	
	private Text txtName;
	
	private Text txtFile;
	
	private Text txtNotice;
	
	private Button btnEnabled;

	public NewTrainigDialog(Shell parent) {
		super(parent);
	}

	protected Control createDialogArea(Composite parent){
		Composite area = new Composite(parent, SWT.NONE);
		area.setLayout(new GridLayout(2,false));
		area.getShell().setText("Training");
		
		GridData data = new GridData();
		data.heightHint = 20;
		data.widthHint = 150;
		
		Label lblName = new Label(area, SWT.NONE);
		lblName.setText("Name: ");
		lblName.setLayoutData(data);
		
		txtName = new Text(area, SWT.NONE);
		txtName.setLayoutData(data);
		
		Label lblFile = new Label(area, SWT.NONE);
		lblFile.setText("File name: ");
		lblFile.setLayoutData(data);
		
		txtFile = new Text(area, SWT.NONE);
		txtFile.setLayoutData(data);
		
		Label lblNotice = new Label(area, SWT.NONE);
		lblNotice.setText("Notice text:");
		lblNotice.setLayoutData(data);
		
		txtNotice = new Text(area, SWT.NONE);
		txtNotice.setLayoutData(data);
		
		btnEnabled = new Button(area, SWT.CHECK);
		btnEnabled.setText("Enabled");
		btnEnabled.setLayoutData(data);
		
		
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
		if((txtFile!=null && txtFile.getText().length()>0)&&
				(txtName!=null && txtName.getText().length()>0)&&
				(txtNotice!=null && txtNotice.getText().length()>0)){
			DetectionManager manager = DetectionManager.getInstance();
			manager.addTraining(txtName.getText(), txtFile.getText(), txtNotice.getText(), btnEnabled.getSelection());
			super.okPressed();
		}else{
			MessageBox message = new MessageBox(getShell(), SWT.ICON_WARNING);
			message.setText("The Fields can't be empty.");
			message.setMessage("The Fields can't be empty.");
		}
	}
	
}
