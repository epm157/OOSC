package de.rwth.swc.teaching.forumsystem.simple.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.rwth.swc.teaching.forumsystem.simple.domain.Post;
import de.rwth.swc.teaching.forumsystem.simple.exceptions.NotFoundException;

@Stateless
public class PostDAOBean implements PostDAOLocal {

	private @PersistenceContext(name = "forumsystem")
	EntityManager em;

	/**
	 * TODO Add a comment to this method.
	 */
	public void storePost(Post Post) {

		em.persist(Post);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void updatePost(Post Post) {

		em.merge(Post);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public void deletePost(Post Post) {

		em.remove(Post);

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public Post getPostByPostIdAndThreadId(int postId, int threadId)
			throws NotFoundException {

		Query query = em.createNamedQuery("getPostByPostIdAndThreadId");

		query.setParameter("postId", postId);

		query.setParameter("threadId", threadId);

		try {
			return (Post) query.getSingleResult();
		} catch (NoResultException e) {
			throw new NotFoundException("Post", "PostId" + postId + "ThreadId"
					+ threadId);
		}

	}

	/**
	 * TODO Add a comment to this method.
	 */
	public boolean existsPost(int postId, int threadId) {

		try {
			if (this.getPostByPostIdAndThreadId(postId, threadId) != null) {
				return true;
			} else {
				return false;
			}
		} catch (NotFoundException e) {
			return false;
		}

	}

}
