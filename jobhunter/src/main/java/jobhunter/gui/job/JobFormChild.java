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

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import jobhunter.gui.FormChangeListener;
import jobhunter.gui.Localizable;
import jobhunter.utils.JavaFXUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface JobFormChild<T> extends Localizable, Initializable {
	
	static final Logger l = LoggerFactory.getLogger(JobFormChild.class);

	abstract void changed();
	
	FormChangeListener<T> getListener();
	
	void setListener(FormChangeListener<T> listener);
	
	String getFXMLPath();
	
	default Optional<Parent> show() {
		return JavaFXUtils.loadFXML(this, getFXMLPath(), getBundle());
	}
	
}
