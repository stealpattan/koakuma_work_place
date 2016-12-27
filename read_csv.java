import java.io.*;
import java.util.*;

public class read_csv{
	static int num = 1;
	String sql;
	public static void main(String[] arg){
		String file_name = "input_data.csv";
		int file_long = getFileLong(file_name);
		String[][] file_data = new String[file_long][27];
		String[] sql = new String[file_long];
		//Get the all csv data;
		file_data = readCsv(file_name);


		//below code is used for test 
		System.out.println(file_long);
		System.out.println(Arrays.deepToString(file_data[1]));
		System.out.println(Arrays.deepToString(file_data[2]));
		
		//array initialize;
		String company_name = "";
		String job_kind = "";
		//variable initialize;
		int y = 0;
		for (int j=2; j<=file_long-2; j++) {
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
				sql[j] = "INSERT INTO table(company_name,job_kind,5_ago,4_ago,3_ago,2_ago,last_year) VALUES(" + 
							company_name + "," + 
							job_kind + "," + 
							out_data[4] + "," + 
							out_data[3] + "," + 
							out_data[2] + "," + 
							out_data[1] + "," + 
							out_data[0] + ")";
			}	
		}
		//show status;
		// for (int i=0; i<96; i++) {
		// 	System.out.println(sql[i]);
		// }
		//show status;
		// System.out.println("company_name:" + company_name);
		// System.out.println("job_kind:" + job_kind);
		// for(int j=4;j>=0;j--){
		// 	System.out.println(j + ":" + out_data[j]);
		// }


		/*
			Data base structure
				id
				company name
				job kind
				5
					mechanical
					intelligence
					infomation
					bio
					environment
				4
				3
				2
				1
				created
				modified
		*/
		/*
			insert into table(company_name, job_kind, 5_ago, 4_ago, 3_ago, 2_ago, last_year)
						 values(company_name, job_kind, 5_ago_text, 4_ago_text, 3_ago_text, 2_ago_text, last_year_text);
			sampe -> 5_ago_text -> mechanical_0,intelligence_0,information_0,bio_1,environment_0 <- String form.
		*/
		/*
			insert into table()
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
}
