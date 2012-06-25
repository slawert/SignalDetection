package dialogs;

import java.util.ArrayList;

import manager.DetectionManager;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import training.Training;

public class TrainingManagerDialog extends Dialog {

	private Table table;
	
	private DetectionManager manager;

	public TrainingManagerDialog(Shell parentShell) {
		super(parentShell);
		manager = DetectionManager.getInstance();
		// TODO Auto-generated constructor stub
	}

	protected Control createDialogArea(Composite parent){
		//Composite total
		Composite area = new Composite(parent, SWT.NONE);
		area.setLayout(new GridLayout(2,false));
		area.getShell().setText("Gestión de Entrenamientos");
//		area.getShell().setSize(400, 300);
//		area.setSize(395, 295);
		GridData areaData = new GridData();
		areaData.widthHint=400;
		areaData.heightHint=300;
		area.setLayoutData(areaData);
		
		//Composite para la tabla
		
		Composite leftComposite = new Composite(area, SWT.None);
		leftComposite.setLayout(new GridLayout(1, false));
		table = new Table(leftComposite,SWT.FULL_SELECTION | SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
//		tViewer = new TableViewer(table);
		
		
		GridData tableData = new GridData(); 
		tableData.heightHint = 250;
		table.setLayoutData(tableData);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		
		
		TableColumn tc1 = new TableColumn(table, SWT.CENTER);
		tc1.setWidth(25);
		TableColumn tc2 = new TableColumn(table, SWT.LEFT);
		tc2.setText("Nombre");
		tc2.setWidth(50);
		TableColumn tc3 = new TableColumn(table, SWT.LEFT);
		tc3.setText("Fichero");
		tc3.setWidth(80);
		TableColumn tc4 = new TableColumn(table, SWT.LEFT);
		tc4.setText("Aviso");
		tc4.setWidth(100);
		
		
		Composite rightComposite = new Composite(area, SWT.TOP);
		rightComposite.setLayout(new GridLayout(1, false));
		
		
		
		Button btnAdd = new Button(rightComposite, SWT.NONE);
		btnAdd.setText("Añadir");
		btnAdd.setLayoutData(getButtonGridData());
		btnAdd.addListener(SWT.Selection, new Listener(){

			@Override
			public void handleEvent(Event arg0) {
				NewTrainigDialog dialog = new NewTrainigDialog(getShell());
				dialog.open();
				table.removeAll();
				loadTrainigs();
			}
			
		});
		
		Button btnModify = new Button(rightComposite, SWT.NONE);
		btnModify.setText("Modificar");
		btnModify.setLayoutData(getButtonGridData());
		
		
		Button btnDelete = new Button(rightComposite, SWT.NONE);
		btnDelete.setText("Borrar");
		btnDelete.setLayoutData(getButtonGridData());
		
		btnDelete.addListener(SWT.Selection, new Listener(){

			@Override
			public void handleEvent(Event arg0) {
				deleteSelectedItems();
				
			}

			
			
		});
		
		loadTrainigs();
		
		return parent;
	}
	
	
	

	protected Control createButtonBar(Composite parent){
		Composite buttonArea = new Composite(parent, SWT.NONE);
		buttonArea.setLayout(new GridLayout(2, false));
		
		
		Button btnOK =  createButton(buttonArea, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		btnOK.setLayoutData(getButtonGridData());

		
		return parent;
	}
	
	@Override
	protected void okPressed(){
		
		saveTrainings();
		super.okPressed();
	}
	
	
	/**
	 * Devuelve el grid data para los botones.
	 * @return Grid data para botones.
	 */
	private GridData getButtonGridData(){
		GridData btnData = new GridData();
		btnData.heightHint = 22;
		btnData.widthHint = 60;
		return btnData;
		
	}
	
	/**
	 * Carga los entrenammientos en la tabla
	 */
	private void loadTrainigs() {
		if(table!=null){
			if(manager!=null){
				ArrayList<Training> trainings = manager.getTrainings();
				if(trainings!=null && trainings.size()>0){
					for(int i=0;i< trainings.size();i++){
						TableItem ti = new TableItem(table, SWT.NONE);
						ti.setText(1,trainings.get(i).getName());
						ti.setText(2,trainings.get(i).getFileName());
						ti.setText(3,trainings.get(i).getNotice());
						ti.setChecked(trainings.get(i).getState());
					}
					
				}
				
			}
		} 
		
	}
	
	private void saveTrainings(){
		if(table != null && table.getItemCount()>0){
			if(manager!=null){
				ArrayList<Training> newTrainings = new ArrayList<Training>();
				for(int i=0;i< table.getItemCount();i++){
					TableItem ti = table.getItem(i);
					newTrainings.add(new Training(ti.getText(1), ti.getText(2), ti.getText(3), ti.getChecked()));
				}
				manager.setTrainings(newTrainings);
				manager.saveTrainings();
			}
			
		}
	}
	
	private void deleteSelectedItems() {
		if(table!=null && table.getSelection().length>0){
			table.remove(table.getSelectionIndices());
		}
	}
	
}
