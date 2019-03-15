## Comic Generator
This project allows to **generate a comics** and uses as base the project [AguiaJ](https://github.com/andre-santos-pt/aguiaj).
![image](https://drive.google.com/file/d/1kjCp5tf4qXcpoYt_H60tuV-Sl9itoiyq/view?usp=sharing)

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

### Other Features

## Next Steps
Update all information to English and build a new version without using AguiaJ.

## AguiaJ
More information about the [AguiaJ project](https://github.com/andre-santos-pt/aguiaj).

## Author
* [dominoesbase](https://twitter.com/jorgedominoes)
