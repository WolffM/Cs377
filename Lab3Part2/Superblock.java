import java.io.Serializable;

public class Superblock implements Serializable{
	
	inode [] inodes = new inode[16];
	byte [] freeBlockList = new byte[128];
	
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
	public void readInodes(byte[] data){
		byte[] temp = new byte[8];
		byte[] temp2 = new byte[4];
		byte[] temp3 = new byte[32];
		byte[] temp4 = new byte[4];
		for(int i = 0; i < inodes.length; i++){
			for(int ix = 0; ix < 8; ix++){
				temp[ix] = data[48*i+ix+1];
			}
			inodes[i].name = temp.toString().toCharArray();
			
			for(int iy = 0; iy < 4; iy++){
				temp2[iy] = data[48*i+iy+9];
			}
			inodes[i].size = byteArrayToInt(temp2)[0];
			for(int iz = 0; iz < 32; iz++){
				temp3[iz] = data[48*i+iz+13];
			}
			inodes[i].blockPointers = byteArrayToInt(temp3);
			
			for(int iw = 0; iw < 4; iw++)
			{
				temp4[iw] = data[48*i+iw+45];
			}
			inodes[i].used = byteArrayToInt(temp4)[0];
		}
	}
	public static int[] byteArrayToInt(byte [] b)
	{
		int[] answer = new int[b.length/4];
		int sum = 0;
		
		for(int i = 0; i < answer.length; i++)
		{
			sum = 0;
			for(int j = 0; j < b.length; j++)
			{
				int lshift = (4-1-j)*8;
				sum+= (b[j]& 0x000000FF) << lshift;
			}
			answer[i] = sum;
		}
		return answer;
	}
	public void changeBlock(int position, byte change)
	{
		freeBlockList[position] = change;
	}
}
