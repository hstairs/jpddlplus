package se_util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;



public class InputFile {


	
	 public static String[] read(String path){
		 
		 String[] tmp = new String[0];
		 
		 try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))){
			 
			List<String> lines = br.lines().collect(Collectors.toList());

	 //salvo la prima riga del file relativa alle dimensioni generali m,h,p,o,q,k


			tmp= lines.get(0).split(" ");

		 } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 return tmp;

		 
	 
	 }
         public static String readDomain(String path){
             String out = "";
             try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))){
			 
			out += br.lines().collect(Collectors.toList());

	 //salvo la prima riga del file relativa alle dimensioni generali m,h,p,o,q,k



		 } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
             return out;
         }
}