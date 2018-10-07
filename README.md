# Introduction
A gift-wrapping (Jarvis march) algorithm to compute the non-dominated points of an input
point set S = {p<sub>1</sub>, ..., p<sub>n</sub>} in the plane. The program allow input by mouse, read from a text File, and randomly generated. The output is shown graphically, and a list of the non-dominated points in ccw order. Tried to animate the algorithm to make it educational. It is able to control the speed of animation.

The code is written by Java. It includes following classes:   

public class domicated_point:     
>contains the main function;

class MyFrame extends JFrame implements ActionListener:  
>create the window for visualization;   

class MyPanel extends JPanel:   
>call the function for different way of input and painting;   

class MyMouseListener extends MouseAdapter:
>Listen to the mouse event.   

   
# Features    
## Input    
There are three ways to input data    
1. Random points generation;    
2. Input File;    
3. Mouse input    
We can also combine 1 and 3 or 2 and 3.

## Output    
1. Color all the nondominated points red;
2. Color all the dominated points grey;
3. Show the nondominated points one by one, in the order of ccw;
4. Each time when a dominated point is shown, all points are dominated by it are also shown;
5. All the nondominated points are written in an output File, sorted in ccw order.

## Animation feature    
The process of coloring points is step by step and it's allowed to control the speed to color. Using the button "Delay", one can control the speed as he wants.

## Input size    
For the random generating method, an appropriate size of input is related to the size of the window,
considering too many points will lead to a vague visualization, because of the overlapped points. However, it will not make an influence of the output, since for the repeated points, we only count it once. In addition, the window is adjustable. Maximizing the size of the window can also make the vision clear.