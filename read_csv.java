import java.io.*;

public class read_csv{
	static int num = 1;
	String sql;
	public static void main(String[] arg){
		String file_name = "input_data.csv";
		int number_of_line = getFileLong(file_name);
		System.out.println(number_of_line);
		String[][] file_data = new String[number_of_line][27];
		file_data = readCsv(file_name);
		//below code is used for test 
		for (int i=0; i<10; i++) {
			for(int j=0; j<=25; j++){
				System.out.println("num=" + i + ": kind=" + j + ": data=" + file_data[i][j]);
			}
		}
		for (int i=0; i<=25; i++) {
			System.out.println(
				"colomun_number=" + i + ":識別結果=" + identify(i)+ ":就職者人数=" + file_data[3][i]
			);
		}
		//above code is used for test
		/*
			insert into employ(`company_name`,`job_kind`,`5_ago`,`4_ago`,`3_ago`,`2_ago`,`last_year`)
		*/
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
				num++;
			}
		}catch(IOException e){
			System.out.println("Cannot find the file");
		}
		return datas;
	}

	public static String identify(int num){
		String st = "no_data";
		int extra_num;
		if(num <= 6){
			switch(num){
				case 0:
					st = "企業名";
					break;
				case 1:
					st = "職種";
					break;
				case 2:
					st = "機械";
					break;
				case 3:
					st = "知能";
					break;
				case 4:
					st = "情報";
					break;
				case 5:
					st = "生物";
					break;
				case 6:
					st = "環境";
					break;
				default:
					st = "エラー";
					break;
			}
		}
		else if(num > 6){
			
		}
		else{
			System.out.println("Error is happened");
		}
		return st;
	}
}
