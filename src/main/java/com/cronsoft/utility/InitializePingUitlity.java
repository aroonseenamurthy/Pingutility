package com.cronsoft.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class InitializePingUitlity {

	public static void main(String args[]) {
		String fileName = "/Users/Arun/Documents/workspace/PingUtility/src/main/resources/url.properties";
		try(Stream<String> stream = Files.lines(Paths.get(fileName))){
			stream.forEach(s -> pingSite(s));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void pingSite(String url) {
		// Lambda Runnable
		Runnable task = () -> { 

			HttpClient client = HttpClientBuilder.create()
					.setDefaultRequestConfig(RequestConfig.custom()
		                    .setCookieSpec(CookieSpecs.STANDARD).build())
					.build();
			HttpGet request = new HttpGet(url);

			HttpResponse response = null;
			try {
				response = client.execute(request);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Response Code  for " + url + " : "
		                + response.getStatusLine().getStatusCode());

			BufferedReader rd = null;
			try {
				rd = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent()));
			} catch (UnsupportedOperationException | IOException e) {
				e.printStackTrace();
			}

			StringBuffer result = new StringBuffer();
			String line = "";
			try {
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			};
		 
		// start the thread
		new Thread(task).start();
	}
}
