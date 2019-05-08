## Comic Generator ![Image](https://img.shields.io/badge/license-EPL--2.0-blue.svg)![Image](https://img.shields.io/badge/status-stable-green.svg)
This project allows to **generate a comics** and uses as base the project [AguiaJ](https://github.com/andre-santos-pt/aguiaj).

<img src="https://raw.githubusercontent.com/dominoesbase/comic-generator/master/resources/images/plpl.png" width="300"/>

**Panels** and **tiers** make up a **board**.

## How to Use

### Build a Panel
Given a certain image, the **panel** is obtained through the Panel constructor.

```java
/**
 * Build a new Panel.
 * @param image - Image Panel
 * @param sizeFrame - Frame size
 * @param typeFrame - Frame type
 * @param colorFrame - Frame color
 */

Panel p = new Panel(ColorImage image, int sizeFrame, int typeFrame, Color colorFrame);
```
Build a **new Panel** with frame black by default.

```java
/**
 * Build a new Panel with black frame by default.
 * @param image - Image Panel
 * @param sizeFrame - Frame size
 * @param typeFrame - Frame type
 */

Panel pBlack = new Panel(ColorImage image, int sizeFrame, int typeFrame);
```
#### Frame Type - Information
**Frame Type** receives the integer value **a**, where **a** is an element of the set **A = {0, 1, 2, 3}**. The integer define the Frame Type.
<img src="https://raw.githubusercontent.com/dominoesbase/comic-generator/master/resources/images/frame_type.png" width="600"/>

### Build a Board
Given a set of **Panels** it is possible to generate a **Board**.

```java
/**
 * Build a Board of a CS.
 * @param strips - Number of strips
*/

Board b = new Board(int strips);
```
To insert a **Panel** in a certain position of the **Board**, you must
indicate the position on the Board with **setPanel**.

```java
void setPanel(int xPosition, int yPosition, Panel v)
```

#### Build a Board - Example
<img src="https://raw.githubusercontent.com/dominoesbase/comic-generator/master/resources/images/sapinho.png" width="300"/>

```java
Board p = new Board(6);

// Insert panel frog on p
p.setPanel(0, 1, frog);

// Insert panel car on p
p.setPanel(1, 2, car);
```

## Other Features

### PANEL

Get Panel Height.

```java
int getHeightPanel()
```

Get Panel Width

```java
int getWidthPanel()
```

Get image embedded in the Panel.

```java
ColorImage getImage()
```

Get frame size.

```java
int getSizeFrame()
```

Get frame type.

```java
int getTypeFrame()
```

Get frame color.

```java
Color getColorFrame()
```

Convert image color to black and white image.

```java
ColorImage getImageBlackAndWhite()
```

Get the current image state.

```java
boolean getStateImagePanel()
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

Set black and white image Panel.

```java
setBlackAndWhiteImage()
```
Set color image Panel.

```java
setColorImage()
```
Get Panel according all specifications.

```java
ColorImage getPanel()
```
### BOARD

Get width of the Board according all specifications.

```java
int getWidthBoard()
```

Get height of the Board according all specifications.

```java
int getHeightBoard()
```

Define the number of Panels on the Board.

```java
void setNumberOfStrips(int strips)
```

Define number of strips on the particular Panel.

```java
setNumberOfPanels(int locationStrips, int numberOfPanels)
```

Change Panels distance.

```java
void setDistance(int distance)
```

Replace a Panel image.

```java
void setPanel(int xPosition, int yPosition, Panel v)
```

Modify frame size of a particular Panel.

```java
void setPanelSizeFrame(int xPosition, int yPosition, int sizeFrame)
```

Modify frame color of a particular Panel.

```java
void setColorFramePanel(int xPosition, int yPosition, Color colorFrame)
```

Show Panel with image black and white.

```java
void setBlackAndWhitePanel(int xPosition, int yPosition)
```

Show Panel with real image color.

```java
void setColorImagePanel(int xPosition, int yPosition)
```

Modify the frame type of a Panel.

```java
void setTypePanel(int xPosition, int yPosition, int typeFrame)
```

 Get image associated with the board.

```java
ColorImage getBoard()
```

This method allows you to split a Panel and create new Panel.

```java
setDividirPanel(int xPosition, int yPosition, int value)
```

Get the maximum height of a tier.

```java
int maximumHeight(Panel[] v)
```

Get the maximum number of Panels on a strip or the number of Panels on a strip.

```java
int maximumPanelsStrip(Panel[][] v, int locationLine, boolean access)
```

Build new Matrix
```java
Panel[][] buildMatrix(int width, int height)
```

## Next Steps
Build a new version without AguiaJ.

## AguiaJ
More information about the [AguiaJ project](https://github.com/andre-santos-pt/aguiaj).

## Author
* [dominoesbase](https://twitter.com/jorgedominoes)
