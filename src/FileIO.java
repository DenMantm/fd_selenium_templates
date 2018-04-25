import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



//This is for just to handle password saving, saves user object onto h drive
public class FileIO {

	
	public void setValues(User u){
		ObjectOutputStream oos = null;
		FileOutputStream fout = null;
		try{
		     fout = new FileOutputStream("H:\\user.ser", false);
		     oos = new ObjectOutputStream(fout);
		    oos.writeObject(u);
		    oos.close();
		} catch (Exception ex) {
		    ex.printStackTrace();
		} finally {
		    if(oos  != null){
		        try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    } 
		}
	}
	public User getValues(){
		
		ObjectInputStream objectinputstream = null;
		User u = null;
		
		try {
			FileInputStream streamIn = new FileInputStream("H:\\user.ser");
		     objectinputstream = new ObjectInputStream(streamIn);
		    u = (User) objectinputstream.readObject();
		    
		    
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    if(objectinputstream != null){
		        try {
					objectinputstream .close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    } 
		}
		return u;
	}
	
}
