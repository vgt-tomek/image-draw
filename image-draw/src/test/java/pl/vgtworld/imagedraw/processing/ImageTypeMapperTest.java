package pl.vgtworld.imagedraw.processing;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import pl.vgtworld.imagedraw.ImageType;

public class ImageTypeMapperTest {
	
	@Test
	public void shouldMapPathToJpegImageType() {
		String path = "/home/user/file.jpg";
		ImageTypeMapper mapper = new ImageTypeMapper();
		
		ImageType imageType = mapper.mapFromExtension(path);
		
		assertThat(imageType).isEqualTo(ImageType.JPEG);
	}
	
	@Test
	public void shouldProperlyMapUpperCasePath() {
		String path = "/home/user/FILE.JPEG";
		ImageTypeMapper mapper = new ImageTypeMapper();
		
		ImageType imageType = mapper.mapFromExtension(path);
		
		assertThat(imageType).isEqualTo(ImageType.JPEG);
	}
	
	@Test
	public void shouldReturnNullOnUnknownExtension() {
		String path = "/home/user/file.txt";
		ImageTypeMapper mapper = new ImageTypeMapper();
		
		ImageType imageType = mapper.mapFromExtension(path);
		
		assertThat(imageType).isNull();
	}
	
	@Test
	public void shouldProperlyMapJpegImageTypeToExtension() {
		ImageTypeMapper mapper = new ImageTypeMapper();
		
		String extension = mapper.mapFromImageType(ImageType.JPEG);
		
		assertThat(extension).isEqualTo("jpg");
	}
	
	@Test
	public void shouldProperlyMapBmpImageTypeToExtension() {
		ImageTypeMapper mapper = new ImageTypeMapper();
		
		String extension = mapper.mapFromImageType(ImageType.BMP);
		
		assertThat(extension).isEqualTo("bmp");
	}
	
	@Test
	public void shouldProperlyMapPngImageTypeToExtension() {
		ImageTypeMapper mapper = new ImageTypeMapper();
		
		String extension = mapper.mapFromImageType(ImageType.PNG);
		
		assertThat(extension).isEqualTo("png");
	}
	
}
