import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.nio.file.Path;
public class filesystem {
	static Superblock fst;
	static Scanner a = new Scanner(System.in);
	static byte [] data;
	
	//Looks for the first inode that is not used and gives it the name and uses a helper function to add that to the data array
	public static void create(char name[], int size){
		for(int x = 0; x < 16; x ++){
			if(fst.inodes[x].used != 1){
				fst.inodes[x].used = 1;
				fst.inodes[x].setName(name);
				byte[] temp = name.toString().getBytes();
				 
				data = copyToArray(data,temp,fst.inodes[x].blockPointers[0]*1024);
				fst.inodes[x].size = size;
				return;
			}
		}
	}
	
	//Helper function for adding elements into a larger array
	public static byte[] copyToArray(byte[] original, byte[] from, int originalStart)
	{
		for(int i = 0; i < from.length; i++)
		{
			original[i + originalStart] 
					=
					from[i];
		}
		
		return original;
	}
	
	//Finds the first file with the same name as the one entered and then uses the clearblock helper function to remove it
	public static void delete(char name[]){
		for(int x = 0; x < 16; x++){
			if(Arrays.equals(fst.inodes[x].name,name)){
				inode temp = new inode(8);
				temp.blockPointers = fst.inodes[x].blockPointers;
				fst.inodes[x] = temp;
				for(int y = 0; y < 8; y++)
				clearblock(fst.inodes[x].blockPointers[x]);
				System.out.println("Deleted!");
				return;
			}
		}
		System.out.println("File not found.");
	}
	//Helper function to change all values of a block to 0
	public static void clearblock(int start){
		for(int x = 0; x < 8192; x++){
			filesystem.data[start*1024+x] = 0;
		}
	}
	//Looks for the inodes and the right block and copies it into a temp byte array. Then turns the result into a string and sends it back as a char array.
	public static char[] read(char name[], int blockNum, char buf[]){
		byte[] temp = new byte[buf.length];
		for(int x = 0; x < 16; x++){
			if(Arrays.equals(fst.inodes[x].name,name)){
				for(int y = 0; y < buf.length; y++){
					temp[y]=data[fst.inodes[x].blockPointers[blockNum]*1024+y];
				}
				String temp2 = new String(temp);
				buf = temp2.toCharArray();
				return buf;
			}
		}
		System.out.println("File not found!");
		return buf;
	}
	
	//Similar to the read method but instead of sending the information in the buffer to a temp byte array, it writes it directly to the main data array.
	public static void write(char name[], int blockNum, char buf[]){
		for(int x = 0; x < 16; x++){
			if(Arrays.equals(fst.inodes[x].name,name)){
					String temp = new String(buf);
					data = copyToArray(data, temp.getBytes(), fst.inodes[x].blockPointers[blockNum]*1024);
					System.out.println("Data written!");
			}
		}
	}
	
	//Reads the name of every inode and prints out all names in order, even if they are NULL.
	public static void ls(){
		inode [] temp = fst.inodes;
		for(int i = 0; i < temp.length; i++)
		{
			String name = new String(temp[i].name);
			System.out.println(name);
		}
	}
	//Helper function to write to disk at the end of the method
	public static byte[] writeToData(Object spblk) throws IOException {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
	    os.writeObject(spblk);
	    return out.toByteArray();
	}
	//Helper function to get the inode and superblock objects from disk
	public static Object readFromData(byte[] data) throws IOException, ClassNotFoundException
	{
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream is = new ObjectInputStream(in);
		return is.readObject();
	}
	
	//Reads from file named "disk0"	and sets up a menu interface to run all the methods
	public static void main (String []args) throws ClassNotFoundException{
		String name = "disk0";
		System.out.println("Program start!");
		Path path =  Paths.get(name);
		boolean check = false;
		try {
			filesystem.data = Files.readAllBytes(path);
			for(int x = 0; x < 1024; x++){
				if(filesystem.data[x] != 0){
					byte[] temp = new byte[1024];
					temp = Arrays.copyOfRange(data,0,1024);
					fst = (Superblock)readFromData(data);
					check = true;
				
				}
			}
			if(!check)
			{
				fst = new Superblock();
				byte[] temp = writeToData(fst);
				data = filesystem.copyToArray(data, temp, 0);
			}
				
			
				//TODO WRITE SUPERBLOCK TO DATA
			
			int swap = 0;
			do{
				System.out.print("\n\nChoose:\n"
						+ "1. create\n"
						+ "2. delete\n"
						+ "3. read\n"
						+ "4. write\n"
						+ "5. ls\n"
						+ "6. exit\n\n"
						+ "Enter:");
				swap = a.nextInt();
				String temp;
				int temp2;
				char[] temp3;
				switch(swap){
				case 1: 
					System.out.print("Enter String fileName(size <8):");
					temp = a.next();
					System.out.print("\nEnter size:");
					temp2 = a.nextInt();
					create(temp.toCharArray(), temp2);
					break;
				case 2:	
					System.out.print("Enter String fileName(size <8):");
					temp = a.next();
					delete(temp.toCharArray());
					break;
				case 3:
					System.out.print("Enter String fileName(size <8):");
					temp = a.next();
					System.out.print("\nEnter blocknum:");
					temp2 = a.nextInt();
					System.out.print("\nEnter Buffer Size:");
					temp3 = new char[a.nextInt()];
					temp3 = read(temp.toCharArray(), temp2, temp3);
					System.out.println(temp3);
					break;
				case 4:
					System.out.print("Enter String fileName(size <8):");
					temp = a.next();
					System.out.print("\nEnter blocknum:");
					temp2 = a.nextInt();
					System.out.print("\nEnter text:");
					temp3 = a.next().toCharArray();
					write(temp.toCharArray(), temp2, temp3);
					break;
				case 5: ls(); break;
				case 6: break;
				}
			}while(swap != 6);
			
			//Program must exit properly to write to disk
			
			FileOutputStream fs = new FileOutputStream(name);
			byte[] temp = writeToData(fst);
			data = filesystem.copyToArray(data, temp, 0);
			fs.write(data);
			fs.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
