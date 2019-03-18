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

class Board {

	private Panel Board[][];
	private int strips;
	private int distancePanels;

	private static final int WIDTHDEFAULT = 100;
	private static final int HEIGHTDEFAULT = 50;
	private static final ColorImage IMAGEDEFAULT = new ColorImage(WIDTHDEFAULT - 2, HEIGHTDEFAULT - 2);
	private static final Panel PanelDEFAULT = new Panel(IMAGEDEFAULT, 1, 1);

	/**
	 * Build a Board of a CS.
	 * @param strips Number of strips
	*/
	Board(int strips) {

		if(strips <= 0) {
			 throw new IllegalArgumentException("To construct a board insert a valid number of strips!");
		}

		this.strips = strips;
		distancePanels = 0;
		Board = new Panel[this.strips][1];

		for (int i = 0; i != Board.length; i++) {
			Board[i][0] = PanelDEFAULT;
		}
	}

	/**
	 * Know the width of the Board according to all specifications.
	 * @return width of the Board
	 */
	int getWidthBoard() {
		int sumWidth = 0;
		int maxWidth = 0;
		int line = 0;
		int length = 0;

		for (int a = 0; a != Board.length; a++) {
			for (int b = 0; b != Board[a].length; b++) {
				if (Board[a][b] != null) {
					sumWidth += Board[a][b].getWidthPanel();
				}
			}

			// Find the maximum width value
			if (sumWidth > maxWidth) {
				maxWidth = sumWidth;
				line = a;
			}

			sumWidth = 0; // Reset sum
		}

		for (int a = 0; a != Board[line].length; a++) {
			if (Board[line][a] != null) {
				length++;
			}
		}

		return maxWidth + (length - 1) * distancePanels;
	}

	/**
	 * Know the height of the Board according to all specifications.
	 * @return  height of the Board
	 */
	int getHeightBoard() {
		int sumHeight = 0;
		int maxHeightLine = 0;
		for (int a = 0; a != Board.length; a++) {
			for (int b = 0; b != Board[a].length; b++) {
				if (Board[a][b] != null && Board[a][b].getHeightPanel() > maxHeightLine) {
					maxHeightLine = Board[a][b].getHeightPanel();
				}
			}

			sumHeight += maxHeightLine;
			maxHeightLine = 0; // Reset max of line
		}

		return sumHeight + (Board.length - 1) * distancePanels;
	}

	/**
	 * Define the number of strips on the Board.
	 * @param strips Number of strips on the Board
	 */
	void setNumberOfStrips(int strips) {
		if(strips <= 0) {
			 throw new IllegalArgumentException("Please enter a valid strip value!");
		}

		this.strips = strips;
		Panel[][] newBoard = null;

		// FIRST CASE - add more strips
		if (this.strips > Board.length) {

			// Build Matrix
			newBoard = Board.buildMatrix(Board[0].length, this.strips);

			// Copy the previous Matrix state
			for (int a = 0; a != Board.length; a++) {
				for (int b = 0; b != Board[a].length; b++) {
					if (Board[a][b] != null) {
						newBoard[a][b] = Board[a][b];
					}
				}
			}

			// Add modifications
			for (int a = Board.length; a != newBoard.length; a++) {
				newBoard[a][0] = PanelDEFAULT;
			}

			Board = newBoard;
		}

		// SECOND CASE - Remove a number of strips
		else if (this.strips < Board.length) {

			// Build Matrix - add more strips
			newBoard = Board.buildMatrix(Board[0].length, this.strips);

			// Copy the previous Matrix state
			for (int a = 0; a != newBoard.length; a++) {
				for (int b = 0; b != newBoard[a].length; b++) {
					if (Board[a][b] != null) {
						newBoard[a][b] = Board[a][b];
					}
				}
			}

			Board = newBoard;
		}
	}

	/**
	 * Define the number of strips on the particular Panel.
	 * @param locationStrips Location Strip
	 * @param numberOfPanels Define number of Panels
	 */
	void setNumberOfPanels(int locationStrips, int numberOfPanels) {
		if(locationStrips < 0 || locationStrips >= Board.length) {
			 throw new IllegalArgumentException("Please enter a valid value for the row!");
		}
		else if(numberOfPanels <= 0) {
			throw new IllegalArgumentException("Please enter a valid value for the stickers!");
		}

		Panel[][] newBoard = null;

		// FIRST CASE - add more Panels
		if (numberOfPanels > Board[0].length) {

			// Build array
			newBoard = Board.buildMatrix(numberOfPanels, Board.length);

			// Copy the previous Matrix state
			for (int a = 0; a != Board.length; a++) {
				for (int b = 0; b != Board[a].length; b++) {
					if (Board[a][b] != null) {
						newBoard[a][b] = Board[a][b];
					}
				}
			}

			// Add modifications

			for (int b = 0; b != newBoard[locationStrips].length; b++) {
				if (newBoard[locationStrips][b] == null) {
					newBoard[locationStrips][b] = PanelDEFAULT;
				}
			}

			Board = newBoard;
		}

		// SECOND CASE - remove Panels

		else if (numberOfPanels <= Board[0].length) {

			int maximumPanels = 0;
			maximumPanels = Board.maximumPanelsStrip(Board, locationStrips, false);

			if (maximumPanels < numberOfPanels) {
				maximumPanels = numberOfPanels;
			}

			// Build array
			newBoard = Board.buildMatrix(maximumPanels, Board.length);

			// Copy the previous Matrix state
			for (int a = 0; a != newBoard.length; a++) {
				for (int b = 0; b != newBoard[a].length; b++) {
					if (Board[a][b] != null) {
						newBoard[a][b] = Board[a][b];
					}
				}
			}

			// Add modifications
			for (int b = 0; b != numberOfPanels; b++) {
				if (newBoard[locationStrips][b] == null) {
					newBoard[locationStrips][b] = PanelDEFAULT;
				}
			}

			for (int b = numberOfPanels; b != newBoard[0].length; b++) {
				newBoard[locationStrips][b] = null;
			}

			Board = newBoard;
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

		this.distancePanels = distance;
	}

	/**
	 * To replace a Panel image.
	 * @param xPosition Row position
	 * @param yPosition Column position
	 * @param v New Panel
	 */
	void setPanel(int xPosition, int yPosition, Panel v) {
		illegalPosition(xPosition, yPosition, false);

		Board[xPosition][yPosition] = new Panel(v.getImage(), v.getSizeFrame(), v.getTypeFrame(),
				v.getColorFrame());
	}

	/**
	 * To modify size frame of a particular Panel.
	 * @param xPosition Row Position
	 * @param yPosition Column Position
	 * @param sizeFrame Size Frame
	 */
	void setPanelSizeFrame(int xPosition, int yPosition, int sizeFrame) {
		illegalPosition(xPosition, yPosition, true);

		Board[xPosition][yPosition].setSizeFrame(sizeFrame);
	}

	/**
	 * To modify color frame of a particular Panel.
	 * @param xPosition Row Position
	 * @param yPosition Column position
	 * @param colorFrame Color Frame
	 */
	void setColorFramePanel(int xPosition, int yPosition, Color colorFrame) {
		illegalPosition(xPosition, yPosition, true);

		Board[xPosition][yPosition].setColorFrame(colorFrame);
	}

	/**
	 * Show Panel black and white image.
	 * @param xPosition Row position
	 * @param yPosition column
	 */
	void setBlackAndWhitePanel(int xPosition, int yPosition) {
		illegalPosition(xPosition, yPosition, true);

		Board[xPosition][yPosition].setBlackAndWhiteImage();
	}

	/**
	 * Show Panel color image.
	 * @param xPosition Row position
	 * @param yPosition Column position
	 */
	void setColorImagePanel(int xPosition, int yPosition) {
		illegalPosition(xPosition, yPosition, true);

		Board[xPosition][yPosition].setColorImage();
	}

	/**
	 * To modify the type frame of a Panel.
	 * @param xPosition Row position
	 * @param yPosition Column position
	 * @param typeFrame Type Frame
	 */
	void setTypePanel(int xPosition, int yPosition, int typeFrame) {
		illegalPosition(xPosition, yPosition, true);

		Board[xPosition][yPosition].setTypeFrame(typeFrame);
	}

	/**
	 * Operation to display image corresponding to the Board.
	 * @return New image corresponding to the board
	 */
	ColorImage getBoard() {

		ColorImage newImage = new ColorImage(this.getWidthBoard(), this.getHeightBoard());
		int jumpY = 0;
		int jumpX = 0;
		int maxHeight = 0;
		boolean access = true;

		for (int a = 0; a != Board.length; a++) {
			if (a != 0) {
				maxHeight = Board.maximumHeight(Board[a - 1]); // Width max
			}

			for (int b = 0; b != Board[a].length; b++) {
				if (access && a != 0) {
					jumpY += maxHeight + distancePanels;
					access = false;
				}

				if (Board[a][b] != null) {
					Utilities.pasteImage(newImage, Board[a][b].getPanel(), jumpX, jumpY);
					jumpX += Board[a][b].getWidthPanel() + distancePanels;
				}
			}

			jumpX = 0;
			access = true;
			maxHeight = 0;
		}

		return newImage;
	}

	/**
	 * This method allows you to split a Panel and create new Panel.
	 * @param xPosition Row position
	 * @param yPosition Column position
	 * @param value Division value
	 */
	void setDividirPanel(int xPosition, int yPosition, int value) {
		illegalPosition(xPosition, yPosition, true);

		if(value <= 0){
			throw new IllegalArgumentException("Please enter a positive value greater than zero.");
		}

		int currentSizeBoard = Board[xPosition].length;
		ColorImage imageBorderless = null;
		ColorImage[] images = new ColorImage[value];
		int jumps = 0;

		// Check if the image is Black and White.
		if (Board[xPosition][yPosition].getStateImagePanel()) {
			imageBorderless = Board[xPosition][yPosition].getImageBlackAndWhite();
		}
		else {
			imageBorderless = Board[xPosition][yPosition].getImage();
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

		// Know the number of default Panels.
		int firstnumberDefault = 0;
		for (int a = 0; a != Board[xPosition].length; a++) {
			if (Board[xPosition][a] == PanelDEFAULT) {
				firstnumberDefault++;
			}
		}

		// Execution of the various changes in the Board.

		this.setNumberOfPanels(xPosition, currentSizeBoard + value - 1);
		Panel[] v = new Panel[value];


		for (int a = 0; a != v.length; a++) {
			v[a] = new Panel(images[a], Board[xPosition][yPosition].getSizeFrame(),
					Board[xPosition][yPosition].getTypeFrame(), Board[xPosition][yPosition].getColorFrame());
		}

		// Algorithm to maintain the state of the elements that will not be modified.
		for (int b = currentSizeBoard + value - 2; b != yPosition + value - 1; b--) {
			Board[xPosition][b] = Board[xPosition][b - value + 1];
		}

		int i = 0;
		for (int a = yPosition; a != yPosition + value; a++) {
			Board[xPosition][a] = v[i];
			i++;
		}

		int secondNumberDefault = 0;
		for (int a = 0; a != Board[xPosition].length; a++) {
			if (Board[xPosition][a] == PanelDEFAULT) {
				secondNumberDefault++;
			}
		}

		// Remove number of vignettes in excess.
		if (secondNumberDefault > firstnumberDefault) {
			boolean access = true;
			for (int a = Board[xPosition].length - 1; a != -1 && access; a--) {
				if (Board[xPosition][a] == PanelDEFAULT) {
					Board[xPosition][a] = null;
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
	 * @param compareObject If need compare a Panel default
	 */
	private void illegalPosition(int xPosition, int yPosition, boolean compareObject) {
		if((xPosition < 0 || xPosition >= Board.length || yPosition < 0 || yPosition >= Board[0].length)){
			throw new IllegalArgumentException("Please enter a valid value for the row or column!");
		}
		else if(Board[xPosition][yPosition] == PanelDEFAULT && compareObject) {
			throw new IllegalArgumentException("First step: Insert a Panel");
		}
		else if(Board[xPosition][yPosition] == null && !compareObject){
			throw new IllegalArgumentException("First step: Insert a valid Panel");
		}
	}

	/**
	 * To know the maximum height of a sticker.
	 * @param v Panel
	 * @return The maximum height.
	 */
	static int maximumHeight(Panel[] v) {
		int maxHeight = 0;

		for (int a = 0; a != v.length; a++) {
			if (v[a] != null && v[a].getHeightPanel() > maxHeight) {
				maxHeight = v[a].getHeightPanel();
			}
		}
		return maxHeight;
	}

	/**
	 * To know the maximum number of Panels on a strip or the number of Panels on a strip.
	 * @param v Board.
	 * @param locationLine Row to know the number of stickers.
	 * @param access To know the number of Panels on the strip itself.
	 * @return The maximum number of Panels.
	 */
	static int maximumPanelsStrip(Panel[][] v, int locationLine, boolean access) {
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
	static Panel[][] buildMatrix(int width, int height) {
		Panel[][] v = new Panel[height][width];
		for (int a = 0; a != v.length; a++) {
			for (int b = 0; b != v[a].length; b++) {
				v[a][b] = null;

			}
		}
		return v;
	}
}
