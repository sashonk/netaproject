package com.me.neta.util;

import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.graphics.Color;

public class ColorHelper {
	static 	Color[][] colors = new Color[5][6];
	static List<Color> darkColors;
	static {
		colors[0][0] = Color.RED;
		colors[0][1] = new Color(1f, 153f/255f, 153f/ 255f, 1);
		colors[0][2] = new Color(1f, 204/255f, 204/ 255f, 1);
		colors[0][3] = new Color(102/255f,0, 102/ 255f, 1);
		colors[0][4] = new Color(153/255f,0, 153/ 255f, 1);
		colors[0][5] = new Color(204/255f,0, 204/ 255f, 1);
		
		
		colors[1][0] = new Color(255  /255f, 153  /255f, 51  /255f, 1);
		colors[1][1] = new Color(255  /255f, 204  /255f, 102  /255f, 1);
		colors[1][2] = new Color(255  /255f, 204  /255f, 153  /255f, 1);
		colors[1][3] = new Color(102  /255f, 51  /255f, 0  /255f, 1);
		colors[1][4] = new Color(153  /255f, 102  /255f, 51  /255f, 1);
		colors[1][5] = new Color(204  /255f, 153  /255f, 102  /255f, 1);
		
				 
		colors[2][0] = new Color(255  /255f, 255  /255f, 0  /255f, 1);
		colors[2][1] = new Color(255  /255f, 255  /255f, 102  /255f, 1);
		colors[2][2] = new Color(255  /255f, 255  /255f, 153  /255f, 1);
		colors[2][3] = new Color(51  /255f, 153  /255f, 204  /255f, 1);
		colors[2][4] = new Color(0  /255f, 204  /255f, 255  /255f, 1);
		colors[2][5] = new Color(0  /255f, 255  /255f, 255  /255f, 1);

		
		colors[3][0] = new Color(0  /255f, 255  /255f, 0  /255f, 1);
		colors[3][1] = new Color(153  /255f, 255  /255f, 153  /255f, 1);
		colors[3][2] = new Color(204  /255f, 255  /255f, 204  /255f, 1);
		colors[3][3] = new Color(0  /255f, 0  /255f, 0  /255f, 1);
		colors[3][4] = new Color(102  /255f, 102  /255f, 102  /255f, 1);
		colors[3][5] = new Color(204 /255f, 204  /255f, 204  /255f, 1);

				
		colors[4][0] = new Color(0  /255f, 0  /255f, 255  /255f, 1);
		colors[4][1] = new Color(102  /255f, 102  /255f, 255  /255f, 1);
		colors[4][2] = new Color(204  /255f, 204  /255f, 255  /255f, 1);
		colors[4][3] = Color.WHITE;
		colors[4][4] = new Color(51  /255f, 51  /255f, 51  /255f, 1);
		colors[4][5] = new Color(51 /255f, 0  /255f, 0  /255f, 1);
	
		darkColors = Arrays.asList(new Color[]{colors[0][0], colors[0][3], colors[1][3], colors[3][3], colors[4][0], colors[4][4], colors[4][5]});

	}
	
	public static Color[][] getColors(){return colors;}
	
	public static boolean isDark(Color c){
		return darkColors.contains(c);
	}
}
