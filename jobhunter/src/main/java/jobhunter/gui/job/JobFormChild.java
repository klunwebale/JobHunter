/*
 * Copyright (C) 2014 Alejandro Ayuso
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package jobhunter.gui.job;

import java.util.Optional;

import javafx.scene.Parent;
import jobhunter.gui.FormChangeListener;

public interface JobFormChild<T> {

	Optional<Parent> load();
	
	abstract void changed();
	
	FormChangeListener<T> getListener();
	
	void setListener(FormChangeListener<T> listener);
	
}
