/**
 * Entity implementation class for Entity: Thread
 *
 * TODO Add a description to the class Thread
 */
package de.rwth.swc.teaching.forumsystem.simple.domain;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@NamedQueries({
		@NamedQuery(name = "getAllThread", query = "SELECT thread FROM Thread thread"),
		@NamedQuery(name = "getThreadByThreadId", query = "SELECT thread FROM Thread thread WHERE thread.threadId = :threadId")
})
@Entity
public class Thread{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int threadId;

	private String threadTitle;

	private boolean locked;

	@OneToMany(cascade = {CascadeType.MERGE}, mappedBy = "thread")
	private List<Post> posts = new LinkedList<Post>();

	public Thread() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getThreadId() {
		return this.threadId;
	}

	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}

	public String getThreadTitle() {
		return this.threadTitle;
	}

	public void setThreadTitle(String threadTitle) {
		this.threadTitle = threadTitle;
	}

	public boolean getLocked() {
		return this.locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public List<Post> getPosts() {
		return this.posts;
	}

	public void addPostToPosts(Post posts) {
		this.posts.add(posts);
	}

	public void clearPosts() {
		this.posts.clear();
	}

	public void removePostFromPosts(Post posts) {
		this.posts.remove(posts);
	}

	/**
	 * Special implementation of the equals method due to some issues with lazy fetching and
	 * creation of entities.
	 * See http://burtbeckwith.com/blog/?p=53 for more details.
	 * {@inheritDoc}
	 **/
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (Thread.class.isInstance(object)) {
			if (this.getId() != 0 && ((Thread) object).getId() != 0
					&& this.getId() == ((Thread) object).getId()) {
				return true;
			} else {
				return (

				this.getThreadId() == ((Thread) object).getThreadId()

				);
			}
		} else {
			return false;
		}
	}

	/**
	 * Special implementation of the hash code method due to some issues with lazy fetching and
	 * creation of entities.
	 * See http://burtbeckwith.com/blog/?p=53 for more details.
	 * {@inheritDoc}
	 **/
	@Override
	public int hashCode() {
		int hash = 1;

		hash = hash * 17 + this.getThreadId();

		return hash;
	}

}
