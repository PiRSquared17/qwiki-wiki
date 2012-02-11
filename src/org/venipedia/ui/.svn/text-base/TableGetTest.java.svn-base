package org.venipedia.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class TableGetTest {
	public static void main(String[] args) {

		try {
			// Construct data
			String data = URLEncoder.encode("user", "UTF-8") + "="
					+ URLEncoder.encode("venipedi_maint", "UTF-8");
			data += "&" + URLEncoder.encode("pass", "UTF-8") + "="
			+ URLEncoder.encode("100institute", "UTF-8");
			data += "&" + URLEncoder.encode("table", "UTF-8") + "="
			+ URLEncoder.encode("ponti", "UTF-8");
			data += "&" + URLEncoder.encode("db", "UTF-8") + "="
			+ URLEncoder.encode("venipedi_maint", "UTF-8");

			// Send data
			URL url = new URL("http://localhost/TUT/POSTDATA/post.php");
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());
			wr.write(data);
			wr.flush();

			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				System.out.println(line);
			}
			wr.close();
			rd.close();
		} catch (Exception e) {
		}

	}
}
