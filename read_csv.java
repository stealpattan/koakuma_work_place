import java.io.*;
import java.util.*;
import java.text.Normalizer;

public class read_csv{
	static int num = 0;
	static int file_long;
	public static void main(String[] arg){
		String input_file_name = "input_data.csv";
		String output_file_name = "out.sql";
		file_long = getFileLong(input_file_name);
		String[][] file_data = new String[file_long][27];
		String[] sql = new String[file_long];
		//Get the all csv data;
		file_data = readCsv(input_file_name);

		//below code is used for test 
		System.out.println(file_long);
		System.out.println(Arrays.deepToString(file_data[1]));
		System.out.println(Arrays.deepToString(file_data[2]));
		
		//array initialize;
		String company_name = "";
		String job_kind = "";
		//variable initialize;
		int y = 0;
		for (int j=2; j<file_long; j++) {
			y = 4;
			String out_data[] = {"","","","",""};
			for(int i=0;i<27;i++){
				if(i<2){
					company_name = file_data[j][0];
					job_kind = file_data[j][1];
				}
				else if(i<26){
					switch(identify(file_data[1][i])){
						case "machanical":
							out_data[y] = identify(file_data[1][i]) + "_" + file_data[j][i] + ":";
						break;
						case "environment":
							out_data[y] += identify(file_data[1][i]) + "_" + file_data[j][i];
							y--;
						break;
						default:
							out_data[y] += identify(file_data[1][i]) + "_" + file_data[j][i] + ":";
						break;
					}
				}
				else{
					out_data[y] += identify(file_data[1][i]) + "_" + file_data[j][i];
				}
				sql[j] = "INSERT INTO `employee`(`company_name`,`job_kind`,`5_ago`,`4_ago`,`3_ago`,`2_ago`,`last_year`,`created`) VALUES("
							+ "'" + company_name + "'" + ","
							+ "'" + Normalizer.normalize(job_kind,Normalizer.Form.NFKC) + "'" + ","
							+ "'" + out_data[4] + "'" + ","
							+ "'" + out_data[3] + "'" + "," 
							+ "'" + out_data[2] + "'" + "," 
							+ "'" + out_data[1] + "'" + "," 
							+ "'" + out_data[0] + "'" + ","
							+ "NOW()" +
							");";
			}	
		}
		//show status;
		for (int i=0; i<file_long; i++) {
			System.out.println(i + ":" + sql[i]);
		}
		System.out.println(file_long);
		//Using BufferedWriter to output csv data;
		writeCsv(sql,output_file_name);
	}
	public static int getFileLong(String f){
		try{
			BufferedReader br = new BufferedReader(new FileReader(f));
			String data;
			num = 0;
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
	public static String identify(String depertment){
		switch(depertment){
			case "機":
				depertment = "mechanical";
				break;
			case "知":
				depertment = "intelligence";
				break;
			case "情":
				depertment = "information";
				break;
			case "生":
				depertment = "bio";
				break;
			case "環":
				depertment = "environment";
				break;
			case "電":
				depertment = "environment";
				break;
			case "医":
				depertment = "medical";
				break;
			case "看":
				depertment = "nurse";
				break;
		}
		return depertment;
	}
	public static void writeCsv(String s[], String f){
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			for (int i=2; i<file_long; i++) {
				bw.write(s[i]);
				bw.newLine();	
			}
			bw.close();
		}catch(IOException e){
			System.out.println("Cannot out put");
		}
	}	
}
