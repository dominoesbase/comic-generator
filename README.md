## Comic Generator ![Image](https://img.shields.io/badge/license-EPL--2.0-blue.svg)![Image](https://img.shields.io/badge/status-unstable-red.svg)
This project allows to **generate a comics** and uses as base the project [AguiaJ](https://github.com/andre-santos-pt/aguiaj).

<img src="https://raw.githubusercontent.com/dominoesbase/comic-generator/master/resources/plpl.png" width="200"/>

**Panels** and **tiers** make up a **board**.

## How to Use

### Build a Panel
Given a certain image, the **panel** is obtained through the Vinheta builder.

```java
/**
 * Build a new Panel.
 * @param image - Image Panel
 * @param sizeFrame - Size Panel
 * @param typeFrame - Type Frame Panel
 * @param colorFrame - Color Frame Panel
 */
Vinheta v = new Vinheta(ColorImage image, int sizeFrame, int typeFrame, Color colorFrame);
```
Build a **new Panel** with frame black by default.

```java
/**
 * Build a new Panel with frame black by default.
 * @param image -  Imagem Vinheta
 * @param sizeFrame - Size Vinheta
 * @param typeFrame - Type Vinheta
 */

 Vinheta vBlack = new Vinheta(ColorImage image, int sizeFrame, int typeFrame);
```
### Build a Board
Given a set of Panels it is possible to generate a Board.

```java
/**
 * Build a Prancha of a CS.
 * @param strips - Number of strips
*/

Prancha b = new Prancha(int strips);
```
To insert a **Panel** in a certain position of the **Board**, you must
indicate the position on the Board with **setVinheta**.

```java
void setVinheta(int xPosition, int yPosition, Vinheta v)
```

#### Build a Board - Example
<img src="https://raw.githubusercontent.com/dominoesbase/comic-generator/master/resources/sapinho.png" width="200"/>

```java
Prancha p = new Prancha(...);

// Insert panel frog on p
p.setVinheta(0, 1, Vinheta frog);
```

## Other Features
### Panel

Know the height of the Panel.

```java
int getHeightVinheta()
```

Know the width of the Panel.

```java
int getWidthVinheta()
```

Returns image embedded in the Panel.

```java
ColorImage getImage()
```

Know the size frame.

```java
int getSizeFrame()
```

Know the type frame.

```java
int getTypeFrame()
```

Know the color frame.

```java
Color getColorFrame()
```

Know the black and white image.

```java
ColorImage getImageBlackAndWhite()
```

Know the current image state.

```java
boolean getStateImageVinheta()
```

Change size frame Panel.

```java
void setSizeFrame(int size)
```

Change color frame Panel.

```java
void setColorFrame(Color color)
```

Change type frame Panel.

```java
setTypeFrame(int type)
```

Show black and white image Panel.

```java
setBlackAndWhiteImage()
```
Show color image Panel.

```java
setColorImage()
```
Return the sticker according to all specifications.

```java
ColorImage getVinheta()
```
### BOARD

Know the width of the Board according to all specifications.

```java
int getWidthPrancha()
```

Know the height of the Board according to all specifications.

```java
int getHeightPrancha()
```

Define the number of Panels on the Board.

```java
void setNumberOfStrips(int strips)
```

Define the number of Panels on the particular Panel.

```java
setNumberOfVinhetas(int locationStrips, int numberOfVinhetas)
```

To change the distance of the Panels.

```java
void setDistance(int distance)
```

To replace a Panel image.

```java
void setVinheta(int xPosition, int yPosition, Vinheta v)
```

To modify size frame of a particular Panel.

```java
void setVinhetaSizeFrame(int xPosition, int yPosition, int sizeFrame)
```

To modify color frame of a particular Panel.

```java
void setColorFrameVinheta(int xPosition, int yPosition, Color colorFrame)
```

Show image black and white panel.

```java
void setBlackAndWhiteVinheta(int xPosition, int yPosition)
```

Show Panel color image.

```java
void setColorImageVinheta(int xPosition, int yPosition)
```

To modify the type frame of a Panel.

```java
void setTypeVinheta(int xPosition, int yPosition, int typeFrame)
```

Operation to display image corresponding to the Board.

```java
ColorImage getPrancha()
```

This method allows you to split a Panel and create new Panel.

```java
setDividirVinheta(int xPosition, int yPosition, int value)
```

To know the maximum height of a tier.

```java
int maximumHeight(Vinheta[] v)
```

To know the maximum number of Panels on a tier or the number of Panels on a tier.

```java
int maximumVinhetasStrip(Vinheta[][] v, int locationLine, boolean access)
```

Build new Matrix
```java
Vinheta[][] buildMatrix(int width, int height)
```

## Next Steps
Update all information to English and build a new version without AguiaJ.

## AguiaJ
More information about the [AguiaJ project](https://github.com/andre-santos-pt/aguiaj).

## Author
* [dominoesbase](https://twitter.com/jorgedominoes)
