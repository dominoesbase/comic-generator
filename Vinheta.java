/*******************************************************************************
* Copyright (c) 2014 Andre L. Santos.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v2.0
* which accompanies this distribution.
*
* Contributors:
*     Jorge V. Rodrigues - developer
 ******************************************************************************/
import java.util.Random;
import aguiaj.iscte.*;

class Vinheta {

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
	 * Build a new Vinheta.
	 * @param image Image Vinheta
	 * @param sizeFrame Size Vinheta
	 * @param typeFrame Type Frame Vinheta
	 * @param colorFrame Color Frame Vinheta
	 */
	Vinheta(ColorImage image, int sizeFrame, int typeFrame, Color colorFrame) {

		if(image == null) {
			throw new NullPointerException("Please enter an image!");
		}
		else if(colorFrame == null) {
			throw new NullPointerException("Please enter a color!");
		}
		else if(sizeFrame < 0) {
			 throw new IllegalArgumentException("The size frame is greater or equal 0!");
		}
		else if(typeFrame < 0 || typeFrame > 3) {
			throw new IllegalArgumentException("The type frame is 0, 1,2 or 3!");
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
				int luminance = image.getColor(x, y).getLuminance();
				Color luminanceColor = new Color(luminance, luminance, luminance);
				blackAndWhite.setColor(x, y, luminanceColor);;
			}
		}
	}

	/**
	 * Build a new Vinheta with with frame black by default.
	 * @param imagemVinheta Imagem Vinheta
	 * @param tamanhoMoldura Size Vinheta
	 * @param tipoMoldura Type Vinheta
	 */
	Vinheta(ColorImage image, int sizeFrame, int typeFrame) {
		this(image, sizeFrame, typeFrame, Color.BLACK);
	}

	/**
	 * Know the height of the Vinheta.
	 * @return Height Vinhets
	 */
	int getHeightVinheta() {
		return image.getHeight() + 2*sizeFrame;
	}

	/**
	 * Know the width of the Vinheta.
	 * @return Width vinheta
	 */
	int getWidthVinheta() {
		return image.getWidth() + 2*sizeFrame;
	}

	/**
	 * Returns image embedded in the vinheta.
	 * @return image
	 */
	ColorImage getImage() {
		return image;
	}

	/**
	 * Know the size frame.
	 * @return size frame
	 */
	int getSizeFrame() {
		return sizeFrame;
	}

	/**
	 * Know the type frame.
	 * @return type frame
	 */
	int getTypeFrame() {
		return typeFrame;
	}

	/**
	 * Know the color frame.
	 * @return color frame
	 */
	Color getColorFrame() {
		return colorFrame;
	}

	/**
	 * Know the black and white image.
	 * @return black and white image
	 */
	ColorImage getImageBlackAndWhite() {
		return blackAndWhite;
	}

	/**
	 * Know the current image state.
	 * @return if image is black and white
	 */
	boolean getStateImageVinheta() {
		return isBlackWhite;
	}

	/**
	 * Change size frame Vinheta.
	 * @param size New size Frame
	 */
	void setSizeFrame(int size) {
		if(size < 0) {
			 throw new IllegalArgumentException("The size frame is greater or equal 0!");
		}

		sizeFrame = size;
	}

	/**
	 * Change color frame Vinheta.
	 * @param color New color frame
	 */
	void setColorFrame(Color color) {
		if(color == null) {
			throw new NullPointerException("Please enter a color!");
		}

		colorFrame = color;
	}

	/**
	 * Change type frame Vinheta.
	 * @param type New type frame
	 */
	void setTypeFrame(int type) {
		if(type < 0 || type > 3) {
			throw new IllegalArgumentException("The type frame is 0, 1,2 or 3!");
		}

		typeFrame = type;
	}

	/**
	 * Show black and white image Vinheta.
	 */
	void setBlackAndWhiteImage() {
		isBlackWhite = true;
	}

	/**
	 * Show color image Vinheta.
	 */
	void setColorImage() {
		isBlackWhite = false;
	}

	/**
	 * Construct the sticker according to all specifications.
	 * @return new Vinheta
	 */
	ColorImage getVinheta() {
		ColorImage vinheta = new ColorImage(image.getWidth() + 2*sizeFrame,
											image.getHeight() + 2*sizeFrame);

		if(!isBlackWhite) {
			Utilities.pasteImage(vinheta, image, sizeFrame, sizeFrame);
		}
		else {
			Utilities.pasteImage(vinheta, blackAndWhite, sizeFrame, sizeFrame);
		}

		// Type frame - 0
		if(typeFrame == WITHOUTFRAME) {
			// Horizontal Line

			for(int x = 0; x != sizeFrame; x++) {
				for(int y = 0; y != vinheta.getHeight(); y++) {
					vinheta.setColor(x, y, Color.WHITE);
					vinheta.setColor(vinheta.getWidth() - x - 1, y, Color.WHITE);
				}
		    }

			// Vertical Line
			for(int x = 0; x != vinheta.getWidth(); x++) {
				for(int y = 0; y != sizeFrame; y++) {
					vinheta.setColor(x, y, Color.WHITE);
					vinheta.setColor(x, vinheta.getHeight() - 1 - y, Color.WHITE);
				}
			}
		}

		// Type frame - 1
		else if(typeFrame == LINEFRAME) {

			// Horizontal Line
			for(int x = 0; x != sizeFrame; x++) {
				for(int y = 0; y != vinheta.getHeight(); y++) {
					vinheta.setColor(x, y, colorFrame);
					vinheta.setColor(vinheta.getWidth() - x - 1, y, colorFrame);
				}
		    }

			// Vertical Line
			for(int x = 0; x != vinheta.getWidth(); x++) {
				for(int y = 0; y != sizeFrame; y++) {
					vinheta.setColor(x, y, colorFrame);
					vinheta.setColor(x, vinheta.getHeight() - 1 - y, colorFrame);
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

			Utilities.fillSquare(vinheta, 0, 0, sizeFrame, colorFrame);
			Utilities.fillSquare(vinheta, vinheta.getWidth() - sizeFrame, 0, sizeFrame, colorFrame);
			Utilities.fillSquare(vinheta, 0, vinheta.getHeight() - sizeFrame, sizeFrame, colorFrame);
			Utilities.fillSquare(vinheta, vinheta.getWidth() - sizeFrame, vinheta.getHeight() - sizeFrame, sizeFrame, colorFrame);

			// Horizontal Effect
			for(int x = sizeFrame + expressionWidth + 3; x < widthImage; x += sizeFrame + expressionWidth) {
				   Utilities.fillSquare(vinheta, x, 0, sizeFrame, colorFrame);
				   Utilities.fillSquare(vinheta, x, vinheta.getHeight() - sizeFrame, sizeFrame, colorFrame);
			}

			//Vertical Effect
			for(int y = sizeFrame + expressionHeight; y < heightImage ; y += sizeFrame + expressionHeight) {
				   Utilities.fillSquare(vinheta, 0, y, sizeFrame, colorFrame);
				   Utilities.fillSquare(vinheta, vinheta.getWidth() - sizeFrame, y, sizeFrame, colorFrame);
			}
		}

		// Type frame - 3
		else if(typeFrame == DRAWFRAME){
			Random randomizer = new Random();
			boolean access = false;

			// Horizontal Line
			for(int x = 0; x != vinheta.getWidth(); x++){
				for(int y = 0; y != sizeFrame; y++){
					access = randomizer.nextBoolean();
					if(access){
						vinheta.setColor(x, y, colorFrame);
					}
					else{
						vinheta.setColor(x, vinheta.getHeight() - sizeFrame + y, colorFrame);
					}
				}
			}

			// Vertical Line
			for(int y = sizeFrame; y != vinheta.getHeight() - sizeFrame; y++){
				for(int x = 0; x != sizeFrame; x++){
					access = randomizer.nextBoolean();
					if(access){
						vinheta.setColor(x, y, colorFrame);
					}
					else{
						vinheta.setColor(vinheta.getWidth() - sizeFrame + x, y, colorFrame);
					}
				}
			}
		}
		return vinheta;
	}

}
