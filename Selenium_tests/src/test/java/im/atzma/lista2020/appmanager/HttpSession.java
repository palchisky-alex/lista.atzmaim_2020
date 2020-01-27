package im.atzma.lista2020.appmanager;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;

public class HttpSession {
    CloseableHttpClient httpClient;
    ApplicationManager app;

    public HttpSession(ApplicationManager app) {
        CloseableHttpClient httpClient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
        this.app = app;
    }

    public boolean login() {
    HttpPost post = new HttpPost();
    return true;
    }
}
