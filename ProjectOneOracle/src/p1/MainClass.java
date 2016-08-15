package p1;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class MainClass {
	
	public static void main(String[] args) {

		try {
			Driver myDriver = new oracle.jdbc.driver.OracleDriver();
			DriverManager.registerDriver(myDriver);

			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Load Driver: Success");

			String url = "jdbc:oracle:thin:@db-test.p.icpsr.umich.edu:1521:icpsr";
			String user = "archonnex";

			Connection conn = DriverManager.getConnection(url, user, user);

			//int counter = 138;
			
			// Query insert to table product with 4 values
			String query = "INSERT INTO DIP_GRANT_T VALUES(?, ?, ?, ?)";
			// Create prepare statement
			PreparedStatement pstmt = conn.prepareStatement(query);
			// get list product from file text
			ArrayList<Product> listProduct = getListProductFromTextFile("D:/jake/docs/firstDay.txt");
			// insert list to db
			for (int i = 0; i < listProduct.size(); i++) {
				pstmt.setInt(1, listProduct.get(i).getId());
				pstmt.setString(2, listProduct.get(i).getPi_names());
				pstmt.setString(3, listProduct.get(i).getTerm());
				pstmt.setString(4, listProduct.get(i).getFunders());

				pstmt.executeUpdate();
				System.out.println("Insert success record: " + (i + 1));	
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}

	public static ArrayList<Product> getListProductFromTextFile(String filePath) {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader bReader = null;
		ArrayList<Product> listResult = new ArrayList<Product>();
	

		try {
			fis = new FileInputStream(filePath);
			isr = new InputStreamReader(fis);
			bReader = new BufferedReader(isr);
			// String that saves line get from text file
			String line = null;
			// Array save product
			String[] strProduct = null;
			
			int count = 238;
			// loop and get all data from text file
			while (true) {
				// Get one line
				line = bReader.readLine();
				// Check if line get is empty
				if (line == null) {
					break;
				} else {
					strProduct = line.split(",");
					//intProduct[0] = first;
					listResult.add(new Product(++count, strProduct[1].substring(2, strProduct[1].length()-1),
							strProduct[2].substring(2, strProduct[2].length()-1), strProduct[3].substring(2, strProduct[3].length()-1)));
				}
			}
		} catch (Exception e) {
			System.out.println("Error.");
			e.printStackTrace();
		}finally {
			// close file
			try {
				bReader.close();
				isr.close();
				fis.close();
			} catch (IOException e) {
				// TODO auto-generated catch block
				e.printStackTrace();
			}// catch
		}// finally
		return listResult;
	}// array list
}// main
