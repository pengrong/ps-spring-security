package com.inspur.gcloud.config;

import com.inspur.gcloud.PhotoInfo;
import com.inspur.gcloud.impl.PhotoServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfig {


	@Bean
	public PhotoServiceImpl photoServices() {
		List<PhotoInfo> photos = new ArrayList<PhotoInfo>();
		photos.add(createPhoto("1", "marissa"));
		photos.add(createPhoto("2", "paul"));
		photos.add(createPhoto("3", "marissa"));
		photos.add(createPhoto("4", "paul"));
		photos.add(createPhoto("5", "marissa"));
		photos.add(createPhoto("6", "paul"));

		PhotoServiceImpl photoServices = new PhotoServiceImpl();
		photoServices.setPhotos(photos);
		return photoServices;
	}


	private PhotoInfo createPhoto(String id, String userId) {
		PhotoInfo photo = new PhotoInfo();
		photo.setId(id);
		photo.setName("photo" + id + ".jpg");
		photo.setUserId(userId);
		photo.setResourceURL("/org/springframework/security/oauth/examples/sparklr/impl/resources/" + photo.getName());
		return photo;
	}

}