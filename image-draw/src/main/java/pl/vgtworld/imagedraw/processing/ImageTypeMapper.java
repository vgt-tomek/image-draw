package pl.vgtworld.imagedraw.processing;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import pl.vgtworld.imagedraw.ImageType;

class ImageTypeMapper {
	
	private static final Map<String, ImageType> EXTENSION_TO_IMAGE_TYPE = new HashMap<>();
	
	private static final Map<ImageType, String> IMAGE_TYPE_TO_EXTENSION = new EnumMap<>(ImageType.class);
	
	static {
		EXTENSION_TO_IMAGE_TYPE.put(".jpg", ImageType.JPEG);
		EXTENSION_TO_IMAGE_TYPE.put(".jpeg", ImageType.JPEG);
		EXTENSION_TO_IMAGE_TYPE.put(".bmp", ImageType.BMP);
		EXTENSION_TO_IMAGE_TYPE.put(".png", ImageType.PNG);
		
		IMAGE_TYPE_TO_EXTENSION.put(ImageType.JPEG, "jpg");
		IMAGE_TYPE_TO_EXTENSION.put(ImageType.BMP, "bmp");
		IMAGE_TYPE_TO_EXTENSION.put(ImageType.PNG, "png");
	}
	
	ImageType mapFromExtension(String path) {
		String lowerCasePath = path.toLowerCase();
		int index = lowerCasePath.lastIndexOf('.');
		if (index < 0) {
			return null;
		}
		String pathSuffix = lowerCasePath.substring(index);
		return EXTENSION_TO_IMAGE_TYPE.get(pathSuffix);
	}
	
	String mapFromImageType(ImageType imageType) {
		return IMAGE_TYPE_TO_EXTENSION.get(imageType);
	}
	
}
