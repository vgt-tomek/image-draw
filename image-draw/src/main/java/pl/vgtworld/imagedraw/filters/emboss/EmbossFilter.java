package pl.vgtworld.imagedraw.filters.emboss;

import java.util.HashMap;
import java.util.Map;

import pl.vgtworld.imagedraw.ImageDrawEntity;
import pl.vgtworld.imagedraw.filters.ImageDrawFilter;
import pl.vgtworld.imagedraw.filters.matrix.MatrixFilter;

public class EmbossFilter implements ImageDrawFilter {
	
	private static final Map<EmbossDirection, float[][]> matrices = new HashMap<>();
	
	private EmbossDirection direction;
	
	static {
		matrices.put(EmbossDirection.LOWER_RIGHT, new float[][] {
				{ -2, -1, 0 },
				{ -1, 1, 1 },
				{ 0, 1, 2 }
		});
		matrices.put(EmbossDirection.LOWER_LEFT, new float[][] {
				{ 0, -1, -2 },
				{ 1, 1, -1 },
				{ 2, 1, 0 }
		});
		matrices.put(EmbossDirection.UPPER_LEFT, new float[][] {
				{ 2, 1, 0 },
				{ 1, 1, -1 },
				{ 0, -1, -2 }
		});
		matrices.put(EmbossDirection.UPPER_RIGHT, new float[][] {
				{ 0, 1, 2 },
				{ -1, 1, 1 },
				{ -2, -1, 0 }
		});
	}
	
	public EmbossFilter(EmbossDirection direction) {
		this.direction = direction;
	}
	
	@Override
	public void doFilter(ImageDrawEntity image, int x, int y, int width, int height) {
		if (direction == null) {
			throw new IllegalStateException("Direction is not defined.");
		}
		MatrixFilter filter = new MatrixFilter(matrices.get(direction));
		filter.doFilter(image, x, y, width, height);
	}
	
}
