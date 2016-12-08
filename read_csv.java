import java.io.*;

public class read_csv{
	public static void main(String[] arg){

	}

	private void readCsv(String f){
		int num = 0;
		try{
			String data;
			BufferedReader br = new BufferedReader(new FileReader(f));
			while((data = br.readLine()) != null){
				num++;
			}
		}catch(IOException e){
			System.out.println("Cannot open the file");
		}

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
	}
}
