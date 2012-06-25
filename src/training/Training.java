package training;


/**
 * Clase encargada de guardar la información de un entrenamiento en particular.
 * @author Robert
 *
 */
public class Training {

	/**
	 * Nombre del entrenamiento.
	 */
	private String name;
	
	/**
	 * Nombre del fichero xml que contiene el entrenamiento.
	 */
	private String fileName;
	
	/**
	 * Texto de aviso de objeto encontrado.
	 */
	private String notice;
	
	/**
	 * Estado del entrenamiento.
	 * true = activo.
	 * false = deshabilitado.
	 */
	private boolean state;

	/**
	 * Constructor.
	 * @param name nombre del entrenamiento
	 * @param fileName fichero del entrenamiento
	 * @param notice cadena de aviso para objeto encontrado.
	 * @param state estado del entrenamiento activado/desactivado.
	 */
	public Training(String name, String fileName, String notice, boolean state){
		this.name = name;
		this.fileName = fileName;
		this.notice  = notice;
		this.state = state;
	}
	
	
	/**
	 * Establece el estado del entrenamiento.
	 * @param state estado del entrenamiento.
	 */
	public void setState(boolean state){
		this.state = state;
	}
	
	
	/**
	 * Devuelve el estado del entrenaminento.
	 * @return estado del entrenamiento.
	 */
	public boolean getState(){
		return this.state;
	}
	
	/**
	 * Devuelve el nombre del entrenamiento.
	 * @return nombre del entrenamiento.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Establece el nombre para el entrenamiento
	 * @param name Nombre del entrenamiento.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Devuelve el nombre del fichero de entrenamiento.
	 * @return nombre del fichero de entrenamiento.
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * Establece el nombre del fichero de entrenamiento.
	 * @param fileName nombre del fichero de entrenamiento.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
 
	/**
	 * Devuelve la cadena de aviso.
	 * @return cadena de aviso.
	 */
	public String getNotice() {
		return notice;
	}

	/**
	 * Establece la cadena de aviso para un objeto encontrado del entrenamiento.
	 * @param notice cadena de aviso.
	 */
	public void setNoticeSttring(String notice) {
		this.notice = notice;
	}
	
}
