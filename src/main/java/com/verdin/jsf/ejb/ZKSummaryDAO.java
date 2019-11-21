package com.verdin.jsf.ejb;

import com.verdin.jsf.model.ZKSummary;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jboss.logging.Logger;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


@Named("zkSumService")
@ApplicationScoped
public class ZKSummaryDAO {
	
	static Logger logger = Logger.getLogger(ZKSummary.class);
	
	public ZKSummary getZKSummary() throws Exception {

		logger.info("get in DAO ...");

		ZKSummary m = new ZKSummary();
		String json = null;

		try {

			String url = "http://192.168.3.132:8080/api/v1/clusters/C10H16/services/ZOOKEEPER/";

			HttpHost targetHost = new HttpHost("192.168.3.132" , 8080, "http");
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("admin", "admin"));

			AuthCache authCache = new BasicAuthCache();
			authCache.put(targetHost, new BasicScheme());

			// Add AuthCache to the execution context
			final HttpClientContext context = HttpClientContext.create();
			context.setCredentialsProvider(credsProvider);
			context.setAuthCache(authCache);

			HttpClient client = HttpClientBuilder.create().build();

			HttpResponse response = client.execute(new HttpGet(url), context);

			HttpEntity entity = response.getEntity();

			System.out.println(response.getStatusLine());


			Header[] headers = response.getAllHeaders();

			for (int i = 0; i < headers.length; i++) {
				System.out.println(headers[i]);
			}

			if (entity != null) {

				json =     EntityUtils.toString(entity);

			}

			JSONObject obj = new JSONObject(json);

			//JSONArray arr = obj.getJSONArray("alerts_summary");
			JSONObject arr = obj.getJSONObject("alerts_summary");

			m.setCritical(arr.getInt("CRITICAL"));
			m.setMaintenance(arr.getInt("MAINTENANCE"));
			m.setOk(arr.getInt("OK"));
			m.setUnknown(arr.getInt("UNKNOWN"));
			m.setWarning(arr.getInt("WARNING"));

		}  catch (Exception e) {
			
			e.printStackTrace();

		}


		return m;

	}


}
