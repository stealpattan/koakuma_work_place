import java.io.*;

public class read_csv{
	static int num = 0;
	public static void main(String[] arg){
		if(arg != null){
			int number_of_line = getFileLong("kougakukengai26.csv");
			System.out.println(number_of_line);
		}
	}

	public static int getFileLong(String f){
		try{
			BufferedReader br = new BufferedReader(new FileReader(f));
			String data;
			while((data = br.readLine()) != null){
				num++;
			}
		}catch(IOException e){
				System.out.println(e);
		}
		return num;
	}

	private String[] readCsv(String f){
		String[] datas = new String[num];
		num = 0;
		try{
			String data;
			BufferedReader br = new BufferedReader(new FileReader(f));
			while((data = br.readLine()) != null){
				datas[num] = data;
				num++;
			}
		}catch(IOException e){
			System.out.println("Cannot find the file");
		}
		return datas;
	}
}
