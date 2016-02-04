import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.jmx.snmp.Timestamp;


public class BackendController {
	private String [] songTitles;

	public JSONArray requestJSONArray(String url, String data) throws Exception{
		
		//String url="http://localhost:8888/DataSecurity/login.php";
		// String json = "{\"message\":\"This is a message\"}";

		    HttpClient httpClient = new DefaultHttpClient();

		    try {
		        HttpPost request = new HttpPost(url);
		        StringEntity params =new StringEntity(data);
		        request.addHeader("content-type", "application/x-www-form-urlencoded");
		        request.setEntity(params);
		        HttpResponse response = httpClient.execute(request);
		        String stringJson = org.apache.http.util.EntityUtils.toString(response.getEntity());
		        System.out.println("Data json="+stringJson);
		        
		        JSONArray jsonArr = new JSONArray(stringJson);
//		        JSONObject item = jsonArr.getJSONObject(2);
//		        item.getInt("id");
//		        System.out.println("Song id ="+item.getInt("id"));
//		       /// JSONObject jsonObject = new JSONObject("{\"phonetype\":\"N95\",\"cat\":\"WP\"}");
//		        
//		        // handle response here...
//		        System.out.println("Data json object ="+ jsonArr);
//		        org.apache.http.util.EntityUtils.consume(response.getEntity());
		        return jsonArr;
		        
		    } catch (Exception ex) {
		        // handle exception here
		    } finally {
		        httpClient.getConnectionManager().shutdown();
		    }
			return null;
	}
	
	public JSONObject requestJSONObject(String url, String data) throws Exception{
		
		//String url="http://localhost:8888/DataSecurity/login.php";
		// String json = "{\"message\":\"This is a message\"}";

		    HttpClient httpClient = new DefaultHttpClient();

		    try {
		        HttpPost request = new HttpPost(url);
		        StringEntity params =new StringEntity(data);
		        request.addHeader("content-type", "application/x-www-form-urlencoded");
		        request.setEntity(params);
		        HttpResponse response = httpClient.execute(request);
		        String stringJson = org.apache.http.util.EntityUtils.toString(response.getEntity());
		       
		        
		        JSONObject jsonObj = new JSONObject(stringJson);
		        return jsonObj;
		        
		    } catch (Exception ex) {
		        // handle exception here
		    } finally {
		        httpClient.getConnectionManager().shutdown();
		    }
			return null;
	}
	
	public String requestString(String url, String data) throws Exception{
		
		//String url="http://localhost:8888/DataSecurity/login.php";
		// String json = "{\"message\":\"This is a message\"}";

		    HttpClient httpClient = new DefaultHttpClient();

		    try {
		        HttpPost request = new HttpPost(url);
		        StringEntity params =new StringEntity(data);
		        request.addHeader("content-type", "application/x-www-form-urlencoded");
		        request.setEntity(params);
		        HttpResponse response = httpClient.execute(request);
		        String stringJson = org.apache.http.util.EntityUtils.toString(response.getEntity());
		       
		        return stringJson;
		        		        
		    } catch (Exception ex) {
		        // handle exception here
		    } finally {
		        httpClient.getConnectionManager().shutdown();
		    }
			return null;
	}

public void login(String email, String password) throws Exception{
		
		String url = "http://54.169.229.161/music/public/clientlogin";
		String data = "email="+email+"&password="+password;
		HttpClient httpClient = new DefaultHttpClient();

	    try {
	        HttpPost request = new HttpPost(url);
	        StringEntity params =new StringEntity(data);
	        request.addHeader("content-type", "application/x-www-form-urlencoded");
	        request.setEntity(params);
	        HttpResponse response = httpClient.execute(request);
	        String stringJson = org.apache.http.util.EntityUtils.toString(response.getEntity());
	       
	        
	        JSONObject jsonObj = new JSONObject(stringJson);
	        if(jsonObj.getInt("login_status")==1){
	        	
	        	System.out.println("Your login is success");
	        }
	        else{
	        	System.out.println("Your login is not success");

	        }
	        
	    } catch (Exception ex) {
	        // handle exception here
	    } finally {
	        httpClient.getConnectionManager().shutdown();
	    }
				

}
	
	public String[][] listSong(String userid) throws Exception{
		
		String url = "http://54.169.229.161/music/public/musics/list";
		String data = "userid="+userid;
		
		JSONArray jsonArr=this.requestJSONArray(url, data); //array in array
		
		String [] songTitles = new String[jsonArr.length()];
		String [] songIds = new String[jsonArr.length()];
		
		for (int i=0;i<jsonArr.length();i++){
			JSONObject jsonObj= jsonArr.getJSONObject(i);
			songTitles[i]=jsonObj.getString("title");
			songIds[i] = jsonObj.getString("id");
		}
		
		
		String [][] listSong = new String[][]{songIds,songTitles};
		return listSong;
	}
	
	public void buySong(String userid, String songid, String duration, String string, String password ) throws Exception{
		
		String url = "http://54.169.229.161/music/public/musics/buy";
		String data = "songid="+songid+"&duration="+duration+"&expire_date="+string+"&password="+password;
		JSONObject jsonObj = this.requestJSONObject(url, data);
		String success = jsonObj.getString("status");
		if(success.equals("success")){
			downloadSong(userid, songid, password);
		}
		else{
			
		}
	}
	
	
	public void renewSong(String userid, String songid, String duration, String string, String password ) throws Exception{
		
		String url = "http://54.169.229.161/music/public/musics/buy";
		String data = "songid="+songid+"&duration="+duration+"&expire_date="+string+"&password="+password;
		JSONObject jsonObj = this.requestJSONObject(url, data);
		String success = jsonObj.getString("status");
		if(success.equals("success")){
			downloadSong(userid, songid, password);
		}
		else{
			
		}
	}	
	public void downloadSong(String userid, String songid, String password) throws Exception{
		String url = "http://54.169.229.161/music/public/musics/download";
		String data = "userid="+userid+"&songid="+songid+"&password="+password;

		writeFile(this.requestString(url, data),songid+".txt");
	}
	
	
	
	
	public void writeFile(String text, String fileName){
		try {
           // String str = "SomeMoreTextIsHere";
            File newTextFile = new File("/Users/duchdynil/Desktop/Mysong/"+fileName);

            FileWriter fw = new FileWriter(newTextFile);
            fw.write(text);
            fw.close();

        } catch (IOException iox) {
            //do stuff with exception
            iox.printStackTrace();
        }
	}
	
	 
}