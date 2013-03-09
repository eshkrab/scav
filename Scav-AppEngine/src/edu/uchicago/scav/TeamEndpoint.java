package edu.uchicago.scav;

import edu.uchicago.scav.EMF;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Api(name = "teamendpoint")
public class TeamEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public CollectionResponse<Team> listTeam(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Team> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Team as Team");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Team>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Team obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Team> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	public Team getTeam(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		Team team = null;
		try {
			team = mgr.find(Team.class, id);
		} finally {
			mgr.close();
		}
		return team;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param team the entity to be inserted.
	 * @return The inserted entity.
	 */
	public Team insertTeam(Team team) {
		EntityManager mgr = getEntityManager();
		try {
			if (containsTeam(team)) {
				throw new EntityExistsException("Object already exists");
			}
			mgr.persist(team);
		} finally {
			mgr.close();
		}
		return team;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param team the entity to be updated.
	 * @return The updated entity.
	 */
	public Team updateTeam(Team team) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsTeam(team)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(team);
		} finally {
			mgr.close();
		}
		return team;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 * @return The deleted entity.
	 */
	public Team removeTeam(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		Team team = null;
		try {
			team = mgr.find(Team.class, id);
			mgr.remove(team);
		} finally {
			mgr.close();
		}
		return team;
	}

	private boolean containsTeam(Team team) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Team item = mgr.find(Team.class, team.getId());
			if (item == null) {
				contains = false;
			}
		} finally {
			mgr.close();
		}
		return contains;
	}

	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}

}
