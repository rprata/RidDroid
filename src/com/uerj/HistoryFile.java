package com.uerj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import android.os.Environment;

public class HistoryFile {
	
	public HistoryFile() {
		// TODO Auto-generated constructor stub
		File file = new File(Environment.getExternalStorageDirectory()+"/RIDMobile/history");	
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(file));		
			out.write("\n");
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	public static void clearHistory(){
		File file = new File(Environment.getExternalStorageDirectory()+"/RIDMobile/history");	
		file.delete();
		new HistoryFile();
		
	}

	private static int getNumberOfLines(){
		int numberOfLines=0;
		try{
		BufferedReader br = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory()+"/RIDMobile/history"));
		while((br.readLine()) != null){
			numberOfLines++;
		}
		br.close();
		}catch (Exception e){
		e.printStackTrace();
		}
		return numberOfLines;	
	}
	
	public static String[] loadHistory(){
		BufferedReader br;
		String str[] = new String[getNumberOfLines()];
		try {
			br = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory()+"/RIDMobile/history"));
		for(int i=0;i < getNumberOfLines();i++){
				str[i]=br.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;

	}
	
	public static void AdcionaMatricula(String matricula){
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(Environment.getExternalStorageDirectory()+"/RIDMobile/history",true));		
			out.write(matricula+"\n");
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static boolean verificaMatricula(String matricula){
		try{
			BufferedReader br = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory()+"/RIDMobile/history"));
			String str;
			while ((str=br.readLine()) != null){
				if(str.compareTo(matricula)==0){
					return true;
				}
			}
			br.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;

	}
}