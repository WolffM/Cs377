
import java.io.Serializable;
import java.util.Arrays;

public class inode implements Serializable{
	
	char[] name = new char[8];
	int size;
	int[] blockPointers = new int[8];
	int used = 0;
	

public inode(int size)
{
	this.size = size;
}

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
	String thisIsBroken = "";
	for(int i = 0; i < name.length; i++)
		thisIsBroken += name[i];
	System.out.println(thisIsBroken);
}

}
