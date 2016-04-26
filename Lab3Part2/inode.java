
import java.io.Serializable;
import java.util.Arrays;

//Needs to be Serializable so we can read the object in from disk using upstream
public class inode implements Serializable{
	
	char[] name = new char[8];
	int size;
	int[] blockPointers = new int[8];
	int used = 0;
	

public inode(int size)
{
	this.size = size;
}

//Contains the references to the block pointers it is responsible for
public void setBlockPointers(int start)
{
	for(int x = 0; x < blockPointers.length; x++)
	{
		blockPointers[x] = start + x;
	}
}

public void setName(char[] fileName)
{
	name = Arrays.copyOf(fileName, fileName.length);
	String t = "";
	for(int i = 0; i < name.length; i++)
		t += name[i];
	System.out.println(t);
}

}
