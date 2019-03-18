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

class Prancha {

	private Vinheta prancha[][];
	private int strips;
	private int distanceVinhetas;

	private static final int WIDTHDEFAULT = 100;
	private static final int HEIGHTDEFAULT = 50;
	private static final ColorImage IMAGEDEFAULT = new ColorImage(WIDTHDEFAULT - 2, HEIGHTDEFAULT - 2);
	private static final Vinheta VINHETADEFAULT = new Vinheta(IMAGEDEFAULT, 1, 1);

	/**
	 * Build a Prancha of a CS.
	 * @param strips Number of strips
	*/
	Prancha(int strips) {

		if(strips <= 0) {
			 throw new IllegalArgumentException("To construct a board insert a valid number of strips!");
		}

		this.strips = strips;
		distanceVinhetas = 0;
		prancha = new Vinheta[this.strips][1];

		for (int i = 0; i != prancha.length; i++) {
			prancha[i][0] = VINHETADEFAULT;
		}
	}

	/**
	 * Know the width of the Prancha according to all specifications.
	 * @return width of the Prancha
	 */
	int getWidthPrancha() {
		int sumWidth = 0;
		int maxWidth = 0;
		int line = 0;
		int length = 0;

		for (int a = 0; a != prancha.length; a++) {
			for (int b = 0; b != prancha[a].length; b++) {
				if (prancha[a][b] != null) {
					sumWidth += prancha[a][b].getWidthVinheta();
				}
			}

			// Find the maximum width value
			if (sumWidth > maxWidth) {
				maxWidth = sumWidth;
				line = a;
			}

			sumWidth = 0; // Reset sum
		}

		for (int a = 0; a != prancha[line].length; a++) {
			if (prancha[line][a] != null) {
				length++;
			}
		}

		return maxWidth + (length - 1) * distanceVinhetas;
	}

	/**
	 * Know the height of the Prancha according to all specifications.
	 * @return  height of the Prancha
	 */
	int getHeightPrancha() {
		int sumHeight = 0;
		int maxHeightLine = 0;
		for (int a = 0; a != prancha.length; a++) {
			for (int b = 0; b != prancha[a].length; b++) {
				if (prancha[a][b] != null && prancha[a][b].getHeightVinheta() > maxHeightLine) {
					maxHeightLine = prancha[a][b].getHeightVinheta();
				}
			}

			sumHeight += maxHeightLine;
			maxHeightLine = 0; // Reset max of line
		}

		return sumHeight + (prancha.length - 1) * distanceVinhetas;
	}

	/**
	 * Define the number of strips on the Prancha.
	 * @param strips Number of strips on the Prancha
	 */
	void setNumberOfStrips(int strips) {
		if(strips <= 0) {
			 throw new IllegalArgumentException("Please enter a valid strip value!");
		}

		this.strips = strips;
		Vinheta[][] newPrancha = null;

		// FIRST CASE - add more strips
		if (this.strips > prancha.length) {

			// Build Matrix
			newPrancha = Prancha.buildMatrix(prancha[0].length, this.strips);

			// Copy the previous Matrix state
			for (int a = 0; a != prancha.length; a++) {
				for (int b = 0; b != prancha[a].length; b++) {
					if (prancha[a][b] != null) {
						newPrancha[a][b] = prancha[a][b];
					}
				}
			}

			// Add modifications
			for (int a = prancha.length; a != newPrancha.length; a++) {
				newPrancha[a][0] = VINHETADEFAULT;
			}

			prancha = newPrancha;
		}

		// SECOND CASE - Remove a number of strips
		else if (this.strips < prancha.length) {

			// Build Matrix - add more strips
			newPrancha = Prancha.buildMatrix(prancha[0].length, this.strips);

			// Copy the previous Matrix state
			for (int a = 0; a != newPrancha.length; a++) {
				for (int b = 0; b != newPrancha[a].length; b++) {
					if (prancha[a][b] != null) {
						newPrancha[a][b] = prancha[a][b];
					}
				}
			}

			prancha = newPrancha;
		}
	}

	/**
	 * Define the number of strips on the particular Vinheta.
	 * @param locationStrips Location Strip
	 * @param numberOfVinhetas Define number of Vinhetas
	 */
	void setNumberOfVinhetas(int locationStrips, int numberOfVinhetas) {
		if(locationStrips < 0 || locationStrips >= prancha.length) {
			 throw new IllegalArgumentException("Please enter a valid value for the row!");
		}
		else if(numberOfVinhetas <= 0) {
			throw new IllegalArgumentException("Please enter a valid value for the stickers!");
		}

		Vinheta[][] newPrancha = null;

		// FIRST CASE - add more Vinhetas
		if (numberOfVinhetas > prancha[0].length) {

			// Build array
			newPrancha = Prancha.buildMatrix(numberOfVinhetas, prancha.length);

			// Copy the previous Matrix state
			for (int a = 0; a != prancha.length; a++) {
				for (int b = 0; b != prancha[a].length; b++) {
					if (prancha[a][b] != null) {
						newPrancha[a][b] = prancha[a][b];
					}
				}
			}

			// Add modifications

			for (int b = 0; b != newPrancha[locationStrips].length; b++) {
				if (newPrancha[locationStrips][b] == null) {
					newPrancha[locationStrips][b] = VINHETADEFAULT;
				}
			}

			prancha = newPrancha;
		}

		// SECOND CASE - remove Vinhetas

		else if (numberOfVinhetas <= prancha[0].length) {

			int maximumVinhetas = 0;
			maximumVinhetas = Prancha.maximumVinhetasStrip(prancha, locationStrips, false);

			if (maximumVinhetas < numberOfVinhetas) {
				maximumVinhetas = numberOfVinhetas;
			}

			// Build array
			newPrancha = Prancha.buildMatrix(maximumVinhetas, prancha.length);

			// Copy the previous Matrix state
			for (int a = 0; a != newPrancha.length; a++) {
				for (int b = 0; b != newPrancha[a].length; b++) {
					if (prancha[a][b] != null) {
						newPrancha[a][b] = prancha[a][b];
					}
				}
			}

			// Add modifications
			for (int b = 0; b != numberOfVinhetas; b++) {
				if (newPrancha[locationStrips][b] == null) {
					newPrancha[locationStrips][b] = VINHETADEFAULT;
				}
			}

			for (int b = numberOfVinhetas; b != newPrancha[0].length; b++) {
				newPrancha[locationStrips][b] = null;
			}

			prancha = newPrancha;
		}
	}

	/**
	 * To change the distance of the stickers.
	 * @param distance Distance
 	 */
	void setDistance(int distance) {
		if(distance < 0) {
			 throw new IllegalArgumentException("Distance is a positive value!");
		}

		this.distanceVinhetas = distance;
	}

	/**
	 * To replace a Vinheta image.
	 * @param xPosition Row position
	 * @param yPosition Column position
	 * @param v New Vinheta
	 */
	void setVinheta(int xPosition, int yPosition, Vinheta v) {
		illegalPosition(xPosition, yPosition, false);

		prancha[xPosition][yPosition] = new Vinheta(v.getImage(), v.getSizeFrame(), v.getTypeFrame(),
				v.getColorFrame());
	}

	/**
	 * To modify size frame of a particular Vinheta.
	 * @param xPosition Row Position
	 * @param yPosition Column Position
	 * @param sizeFrame Size Frame
	 */
	void setVinhetaSizeFrame(int xPosition, int yPosition, int sizeFrame) {
		illegalPosition(xPosition, yPosition, true);

		prancha[xPosition][yPosition].setSizeFrame(sizeFrame);
	}

	/**
	 * To modify color frame of a particular Vinheta.
	 * @param xPosition Row Position
	 * @param yPosition Column position
	 * @param colorFrame Color Frame
	 */
	void setColorFrameVinheta(int xPosition, int yPosition, Color colorFrame) {
		illegalPosition(xPosition, yPosition, true);

		prancha[xPosition][yPosition].setColorFrame(colorFrame);
	}

	/**
	 * Show vinheta black and white image.
	 * @param xPosition Row position
	 * @param yPosition column
	 */
	void setBlackAndWhiteVinheta(int xPosition, int yPosition) {
		illegalPosition(xPosition, yPosition, true);

		prancha[xPosition][yPosition].setBlackAndWhiteImage();
	}

	/**
	 * Show Vinheta color image.
	 * @param xPosition Row position
	 * @param yPosition Column position
	 */
	void setColorImageVinheta(int xPosition, int yPosition) {
		illegalPosition(xPosition, yPosition, true);

		prancha[xPosition][yPosition].setColorImage();
	}

	/**
	 * To modify the type frame of a Vinheta.
	 * @param xPosition Row position
	 * @param yPosition Column position
	 * @param typeFrame Type Frame
	 */
	void setTypeVinheta(int xPosition, int yPosition, int typeFrame) {
		illegalPosition(xPosition, yPosition, true);

		prancha[xPosition][yPosition].setTypeFrame(typeFrame);
	}

	/**
	 * Operation to display image corresponding to the Prancha.
	 * @return New image corresponding to the board
	 */
	ColorImage getPrancha() {

		ColorImage newImage = new ColorImage(this.getWidthPrancha(), this.getHeightPrancha());
		int jumpY = 0;
		int jumpX = 0;
		int maxHeight = 0;
		boolean access = true;

		for (int a = 0; a != prancha.length; a++) {
			if (a != 0) {
				maxHeight = Prancha.maximumHeight(prancha[a - 1]); // Width max
			}

			for (int b = 0; b != prancha[a].length; b++) {
				if (access && a != 0) {
					jumpY += maxHeight + distanceVinhetas;
					access = false;
				}

				if (prancha[a][b] != null) {
					Utilities.pasteImage(newImage, prancha[a][b].getVinheta(), jumpX, jumpY);
					jumpX += prancha[a][b].getWidthVinheta() + distanceVinhetas;
				}
			}

			jumpX = 0;
			access = true;
			maxHeight = 0;
		}

		return newImage;
	}

	/**
	 * This method allows you to split a Vinheta and create new Vinheta.
	 * @param xPosition Row position
	 * @param yPosition Column position
	 * @param value Division value
	 */
	void setDividirVinheta(int xPosition, int yPosition, int value) {
		illegalPosition(xPosition, yPosition, true);

		if(value <= 0){
			throw new IllegalArgumentException("Please enter a positive value greater than zero.");
		}

		int currentSizePrancha = prancha[xPosition].length;
		ColorImage imageBorderless = null;
		ColorImage[] images = new ColorImage[value];
		int jumps = 0;

		// Check if the image is Black and White.
		if (prancha[xPosition][yPosition].getStateImageVinheta()) {
			imageBorderless = prancha[xPosition][yPosition].getImageBlackAndWhite();
		}
		else {
			imageBorderless = prancha[xPosition][yPosition].getImage();
		}

		// Constructs an array of images with split images.
		for (int a = 0; a != images.length; a++) {
			images[a] = new ColorImage(imageBorderless.getWidth() / value, imageBorderless.getHeight());
			if (a != 0) {
				jumps += (imageBorderless.getWidth() / value);
			}
			for (int x = 0; x != images[a].getWidth() && x + jumps != imageBorderless.getWidth(); x++) {
				for (int y = 0; y != images[a].getHeight(); y++) {
					images[a].setColor(x, y, imageBorderless.getColor(x + jumps, y));

				}
			}
		}

		// Know the number of default Vinhetas.
		int firstnumberDefault = 0;
		for (int a = 0; a != prancha[xPosition].length; a++) {
			if (prancha[xPosition][a] == VINHETADEFAULT) {
				firstnumberDefault++;
			}
		}

		// Execution of the various changes in the Prancha.

		this.setNumberOfVinhetas(xPosition, currentSizePrancha + value - 1);
		Vinheta[] v = new Vinheta[value];


		for (int a = 0; a != v.length; a++) {
			v[a] = new Vinheta(images[a], prancha[xPosition][yPosition].getSizeFrame(),
					prancha[xPosition][yPosition].getTypeFrame(), prancha[xPosition][yPosition].getColorFrame());
		}

		// Algorithm to maintain the state of the elements that will not be modified.
		for (int b = currentSizePrancha + value - 2; b != yPosition + value - 1; b--) {
			prancha[xPosition][b] = prancha[xPosition][b - value + 1];
		}

		int i = 0;
		for (int a = yPosition; a != yPosition + value; a++) {
			prancha[xPosition][a] = v[i];
			i++;
		}

		int secondNumberDefault = 0;
		for (int a = 0; a != prancha[xPosition].length; a++) {
			if (prancha[xPosition][a] == VINHETADEFAULT) {
				secondNumberDefault++;
			}
		}

		// Remove number of vignettes in excess.
		if (secondNumberDefault > firstnumberDefault) {
			boolean access = true;
			for (int a = prancha[xPosition].length - 1; a != -1 && access; a--) {
				if (prancha[xPosition][a] == VINHETADEFAULT) {
					prancha[xPosition][a] = null;
				}
				else {
					access = false;
				}
			}
		}
	}

	/**
	 * Exceptions Launcher.
	 * @param xPosition Row Position
	 * @param yPosition Columns Position
	 * @param compareObject If need compare a Vinheta default
	 */
	private void illegalPosition(int xPosition, int yPosition, boolean compareObject) {
		if((xPosition < 0 || xPosition >= prancha.length || yPosition < 0 || yPosition >= prancha[0].length)){
			throw new IllegalArgumentException("Please enter a valid value for the row or column!");
		}
		else if(prancha[xPosition][yPosition] == VINHETADEFAULT && compareObject) {
			throw new IllegalArgumentException("First step: Insert a vinheta");
		}
		else if(prancha[xPosition][yPosition] == null && !compareObject){
			throw new IllegalArgumentException("First step: Insert a valid vinheta");
		}
	}

	/**
	 * To know the maximum height of a sticker.
	 * @param v Vinheta
	 * @return The maximum height.
	 */
	static int maximumHeight(Vinheta[] v) {
		int maxHeight = 0;

		for (int a = 0; a != v.length; a++) {
			if (v[a] != null && v[a].getHeightVinheta() > maxHeight) {
				maxHeight = v[a].getHeightVinheta();
			}
		}
		return maxHeight;
	}

	/**
	 * To know the maximum number of Vinhetas on a strip or the number of Vinhetas on a strip.
	 * @param v Prancha.
	 * @param locationLine Row to know the number of stickers.
	 * @param access To know the number of Vinhetas on the strip itself.
	 * @return The maximum number of Vinhetas.
	 */
	static int maximumVinhetasStrip(Vinheta[][] v, int locationLine, boolean access) {
		int count = 0;
		int maximum = 0;

		if (!access) {
			for (int a = 0; a != v.length; a++) {

				//Jump the row itself
				if (v[a] != null && a == locationLine && a != v.length - 1) {
					a++;
				}
				for (int b = 0; b != v[a].length; b++) {
					if (v[a][b] != null) {
						count++;
					}
				}
				if (count > maximum) {
					maximum = count;
				}
				count = 0;
			}
		}
		else { //To study the row itself
			for (int b = 0; b != v.length; b++) {
				if (v[locationLine][b] != null) {
					count++;
				}
			}

			maximum = count;
		}

		return maximum;
	}

	/**
	 * Build new Matrix
	 * @param width Number of columns
	 * @param height Number of Rows
	 * @return Matrix
	 */
	static Vinheta[][] buildMatrix(int width, int height) {
		Vinheta[][] v = new Vinheta[height][width];
		for (int a = 0; a != v.length; a++) {
			for (int b = 0; b != v[a].length; b++) {
				v[a][b] = null;

			}
		}
		return v;
	}
}
