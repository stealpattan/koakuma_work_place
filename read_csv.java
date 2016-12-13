import java.io.*;

public class read_csv{
	static int num = 1;
	public static void main(String[] arg){
		String file_name = "kougakukengai26.csv";
		int number_of_line = getFileLong(file_name);
		System.out.println(number_of_line);
		String[] file_data = new String[number_of_line];
		file_data = readCsv(file_name);
		for (int i=0; i<100; i++) {
			System.out.println(file_data[i]);
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

	private static String[] readCsv(String f){		
		String[] datas = new String[num];
		num = 0;
		try{
			File file = new File(f);
			FileInputStream input = new FileInputStream(file);
			InputStreamReader stream = new InputStreamReader(input,"SJIS");
			BufferedReader br = new BufferedReader(stream);			
			String data;
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
