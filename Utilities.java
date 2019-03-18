/*******************************************************************************
* Copyright (c) 2019 Jorge V. Rodrigues.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v2.0
* which accompanies this distribution.
*
* Contributors:
*     Jorge V. Rodrigues - developer
 ******************************************************************************/

import aguiaj.iscte.*;

class Utilities {

	/**
	 * Paste an image given another image.
	 * @param img Image that will receive the copy.
	 * @param paste Image to paste
	 * @param a
	 * @param b
	 */
	static void pasteImage(ColorImage img, ColorImage paste, int a, int b) {
		ColorImage imgCopy = Utilities.copyImage(img);

		// Condition
		if(a > img.getWidth() || b > img.getHeight()) {
			throw new IllegalArgumentException("Points are out of bounds");
		}

		// Transform Image
		for(int x = 0; x != paste.getWidth() && x + a!= img.getWidth(); x++) {
			for(int y = 0; y != paste.getHeight() && y + b!= img.getHeight(); y++) {
				imgCopy.setColor(x + a, y + b, paste.getColor(x,y));
			}
		}

		for(int x = 0; x != imgCopy.getWidth(); x++) {
			for(int y = 0; y != imgCopy.getHeight(); y++) {
				img.setColor(x, y, imgCopy.getColor(x,y));
			}
		}
	}

	/**
	 * Copy the image
	 * @param img Image to copy
	 * @return New image copied
	 */
	static ColorImage copyImage(ColorImage img) {
		ColorImage copyImage = new ColorImage(img.getWidth(), img.getHeight());
		for(int x = 0; x != img.getWidth(); x++) {
			for(int y = 0; y != img.getHeight(); y++) {
				copyImage.setColor(x,y, img.getColor(x,y));
			}
		}

		return copyImage;
	}

	/**
	 * Construct a square in an image.
	 * @param image Image to build the square
	 * @param x
	 * @param y
	 * @param size
	 * @param color
	 */
	static void fillSquare(ColorImage image, int x, int y, int size, Color color) {
		for(int x0 = x ; x0 != x + size && x0 < image.getWidth(); x0++) {
			for(int y0 = y; y0 != y + size && y0 < image.getHeight(); y0++) {
				image.setColor(x0, y0, color);
			}
		}
	}
}
