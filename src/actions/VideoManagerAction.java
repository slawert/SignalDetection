package actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;

import rcpdetection.Activator;
import dialogs.VideoManagerDialog;

public class VideoManagerAction  extends Action {
	
	private final IWorkbenchWindow window;
	
	
	
	public VideoManagerAction(IWorkbenchWindow window, String label) {
		this.window = window;
        setText(label);
        // The id is used to refer to the action in a menu or toolbar
		setId(IActionIds.VIDEOMANAGER);
        // Associate the action with a pre-defined command, to allow key bindings.
		setActionDefinitionId(IActionIds.VIDEOMANAGER);
		setImageDescriptor(Activator.getImageDescriptor("/icons/eye.png"));
	}
	
	public void run() {
		if(window != null) {	
				VideoManagerDialog dialog = new VideoManagerDialog(window.getShell());
				dialog.open();
		}
	}
}
