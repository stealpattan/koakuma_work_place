import java.io.*;

public class read_csv{
	static int num = 1;
	public static void main(String[] arg){
		String file_name = "input_data.csv";
		int number_of_line = getFileLong(file_name);
		System.out.println(number_of_line);
		String[][] file_data = new String[number_of_line][27];
		file_data = readCsv(file_name);
		for (int i=0; i<10; i++) {
			for(int j=0; j<27; j++){
				System.out.println("num=" + i + ": kind=" + j + ": data=" + file_data[i][j]);
			}
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

	private static String[][] readCsv(String f){		
		String[][] datas = new String[num][27];
		num = 0;
		try{
			File file = new File(f);
			FileInputStream input = new FileInputStream(file);
			InputStreamReader stream = new InputStreamReader(input,"UTF-8");
			BufferedReader br = new BufferedReader(stream);			
			String data;
			while((data = br.readLine()) != null){
				byte[] b = data.getBytes();
				data = new String(b, "UTF-8");
				String[] split_data = data.split("," , -1);
				for(int j=0;j<split_data.length;j++){
					datas[num][j] = split_data[j];
				}
//				datas[num] = data;
				num++;
			}
		}catch(IOException e){
			System.out.println("Cannot find the file");
		}
		return datas;
	}
}
