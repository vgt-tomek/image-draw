package pl.vgtworld.imagedraw.processing;

import java.util.HashMap;
import java.util.Map;

import pl.vgtworld.imagedraw.ImageType;

class ImageTypeMapper {
	
	private static final Map<String, ImageType> extensionToImageType = new HashMap<>();
	
	private static final Map<ImageType, String> imageTypeToExtension = new HashMap<>();
	
	static {
		extensionToImageType.put(".jpg", ImageType.JPEG);
		extensionToImageType.put(".jpeg", ImageType.JPEG);
		extensionToImageType.put(".bmp", ImageType.BMP);
		extensionToImageType.put(".png", ImageType.PNG);
		
		imageTypeToExtension.put(ImageType.JPEG, "jpg");
		imageTypeToExtension.put(ImageType.BMP, "bmp");
		imageTypeToExtension.put(ImageType.PNG, "png");
	}
	
	public ImageType mapFromExtension(String path) {
		String lowerCasePath = path.toLowerCase();
		int index = lowerCasePath.lastIndexOf('.');
		if (index < 0) {
			return null;
		}
		String pathSuffix = lowerCasePath.substring(index);
		return extensionToImageType.get(pathSuffix);
	}
	
	public String mapFromImageType(ImageType imageType) {
		return imageTypeToExtension.get(imageType);
	}
	
}
