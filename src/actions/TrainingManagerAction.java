package actions;





import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;

import dialogs.TrainingManagerDialog;

import rcpdetection.Activator;


public class TrainingManagerAction extends Action {
	
	private final IWorkbenchWindow window;
	
	
	
	public TrainingManagerAction(IWorkbenchWindow window, String label) {
		this.window = window;
        setText(label);
        // The id is used to refer to the action in a menu or toolbar
		setId(IActionIds.TRAININGMANAGER);
        // Associate the action with a pre-defined command, to allow key bindings.
		setActionDefinitionId(IActionIds.TRAININGMANAGER);
		setImageDescriptor(Activator.getImageDescriptor("/icons/manager.png"));
	}
	
	public void run() {
		if(window != null) {	
				TrainingManagerDialog dialog = new TrainingManagerDialog(window.getShell());
				dialog.open();
		}
	}
}
