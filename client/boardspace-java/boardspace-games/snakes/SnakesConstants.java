/*
	Copyright 2006-2023 by Dave Dyer

    This file is part of the Boardspace project.
    
    Boardspace is free software: you can redistribute it and/or modify it under the terms of 
    the GNU General Public License as published by the Free Software Foundation, 
    either version 3 of the License, or (at your option) any later version.
    
    Boardspace is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
    without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
    See the GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along with Boardspace.
    If not, see https://www.gnu.org/licenses/. 
 */
package snakes;

import lib.CellId;

import online.game.BaseBoard.BoardState;

public interface SnakesConstants 
{	static final int DEFAULT_COLUMNS = 12;	// 8x6 board
	static final int DEFAULT_ROWS = 12;
	static final String Snakes_INIT = "snakes";	//init for standard game

    enum SnakeId implements CellId
    {
    	Snake_Pool, // positive numbers are trackable
    	InTheAir,
    	BoardLocation ,
    	RotateLocation,
    	PatternLocation,
    	HeadsLocation,
    	SaveGivensLocation,;
    	public String shortName() { return(name()); }
    }
    public enum SnakeState implements BoardState
    {	PUZZLE_STATE(PuzzleStateDescription),
    	RESIGN_STATE(ResignStateDescription),
    	GAMEOVER_STATE(GameOverStateDescription),
    	PLAY_STATE("Place a tile on the board"),
    	DRAW_STATE(DrawStateDescription),
    	CONFIRM_STATE(ConfirmStateDescription);
    	String description;
    	SnakeState(String des) { description = des; }
    	public String getDescription() { return(description); }
    	public boolean GameOver() { return(this==GAMEOVER_STATE); }
    	public boolean Puzzle() { return(this==PUZZLE_STATE); } public boolean simultaneousTurnsAllowed() { return(false); }
    }

    enum TileType { center("c"),edge("e"),horizontal("h"),vertical("v") ;
    	TileType(String s) 
    		{ ss = s;
     		}
    	String ss = null;
     	public String toShortString() { return(ss); }
    	public static TileType getPlacedRole(TileType tile,int rot)
    	{
    		return(tile==null?null:tile.getPlacedRole(rot));
    	}
    	public TileType getPlacedRole(int rot)
    	{
    		if((rot&1)!=0)
    		{	switch(this)
    			{
    			case horizontal: return(vertical);
    			case vertical: return(horizontal);
    			default: ;
    			}
    		}
    		return(this);
    	}
    	};
    enum CellType { 
    	head("H"), tail("T"), body("B"), blank("");
    	String ss;
    	CellType(String ss) { this.ss = ss; }
    	String toShortString() {return(ss);}
    }
    enum targetType {
    	none(0,0,"No Target"),
    	two_two(2,2,"2x2"),		// puzzle 1
    	three_two(3,2,"3x2"),	// puzzle 2
    	two_four(2,4,"2x4"),	// puzzle 3
    	three_three(3,3,"3x3"),	// puzzle 4
    	four_three(4,3,"4x3"),	// puzzle 5
    	four_four(4,4,"4x4"),	// puzzle 6
    	five_three(5,3,"5x3"),	// puzzle 7
    	five_four(5,4,"5x4"),	// puzzle 8
    	five_five(5,5,"5x5"),	// puzzle 10
    	six_three(6,3,"6x3"),	// puzzle 11
    	six_four(6,4,"6x4"),	// puzzle 9
    	six_five(6,5,"6x5"),	// puzzle 13
    	six_six(6,6,"6x6"),		// puzzle 15
    	seven_four(7,4,"7x4"),	// puzzle 12
    	seven_five(7,5,"7x5"),	// puzzle 14
    	seven_six(7,6,"7x6"),
    	eight_five(8,5,"8x5"),
    	ten_four(4,10,"4x10"),	// puzzle 16
    	ten_eight(10,8,"10x8");

    	int width;
    	int height;
    	String name;
    	targetType(int w,int h,String n)
    	{	width = w;
    		height = h;
    		name = n;
    	}
    }

    
	
    static final int MOVE_PICK = 204; // pick a chip from a pool
    static final int MOVE_DROP = 205; // drop a chip
    static final int MOVE_PICKB = 206; // pick from the board
    static final int MOVE_DROPB = 207; // drop on the board
    static final int MOVE_RACK_BOARD = 209;	// move from rack to board
    static final int MOVE_BOARD_BOARD = 210;	// move board to board
    static final int MOVE_ROTATE = 211;
	
    static final String Snakes_SGF = "Snakes"; // sgf game name
    static final String[] SNAKESGRIDSTYLE = { "1", null, "A" }; // left and bottom numbers

 
    // file names for jpeg images and masks
    static final String ImageDir = "/snakes/images/";
	// sounds
    static final int BACKGROUND_TILE_INDEX = 0;
    static final int BACKGROUND_REVIEW_INDEX = 1;
    static final String TextureNames[] = 
    	{ "background-tile" ,
    	  "background-review-tile"};

}