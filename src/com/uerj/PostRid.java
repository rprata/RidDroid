package com.uerj;


import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EncodingUtils;
import android.util.Log;

public class PostRid {

	HttpClient httpClient;
	HttpPost httpPost;
	String matricula;
	String nome;
	
	private String formMatricula(String matricula){
		StringBuffer formMatricula = new StringBuffer(matricula);
		formMatricula.insert(4,'.');
		formMatricula.insert(6,'.');
		formMatricula.insert(12,'.');
		return formMatricula.toString();
	}

	public PostRid(String matricula) {
		// TODO Auto-generated constructor stub
		this.matricula = matricula;
		
		httpClient = new DefaultHttpClient();
		httpPost = new HttpPost("http://www.alunoonline.uerj.br/rid/id_rid.php?matricula="+this.matricula+"&button=Enviar");
		String responseText = "";
		try {
			HttpResponse response = httpClient.execute(httpPost);
			responseText = new BasicResponseHandler().handleResponse(response);
			StringBuffer stringBuffer = new StringBuffer(responseText);
			stringBuffer.delete(0,responseText.indexOf(formMatricula(matricula)) + formMatricula(matricula).length()+3);
			Log.i("RidMobile","***************** Index: "+stringBuffer.toString());
			for(int i=0;i<stringBuffer.length();i++){
				if(stringBuffer.charAt(i)>47&&stringBuffer.charAt(i)<58){
					stringBuffer.delete(i-1, stringBuffer.length());
					break;
				}
			}
			Log.i("RidMobile","***************** Index: "+stringBuffer.toString());
			Log.i("RidMobile","***************** Index: "+stringBuffer.toString().replace(' ', '+'));
			this.nome=stringBuffer.toString();

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getUrlHistorico(){	
		return "http://www.alunoonline.uerj.br/discursadas/discursadas.php";
	}

	public String getUrlNotas(){
	    return "http://www.alunoonline.uerj.br/notasdoperiododoaluno/notasparciais.php";
	}
	
	public String getUrlDisciplinas(){
	    return "http://www.alunoonline.uerj.br/disemcur/disemcur.php";
	}
	
	public byte[] postData(){
		String postData = "lixo="+this.matricula+"&matricula="+this.matricula+"&nome="+this.nome;
	    return EncodingUtils.getBytes(postData, "BASE64");
	}

	public String getUrlRid(){
		return "http://www.alunoonline.uerj.br/rid/id_rid.php?matricula="+this.matricula+"&button=Enviar";
	}

}

