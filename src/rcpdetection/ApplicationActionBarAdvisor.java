package rcpdetection;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import actions.TrainingManagerAction;
import actions.VideoManagerAction;

/**
 * An action bar advisor is responsible for creating, adding, and disposing of
 * the actions added to a workbench window. Each window will be populated with
 * new actions.
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	
	 // Actions - important to allocate these only in makeActions, and then use them
    // in the fill methods.  This ensures that the actions aren't recreated
    // when fillActionBars is called with FILL_PROXY.
    private IWorkbenchAction exitAction;
    private IWorkbenchAction aboutAction;
    private TrainingManagerAction trainingManagerAction;
    private VideoManagerAction videoManagerAction;
	

	// Actions - important to allocate these only in makeActions, and then use
	// them
	// in the fill methods. This ensures that the actions aren't recreated
	// when fillActionBars is called with FILL_PROXY.

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}
	
	 protected void makeActions(final IWorkbenchWindow window) {
	        // Creates the actions and registers them.
	        // Registering is needed to ensure that key bindings work.
	        // The corresponding commands keybindings are defined in the plugin.xml file.
	        // Registering also provides automatic disposal of the actions when
	        // the window is closed.

	        exitAction = ActionFactory.QUIT.create(window);
	        register(exitAction);
	        
	        aboutAction = ActionFactory.ABOUT.create(window);
	        register(aboutAction);
	        
	        trainingManagerAction = new TrainingManagerAction(window, "Gestión de Entrenamientos");
	        register(trainingManagerAction);
	        
	        videoManagerAction = new VideoManagerAction(window, "Gestión de Vídeo");
	        register(videoManagerAction);
	    }
	    
	    protected void fillMenuBar(IMenuManager menuBar) {
	        MenuManager fileMenu = new MenuManager("&Archivo", IWorkbenchActionConstants.M_FILE);
	        MenuManager configMenu = new MenuManager("&Configuración", "configuracion");
	        MenuManager helpMenu = new MenuManager("A&yuda", IWorkbenchActionConstants.M_HELP);
	        
	        menuBar.add(fileMenu);
	        menuBar.add(configMenu);
	        menuBar.add(helpMenu);
	        
	        // File

	        fileMenu.add(exitAction);
	        
	        //Configuración
	        configMenu.add(trainingManagerAction);
	        configMenu.add(new Separator());
	        configMenu.add(videoManagerAction);
	        
	        // Help
	        helpMenu.add(aboutAction);
	    }
	    
	    protected void fillCoolBar(ICoolBarManager coolBar) {
	        IToolBarManager toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
	        coolBar.add(new ToolBarContributionItem(toolbar, "main"));   
	        toolbar.add(trainingManagerAction);
	        toolbar.add(videoManagerAction);
	    }
}
