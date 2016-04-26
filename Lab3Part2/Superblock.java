import java.io.Serializable;

//Singleton object which contains all the inodes and freeblocklist
public class Superblock implements Serializable{
	
	inode [] inodes = new inode[16];
	byte [] freeBlockList = new byte[128];
	
	//Defaults all inodes and their blockpointers on creation
	public Superblock(){
		freeBlockList[0] = 1;
		initializeInodes();
		
	}
	public Superblock(byte[] data){
		for(int x = 0; x < 128; x ++){
			freeBlockList[x] = data[x];
		}
		readInodes(data);
	}

	public void initializeInodes()
	{
		for(int i = 0; i < inodes.length; i++)
		{
			inodes[i] = new inode(8);
			inodes[i].setBlockPointers((8*i)+1);
		}
	}
	public void changeBlock(int position, byte change)
	{
		freeBlockList[position] = change;
	}
}
