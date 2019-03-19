/*******************************************************************************
* Copyright (c) 2019 Jorge V. Rodrigues.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v2.0
* which accompanies this distribution.
*
* Contributors:
*     Jorge V. Rodrigues - developer
 ******************************************************************************/
import java.util.Random;
import aguiaj.iscte.*;

class Panel {

	private ColorImage image;
	private ColorImage blackAndWhite;
	private int sizeFrame;
	private int typeFrame;
	private Color colorFrame;
	private boolean isBlackWhite = false;

	private static final int WITHOUTFRAME = 0;
	private static final int LINEFRAME = 1;
	private static final int DASHEDFRAME = 2;
	private static final int DRAWFRAME = 3;


	/**
	 * Build a new Panel.
	 * @param image - Image Panel
	 * @param sizeFrame - Frame size
	 * @param typeFrame - Frame type
	 * @param colorFrame - Frame color
	 */
	Panel(ColorImage image, int sizeFrame, int typeFrame, Color colorFrame) {

		if(image == null) {
			throw new NullPointerException("Please insert an image!");
		}
		else if(colorFrame == null) {
			throw new NullPointerException("Please insert a color!");
		}
		else if(sizeFrame < 0) {
			 throw new IllegalArgumentException("The size frame is greater or equal 0!");
		}
		else if(typeFrame < 0 || typeFrame > 3) {
			throw new IllegalArgumentException("The type frame is 0, 1, 2 or 3!");
		}

		this.image = image;
		this.sizeFrame = sizeFrame;
		this.typeFrame = typeFrame;
		this.colorFrame = colorFrame;
		isBlackWhite = false;

		blackAndWhite = new ColorImage(image.getWidth(), image.getHeight());

		// Build black and white image
		for(int x = 0; x != this.image.getWidth(); x++) {
			for(int y = 0; y != this.image.getHeight(); y++) {
				int luminan
				ce = image.getColor(x, y).getLuminance();
				Color luminanceColor = new Color(luminance, luminance, luminance);
				blackAndWhite.setColor(x, y, luminanceColor);;
			}
		}
	}

	/**
	 * Build a new Panel with black frame by default.
	 * @param image - Image Panel
	 * @param sizeFrame - Frame size
	 * @param typeFrame - Frame type
	 */
	Panel(ColorImage image, int sizeFrame, int typeFrame) {
		this(image, sizeFrame, typeFrame, Color.BLACK);
	}

	/**
	 * Get Panel Height.
	 * @return Height Panel
	 */
	int getHeightPanel() {
		return image.getHeight() + 2*sizeFrame;
	}

	/**
	 * Get Panel Width
	 * @return Width Panel
	 */
	int getWidthPanel() {
		return image.getWidth() + 2*sizeFrame;
	}

	/**
	 * Get image embedded in the Panel.
	 * @return image
	 */
	ColorImage getImage() {
		return image;
	}

	/**
	 * Get frame size.
	 * @return size frame
	 */
	int getSizeFrame() {
		return sizeFrame;
	}

	/**
	 * Get frame type.
	 * @return type frame
	 */
	int getTypeFrame() {
		return typeFrame;
	}

	/**
	 * Get frame color.
	 * @return color frame
	 */
	Color getColorFrame() {
		return colorFrame;
	}

	/**
	 * Convert image color to black and white image.
	 * @return black and white image
	 */
	ColorImage getImageBlackAndWhite() {
		return blackAndWhite;
	}

	/**
	 * Get the current image state.
	 * @return if image is black and white
	 */
	boolean getStateImagePanel() {
		return isBlackWhite;
	}

	/**
	 * Change size frame Panel.
	 * @param size - New size Frame
	 */
	void setSizeFrame(int size) {
		if(size < 0) {
			 throw new IllegalArgumentException("The size frame is greater or equal 0!");
		}

		sizeFrame = size;
	}

	/**
	 * Change color frame Panel.
	 * @param color - New color frame
	 */
	void setColorFrame(Color color) {
		if(color == null) {
			throw new NullPointerException("Please enter a color!");
		}

		colorFrame = color;
	}

	/**
	 * Change type frame Panel.
	 * @param type - New type frame
	 */
	void setTypeFrame(int type) {
		if(type < 0 || type > 3) {
			throw new IllegalArgumentException("The type frame is 0, 1, 2 or 3!");
		}

		typeFrame = type;
	}

	/**
	 * Set black and white image Panel.
	 */
	void setBlackAndWhiteImage() {
		isBlackWhite = true;
	}

	/**
	 * Set color image Panel.
	 */
	void setColorImage() {
		isBlackWhite = false;
	}

	/**
	 * Get Panel according all specifications.
	 * @return new Panel
	 */
	ColorImage getPanel() {
		ColorImage Panel = new ColorImage(image.getWidth() + 2*sizeFrame,
											image.getHeight() + 2*sizeFrame);

		if(!isBlackWhite) {
			Utilities.pasteImage(Panel, image, sizeFrame, sizeFrame);
		}
		else {
			Utilities.pasteImage(Panel, blackAndWhite, sizeFrame, sizeFrame);
		}

		// Type frame - 0
		if(typeFrame == WITHOUTFRAME) {
			// Horizontal Line

			for(int x = 0; x != sizeFrame; x++) {
				for(int y = 0; y != Panel.getHeight(); y++) {
					Panel.setColor(x, y, Color.WHITE);
					Panel.setColor(Panel.getWidth() - x - 1, y, Color.WHITE);
				}
		    }

			// Vertical Line
			for(int x = 0; x != Panel.getWidth(); x++) {
				for(int y = 0; y != sizeFrame; y++) {
					Panel.setColor(x, y, Color.WHITE);
					Panel.setColor(x, Panel.getHeight() - 1 - y, Color.WHITE);
				}
			}
		}

		// Type frame - 1
		else if(typeFrame == LINEFRAME) {

			// Horizontal Line
			for(int x = 0; x != sizeFrame; x++) {
				for(int y = 0; y != Panel.getHeight(); y++) {
					Panel.setColor(x, y, colorFrame);
					Panel.setColor(Panel.getWidth() - x - 1, y, colorFrame);
				}
		    }

			// Vertical Line
			for(int x = 0; x != Panel.getWidth(); x++) {
				for(int y = 0; y != sizeFrame; y++) {
					Panel.setColor(x, y, colorFrame);
					Panel.setColor(x, Panel.getHeight() - 1 - y, colorFrame);
				}
			}
		}

		// Type frame - 2
		else if(typeFrame == DASHEDFRAME) {

			// Algorithm Width
			double widthImage = image.getWidth();
			double constantWidth = Math.round(widthImage/2*sizeFrame);
			int expressionWidth = (int)Math.abs((widthImage - constantWidth*sizeFrame)/constantWidth);

			//Algorithm Height
			double heightImage = image.getHeight();
			double constantHeight = Math.round(heightImage/2*sizeFrame);
			int expressionHeight = (int)Math.abs((heightImage - constantHeight*sizeFrame)/constantHeight);

			Utilities.fillSquare(Panel, 0, 0, sizeFrame, colorFrame);
			Utilities.fillSquare(Panel, Panel.getWidth() - sizeFrame, 0, sizeFrame, colorFrame);
			Utilities.fillSquare(Panel, 0, Panel.getHeight() - sizeFrame, sizeFrame, colorFrame);
			Utilities.fillSquare(Panel, Panel.getWidth() - sizeFrame, Panel.getHeight() - sizeFrame, sizeFrame, colorFrame);

			// Horizontal Effect
			for(int x = sizeFrame + expressionWidth + 3; x < widthImage; x += sizeFrame + expressionWidth) {
				   Utilities.fillSquare(Panel, x, 0, sizeFrame, colorFrame);
				   Utilities.fillSquare(Panel, x, Panel.getHeight() - sizeFrame, sizeFrame, colorFrame);
			}

			//Vertical Effect
			for(int y = sizeFrame + expressionHeight; y < heightImage ; y += sizeFrame + expressionHeight) {
				   Utilities.fillSquare(Panel, 0, y, sizeFrame, colorFrame);
				   Utilities.fillSquare(Panel, Panel.getWidth() - sizeFrame, y, sizeFrame, colorFrame);
			}
		}

		// Type frame - 3
		else if(typeFrame == DRAWFRAME){
			Random randomizer = new Random();
			boolean access = false;

			// Horizontal Line
			for(int x = 0; x != Panel.getWidth(); x++){
				for(int y = 0; y != sizeFrame; y++){
					access = randomizer.nextBoolean();
					if(access){
						Panel.setColor(x, y, colorFrame);
					}
					else{
						Panel.setColor(x, Panel.getHeight() - sizeFrame + y, colorFrame);
					}
				}
			}

			// Vertical Line
			for(int y = sizeFrame; y != Panel.getHeight() - sizeFrame; y++){
				for(int x = 0; x != sizeFrame; x++){
					access = randomizer.nextBoolean();
					if(access){
						Panel.setColor(x, y, colorFrame);
					}
					else{
						Panel.setColor(Panel.getWidth() - sizeFrame + x, y, colorFrame);
					}
				}
			}
		}
		return Panel;
	}
}
