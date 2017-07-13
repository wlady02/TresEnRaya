package sistemasdistrubuidos.tresenraya.server.logic;

import sistemasdistrubuidos.tresenraya.common.Player;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Hashtable;

public class SkeletonGame implements ISkeleton {
	
	
	private final int iid = 1;
    private static int objid = 0;
    //No puede haber dos esqueletos con iids iguales
    Hashtable<Integer, Object> objectHash = new Hashtable<Integer, Object>();
    public void addObject(Object obj) {
        objectHash.put(objid, obj);
        objid++;
    }
    public Object getObject(int objid) {

        return objectHash.get(objid);
    }

	@Override
	public int getIid() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void process(DataInputStream canalEntrada,
			DataOutputStream canalSalida) {

				String message;
				int movement;
				int player;				

				try {
					
					int numMethod = canalEntrada.readInt();
		            switch (numMethod) {

	                case 1:
	                	
	                	//Crear instancia del juego
	                    Game32 objetoGame = new Game32();
	                    addObject(objetoGame);
	                    canalSalida.writeInt(objid-1);
	                    break;
	                    
	                case 2:
	                	
	                	objid =canalEntrada.readInt();
	                    player = canalEntrada.readInt();
						//movement = Integer.parseInt(message);	          
	                    objetoGame = (Game32) getObject(objid);
	                    
	                    if (player == Player.PLAYER1.ordinal()){
							canalSalida.writeUTF("Es tu turno");
	                    }
						else
							canalSalida.writeUTF("Es el turno del jugador 1");
	                    
	                    break;
	                    
		            }
					

				} catch (IOException exception) {
					System.out.println("Error on playing: " + exception);
				} 
						
					
				
			}		
	}


