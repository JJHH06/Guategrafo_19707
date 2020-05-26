import java.util.ArrayList;
/**
 * Clase hecha mediante tutorial de algoritmo de floyd en java
 * 
 * @author Jose Hurtarte 19707
 * @author MasterHeHeGar del tutorial https://www.youtube.com/watch?v=xK0ShW9G-Ts
 *
 *
 */


/**
 * originalmente fui modificando ciertas cosas del tutorial para que las rutas fueran relacionadas a los lugares
 * esta clase se modifico drasticamente debido a que asi se podria incluir en Guategrafo
 * 
 *
 */
public class ShortestPath {
	
	private GraphProvider managerGrafo = new GraphProvider();
	ArrayList<String> cityNames;
	long[][] matrizDeAdyacencia;
	
	/**
	 * constructor basado en el grafo de Direcciones de Guatemala
	 */
	public ShortestPath() {
		cityNames = managerGrafo.nodeNames();
		matrizDeAdyacencia = managerGrafo.adjacencyMatrix();
	}
	
	
	

    /**
     * Metodo que determina todas las caracteristicas del grafo y las rutas mas cortas
     * @param mAdy matriz de adyacencia del grafo
     * @return String concatenado del resumen de guategrafo
     */
    public String algoritmoFloyd( long [][] mAdy){
    
        int vertices = mAdy.length;
        long matrizAdyacencia [][] = mAdy;
        String caminos [][] = new String [vertices][vertices];
        String caminosAuxiliares [][]=new String [vertices][vertices];
        String caminoRecorrido ="",cadena="",caminitos="";
        int i,j,k;
        float temporal1, temporal2, temporal3, temporal4, minimo;

 
        for(i=0;i<vertices;i++) {
            for(j=0;j<vertices;j++){
                caminos[i][j]="";
                caminosAuxiliares[i][j]="";
            }
        }
        ///////////////////////////////////
        for(k=0;k<vertices; k++){
            for(i=0;i<vertices; i++){
               for (j=0;j<vertices; j++){
                   
            temporal1=matrizAdyacencia [i][j];
            temporal2=matrizAdyacencia [i][k];
            temporal3=matrizAdyacencia [k][j];
            temporal4= temporal2 + temporal3;
            minimo=Math.min(temporal1, temporal4);
            if(temporal1 != temporal4){
                if(minimo == temporal4){
                    caminoRecorrido="";
                    caminosAuxiliares[i][j] = k + "";
                    caminos[i][j]= caminosR(i,k,caminosAuxiliares, caminoRecorrido) + cityNames.get(k);
                          
                }
            }
            matrizAdyacencia[i][j]=(long) minimo;
               }
            }
        }
    for(i=0;i<vertices;i++) {
            for(j=0;j<vertices;j++){
                cadena=cadena+"["+matrizAdyacencia[i][j]+"]";
            }
            cadena=cadena+"\n";
        }
    for(i=0;i<vertices;i++) {
            for(j=0;j<vertices;j++){
                if(matrizAdyacencia[i][j]!=1000000000){
                    if(i!=j){
                        if(caminos[i][j].equals("")){
                            caminitos += "de ( "+ cityNames.get(i)+" ---->"+cityNames.get(j)+") irse por...("+cityNames.get(i)+", "+cityNames.get(j)+")\n";
                        }else{ caminitos +="de ("+ cityNames.get(i)+"---->"+cityNames.get(j)+") irse por...("+cityNames.get(i)+", "+caminos[i][j] + ", " +cityNames.get(j)+")\n";
                    }
                }
            }
        }
    }
    
    return "LA MATRIZ DE CAMINOS MAS CORTOS ENTRE LOS DIFERENTES VERTICES ES \n" +cadena+
            "\n LOS DIFERENTES CAMINOS MAS CORTOS ENTRE VERTICES SON:\n"+caminitos;
    }
    
    /**
     * 
     *metodo recursivo para dar direcciones alternas
     * @return texto concatenado de direcciones
     */
    public String caminosR(int i, int k, String[][] caminosAuxiliares, String caminoRecorrido) {
        if(caminosAuxiliares[i][k].equals("")){
            return "";
         }else{
            caminoRecorrido +=caminosR(i, Integer.parseInt(caminosAuxiliares[i][k].toString()), caminosAuxiliares, caminoRecorrido)+(Integer.parseInt(caminosAuxiliares[i][k].toString())+1)+", ";
            return caminoRecorrido;
        }
    }
    
    /**
     * 
     * @return el resumen general basado en la matriz de adyacencia
     */
    public String resumenGeneral() {
    	return algoritmoFloyd(matrizDeAdyacencia);
    }
    
}
