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
package lehavre.model.buildings.special;

import lehavre.main.*;
import lehavre.model.*;
import lehavre.model.buildings.*;
import lehavre.util.*;

/**
 *
 *	The <code>DepartmentOfEconomics</code> class represents the Department of Economics (GH19).
 *
 *	@author Grzegorz Kobiela
 *	@version 1.00 2009/12/28
 */
public final class DepartmentOfEconomics
extends Building
{
	static final long serialVersionUID =1L;
	/** The building properties. */
	private final double OUTPUT_IRON = getProperty("iout", 1);
	private final double OUTPUT_FRANC = getProperty("$out", 2);

	/** The instance. */
	private static DepartmentOfEconomics instance = null;

	/** Creates a new <code>DepartmentOfEconomics</code> instance. */
	private DepartmentOfEconomics() {
		super(Buildings.$GH19);
	}

	/**
	 *	Creates a new instance or returns the existing one.
	 *	@return the instance
	 */
	public static DepartmentOfEconomics getInstance() {
		if(instance == null) instance = new DepartmentOfEconomics();
		return instance;
	}

	/**
	 *	The active player enters the building and uses its function.
	 *	@param control the control object
	 *	@return true after usage
	 */
	public boolean use(LeHavre control) {
		Player player = control.getGameState().getActivePlayer();
		double iron = 0, franc = 0;
		for(Building building: player.getBuildings()) {
			if(building.isIndustrial()) iron += OUTPUT_IRON;
			if(building.isEconomic()) franc += OUTPUT_FRANC;
		}
		GoodsList goods = new GoodsList();
		if(iron > 0) goods.add(iron, Good.Iron);
		if(franc > 0) goods.add(franc, Good.Franc);
		control.receive(goods);
		return true;
	}

	/**
	 *	Returns true if the active player is able to use the building.
	 *	@param control the control object
	 */
	public boolean isUsable(LeHavre control) {
		Player player = control.getGameState().getActivePlayer();
		for(Building building: player.getBuildings()) if(building.isIndustrial() || building.isEconomic()) return true;
		return false;
	}
}