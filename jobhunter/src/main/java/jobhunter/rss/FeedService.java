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

package jobhunter.rss;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * A JavaFX Service to update RSS feeds on request by the user.
 */
public class FeedService extends Service<Integer> {
	
	public FeedService() {
		super();
	}
	
	@Override
	protected Task<Integer> createTask() {
		return new ScheduledFeedService.FeedTask(true);
	}
	
}
