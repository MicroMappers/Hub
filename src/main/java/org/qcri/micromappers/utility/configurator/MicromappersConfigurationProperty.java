package org.qcri.micromappers.utility.configurator;

/**
 * @author Kushal
 * 
 *         Enum containing all the property keys required by the Micromappers.
 *
 */

public enum MicromappersConfigurationProperty implements ConfigurationProperty {
	SERVER_NAME("SERVER_NAME"),
	TWITTER_APP_KEY("TWITTER_APP_KEY"),
	TWITTER_APP_SECRET("TWITTER_APP_SECRET"),
	FACEBOOK_APP_KEY("FACEBOOK_APP_KEY"),
	FACEBOOK_APP_SECRET("FACEBOOK_APP_SECRET"),
	GOOGLE_APP_KEY("GOOGLE_APP_KEY"),
	GOOGLE_APP_SECRET("GOOGLE_APP_SECRET"),
	GDELT_IMAGE_PATH("GDELT_IMAGE_PATH"),
	GDELT_ARTICLE_PATH("GDELT_ARTICLE_PATH"),
	GDELT_LAST_UPDATE_URL("GDELT_LAST_UPDATE_URL"),
	GDELT_DOWNLOADED_LAST_UPDATE_PATH("GDELT_DOWNLOADED_LAST_UPDATE_PATH"),
	GDELT_JSON_UPDATE_PATH("GDELT_JSON_UPDATE_PATH"),
	Feed_PATH("Feed_PATH"),
	IMAGE_CLASSIFIER_PATH("IMAGE_CLASSIFIER_PATH"),
	SNOPES_COM_BASE_URL("SNOPES_COM_BASE_URL"),
	SNOPES_COM_FACT_CHECK_URL("SNOPES_COM_FACT_CHECK_URL"),
	SNOPES_COM_NEWS_URL("SNOPES_COM_NEWS_URL"),
	RECONNECT_NET_FAILURE_WAIT_SECONDS("RECONNECT_NET_FAILURE_WAIT_SECONDS"),
	RECONNECT_NET_FAILURE_RETRY_ATTEMPTS("RECONNECT_NET_FAILURE_RETRY_ATTEMPTS"),
	RECONNECT_RATE_LIMIT_WAIT_SECONDS("RECONNECT_RATE_LIMIT_WAIT_SECONDS"),
	RECONNECT_RATE_LIMIT_RETRY_ATTEMPTS("RECONNECT_RATE_LIMIT_RETRY_ATTEMPTS"),
	FACEBOOK_MAX_API_HITS_HOURLY_PER_USER("FACEBOOK_MAX_API_HITS_HOURLY_PER_USER"),
	FACEBOOK_LOAD_CHECK_INTERVAL_MINUTES("FACEBOOK_LOAD_CHECK_INTERVAL_MINUTES"),
	RECONNECT_SERVICE_UNAVAILABLE_WAIT_SECONDS("RECONNECT_SERVICE_UNAVAILABLE_WAIT_SECONDS"),
	RECONNECT_SERVICE_UNAVAILABLE_RETRY_ATTEMPTS("RECONNECT_SERVICE_UNAVAILABLE_RETRY_ATTEMPTS"),
	GOOGLE_NEWS_RSS_URL("GOOGLE_NEWS_RSS_URL"),
	FACT_CHECK_RSS_URL("FACT_CHECK_RSS_URL"),
	MICROSOFT_CONGNITIVE_VISION_API_ENDPOINT("MICROSOFT_CONGNITIVE_VISION_API_ENDPOINT"),
	MICROSOFT_CONGNITIVE_VISION_API_SUBSCRIPTION_KEY("MICROSOFT_CONGNITIVE_VISION_API_SUBSCRIPTION_KEY");
	private final String configurationProperty;

	private MicromappersConfigurationProperty(String property) {
		configurationProperty = property;
	}

	@Override
	public String getName() {
		return this.configurationProperty;
	}

}
