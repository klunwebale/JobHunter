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

package jobhunter.persistence;

import java.io.File;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import jobhunter.models.Job;
import jobhunter.models.Profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum ProfileRepository {

	_INSTANCE;

	private static final Logger l = LoggerFactory.getLogger(ProfileRepository.class);

	public static ProfileRepository instanceOf() {
		return _INSTANCE;
	}

	private Profile current;
	
	private ProfileRepositoryListener listener;

	public void saveJob(final Job job) {
		l.debug("Saving Job: ");
		l.debug(job.toString());
		current.addJob(job);
		fireEvent();
	}

	public void deleteJob(final Job job) {
		current.getJobs().stream()
			.filter(j -> j.equals(job))
			.forEach(j -> j.setActive(Boolean.FALSE));
		fireEvent();
		
	}

	public Optional<Job> getJob(final ObjectId id) {
		return current.getJobs().stream().filter(j -> j.getId().equals(id))
				.findFirst();
	}

	public Set<Job> getActiveJobs() {
		return current.getJobs().stream().filter(j -> j.getActive()).sorted()
				.collect(Collectors.toSet());
	}

	public Set<Job> getJobsByDate() {
		return current.getJobs().stream().filter(j -> j.getActive())
				.sorted(new Job.DateComparator()).collect(Collectors.toSet());
	}

	public Set<Job> getAllJobs() {
		return current.getJobs();
	}

	public Profile getProfile() {
		if (current == null)
			current = Profile.instanceOf();
		return current;
	}

	public void clear() {
		current = Profile.instanceOf();
		fireEvent();
	}

	public Set<String> getAutocompletePositions() {
		Set<String> positions = new HashSet<>();
		getProfile().getJobs().forEach(job -> positions.add(job.getPosition()));
		return positions;
	}

	public Set<String> getAutocompleteCompanies() {
		Set<String> companies = new HashSet<>();
		getProfile().getJobs().forEach(
				j -> companies.add(j.getCompany().getName()));
		return companies;
	}

	public void load(final File file) {
		Optional<Profile> profile = Persistence.read(file);

		if (profile.isPresent())
			this.current = profile.get();
		
		fireEvent();
	}

	public void save(final File file) {
		Persistence.save(current, file);
	}
	
	private void fireEvent() {
		if(this.listener != null)
			this.listener.changed();
	}

	public ProfileRepositoryListener getListener() {
		return listener;
	}

	public void setListener(ProfileRepositoryListener listener) {
		this.listener = listener;
	}

}
