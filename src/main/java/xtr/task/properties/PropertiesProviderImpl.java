package xtr.task.properties;

import lombok.AccessLevel;
import lombok.Synchronized;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
public class PropertiesProviderImpl implements PropertiesProvider {

	volatile String url;
	volatile String keyWords;
	volatile String city;

	final Object setLock = new Object();

	@Override
	public String getUrl() {
		return url;
	}

	@Value("${fetch.url}")
	@Synchronized("setLock")
	@Override
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String getKeyWords() {
		return keyWords;
	}

	@Value("${fetch.key.words}")
	@Synchronized("setLock")
	@Override
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	@Override
	public String getCity() {
		return city;
	}

	@Value("${fetch.city}")
	@Synchronized("setLock")
	@Override
	public void setCity(String city) {
		this.city = city;
	}
}
