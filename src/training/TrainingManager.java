package training;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import rcpdetection.Activator;
import utils.Util;


/**
 * Clase encargada de gestionar los entrenamientos
 * @author Robert
 *
 */
public class TrainingManager {
	
	/**
	 * Lista de entrenamientos.
	 */
	private ArrayList<Training> trainings;
	
	

	
	/**
	 * Ruta del fichero de entrenamientos.
	 */
	private static final String TRAINIGS_FILE = "xml\\trainings.xml";
	
	
	
	/**
	 * Constructor. Siempre carga directamente del fichero xml.
	 */
	public TrainingManager(){
		this.initialize();
		
	}
	
	/**
	 * Inicializa y obtiene los entrenamientos del xml 
	 */
	public void initialize(){
		//inicializamos el array
		trainings = new ArrayList<Training>();
		//cargamos los datos del xml.
		try {
			loadDataFromXml();
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * Carga los datos del xml. 
	 */
	private void loadDataFromXml() throws Exception{
		Node rootNode = Util.getRootNodeFromXml(Activator.getPlugInPath()+File.separator+TRAINIGS_FILE);
		if(rootNode!=null){
			loadTrainings(rootNode);
		}


	}

	/**
	 * Carga los entrenamientos en el array.
	 * @param rootNode nodo raiz del xml.
	 */
	private void loadTrainings(Node rootNode) {
		//comprobamos que no sea un nodo nulo y que sea el nodo Trainings.
		if(rootNode != null  && rootNode.getNodeName().equals("trainings")){
			NodeList trainingsChilds =  rootNode.getChildNodes();
			if(trainingsChilds != null && trainingsChilds.getLength()>0){
				for(int i = 0; i< trainingsChilds.getLength();i++){
					//Comprobamos que es un nodo training.
					if(trainingsChilds.item(i).getNodeName().equals("training")){
					loadTraining(trainingsChilds.item(i));
					}
					
				}
				
			}
			
		}
		
	}

	/**
	 * Crea un entrenamiemto apartir del nodo training
	 * @param trainingNode nodo training.
	 */
	private void loadTraining(Node trainingNode) {
        //Comprobamos que el nodo no sea nuelo y sea un nodo training
		if(trainingNode!=null && trainingNode.getNodeName().equals("training")){
			
			NodeList trainingAtt = trainingNode.getChildNodes();
			//Comprobamos que existan atributos
			if(trainingAtt!=null && trainingAtt.getLength()>0){
				String trainingName =""; 
				String trainingFileName = ""; 
				String trainingNotice = "";	
				boolean trainingState = false;
				
				for(int i=0 ; i< trainingAtt.getLength(); i++){
					if(trainingAtt.item(i).getNodeName().equals("name")){
						trainingName = trainingAtt.item(i).getTextContent();
					}else if(trainingAtt.item(i).getNodeName().equals("filename")){
						trainingFileName = trainingAtt.item(i).getTextContent();
					}else if(trainingAtt.item(i).getNodeName().equals("notice")){
						trainingNotice = trainingAtt.item(i).getTextContent();
					}else if(trainingAtt.item(i).getNodeName().equals("state")){
						String state = trainingAtt.item(i).getTextContent();
						trainingState = Boolean.parseBoolean(state);
					}
					
				}
				//añadimos el entrenamiento a la lista de entrenamientos.
				addTraining(trainingName, trainingFileName, trainingNotice, trainingState);
			}
		}
	}
	
	/**
	 * Añade un nuevo entrenamiento a la lista de entrenamientos.
	 * @param name nombre del entrenamiento.
	 * @param fileName fichero de entrenamiento.
	 * @param notice texto de aviso.
	 */
	public void addTraining(String name, String fileName, String notice, boolean state){
		if(!name.equals("") && !fileName.equals("") && !notice.equals("")){
			trainings.add(new Training(name, fileName, notice, state));
		}
	}
	
	/**
	 * Elimina un entrenamiento de la lista.
	 * @param name nombre del entrenamiento.
	 */
	public void deleteTraining(String name){
		if(name != null && (!name.equals(""))){
			if(trainings!=null && trainings.size()>0){
				for(int i =0; i< trainings.size();i++){
					if(trainings.get(i).getName().equals(name)){
						trainings.remove(i);
					}
				}
			}
			
		}
	}
	
	
	/**
	 * Devuelve los entrenamientos 
	 * @return Array con los entrenamientos.
	 */
	public ArrayList<Training> getTrainigs(){
		return trainings;
	} 
	
	/**
	 * Devuelve el estado para un entrenamiento.
	 * @param trainingName nombre del entrenamiento.
	 * @return estado.
	 */
	public boolean getTrainingState(String trainingName){
		if(trainingName != null && (!trainingName.equals(""))){
			if(trainings!=null && trainings.size()>0){
				for(int i =0; i< trainings.size();i++){
					if(trainings.get(i).getName().equals(trainingName)){
						return trainings.get(i).getState();
					}
				}
			}
			
		}
		return false;
		
	}
	
	/**
	 * Establece el estado a un entrenamiento..
	 * @param trainingName nombre del entrenamiento.
	 * @param state estado del entrenamiento.
	 * 
	 */
	public void setTrainingState(String trainingName, boolean state){
		if(trainingName != null && (!trainingName.equals(""))){
			if(trainings!=null && trainings.size()>0){
				for(int i =0; i< trainings.size();i++){
					if(trainings.get(i).getName().equals(trainingName)){
						trainings.get(i).setState(state);
						return;
					}
				}
			}
			
		}
	}

	public void setTrainings(ArrayList<Training> trainings){
		if(trainings!=null){
			this.trainings = trainings;
		}
	}

	public void saveTrainings() {
		try{
			Document xmldoc = null;
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			DOMImplementation impl = builder.getDOMImplementation();
			// Document.
			xmldoc = impl.createDocument(null,null, null);
			// Root element.
			Element root = xmldoc.createElement("trainings");
			xmldoc.appendChild(root);
			
			if(trainings!=null && trainings.size()>0){
				for(int i=0;i<trainings.size();i++){
					Element training = xmldoc.createElement("training");
					
					Element eName = xmldoc.createElement("name");
					eName.setTextContent(trainings.get(i).getName());
					training.appendChild(eName);
					
					Element eFile = xmldoc.createElement("filename");
					eFile.setTextContent(trainings.get(i).getFileName());
					training.appendChild(eFile);
					
					Element eNotice = xmldoc.createElement("notice");
					eNotice.setTextContent(trainings.get(i).getNotice());
					training.appendChild(eNotice);
					
					Element eState = xmldoc.createElement("state");
					eState.setTextContent(Boolean.toString(trainings.get(i).getState()));
					training.appendChild(eState);
					
					root.appendChild(training);
				}
			}
			Util.saveXml(xmldoc, Activator.getPlugInPath()+File.separator+TRAINIGS_FILE);
		}catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
