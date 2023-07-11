import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.DefaultHttpClient
import org.json.JSONObject

import java.nio.charset.StandardCharsets

HttpClient client = new DefaultHttpClient();
// add the url here
HttpPost request = new HttpPost("<URL>");
// set parameters
request.setHeader("Content-type", "application/json");

// add required body using json object
JSONObject item = new JSONObject();
item.put("<parameter key >", "<parameter value>");

StringEntity body = new StringEntity(item.toString());

request.setEntity(body)
// execute thr request and get response
HttpResponse resp = client.execute(request);

InputStream inputStream = resp.getEntity().getContent();

String text = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
println(text)
// get output from a json response
JSONObject json = new JSONObject(text);
System.out.println(json.getString("get output"));

//import com.mirakl.hybris.core.model.ShopModel
//
//List<ShopModel> shops = flexibleSearchService.search("Select {pk} from {shop}").getResult();
//
//shops.stream().forEach {shop->
//    if (shop.state.code == "OPEN"){
//        println(shop.getId()+"|"+shop.getName()+"|"+shop.hasShippingFeesToCAG)
//    }
//}



