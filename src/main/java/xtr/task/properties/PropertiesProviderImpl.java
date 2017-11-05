package xtr.task.properties;

import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by root on 05.11.2017.
 */
@Component
public class PropertiesProviderImpl implements PropertiesProvider {

    private String url;
    private String keyWords;
    private String city;

    @Override
    public String getUrl() {
        return url;
    }

    @Value("${fetch.url}")
    @Synchronized
    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getKeyWords() {
        return keyWords;
    }

    @Value("${fetch.key.words}")
    @Synchronized
    @Override
    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Value("${fetch.city}")
    @Synchronized
    @Override
    public void setCity(String city) {
        this.city = city;
    }
}
