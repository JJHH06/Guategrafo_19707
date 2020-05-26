import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
/**
 * 
 * @author Jose Hurtarte 19707
 *
 */

public class GraphProvider {
	
	private ArrayList<String> cityNames = new ArrayList<String>();
	private long[][] matrizDeAdyacencia = null;
	
	
	private void fillNames() {
		cityNames = new ArrayList<String>();
		String barra = File.separator;
		String dir = System.getProperty("user.dir");
		File archivo = new File (dir + barra + "guategrafo.txt");
		FileReader fr;
		String linea = "";

		try {
			fr = new FileReader (archivo);
			BufferedReader br = new BufferedReader(fr);
			String[] listaLinea = null;

			while((linea = br.readLine()) != null){
				
				
				if(!(Character.toString(linea.charAt(0)).equals("#"))) {//el # es para comentarios
					
					//Con esto se separan todos los posibles valores de definicion
					listaLinea = linea.split(" ");
					if(!cityNames.contains(listaLinea[0])) {
						cityNames.add(listaLinea[0]);
					}
					
					if(!cityNames.contains(listaLinea[1])) {
						cityNames.add(listaLinea[1]);
					}
					
				}
				
				
			}
		}
		
		catch (Exception e) {
		}

	}
	
	/**
	 * llena todas las casillas de la matriz de adyacencia con un numero muy grande
	 */
	private void setInfinitePaths() {
		matrizDeAdyacencia = new long[cityNames.size()][cityNames.size()];
		for (int row = 0; row < matrizDeAdyacencia.length; row++) { 
			for (int col = 0; col < matrizDeAdyacencia[row].length; col++) {
				matrizDeAdyacencia[row][col] = 999999999; 
				} 
			}

	}
	
	/**
	 * cambia las casillas que si existen, en vez de infinito, por un valor real de distancia
	 */
	private void filMatrix() {
		
		String barra = File.separator;
		String dir = System.getProperty("user.dir");
		File archivo = new File (dir + barra + "guategrafo.txt");
		FileReader fr;
		String linea = "";

		try {
			fr = new FileReader (archivo);
			BufferedReader br = new BufferedReader(fr);
			String[] listaLinea = null;

			while((linea = br.readLine()) != null){
				
				
				
					
					//Con este se separan todas las casillas
					listaLinea = linea.split(" ");
					
					matrizDeAdyacencia[cityNames.indexOf(listaLinea[0])][cityNames.indexOf(listaLinea[1])] =Long.parseLong(listaLinea[2]);
					
				
				
				
			}
		}
		
		catch (Exception e) {
		}

	}
	
	
	
	
	/**
	 * 
	 * @return el arraylist de los nombres de ciudades
	 */
	public ArrayList<String> nodeNames(){
		fillNames();
		return cityNames;
	}
	
	/**
	 * 
	 * @return matriz de adyacencia de el grafo
	 */
	public long[][] adjacencyMatrix(){
		setInfinitePaths();
		filMatrix();
		return matrizDeAdyacencia;
	}
	
	
	
	
	
	
	

}
